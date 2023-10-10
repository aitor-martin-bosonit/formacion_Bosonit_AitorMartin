package Modelo.clases.subClases;

import Modelo.Personaje;
import Modelo.clases.Heroe;

public class Elfo extends Heroe {
    public Elfo(String nombre, int vida, int armadura) {
        super(nombre, vida, armadura);
    }


    public void atacar(Personaje objetivo) {

        int dado1 = (int) (Math.random() * 101);
        int dado2 = (int) (Math.random() * 101);


        int valorAtaque = Math.max(dado1, dado2);

        //comprobamos si el objetivo es un orco y suma 10 al valor de ataque
        if (objetivo instanceof Orco) {
            valorAtaque += 10;
        }

        //comprobamos si el valor de ataque supera la armadura del objetivo
        if (valorAtaque > objetivo.getArmadura()) {
            int danio = valorAtaque - objetivo.getArmadura();
            objetivo.recibirDanio(danio);
            //actualiza el valor de ataque del Elfo
            setAtaque(danio);
        }
    }

}
