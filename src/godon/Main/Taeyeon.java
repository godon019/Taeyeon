package godon.Main;

import godon.Environment.Directories;
import godon.Environment.Frame;

/**
 * Created by Godon on 2016-06-28.
 */
public class Taeyeon{

    public static void main(String[] args){
        Directories directories = new Directories(null, null);
        //directories.setLoadDirectory("E:\\LIBRARY\\Desktop\\작업\\2016-06-28 태연이 프로그램\\20160630_LG단가표(웨딩,복지몰)_(주)디에이블커머스 - 짧은거.xlsx");
        directories.setLoadDirectory("E:\\LIBRARY\\Desktop\\작업\\2016-06-28 태연이 프로그램\\20160630_LG단가표(웨딩,복지몰)_(주)디에이블커머스.xlsx");
        directories.setSaveDirectory("E:\\LIBRARY\\Desktop\\작업\\2016-06-28 태연이 프로그램\\테스트2.xlsx");

        Frame frame = new Frame(directories);

        while(frame.okayPushed == false){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        LoadAnalyzeAndSave loadAnalyzeAndSave = new LoadAnalyzeAndSave(directories);
        loadAnalyzeAndSave.perform();


    }
}

