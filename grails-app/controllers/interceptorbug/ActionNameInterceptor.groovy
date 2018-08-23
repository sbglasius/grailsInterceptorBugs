package interceptorbug

class ActionNameInterceptor {

    ActionNameInterceptor() {
        matchAll()
    }

    boolean before() {
        println "$controllerName $actionName $params"
        if (controllerName) {
            request.setAttribute('controllerNameSet', 'controller name set')
        }
        if (actionName) {
            request.setAttribute('actionNameSet', 'action name set')
        }
        if(params.test == 'is set') {
            request.setAttribute('paramSet', 'param set')
        }
        return true
    }

    boolean after() {
        true
    }

    void afterView() {
        // no-op
    }
}
