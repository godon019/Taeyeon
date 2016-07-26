package godon.Analyze.Log.AnalyzingProvider.SamSungCase;

import java.util.ArrayList;

/**
 * Created by Godon on 2016-07-26.
 */
public class SolutionArrFactory {
    public ArrayList<Solution> getSamSumgCases(StringBuilder log, String modelName, String productName){
        ArrayList<Solution> aCases = new ArrayList<>();
        aCases.add(new SuffixSolution(log, modelName, productName, "S"));
        return aCases;
    }
}
