package com.formacion.block6pathvariableheaders;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/peticionget1")
public class PeticionGet1 {

    @GetMapping("/getPersona/{id}")
    public String getPersona(@PathVariable("id") String id) {

        return id;
    }

}
