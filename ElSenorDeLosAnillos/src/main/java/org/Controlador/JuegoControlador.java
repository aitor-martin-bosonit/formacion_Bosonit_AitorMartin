package org.Controlador;

import Modelo.Ejercito;
import Modelo.Personaje;
import Modelo.clases.Bestia;
import Modelo.clases.Heroe;
import Vista.VistaJuego;

import java.util.List;
import java.util.Scanner;

public class JuegoControlador {
    private Ejercito ejercitoHeroes;
    private Ejercito ejercitoBestias;
    private VistaJuego vista;

    private Scanner scanner = new Scanner(System.in);

    public JuegoControlador(Ejercito ejercitoHeroes, Ejercito ejercitoBestias, VistaJuego vista) {
        this.ejercitoHeroes = ejercitoHeroes;
        this.ejercitoBestias = ejercitoBestias;
        this.vista = vista;
    }

    public void jugar() {
        int numeroTurno = 1;
        int opcion;
     /*   while (ejercitoHeroes.estaVivo() && ejercitoBestias.estaVivo()) {
            vista.imprimirTurno(numeroTurno);
            List<Personaje> heroes = ejercitoHeroes.getPersonajes();
            List<Personaje> bestias = ejercitoBestias.getPersonajes();

            for (int i = 0; i < Math.min(heroes.size(), bestias.size()); i++) {
                Personaje heroe = heroes.get(i);
                Personaje bestia = bestias.get(i);
                vista.imprimirLucha(heroe, bestia);

                heroe.atacar(bestia);
                bestia.atacar(heroe);

                vista.imprimirResultadoLucha(heroe, bestia, heroe.getAtaque());
                vista.imprimirResultadoLucha(bestia, heroe, bestia.getAtaque());

                if (heroe.getVida() < 1) {
                    ejercitoHeroes.eliminarPersonaje(heroe);
                    vista.imprimirMuertePersonaje(heroe);
                }

                if (bestia.getVida() < 1) {
                    ejercitoBestias.eliminarPersonaje(bestia);
                    vista.imprimirMuertePersonaje(bestia);
                }

            }

            numeroTurno++;
        }

        if (ejercitoHeroes.estaVivo()) {
            vista.imprimirVictoriaHeroes();
        } else {
            vista.imprimirVictoriaBestias();
        }*/
//******************** LOGICA MENÚ *************************************



        do {
            opcion = vista.mostrarMenu();
            switch (opcion) {
                case 1:
                    crearHeroe();
                    break;
                case 2:
                    crearBestia();
                    break;
                case 3:
                    modificarHeroe();
                    break;
                case 4:
                    modificarBestia();
                    break;
                case 5:
                    borrarHeroe();
                    break;
                case 6:
                    borrarBestia();
                    break;
                case 7:
                    elegirCombate();
                    break;
                case 8:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elija una opción válida.");
                    break;
            }
        } while (opcion != 8);

//********************** CREATE, UPDATE, DELETE ******************************

    }

    private void crearHeroe() {
        System.out.print("Nombre del Héroe: ");
        String nombre = scanner.next();
        System.out.print("Vida del Héroe: ");
        int vida = scanner.nextInt();
        System.out.print("Armadura del Héroe: ");
        int armadura = scanner.nextInt();

        Heroe nuevoHeroe = new Heroe(nombre, vida, armadura);
        ejercitoHeroes.crearPersonaje(nuevoHeroe);
        System.out.println("Héroe creado con éxito.");
    }

    private void crearBestia() {
        System.out.print("Nombre de la Bestia: ");
        String nombre = scanner.next();
        System.out.print("Vida de la Bestia: ");
        int vida = scanner.nextInt();
        System.out.print("Armadura de la Bestia: ");
        int armadura = scanner.nextInt();

        Bestia nuevaBestia = new Bestia(nombre, vida, armadura);
        ejercitoBestias.crearPersonaje(nuevaBestia);
        System.out.println("Bestia creada con éxito.");
    }

    private void modificarHeroe() {
        System.out.print("Nombre del Héroe a modificar: ");
        String nombre = scanner.next();
        Personaje heroe = buscarPersonajePorNombre(nombre, ejercitoHeroes);

        if (heroe != null) {
            System.out.print("Nuevo nombre del Héroe: ");
            String nuevoNombre = scanner.next();
            System.out.print("Nueva vida del Héroe: ");
            int nuevaVida = scanner.nextInt();
            System.out.print("Nueva armadura del Héroe: ");
            int nuevaArmadura = scanner.nextInt();

            ejercitoHeroes.modificarPersonaje(heroe, nuevoNombre, nuevaVida, nuevaArmadura);
            System.out.println("Héroe modificado con éxito.");
        } else {
            System.out.println("Héroe no encontrado.");
        }
    }

