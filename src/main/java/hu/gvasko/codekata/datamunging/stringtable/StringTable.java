package hu.gvasko.codekata.datamunging.stringtable;

import java.util.List;
import java.util.function.Predicate;

/**
 * Immutable string table
 * Created by Gvasko on 2015.04.23..
 */
public interface StringTable {
    List<StringRecord> getAllRecords();
    List<StringRecord> getRecordsWhere(Predicate<StringRecord> predicate);
    void addFieldEncoder(FieldEncoder encoder);
}
