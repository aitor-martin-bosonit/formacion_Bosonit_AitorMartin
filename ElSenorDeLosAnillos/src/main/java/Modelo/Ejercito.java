package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Ejercito {
    private List<Personaje> personajes;

    public Ejercito() {
        personajes = new ArrayList<>();
    }

    public void agregarPersonaje(Personaje personaje) {
        personajes.add(personaje);
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void crearPersonaje(Personaje personaje) {
        personajes.add(personaje);
    }

    public void modificarPersonaje(Personaje personaje, String nuevoNombre, int nuevaVida, int nuevaArmadura) {
        if (personajes.contains(personaje)) {
            personaje.setNombre(nuevoNombre);
            personaje.setVida(nuevaVida);
            personaje.setArmadura(nuevaArmadura);
        }
    }

    public void eliminarPersonaje(Personaje personaje) {
        if (personajes.contains(personaje)) {
            personajes.remove(personaje);
        }
    }



}
