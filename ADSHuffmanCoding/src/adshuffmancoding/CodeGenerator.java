package adshuffmancoding;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author Kunal Phaltane
 */
public class CodeGenerator {

    public CodeGenerator() {
    }
    
    public void GenerateCodes(Node root, String currentCode, FileWriter w, HashMap<Integer, String> codeTable) throws IOException{
        if(root.getLeftPtr() == null && root.getRightPtr() == null){
            int data = (((LeafNode)root).getData());
            codeTable.put( data , currentCode);
            w.write( data + " " + currentCode + "\n");
        }
        else{
            String l = currentCode + "0";
            String r = currentCode + "1";
            GenerateCodes(root.getLeftPtr(), l, w, codeTable);
            GenerateCodes(root.getRightPtr(), r, w, codeTable);
        }
    }
}