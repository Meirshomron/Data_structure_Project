
public class Comparable {

	Point data;
	int key;
	
	
	
	public Comparable(Point r,int k){
		data=r;
		key=k;
		
	}
	
	public int compareTo(Point t){
		if(data.getY()>t.getY()||data.getY()==t.getY()&&data.getX()>t.getX())
			return 1;
		else if(data.getY()<t.getY()||data.getY()==t.getY()&&data.getX()<t.getX())
			return -1;
		else
			return 0;
		
	}
	
}

