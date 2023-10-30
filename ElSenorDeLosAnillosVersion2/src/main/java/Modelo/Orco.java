package Modelo;

public class Orco extends Bestia {
    public Orco(String nombre, int vida, int armadura) {
        super(nombre, vida, armadura);
    }

    @Override
    public void atacar(Personaje enemigo) {
        if (enemigo instanceof Heroe) {

            int armaduraReducida = (int) (enemigo.getArmadura() * 0.10);
            System.out.println("El Orco reduce un 10% de la armadura del Héroe.");
            enemigo.recibirAtaque(armaduraReducida);
        } else {

            super.atacar(enemigo);
        }
    }

}
