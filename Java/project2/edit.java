/*
Author: Danny Lu
Project: CIS 422 Project 2: Music Maker

Functions: speed(), pitch(), volume(), loop()
reference the Module Interface Specification to learn more about
how to use each function.

This file mainly handles manipulating the audio of specified clips.
*/
import javax.sound.sampled.*;
import java.util.Scanner;

public class edit{
    //volume
    public void volume(float volume, Clip editClip){
        if (volume < 0f || volume > 1f){
            throw new IllegalArgumentException("Volumne not valid: " + volume);
        }
        FloatControl gain = (FloatControl) editClip.getControl(FloatControl.Type.MASTER_GAIN);
        gain.setValue(20f * (float) Math.log10(volume));
    }

    //speed
    public void speed(float rate, Clip editClip, AudioFormat editFormat){
        //get current sample rate
        float currSample = editFormat.getSampleRate();
        FloatControl sampleRate = (FloatControl) editClip.getControl(FloatControl.Type.SAMPLE_RATE);
        sampleRate.setValue(currSample * rate);
    }

    //pitch
    public void pitch(){
    }

    public void loop(Clip editClip){
        editClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void main(String args[]){
    }
}
