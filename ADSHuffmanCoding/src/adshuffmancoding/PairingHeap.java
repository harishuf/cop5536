package adshuffmancoding;

/**
 *
 * @author Kunal Phaltane
 */
public class PairingHeap {

    public PairingHeap() {
        root = null;
        length=0;
    }
    
    public void insert(Node x){
        if (root == null){
            //insert first element with no child, no left(parent), no right
            root = new PairingHeapNode(x, null, null, null);
        }
        else{
            if(root.getData().getFrequency() > x.getFrequency() ){
                // make x as the new root of pairing heap. 
                // with current root as it;s child and no left (parent) 
                // and right ptr
                PairingHeapNode temp = new PairingHeapNode(x, root, null, null);
                //set temp as root's left ptr (parent)
                root.setLeftPtr(temp);
                root = temp;
            }
            else{
                // add x to the left of current root. Update left(parent)
                // link of x.
                if(root.getChildPtr() == null){
                    //no child ptr to root. So just add it as child to current root
                    // data, child, left, right
                    PairingHeapNode temp = new PairingHeapNode(x, null, root, root.getChildPtr());
                    root.setChildPtr(temp);
                }
                else{
                    PairingHeapNode currentChild = root.getChildPtr();
                    PairingHeapNode temp = new PairingHeapNode(x, null, root, root.getChildPtr());
                    currentChild.setLeftPtr(temp);
                    root.setChildPtr(temp);
                }
            }
        }
        length++;
    }
    
    public void view(){
        if(root == null){
            System.out.println("Root is null");
        }
        else{
            System.out.println("Root freq"+root.getData().getFrequency());
            PairingHeapNode temp = root.getChildPtr();;
            while(temp != null){
                System.out.println("Child freq"+temp.getData().getFrequency());
                temp = temp.getRightPtr();
            }
            temp = root.getChildPtr().getRightPtr().getRightPtr().getChildPtr();
            while(temp != null){
                System.out.println("Child freq"+temp.getData().getFrequency());
                temp = temp.getRightPtr();
            }
            
        }
        
    }
    
    public PairingHeapNode extractMin(){
        //extract min and meld all the subtrees using multipass
        PairingHeapNode minNode = root;
        meldChildren(minNode.getChildPtr());
        minNode.setChildPtr(null);       // delete any pointers before returning
        length--;
        return minNode;
    }
    
    public int getSize(){
        return length;
    }
    
    public boolean isEmpty(){
        return length==0;
    }
    
    private void meldChildren(PairingHeapNode firstLeftChild){
        //meld using multipass scheme.
        if(firstLeftChild == null){
            //no children to this root. Do nothing
            //System.out.println("First child is null");
            root = null;
        }
        else{
            //System.out.println("First child is NOT null");
            
            //follow the linked list and insert all children in fifo
            FIFOPairingHeap fifoq = new FIFOPairingHeap();
            //sever the right and left nodes and insert in queue
            PairingHeapNode traverser = firstLeftChild;
            while(traverser != null){
                PairingHeapNode temp = traverser.getRightPtr();
                traverser.setRightPtr(null);
                traverser.setLeftPtr(null);
                fifoq.insert(traverser);
                //System.out.println("FIFO Q INSERT"+traverser.getData().getFrequency());  
                traverser = temp;
            }
            
            //all children are in queue 
            //fifoq.view();
            
            while(!fifoq.isEmpty()){
                if(fifoq.getSize() > 1){
                    PairingHeapNode temp1 = fifoq.remove();
                    PairingHeapNode temp2 = fifoq.remove();
                    //System.out.println("In fifo q "+temp1.getData().getFrequency());
                    //System.out.println("In fifo q "+temp2.getData().getFrequency());
                    PairingHeapNode temp = meld(temp1, temp2);
                    fifoq.insert(temp);
                }
                else{
                    //just one tree in fifo. Which is the new pairing heap
                    root = fifoq.remove();
                }
            }
        }
    }
    
    private PairingHeapNode meld(PairingHeapNode x, PairingHeapNode y){
        if(x.getData().getFrequency() < y.getData().getFrequency()){
            //x becomes the root and y becomes its left child.
            if(x.getChildPtr() == null){
                x.setChildPtr(y);
                y.setLeftPtr(x);
                return x;
            }
            else{
                PairingHeapNode temp = x.getChildPtr();
                x.setChildPtr(y);
                y.setRightPtr(temp);
                temp.setLeftPtr(y);
                y.setLeftPtr(x);
                return x;
            }
            
        }
        else{
            // y becomes root and x becomes its first left child
            if(y.getChildPtr() == null){
                y.setChildPtr(x);
                x.setLeftPtr(y);
                return y;
            }
            else{
                
                PairingHeapNode temp = y.getChildPtr();
                y.setChildPtr(x);
                x.setRightPtr(temp);
                temp.setLeftPtr(x);
                x.setLeftPtr(y);
                return y;
            }
        }
    }
    
    private int length;
    private PairingHeapNode root;
}