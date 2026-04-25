/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.spaceinvaders;

/**
 *
 * @author German
 */
public class Enemigo extends Personaje{
    protected boolean vivo;
    EscenaJuego escena;
    public Enemigo(int x, int y, EscenaJuego escena){
        super(escena.LARGO);
        this.vivo = true;
        this.velocidad = 1;
        this.x_posision = x;
        this.y_posision = y;
        this.x_centro = x_posision + P_LARGO/2;
        this.y_centro = y_posision + P_ANCHO/2;
        this.escena = escena;
    }
    protected void descender(){
        this.y_posision += P_ANCHO;
        this.y_centro = y_posision + P_ANCHO/2;
    }

    @Override
    protected void moverPersonaje(int vel) {
        this.x_posision += vel;
        this.x_centro = x_posision + P_LARGO/2;
        if(this.y_centro >= escena.ANCHO - P_ANCHO){
            escena.jugador.destroy();
        }
    }

    @Override
    protected void destroy() {
        escena.puntaje += 100;
        this.vivo = false;
    }
}
