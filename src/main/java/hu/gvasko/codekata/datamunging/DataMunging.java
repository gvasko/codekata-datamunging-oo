package hu.gvasko.codekata.datamunging;

import hu.gvasko.codekata.datamunging.stringtable.StringRecord;
import hu.gvasko.codekata.datamunging.stringtable.StringTable;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.IntSummaryStatistics;
import java.util.stream.Stream;

/**
 * DataMunging functionality
 * Created by Gvasko on 2015.04.24..
 */
public class DataMunging {

    public static int getDayOfSmallestTemperatureSpread(StringTable table) {
        StringRecord minRec = null;
        float minDiff = 0.0f;
        for (StringRecord rec : table.getAllRecords()) {
            float diff = Float.parseFloat(rec.get("MxT")) - Float.parseFloat(rec.get("MnT"));
            if (diff < 0.0f) {
                throw new RuntimeException("Inconsistent data.");
            }
            if (minRec == null || diff < minDiff) {
                minRec = rec;
                minDiff = diff;
            }
        }
        if (minRec == null) {
            return -1;
        } else {
            return Integer.parseInt(minRec.get("Dy"));
        }
    }
}
