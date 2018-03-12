def call(Map parameters = [:]){
	def ant = new AntBuilder()

	if(parameters.containsKey("p")){
		dir(parameters.p){
			return bat(returnStdout:true, script:"@echo off && \"${env.git}\" describe --abbrev=0")?.trim()
		}
	}

	return bat(returnStdout:true, script:"@echo off && \"${env.git}\" describe --abbrev=0")?.trim()
}
