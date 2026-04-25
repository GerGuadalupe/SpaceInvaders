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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Jugador extends Personaje implements KeyListener, ActionListener{
    private EscenaJuego escena;
    private final int delay_balas;
    private int contador_delay = 32;
    public Jugador(int x, int y, EscenaJuego escena){
        super(x);
        this.x_posision = (x/2 - P_LARGO/2);
        this.y_posision = (y - P_ANCHO);
        this.x_centro = x_posision + P_LARGO/2;
        this.y_centro = y_posision + P_ANCHO/2;
        this.velocidad = 25;
        this.escena = escena;
        delay_balas = 16;
        escena.t.addActionListener(this);
    }
    
    private void disparar(){
        if(contador_delay >= delay_balas){
            escena.balas.add(new Bala(this.x_centro, this.y_posision, escena));
            contador_delay = 0;
        }
    }
    
    @Override
    protected void moverPersonaje(int vel) {
        if(!(x_posision + vel > x_max || x_centro + vel <= 0)){
            x_posision += vel;
            this.x_centro = x_posision + P_LARGO/2;
            escena.repaint();
        }
    }

    @Override
    protected void destroy() {
        escena.gameOver = true;
        escena.t.stop();
        escena.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_RIGHT:
                moverPersonaje(velocidad);
            break;
            case KeyEvent.VK_LEFT:
                moverPersonaje(-velocidad);
            break;
            case KeyEvent.VK_SPACE:
                disparar();
            break;
            case KeyEvent.VK_ENTER:
                if(escena.gameOver){
                    escena.nuevaPartida();
                }
            break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
            contador_delay = 32;
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        contador_delay++;
    }
    
}
