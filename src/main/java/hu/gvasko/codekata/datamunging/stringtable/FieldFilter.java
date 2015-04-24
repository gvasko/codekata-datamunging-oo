package hu.gvasko.codekata.datamunging.stringtable;

/**
 * Created by Gvasko on 2015.04.23..
 */
public interface FieldFilter {
    String executeFilter(String fieldName, String value);
}
