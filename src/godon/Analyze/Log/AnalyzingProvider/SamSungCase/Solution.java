package godon.Analyze.Log.AnalyzingProvider.SamSungCase;

/**
 * Created by Godon on 2016-07-26.
 */
public abstract class Solution {
    StringBuilder log;
    String modelName;
    SolutionEnum solved = SolutionEnum.BEFORE_SOLVE;

    public abstract void solve();

    protected void setEnumAsNothingDone(){
        solved = SolutionEnum.NOTHING_DONE;
    }
    protected void setEnumAsFailed(){
        solved = SolutionEnum.FAILED;
    }
    protected void setEnumAsSolved(){
        solved = SolutionEnum.SOLVED;
    }
    public SolutionEnum getEnumAboutResult(){
        return solved;
    }

    public abstract String getSolvedName();
}
