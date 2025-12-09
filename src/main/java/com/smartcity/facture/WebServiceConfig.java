package com.smartcity.facture;

import com.smartcity.facture.services.ServiceFactureAPI;
import jakarta.xml.ws.Endpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebServiceConfig {

    private ServiceFactureAPI serviceFactureAPI;

    @Autowired
    WebServiceConfig(ServiceFactureAPI serviceFactureAPI){
        this.serviceFactureAPI = serviceFactureAPI;
    }

    @Bean
    public Endpoint ClientEndpoint() {
        Endpoint endpoint = Endpoint.create(serviceFactureAPI);
        endpoint.publish("http://localhost:8082/services/API");
        return endpoint;
        //http://localhost:8082/services/API?wsdl
    }

//    @Bean
//    public Endpoint ClientEndpoint() {
//        Endpoint endpoint = Endpoint.create(clientService);
//        endpoint.publish("http://localhost:8080/services/Client");
//        return endpoint;
//        //http://localhost:8080/services/Client?wsdl
//    }
//    @Bean
//    public Endpoint FactureEndpoint(FactureService factureService) {
//        Endpoint endpoint = Endpoint.create(factureService);
//        endpoint.publish("http://localhost:8080/services/Facture");
//        return endpoint;
//        //http://localhost:8080/services/Facture?wsdl
//    }

    // Other web service endpoints can be configured similarly
    /*
    @Bean
    public Endpoint OTHEREndpoint() {
        Endpoint endpoint = Endpoint.create(OTHERService);
        endpoint.publish("/OTHER");
        return endpoint;
        //http://localhost:8080/services/OTHER?wsdl
    }
     */

}
