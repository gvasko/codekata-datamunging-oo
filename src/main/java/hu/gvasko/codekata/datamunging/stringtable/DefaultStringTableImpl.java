package hu.gvasko.codekata.datamunging.stringtable;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;

/**
 * Default implementation
 * Created by Gvasko on 2015.04.23..
 */
class DefaultStringTableImpl implements StringTable {

    static class Builder {
        private String[] schema;
        private List<String[]> records;

        Builder(String... schema) {
            // TODO: no duplicate names allowed
            this.schema = schema;
            records = new ArrayList<>();
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

    public static StringTable readTable(URI fileLocation, int... columnsLen) throws IOException {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileLocation))) {
            Builder tableBuilder = null;
            String line;
            while ((line = br.readLine()) != null) {
                if ("".equals(line.trim())) {
                    continue;
                }
                String[] fields = toStringArray(line, columnsLen);
                if (tableBuilder == null) {
                    tableBuilder = new Builder(fields);
                } else {
                    tableBuilder.addRecord(fields);
                }
            }
            if (tableBuilder == null) {
                return null;
            } else {
                return tableBuilder.build();
            }
        }
    }

    private static String[] toStringArray(String line, int... columnsLen) {
        String[] strArr = new String[columnsLen.length];
        int beginIndex = 0;
        for (int i = 0; i < columnsLen.length; i++) {
            int endIndex = Math.min(beginIndex + columnsLen[i], line.length());
            strArr[i] = line.substring(beginIndex, endIndex).trim();
            beginIndex = endIndex;
        }
        return strArr;
    }

    private String[] schema;
    private List<String[]> records;
    private List<FieldEncoder> fieldEncoders;

    DefaultStringTableImpl(String[] sharedSchema, List<String[]> sharedRecords) {
        this.schema = sharedSchema;
        this.records = sharedRecords;
        this.fieldEncoders = new ArrayList<>();
    }

    @Override
    public List<StringRecord> getAllRecords() {
        return getRecordsWhere( r -> true );
    }

    @Override
    public List<StringRecord> getRecordsWhere(Predicate<StringRecord> predicate) {
        List<StringRecord> resultRecords = new ArrayList<>();
        for (String[] rec : this.records) {
            StringRecord srec = toStringRecord(rec);
            if (predicate.test(srec)) {
                resultRecords.add(srec);
            }
        }
        return resultRecords;
    }

    private StringRecord toStringRecord(String[] rec) {
        DefaultStringRecordImpl.Builder recBuilder = DefaultStringRecordImpl.newBuilder();
        for (int i = 0; i < schema.length; i++) {
            String field = schema[i];
            String value = getEncodedValue(field, rec[i]);
            recBuilder.addField(field, value);
        }
        return recBuilder.build();
    }

    private String getEncodedValue(String field, String value) {
        String tmp = value;
        for (FieldEncoder encoder : fieldEncoders) {
            tmp = encoder.execute(field, tmp);
        }
        return tmp;
    }

    @Override
    public void addFieldEncoder(FieldEncoder encoder) {
        fieldEncoders.add(encoder);
    }
}
