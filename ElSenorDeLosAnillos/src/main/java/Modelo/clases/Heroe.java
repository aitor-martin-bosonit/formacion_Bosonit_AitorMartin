package Modelo.clases;

import Modelo.Personaje;
import Vista.VistaJuego;

public class Heroe extends Personaje {
    public Heroe(String nombre, int vida, int armadura) {
        super(nombre, vida, armadura);
    }

    public void atacar(Personaje objetivo) {
        int valorAtaque1 = (int) (Math.random() * 101);
        int valorAtaque2 = (int) (Math.random() * 101);
        int valorAtaque = Math.max(valorAtaque1, valorAtaque2);

        if (valorAtaque > objetivo.getArmadura()) {
            int danio = valorAtaque - objetivo.getArmadura();
            objetivo.recibirDanio(danio);
            setAtaque(danio);

        }

    }
}
