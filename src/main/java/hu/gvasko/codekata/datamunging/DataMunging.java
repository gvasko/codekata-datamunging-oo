package hu.gvasko.codekata.datamunging;

import hu.gvasko.codekata.datamunging.stringtable.StringRecord;

import java.util.List;

/**
 * DataMunging functionality
 * Created by Gvasko on 2015.04.24..
 */
public final class DataMunging {

    public static StringRecord getFirstMinDiffRecord(List<StringRecord> records, String f1, String f2) {
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
