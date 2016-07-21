package godon.Main;

import godon.Environment.Frame;

/**
 * Created by Godon on 2016-06-28.
 */
public class Taeyeon{

    public static void main(String[] args){

        Frame frame = new Frame();

        while(frame.okayPushed == false){
            try {

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        LoadAnalyzeAndSave loadAnalyzeAndSave = new LoadAnalyzeAndSave();
        loadAnalyzeAndSave.perform();
    }
}

