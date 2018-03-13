def call(Map parameters = [:]){
	if(parameters.containsKey("p")){
		dir(parameters.p){
			return bat(returnStdout:true, script:"@echo off && \"${env.git}\" describe --abbrev=0")?.trim()
		}
	}

	return bat(returnStdout:true, script:"@echo off && \"${env.git}\" describe --abbrev=0")?.trim()
}