    private void modificarBestia() {
        System.out.print("Nombre de la Bestia a modificar: ");
        String nombre = scanner.next();
        Personaje bestia = buscarPersonajePorNombre(nombre, ejercitoBestias);

        if (bestia != null) {
            System.out.print("Nuevo nombre de la Bestia: ");
            String nuevoNombre = scanner.next();
            System.out.print("Nueva vida de la Bestia: ");
            int nuevaVida = scanner.nextInt();
            System.out.print("Nueva armadura de la Bestia: ");
            int nuevaArmadura = scanner.nextInt();

            ejercitoBestias.modificarPersonaje(bestia, nuevoNombre, nuevaVida, nuevaArmadura);
            System.out.println("Bestia modificada con éxito.");
        } else {
            System.out.println("Bestia no encontrada.");
        }
    }

    private void borrarHeroe() {
        System.out.print("Nombre del Héroe a borrar: ");
        String nombre = scanner.next();
        Personaje heroe = buscarPersonajePorNombre(nombre, ejercitoHeroes);

        if (heroe != null) {
            ejercitoHeroes.eliminarPersonaje(heroe);
            System.out.println("Héroe borrado con éxito.");
        } else {
            System.out.println("Héroe no encontrado.");
        }
    }

    private void borrarBestia() {
        System.out.print("Nombre de la Bestia a borrar: ");
        String nombre = scanner.next();
        Personaje bestia = buscarPersonajePorNombre(nombre, ejercitoBestias);

        if (bestia != null) {
            ejercitoBestias.eliminarPersonaje(bestia);
            System.out.println("Bestia borrada con éxito.");
        } else {
            System.out.println("Bestia no encontrada.");
        }
    }

    private void elegirCombate() {
        System.out.println("Elija un Héroe para la batalla:");
        listarPersonajes(ejercitoHeroes);

        System.out.print("Nombre del Héroe: ");
        String nombreHeroe = scanner.next();
        Personaje heroe = buscarPersonajePorNombre(nombreHeroe, ejercitoHeroes);

        if (heroe == null) {
            System.out.println("Héroe no encontrado.");
            return;
        }

        System.out.println("Elija una Bestia para la batalla:");
        listarPersonajes(ejercitoBestias);

        System.out.print("Nombre de la Bestia: ");
        String nombreBestia = scanner.next();
        Personaje bestia = buscarPersonajePorNombre(nombreBestia, ejercitoBestias);

        if (bestia == null) {
            System.out.println("Bestia no encontrada.");
            return;
        }


        realizarBatalla(heroe, bestia);
    }

    private void listarPersonajes(Ejercito ejercito) {
        System.out.println("Personajes disponibles:");
        for (Personaje personaje : ejercito.getPersonajes()) {
            System.out.println(personaje.getNombre());
        }
    }

    private void realizarBatalla(Personaje heroe, Personaje bestia) {
        vista.imprimirLucha(heroe, bestia);

        while (heroe.getVida() > 0 && bestia.getVida() > 0) {
            heroe.atacar(bestia);
            bestia.atacar(heroe);

            vista.imprimirResultadoLucha(heroe, bestia, heroe.getAtaque());
            vista.imprimirResultadoLucha(bestia, heroe, bestia.getAtaque());

            if (heroe.getVida() <= 0 && bestia.getVida() <= 0) {
                System.out.println("¡Es un empate!");
            } else if (heroe.getVida() <= 0) {
                System.out.println("¡" + bestia.getNombre() + " ha ganado la batalla!");
            } else if (bestia.getVida() <= 0) {
                System.out.println("¡" + heroe.getNombre() + " ha ganado la batalla!");
            }
        }

        if (heroe.getVida() <= 0) {
            ejercitoHeroes.eliminarPersonaje(heroe);
            vista.imprimirMuertePersonaje(heroe);
        }

        if (bestia.getVida() <= 0) {
            ejercitoBestias.eliminarPersonaje(bestia);
            vista.imprimirMuertePersonaje(bestia);
        }
    }

    private Personaje buscarPersonajePorNombre(String nombre, Ejercito ejercito) {
        for (Personaje personaje : ejercito.getPersonajes()) {
            if (personaje.getNombre().equals(nombre)) {
                return personaje;
            }
        }
        return null;
    }
}
