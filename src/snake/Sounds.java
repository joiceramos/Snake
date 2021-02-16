/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;
import java.io.File;

public class Sounds extends Thread{
	
	 PlayerSound song;
	 String selectedSound = "";
	 boolean fimJogo;
	 String songGame = "src/sound/background_game.mp3";
	 String songEat = "src/sound/eat_food.mp3";
	 String songEnd = "src/sound/end_game.mp3";
	
	public Sounds (String selectedSound, boolean fimJogo) {
		this.selectedSound = selectedSound;
		this.fimJogo = fimJogo;
	};
	
	@Override
	public void run() {

		File sound;
		
		if(selectedSound == "fundo") {
			sound = new File(songGame);
		} else {
			if(selectedSound == "eat") {
				sound = new File(songEat);
			} else {
				sound = new File(songEnd);
			}
		}
		
		song = new PlayerSound();
		song.play(sound);
		song.start();
		stopSongWhenGameOver(song);
	}
	private void stopSongWhenGameOver(PlayerSound song) {
		if(Tela.fimJogo) {
			song.stop();
		} else {

			try {
				Thread.sleep(100);
				stopSongWhenGameOver(song);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
