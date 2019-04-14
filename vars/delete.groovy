def call(Map parameters = [:]){
	def ant = new AntBuilder()
	def baos = new ByteArrayOutputStream()
	def printStream = new PrintStream(baos)

	// redirect ant output
	ant.project.getBuildListeners().each{   
		it.setOutputPrintStream(printStream)
	}
	
	def file = parameters.file
	def dir = parameters.dir
	def quiet = parameters.quiet ? parameters.quiet : true

	try{
		if(file)
		{
			ant.delete(file:file, quiet:quiet, verbose:true)
		}
		else if(dir)
		{
			ant.delete(dir:dir, quiet:quiet,verbose:true)
		}
		else{
			echo "Incorrect input arguments"
		}
	}
	catch(Exception e){
		println e.getMessage()

		if(failOnException){ throw e; }
	}
	finally{
		println baos.toString()
		printStream.close();
	}
}
