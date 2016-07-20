package godon.Product;

import godon.ProductColumn.Column;
import godon.ProductColumn.Columns;
import godon.ProductColumn.ComparableColumn;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Godon on 2016-07-19.
 */
public class ColumnArrayFunctionsTest {


    @Test
    public void Test() throws Exception {
        Columns columns = new Columns();
        columns.addProduct(new Column("일련번호", Column.IOType.WRITE_ONLY));
        columns.addProduct(new ComparableColumn("모델", Column.IOType.WRITE_ONLY, ComparableColumn.ValueType.NUMBER));
        columns.addProduct(new Column("번호", Column.IOType.WRITE_ONLY));

        assertEquals("Column lists : 일련번호, 모델, 번호,", columns.toString());

        columns.removeProduct("모델");
        assertEquals("Column lists : 일련번호, 번호,", columns.toString());

        columns.addProduct(new Column("모델", Column.IOType.WRITE_ONLY));
        assertEquals("Column lists : 일련번호, 번호, 모델,", columns.toString());

        //이름이 중복될때 타입도 중복되면 add안되게 하기
    }

}