package Clases;

import java.util.Random;

public class Hobbit extends Heroe {
    public Hobbit(String nombre, int vida, int armadura) {
        super(nombre, vida, armadura);
    }

    @Override
    public void atacar(Personaje enemigo) {
        if (enemigo instanceof Trasgo) {

            Random dado = new Random();
            int tirada1 = dado.nextInt(101);
            int tirada2 = dado.nextInt(101);
            System.out.println("Primer dado: " + tirada1);
            System.out.println("Segundo dado: " + tirada2);
            int maximo =  Math.max(tirada1, tirada2);
            System.out.println("¡¡El miedo a los trasgos reduce el resultado en -5!!");
            maximo -= 5;
            System.out.println("Valor de ataque resultante: " + maximo);
            enemigo.recibirAtaque(maximo);

        }
        else
            super.atacar(enemigo);
    }
}
