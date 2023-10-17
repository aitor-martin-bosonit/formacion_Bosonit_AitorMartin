package Modelo.clases;

import Modelo.Personaje;

public class Bestia extends Personaje {
    private String raza;
    public Bestia(String nombre, int vida, int armadura, String raza) {

        super(nombre, vida, armadura, raza);
        this.raza = raza;
    }


    public int atacar(Personaje objetivo) {
        int valorAtaque = (int) (Math.random() * 91);

        if (valorAtaque > objetivo.getArmadura()) {
            int danio = valorAtaque - objetivo.getArmadura();
            objetivo.recibirDanio(danio);
            setAtaque(danio);

        }

        return valorAtaque;
    }


}
