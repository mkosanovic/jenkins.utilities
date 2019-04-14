def call(Map parameters = [:]){
	// String repo, String relativeTargetDir, String credentials, String branch= '*/master',  String refspec='+refs/heads/master:refs/remotes/origin/master'){
	def credentials = parameters.containsKey("credentials") ? parameters.credentials : "$env.GIT_CREDENTIALS"
	// def refspec = "+refs/tags/v*:refs/remotes/tags/v*"
	def branch = parameters.containsKey("branch") ? parameters.branch : "*/master"
	def verbose = parameters.verbose ? parameters.verbose: false
	def repo = parameters.repo
    def tag = branch.startsWith("refs/tags/") ? branch.replace("refs/tags/", "") : null
    def commit = parameters.containsKey("commit") ? parameters.commit : null
    
	if(!credentials?.trim()){
		throw new IllegalArgumentException("credentials")
	}	
	if(!repo){
		throw new IllegalArgumentException("repo")
	}

    checkout changelog:true, poll:true, scm:[
        $class:'GitSCM',
        branches: [[name: "${branch}"]],
        doGenerateSubmoduleConfigurations: false, 
        submoduleCfg: [], 
        userRemoteConfigs:[[credentialsId: "${credentials}", url: "${repo}"]]
    ]

    // if gitlab commit
    if(commit && sh(returnStatus: true, script: "git describe --tag ${commit} --exact-match") == 0){
        tag = sh(script:"git describe --tag ${commit} --exact-match", returnStdout: true)?.trim()
    }
    else if(commit){
        tag = commit?.substring(0, 6)
    }
    // if explicit tag
    else if(tag && sh(returnStatus: true, script: "git rev-parse ${tag}") == "0"){
        tag = sh(script:"git describe --tag ${tag} --exact-match", returnStdout: true)?.trim()
    }
    // use branch
    else {
        tag = sh(script:"git rev-parse HEAD", returnStdout: true)?.trim()?.substring(0, 6)
    }

    sh "git checkout ${tag}"

    return tag
}