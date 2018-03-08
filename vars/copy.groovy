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
		ant.copy(file:file, toFile:toFile,overwrite:true,flatten:flatten, verbose:true)
	}
	else if(file && toDir)
	{
		ant.copy(file:file, toDir:toFile,overwrite:true,flatten:flatten, verbose:true)
	}
	else if(fromDir && toDir && filter)
	{
		antFunction(toDir,fromDir,filter,flatten)
	}
	else{
		echo "Incorrect input arguments"
	}
}

def antFunction(String toDir, String fromDir, String filter, boolean flatten){
	def ant =new AntBuilder()

	// ant.copy(toDir:toDir,overwrite:true,flatten:flatten, verbose:true){		
	// 	fileset(dir:fromDir){
	// 		include(name:filter)
	// 		exclude(name:"**/.git/**/*.*")
	// 	}
	// }

	def t = ant.copy(toDir:toDir,overwrite:true,flatten:flatten, verbose:true)
	t.fileset(dir:fromDir)
	{
		include(name:filter)
		exclude(name:"**/.git/**/*.*")
	}
}
