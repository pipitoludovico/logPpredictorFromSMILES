package Reader;

import org.openscience.cdk.DefaultChemObjectBuilder;
import org.openscience.cdk.exception.InvalidSmilesException;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.qsar.result.IDescriptorResult;
import org.openscience.cdk.smiles.SmilesParser;
import org.openscience.cdk.qsar.DescriptorValue;
import org.openscience.cdk.qsar.descriptors.molecular.XLogPDescriptor;

import java.util.HashMap;
import java.util.Map;

public class Predictor {
    HashMap<String, IDescriptorResult> outputDict = new HashMap<>();

    public HashMap<String, IDescriptorResult> getLogP(HashMap<String, String> inputDict) throws InvalidSmilesException {

        for (Map.Entry<String, String> entry : inputDict.entrySet()) {

            SmilesParser smilesParser = new SmilesParser(DefaultChemObjectBuilder.getInstance());
            IAtomContainer molecule = smilesParser.parseSmiles(entry.getKey());
            XLogPDescriptor xLogPDescriptor = new XLogPDescriptor();
            DescriptorValue xLogP = xLogPDescriptor.calculate(molecule);
            outputDict.put(entry.getKey(), xLogP.getValue());

        }   return outputDict;
    }
}