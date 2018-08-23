package interceptorbug

import grails.testing.web.interceptor.InterceptorUnitTest
import grails.web.Controller
import spock.lang.Specification

class TestInterceptorSpec extends Specification implements InterceptorUnitTest<TestInterceptor> {

    void "Test interceptor names"() {
        given:
        TestController controller = mockController(TestController) as TestController

        when:
        withInterceptors([controller: 'test']) {
            controller.index()
        }
        then:
            request.getAttribute('controllerNameSet') == 'controller name set'
            request.getAttribute('actionNameSet') == 'action name set'
    }
}

