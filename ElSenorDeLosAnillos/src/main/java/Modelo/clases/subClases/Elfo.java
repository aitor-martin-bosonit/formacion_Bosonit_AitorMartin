package Modelo.clases.subClases;

import Modelo.Personaje;
import Modelo.clases.Heroe;

public class Elfo extends Heroe {
    public Elfo(String nombre, int vida, int armadura, String raza) {

        super(nombre, vida, armadura, raza);

    }


    public int atacar(Personaje objetivo) {

        int dado1 = (int) (Math.random() * 101);
        int dado2 = (int) (Math.random() * 101);


        int valorAtaque = Math.max(dado1, dado2);


        if (objetivo instanceof Orco) {
            valorAtaque += 10;
        }


        if (valorAtaque > objetivo.getArmadura()) {
            int danio = valorAtaque - objetivo.getArmadura();
            objetivo.recibirDanio(danio);

            setAtaque(danio);
        }
        return dado1;
    }

}
