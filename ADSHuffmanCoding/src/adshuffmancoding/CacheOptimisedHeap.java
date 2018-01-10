/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adshuffmancoding;

import java.util.ArrayList;

/**
 *
 * @author Kunal Phaltane
 */
public class CacheOptimisedHeap {

    //constructor
    public CacheOptimisedHeap() {
        length = 0;
    }
    
    //insert element
    public void insert(Node x){
        
    }
    
    //remove min element
    public Node extractMin(){
        Node dummy = new LeafNode(1, 2, null, null);
        return dummy;
    }
    
    
    public boolean isEmpty(){
        return length==0;
    }
    
    public void view(){
        for (Node el: heapArray ){
            System.out.println(el.freq_weight);
        }
    }
    
    
    private void swap (int idx1, int idx2){
        Node temp = heapArray.get(idx1);
        heapArray.set(idx1, heapArray.get(idx2));
        heapArray.set(idx2, temp);
    }
    
    private int length;
    private ArrayList<Node> heapArray;

}