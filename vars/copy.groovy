def call(Map parameters = [:]){
	def ant = new AntBuilder()

	def file = parameters.file
	def toFile = parameters.toFile
	def fromDir = parameters.fromDir
	def toDir = parameters.toDir
	def flatten = parameters.flatten ? parameters.flatten : true // true by default

	if(file && toFile)
	{
		ant.copy(file:file, toFile:toFile,overwrite:true,flatten:flatten, verbose:true)
	}
	else if(file && toDir)
	{
		ant.copy(file:file, toDir:toFile,overwrite:true,flatten:flatten, verbose:true)
	}
	else if(fromDir && toDir)
	{
		script{
			ant.copy(toDir:toDir,overwrite:true,flatten:flatten, verbose:true){
				fileset(dir:fromDir){
					include(name:filter)
					exclude(name:"**/.git/**/*.*")
				}
			}
		}
	}
	else{
		echo "Incorrect input arguments"
	}
}
