def call(Map parameters = [:]){
	def ant = new AntBuilder()
	
	// redirect ant output
	ant.project.getBuildListeners().each{   
		it.setOutputPrintStream(System.out)
	}

	def file = parameters.file
	def toFile = parameters.toFile
	def fromDir = parameters.fromDir
	def toDir = parameters.toDir
	def flatten = parameters.flatten ? parameters.flatten : true // true by default
	def filter = parameters.filter
	def failOnException = parameters.containsKey("failOnException") ? parameters.failOnException : true

	try{
		if(file && toFile)
		{
			ant.copy(file:file, toFile:toFile,overwrite:true,flatten:flatten, verbose:true)
		}
		else if(file && toDir)
		{
			ant.copy(file:file, toDir:toDir,overwrite:true,flatten:flatten, verbose:true)
		}
		else if(fromDir && toDir && filter)
		{
			println "Not implemented"
			// antFunction(toDir,fromDir,filter,flatten)
		}
		else{
			println "Incorrect input arguments"
		}
	}catch(Exception e){
		println e.getMessage()

		if(failOnException){ throw e; }
	}
}

def antFunction(String toDir, String fromDir, String filter, boolean flatten){	
	def ant =new AntBuilder()

	ant.copy(toDir:toDir,overwrite:true,flatten:flatten, verbose:true){		
		fileset(dir:fromDir){
			include(name:filter)
			exclude(name:"**/.git/**/*.*")
		}
	}
}
