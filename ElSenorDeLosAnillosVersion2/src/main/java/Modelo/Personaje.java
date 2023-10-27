package Modelo;

public abstract class Personaje {

    private String nombre;
    private int vida;
    private int armadura;

    public Personaje(String nombre, int vida, int armadura) {
        this.nombre = nombre;
        this.vida = vida;
        this.armadura = armadura;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean estaMuerto() {
        return vida <= 0;
    }

    public int getArmadura() {
        return armadura;
    }

    public void recibirAtaque(int ataque) {
        if (ataque > armadura)
            vida -= ataque - armadura;

        if (vida < 0)
            vida = 0; //Evitamos valores negativos
    }

    @Override
    public String toString() {
        return String.format("%s (Vida=%d Armadura=%d)", nombre, vida, armadura);
    }


    public abstract void atacar(Personaje enemigo);

    public void modificarPersonaje(String nuevoNombre, int nuevaVida, int nuevaArmadura) {
        this.nombre = nuevoNombre;
        this.vida = nuevaVida;
        this.armadura = nuevaArmadura;
    }

}
