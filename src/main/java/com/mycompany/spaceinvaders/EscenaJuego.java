/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.spaceinvaders;

/**
 *
 * @author German
 */
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;
import java.awt.Graphics;
import java.util.ArrayList;
public class EscenaJuego extends JPanel{
    protected ArrayList<Bala> balas;
    protected final int ANCHO = 500;
    protected final int LARGO = 600;
    protected Jugador jugador;
    protected FormacionEnemiga form;
    protected Timer t;
    protected int puntaje;
    private JLabel contador, finDelJuego;
    boolean gameOver;
    
    public EscenaJuego(){
        this.setLayout(new BorderLayout());
        gameOver = false;
        balas = new ArrayList<>();
        form = new FormacionEnemiga(this);
        t = new Timer(8, form);
        jugador = new Jugador(LARGO,ANCHO,this);
        t.start();
        contador = new JLabel("PUNTAJE: 0");
        contador.setForeground(Color.white);
        contador.setAlignmentX(SwingConstants.RIGHT);
        add(contador, BorderLayout.NORTH);
        finDelJuego = new JLabel();
        finDelJuego.setForeground(Color.white);
        finDelJuego.setHorizontalAlignment(SwingConstants.CENTER);
        add(finDelJuego, BorderLayout.CENTER);
        puntaje = 0;
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.BLACK);
        
        //dibujar a los enemigos
        g.setColor(Color.green);
        for(Enemigo[] n: form.enemigos){
            for(Enemigo m: n){
                if(m != null){
                    int x = m.x_posision;
                    int y = m.y_posision;
                    int largo = Personaje.P_LARGO; // 50
                    int ancho = Personaje.P_ANCHO; // 40

                    // Cuerpo principal
                    g.fillRect(x + largo/5, y, largo/5*3, ancho/4); // parte superior
                    g.fillRect(x + largo/10, y + ancho/4, largo/10*8, ancho/4); // Cuerpo medio superior
                    g.fillRect(x, y + ancho/2, largo, ancho/4); // Cuerpo medio inferior
                    g.fillRect(x + largo/10, y + ancho/4*3, largo/10*2, ancho/4); // Patas/base izquierda
                    g.fillRect(x + largo/10*7, y + ancho/4*3, largo/10*2, ancho/4); // Patas/base derecha
                    g.fillRect(x + largo/5*2, y + ancho/4*3, largo/5, ancho/4);
                }
            }
        }
        //dibuja las balas
        g.setColor(Color.YELLOW);
        for(Bala b: balas){
            g.fillRect(b.x_posicion, b.y_posicion, 2, 10);
        }
        
        
        //dibujar al personaje
        int jugadorX = jugador.x_posision;
        int jugadorY = jugador.y_posision;
        int jugadorLargo = Personaje.P_LARGO; // 50
        int jugadorAncho = Personaje.P_ANCHO; // 40
        //base de la nave
        g.setColor(new Color(0.23f,0.44f,0.72f));
        g.fillRect(jugadorX, jugadorY + jugadorAncho/5*3, jugadorLargo, jugadorAncho/4);
        // Cañón
        g.setColor(Color.GRAY);
        g.fillRect(jugadorX + jugadorLargo/2 - jugadorLargo/20, jugadorY - jugadorAncho/4, jugadorLargo/10, jugadorAncho/2);
        // Torreta
        g.setColor(new Color(0.23f,0.44f,0.72f));
        g.fillOval(jugadorX + jugadorLargo/4, jugadorY, jugadorLargo/2, jugadorAncho/2);
        // Cuerpo principal
         g.setColor(new Color(0.23f,0.582f,0.99f));
        g.fillRect(jugadorX + jugadorLargo/8, jugadorY + jugadorAncho/4, jugadorLargo/8*6, jugadorAncho/5*2);
        
        
        //puntaje:
        contador.setText("PUNTAJE: " + puntaje);
        
        //Game Over
        if(gameOver){
            finDelJuego.setText("GAME OVER PRESIONE ENTER PARA VOLVER A JUGAR");
        }
    }
    
    protected void nuevaPartida(){
        gameOver = false;
        form = new FormacionEnemiga(this);
        t = new Timer(8, form);
        t.start();
        puntaje = 0;
        finDelJuego.setText("");
    }
}
