package adshuffmancoding;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kunal Phaltane
 */
public class encoder {
    public static void main(String args[]){
        _inputFile = args[0]; 
        long start_time = System.nanoTime();
        encode();
        long end_time = System.nanoTime();
        System.out.println((end_time - start_time)/1000000);
    }
    
    private static void encode(){
        FileWriter w = null;
        try {
            //using binary heap as
            FrequencyTableBuilder ftb = new FrequencyTableBuilder(_inputFile);
            HashMap<Integer, Integer> freqTable;
            Node huffroot = null;
            try {
                freqTable = ftb.build_freq_table();
                BinaryHeap bh = new BinaryHeap();
                /* Insert all keys serially to construct the heap*/
                for(int key : freqTable.keySet()){
                    Node leaf = new LeafNode(freqTable.get(key), key, null, null);
                    bh.insert(leaf);
                }
                
                /* Remove 2 from the front, combine and put back */
                while(!bh.isEmpty()){
                    if(bh.getSize() > 1){    // remove two elements, combine and put back
                        Node temp_left = bh.extractMin();
                        Node temp_right = bh.extractMin();
                        Node branchNode = new BranchNode(
                                temp_left.getFrequency() +
                                        temp_right.getFrequency(),
                                temp_left, temp_right);
                        bh.insert(branchNode);
                    }
                    else{
                        //huffman tree is built at this point.
                        huffroot = bh.extractMin();
                    }
                }
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(encoder.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            /* Generate codes by inorder traversing the huffman tree. Write them
                to the file named "code_table.txt" given by _codeTableFileName
                Also create a codeTable hashmap */
            
            CodeGenerator codeGenerator = new CodeGenerator();
            w = new FileWriter("./" + _codeTableFileName );
            String currentCode = "";
            codeTable = new HashMap<Integer, String>();
            codeGenerator.GenerateCodes(huffroot, currentCode, w, codeTable);
            
        } catch (IOException ex) {
            Logger.getLogger(encoder.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                w.close();
            } catch (IOException ex) {
                Logger.getLogger(encoder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        encodeDelegate();
    }
    
    public static void encodeDelegate(){
        /*Read data fom input file, look up its code in the Hashmap
          Convert that string in binary and put it in the encoded.bin*/
        try (BufferedReader br = new BufferedReader(new FileReader(_inputFile))) {
            
            DataOutputStream os = new DataOutputStream(new FileOutputStream("./" + _encodeFileName));
            String line;
            StringBuilder binaryEncoding = new StringBuilder("");
            while ((line = br.readLine()) != null) {
                // process the line.
                if(!line.isEmpty() && line!=null){
                    int key = Integer.parseInt(line);
                    if(codeTable.containsKey(key)){
                        /*Read the string, convert into binary and write to file*/
                        String code = codeTable.get(key);
                        binaryEncoding.append(code);
                    }
                    else{
                        System.err.println("Huh? No code fond for this data?");
                    }
                }
            }
            //write to binary 
            byte[] byteArray = new byte[(binaryEncoding.length()/8) + 1];
            for (int i =0 ; i < binaryEncoding.length()/8; i++){
                byteArray[i] = (byte) Short.parseShort(binaryEncoding.substring(8*i, 8*(i+1)) , 2);
            }
            // some codes could be remaining in the binaryEncoding
            
            for ( byte b: byteArray){
                os.write(b);
            }
            os.close();
        } 
        catch (IOException ex) {
            Logger.getLogger(FrequencyTableBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    private static String _inputFile;
    private static String _codeTableFileName = "code_table.txt";
    private static HashMap<Integer, String> codeTable;
    private static String _encodeFileName= "encoded.bin";
}