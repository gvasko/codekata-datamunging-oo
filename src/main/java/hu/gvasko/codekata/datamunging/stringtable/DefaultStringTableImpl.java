package hu.gvasko.codekata.datamunging.stringtable;

import java.util.*;

/**
 * Default implementation
 * Created by Gvasko on 2015.04.23..
 */
class DefaultStringTableImpl implements StringTable {

    static class Builder {
        private String[] schema;
        private List<String[]> records;

        Builder(String... schema) {
            this.schema = schema;
            records = new ArrayList<String[]>();
        }

        Builder addRecord(String... fields) {
            if (schema.length != fields.length) {
                throw new RuntimeException("Unexpected record.");
            }
            records.add(fields);
            return this;
        }

        DefaultStringTableImpl build() {
            return new DefaultStringTableImpl(schema, records);
        }

    }

    static Builder newBuilder(String... schema) {
        return new Builder(schema);
    }

    private String[] schema;
    private List<String[]> records;
    private Map<String,List<FieldFilter>> fieldFilters;

    DefaultStringTableImpl(String[] sharedSchema, List<String[]> sharedRecords) {
        this.schema = sharedSchema;
        this.records = sharedRecords;
        this.fieldFilters = new HashMap<String, List<FieldFilter>>();
    }

    public Collection<StringRecord> getAllRecords() {
        List<StringRecord> allRecords = new ArrayList<StringRecord>();
        for (String[] rec : records) {
            DefaultStringRecordImpl.Builder recBuilder = DefaultStringRecordImpl.newBuilder();
            for (int i = 0; i < schema.length; i++) {
                recBuilder.addField(schema[i], rec[i]);
            }
            allRecords.add(recBuilder.build());
        }
        return allRecords;
    }

    public void addFilter(FieldFilter filter, String... field) {

    }
}
