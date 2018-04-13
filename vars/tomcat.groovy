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
 
    echo "${env.PROFILE}"

    // bat "\"${tool ant}\\bin\\ant.bat\" ${task} -Dproject-name=${project} ${file}"
}