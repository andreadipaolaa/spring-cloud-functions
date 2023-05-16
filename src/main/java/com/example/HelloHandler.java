package com.example;

import com.example.dto.PersonaDTO;
import com.example.model.Greeting;
import com.example.model.Persona;
import com.example.model.PersonaService;
import com.example.model.User;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
@Component
public class HelloHandler {

    @Autowired
    private PersonaService personaService;

    @FunctionName("insert")
    public HttpResponseMessage execute(
            @HttpTrigger(name = "request", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<PersonaDTO>> request,
            ExecutionContext context) {
        context.getLogger().info("insert new value");
        PersonaDTO personaDTO = request.getBody().get();
        personaService.saveUser(new Persona(personaDTO.getName(),personaDTO.getEmail()));
        return request
                .createResponseBuilder(HttpStatus.OK)
                .body(personaDTO)
                .build();
    }

    @FunctionName("findAll")
    public HttpResponseMessage findAll(
            @HttpTrigger(name = "request", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            ExecutionContext context) {
        context.getLogger().info("prova di find");
        List<Persona> persone = personaService.findAll();
        return request
                .createResponseBuilder(HttpStatus.OK)
                .body(persone)
                .build();
    }
}
