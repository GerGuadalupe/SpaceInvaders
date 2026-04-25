/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.spaceinvaders;

/**
 *
 * @author German
 */
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.SwingUtilities;
public class SpaceInvaders extends JFrame{
    private EscenaJuego escena;
    public SpaceInvaders(){
        escena = new EscenaJuego();
        escena.setPreferredSize(new Dimension(escena.LARGO, escena.ANCHO));
        this.add(escena);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(escena.jugador);
        this.setVisible(true);
        this.setFocusable(true);
        this.setTitle("Space invaders");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SpaceInvaders::new);
    }
}
