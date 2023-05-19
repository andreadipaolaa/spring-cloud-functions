package com.example;

import com.example.dto.PersonaDTO;
import com.example.model.Persona;
import com.example.model.PersonaService;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
@Log4j2
public class HelloHandler {

    @Autowired
    private PersonaService personaService;

    @FunctionName("insert")
    public HttpResponseMessage execute(
            @HttpTrigger(name = "request", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<PersonaDTO>> request,
            ExecutionContext context) {
        context.getLogger().info("insert new value");
        log.debug("inserisco nuovo valore");
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
        log.debug("leggo tutti gli oggetti nel db");
        List<Persona> persone = personaService.findAll();
        return request
                .createResponseBuilder(HttpStatus.OK)
                .body(persone)
                .build();
    }
}
