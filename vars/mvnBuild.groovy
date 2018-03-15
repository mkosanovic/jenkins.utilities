def call(Map parameters = [:]){
    def maven = parameters.mvn ? parameters.mvn : 'MAVEN'
    def buildSteps = parameters.buildSteps ? parameters.buildSteps : 'clean install'
    
    if(parameters.jdk){
        env.JAVA_HOME=parameters.jdk
    }

    bat "\"${tool maven}\\bin\\mvn.cmd\" ${buildSteps}"
}