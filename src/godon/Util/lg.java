package godon.Util;

/**
 * Created by Godon on 2016-07-26.
 */
public class lg {
    public static String er(){
        StackTraceElement[] stacks = new Throwable().getStackTrace();
        StackTraceElement currentStack = stacks[ 1 ];
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Error occured : ");
        stringBuilder.append( "CLASS : " + currentStack.getClassName() );
        stringBuilder.append( "METHOD : " + currentStack.getMethodName() +" : \n");
        return stringBuilder.toString();
    }
}
