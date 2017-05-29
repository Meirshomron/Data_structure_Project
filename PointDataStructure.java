
public class PointDataStructure implements PDT {
	private Point median;
	private BST root;
	private MinHeap2 minH;
	private MaxHeap2 maxH;

	//////////////// DON'T DELETE THIS CONSTRUCTOR ////////////////
	public PointDataStructure(Point[] points, Point initialYMedianPoint) {
		median = initialYMedianPoint;
		minH = new MinHeap2(points.length);
		maxH = new MaxHeap2(points.length);
		Point[] minbig = new Point[points.length / 2 + 2];
		Point[] maxsmall = new Point[points.length / 2 + 1];
		int maximum = points.length;
		int a = 0, b = 0;
		Point[] copy = new Point[maximum];
		for (int i = 0; i < points.length; i++) {

			if (points[i].getX() > maximum)
				maximum = points[i].getX();
			if (initialYMedianPoint.getY() > points[i].getY() || (initialYMedianPoint.getY() == points[i].getY()
					&& initialYMedianPoint.getX() > points[i].getX())) {
				maxsmall[a] = points[i];
				a++;
			}
			if (initialYMedianPoint.getY() < points[i].getY() || (initialYMedianPoint.getY() == points[i].getY()
					&& initialYMedianPoint.getX() <= points[i].getX())) {
				minbig[b] = points[i];
				b++;

			}
		}
		if (maximum == points.length)
			copy = countingSort(points, maximum);
		else
			copy = points;
		root = new BST(copy, points.length);
		minH.createHeap(minbig);
		maxH.createHeap(maxsmall);

	}

	@Override
	/* function that add a new point to our structures */
	public void addPoint(Point point) {
		root.insert(point);
		int minsize = minH.sizeOfHeap();
		int maxsize = maxH.sizeOfHeap();
		if (minsize == maxsize) {
			if (point.getY() > median.getY() || point.getY() == median.getY() && point.getX() > median.getX()) {
				minH.insert(point);
				median = minH.getMin();
			} else

			{
				maxH.insert(point);
				minH.insert(maxH.extractMax());
				median = minH.getMin();
			}
		} else {
			if (point.getY() < median.getY() || point.getY() == median.getY() && point.getX() < median.getX()) {
				maxH.insert(point);
			} else {
				minH.insert(point);
				maxH.insert(minH.extractMin());
				median = minH.getMin();
			}
		}
	}

	@Override
	/* function that return an array with points in the limits */
	public Point[] getPointsInRange(int XLeft, int XRight) {
		if (root == null)
			return null;
		else
			return root.pointArrRange(XLeft, XRight);
	}

	@Override
	/* function that return the number of points in limits */
	public int numOfPointsInRange(int XLeft, int XRight) {
		if (root != null)
			return root.amountinRange(XLeft, XRight);
		else
			return 0;
	}

	@Override
	/* function that return the average of point between limits */
	public double averageHeightInRange(int XLeft, int XRight) {
		if (root != null)
			return root.sumAverage(XLeft, XRight);
		else
			return 0;
	}

	@Override
	/* function that remove the median point and update the median */
	public void removeMedianPoint() {
		if (minH != null && root != null) {
			root.delete(median);
			minH.extractMin();
			if (minH.sizeOfHeap() + 1 == maxH.sizeOfHeap()) {
				minH.insert(maxH.extractMax());
			}
			median = minH.getMin();

		}
	}

	@Override
	/* function that return an array with k median points */
	public Point[] getMedianPoints(int k) {
		if (k >= 0 && k < (minH.size + maxH.size))
			return this.Kmedian(k);

		return null;
	}

	@Override
	/* function that return all the points in the structure */
	public Point[] getAllPoints() {
		Point[] a = new Point[minH.sizeOfHeap() + maxH.sizeOfHeap()];
		if (a.length > 0) {
			Point[] m1 = minH.getHeap();
			Point[] m2 = maxH.getHeap();
			int i;
			for (i = 0; i < minH.sizeOfHeap(); i++)
				a[i] = m1[i + 1];
			for (int j = 0; j < maxH.sizeOfHeap(); j++)
				a[minH.sizeOfHeap() + j] = m2[j + 1];
			return a;
		}
		return null;
	}

	// TODO: add members, methods, etc.

	/* implementation of counting sort. */
	public static Point[] countingSort(Point[] a, int k) {
		int c[] = new int[k];
		for (int i = 0; i < a.length; i++)
			c[a[i].getX()]++;
		for (int i = 1; i < k; i++)
			c[i] += c[i - 1];
		Point b[] = new Point[a.length];
		for (int i = a.length - 1; i >= 0; i--) {
			c[a[i].getX()]--;
			b[c[a[i].getX()]] = a[i];
		}
		return b;
	}

	/* function to return the median point */
	public Point getMedian() {
		return median;
	}

	/* function that return an array with k median points */
	public Point[] Kmedian(int k) {
		if(k>minH.size+maxH.size)
			k=minH.size+maxH.size;
		
		Point[] ans = new Point[k];
		Point[] min = new Point[(k + 1) / 2];
		Point[] max = new Point[k / 2];
		MinHeap a = new MinHeap(5 * k);// still not sure how big but atm it
										// works.
		MaxHeap b = new MaxHeap(5 * k);

		a.createHeap(new Comparable[] { minH.getMinObject() });
		b.createHeap(new Comparable[] { maxH.getMaxObject() });

		min = KmedianMin((k + 1) / 2, min, 0, a);
		max = KmedianMax(k / 2, max, 0, b);

		int i;
		for (i = 0; i < k / 2; i++) {
			if (max[i] != null)
				ans[i] = max[i];
		}
		for (i = 0; i < (k + 1) / 2; i++) {
			if (min[i] != null)
				ans[k / 2 + i] = min[i];
		}

		return ans;

	}

	/* function that return the median points from the minimum heap */
	private Point[] KmedianMin(int k, Point[] min, int index, MinHeap a) {
		if (k == 0 || a.sizeOfHeap() == 0 || index == min.length)
			return min;
		int t = (a.getMin()).key;

		min[index] = (a.extractMin()).data;
		if (minH.left(t) != null)
			a.insert(minH.left(t));
		if (minH.right(t) != null)
			a.insert(minH.right(t));

		return KmedianMin(k - 1, min, index + 1, a);

	}

	/* function that return the median points from the maximum heap */
	private Point[] KmedianMax(int k, Point[] max, int index, MaxHeap b) {
		if (k == 0 || b == null || b.sizeOfHeap() == 0 || index == max.length)
			return max;
		int t = (b.getMax()).key;
		max[index] = (b.extractMax()).data;
		if (maxH.left(t) != null) {
			b.insert(maxH.left(t));

		}
		if (maxH.right(t) != null) {
			b.insert(maxH.right(t));

		}
		return KmedianMax(k - 1, max, index + 1, b);

	}

}
