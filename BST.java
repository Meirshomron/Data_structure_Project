
/*Binary search tree class*/
public class BST {

	protected BinaryNode root;

	/* Constructors */
	public BST() {
		root = null;
	}

	public BST(Point[] arr, int n) {
		root = buildBST(arr, 0, n - 1);
	}

	/* the order in the BST base on median points in each half of the array */
	private BinaryNode buildBST(Point[] arr, int start, int end) {
		if (start > end) {
			return null;

		}
		int mid = start + (end - start) / 2;
		BinaryNode root = new BinaryNode(arr[mid]);
		root.ranges = arr[start].getX();
		root.rangef = arr[end].getX();
		BinaryNode left = buildBST(arr, start, mid - 1);
		BinaryNode right = buildBST(arr, mid + 1, end);
		root.numOfsmaller = mid - start + 1;
		if (left != null)
			root.sumofY += left.sumofY;
		if (right != null)
			root.sumofY += right.sumofY;
		root.left = left;
		root.right = right;

		return root;
	}

	/* return root */
	public BinaryNode getRoot() {
		return root;
	}

	/* inserting new node */
	public void insert(Point data) {
		root = insert(root, data);
	}

	/* insert a new point to the BST in order to the legality */
	private BinaryNode insert(BinaryNode r, Point data) {
		if (r == null) {
			r = new BinaryNode(data);
			r.numOfsmaller++;
			r.ranges = r.data.getY();
			r.rangef = r.data.getY();
		}
		if (r.data.getX() > data.getX()) {
			r.numOfsmaller++;
			r.sumofY += data.getY();
			if (data.getX() < r.ranges)
				r.ranges = data.getX();
			r.left = insert(r.left, data);
		}
		if (r.data.getX() < data.getX()) {
			r.sumofY += data.getY();
			if (data.getX() > r.rangef)
				r.rangef = data.getX();
			r.right = insert(r.right, data);
		}
		return r;
	}

	/* function to return minimum value of a node */
	private BinaryNode min(BinaryNode t) {
		// To find the minimum, go left as far as possible.
		if (t.left == null)
			return t;
		else
			return min(t.left);
	}

	/* deleting node from tree */
	public void delete(Point k) {
		root = delete(root, k);
	}

	private BinaryNode delete(BinaryNode t, Point k) {
		if (t == null) { // k not in tree; do nothing.
		} else if (k.getX() < t.data.getX()) {
			t.left = delete(t.left, k);
			t.numOfsmaller--;
		} else if (k.getX() > t.data.getX()) {
			t.right = delete(t.right, k);
		} else { // Found it; now delete it.
			if (t.right == null) {
				// t has at most one child, on the left.
				t = t.left;
			} else {
				// t has a right child. Replace t’s value
				// with its successor value.
				BinaryNode successor = min(t.right);
				t = successor;
				// Delete that successor.
				t.right = delete(t.right, successor.data);
			}

		}
		if (t != null) {
			t.sumofY = t.data.getY();
			if (t.left != null) {
				t.sumofY += t.left.sumofY;
				t.ranges = t.left.ranges;

			} else
				t.ranges = t.data.getX();
			if (t.right != null) {
				t.sumofY += t.right.sumofY;
				t.rangef = t.right.rangef;
			} else
				t.rangef = t.data.getX();

		}
		return t;

	}

	/* return if a node is in the tree,otherwise false */
	public boolean Search(Point data) {
		return search(root, data);
	}

	/*
	 * function that return true if node r is exist in the BST otherwise false
	 */
	private boolean search(BinaryNode r, Point data) {
		boolean found = false;
		while ((r != null) && !found) {
			if (data.getX() < r.data.getX())
				r = r.left;
			else if (data.getX() > r.data.getX())
				r = r.right;
			else
				found = true;
		}
		return found;
	}

	/* function to return an array of points in a range in the tree */
	public Point[] pointArrRange(int a, int b) {
		Point[] ans = new Point[amountinRange(a, b)];

		return pointArrRange(root, a, b, ans, 0);
	}

	public Point[] pointArrRange(BinaryNode r, int a, int b, Point[] ans, int i) {
		if (r == null)
			return ans;

		// Special Optional case for improving efficiency
		if (r.data.getX() == a && r.data.getX() == b) {
			ans[i] = r.data;
			return ans;
		}
		// If current node is in range, then include it in array and
		// recur for left and right children of it
		if (r.data.getX() <= b && r.data.getX() >= a) {
			while (ans[i] != null)
				i++;
			ans[i] = r.data;
			if (r.left != null)
				pointArrRange(r.left, a, r.data.getX(), ans, i++);
			if (r.right != null)
				pointArrRange(r.right, r.data.getX(), b, ans, i++);
		}
		// If current node is smaller than low, then recur for right
		// child
		else if (r.data.getX() < a)
			pointArrRange(r.right, a, b, ans, i);
		// Else recur for left child
		else
			return pointArrRange(r.left, a, b, ans, i);

		return ans;
	}

	/* function to return amount of nodes in a range in the tree */
	public int amountinRange(int a, int b) {

		return amountinRange(root, a, b);
	}

	private int amountinRange(BinaryNode r, int a, int b) {
		boolean found = false;
		int aSum = 0;
		int bSum = 0;

		while ((r != null) && !found) {
			if (r.data.getX() > a)
				r = r.left;
			else if (a > r.data.getX()) {
				aSum = r.numOfsmaller + aSum;
				r = r.right;
			} else {
				found = true;
				aSum = r.numOfsmaller + aSum;
			}
		}
		r = root;
		found = false;
		while ((r != null) && !found) {
			if (r.data.getX() > b)
				r = r.left;
			else if (b > r.data.getX()) {
				bSum = r.numOfsmaller + bSum;
				r = r.right;
			} else {
				found = true;
				bSum = r.numOfsmaller + bSum;
			}
		}
		if (Search(new Point(a, 0)))
			return bSum - aSum + 1;
		return bSum - aSum;
	}

	/*
	 * function to return average of all the y of of nodes in a range in the
	 * tree
	 */
	public double sumAverage(int a, int b) {
		int sumOfYinRange = 0;
		sumOfYinRange = sumYin(root, a, b, 0);
		int amount = amountinRange(a, b);
		System.out.println(amount + " amount");
		System.out.println(sumOfYinRange + " sumOfYinRange");

		return (double) sumOfYinRange / amount;
	}

	/* function to return sum of all the y of of nodes in a range in the tree */
	private int sumYin(BinaryNode r, int a, int b, int sumOfYinRange) {
		if (r == null)
			return sumOfYinRange;
		/*checks if the range of this node include the range we are looking for.if so sum it and return it.*/
		if (r.ranges >= a && b >= r.rangef) {
			sumOfYinRange += r.sumofY;
			return sumOfYinRange;
		}
		/*if we are here then we didn'tgo into the first 'if' and here we see if the node we are in is in the range we want if so then add his y only to the sum*/
		if (r.data.getX() >= a && r.data.getX() <= b)
			sumOfYinRange += r.data.getY();
		/*if we go left we might find nods in the range*/
		if(r.data.getX()>a)
		sumOfYinRange = sumYin(r.left, a, b, sumOfYinRange);
		/*if we go right we might find nods in the range*/
		if(r.data.getX()<b)
		sumOfYinRange = sumYin(r.right, a, b, sumOfYinRange);

		return sumOfYinRange;
	}

}
