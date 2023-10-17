package org.Controlador;

import Modelo.Ejercito;
import Modelo.Personaje;
import Modelo.clases.Bestia;
import Modelo.clases.Heroe;
import Vista.VistaJuego;

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
                    vista.imprimirMensaje("Saliendo del programa...");
                    break;
                default:
                    vista.imprimirMensaje("Opción no válida. Por favor, elija una opción válida.");
                    break;
            }
        } while (opcion != 8);
    }

    private void crearHeroe() {
        String nombre = vista.obtenerNombre();
        int vida = vista.obtenerVida();
        int armadura = vista.obtenerArmadura();
        String raza = vista.obtenerRazaHeroe();

        Heroe nuevoHeroe = new Heroe(nombre, vida, armadura, raza);
        ejercitoHeroes.crearPersonaje(nuevoHeroe);
        vista.imprimirMensaje("Héroe creado con éxito.");
    }

    private void crearBestia() {
        String nombre = vista.obtenerNombre();
        int vida = vista.obtenerVida();
        int armadura = vista.obtenerArmadura();
        String raza = vista.obtenerRazaBestia();

        Bestia nuevaBestia = new Bestia(nombre, vida, armadura, raza);
        ejercitoBestias.crearPersonaje(nuevaBestia);
        vista.imprimirMensaje("Bestia creada con éxito.");
    }

    private void modificarHeroe() {
        listarPersonajes(ejercitoHeroes);
        vista.imprimirMensaje("Modificar Héroe");
        String nombre = vista.obtenerNombre();
        Personaje heroe = buscarPersonajePorNombre(nombre, ejercitoHeroes);

        if (heroe != null) {
            String nuevoNombre = vista.obtenerNombre();
            int nuevaVida = vista.obtenerVida();
            int nuevaArmadura = vista.obtenerArmadura();

            ejercitoHeroes.modificarPersonaje(heroe, nuevoNombre, nuevaVida, nuevaArmadura);
            vista.imprimirMensaje("Héroe modificado con éxito.");
        } else {
            vista.imprimirMensaje("Héroe no encontrado.");
        }
    }

    private void modificarBestia() {
        listarPersonajes(ejercitoBestias);
        vista.imprimirMensaje("Modificar Bestia");
        String nombre = vista.obtenerNombre();
        Personaje bestia = buscarPersonajePorNombre(nombre, ejercitoBestias);

        if (bestia != null) {
            String nuevoNombre = vista.obtenerNombre();
            int nuevaVida = vista.obtenerVida();
            int nuevaArmadura = vista.obtenerArmadura();

            ejercitoBestias.modificarPersonaje(bestia, nuevoNombre, nuevaVida, nuevaArmadura);
            vista.imprimirMensaje("Bestia modificada con éxito.");
        } else {
            vista.imprimirMensaje("Bestia no encontrada.");
        }
    }

    private void borrarHeroe() {
        listarPersonajes(ejercitoHeroes);
        vista.imprimirMensaje("Borrar Héroe");
        String nombre = vista.obtenerNombre();
        Personaje heroe = buscarPersonajePorNombre(nombre, ejercitoHeroes);

        if (heroe != null) {
            ejercitoHeroes.eliminarPersonaje(heroe);
            vista.imprimirMensaje("Héroe borrado con éxito.");
        } else {
            vista.imprimirMensaje("Héroe no encontrado.");
        }
    }

    private void borrarBestia() {
        listarPersonajes(ejercitoBestias);
        vista.imprimirMensaje("Borrar Bestia");
        String nombre = vista.obtenerNombre();
        Personaje bestia = buscarPersonajePorNombre(nombre, ejercitoBestias);

        if (bestia != null) {
            ejercitoBestias.eliminarPersonaje(bestia);
            vista.imprimirMensaje("Bestia borrada con éxito.");
        } else {
            vista.imprimirMensaje("Bestia no encontrada.");
        }
    }

    private void elegirCombate() {
        vista.imprimirMensaje("Elegir Combate");
        vista.imprimirMensaje("Elija un Héroe para la batalla:");
        listarPersonajes(ejercitoHeroes);

        String nombreHeroe = vista.obtenerNombre();
        Personaje heroe = buscarPersonajePorNombre(nombreHeroe, ejercitoHeroes);

        if (heroe == null) {
            vista.imprimirMensaje("Héroe no encontrado.");
            return;
        }

        vista.imprimirMensaje("Elija una Bestia para la batalla:");
        listarPersonajes(ejercitoBestias);

        String nombreBestia = vista.obtenerNombre();
        Personaje bestia = buscarPersonajePorNombre(nombreBestia, ejercitoBestias);

        if (bestia == null) {
            vista.imprimirMensaje("Bestia no encontrada.");
            return;
        }

        realizarBatalla(heroe, bestia);
    }

    private void listarPersonajes(Ejercito ejercito) {
        vista.imprimirMensaje("Personajes disponibles:");
        for (Personaje personaje : ejercito.getPersonajes()) {
            vista.imprimirMensaje(personaje.getNombre());
        }
    }

    private void realizarBatalla(Personaje heroe, Personaje bestia) {
        vista.imprimirMensaje("Realizar Batalla");
        vista.imprimirLucha(heroe, bestia);
        vista.imprimirLucha(bestia, heroe);

        while (heroe.getVida() > 0 && bestia.getVida() > 0) {
            heroe.atacar(bestia);
            bestia.atacar(heroe);

            vista.imprimirResultadoLucha(heroe, bestia, heroe.getAtaque());
            vista.imprimirResultadoLucha(bestia, heroe, bestia.getAtaque());

            if (heroe.getVida() <= 0) {
                ejercitoHeroes.eliminarPersonaje(heroe);
                vista.imprimirMuertePersonaje(heroe);
                vista.imprimirMensaje(bestia.getNombre() + " ha ganado la batalla!");
                return;
            } else if (bestia.getVida() <= 0) {
                ejercitoBestias.eliminarPersonaje(bestia);
                vista.imprimirMuertePersonaje(bestia);
                vista.imprimirMensaje(heroe.getNombre() + " ha ganado la batalla!");
                return;
            }
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