package godon.Product;

import godon.ProductColumn.Column;
import godon.ProductColumn.ComparableColumn;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Godon on 2016-07-18.
 */
public class ComparableColumnTest {
    @Test
    public void getValueType() throws Exception {
        Column column = new ComparableColumn("모델명", Column.IOType.READ_AND_WRITE, ComparableColumn.ValueType.STRING);
        ComparableColumn comparableProduct = (ComparableColumn) column;

        assertEquals(comparableProduct.getValueType(), ComparableColumn.ValueType.STRING);
    }



}