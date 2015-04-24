package hu.gvasko.codekata.datamunging.stringtable;

import java.io.IOException;
import java.net.URI;

/**
 * Default implementation
 * Created by Gvasko on 2015.04.24..
 */
public final class Factory {

    private static Factory instance = new Factory();

    public static Factory getInstance() {
        return instance;
    }

    private Factory() {
        // nothing to do
    }

    public StringTable readStringTableFromFile(URI fileLocation, int... columnsLen) throws IOException {
        return DefaultStringTableImpl.readTable(fileLocation, columnsLen);
    }
}
