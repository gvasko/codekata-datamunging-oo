package hu.gvasko.codekata.datamunging;

import hu.gvasko.codekata.datamunging.DataMunging;
import hu.gvasko.codekata.datamunging.stringtable.Factory;
import hu.gvasko.codekata.datamunging.stringtable.FieldFilter;
import hu.gvasko.codekata.datamunging.stringtable.StringRecord;
import hu.gvasko.codekata.datamunging.stringtable.StringTable;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * The exercise as a test
 * Created by Gvasko on 2015.04.24..
 */
public class DataMungingTest {

    @Test
    public void testDayOfSmallestTemperatureSpread() throws IOException, URISyntaxException {
        StringTable weather = getWeatherTable();
        weather.addFilter(getWeatherSpecificFilter());
        Assert.assertEquals(14, DataMunging.getDayOfSmallestTemperatureSpread(weather));
    }

    @Test
    public void testARowInWeatherTable() throws IOException, URISyntaxException {
        StringTable weather = getWeatherTable();
        StringRecord recDay9 = weather.getRecordsWhere( rec -> rec.get("Dy").equals("9")).get(0);
        Assert.assertEquals("32*", recDay9.get("MnT"));
        weather.addFilter(getWeatherSpecificFilter());
        recDay9 = weather.getRecordsWhere( rec -> rec.get("Dy").equals("9")).get(0);
        Assert.assertEquals("32", recDay9.get("MnT"));
    }

    @Test
    public void testTeamOfSmallestDifferenceInGoals() throws IOException, URISyntaxException {
        StringTable football = getFootballTable();
        Assert.assertEquals("Aston_Villa", DataMunging.getTeamOfSmallestDifferenceInGoals(football));
    }
    private StringTable getWeatherTable() throws IOException, URISyntaxException {
        URL datFile = this.getClass().getResource("weather.dat");
        return Factory.getInstance().readStringTableFromFile(datFile.toURI(), 5, 6, 6);
    }

    private StringTable getFootballTable() throws IOException, URISyntaxException {
        URL datFile = this.getClass().getResource("football.dat");
        return Factory.getInstance().readStringTableFromFile(datFile.toURI(), 7, 16, 6, 4, 4, 6, 4, 3, 6, 3);
    }

    private FieldFilter getWeatherSpecificFilter() {
        return (String k, String v) -> "MxT".equals(k) || "MnT".equals(k) ? v.replace('*', ' ').trim() : v;
    }

}
