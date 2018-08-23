package interceptorbug

import grails.testing.web.interceptor.InterceptorUnitTest
import spock.lang.Specification

class ActionNameInterceptorSpec extends Specification implements InterceptorUnitTest<ActionNameInterceptor> {

    void "Test interceptor actionName should be set to default index"() {
        given:
        TestController controller = mockController(TestController) as TestController

        expect:
        interceptor // See NotCalledInterceptorSpec

        when:
        withInterceptors([controller: 'test']) {
            controller.index()
        }
        then: "the controller name is set"
            request.getAttribute('controllerNameSet') == 'controller name set'
        and: "the action name should be default index, but is not set"
            request.getAttribute('actionNameSet') == 'action name set'
    }

    void "Test interceptor actionName should be set to specific name"() {
        given:
        TestController controller = mockController(TestController) as TestController

        expect:
        interceptor // See NotCalledInterceptorSpec

        when:
        withInterceptors([controller: 'test', action: 'other']) {
            controller.index()
        }
        then: "the controller name is set"
            request.getAttribute('controllerNameSet') == 'controller name set'
        and: "the action name should be default index, but is not set"
            request.getAttribute('actionNameSet') == 'action name set'
    }

    void "Test interceptor actionName should be set to specific name - hacked to pass"() {
        given:
        TestController controller = mockController(TestController) as TestController

        and: "hack to set action name"
        webRequest.setActionName('other')

        expect:
        interceptor // See NotCalledInterceptorSpec

        when:
        withInterceptors([controller: 'test', action: 'other']) {
            controller.index()
        }
        then: "the controller name is set"
            request.getAttribute('controllerNameSet') == 'controller name set'
        and: "the action name is set"
            request.getAttribute('actionNameSet') == 'action name set'
    }

    void "Test interceptor params should be set"() {
        given:
        TestController controller = mockController(TestController) as TestController

        and: "hack to set action name"
        webRequest.setActionName('other')

        expect:
        interceptor // See NotCalledInterceptorSpec

        when:
        withInterceptors([controller: 'test', action: 'other']) {
            params.test = 'is set'
            controller.index()
        }
        then: "the controller name is set"
            request.getAttribute('controllerNameSet') == 'controller name set'
        and: "the action name is set - because it's hacked to be so"
            request.getAttribute('actionNameSet') == 'action name set'
        and: "the param should be set"
            request.getAttribute('paramSet') == 'param set'
    }

    void "Test interceptor params should be set - alternative way of setting params"() {
        given:
        TestController controller = mockController(TestController) as TestController

        and: "hack to set action name"
        webRequest.setActionName('other')

        expect:
        interceptor // See NotCalledInterceptorSpec

        when:
        withInterceptors([controller: 'test', action: 'other', params: [test: 'is set']]) {
            controller.index()
        }
        then: "the controller name is set"
            request.getAttribute('controllerNameSet') == 'controller name set'
        and: "the action name is set - because it's hacked to be so"
            request.getAttribute('actionNameSet') == 'action name set'
        and: "the param should be set"
            request.getAttribute('paramSet') == 'param set'
    }

    void "Test interceptor params should be set - hacked to pass"() {
        given:
        TestController controller = mockController(TestController) as TestController

        and: "hack to set action name"
        webRequest.setActionName('other')
        and: "hack setting params in webRequest"
        webRequest.params.test = 'is set'

        expect:
        interceptor // See NotCalledInterceptorSpec

        when:
        withInterceptors([controller: 'test', action: 'other']) {
            params.test = 'is set'
            controller.index()
        }
        then: "the controller name is set"
            request.getAttribute('controllerNameSet') == 'controller name set'
        and: "the action name is set - because it's hacked to be so"
            request.getAttribute('actionNameSet') == 'action name set'
        and: "the param should be set"
            request.getAttribute('paramSet') == 'param set'
    }
}

