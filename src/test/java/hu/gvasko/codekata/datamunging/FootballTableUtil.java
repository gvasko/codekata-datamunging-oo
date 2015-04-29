package hu.gvasko.codekata.datamunging;

import hu.gvasko.codekata.datamunging.stringtable.Factory;
import hu.gvasko.codekata.datamunging.stringtable.StringRecord;
import hu.gvasko.codekata.datamunging.stringtable.StringTable;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Util class for football table
 * Created by Gvasko on 2015.04.29..
 */
public class FootballTableUtil {

    private static FootballTableUtil soleInstance = new FootballTableUtil();

    public static FootballTableUtil getSoleInstance() {
        return soleInstance;
    }

    public String getTeamOfSmallestDifferenceInGoals(StringTable table) {
        StringRecord minRec = DataMunging.getFirstMinDiffRecord(table.getRecordsWhere(rec -> !rec.get("Team").startsWith("---")), "F", "A");
        return minRec == null ? "" : minRec.get("Team");
    }


    public StringTable getFootballTable() throws IOException, URISyntaxException {
        URL datFile = this.getClass().getResource("football.dat");
        return Factory.getInstance().readStringTableFromFile(datFile.toURI(), 7, 16, 6, 4, 4, 6, 4, 3, 6, 3);
    }
}
