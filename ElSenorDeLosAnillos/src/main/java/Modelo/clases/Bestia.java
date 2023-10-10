package Modelo.clases;

import Modelo.Personaje;
import Vista.VistaJuego;

public class Bestia extends Personaje {
    public Bestia(String nombre, int vida, int armadura) {
        super(nombre, vida, armadura);
    }


    public void atacar(Personaje objetivo) {
        int valorAtaque = (int) (Math.random() * 91);

        if (valorAtaque > objetivo.getArmadura()) {
            int danio = valorAtaque - objetivo.getArmadura();
            objetivo.recibirDanio(danio);
            setAtaque(danio);

        }

    }


}
