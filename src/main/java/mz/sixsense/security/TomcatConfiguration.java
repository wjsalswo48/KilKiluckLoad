//package mz.sixsense.security;
//
//import org.apache.catalina.connector.Connector;
//import org.apache.coyote.ajp.AbstractAjpProtocol;
//import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
//import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class TomcatConfiguration {
//
//    @Bean
//    public ServletWebServerFactory servletContainer() {
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
//        tomcat.addAdditionalTomcatConnectors(createAjpConnector());
//        return tomcat;
//    }
//
//    private Connector createAjpConnector() {
//        Connector ajpConnector = new Connector("AJP/1.3");
//        ajpConnector.setPort(8009);
//        ajpConnector.setSecure(false);
//        ajpConnector.setAllowTrace(false);
//        ajpConnector.setScheme("http");
//        ((AbstractAjpProtocol) ajpConnector.getProtocolHandler()).setSecretRequired(false); // 해당 줄을 추가함
//        return ajpConnector;
//    }
//}