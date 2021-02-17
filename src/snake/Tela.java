/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.io.File;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Benny
 */
public class Tela extends javax.swing.JFrame {
    public static int tamTabuleiro = 60;
    public static int keyPressed = 0;
    public static ArrayList<Snake> posSnake = new ArrayList<Snake>();
    public JLabel[][] pixels = new JLabel[tamTabuleiro][tamTabuleiro];
    public static Tabuleiro t = new Tabuleiro();
    public static boolean fimJogo = false;
    public static int velocidadeSnake = 125; // > mais lenta | < mais rapida
    public static boolean temComidaNaTela = false;
    public static int pontos = 0;
    public static int dificuldade = 1;
    
    
    public Tela() {
        initComponents();
        
        Snake pos1 = new Snake();
        pos1.setTop(1);
        pos1.setLeft(1);
        pos1.setCor(Color.ORANGE);
        
        Snake pos2 = new Snake();
        pos2.setTop(1);
        pos2.setLeft(2);
        pos2.setCor(Color.ORANGE);
        
        posSnake.add(pos1);
        posSnake.add(pos2);
        
        pack();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
    	
        keyPressed = 0;
        //posSnake = new ArrayList<Snake>();
        //pixels = new JLabel[tamTabuleiro][tamTabuleiro];
        fimJogo = false;
        temComidaNaTela = false;
        pontos = 0;
        
    	fimJogo = false;
        //jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabelPontos = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setName("Snake BCC"); // NOI18N
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MoveSnake(evt);
            }
        });

        
        songBackground();
        

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
        );

        jLabelPontos.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        jLabelPontos.setText("0");

        jLabel2.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        jLabel2.setText("Pontos:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelPontos)
                .addContainerGap(559, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabelPontos)))
        );

        jPanel1.getAccessibleContext().setAccessibleName("");
        jPanel1.getAccessibleContext().setAccessibleDescription("");

    }

    private void MoveSnake(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MoveSnake
        
        if(keyPressed != 0) {
            if(keyPressed == 37 && evt.getKeyCode() == 39
            || keyPressed == 39 && evt.getKeyCode() == 37
            || keyPressed == 38 && evt.getKeyCode() == 40
            || keyPressed == 40 && evt.getKeyCode() == 38
            || keyPressed == evt.getKeyCode()
            || fimJogo)
               return;
        }
        
        keyPressed = evt.getKeyCode();
        
        if(!addPixel.isAlive()) {
            addPixel.start();
        }
        
        if(!removePixel.isAlive()) {
            removePixel.start();
        }
        
    }//GEN-LAST:event_MoveSnake
 
    public void atualizaPontos() {
        temComidaNaTela = false;
        songEat();
        pontos += 1*dificuldade;
   	 	velocidadeSnake-=dificuldade;
        jLabelPontos.setText(String.valueOf(pontos));
        Tabuleiro tab = new Tabuleiro();
        tab.addFood.start();
    }
    
    public  void songBackground() {
    	new Sounds("fundo").start();
    }
    public  void songEat() {
    	new Sounds("eat").start();
    }
    public void songEnd() {
    	new Sounds("end").start();
    }
    
     public void gameOver() {        
    	 songEnd();
        
        int i = JOptionPane.showConfirmDialog(null, "Game Over!\nDeseja jogar novamente?", "Fim de Jogo!", JOptionPane.YES_NO_OPTION);
        if (i == JOptionPane.YES_OPTION) {
        	
        	setVisible(false);
			main(null);
        }else{
        	setVisible(false);
        }

    }

     Thread addPixel = new Thread () {
         public void run() {
             int posLeft = 0;
             int posTop  = 0;
             int key2 = -1;
             
             try {
                 while (!fimJogo) { 
                 
                 switch (keyPressed) {
                     case 37:
                         if(key2 != keyPressed) {
                             key2 = keyPressed;
                             posLeft = posSnake.get(posSnake.size()-1).getLeft();
                             posTop = posSnake.get(posSnake.size()-1).getTop();

                             for(int i = posLeft; i < tamTabuleiro; i--) {
                                 if(t.tabuleiro[posTop][i] == 3) {
                                     Snake s = new Snake();
                                     s.setTop(posTop);
                                     s.setLeft(i);
                                     posSnake.add(s);
                                     atualizaPontos();
                                     t.tabuleiro[posTop][i] = 0;
                                 }

                                 t.tooglePosition(posTop, i, 2);

                                 Snake s = new Snake();
                                 s.setTop(posTop);
                                 s.setLeft(i);

                                 Tela.jPanel1.revalidate();
                                 Tela.jPanel1.repaint();
                                 
                                 
                                 Thread.sleep(velocidadeSnake);
                                 	
                                 posSnake.add(s);

                                 if(i <= 0) {
                                     fimJogo = true; 
                                     gameOver();
                                     break;
                                 }

                                 if(keyPressed != 37) {
                                     break;
                                 }
                             }
                         }
                         break;
                     case 38:
                         if(key2 != keyPressed) {
                             key2 = keyPressed;
                             posLeft = posSnake.get(posSnake.size()-1).getLeft();
                             posTop = posSnake.get(posSnake.size()-1).getTop();

                             for(int i = posTop; i < tamTabuleiro; i--) {
                                 if(t.tabuleiro[i][posLeft] == 3) {
                                     Snake s = new Snake();
                                     s.setTop(i);
                                     s.setLeft(posLeft);
                                     posSnake.add(s);
                                     atualizaPontos();
                                     t.tabuleiro[i][posLeft] = 0;
                                 }
                                 
                                 t.tooglePosition(i, posLeft, 2);
                                 System.out.println(t.tabuleiro[posTop][i]);
                                        
                                 Snake s = new Snake();
                                 s.setTop(i);
                                 s.setLeft(posLeft);

                                 Thread.sleep(velocidadeSnake);
                                 posSnake.add(s);

                                 if(i <= 0) {
                                     fimJogo = true; 
                                     gameOver();
                                     break;
                                 }

                                 if(keyPressed != 38) {
                                     break;
                                 }
                             }
                         }
                         break;
                     case 39:
                         if(key2 != keyPressed) {
                             key2 = keyPressed;
                             posLeft = posSnake.get(posSnake.size()-1).getLeft();
                             posTop = posSnake.get(posSnake.size()-1).getTop();

                             for(int i = posLeft; i < tamTabuleiro; i++) {
                                 if(t.tabuleiro[posTop][i] == 3) {
                                     Snake s = new Snake();
                                     s.setTop(posTop);
                                     s.setLeft(i);
                                     posSnake.add(s);
                                     atualizaPontos();
                                     t.tabuleiro[posTop][i] = 0;
                                 }

                                 t.tooglePosition(posTop, i, 2);

                                 Snake s = new Snake();
                                 s.setTop(posTop);
                                 s.setLeft(i);

                                 Thread.sleep(velocidadeSnake);
                                 posSnake.add(s);

                                 if(i >= tamTabuleiro - 1) {
                                     fimJogo = true; 
                                     gameOver();
                                     break;
                                 }

                                 if(keyPressed != 39) {
                                     break;
                                 }
                             }
                         }
                         break;
                     case 40:
                         if(key2 != keyPressed) {
                             key2 = keyPressed;
                             posLeft = posSnake.get(posSnake.size()-1).getLeft();
                             posTop = posSnake.get(posSnake.size()-1).getTop();

                             for(int i = posTop; i < tamTabuleiro; i++) {
                                 System.out.println(t.tabuleiro[i][posLeft]);
                                 if(t.tabuleiro[i][posLeft] == 3) {
                                     Snake s = new Snake();
                                     s.setTop(i);
                                     s.setLeft(posLeft);
                                     posSnake.add(s);
                                     atualizaPontos();
                                     t.tabuleiro[i][posLeft] = 0;
                                     
                                 } 

                                 t.tooglePosition(i, posLeft, 2);

                                 Snake s = new Snake();
                                 s.setTop(i);
                                 s.setLeft(posLeft);

                                 Thread.sleep(velocidadeSnake);
                                 posSnake.add(s);

                                 if(i >= tamTabuleiro - 1) {
                                     gameOver();
                                     break;
                                 }

                                 if(keyPressed != 40) {
                                     break;
                                 }
                             }
                         }
                         break;
                     }
                     sleep(velocidadeSnake/8); 
                 }
                 Thread.interrupted();
                 removePixel.interrupt();
                 
             } catch(InterruptedException ex) {
                 System.out.println("erro: "+ ex);
             }
         }
     };
     
    Thread removePixel = new Thread () {
        public void run() {
            try {
                int posTop  = 0;
                int posLeft = 0;
                int key = -1;
                
                while (!fimJogo) { 
                    switch (keyPressed) {
                        case 37:
                        if(key != keyPressed) {
                            key = keyPressed;
                            posLeft = posSnake.get(0).getLeft();

                            for(int i = posLeft; i < tamTabuleiro; i--) {
                                t.addTabuleiro(posSnake.get(0).getTop(), posSnake.get(0).getLeft(), 0);
                                posSnake.remove(0);
                                
                                Thread.sleep(velocidadeSnake);
                                
                                if(keyPressed != 37) {
                                    break;
                                }
                            }
                        }
                        break;
                    case 38:
                        if(key != keyPressed) {
                            key = keyPressed;
                            posTop  = posSnake.get(0).getTop();
                            posLeft = posSnake.get(0).getLeft();

                            for(int i = posTop; i < tamTabuleiro; i--) {
                                t.addTabuleiro(posSnake.get(0).getTop(), posSnake.get(0).getLeft(), 0);
                                posSnake.remove(0);

                                Thread.sleep(velocidadeSnake);
                                if(keyPressed != 38) {
                                    break;
                                }
                            }
                        }
                        break;
                    case 39:
                        if(key != keyPressed) {
                            key = keyPressed;
                            posLeft = posSnake.get(0).getLeft();

                            for(int i = posLeft; i < tamTabuleiro; i++) {
                                t.addTabuleiro(posSnake.get(0).getTop(), posSnake.get(0).getLeft(), 0);
                                posSnake.remove(0);

                                Thread.sleep(velocidadeSnake);
                                if(keyPressed != 39) {
                                    break;
                                }

                            }
                        }
                        break;
                    case 40:
                        if(key != keyPressed) {
                            key = keyPressed;
                            posLeft = posSnake.get(0).getLeft();
                            posTop  = posSnake.get(0).getTop();

                            for(int i = posTop; i < tamTabuleiro; i++) {
                                t.addTabuleiro(posSnake.get(0).getTop(), posSnake.get(0).getLeft(), 0);
                                posSnake.remove(0);
                                Thread.sleep(velocidadeSnake);
                                
                                if(keyPressed != 40) {
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    sleep(velocidadeSnake/8); 
                }
                Thread.interrupted();
                
            } catch(InterruptedException ex) {
                System.out.println("erro: "+ ex);
            }
        }
    };
    
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Tela().setVisible(true);
                
                    Tabuleiro t = new Tabuleiro();
                    t.run();
                    
                    t.addPositionSnake(1, 1, 2, posSnake.size()-1);
                    t.addPositionSnake(1, 2, 2, posSnake.size()-1);
                    
                    jPanel1.setFocusable( true );
                } catch(Exception ex) {
                    System.out.println(ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelPontos;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

}
