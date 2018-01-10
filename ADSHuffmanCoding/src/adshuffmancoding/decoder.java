package adshuffmancoding;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kunal Phaltane
 */
public class decoder {
    public static void main(String[] args) throws IOException{
        _encodedBinFile = args[0];
        _codeTableFile = args[1];
        root = new BranchNode(999999, null, null); //invalid frequency; Not reqd
        generateHuffmanTree();
        //test_huffTreeGen();
        decode();
        
    }
    
    private static void generateHuffmanTree(){
        /*Read the codeTable file and generate huffman tree*/
        try (BufferedReader br = new BufferedReader(new FileReader(_codeTableFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                // process the line.
                if(!line.isEmpty() && line!=null){
                    int numberData = Integer.parseInt(line.split(" ")[0]);
                    String code = line.split(" ")[1];
                    Node traverser =  root;
                    for (int i = 0; i < code.length() - 1; i++){
                        char c = code.charAt(i);        
                        if (c == '0'){
                            //bit is 0
                            if(traverser.getLeftPtr() == null){
                                traverser.setLeftPtr(new BranchNode(999999, null, null));
                            }
                            traverser = traverser.getLeftPtr();
                        }
                        else{
                            //bit is 1
                            if(traverser.getRightPtr() == null){
                                traverser.setRightPtr(new BranchNode(999999, null, null));
                            }
                            traverser = traverser.getRightPtr();
                        }
                    }
                    //Put data in the last node by creating a new leafNode
                    char c = code.charAt(code.length() - 1);
                    if(c == '0'){
                        traverser.setLeftPtr(new LeafNode(9999999, numberData, null, null));
                    }
                    else{
                        traverser.setRightPtr(new LeafNode(9999999, numberData, null, null));
                    }
                }
            }
        } 
        catch (IOException ex) {
            Logger.getLogger(FrequencyTableBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void decode() throws IOException{
        //read a bit at a time and traverse the tree until you fall off.
        File file = new File(_encodedBinFile);
        String longStringAsFile;
        
        //DataInputStream dis;
        FileWriter w;
        try {
            //dis = new DataInputStream(new FileInputStream(file));
            longStringAsFile = getLongStringAsFile();
            //dis.close();
            w = new FileWriter("decoded.txt");
            Node traverser = root;
            for(int i=0; i < longStringAsFile.length(); i++){
                if(longStringAsFile.charAt(i) == '0'){
                    //go to left
                    if (traverser.getLeftPtr() == null){
                        //fallen off the tree. Print to file
                        if (i == longStringAsFile.length() - 1 ){
                            w.write(((LeafNode)traverser).getData());
                        }
                        else{
                            w.write(((LeafNode)traverser).getData() + "\n");
                            traverser = root.getLeftPtr();
                        }
                    }
                    else{
                        traverser = traverser.getLeftPtr();
                    }
                }
                else{
                    //go to right of tree
                    if (traverser.getRightPtr() == null){
                        //fallen off the tree. PRint to file
                        if (i == longStringAsFile.length() - 1){
                            w.write(((LeafNode)traverser).getData() );
                        }
                        else{
                            w.write(((LeafNode)traverser).getData() + "\n");
                            traverser = root.getRightPtr();
                        }

                    }
                    else{
                        traverser = traverser.getRightPtr();
                    }
                }
                
            }
            //dis.close();
            w.close();
        } catch (IOException ex) {
            Logger.getLogger(decoder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
    
    private static void test_huffTreeGen(){
        CodeGenerator cg = new CodeGenerator();
        String c = "";
        FileWriter fw;
        try {
            fw = new FileWriter("test_code_table.txt");
            HashMap<Integer, String> waste = new HashMap<Integer, String>();
            cg.GenerateCodes(root, c, fw, waste);
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(decoder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private static String getLongStringAsFile() throws IOException{
        StringBuilder oneLargeString = new StringBuilder("");
        byte[] byteArray;
        byteArray = Files.readAllBytes(new File(_encodedBinFile).toPath());
        for(byte b: byteArray){
            oneLargeString.append( Integer.toBinaryString((b & 255 | 256)).substring(1));
        }
        return oneLargeString.toString();
    }
    
    private static boolean isBitSet(byte b, int bit){
        return ((b & (1 << bit)) != 0);
    }
    
    private static String _encodedBinFile;
    private static String _codeTableFile;
    private static Node root;
}