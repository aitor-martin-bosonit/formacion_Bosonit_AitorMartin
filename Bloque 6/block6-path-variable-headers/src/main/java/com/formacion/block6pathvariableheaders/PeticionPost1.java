package com.formacion.block6pathvariableheaders;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/peticionpost1")
public class PeticionPost1 {

    @PostMapping("/addPersona")
    public Persona addPersona(@RequestBody Persona persona) {

        return persona;
    }

}
