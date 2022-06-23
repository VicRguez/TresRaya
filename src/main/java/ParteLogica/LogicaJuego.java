/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParteLogica;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author dam_daw
 */
public class LogicaJuego {
    
    //Matriz logica de los botones
    private char[][] matriz=new char[3][3];
    
    //Turno solo tendra 0 para el turno de las O u 1 para el turno de las X
    private int turno=0;
    
    //contador de las partidas ganadas
    private int pO=0;
    private int pX=0;
    

    //metodo que devuelve por el turno que se está
    public int getTurno() {
        return turno;
    }
    
    //metodo que cambia el turno
    public void cambioTurno(){
        if(turno==0){
            turno=1;
            
        }else {
            turno=0;
            
        }
    }
    
    //metodo que te devolvera si se ha ganado el juego llamando a los metodos 
    //para comprobar la horizontal, la vertical o diagonal
    public boolean comprobarJuego (int x,int y){
        boolean comp=false;
        //se comprueba si se ha ganado en horizontal, en vertical y en diagonal
        if(compruebaHorizontal(x)||compruebaVertical(y)||compruebaDiagonal(x,y)){
            comp=true;
        }
        return comp;
       }
    //metodo que comprueba si se ha hecho linea horizontalmente
    public boolean compruebaHorizontal(int x){
        int cont=0;
        for (int i=0;i<matriz[0].length;i++){//se recorre la fila
            if((turno==0)&&(matriz[x][i]=='O')){//si el turno es el de los "O" y hay una "0" en esa posicion se incrementa el contador
                cont++;
            } else if((turno==1)&&(matriz[x][i]=='X')){//lo mismo con las "X"
                cont++;
            } else
                break;
        }
        return cont==3;//si es tres significa que ha hecho linea
    }
    public boolean compruebaVertical(int y){
        int cont=0;
        for (int i=0;i<matriz.length;i++){
            //se comprueba segun el turno si el contenido es una "O" o "X"
            //Si lo es, se incrementa el contador
            if((turno==0)&&(matriz[i][y]=='O')){
                cont++;
            } else if((turno==1)&&(matriz[i][y]=='X')){
                cont++;
            } else
                break;
        }
        return cont==3;
    }
    //metodo que te comprueba la diagonal
    public boolean compruebaDiagonal(int x, int y){
        int cont=0;
        for(int i=0;i<matriz.length;i++){//se recorre la matriz
            for(int j=0;j<matriz[i].length;j++){
                //si las coordenadas de la posicion son iguales, significa que esta en la diagonal y se va comprobando las otras posiciones posibles
                if((x==y)){
                    if ((turno==0)&&(matriz[i][j]=='O')&&(i==j)){
                     cont++;
                    }else if((turno==1)&&(matriz[i][j]=='X')&&(i==j)){
                        cont++;
                    } 
                    //este if comprueba si esta en alguna posicion de la otra 
                    //diagonal y si esta comprueba las otras posiciones
                    //si es asi se incrementa el contador
                }else if((x==0&&y==2)||(x==1&&y==1)||(x==2&&y==0)){
                    if ((turno==0)&&(matriz[i][j]=='O')&&(i+j==2)){
                     cont++;
                    }else if((turno==1)&&(matriz[i][j]=='X')&&(i+j==2)){
                        cont++;
                    } 
                }
            }
        }
        //si es tres significa que ha hecho linea
        return cont==3;
    }
    
    //este motodo pintará en el botón el tecto adecuado segun el turno
    public char pintarFicha (JButton boton){
         
        if(getTurno()==0){//si ess el turno 0 se pondrá una "o" en azul 
            boton.setText("0");
            boton.setForeground(Color.blue);
            boton.setFont(new java.awt.Font("Calibri", 0, 45));
            return 'O';
        }else{//si ess el turno 1 se pondrá una "X" en rojo 
            boton.setText("X");
            boton.setForeground(Color.red);
            boton.setFont(new java.awt.Font("Calibri", 0, 45));
            return 'X';
        }
        
            
    }
    //aqui se pondra en nuestra matriz logica la letra del jugador segun el turno y se llama a pintarFicha
    public void ponerFicha (JButton boton,int x,int y){
        matriz[x][y]=pintarFicha (boton);        
    }
    //Este metodo es el de la tirada del jugador donde se llama a pintar ficha y se comprueba el juego
    public void tiradaJugador (JButton[][] boton, JLabel pO,JLabel pX, int x,int y){
        ponerFicha(boton[x][y],x,y);
        //se bloquea la casilla seleccionada
        boton[x][y].setEnabled(false);
        //se comprueba el juego
        if(comprobarJuego(x,y)){
            //si se gana se llama al metodo ganador
            ganador();
            //se actualiza las etiquetas donde sale la puntuacion
            pO.setText(String.valueOf(this.pO));
            pX.setText(String.valueOf(this.pX));
            //se desabitila el tablero
            habilitarTablero(boton,false);
            
        }else{
            //si no se ha ganado se coambia de turno
            cambioTurno();
        }
    }
    //si se gana depende de quien gane se incrementa los contadores de partidas ganadas
    public void ganador (){
        if(turno==0){
            pO++;
        }else{
            pX++;
        }
        
        cambioTurno(); 
    }
    //se habilita o se desabilita el tablero
    public void habilitarTablero(JButton[][] tablero,boolean habilitado){
        for (JButton[] tablero1 : tablero) {
            for (JButton item : tablero1) {
                item.setEnabled(habilitado);
            }
        }
    }

    //se habilita el yablero de botones y se reinicia la matriz de la logica
    public void iniciarPartida(JButton[][] tablero) {
        habilitarTablero(tablero,true);
        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[i].length;j++){
                matriz[i][j]=' ';
            }
        }
        
    }
}
