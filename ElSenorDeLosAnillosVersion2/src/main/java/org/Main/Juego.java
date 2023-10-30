package org.Main;

import Clases.*;
import Batalla.Batalla;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Juego {

    public static void main (String args[]){
        Scanner scanner = new Scanner(System.in);
        Ejercito heroes = new Ejercito();
        Ejercito bestias = new Ejercito();

        heroes.reclutarPersonaje(new Elfo("Legolas", 150, 30));
        heroes.reclutarPersonaje(new Humano("Aragorn", 150, 50));
        heroes.reclutarPersonaje(new Humano("Boromir",100, 60));
        heroes.reclutarPersonaje(new Humano("Gandalf",300, 10));
        heroes.reclutarPersonaje(new Hobbit("Frodo", 20, 10));

        bestias.reclutarPersonaje(new Orco("Lurtz", 200, 60));
        bestias.reclutarPersonaje(new Orco("Shagrat", 220, 50));
        bestias.reclutarPersonaje(new Orco("Azog", 150, 60));
        bestias.reclutarPersonaje(new Trasgo("Uglúk", 120, 30));
        bestias.reclutarPersonaje(new Trasgo("Mauhúr", 100, 30));

//********************************************** MENU ***************************************************************

        boolean salir = false;


        while (!salir) {
            System.out.println("Menú:");
            System.out.println("1. Crear Personaje");
            System.out.println("2. Modificar Personaje");
            System.out.println("3. Borrar Personaje");
            System.out.println("4. Batalla");
            System.out.println("5. Salir del Programa");
            System.out.print("Elige una opción: ");

            int opcion = obtenerEntero(scanner);

            switch (opcion) {
                case 1:
                    submenuCrearPersonaje(heroes, bestias, scanner);
                    break;
                case 2:
                    submenuModificarPersonaje(heroes, bestias, scanner);
                    break;
                case 3:
                    submenuBorrarPersonaje(heroes, bestias, scanner);
                    break;
                case 4:
                    Batalla.batallar(heroes, bestias);
                    break;
                case 5:
                    salir = true;
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Ingresa un número del 1 al 5.");
                    break;
            }
        }

        scanner.close();
    }

    private static int obtenerEntero(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Opción no válida. Ingresa un número del 1 al 5.");
                System.out.print("Elige una opción: ");
            }
        }
    }

    private static void submenuCrearPersonaje(Ejercito heroes, Ejercito bestias, Scanner scanner) {
        System.out.println("Crear Personaje:");
        System.out.println("1. Héroe");
        System.out.println("2. Bestia");
        System.out.print("Elige una opción: ");

        int opcionCrear;
        try {
            opcionCrear = scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.next();
            System.out.println("Opción no válida. Ingresa 1 para Héroe o 2 para Bestia.");
            return;
        }

        String nombre;
        int vida, armadura;

        scanner.nextLine();

        System.out.print("Nombre: ");
        nombre = scanner.nextLine();
        try{
        System.out.print("Vida: ");
        vida = scanner.nextInt();}
        catch (InputMismatchException e) {
            scanner.next();
            System.out.println("Opción no válida. Ingrese un numero.");
            return;
        }

        try{
        System.out.print("Armadura: ");
        armadura = scanner.nextInt();}
        catch (InputMismatchException e) {
            scanner.next();
            System.out.println("Opción no válida. Ingrese un numero.");
            return;
        }

        if (opcionCrear == 1) {
            submenuCrearHeroe(heroes, nombre, vida, armadura, scanner);
        } else if (opcionCrear == 2) {
            submenuCrearBestia(bestias, nombre, vida, armadura, scanner);
        } else {
            System.out.println("Opción no válida. Ingresa 1 para Héroe o 2 para Bestia.");
        }
    }

    private static void submenuCrearHeroe(Ejercito heroes, String nombre, int vida, int armadura, Scanner scanner) {
        System.out.println("Crear Héroe:");
        System.out.println("1. Elfo");
        System.out.println("2. Hobbit");
        System.out.println("3. Humano");
        System.out.print("Elige una opción: ");

        int opcionHeroe;
        try {
            opcionHeroe = scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.next();
            System.out.println("Opción no válida. Ingresa un número del 1 al 3.");
            return;
        }

        if (opcionHeroe == 1) {
            heroes.reclutarPersonaje(new Elfo(nombre, vida, armadura));
        } else if (opcionHeroe == 2) {
            heroes.reclutarPersonaje(new Hobbit(nombre, vida, armadura));
        } else if (opcionHeroe == 3) {
            heroes.reclutarPersonaje(new Humano(nombre, vida, armadura));
        } else {
            System.out.println("Opción no válida. Ingresa un número del 1 al 3.");
        }
    }

    private static void submenuCrearBestia(Ejercito bestias, String nombre, int vida, int armadura, Scanner scanner) {
        System.out.println("Crear Bestia:");
        System.out.println("1. Orco");
        System.out.println("2. Trasgo");
        System.out.print("Elige una opción: ");

        int opcionBestia;
        try {
            opcionBestia = scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.next();
            System.out.println("Opción no válida. Ingresa 1 para Orco o 2 para Trasgo.");
            return;
        }

        if (opcionBestia == 1) {
            bestias.reclutarPersonaje(new Orco(nombre, vida, armadura));
        } else if (opcionBestia == 2) {
            bestias.reclutarPersonaje(new Trasgo(nombre, vida, armadura));
        } else {
            System.out.println("Opción no válida. Ingresa 1 para Orco o 2 para Trasgo.");
        }
    }

    private static void submenuModificarPersonaje(Ejercito heroes, Ejercito bestias, Scanner scanner) {
        System.out.println("Modificar Personaje:");
        System.out.println("1. Héroe");
        System.out.println("2. Bestia");
        System.out.print("Elige una opción: ");

        int opcionModificar;
        try {
            opcionModificar = scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.next();
            System.out.println("Opción no válida. Ingresa 1 para Héroe o 2 para Bestia.");
            return;
        }

        scanner.nextLine();

        if (opcionModificar == 1) {
            listarHeroes(heroes);
            System.out.print("Selecciona el número del Héroe que deseas modificar: ");
            int numeroHeroe = scanner.nextInt();
            scanner.nextLine();
            Heroe heroe = (Heroe) heroes.getPersonaje(numeroHeroe - 1);
            if (heroe != null) {

                System.out.print("Nuevo nombre: ");
                String nuevoNombre = scanner.nextLine();
                try{
                System.out.print("Nueva vida: ");
                int nuevaVida = scanner.nextInt();
                System.out.print("Nueva armadura: ");
                int nuevaArmadura = scanner.nextInt();
                scanner.nextLine();

                heroe.modificarPersonaje(nuevoNombre, nuevaVida, nuevaArmadura);
                System.out.println("Héroe modificado correctamente.");}
                catch (InputMismatchException e) {
                    scanner.next();
                    System.out.println("Opción no válida. Ingrese un numero.");
                    return;
                }
            } else {
                System.out.println("Héroe no encontrado.");
            }
        } else if (opcionModificar == 2) {
            listarBestias(bestias);
            System.out.print("Selecciona el número de la Bestia que deseas modificar: ");
            int numeroBestia = scanner.nextInt();
            scanner.nextLine();
            Bestia bestia = (Bestia) bestias.getPersonaje(numeroBestia - 1);
            if (bestia != null) {
                System.out.print("Nuevo nombre: ");
                String nuevoNombre = scanner.nextLine();
                try{
                System.out.print("Nueva vida: ");
                int nuevaVida = scanner.nextInt();
                System.out.print("Nueva armadura: ");
                int nuevaArmadura = scanner.nextInt();
                scanner.nextLine();
                bestia.modificarPersonaje(nuevoNombre, nuevaVida, nuevaArmadura);
                System.out.println("Bestia modificada correctamente.");}
                catch (InputMismatchException e) {
                    scanner.next();
                    System.out.println("Opción no válida. Ingrese un numero.");
                    return;
                }
            } else {
                System.out.println("Bestia no encontrada.");
            }
        } else {
            System.out.println("Opción no válida. Ingresa 1 para Héroe o 2 para Bestia.");
        }
    }

    private static void listarHeroes(Ejercito heroes) {
        System.out.println("Lista de Héroes:");
        for (int i = 0; i < heroes.personajes.size(); i++) {
            Heroe heroe = (Heroe) heroes.getPersonaje(i);
            if (heroe != null) {
                System.out.println((i + 1) + ". " + heroe.getNombre());
            }
        }
    }

    private static void listarBestias(Ejercito bestias) {
        System.out.println("Lista de Bestias:");
        for (int i = 0; i < bestias.personajes.size(); i++) {
            Bestia bestia = (Bestia) bestias.getPersonaje(i);
            if (bestia != null) {
                System.out.println((i + 1) + ". " + bestia.getNombre());
            }
        }
    }

    private static void submenuBorrarPersonaje(Ejercito heroes, Ejercito bestias, Scanner scanner) {
        System.out.println("Borrar Personaje:");
        System.out.println("1. Héroe");
        System.out.println("2. Bestia");
        System.out.print("Elige una opción: ");

        int opcionBorrar;
        try {
            opcionBorrar = scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.next();
            System.out.println("Opción no válida. Ingresa 1 para Héroe o 2 para Bestia.");
            return;
        }

        if (opcionBorrar == 1) {
            listarHeroes(heroes);
            try {
                System.out.print("Selecciona el número del Héroe que deseas borrar: ");
                int numeroHeroe = scanner.nextInt();
                Heroe heroe = (Heroe) heroes.getPersonaje(numeroHeroe - 1);
                if (heroe != null) {
                    heroes.personajes.remove(heroe);
                    System.out.println("Héroe eliminado.");
                } else {
                    System.out.println("Héroe no encontrado.");
                }
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Entrada no válida. Debes ingresar un número.");
            }
        } else if (opcionBorrar == 2) {
            listarBestias(bestias);
            try {
                System.out.print("Selecciona el número de la Bestia que deseas borrar: ");
                int numeroBestia = scanner.nextInt();
                Bestia bestia = (Bestia) bestias.getPersonaje(numeroBestia - 1);
                if (bestia != null) {
                    bestias.personajes.remove(bestia);
                    System.out.println("Bestia eliminada.");
                } else {
                    System.out.println("Bestia no encontrada.");
                }
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Entrada no válida. Debes ingresar un número.");
            }
        } else {
            System.out.println("Opción no válida. Ingresa 1 para Héroe o 2 para Bestia.");
        }
    }
}



