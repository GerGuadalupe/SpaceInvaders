/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.spaceinvaders;

/**
 *
 * @author German
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Bala implements ActionListener{
    private EscenaJuego escena;
    protected final int x_posicion;
    protected int y_posicion;
    public Bala(int x, int y, EscenaJuego escena){
        x_posicion = x;
        y_posicion = y;
        this.escena = escena;
        escena.t.addActionListener(this);
    }
    public void destroy(){
        escena.t.removeActionListener(this);
        this.escena.balas.remove(this);
    }
    private int calcularDistancia(int x_b, int y_b, int x_e, int y_e){
        double x = x_b - x_e, y = y_b - y_e;
        return (int) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        y_posicion -= 5;
        if(this.y_posicion <= 0)
            destroy();
        for(Enemigo[] m: escena.form.enemigos){
            for(Enemigo n: m){
                if(n != null){
                    int distancia = calcularDistancia(this.x_posicion, this.y_posicion, n.x_centro, n.y_centro);
                    if(distancia <= Personaje.P_LARGO/2){
                        destroy();
                        n.destroy();
                    }
                }
            }
        }
    }
}
