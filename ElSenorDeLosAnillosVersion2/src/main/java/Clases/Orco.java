package Clases;

import java.util.Random;

public class Orco extends Bestia {
    public Orco(String nombre, int vida, int armadura) {
        super(nombre, vida, armadura);
    }

    @Override
    public void atacar(Personaje enemigo) {
        if (enemigo instanceof Heroe) {
            Random dado = new Random();
            int tirada = (int) (dado.nextInt(91) * 1.10);
            System.out.println("Resultado del dado es: " + tirada);
            System.out.println("El Orco reduce la armadura del Heroe en un 10%");

            enemigo.recibirAtaque(tirada);
        } else {

            Random dado = new Random();
            int tirada = dado.nextInt(91); // Dado normal
            System.out.println("Resultado del dado es: " + tirada);
            enemigo.recibirAtaque(tirada);
        }
    }
}
