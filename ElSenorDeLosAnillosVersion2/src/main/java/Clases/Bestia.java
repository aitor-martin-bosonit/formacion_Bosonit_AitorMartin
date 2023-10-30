package Clases;

import java.util.Random;

public class Bestia extends Personaje{

    public Bestia(String nombre, int vida, int armadura) {
        super(nombre, vida, armadura);
    }

    @Override
    public void atacar(Personaje enemigo) {

        Random dado = new Random();
        int tirada = dado.nextInt(91);
        System.out.println("Resultado del dado es: " + tirada);
        enemigo.recibirAtaque(tirada);
    }

}
