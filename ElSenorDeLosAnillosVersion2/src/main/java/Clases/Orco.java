package Clases;

import java.util.Random;

public class Orco extends Bestia {
    public Orco(String nombre, int vida, int armadura) {
        super(nombre, vida, armadura);
    }

    @Override
    public void atacar(Personaje enemigo) {

        Random dado = new Random();
        int tirada = dado.nextInt(91);
        System.out.println("Resultado del dado es: " + tirada);
        System.out.println("El Orco reduce la armadura del herore en un 10%");
        enemigo.recibirAtaque(tirada);
    }
}
