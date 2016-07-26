package godon.Analyze.Log.AnalyzingProvider;

import godon.Analyze.Log.AnalyzingProvider.SamSungCase.SolutionArrFactory;
import godon.Analyze.Log.AnalyzingProvider.SamSungCase.SolutionEnum;
import godon.Analyze.Log.AnalyzingProvider.SamSungCase.Solution;

import java.util.ArrayList;

/**
 * Created by Godon on 2016-07-25.
 */
public class SamSungTrimmingCase {
    StringBuilder log;
    String modelName;
    String productName;

    public SamSungTrimmingCase(StringBuilder log, String modelName, String productName){
        this.log = log;
        this.modelName = modelName;
        this.productName = productName;
    }


    public String getTrimmedModelName() throws Exception {
        log.append("Do SamSung Trimming : \n");

        if(assertProductNameDoesntContainModelName() && assertProductNameIsNotEmpty()) {
            doMainTrimming();
        }
        else{
            log.append(" So nothing to do with SamSung Trimming \n");
        }

        return modelName;
    }

    private boolean assertProductNameDoesntContainModelName(){
        if(productName.contains(modelName)){
            log.append(" ProductName Contain ModelName \n");
            return false;
        }
        else {
            return true;
        }
    }

    private boolean assertProductNameIsNotEmpty(){
        if(productName.trim().isEmpty()){
            log.append(" ProductName is Empty \n");
            return false;
        }
        else {
            return true;
        }
    }

    private void doMainTrimming()throws Exception{
        ArrayList<Solution> caseArr = new SolutionArrFactory().getSamSumgCases(log, modelName, productName);
        SolutionEnum resultEnum = null;

        for (Solution caseOne : caseArr) {
            modelName = caseOne.getSolvedName();

            resultEnum = caseOne.getEnumAboutResult();
            if (ifAnyOfSolutionsSuccess(resultEnum))
                break;
        }

        if (ifNoSolutionSuccess(resultEnum)) {
            throw new Exception("Cannot get proper result of trimmed Model Name : " + modelName);
        }
    }

    private boolean ifAnyOfSolutionsSuccess(SolutionEnum resultEnum){
        if(resultEnum == SolutionEnum.SOLVED)
            return true;
        else
            return false;
    }

    private boolean ifNoSolutionSuccess(SolutionEnum resultEnum){
        if((resultEnum != SolutionEnum.SOLVED) || (resultEnum == null))
            return true;
        else
            return false;

    }


}
