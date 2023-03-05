package Reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class SmilesReader {
    BufferedReader inputfile;

    public HashMap<String, String> getDictionary(String fileName) throws IOException {
        HashMap<String, String> results = new HashMap<>();
        inputfile = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = inputfile.readLine()) != null) {
            if (line.startsWith("SMILE")) {
                continue;
            }
            String[] parts = line.split("\t");
            String key = parts[0];
            String value = parts[1];

            results.put(key, value);
        }
        return results;
    }
}
