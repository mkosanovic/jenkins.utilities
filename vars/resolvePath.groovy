def call(Map parameters = [:]){
    if(!parameters.containsKey("target")){
        throw new IllegalArgumentException("Missing argument [target]")
    }

    if(!parameters.containsKey("profile") && !"${env.PROFILE}" && (profile.equals("cges-dev") || profile.equals("eso-dev"))){
        throw new IllegalArgumentException("Missing argument [profile]")
    }

    def profile = parameters.containsKey("profile") ? parameters.profile : env.PROFILE
    def target = parameters.target

    switch(target){
        case "portlets":
            return profile.equals("cges-dev") ? env.CGES_LIFERAY_HOME : env.ESO_LIFERAY_HOME
        case "services":
            return profile.equals("cges-dev") ? env.CGES_SERVICE_CATALINA_HOME : env.ESO_SERVICE_CATALINA_HOME
        case "services-url":
            return profile.equals("cges-dev") ? env.CGES_SERVICES_URL : env.ESO_SERVICES_URL
    }
}