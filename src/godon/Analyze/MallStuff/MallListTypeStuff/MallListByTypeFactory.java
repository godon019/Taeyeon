package godon.Analyze.MallStuff.MallListTypeStuff;

/**
 * Created by Godon on 2016-07-27.
 */
public class MallListByTypeFactory {
    public static MallListByType getProviderByType(MallTypeEnum mallTypeEnum){
        MallListByType mallListByType = null;
        switch (mallTypeEnum){
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
