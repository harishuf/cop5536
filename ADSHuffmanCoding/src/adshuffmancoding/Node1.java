package adshuffmancoding;

/**
 *
 * @author Kunal Phaltane
 */
public class Node1 {
    public Node1()
	{
		LeftChild = null;
		RightChild = null;
		frequency = 0;
		data = 0;
	}
	public Integer getData() {
		return data;
	}
	
	public void setData(Integer d) {
		data = d;
	}

	public Integer getFrequency(){
		return frequency;
	}
	
	public void setFrequency(Integer f){
		 frequency = f;
	}
	
	public Node1 getLeftChild(){
		return LeftChild;
	}
	
	public Node1 getRightChild(){
		return RightChild;
	}
	
	public void setLeftChild(Node1 l)
	{
		LeftChild = l; 
	}
	public void setRightChild(Node1 r)
	{
		RightChild = r; 
	}
	private Integer data;
	private Node1 LeftChild;
	private Node1 RightChild;
	private Integer frequency;
}