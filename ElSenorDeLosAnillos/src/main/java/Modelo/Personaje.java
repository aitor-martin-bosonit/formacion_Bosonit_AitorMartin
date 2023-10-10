package Modelo;


public abstract class Personaje {
    private String nombre;
    private int vida;
    private int armadura;
    private int ataque;

    public Personaje(String nombre, int vida, int armadura) {
        this.nombre = nombre;
        this.vida = vida;
        this.armadura = armadura;
        this.ataque = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {this.nombre = nombre;}

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {this.vida = vida;}

    public int getArmadura() {
        return armadura;
    }

    public void setArmadura(int armadura) {this.armadura = armadura;}

    public int getAtaque() {
        return ataque;
    }


    public abstract void atacar(Personaje objetivo);

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void recibirDanio(int danio) {
        vida -= danio;
        if (vida <= 0) {
            vida = 0;
        }
    }
}
