package hu.gvasko.codekata.datamunging;

import hu.gvasko.codekata.datamunging.stringtable.StringRecord;
import hu.gvasko.codekata.datamunging.stringtable.StringTable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * The exercise as a test
 * Created by Gvasko on 2015.04.24..
 */
public class DataMungingTest {

    private WeatherTableUtil weatherTableUtil;
    private FootballTableUtil footballTableUtil;

    @Before
    public void setUp() {
        weatherTableUtil = WeatherTableUtil.getInstance();
        footballTableUtil = FootballTableUtil.getSoleInstance();
    }

    @Test
    public void testDayOfSmallestTemperatureSpread() throws IOException, URISyntaxException {
        StringTable weather = weatherTableUtil.getWeatherTable();
        weatherTableUtil.addFilter(weather);
        Assert.assertEquals(14, weatherTableUtil.getDayOfSmallestTemperatureSpread(weather));
    }

    @Test
    public void testRow9InWeatherTable() throws IOException, URISyntaxException {
        StringTable weather = weatherTableUtil.getWeatherTable();
        StringRecord recDay9 = weather.getRecordsWhere( rec -> rec.get("Dy").equals("9")).get(0);
        Assert.assertEquals("32*", recDay9.get("MnT"));
        weatherTableUtil.addFilter(weather);
        recDay9 = weather.getRecordsWhere( rec -> rec.get("Dy").equals("9")).get(0);
        Assert.assertEquals("32", recDay9.get("MnT"));
    }

    @Test
    public void testTeamOfSmallestDifferenceInGoals() throws IOException, URISyntaxException {
        StringTable football = footballTableUtil.getFootballTable();
        Assert.assertEquals("Aston_Villa", footballTableUtil.getTeamOfSmallestDifferenceInGoals(football));
    }

}
