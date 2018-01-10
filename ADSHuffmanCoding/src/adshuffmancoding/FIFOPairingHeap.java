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
public class FIFOPairingHeap {
    
    FIFOPairingHeap(){
        length = 0;
        fifoQueue = new ArrayList<PairingHeapNode>();
    }
    
    public void insert(PairingHeapNode x){
        fifoQueue.add(x);
        length++;
    }
    
    public PairingHeapNode remove(){
        //TODO: fill stub
        if(length < 1){
            // Error.
            System.out.println("FIFO queue empty");
        }
        length--;
        return fifoQueue.remove(0);
    }
    
    public int getSize(){
        return length;
    }
    
    public boolean isEmpty(){
        return length == 0;
    }
    
    public void view(){
        for(PairingHeapNode el: fifoQueue){
            System.out.println("FIFO ontents- " + el.getData().getFrequency());
        }
    }
    private int length;
    private ArrayList<PairingHeapNode> fifoQueue;
}
