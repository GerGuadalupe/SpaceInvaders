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
public final class FormacionEnemiga implements ActionListener{
    protected Enemigo[][] enemigos;
    private EscenaJuego escena;
    private int oleada;
    
    public FormacionEnemiga(EscenaJuego escena){
        this.escena = escena;
        oleada = 1;
        iniciarOleada(oleada);
    }
    
    //cambiar la sireccion de movimiento de los enemicos
    public void cambiarDireccion(){
        for(int i = 0; i < oleada; i++){
            for(int j = 0; j < oleada; j++){
                if(enemigos[i][j] != null){
                    enemigos[i][j].velocidad *= -1;
                    enemigos[i][j].descender();
                }
            }
        }
        escena.repaint();
    }
    
    //se inicializa una nueva oleada de enemigos
    public void iniciarOleada(int oleada){
        for(Bala b: escena.balas){
            escena.t.removeActionListener(b);
        }
        escena.balas.clear();
        this.oleada = oleada;
        enemigos = new Enemigo[oleada][oleada];
        for(int i = 0; i < oleada; i++){
            for(int j = 0; j < oleada; j++){
                enemigos[i][j] = new Enemigo((j*(Personaje.P_LARGO + 10)), (i*(Personaje.P_ANCHO + 10)), escena);
            }
        }
    }
    
    //esta funcion se activa con el timer en la clase SpaceInvaders, controla el movimiento de los enemigos y su vida
    @Override
    public void actionPerformed(ActionEvent e) {
        int enemigosDerrotados = 0;
        for(int i = 0; i < oleada; i++){
            for(int j = 0; j < oleada; j++){
                if(enemigos[i][j] != null){
                    enemigos[i][j].moverPersonaje(enemigos[i][j].velocidad);
                    if(!enemigos[i][j].vivo){
                        enemigos[i][j] = null;
                    }
                }
            }
        }
        escena.repaint();
        for(Enemigo[] n: enemigos){
            boolean borde = false;
            for(Enemigo m: n){
                if(m != null){
                    if(m.x_posision >= m.x_max || m.x_posision <= 0){
                        cambiarDireccion();
                        borde = true;
                        break;
                    }
                }
            }
            if(borde)
                break;
        }
        for(Enemigo[] n: enemigos){
            for(Enemigo m: n){
                if(m == null)
                    enemigosDerrotados++;
            }
        }
        if(enemigosDerrotados == oleada*oleada){
            oleada++;
            iniciarOleada(oleada);
        }
    }
    
}
