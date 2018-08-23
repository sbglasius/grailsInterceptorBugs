package interceptorbug

import grails.testing.web.interceptor.InterceptorUnitTest
import spock.lang.Specification

class NotCalledInterceptorSpec extends Specification implements InterceptorUnitTest<NotCalledInterceptor> {

    void "Expect that interceptor gets called"() {
        setup:
            TestController controller = mockController(TestController) as TestController

        when:
            withInterceptors(controller:"test") {
                controller.index()
            }

        then: "This test should pass, but does not"
            request.getAttribute('interceptor-called') == 'yes I was called'
    }

    void "Second time around it is initialized and it does get called"() {
        setup:
            TestController controller = mockController(TestController) as TestController

        when: "calling the interceptor again"
            withInterceptors(controller: "test") {
                controller.index()
            }

        then: "it is already registered with handleInterceptor (see: InterceptorUnitTest.groovy:40)"
            request.getAttribute('interceptor-called') == 'yes I was called'
    }
}
