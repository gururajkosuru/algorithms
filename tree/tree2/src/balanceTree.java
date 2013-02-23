import java.util.LinkedList;
import java.util.Queue;


public class balanceTree {
	int index = 0;
	node balance(int min, int max,node[] arr) {
		if(min == max)
			return arr[min];
		int mid = (min + max )/2;
		node curr = arr[mid];
		curr.left = balance(min, mid-1, arr);
		curr.right = balance(mid + 1, max, arr);
		return curr;
	}
	
	void inorder(node root, node[] arr) {
		if(root == null)
			return;
		inorder(root.left, arr);
		arr[index++] = root;
		inorder(root.right, arr);
	}
	
	void BST(node root) {
		Queue<node> q = new LinkedList<node>();
		q.add(root);
		while( !q.isEmpty()) {
			node temp = q.remove();
			System.out.print(" "+temp.key);
			if(temp.left != null)
				q.add(temp.left);
			if(temp.right != null)
				q.add(temp.right);
		}
	}
	public static void main(String args[])  {
		balanceTree b = new balanceTree();
		node n1 = new node(6);
		n1.right = new node(7);
		n1.left = new node(4);
		n1.left.left = new node(2);
		n1.left.right = new node(5);
		n1.left.left.left = new node(1);
		n1.left.left.right = new node(3);
		node[] arr = new node[7];
		b.inorder(n1, arr);
		b.BST(n1);
		node root = b.balance(0, 6, arr);
		b.BST(root);
	}

}
