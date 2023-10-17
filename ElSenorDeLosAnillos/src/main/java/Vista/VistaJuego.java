package Vista;

import Modelo.Personaje;

import java.util.Scanner;


public class VistaJuego {

    private Scanner scanner = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("Menú:");
        System.out.println("1. Crear Héroe");
        System.out.println("2. Crear Bestia");
        System.out.println("3. Modificar Héroe");
        System.out.println("4. Modificar Bestia");
        System.out.println("5. Borrar Héroe");
        System.out.println("6. Borrar Bestia");
        System.out.println("7. Elegir combate");
        System.out.println("8. Salir");
        System.out.print("Seleccione una opción: ");
        return scanner.nextInt();
    }

    public void imprimirMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public String obtenerNombre() {
        System.out.print("Nombre: ");
        return scanner.next();
    }

    public int obtenerVida() {
        System.out.print("Vida: ");
        return scanner.nextInt();
    }

    public int obtenerArmadura() {
        System.out.print("Armadura: ");
        return scanner.nextInt();
    }

    public String obtenerRazaHeroe() {
        String raza;
        int opcionRaza;

        do {
            imprimirMensaje("Elija la raza:");
            imprimirMensaje("1. Elfo");
            imprimirMensaje("2. Hobbit");
            imprimirMensaje("3. Humano");

            opcionRaza = scanner.nextInt();

            switch (opcionRaza) {
                case 1:
                    raza = "Elfo";
                    break;
                case 2:
                    raza = "Hobbit";
                    break;
                case 3:
                    raza = "Humano";
                    break;
                default:
                    imprimirMensaje("Opción no válida. Por favor, elija una opción válida.");
                    raza = null;
                    break;
            }
        } while (raza == null);

        return raza;
    }

    public String obtenerRazaBestia() {
        String raza;
        int opcionRaza;

        do {
            imprimirMensaje("Elija la raza:");
            imprimirMensaje("1. Orco");
            imprimirMensaje("2. Trasgo");

            opcionRaza = scanner.nextInt();

            switch (opcionRaza) {
                case 1:
                    raza = "Orco";
                    break;
                case 2:
                    raza = "Trasgo";
                    break;

                default:
                    imprimirMensaje("Opción no válida. Por favor, elija una opción válida.");
                    raza = null;
                    break;
            }
        } while (raza == null);

        return raza;
    }

    public void imprimirLucha(Personaje personaje1, Personaje personaje2) {
        System.out.println("Combate entre " + personaje1.getNombre() + "(Raza= " + personaje1.getRaza() + " Vida= " + personaje1.getVida() +
                " Armadura= " + personaje1.getArmadura() + ")  y " + personaje2.getNombre() + "(Raza= " + personaje2.getRaza() +
                " Vida= " + personaje2.getVida() + " Armadura= " + personaje2.getArmadura() + ")");
    }

    public void imprimirResultadoLucha(Personaje atacante, Personaje objetivo, int danio) {

        int valorTiradaDados = atacante.getAtaque() + objetivo.getArmadura();

        if (valorTiradaDados > objetivo.getArmadura()) {
            System.out.println(atacante.getNombre() + " saca " + valorTiradaDados + " y le quita " +
                    danio + " de vida a " + objetivo.getNombre());
        } else {
            System.out.println(atacante.getNombre() + " no inflige daño a " + objetivo.getNombre());
        }

    }

    public void imprimirMuertePersonaje(Personaje personaje) {
        System.out.println("¡Muere " + personaje.getNombre() + "!");
    }
}