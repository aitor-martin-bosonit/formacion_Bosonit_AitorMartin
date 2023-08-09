package com.formacion.block6personcontrollers;
import org.springframework.stereotype.Service;
public class ServicioPersonaMetodos extends ServicioPersona {
    private Persona persona;

    @Override
    public Persona agregarPersona(String nombre, String poblacion, int edad) {
        persona = new Persona();
        persona.setNombre(nombre);
        persona.setPoblacion(poblacion);
        persona.setEdad(edad);
        return persona;

    }

    @Override
    public Persona obtenerPersona() {
        return persona;
    }
}
