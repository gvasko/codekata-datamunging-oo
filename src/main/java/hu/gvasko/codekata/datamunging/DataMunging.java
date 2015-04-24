package hu.gvasko.codekata.datamunging;

import hu.gvasko.codekata.datamunging.stringtable.StringRecord;
import hu.gvasko.codekata.datamunging.stringtable.StringTable;

import java.util.List;

/**
 * DataMunging functionality
 * Created by Gvasko on 2015.04.24..
 */
public class DataMunging {

    public static int getDayOfSmallestTemperatureSpread(StringTable table) {
        StringRecord minRec = getFirstMinDiffRecord(table.getAllRecords(), "MxT", "MnT");
        if (minRec == null) {
            return -1;
        } else {
            return Integer.parseInt(minRec.get("Dy"));
        }
    }

    public static String getTeamOfSmallestDifferenceInGoals(StringTable table) {
        StringRecord minRec = getFirstMinDiffRecord(table.getRecordsWhere( rec -> !rec.get("Team").startsWith("---")), "F", "A");
        if (minRec == null) {
            return "";
        } else {
            return minRec.get("Team");
        }
    }

    private static StringRecord getFirstMinDiffRecord(List<StringRecord> records, String f1, String f2) {
        StringRecord minRec = null;
        float minDiff = 0.0f;
        for (StringRecord rec : records) {
            float diff = Math.abs(Float.parseFloat(rec.get(f1)) - Float.parseFloat(rec.get(f2)));
            if (minRec == null || diff < minDiff) {
                minRec = rec;
                minDiff = diff;
            }
        }
        return minRec;
    }

}
