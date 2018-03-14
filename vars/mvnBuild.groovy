def call(Map parameters = [:]){

    stage('Test'){

    }
    
    def maven = parameters.mvn ? parameters.mvn : 'MAVEN'
    def buildSteps = parameters.buildSteps ? parameters.buildSteps : 'clean install'

    bat "\"${tool maven}\\bin\\mvn.cmd\" ${buildSteps}"
}