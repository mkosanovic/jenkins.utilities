def call(Map parameters = [:]){
    if(!parameters.containsKey("task")){
        throw new IllegalArgumentException("Missing argument [task]")
    }
    if(parameters.containsKey("project")){
        throw new IllegalArgumentException("Missing argument [project]")
    }
    if(parameters.task.equals("deploy") || !parameters.containsKey("file")){
        throw new IllegalArgumentException("Missing argument [file] for task [deploy]")
    }
    
    def task =parameters.task
    def project = parameters.project
    def file = parameters.containsKey("file") ? "-Dfile=" + parameters.file : "";
    def tomcatUrl = resolvePath(target:"services-url")
    def managerUrl = "-Dtomcat-manager-url=${tomcatUrl}/manager/text"
    def buildFile = "${env.TOMCAT_BUILD_FILE}"
    
    bat "\"${tool ant}\\bin\\ant.bat\" -f \"${env.TOMCAT_BUILD_FILE}\" ${task} ${managerUrl} -Dproject-name=${project} ${file}"
}