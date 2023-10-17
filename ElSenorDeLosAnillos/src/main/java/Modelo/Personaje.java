package Modelo;


public abstract class Personaje {
    private String nombre;
    private int vida;
    private int armadura;

    private String raza;
    private int ataque;

    public Personaje(String nombre, int vida, int armadura, String raza) {
        this.nombre = nombre;
        this.vida = vida;
        this.armadura = armadura;
        this.raza = raza;
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

    public String getRaza() {
        return raza;
    }

    public void setRaza(String nombre) {this.raza = raza;}

    public int getAtaque() {
        return ataque;
    }


    public abstract int atacar(Personaje objetivo);

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
