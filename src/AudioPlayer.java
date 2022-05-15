package com.tutorial.main;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class AudioPlayer {
	
	static void playAudioLoop(String fileLocation) {
		try {
			File filePath = new File(fileLocation);
			
			if(filePath.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(filePath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(-12.0f);
				clip.start();
				
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static void playAudio(String fileLocation) {
		try {
			File filePath = new File(fileLocation);
			
			if(filePath.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(filePath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(-11.0f);
				clip.start();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
