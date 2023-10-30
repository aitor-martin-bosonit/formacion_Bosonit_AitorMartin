package Batalla;

import Clases.Bestia;
import Clases.Ejercito;
import Clases.Heroe;

import java.util.Scanner;

public class Batalla {

    private static Scanner sc = new Scanner(System.in);

    public static void batallar(Ejercito heroes, Ejercito bestias) {


        while(!heroes.esDerrotado() && !bestias.esDerrotado()) {


            int turnosTotal;
            if (heroes.personajes.size() >= bestias.personajes.size())
                turnosTotal = heroes.personajes.size();
            else
                turnosTotal = bestias.personajes.size();


            for (int turno = 0; turno < turnosTotal; turno++) {

                Heroe heroe = (Heroe) heroes.getPersonaje(turno);
                Bestia bestia = (Bestia) bestias.getPersonaje(turno);

                if (heroe == null && bestia == null)

                    break;
                else if (heroe == null)

                    System.out.println(bestia.getNombre() + " espera enemigo");
                else if (bestia == null)

                    System.out.println(heroe.getNombre() + " espera enemigo");
                else {

                    System.out.println("Lucha entre " + heroe + " y " + bestia);

                    System.out.println("Turno de " + heroe.getNombre());
                    heroe.atacar(bestia);

                    System.out.println("Puntos de vida restantes de " + bestia);
                    if (bestia.estaMuerto())
                        System.out.println(bestia.getNombre() + " ha muerto.");
                    else {

                        System.out.println("Turno de " + bestia.getNombre());
                        bestia.atacar(heroe);
                        System.out.println("Puntos de vida restantes de " + heroe);
                        if (heroe.estaMuerto())
                            System.out.println(heroe.getNombre() + " ha muerto.");
                    }
                }

                heroes.comprobarEjercito();
                bestias.comprobarEjercito();
                pausa();

            }
        }


        if (heroes.esDerrotado())
            System.out.println("Han ganado las Bestias. Personajes restantes: " + bestias.personajes.size());
        else
            System.out.println("Han ganado los Heroes. Personajes restantes: " + heroes.personajes.size());
    }

    private static void pausa() {
        System.out.println("\n\t\tPulse INTRO para continuar...\n");
        sc.nextLine();
    }

}
