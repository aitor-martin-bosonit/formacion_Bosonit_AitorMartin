package Clases;
import java.util.ArrayList;

public class Ejercito {

    public ArrayList<Personaje> personajes;

    public Ejercito() {
        personajes = new ArrayList<>();
    }

    public void reclutarPersonaje(Personaje personaje) {
        personajes.add(personaje);
    }

    public Personaje getPersonaje(int pos) {
        try {
            return personajes.get(pos);
        }
        catch(Exception e) {
            return null;
        }
    }

    public void comprobarEjercito() {
        int personajeMuerto = -1;

        for (int pos = 0; pos < personajes.size(); pos++)
            if (personajes.get(pos).estaMuerto())
                personajeMuerto = pos;

        if (personajeMuerto != -1)
            personajes.remove(personajeMuerto);

    }

    public boolean esDerrotado() {

        return personajes.size() == 0;
    }
}
