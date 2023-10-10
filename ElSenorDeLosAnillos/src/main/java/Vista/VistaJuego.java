package Vista;

import Modelo.Personaje;

import java.util.Scanner;

public class VistaJuego {

    private Scanner scanner = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("Menú:");
        System.out.println("1. Crear Heroe");
        System.out.println("2. Crear Bestia");
        System.out.println("3. Modificar Heroe");
        System.out.println("4. Modificar Bestia");
        System.out.println("5. Borrar Heroe");
        System.out.println("6. Borrar bestia");
        System.out.println("7. Elegir combate");
        System.out.println("8. Salir");
        System.out.print("Seleccione una opción: ");
        return scanner.nextInt();
    }
    public void imprimirTurno(int numeroTurno) {
        System.out.println("Turno " + numeroTurno + ":");
    }

    public void imprimirLucha(Personaje personaje1, Personaje personaje2) {
        System.out.println("Combate entre " + personaje1.getNombre() + " (Vida=" + personaje1.getVida() +
                " Armadura=" + personaje1.getArmadura() + ") y " + personaje2.getNombre() +
                " (Vida=" + personaje2.getVida() + " Armadura=" + personaje2.getArmadura() + ")");
    }


    public void imprimirResultadoLucha(Personaje atacante, Personaje objetivo, int danio) {
        System.out.println("Lucha entre " + atacante.getNombre() + " (Vida=" + atacante.getVida() +
                " Armadura=" + atacante.getArmadura() + ") y " + objetivo.getNombre() +
                " (Vida=" + objetivo.getVida() + " Armadura=" + objetivo.getArmadura() + ")");

        int valorTiradaDados = atacante.getAtaque() + objetivo.getArmadura();

        if (valorTiradaDados > 0) {
            System.out.println(atacante.getNombre() + " saca " + valorTiradaDados + " y le quita " +
                    danio + " de vida a " + objetivo.getNombre());
        } else {
            System.out.println(atacante.getNombre() + " no inflige daño a " + objetivo.getNombre());
        }
    }

    public void imprimirMuertePersonaje(Personaje personaje) {
        System.out.println("¡Muere " + personaje.getNombre() + "!");
    }

    public void imprimirVictoriaHeroes() {
        System.out.println("¡VICTORIA DE LOS HÉROES!");
    }

    public void imprimirVictoriaBestias() {
        System.out.println("¡VICTORIA DE LAS BESTIAS!");
    }
}
