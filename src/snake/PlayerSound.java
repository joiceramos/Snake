/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

/**
 *
 * @author Benny
 */
public class PlayerSound extends Thread {
    private File sound;
    private Player player;
        
    public void play(File sound) {
        this.sound = sound;
    }
    
    public void run() {
        try {
            FileInputStream fis = new FileInputStream(sound);
            BufferedInputStream bis = new BufferedInputStream(fis);

            this.player = new Player(bis);
            this.player.play();

        } catch (Exception e) {
            System.out.println("Erro: " + sound);
            e.printStackTrace();
        }
    }
}
