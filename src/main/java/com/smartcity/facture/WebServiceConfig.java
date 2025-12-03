package com.smartcity.facture;

import com.smartcity.facture.services.ClientServiceImp;
import jakarta.xml.ws.Endpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebServiceConfig {

    private ClientServiceImp clientService;

    @Autowired
    WebServiceConfig(ClientServiceImp clientService){
        this.clientService = clientService;
    }

    @Bean
    public Endpoint ClientEndpoint() {
        Endpoint endpoint = Endpoint.create(clientService);
        endpoint.publish("http://localhost:8080/services/Client");
        return endpoint;
        //http://localhost:8080/services/Client?wsdl
    }

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
