def call(Map parameters = [:]){
	if(env.gitlabAfter){
		return bat(returnStdout:true, script:"@echo off && \"${env.git}\" describe --tag ${env.gitlabAfter} --exact-match")?.trim()
	}

	if(parameters.containsKey("p")){
		dir(parameters.p){
			return bat(returnStdout:true, script:"@echo off && \"${env.git}\" describe --tags --abbrev=0")?.trim()
		}
	}

	return bat(returnStdout:true, script:"@echo off && \"${env.git}\" describe --tags --abbrev=0")?.trim()
}
