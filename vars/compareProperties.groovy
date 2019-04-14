def call(Map parameters = [:]){    
    if(!parameters.containsKey("source")){
        throw new IllegalArgumentException("Missing source")
    }

    if(!parameters.containsKey("destination")){
        throw new IllegalArgumentException("Missing destination")
    }

    def source = parameters.source
    def destination = parameters.destination
    def failBuild = parameters.containsKey("failBuild") ? parameters.failBuild.toBoolean() : true

    Properties sourceProp = new Properties()
    Properties destinationProp = new Properties()

    DataInputStream sourceDis = new File(source).newDataInputStream();
    DataInputStream destDis = new File(destination).newDataInputStream();

    try{
        sourceProp.load(sourceDis)
        destinationProp.load(destDis)
    }finally{
        destDis.close()
        sourceDis.close()
    }

    Set<String> propSet = sourceProp.stringPropertyNames()
    propSet.removeAll(destinationProp.stringPropertyNames())

    if(propSet.size() > 0){
        
        propSet.each { println it }

        if(failBuild){ error("Missing properties") }
    }
}