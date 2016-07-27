package godon.Analyze.MallStuff.MallListTypeStuff;

import godon.Analyze.MallStuff.MallListTypeStuff.TypeException.MallListByTypeException;
import godon.Analyze.MallStuff.MallListTypeStuff.TypeModel.MallListByTypeModel;
import godon.Analyze.MallStuff.MallListTypeStuff.TypeProduct.MallListByTypeProduct;

/**
 * Created by Godon on 2016-07-27.
 */
public class MallListByTypeFactory {
    public static MallListByType getProviderByType(MallTypeEnum mallTypeEnum){
        MallListByType mallListByType = null;
        switch (mallTypeEnum){
            case EXCEPTION:
                mallListByType = new MallListByTypeException();
                break;
            case MODEL:
                mallListByType = new MallListByTypeModel();
                break;
            case PRODUCT:
                mallListByType = new MallListByTypeProduct();
                break;
            default:
                break;
        }

        return mallListByType;
    }
}
