package adshuffmancoding;

/**
 *
 * @author Kunal Phaltane
 */
public class LeafNode extends Node{
    
    /**/
    LeafNode(int freq_wt, int data, Node left, Node right){
        super(freq_wt, left, right);
        isLeaf = true;
        this.data = data;
    }
    
    public void setData(int data){
        this.data = data;                
    }
    
    public int getData(){
        return data;                
    }
    
    public void setFreqWeight(int freq_wt){
        this.freq_weight = freq_wt;                
    }
    
    public boolean isLeaf(){
        return isLeaf;
    }
    
    private boolean isLeaf;
    private int data; 
}