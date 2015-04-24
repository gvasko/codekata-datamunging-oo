package hu.gvasko.codekata.datamunging.stringtable;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Default implementation
 * Created by Gvasko on 2015.04.23..
 */
public class DefaultStringTableImplTest {

    private DefaultStringTableImpl table;

    @Before
    public void setUp() {
        table = DefaultStringTableImpl.newBuilder("aa", "bb", "cc").addRecord("a1", "b1", "c1").addRecord("a2", "b2", "c2").build();
    }


    @Test
    public void test() {
        StringBuilder sb = new StringBuilder();
        for (StringRecord rec : table.getAllRecords()) {
            sb.append(rec.get("aa"));
            sb.append(", ");
            sb.append(rec.get("bb"));
            sb.append(", ");
            sb.append(rec.get("cc"));
            sb.append("; ");
        }
        Assert.assertEquals("a1, b1, c1; a2, b2, c2; ", sb.toString());
    }
}
