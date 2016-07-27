package godon.Analyze.MallStuff.MallListTypeStuff;

import godon.Analyze.MallStuff.MallException.NoMallTypeExistException;

/**
 * Created by Godon on 2016-07-27.
 */
public enum MallTypeEnum {
    MODEL("_model_list"),
    PRODUCT("_product_list");

    String elementClassName;

    MallTypeEnum(String elementClassName){
        this.elementClassName = elementClassName;
    }

    public String getElementClassName(){
        return elementClassName;
    }

    public static MallTypeEnum getMallTypeFromElementClassName(String elementClassName)throws NoMallTypeExistException {
        for(MallTypeEnum mallTypeEnum : MallTypeEnum.values()){
            if(mallTypeEnum.getElementClassName().equals(elementClassName))
                return mallTypeEnum;
        }

        throw new NoMallTypeExistException("No MallTypeEnum Exist with name : " + elementClassName);
    }
}
