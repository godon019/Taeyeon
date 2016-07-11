package godon.Environment;

/**
 * Created by Godon on 2016-07-09.
 */
public class Directories {
    String loadDirectory = null;
    String saveDirectory = null;

    public Directories(String loadDirectory, String saveDirectory){
        this.loadDirectory = loadDirectory;
        this.saveDirectory = saveDirectory;
    }

    public String getSaveDirectory() {
        return saveDirectory;
    }

    public void setSaveDirectory(String saveDirectory) {
        this.saveDirectory = saveDirectory;
    }

    public String getLoadDirectory() {

        return loadDirectory;
    }

    public void setLoadDirectory(String loadDirectory) {
        this.loadDirectory = loadDirectory;
    }
}
