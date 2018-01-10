package adshuffmancoding;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kunal Phaltane
 */
public class ADSHuffmanCoding {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
  
        FrequencyTableBuilder ftb = new FrequencyTableBuilder(args[0]);
        HashMap<Integer, Integer> freqTable;
        try {
            freqTable = ftb.build_freq_table();
            //System.out.println(freqTable.size());
            long bh_time=0, ch_time=0, p_time=0;
            for(int i=0; i<1; i++){
                long startTime = System.nanoTime();
                timeBinaryHeap(freqTable, args[0]);
                long endTime = System.nanoTime();
                bh_time += (endTime - startTime)/1000000;
                
                startTime = System.nanoTime();
                //timeCacheHeap(freqTable, args[0]);
                endTime = System.nanoTime();
                ch_time += (endTime - startTime)/1000000;
                
                startTime = System.nanoTime();
                //timePairingHeap(freqTable);
                endTime = System.nanoTime();
                p_time += (endTime - startTime)/1000000;                
            }
            
            
            System.out.println("BH time- " + bh_time/1);    // milliseconds.
            //System.out.println("FourWay time- " + ch_time/1);    // milliseconds.
            System.out.println("PH time- " + p_time/1);    // milliseconds.
  
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ADSHuffmanCoding.class.getName()).log(Level.SEVERE, null, ex);
        }    
    
    }
    
    private static void timeBinaryHeap(HashMap<Integer, Integer> freqTable, String input_file){
        BinaryHeap bh = new BinaryHeap();
        for(int key : freqTable.keySet()){
            Node leaf = new LeafNode(freqTable.get(key), key, null, null);
            bh.insert(leaf);
        }
        Node huffRoot=null;
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
            else{                    //huffman tree is built
                huffRoot = bh.extractMin();
            }
        }
        String code = "";
        FileWriter w;
        try {
            w = new FileWriter("C:\\Users\\Kunal Phaltane\\OneDrive - University of Florida\\ADS\\ADSHuffmanCoding\\CodesFromBinaryHeap.txt");
            inorder_traversal(huffRoot, code, w);
            w.close();
        } catch (IOException ex) {
            Logger.getLogger(ADSHuffmanCoding.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                
    }
    
    private static void timeCacheHeap(HashMap<Integer, Integer> freqTable, String input_file){
        FourWayHeap fwh = new FourWayHeap(input_file);
                fwh.buildHeap();
                Node1 n = fwh.huffmanTreeGenerate();
                String c = "";
                FileWriter w;
                try {
                    w = new FileWriter("C:\\Users\\Kunal Phaltane\\OneDrive - University of Florida\\ADS\\ADSHuffmanCoding\\CodesFromFourWayHeap.txt");
                    fwh.generateHuffmanCodes(n, c, w);
                    w.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
    }
    
    private static void timePairingHeap(HashMap<Integer, Integer> freqTable){
        PairingHeap ph = new PairingHeap();
        for(int key : freqTable.keySet()){
            Node leaf = new LeafNode(freqTable.get(key), key, null, null);
            //System.out.println("Leaf insert in PH" + freqTable.get(key) + " "+ key);
            ph.insert(leaf);
        }
        //ph.view();
        Node huffRoot = null;
        while(!ph.isEmpty()){
            if(ph.getSize() > 1){    // remove two elements, combine and put back
                Node temp_left = ph.extractMin().getData();
                Node temp_right = ph.extractMin().getData();
                //System.out.println("After removal l-" + temp_left.getFrequency());
                //System.out.println("After removal r-" + temp_right.getFrequency());
                //ph.view();
                Node branchNode = new BranchNode(
                                    temp_left.getFrequency() + 
                                    temp_right.getFrequency(), 
                                    temp_left, temp_right);
                ph.insert(branchNode);
                
            }
            else{                    //huffman tree is built
                huffRoot = ph.extractMin().getData();
            }
        }
        //inorder_traversal(huffRoot, "");
        //System.out.println ("PH- Huffroot data freq");
        if(huffRoot == null){
             System.out.println("PH huffroot is null. ");
        }
        else{
            System.out.println(huffRoot.getFrequency());
        }
        String c = "";
        FileWriter w;
        try {
            w = new FileWriter("C:\\Users\\Kunal Phaltane\\OneDrive - University of Florida\\ADS\\ADSHuffmanCoding\\CodesFromPairingHeap.txt");
            inorder_traversal(huffRoot, c, w);
            w.close();
        } catch (IOException ex) {
            Logger.getLogger(ADSHuffmanCoding.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private static void inorder_traversal(Node root, String c, FileWriter w) throws IOException{
        
        if(root.getLeftPtr() == null && root.getRightPtr() ==null){
            //print code
            //System.out.println( root.getFrequency()+ "==>" + c);
            w.write(((LeafNode)root).getData() + " " + c + "\n");
        }
        else{
            //System.out.println("Current freq " + root.getFrequency() + " Left freq " + root.getLeftPtr().getFrequency() + " Right freq" + root.getRightPtr().getFrequency());
            String l = c+"0";
            String r = c+"1";
            inorder_traversal(root.getLeftPtr(), l, w);
            inorder_traversal(root.getRightPtr(), r, w);
        }
    }
}

 /*
        Node leaf = new LeafNode(10, 12, null, null);
        Node leaf1 = new LeafNode(11, 13, null, null);
        Node leaf2 = new LeafNode(12, 14, null, null);
        Node leaf3 = new LeafNode(13, 15, null, null);
        Node leaf4 = new LeafNode(14, 16, null, null);
        
        Node branch = new BranchNode(1, leaf, leaf1);
        Node branch1 = new BranchNode(13, branch, leaf2);
        Node branch2 = new BranchNode(30, branch1, leaf3);
        Node branch3 = new BranchNode(12, branch2, leaf4);
        
        bh.insert(leaf);
        bh.insert(leaf1);
        bh.insert(leaf2);
        bh.insert(leaf3);
        bh.insert(leaf4);
        
        bh.insert(branch);
        bh.insert(branch1);
        bh.insert(branch2);
        bh.insert(branch3);
        bh.view();
        */

/*
System.out.println("Should be 3" + huffRoot.getLeftPtr().getLeftPtr().getFrequency());
        System.out.println("Should be 4" + huffRoot.getLeftPtr().getRightPtr().getFrequency());
        System.out.println("Should be 4" + huffRoot.getRightPtr().getLeftPtr().getFrequency());
        System.out.println("Should be 5" + huffRoot.getRightPtr().getRightPtr().getFrequency());
        System.out.println("Should be 1" + huffRoot.getLeftPtr().getLeftPtr().getLeftPtr());
        System.out.println("Should be 2" + huffRoot.getLeftPtr().getLeftPtr().getRightPtr());
//System.out.println(" Check for leaves" + branchNode.getLeftPtr().getFrequency());
//System.out.println(" Check for leaves" + branchNode.getRightPtr().getFrequency());
*/
/*
if(branchNode.getFrequency() == 3 || branchNode.getFrequency() == 5){
                    System.out.println("Check for left of " + branchNode.getFrequency() + " " + branchNode.getLeftPtr().getFrequency());
                    System.out.println("Check for right of " + branchNode.getFrequency() + " " + branchNode.getRightPtr().getFrequency());
                }
*/

/*
System.out.println( "Data in leaf - " + ((LeafNode)leaf).getData() + " Freq- " + leaf.getFrequency() );
*/