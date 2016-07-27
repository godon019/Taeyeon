package godon.Analyze.MallStuff.MallException;

/**
 * Created by Godon on 2016-07-27.
 */
public class NoGoodMallExistException extends Exception {
    public NoGoodMallExistException() {
        super("There are no good malls, so choose next good");
    }
}
