package hu.gvasko.codekata.datamunging.stringtable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gvasko on 2015.04.23..
 */
class DefaultStringRecordImpl implements  StringRecord {

    static class Builder {

        Map<String, String> stringMap;

        Builder() {
            stringMap = new HashMap<>();
        }

        Builder addField(String key, String value) {
            stringMap.put(key, value);
            return this;
        }

        DefaultStringRecordImpl build() {
            return new DefaultStringRecordImpl(stringMap);
        }
    }

    static Builder newBuilder() {
        return new Builder();
    }

    private Map<String, String> fields;

    DefaultStringRecordImpl(Map<String, String> sharedFields) {
        fields = sharedFields;
    }

    public String get(String field) {
        if (fields.containsKey(field)) {
            return fields.get(field);
        }

        throw new RuntimeException("Field does not exist.");
    }
}
