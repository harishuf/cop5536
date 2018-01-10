package adshuffmancoding;

/**
 *
 * @author Kunal Phaltane
 */
public class PairingHeapNode {

    public PairingHeapNode(Node x, PairingHeapNode child, PairingHeapNode left, PairingHeapNode right) {
        data = x;
        childPtr = child;
        leftPtr = left;
        rightPtr = right;
    }
    
    public PairingHeapNode getChildPtr(){
        return childPtr;
    }
    
    public void setChildPtr(PairingHeapNode x){
        this.childPtr = x;
    }
    
    public PairingHeapNode getLeftPtr(){
        return this.leftPtr;
    }
    
    public void setLeftPtr(PairingHeapNode x){
        this.leftPtr = x;
    }
    
    public PairingHeapNode getRightPtr(){
        return this.rightPtr;
    }
    
    public void setRightPtr(PairingHeapNode x){
        this.rightPtr = x;
    }
    
    public Node getData(){
        return data;
    }
    
    private Node data;
    private PairingHeapNode leftPtr;
    private PairingHeapNode rightPtr;
    private PairingHeapNode childPtr;
}