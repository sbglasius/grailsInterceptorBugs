package interceptorbug


class NotCalledInterceptor {

    NotCalledInterceptor() {
        matchAll()
    }

    boolean before() {
        request.setAttribute('interceptor-called', 'yes I was called')
        true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
