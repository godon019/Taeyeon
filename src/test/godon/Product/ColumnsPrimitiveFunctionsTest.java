package godon.Product;

import godon.ColumnPackage.Column;
import godon.ColumnPackage.Columns;
import godon.ColumnPackage.ComparableColumn;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Godon on 2016-07-19.
 */
public class ColumnsPrimitiveFunctionsTest {


    @Test
    public void Test() throws Exception {
        Columns columns = new Columns();
        columns.addColumn(new Column("일련번호"));
        columns.addColumn(new ComparableColumn("모델", ComparableColumn.ValueType.NUMBER));
        columns.addColumn(new Column("번호"));

        assertEquals("Column lists : 일련번호, 모델, 번호,", columns.toString());

        columns.removeColumn("번호");
        assertEquals("Column lists : 일련번호, 모델,", columns.toString());

        columns.addColumn(new Column("모델"));
        assertEquals("Column lists : 일련번호, 모델, 모델,", columns.toString());
    }

}