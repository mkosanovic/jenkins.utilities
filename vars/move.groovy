def call(Map parameters = [:]){
	def ant = new AntBuilder()
	def baos = new ByteArrayOutputStream()
	def printStream = new PrintStream(baos)

	// redirect ant output
	ant.project.getBuildListeners().each{   
		it.setOutputPrintStream(printStream)
	}

	def file = parameters.file
	def toFile = parameters.toFile
	def fromDir = parameters.fromDir
	def toDir = parameters.toDir
	def flatten = parameters.flatten ? parameters.flatten : true // true by default
	def filter = parameters.filter
	def failOnException = parameters.containsKey("failOnException") ? parameters.failOnException : false

	try{
		if(file && toFile)
		{
			ant.move(file:file, toFile:toFile,failonerror:failOnException,verbose:true)
		}
		else if(file && toDir)
		{
			ant.move(file:file, toDir:toDir,flatten:flatten, failonerror:failOnException, verbose:true)
		}
		else if(fromDir && toDir && filter)
		{
			println "Not implemented"
			// antFunction(toDir,fromDir,filter,flatten)
		}
		else{
			echo "Incorrect input arguments"
		}
	}catch(e){
		println e.getMessage()

		if(failOnException){ throw e; }
	}
	finally{
		println baos.toString()
		printStream.close();
	}
}

def antFunction(String toDir, String fromDir, String filter, boolean flatten){	
	def ant =new AntBuilder()

	ant.move(toDir:toDir,flatten:flatten, verbose:true){
		fileset(dir:fromDir){
			include(name:filter)
			exclude(name:"**/.git/**/*.*")
		}
	}
}
