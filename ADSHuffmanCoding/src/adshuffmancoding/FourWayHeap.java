package adshuffmancoding;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Kunal Phaltane
 */
public class FourWayHeap {

    public FourWayHeap(String ipFilePath) {
        heap = new ArrayList();
        // shift the entire heap by 3
        heap.add(null);
        heap.add(null);
        heap.add(null);
        frequencyTable = new HashMap();
        inputFilePath =  ipFilePath;
        Code = "";
    }
    
    
    public int getParentIdx(int n){
        // formula
        if(n == 3 ){
            return 3;
        }
        return (n)/4 + 2;
    }
    
    public int getChildOneIndex(int n){
        return 4*n + 1 - 9 < heap.size() - 1 ? 4*n + 1 - 9 : -1;
    }
    
    public int getChildTwoIndex(int n){
        return 4*n + 2 - 9 < heap.size() - 1 ? 4*n + 2 - 9 : -1;
    }
    
    public int getChildThreeIndex(int n){
        return 4*n + 3 - 9 < heap.size() - 1 ? 4*n + 3 - 9 : -1;
    }
    
    public int getChildFourIndex(int n){
        return 4*n + 4 - 9 < heap.size() - 1 ? 4*n + 4 - 9 : -1;
    }
    
    
    public void buildHeap(){
		try {
		
		Scanner sc = new Scanner(new File(inputFilePath));
		    while (sc.hasNextInt()) {
		        int number = sc.nextInt();
		        frequencyTable.put(number, (frequencyTable.get(number) == null ? 1 :  frequencyTable.get(number)+1 )); 
		        //System.out.println(number);
		    }
		    //System.out.println(frequencyTable);
		    PrintWriter writer = new PrintWriter("C:\\Users\\Kunal Phaltane\\Downloads\\Sample1\\freq.txt", "UTF-8");
		    //writer.println(frequencyTable);
		    Iterator itr = frequencyTable.entrySet().iterator();
		    while (itr.hasNext())
		    {
		      Map.Entry pairs = (Map.Entry)itr.next();
		      //System.out.println(pairs.getKey() + " ==> " + pairs.getValue());
		      //writer.println(pairs.getKey() + " ==> " + pairs.getValue());
		    }
		    for( Map.Entry<Integer, Integer> entry : frequencyTable.entrySet()) {
		    	FourWayHeapNode number = new FourWayHeapNode();
		    	number.setData(entry.getKey());
		    	number.setFrequency(entry.getValue());
		    	insert(number);
		    	
		    }
		     
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} 	
	}
    
    public void insert(FourWayHeapNode number){
		
		heap.add(number);
		Integer numberIndex = heap.size()-1;
		if(heap.size() == 4)
			return;
		FourWayHeapNode parent = heap.get(getParentIdx(numberIndex));		
		while( number.getFrequency() < parent.getFrequency() && getParentIdx(numberIndex) >= 3 ){
			swapNodes(getParentIdx(numberIndex), numberIndex);
			numberIndex = getParentIdx(numberIndex);
			parent = heap.get(getParentIdx(numberIndex));
		}
    }
    
    public Node1 huffmanTreeGenerate(){
		while(heap.size() != 4){
			insert(createNode(extractMin(), extractMin()));	
		}
		return heap.get(3).getNode();
    }
    
    public void generateHuffmanCodes(Node1 root, String c, FileWriter w) throws IOException{
		if(root.getLeftChild() == null && root.getRightChild() == null ){
			w.write(root.getData()+" ==> " +c+"\n");
		}
		else{
			String l = c+"0";
			String r = c+"1";
			generateHuffmanCodes(root.getLeftChild(), l, w);
			generateHuffmanCodes(root.getRightChild(), r, w);
		}
    }
    
    public FourWayHeapNode createNode(FourWayHeapNode N1, FourWayHeapNode N2){
		FourWayHeapNode result = new FourWayHeapNode();
		result.setLeftChild(N1);
		result.setRightChild(N2);
		result.setFrequency(N1.getFrequency() + N2.getFrequency());
		return result;
    }
    
    public FourWayHeapNode extractMin(){
		if(heap.size() < 4){
			return null;
		}
		FourWayHeapNode min = heap.get(3);
		if(heap.size() - 1 == 3){
			//no child at root level
                        heap.remove(3);
			return min;
		}
		heap.set(3, heap.remove(heap.size()-1));
		minHeapify(3);
		//System.out.println(min.getFrequency());
		return min;
    }
    
    
    
    public void minHeapify(Integer numberIndex){
		FourWayHeapNode number = heap.get(numberIndex);
		int minChildIdx = minOfChildren(numberIndex);
                if(minChildIdx == -1){
                    //current node at numberIndex doesn't have any children
                    return;
                }
                swapNodes(minChildIdx, numberIndex);
                minHeapify(minChildIdx);
                
    }

    
    public void swapNodes(Integer node1Idx, Integer node2Idx){
		FourWayHeapNode temp = heap.get(node1Idx);
		heap.set(node1Idx, heap.get(node2Idx));
		heap.set(node2Idx, temp);
    }
    
    /*
    public FourWayHeapNode huffmanTreeGenerate(){
        while(heap.size() != 3){
            //Node N1 = extractMin();
            //Node N2 = extractMin();
            insert(createNode(extractMin(), extractMin()));
            //insert(createInternalNode(N1, N2));
        }
	return heap.get(3);
    }
    */
    
    public void printFourWayHeap(){
        for(FourWayHeapNode el : heap){
            if(el == null){
                System.out.println(el);
            }
            else{
                System.out.println(el.getFrequency());
            }
        }
    }
    
    
    
    private int minOfChildren(int numberIdx){
        int c1 = getChildOneIndex(numberIdx);
        int c2 = getChildTwoIndex(numberIdx);
        int c3 = getChildThreeIndex(numberIdx);
        int c4 = getChildFourIndex(numberIdx);
        int minChildOf12;
        int minChildOf34;
        int minChild;
        
        
        if(c1 != - 1 && c2 != -1){
            minChildOf12 = heap.get(c1).getFrequency() < heap.get(c2).getFrequency() ? c1 :c2;
        }
        else if(c1 == -1 && c2 == -1){
            minChildOf12 = -1;
        }
        else if(c1 == -1){
            minChildOf12 = c2;
        }
        else{
            minChildOf12 = c1;
        }
        
        
        
        
        if(c3 != - 1 && c4 != -1){
              minChildOf34 = heap.get(c3).getFrequency() < heap.get(c4).getFrequency() ? c3 :c4;
        }
        else if(c3 == -1 && c4 == -1){
            minChildOf34 = -1;
        }
        else if(c3 == -1){
            minChildOf34 = c4;
        }
        else{
            minChildOf34 = c3;
        }
        
        
        if (minChildOf12 != -1 && minChildOf34 != -1){
            minChild = heap.get(minChildOf12).getFrequency() < heap.get(minChildOf34).getFrequency() ? minChildOf12 : minChildOf34;
        }
        else if(minChildOf12 == -1 && minChildOf34 == -1 ){
            minChild = -1;
        }
        else if(minChildOf12 == -1){
            minChild = minChildOf34;
        }
        else{
            minChild = minChildOf12;
        }
        return minChild;
    }
    
    private String Code;
    private String inputFilePath;
    private List<FourWayHeapNode> heap;
    private HashMap<Integer, Integer> frequencyTable;
            
}