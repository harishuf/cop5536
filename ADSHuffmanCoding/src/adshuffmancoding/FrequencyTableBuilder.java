package adshuffmancoding;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kunal Phaltane
 */
public class FrequencyTableBuilder {

    public FrequencyTableBuilder(String _inputFilePath) {
        _inputFilePath = "C:/Users/harish/Desktop/Intern 17/sample_input_small.txt";
    }
    
    public HashMap build_freq_table() throws FileNotFoundException{
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(_inputFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // process the line.
                if(!line.isEmpty() && line!=null){
                    int key = Integer.parseInt(line);
                    if(freqMap.containsKey(key)){
                        freqMap.put(key, freqMap.get(key) + 1);
                    }
                    else{
                        freqMap.put(key, 1);
                    }
                }
            }
        } 
        catch (IOException ex) {
            Logger.getLogger(FrequencyTableBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return freqMap;
    }
    
    private String _inputFilePath;
}