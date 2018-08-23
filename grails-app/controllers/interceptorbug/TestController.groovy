package interceptorbug

class TestController {
    def index() {
        render "${request.controllerNameSet} ${request.actionNameSet}"
    }

    def other() {
        render "other action"
    }
}
