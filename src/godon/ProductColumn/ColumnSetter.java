package godon.ProductColumn;

/**
 * Created by Godon on 2016-07-20.
 */
public class ColumnSetter {

    static public void setForLoading(Columns columns){
        columns.addColumn(new ComparableColumn("모델명",ComparableColumn.ValueType.STRING));
        columns.addColumn(new ComparableColumn("최저가", ComparableColumn.ValueType.NUMBER));
        columns.setPrimitiveProduct("모델명");

    }

    static public void setForSaving(Columns columns){
        columns.addColumn(new ComparableColumn("갱신된 최저가", ComparableColumn.ValueType.NUMBER));
        columns.addColumn(new Column("로그"));
    }
}
