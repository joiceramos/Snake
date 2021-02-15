/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import javazoom.jl.player.Player;
import static snake.Tela.musicaFundo;

/**
 *
 * @author Benny
 */
public class Sounds {
 
    public static void SoundEatFood(){
        String path = "src/sound/eat_food.mp3";
        File sound = new File(path);
        PlayerSound musica = new PlayerSound();
        musica.play(sound);
        musica.start();
    }
    
    public static void backgroudGame(){
        Tela.musicaFundo = true;
        String path = "src/sound/background_game.mp3";
        File sound = new File(path);
        PlayerSound musica = new PlayerSound();
        musica.play(sound);
        musica.start();
    }
    
    public static void SoundEndGame(){
        String path = "src/sound/end_game.mp3";
        File sound = new File(path);
        PlayerSound musica = new PlayerSound();
        musica.play(sound);
        musica.start();
    }
    
}
