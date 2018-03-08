def call(String repo, String relativeTargetDir, String credentials, String branch= '*/master',  String refspec=refspec){
	credentials = credentials?.trim();
	credentials = credentials ? credentials : "$env.GIT_CREDENTIALS";

	if(!credentials?.trim()){
		// throw argument exception	
	}

	if(!repo?.trim()){
		// throw argument exception
	}	

	if(relativeTargetDir?.trim())
	{
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
            userRemoteConfigs:[[credentialsId: credentials, refspec: refspec, url: repo]
        ]
	}	
}
