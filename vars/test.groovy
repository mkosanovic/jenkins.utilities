def call(){
    def ant = new AntBuilder()
    println ant.project.buildListeners[0].messageOutputLevel 
    ant.echo("test")
}