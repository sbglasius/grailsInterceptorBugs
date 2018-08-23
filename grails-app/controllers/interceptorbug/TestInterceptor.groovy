package interceptorbug

import grails.artefact.Interceptor

class TestInterceptor implements Interceptor{

    TestInterceptor() {
        match(controller: 'test', action: '*')
    }

    boolean before() {
        println "Before!!!!"
        if (controllerName) {
            request.setAttribute('controllerNameSet', 'controller name set')
        }
        if (actionName) {
            request.setAttribute('actionNameSet', 'action name set')
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
