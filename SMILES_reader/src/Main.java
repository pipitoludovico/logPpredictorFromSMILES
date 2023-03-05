import Reader.Predictor;
import Reader.SmilesReader;
import org.openscience.cdk.exception.InvalidSmilesException;
import org.openscience.cdk.qsar.result.IDescriptorResult;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException, InvalidSmilesException {
        if (args.length != 1) {
            System.out.println("Usage: java Main <input_file>");
            System.exit(1);
        }
        String fileName = args[0];
        SmilesReader smilesReader = new SmilesReader();
        HashMap<String, String> dictionary = smilesReader.getDictionary(fileName);
        HashMap<String, IDescriptorResult> predicted = new Predictor().getLogP(dictionary);
        try {
            PrintWriter writer = new PrintWriter("output.tsv");
            writer.println("SMILES\tlogP\tID");
            for (String key : predicted.keySet()) {
                IDescriptorResult value1 = predicted.get(key);
                String value2 = dictionary.get(key);
                writer.println(key + "\t" + value1.toString() + "\t" + value2);
            }
            writer.close();
            System.out.println("File TSV completed!");
        } catch (IOException e) {
            System.out.println("Errore nella TSV file creation: " + e.getMessage());
        }
    }
}
