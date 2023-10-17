package Modelo.clases.subClases;

import Modelo.Personaje;
import Modelo.clases.Heroe;

public class Hobbit extends Heroe {
    public Hobbit(String nombre, int vida, int armadura, String raza) {

        super(nombre, vida, armadura, raza);

    }


    public int atacar(Personaje objetivo) {

        int dado1 = (int) (Math.random() * 101);
        int dado2 = (int) (Math.random() * 101);


        int valorAtaque = Math.max(dado1, dado2) - 5;


        if (objetivo instanceof Trasgo) {

            valorAtaque = Math.max(valorAtaque, 0);
        }


        if (valorAtaque > objetivo.getArmadura()) {
            int danio = valorAtaque - objetivo.getArmadura();
            objetivo.recibirDanio(danio);

            setAtaque(danio);
        }
        return dado1;
    }
}
