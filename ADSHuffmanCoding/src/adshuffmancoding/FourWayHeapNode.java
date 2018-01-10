package adshuffmancoding;

public class FourWayHeapNode {

    public FourWayHeapNode() {
        node = new Node1();
        
    }
    
    public Node1 getLeftChild(){
        return node.getLeftChild();
    }
    
    public Node1 getRightChild(){
        return node.getRightChild();
    }
    public int getData(){
        return node.getData();
    }
    
    public int getFrequency(){
        return node.getFrequency();
    }
    
    public void setLeftChild(FourWayHeapNode x){
        node.setLeftChild(x.getNode());
    }
    
    public void setRightChild(FourWayHeapNode x){
        node.setRightChild(x.getNode());
    }
    
    public void setData(int x){
         node.setData(x);
    }
    
    public void setFrequency(int x){
        node.setFrequency(x);
    }
    
    public Node1 getNode(){
        return node;
    }
    
    private Node1 node;
}