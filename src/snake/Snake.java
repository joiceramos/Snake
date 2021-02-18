/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author Benny
 */
public class Snake {
    private int top;
    private int left;
    
    public Snake (int top, int left) {
        this.top = top;
        this.left = left;
    }
    
    public Snake (){}

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

 
    
    
}
