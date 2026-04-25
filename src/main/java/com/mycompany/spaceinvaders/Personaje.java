/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.spaceinvaders;

/**
 *
 * @author German
 */
public abstract class Personaje {
    protected static final int P_LARGO = 50;
    protected static final int P_ANCHO = 40;
    protected int x_posision, y_posision, x_centro, y_centro;
    protected int velocidad;
    protected int x_max;
    public Personaje(int largo_escena){
        this.x_max = largo_escena - P_LARGO;
    }
    
    protected abstract void moverPersonaje(int vel);
    protected abstract void destroy();
}
