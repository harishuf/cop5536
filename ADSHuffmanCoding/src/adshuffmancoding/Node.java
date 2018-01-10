package adshuffmancoding;

import java.util.Comparator;

/**
 *
 * @author Kunal Phaltane
 */
public class Node implements Comparable<Node>{
    /*
     * Operator overload for comparing two nodes. 
     * 
     */
    Node(int freq, Node left, Node right){
        freq_weight = freq;
        leftPtr = left;
        rightPtr = right;
    }
    
    public int getFrequency(){
        return freq_weight;
    }
    
    public Node getLeftPtr(){
        return leftPtr;
    }
    
    public Node getRightPtr(){
        return rightPtr;
    }
    
    public void setLeftPtr(Node x){
        leftPtr = x;
    }
    
    public void setRightPtr(Node y){
        rightPtr = y;
    }
    
    protected int freq_weight;
    protected Node leftPtr;
    protected Node rightPtr;

    @Override
    public int compareTo(Node o) {
        return this.getFrequency() - o.getFrequency();
    }

}