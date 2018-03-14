def call(Map parameters = [:]){
	def ant = new AntBuilder()

	def file = parameters.file
	def dir = parameters.dir
	def quiet = parameters.quiet ? parameters.quiet : true

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
