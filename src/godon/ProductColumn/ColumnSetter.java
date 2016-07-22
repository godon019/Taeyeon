package godon.ProductColumn;

/**
 * Created by Godon on 2016-07-20.
 */
public class ColumnSetter {

    static public void setForLoading(Columns columns){
        columns.addColumn(new Column("상품명")); // ->상품명
        columns.addColumn(new ComparableColumn("모델명",ComparableColumn.ValueType.STRING));   //->간략한설명
        columns.addColumn(new ComparableColumn("웨딩몰공급가", ComparableColumn.ValueType.NUMBER));   //->상품공급가
        columns.addColumn(new ComparableColumn("출하가", ComparableColumn.ValueType.NUMBER));  //->시중가격
        columns.setPrimitiveProduct("모델명");
    }

    static public void setForSaving(Columns columns){
        columns.addColumn(new Column("상품명"));
     /*   columns.addColumn(new Column("큰이미지"));
        columns.addColumn(new Column("중간이미지"));
        columns.addColumn(new Column("작은이미지"));
        columns.addColumn(new Column("재고"));*/
        columns.addColumn(new ComparableColumn("상품공급가", ComparableColumn.ValueType.NUMBER));
        columns.addColumn(new ComparableColumn("상품판매가", ComparableColumn.ValueType.NUMBER));
       /* columns.addColumn(new Column("수수료율"));
        columns.addColumn(new Column("상품내용"));*/
        columns.addColumn(new Column("간략한설명"));
        columns.addColumn(new Column("시중가격"));
       /* columns.addColumn(new Column("적립금"));
        columns.addColumn(new Column("브랜드"));
        columns.addColumn(new Column("제조사"));
        columns.addColumn(new Column("원산지"));
        columns.addColumn(new Column("배송비구분"));
        columns.addColumn(new Column("배송비"));
        columns.addColumn(new Column("배송비무료금액"));
        columns.addColumn(new Column("배송정책"));
        columns.addColumn(new Column("카테고리"));
        columns.addColumn(new Column("옵션타입"));
        columns.addColumn(new Column("옵션"));*/
        columns.addColumn(new ComparableColumn("모델명",ComparableColumn.ValueType.STRING));
        columns.addColumn(new Column("로그"));
    }

    static public void setForSamSungLoading(Columns columns){
        columns.addColumn(new ComparableColumn("모델명",ComparableColumn.ValueType.STRING));
        columns.setPrimitiveProduct("모델명");
    }
}
