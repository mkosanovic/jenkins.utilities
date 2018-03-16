def call(Map parameters = [:]){
	// String repo, String relativeTargetDir, String credentials, String branch= '*/master',  String refspec='+refs/heads/master:refs/remotes/origin/master'){

	def credentials = parameters.containsKey("credentials") ? parameters.credentials : "$env.GIT_CREDENTIALS"
	def refspec = "+refs/tags/v*:refs/remotes/tags/v*"
	def branch = "*/tags/v*"
	def verbose = parameters.verbose ? parameters.verbose: false
	// def refspec = "+refs/heads/master:refs/remotes/origin/master"
	// def branch = "*/master"
	// def checkoutLatest = parameters.latest ? parameters.latest : false	
	def repo = parameters.repo

	if(!credentials?.trim()){
		throw new IllegalArgumentException("credentials")
	}	

	if(!repo){
		throw new IllegalArgumentException("repo")
	}

	if(parameters.containsKey("refspec")){
		refspec = parameters.refspec
	}

	if(parameters.branch){				
		if(verbose){ echo "Setting branch parameter to ${parameters.branch}" }

		branch = parameters.branch		
	}

	// if(checkoutLatest){
	// 	checkout changelog:true, poll:true, scm:[
	// 		$class:'GitSCM',
	// 		branches: [[name: "*/tags/v*"]],
	// 		doGenerateSubmoduleConfigurations: false, 
	// 		submoduleCfg: [], 
	// 		userRemoteConfigs:[[credentialsId: "$env.GIT_CREDENTIALS", refspec: "+refs/tags/v*:refs/remotes/tags/v*", url: repo]]
	// 	]

	// 	return
	// }

	if(parameters.containsKey("relativeTargetDir"))	
	{
		if(verbose) echo "Using relativeDirectory ${parameters.relativeTargetDir}"
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
