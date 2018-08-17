/*
Author: Danny Lu
Project: CIS 422 Project 2: Music Maker

Functions: play(), startPlaying(), pause(), stop(), resume()
reference the Module Interface Specification to learn more about
how to use each function.

This file mainly handles playing audio clips.
*/
import javax.sound.sampled.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.tree.TreePath;

public class play{
    static Long frame;
     static Clip clip;
    
    static String status;
    static AudioFormat format;
    static DataLine.Info info;
    static AudioInputStream inStream;
    static String fileName;
    static File file;
    static ArrayList<Clip> arraylist = new ArrayList<>();
    public static void play(String fileNameInput){
    	
        try{
            fileName = fileNameInput;
            file = new File(fileName);
            inStream = AudioSystem.getAudioInputStream(file);
            format = inStream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            
            clip.open(inStream);
            arraylist.add(clip);
            System.out.println("arrarlist is " + arraylist);

            //Loop clip
            //clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch(UnsupportedAudioFileException uae){
            System.out.println(uae);
        } catch(IOException ioe){
            System.out.println(ioe);
        } catch(LineUnavailableException lua){
            System.out.println(lua);
        }
    }

    public static void startPlaying(){
        try{
            clip.start();
            status = "playing";
        } catch(Exception e){
            System.out.println("Error with starting clip");
        }
    }
    //works for 1 clip but doesn't work for multiple clips
    public static void pause(){
        if (status.equals("paused")){
            System.out.println("Audio is already paused");
            return;
        }
        frame = clip.getMicrosecondPosition();
        System.out.println("clip clip"+clip);
        clip.stop();
        status = "paused";
    }
    public static void stopAll(){
    	for (int i = 0; i < arraylist.size(); i++){
    	//	clip.stop();
    		arraylist.get(i).stop();
    		
    	}
    }
    public Clip clip(){
    	return clip;
    }

    public static void stop(){
        if (status.equals("paused") || status.equals("stopped")){
            System.out.println("Clip is already not playing");
            return;
        }
        clip.stop();
        status = "stopped";
    }

    public static void resume() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        if (status.equals("playing")){
            System.out.println("Audio is already playing");
            return;
        }
        clip.setMicrosecondPosition(frame);
        clip.start();
        status = "playing";
    }
/*
    public static void main(String[] args){

        try{
            play p = new play();
            saveLoad l = new saveLoad();
            p.play(l.getFilePath());
            p.startPlaying();

            Scanner scanner = new Scanner(System.in);
            while(true){
                System.out.println("1. Pause");
                System.out.println("2. Resume");
                System.out.println("3. Quit");
                int choice = scanner.nextInt();
                if (choice == 1){
                    p.pause();
                }
                else if (choice == 2){
                    p.resume();
                }
                if(choice == 3){
                    p.stop();
                    break;
                }
            }
        } catch(Exception ex){
            System.out.println("Error with Playing sound");
        }
    }

    //Used to check if UI.java will create play class
    public void test(){
        System.out.println("Play Works");
    }
*/
}
