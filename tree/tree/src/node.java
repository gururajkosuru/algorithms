import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class node {
	int key;
	node left = null;
	node right = null;
	node parent = null;
	node(int key){
		this.key = key;
	}

	node predcessor(node n){
		if(n.left!=null)
			return maximum(n.left);
		node y = n.parent;
		while(y!=null && y.left == n){
			n = y;
			y = y.left;
		}
		return y;
	}

	node maximum(node n){
		if(n.right==null)
			return n;
		return maximum(n.right);
	}

	void insert(node n,node in,node parent){
		if(n!=null){
			insert(n.key<in.key?n.right:n.left,in,n);
		}
		else{
			in.parent = parent;
			if(parent.key < in.key)
				parent.right = in;
			else
				parent.left = in;
		}
	}

	void inorderRec(node n){
		if(n==null)
			return;
		inorder(n.left);
		System.out.print(" "  + n.key);
		inorder(n.right);
	}

	void preorderRec(node n){
		if(n==null)
			return;
		System.out.print(" "  + n.key);
		preorderRec(n.left);
		preorderRec(n.right);
	}

	void postorderRec(node n){
		if(n==null)
			return;
		postorderRec(n.left);
		postorderRec(n.right);
		System.out.print(" "  + n.key);
	}
	void inorder(node n1){
		Stack<node> s = new Stack<node>();
		//s.push(n1);
		node current = n1;
		while(true){
			if(current != null){
				s.push(current);
				current = current.left;
			}
			else{
				if(!s.empty()){
					current = s.pop();
					System.out.print(" "  + current.key);
					current = current.right;
				}
				else{
					break;
				}
			}
		}
	}

	void preorder(node n1) {
		Stack<node> s = new Stack<node>();
		node current = n1;
		s.add(current);
		while(!s.empty()) {

			current  = s.pop();

			if(current == null)
				continue;
			System.out.print(current.key+" ");
			s.push(current.right);
			s.push(current.left);


		}
	}



	node copyTreeFromPreOrder(ArrayList<node> pre){
		Stack<node> stack = new Stack<node>();
		node root = pre.get(0);
		int i=0;
		while(i<pre.size()){
			if(stack.size()==0 || stack.peek().key >pre.get(i).key){
				if(stack.size()!=0)
					stack.peek().left = pre.get(i);
				stack.push(pre.get(i));
			}
			if(stack.peek().key< pre.get(i).key){
				node temp = stack.peek();
				while(stack.size()>0&&stack.peek().key<pre.get(i).key){
					temp = stack.pop();
				}
				temp.right = pre.get(i);
				stack.push(pre.get(i));
			}
			i++;	
		}
		return root;

	}
	node copyTreeFromPostOrder(ArrayList<node> pre){
		return reccopyTreeFromPostOrder(pre,0,pre.size()-1);

	}


	private node reccopyTreeFromPostOrder(ArrayList<node> pre, int i, int j) {
		if( i > j|| i < 0|| j > pre.size()-1 )
			return null;
		if(i==j)
			return pre.get(i);
		node root = pre.get(j);
		int t;
		for( t = i; t < j; t++)
			if( pre.get(t).key > root.key)
				break;

		root.left = reccopyTreeFromPostOrder(pre,i,t-1);
		root.right= reccopyTreeFromPostOrder(pre,t,j-1);

		return root;

	}


	ArrayList<node> preorderRec(node n,ArrayList<node> pre){
		if(n==null)
			return pre;
		System.out.print(" "  + n.key);
		pre.add(new node(n.key));
		preorderRec(n.left,pre);
		preorderRec(n.right,pre);
		return pre;
	}

	ArrayList<node> postorderRec(node n,ArrayList<node> pre){
		if(n==null)
			return pre;

		postorderRec(n.left,pre);
		postorderRec(n.right,pre);
		System.out.print(" "  + n.key);
		pre.add(new node(n.key));
		return pre;
	}
	int sizeOfTree(node root){
		if(root==null)
			return 0;
		if(isLeaf(root))
			return 1;
		return sizeOfTree(root.left)+sizeOfTree(root.right)+1;
	}

	private boolean isLeaf(node root) {
		if(root.left==null&&root.right==null)
			return true;
		return false;
	}

	boolean isCopy(node root1,node root2){
		if(root1==null&&root2==null)
			return true;
		if(root1==null||root2==null)
			return false;
		if(root1.key!=root2.key)
			return false;
		return isCopy(root1.left,root2.left)&isCopy(root1.right,root2.right);
	}

	int getHeight(node root){
		if(root==null)
			return 0;
		if(isLeaf(root))
			return 1;
		return max(getHeight(root.left),getHeight(root.left))+1;
	}

	int max(int a, int b){
		return a > b? a:b;
	}

	void mirror(node root){
		if(root==null)
			return;
		node left = root.right;
		node right = root.left;
		root.left = left;
		root.right = right;
		mirror(left);
		mirror(right);
	}

	void BFS(node n){
		Queue<node> q = new LinkedList<node>();
		q.add(n);
		while(!q.isEmpty()){
			node current = q.remove();
			System.out.print( current.key+ " ");
			if(current.left!=null)
				q.add(current.left);
			if(current.right!=null)
				q.add(current.right);
		}

	}

	void DFS(node n){

	}

	int leastCommonAncestor(node n1, node n2, node root){
		System.out.println();
		System.out.println("LCA of "+n1.key+" and "+ n2.key);
		while(true){
			if (n1.key < root.key && n2.key < root.key )
				root = root.left;
			else
				if (n1.key > root.key && n2.key > root.key )
					root = root.right;
				else
					break;

		}
		return root.key;

	}
	
	node balance(int min, int max,node[] arr) {
		int mid = (min + max )/2;
		node curr = arr[mid];
		curr.left = balance(min, mid-1, arr);
		curr.right = balance(mid + 1, max, arr);
		return curr;
	}
	public static void main(String farts[]){
		node n1 = new node(5);

		node n2 = new node(3);
		n1.left = n2;
		n2.parent = n1;
		node n3 = new node(4);
		n2.right = n3;
		n3.parent = n2;
		node n4 = new node(2);
		n2.left = n4;
		n4.parent = n2;
		node n5 = new node(1);
		n4.left = n5;
		n5.parent = n4;
		node n6 = new node(7);
		n1.right = n6;
		n6.parent = n1;
		node n7 = new node(6);
		n6.left = n7;
		n7.parent = n6;
		node n8 = new node(8);
		n6.right = n8;
		n8.parent = n6;
		node n9 = new node(12);
		n8.right = n9;
		n9.parent = n8;

		//n1.inorder(n1);
		node out = n1.predcessor(n9);
		if(out!=null)
			System.out.println(" "+out.key);
		else
			System.out.println(n1.key+"has no predcessor");
		//Tree Traversal
		n1.insert(n1,new node(-2),null);
		n1.inorder(n1);
		System.out.println();
		System.out.println("Inorder above and below:");
		n1.inorderRec(n1);
		System.out.println();
		System.out.println("Pre Order below:");
		n1.preorderRec(n1);

		System.out.println();
		System.out.println("Pre Order-non rec below:");
		n1.preorder(n1);
		System.out.println();
		System.out.println("Post Order below: ");
		n1.postorderRec(n1);

		//Tree copying from preOrder traversal
		ArrayList<node> pre =  new ArrayList<node>();
		node copied = n1.copyTreeFromPreOrder(n1.preorderRec(n1, pre));
		//Tree comparison using inorder
		System.out.println();
		System.out.println(n1.sizeOfTree(n1));
		System.out.println(n1.isCopy(n2, copied));
		//get Tree height
		System.out.println(n1.getHeight(n1));

		//Mirroring tree
		/*n1.BFS(n1);
		n1.mirror(n1);
		n1.BFS(n1);
		System.out.println("mirrored");
		 */
		//Tree copying from postOrder traversal
		pre =  new ArrayList<node>();
		node copiedPost = n1.copyTreeFromPostOrder(n1.postorderRec(n1, pre));
		System.out.println(" Post order copied: ");
		copiedPost.BFS(copiedPost);
		
		
		System.out.println(n1.leastCommonAncestor(n7, n9, n1));
	}
}
