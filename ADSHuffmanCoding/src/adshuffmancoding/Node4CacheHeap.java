package adshuffmancoding;

/**
 *
 * @author Kunal Phaltane
 */
public class Node4CacheHeap {

    public Node4CacheHeap() {
        frequency = 0;
        data = 0;
        right = null;
        left = null;
    }
    
    public int getData(){
        return data;
    }
    public int getFrequency(){
        return frequency;
    }
    public Node4CacheHeap getLeftChild(){
        return left;
    }
    public Node4CacheHeap getRightChild(){
        return right;
    }
    
    public void setData(int x){
        this.data = x;
    }
    public void setFrequency(int x){
        this.frequency = x;
    }
    public void setLeftChild(Node4CacheHeap x){
        this.left = x;
    }
    public void setRightChild(Node4CacheHeap x){
        this.right = x;
    }
    
    private int frequency;
    private int data;
    Node4CacheHeap left;
    Node4CacheHeap right;
}