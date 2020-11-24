import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvParser {

    private final String filePath;

    public CsvParser(String filePath) {
        this.filePath = filePath;
    }

    public List<CSVRecord> read() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(filePath));
        CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader()
                .withIgnoreHeaderCase()
                .withTrim());

        return  parser.getRecords();
    }
}