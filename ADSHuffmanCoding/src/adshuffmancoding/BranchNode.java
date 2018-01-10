package adshuffmancoding;

/**
 *
 * @author Kunal Phaltane
 */
public class BranchNode extends Node{
    
    BranchNode(int freq, Node left, Node right){
        super(freq, left, right);
        isLeaf = false;
    }
    
    public void setFreqWeight(int freq_wt){
        this.freq_weight = freq_wt;                
    }
    
    public boolean isLeaf(){
        return isLeaf;
    }
    
    public void setLeftPtr(Node leftPtr){
        this.leftPtr = leftPtr;
    }
    
    public void setRightPtr(Node rightPtr){
        this.rightPtr = rightPtr;
    }
    
    private boolean isLeaf;
    
}