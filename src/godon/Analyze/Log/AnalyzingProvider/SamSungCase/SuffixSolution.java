package godon.Analyze.Log.AnalyzingProvider.SamSungCase;

import godon.Util.lg;

/**
 * Created by Godon on 2016-07-26.
 */
public class SuffixSolution extends Solution {
    String productName;
    String suffix;

    public SuffixSolution(StringBuilder log, String modelName, String productName, String suffix){
        this.log = log;
        this.modelName = modelName;
        this.productName = productName;
        this.suffix = suffix;
    }

    public boolean hasSuffix(String str){
        if( str.endsWith(suffix))
            return true;
        else
            return false;
    }

    String removeSuffix(String string){
        log.append(suffix + " suffix 제거\n");
        String productNameWithoutB2B = modelName.replace(suffix, "");
        return productNameWithoutB2B;
    }

    boolean isProductNameNotNull(){
        if(!productName.trim().isEmpty()){
            return true;
        }
        else{
            log.append(lg.er());
            return false;
        }

    }

    boolean assertProductNameDosentContainModelName(){
        if(!productName.contains(modelName)){
            return true;
        }
        else{
            log.append(lg.er());
            return false;
        }


    }

    @Override
    public void solve() {
        try {
            if(hasSuffix(modelName) && isProductNameNotNull() && assertProductNameDosentContainModelName()){
                modelName = removeSuffix(modelName);
                setEnumAsSolved();
            }
            else {
                setEnumAsNothingDone();
            }
        }
        catch (Exception e){
            e.printStackTrace();
            setEnumAsFailed();
        }
    }

    public String getSolvedName(){
        solve();

        if(getEnumAboutResult() != SolutionEnum.SOLVED){
            try {
                throw new Exception("Problem couldn't be solved, / ModelName : " + modelName + " / Suffix : "+ suffix + " / Result is : " + getEnumAboutResult());
            }
            catch (Exception e) {
                e.printStackTrace();
                log.append(e.toString()+"\n");
            }
        }

        return modelName;
    }

}
