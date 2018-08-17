import java.io.*;
import java.util.*;

public class soundManager{
    private static ArrayList<play> clips = new ArrayList<play>();
    private static ArrayList<String> paths = new ArrayList<String>();
    private int num = 0;

    public void addClip(play clip){
        saveLoad l = new saveLoad();
        paths.add(l.getFilePath());
        clips.add(clip);
        num++;
    }

    public void playAll(){
        for (int i = 0; i < clips.size(); i++){
            clips.get(i).play(paths.get(i));
        }
    }

    public void startAll(){
        for (int j = 0; j < clips.size(); j++){
            clips.get(j).startPlaying();
        }
    }
    //Need to debug this function
    public void pauseAll(){
        for (int i = 0; i < clips.size(); i++){
            clips.get(i).pause();
            System.out.println("Clip: " + i + " paused");
        }
    }

    public void resumeAll(){
        for (int i = 0; i < clips.size(); i++){
            try{
                clips.get(i).resume();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    /*
    public static void main(String args[]){
        try{
            play c1 = new play();
            play c2 = new play();
            //play c3 = new play();
            soundManager s = new soundManager();
            s.addClip(c1);
            s.addClip(c2);
            //s.addClip(c3);
            Scanner scan = new Scanner(System.in);
            while(true){
                int exit = scan.nextInt();
                if (exit == 1){
                    s.playAll();
                    s.startAll();
                }
                if (exit == 2){
                    //pause
                    s.pauseAll();
                } if (exit == 3){
                    s.resumeAll();
                }
                if (exit == 4){
                    break;
                }
            }
        } catch (Exception ex){
            System.out.println("Error with soundManager");
        }
    }
    */
}
