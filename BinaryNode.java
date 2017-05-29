
public class BinaryNode {
	protected Point data;
	protected BinaryNode left;
	protected BinaryNode right;
	protected int numOfsmaller;
	protected int sumofY;
	protected int ranges;
	protected int rangef;

	public BinaryNode(Point data){
		this.data = data;
		left = null;
		right = null;
		numOfsmaller=0;
		sumofY=data.getY();
		ranges=0;
		rangef=0;
	}

	
}