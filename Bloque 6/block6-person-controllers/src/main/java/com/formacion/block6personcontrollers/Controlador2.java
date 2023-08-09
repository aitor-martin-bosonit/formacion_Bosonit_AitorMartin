package com.formacion.block6personcontrollers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controlador2")
public class Controlador2 {
    private final ServicioPersona personaService;
    public Controlador2(ServicioPersona personaService) {this.personaService = personaService;}

    @GetMapping("/getPersona")
    public Persona getPersona() {
        Persona persona = personaService.obtenerPersona();
        persona.setEdad(persona.getEdad() * 2);
        return persona;
    }

}
