
public class MinHeap {
	protected int size;
	protected Comparable[] mH;
	protected int position;

	/* Constructor */
	public MinHeap(int size) {
		this.size = size;
		mH = new Comparable[size + 1];
		position = 0;
	}

	/* get array of points and create minimum heap */
	public void createHeap(Comparable[] arr) {
		if (arr.length > 0) {
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] != null)
					insert(arr[i]);
			}
		}
	}

	/* return the size of the heap */
	public int sizeOfHeap() {
		return position - 1;
	}

	/* insert a new point to the heap */
	public void insert(Comparable x) {
		if (position == 0) {
			mH[1] = new Comparable(x.data, x.key);
			position = 2;
		} else {
			mH[position++] = new Comparable(x.data, x.key);
			bubbleUp();
		}
	}

	/* balance the BST to direction up and maintain the legality */
	public void bubbleUp() {
		int pos = position - 1;
		while (pos > 1 && mH[pos / 2].data.getY() > mH[pos].data.getY() || pos > 1
				&& mH[pos / 2].data.getY() == mH[pos].data.getY() && mH[pos / 2].data.getX() > mH[pos].data.getX()) {
			Comparable y = new Comparable(mH[pos].data, mH[pos].key);
			mH[pos] = new Comparable(mH[pos / 2].data, mH[pos / 2].key);
			mH[pos / 2] = new Comparable(y.data, y.key);
			pos = pos / 2;
		}

	}

	/* remove the minimum value and return it */
	public Comparable extractMin() {
		Comparable min = mH[1];
		mH[1] = mH[position - 1];
		mH[position - 1] = null;
		position--;
		sinkDown(1);
		return min;
	}

	/* return the minimum value */
	public Comparable getMin() {
		return mH[1];
	}

	/* balance the BST to direction down and maintain the legality */
	public void sinkDown(int k) {
		int smallest = k;
		if (2 * k < position && mH[smallest].data.getY() > mH[2 * k].data.getY()
				|| (2 * k < position && mH[smallest].data.getY() == mH[2 * k].data.getY()
						&& mH[smallest].data.getX() > mH[2 * k].data.getX())) {
			smallest = 2 * k;
		}
		if (2 * k + 1 < position && mH[smallest].data.getY() > mH[2 * k + 1].data.getY()
				|| (2 * k + 1 < position && mH[smallest].data.getY() == mH[2 * k + 1].data.getY()
						&& mH[smallest].data.getX() > mH[2 * k + 1].data.getX())) {
			smallest = 2 * k + 1;
		}
		if (smallest != k) {
			swap(k, smallest);
			sinkDown(smallest);
		}

	}

	/* returns an array of the points in the minimum heap */
	public Point[] getHeap() {
		Point[] a = new Point[position];
		for (int i = 1; i < position; i++)
			a[i] = mH[i].data;
		return a;
	}

	/* make a switch between to given values */
	public void swap(int a, int b) {
		Comparable temp = mH[a];
		mH[a] = mH[b];
		mH[b] = temp;
	}

	/* print the nodes in the heap */
	public void display() {
		for (int i = 1; i < mH.length; i++) {
			System.out.print(" " + mH[i].data);
		}
	}
}