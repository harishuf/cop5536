package adshuffmancoding;

import java.util.ArrayList;

/**
 *
 * @author Kunal Phaltane
 */
public class BinaryHeap {

    // Constructor
    public BinaryHeap() {
        heapArray = new ArrayList<Node>();
        length = 0;
    }
    
    //Insert
    public void insert(Node x){
        heapArray.add(x);
        length++;
        bubbleUp(length - 1);
        
    }
    
    //Remove min element 
    public Node extractMin(){
        if(heapArray.isEmpty()){
            System.err.println("Can not remove from empty heap");
        }
        Node top = heapArray.get(0);
        Node last = heapArray.get(length - 1);
        heapArray.set(0, last);
        heapArray.remove(length-1);
        length--;
        bubbleDown(0);
        return top;
    }
    
    public boolean isEmpty(){
        return length==0;
    }
    
    public int getSize(){
        return length;
    }
    public void view(){
        for (Node el: heapArray){
            System.out.println(el.freq_weight);
        }
    }
    
    public void buildHeapWilliams(){
        //TODO: Complete stub
    }
    
    public void buildHeapFloyds(){
        //TODO: Complete stub
    }
    
    /*
    public String sort(){
        //TODO: Complete stub
    }
    */
    
    private void bubbleUp(int n){
        int p_idx = parent(n);
        while (n > 0 && heapArray.get(p_idx).getFrequency() > heapArray.get(n).getFrequency()) {
            swap(p_idx, n);
            n = p_idx;
            p_idx = parent(n);
        }
    }
    
    private void bubbleDown(int n){
        int minChildIndex = minChildIndex(n);
        while (minChildIndex != -1 && heapArray.get(minChildIndex).getFrequency() < heapArray.get(n).getFrequency()) {
            swap(minChildIndex, n);
            n = minChildIndex;
            minChildIndex = minChildIndex(n);
        }
        
    }
    
    
    private void swap (int idx1, int idx2){
        Node temp = heapArray.get(idx1);
        heapArray.set(idx1, heapArray.get(idx2));
        heapArray.set(idx2, temp);
    }
    
    //Return index of child having lower value. -1 if no child exists.
    private int minChildIndex(int n) {
        if (left(n) > length - 1) return -1;    //No left n right child exist. 
        if (right(n) > length - 1) return left(n);  //only left exists
        if(heapArray.get(left(n)).getFrequency() <= heapArray.get(right(n)).getFrequency()){
            return left(n);
        }
        else{
            return right(n);
        }
    }

    
    private int parent(int n) { return n == 0 ? -1 : (n - 1) >>> 1; }
    private int left(int n) { return n * 2 + 1; }
    private int right(int n) { return n * 2 + 2; }
    
    private int length;
    private ArrayList<Node> heapArray;
}