package hu.gvasko.codekata.datamunging.stringtable;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Gvasko on 2015.04.23..
 */
public class DefaultStringRecordImplTest {

    private StringRecord rec;

    @Before
    public void setUp() {
        rec = DefaultStringRecordImpl.newBuilder().addField("aa", "bb").addField("cc", "dd").build();
    }

    @Test(expected = RuntimeException.class)
    public void MissingFieldThrowsException() {
        rec.get("missingField");
    }

    @Test
    public void FieldsExist() {
        Assert.assertEquals("bb", rec.get("aa"));
        Assert.assertEquals("dd", rec.get("cc"));
    }
}
