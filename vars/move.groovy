def call(Map parameters = [:]){
	def ant = new AntBuilder()

	def file = parameters.file
	def toFile = parameters.toFile
	def fromDir = parameters.fromDir
	def toDir = parameters.toDir
	def flatten = parameters.flatten ? parameters.flatten : true // true by default
	def filter = parameters.filter

	if(file && toFile)
	{
		ant.move(file:file, toFile:toFile,verbose:true)
	}
	else if(file && toDir)
	{
		ant.move(file:file, toDir:toDir,flatten:flatten, verbose:true)
	}
	else if(fromDir && toDir && filter)
	{
		println "Not implemented"
		// antFunction(toDir,fromDir,filter,flatten)
	}
	else{
		echo "Incorrect input arguments"
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