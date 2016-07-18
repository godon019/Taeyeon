package godon.Product;

/**
 * Created by Godon on 2016-07-18.
 */
public class ComparedDebugProduct extends Product {

    public static String STANDARD_ERROR_MESSEGE = "Compare Debug Error";

    public enum Status{
        NOT_DEBUGGED_YET,
        DEBUGGING_IS_DONE,
        ERROR_OCCURED_BEFORE_INSPECTION,    //it occured before compares, like different type or name or size
        EVERYTHING_FINE,
        HAS_ERROR;
    }
    Status status;

    public ComparedDebugProduct(Product product) {
        super(product.getName(), product.getiOType());
        status = Status.NOT_DEBUGGED_YET;
    }

    public Status getStatus(){
        updateStatus();
        return status;
    }

    public void errorOccuredBeforeInspection(){
        status = Status.ERROR_OCCURED_BEFORE_INSPECTION;
    }

    public void debuggingIsDone(){
        status = Status.DEBUGGING_IS_DONE;
    }

    void updateStatus(){
        if(status != Status.ERROR_OCCURED_BEFORE_INSPECTION) {
            boolean errorFound = false;
            try {
                errorFound = hasErrorBeenFoundWhileGoingOverValues();
            } catch (Exception e) {
                e.printStackTrace();
                status = Status.HAS_ERROR;
                errorFound = true;
            }

            if(errorFound == false){
                status = Status.EVERYTHING_FINE;
            }

        }
    }

    boolean hasErrorBeenFoundWhileGoingOverValues(){
        for (String value : this.getValues()) {
            if (value.contains(STANDARD_ERROR_MESSEGE)) {
                status = Status.HAS_ERROR;
                return true;
            }
        }
        return false;
    }

    public boolean hasError(){
        if(getStatus() != Status.EVERYTHING_FINE){
            return true;
        }
        else {
            return false;
        }
    }

}
