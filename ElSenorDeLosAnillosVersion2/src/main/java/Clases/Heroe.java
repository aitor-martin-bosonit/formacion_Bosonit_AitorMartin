package Clases;

import java.util.Random;

public class Heroe extends Personaje {

    public Heroe(String nombre, int vida, int armadura) {
        super(nombre, vida, armadura);
    }

    @Override
    public void atacar(Personaje enemigo) {
        Random dado = new Random();
        int tirada1 = dado.nextInt(101);
        int tirada2 = dado.nextInt(101);
        System.out.println("Primer dado: " + tirada1);
        System.out.println("Segundo dado: " + tirada2);
        int maximo = Math.max(tirada1, tirada2);

        //reducir la armadura del héroe un 10% si el enemigo es un Orco
        if (enemigo instanceof Orco) {

            double nuevaArmadura = getArmadura() * 0.9;
            setArmadura((int) nuevaArmadura);
        }

        enemigo.recibirAtaque(maximo);
    }
}
