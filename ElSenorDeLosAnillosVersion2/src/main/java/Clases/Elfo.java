package Clases;

import java.util.Random;

public class Elfo extends Heroe {
    public Elfo(String nombre, int vida, int armadura) {
        super(nombre, vida, armadura);
    }

    @Override
    public void atacar(Personaje enemigo) {
        if (enemigo instanceof Orco) {

            Random dado = new Random();
            int tirada1 = dado.nextInt(101);
            int tirada2 = dado.nextInt(101);
            System.out.println("Primer dado: " + tirada1);
            System.out.println("Segundo dado: " + tirada2);
            int maximo =  Math.max(tirada1, tirada2);
            System.out.println("¡¡El odio hacia los Orcos aumenta el ataque en +10!!");
            maximo += 10;
            System.out.println("Valor de ataque resultante: " + maximo);
            enemigo.recibirAtaque(maximo);

        }
        else
            super.atacar(enemigo);
    }

}
