package godon.Editing;

import godon.Environment.Directories;
import org.junit.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by Godon on 2016-07-11.
 */
public class DirectoriesTest {
    @Test
    public void getSaveDirectory() throws Exception {
        //Given
        String loadDirectory = "E:\\LIBRARY\\Desktop\\작업\\2016-06-28 태연이 프로그램\\20160630_LG단가표(웨딩,복지몰)_(주)디에이블커머스 - 짧은거.xlsx";
        String saveDirectory = "E:\\LIBRARY\\Desktop\\작업\\2016-06-28 태연이 프로그램\\테스트2.xlsx";

        //When
        Directories directories = new Directories(loadDirectory, saveDirectory);

        //Then
        assertEquals(directories.getSaveDirectory(), saveDirectory);
    }

    @Test
    public void setSaveDirectory() throws Exception {
        //Given
        String loadDirectory = "E:\\LIBRARY\\Desktop\\작업\\2016-06-28 태연이 프로그램\\20160630_LG단가표(웨딩,복지몰)_(주)디에이블커머스 - 짧은거.xlsx";
        String saveDirectory = "E:\\LIBRARY\\Desktop\\작업\\2016-06-28 태연이 프로그램\\테스트2.xlsx";

        //When
        Directories directories = new Directories(loadDirectory, saveDirectory);
        directories.setSaveDirectory("testValue");

        //Then
        assertEquals(directories.getSaveDirectory(), "testValue");
    }

    @Test
    public void getLoadDirectory() throws Exception {
        //Given
        String loadDirectory = "E:\\LIBRARY\\Desktop\\작업\\2016-06-28 태연이 프로그램\\20160630_LG단가표(웨딩,복지몰)_(주)디에이블커머스 - 짧은거.xlsx";
        String saveDirectory = "E:\\LIBRARY\\Desktop\\작업\\2016-06-28 태연이 프로그램\\테스트2.xlsx";

        //When
        Directories directories = new Directories(loadDirectory, saveDirectory);

        //Then
        assertEquals(directories.getLoadDirectory(), loadDirectory);
    }

    @Test
    public void setLoadDirectory() throws Exception {
//Given
        String loadDirectory = "E:\\LIBRARY\\Desktop\\작업\\2016-06-28 태연이 프로그램\\20160630_LG단가표(웨딩,복지몰)_(주)디에이블커머스 - 짧은거.xlsx";
        String saveDirectory = "E:\\LIBRARY\\Desktop\\작업\\2016-06-28 태연이 프로그램\\테스트2.xlsx";

        //When
        Directories directories = new Directories(loadDirectory, saveDirectory);
        directories.setLoadDirectory("testValue");

        //Then
        assertEquals(directories.getLoadDirectory(), "testValue");
    }

}