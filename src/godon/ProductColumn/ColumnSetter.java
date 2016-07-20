package godon.ProductColumn;

/**
 * Created by Godon on 2016-07-20.
 */
public class ColumnSetter {

    static public void setForLoading(Columns columns){
        columns.addProduct(new ComparableColumn("모델명",ComparableColumn.ValueType.STRING));
        columns.addProduct(new ComparableColumn("최저가", ComparableColumn.ValueType.NUMBER));
        columns.setPrimitiveProduct("모델명");

    }

    static public void setForSaving(Columns columns){
        columns.addProduct(new ComparableColumn("갱신된 최저가", ComparableColumn.ValueType.NUMBER));
        columns.addProduct(new Column("로그"));
    }
}
