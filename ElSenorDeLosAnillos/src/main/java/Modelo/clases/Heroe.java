package Modelo.clases;

import Modelo.Personaje;

public class Heroe extends Personaje {
    private String raza;
    public Heroe(String nombre, int vida, int armadura, String raza) {

        super(nombre, vida, armadura, raza);
        this.raza = raza;
    }

    public int atacar(Personaje objetivo) {
        int valorAtaque1 = (int) (Math.random() * 101);
        int valorAtaque2 = (int) (Math.random() * 101);
        int valorAtaque = Math.max(valorAtaque1, valorAtaque2);

        if (valorAtaque > objetivo.getArmadura()) {
            int danio = valorAtaque - objetivo.getArmadura();
            objetivo.recibirDanio(danio);
            setAtaque(danio);

        }

        return valorAtaque;
    }
}
