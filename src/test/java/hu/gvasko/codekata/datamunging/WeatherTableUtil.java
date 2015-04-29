package hu.gvasko.codekata.datamunging;

import hu.gvasko.codekata.datamunging.stringtable.Factory;
import hu.gvasko.codekata.datamunging.stringtable.FieldFilter;
import hu.gvasko.codekata.datamunging.stringtable.StringRecord;
import hu.gvasko.codekata.datamunging.stringtable.StringTable;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by Gvasko on 2015.04.29..
 */
class WeatherTableUtil {

    private static WeatherTableUtil soleInstance = new WeatherTableUtil();

    public static WeatherTableUtil getInstance() {
        return soleInstance;
    }

    public int getDayOfSmallestTemperatureSpread(StringTable table) {
        StringRecord minRec = DataMunging.getFirstMinDiffRecord(table.getAllRecords(), "MxT", "MnT");
        if (minRec == null) {
            return -1;
        } else {
            return Integer.parseInt(minRec.get("Dy"));
        }
    }

    public StringTable getWeatherTable() throws IOException, URISyntaxException {
        URL datFile = this.getClass().getResource("weather.dat");
        StringTable weather = Factory.getInstance().readStringTableFromFile(datFile.toURI(), 5, 6, 6);
        return weather;
    }

    public void addFilter(StringTable table) {
        table.addFilter(getWeatherSpecificFilter());
    }

    private static FieldFilter getWeatherSpecificFilter() {
        return (String k, String v) -> "MxT".equals(k) || "MnT".equals(k) ? v.replace('*', ' ').trim() : v;
    }


}
