package Vista;


import Modelo.Ejercito;
import Modelo.Personaje;
import Modelo.clases.subClases.*;
import Vista.VistaJuego;
import org.Controlador.JuegoControlador;

public class Juego {
    public static void main(String[] args) {
        //creamos los ejercitos de heroes y bestias
        Ejercito ejercitoHeroes = new Ejercito();
        Ejercito ejercitoBestias = new Ejercito();

        //Héroes
        ejercitoHeroes.agregarPersonaje(new Elfo("Legolas", 150, 30));
        ejercitoHeroes.agregarPersonaje(new Humano("Aragorn", 150, 50));
        ejercitoHeroes.agregarPersonaje(new Humano("Boromir", 100, 60));
        ejercitoHeroes.agregarPersonaje(new Humano("Gandalf", 300, 30));
        ejercitoHeroes.agregarPersonaje(new Hobbit("Frodo", 100, 10));


        //Bestias
        ejercitoBestias.agregarPersonaje(new Orco("Lurtz", 200, 60));
        ejercitoBestias.agregarPersonaje(new Orco("Shagrat", 220, 50));
        ejercitoBestias.agregarPersonaje(new Trasgo("Uglúk", 120, 30));
        ejercitoBestias.agregarPersonaje(new Trasgo("Azog", 120, 30));
        ejercitoBestias.agregarPersonaje(new Trasgo("Mauhúr", 100, 30));

        //creamos el controlador del juego y la vista
        JuegoControlador controlador = new JuegoControlador(ejercitoHeroes, ejercitoBestias, new VistaJuego());

        //iniciamos el juego
        controlador.jugar();
    }
}
