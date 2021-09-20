package tech.muyan

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.socket.config.annotation.EnableWebSocket
import tech.muyan.websocket.CustomWebSocketConfigurer

import groovy.transform.CompileStatic

@CompileStatic
@EnableScheduling
@EnableWebSocket
class Application extends GrailsAutoConfiguration implements CustomWebSocketConfigurer {
    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }
}
