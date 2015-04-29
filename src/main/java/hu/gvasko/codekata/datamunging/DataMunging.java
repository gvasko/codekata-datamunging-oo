package hu.gvasko.codekata.datamunging;

import hu.gvasko.codekata.datamunging.stringtable.StringRecord;

import java.util.List;

/**
 * DataMunging functionality
 * Created by Gvasko on 2015.04.24..
 */
public final class DataMunging {

    public static StringRecord getFirstMinDiffRecord(List<StringRecord> records, String f1, String f2) {
        return records.stream().min( (rec1, rec2) -> Float.compare(getDiff(rec1, f1, f2), getDiff(rec2, f1, f2)) ).get();
    }

    private static float getDiff(StringRecord rec, String f1, String f2) {
        return Math.abs(Float.parseFloat(rec.get(f1)) - Float.parseFloat(rec.get(f2)));
    }

}
