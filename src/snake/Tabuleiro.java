package snake;

import java.awt.Color;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static snake.Tela.jPanel1;
import static snake.Tela.tamTabuleiro;

/**
 *
 * @author Benny
 */
public class Tabuleiro implements Runnable {
    protected static int[][] tabuleiro = new int[tamTabuleiro][tamTabuleiro];
    protected static JLabel[][] pixels  = new JLabel[tamTabuleiro][tamTabuleiro];
    protected static int[][] locationTop = new int[tamTabuleiro][tamTabuleiro];
    protected static int[][] locationLeft = new int[tamTabuleiro][tamTabuleiro];

    public void buildTabuleiro() {
        
        int top = 0;
        for (int linha = 0; linha < tamTabuleiro; linha++) {
            
            int left = 0;
            for (int coluna = 0; coluna < tamTabuleiro; coluna++) {
                
                if(linha == 0 || linha == tamTabuleiro-1 || coluna == 0 || coluna == tamTabuleiro-1) {
                    this.tabuleiro[linha][coluna] = 1;
                    this.locationTop[linha][coluna] = top;
                    this.locationLeft[linha][coluna] = left;
                    this.pixels[linha][coluna] = newWall(top, left);
                } else {
                    this.tabuleiro[linha][coluna] = 0;
                    this.locationTop[linha][coluna] = top;
                    this.locationLeft[linha][coluna] = left;
                    this.pixels[linha][coluna] = newWalkArea(top, left);
                }
                left += 10;
            }
            top += 10;
        }

    }
    
    public JLabel newWall(int top, int left) {
        JLabel pixel = new JLabel();
                    
        pixel.setBackground(Color.decode("#555555"));
        pixel.setSize(10,10);
        pixel.setLocation(top, left);
        pixel.setOpaque(true);
        Tela.jPanel1.add(pixel);
        return pixel;
    }
    
    public JLabel newWalkArea(int top, int left) {
        JLabel pixel = new JLabel();
                    
        pixel.setBackground(Color.decode("#33CC99"));
        pixel.setSize(10,10);
        pixel.setLocation(top, left);
        pixel.setOpaque(true);
        Tela.jPanel1.add(pixel);
        return pixel;
    }
    
    public JLabel newPosSnake(int top, int left) {
        JLabel pixel = new JLabel();
                    
        pixel.setBackground(Color.decode("#D6003B"));
        pixel.setSize(10,10);
        pixel.setLocation(top, left);
        pixel.setOpaque(true);
        Tela.jPanel1.add(pixel);
        return pixel;
    }
    
    public JLabel newFood(int top, int left) {
        JLabel pixel = new JLabel();
                    
        pixel.setBackground(Color.decode("#D65305"));
        pixel.setSize(10,10);
        pixel.setLocation(top, left);
        pixel.setOpaque(true);
        Tela.jPanel1.add(pixel);
        return pixel;
    }
    
    public void addTabuleiro(int left, int top, int tipo) {
        Tela.jPanel1.remove(pixels[top][left]);
        this.tabuleiro[top][left] = tipo;
        
        this.pixels[top][left] = retornaTipo(locationTop[top][left], locationLeft[top][left], tipo);
        Tela.jPanel1.add(this.pixels[top][left]);
    }
    
    public void removerTabuleiro(int left, int top, int tipo) {
        Tela.jPanel1.remove(pixels[top][left]);
        this.tabuleiro[top][left] = tipo;
    }
    
    public void tooglePosition(int top, int left, int tipo) {
        this.removerTabuleiro(top, left, tipo);
        this.addTabuleiro(top, left, tipo);
        
        Tela.jPanel1.revalidate();
        Tela.jPanel1.repaint();
    }
    
    public void addPositionSnake(int top, int left, int tipo, int tamanhoSnake) {
        this.tooglePosition(top, left, tipo);
    }
    
    Thread addFood = new Thread () {
        public void run() {
            try {
                Random random = new Random();
                boolean podeAlocar = false;

                while(!podeAlocar) {
                    System.out.println(Tela.temComidaNaTela);
                    int linha = random.nextInt(Tela.tamTabuleiro);
                    int coluna = random.nextInt(Tela.tamTabuleiro);

                    if(tabuleiro[linha][coluna] == 0 && !Tela.temComidaNaTela) {
                        tabuleiro[linha][coluna] = 3;
                        podeAlocar = true;
                        tooglePosition(linha, coluna, 3);
                        Tela.temComidaNaTela = true;
                    }
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                System.out.println("Erro ao add comida!");
                e.printStackTrace();
            }
        }
    };
    
    public JLabel retornaTipo(int top, int left, int tipo) {
        switch (tipo) {
            case 0:
                return newWalkArea(top, left);
            case 1:
                return newWall(top, left);
            case 2:
                return newPosSnake(top, left);
            case 3:
                return newFood(top, left);
            default:
                return newWalkArea(top, left);
        }
    }
    
    @Override
    public void run() {
        this.buildTabuleiro();
        this.addFood.start();
    }
}
