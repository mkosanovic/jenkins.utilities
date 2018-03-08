def call(Map parameters = [:]){
	// String repo, String relativeTargetDir, String credentials, String branch= '*/master',  String refspec='+refs/heads/master:refs/remotes/origin/master'){

	def credentials = parameters.containsKey("credentials") ? parameters.credentials : "$env.GIT_CREDENTIALS"
	def refspec = "+refs/heads/master:refs/remotes/origin/master"
	def branch = "*/master"

	if(!credentials?.trim()){
		// throw new IllegalArgumentException("credentials")​
	}	

	if(!parameters.containsKey("repo")){
		// throw new IllegalArgumentException("repo")​
	}

	if(parameters.containsKey("refspec")){
		refspec = parameters.refspec
	}

	if(parameters.containsKey("branch")){
		branch = parameters.branch
	}


	def repo = parameters.repo

	if(parameters.contains("relativeTargetDir"))	
	{
		def relativeTargetDir = parameters.relativeTargetDir

        checkout changelog:true, poll:true, scm:[
            $class:'GitSCM',
            branches: [[name: branch]],
            doGenerateSubmoduleConfigurations: false, 
            extensions: [[$class: 'RelativeTargetDirectory', 
                          relativeTargetDir: relativeTargetDir]],
            submoduleCfg: [], 
            userRemoteConfigs:[[credentialsId: credentials, refspec: refspec, url: repo]]
        ]
	}
	else
	{
        checkout changelog:true, poll:true, scm:[
            $class:'GitSCM',
            branches: [[name: branch]],
            doGenerateSubmoduleConfigurations: false, 
            submoduleCfg: [], 
            userRemoteConfigs:[[credentialsId: credentials, refspec: refspec, url: repo]]
        ]
	}	
}
