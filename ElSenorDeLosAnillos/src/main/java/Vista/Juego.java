package Vista;


import Modelo.Ejercito;
import Modelo.clases.subClases.*;
import org.Controlador.JuegoControlador;

public class Juego {
    public static void main(String[] args) {

        Ejercito ejercitoHeroes = new Ejercito();
        Ejercito ejercitoBestias = new Ejercito();


        ejercitoHeroes.agregarPersonaje(new Elfo("Legolas", 150, 30, "Elfo"));
        ejercitoHeroes.agregarPersonaje(new Humano("Aragorn", 150, 50, "Humano"));
        ejercitoHeroes.agregarPersonaje(new Humano("Boromir", 100, 60, "Humano"));
        ejercitoHeroes.agregarPersonaje(new Humano("Gandalf", 300, 30, "Humano"));
        ejercitoHeroes.agregarPersonaje(new Hobbit("Frodo", 100, 10, "Hobbit"));
        ejercitoHeroes.agregarPersonaje(new Hobbit("Sam", 100, 20, "Hobbit"));



        ejercitoBestias.agregarPersonaje(new Orco("Lurtz", 200, 60, "Orco"));
        ejercitoBestias.agregarPersonaje(new Orco("Shagrat", 220, 50, "Orco"));
        ejercitoBestias.agregarPersonaje(new Trasgo("Uglúk", 120, 30, "Trasgo"));
        ejercitoBestias.agregarPersonaje(new Trasgo("Azog", 120, 30, "Trasgo"));
        ejercitoBestias.agregarPersonaje(new Trasgo("Mauhúr", 100, 30, "Trasgo"));
        ejercitoBestias.agregarPersonaje(new Trasgo("Trasgo1", 100, 20, "Trasgo"));


        JuegoControlador controlador = new JuegoControlador(ejercitoHeroes, ejercitoBestias, new VistaJuego());


        controlador.jugar();
    }
}
