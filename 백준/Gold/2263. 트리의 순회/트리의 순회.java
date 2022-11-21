import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	static Node tree;
	static int inOrder[];
	static int postOrder[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int V = Integer.valueOf(br.readLine());

		inOrder = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		postOrder = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();

		tree = recurr(0, V - 1, 0, V - 1);
//		System.out.println(tree.toString());
		preOrder(tree);

		bw.newLine();
		bw.flush();
	}

	public static Node recurr(int inOrderLeft, int inOrderRight, int postOrderLeft, int postOrderRight) {
//		System.out.println(inOrderLeft + " " + inOrderRight + " " + postOrderLeft + " " + postOrderRight);

		if (inOrderLeft < 0 || inOrderRight < 0 || postOrderLeft < 0 || postOrderRight < 0 || inOrderLeft > inOrderRight
				|| postOrderLeft > postOrderRight) {
//			System.out.println("pass");
			return null;
		}
		Node curr = new Node(postOrder[postOrderRight]);

		int mid = 0;

		for (int i = inOrderLeft; i <= inOrderRight; i++) {
			if (inOrder[i] == curr.vertex) {
				mid = i;
				break;
			}
		}

//		System.out.println("mid : " + mid);
//		System.out.println("left length : " + (mid - inOrderLeft));
//		System.out.println("right length : " + (inOrderRight - mid));
		curr.left = recurr(inOrderLeft, mid - 1, postOrderLeft, postOrderLeft + mid - inOrderLeft - 1);
		curr.right = recurr(mid + 1, inOrderRight, postOrderLeft + mid - inOrderLeft, postOrderRight - 1);

		return curr;
	}

	public static void preOrder(Node curr) {
		if (curr == null) {
			return;
		}
		System.out.print(curr.vertex + " ");
		preOrder(curr.left);
		preOrder(curr.right);
	}

}

class Node {
	int vertex;
	Node left;
	Node right;

	public Node() {
	}

	public Node(int vertex) {
		this.vertex = vertex;
	}

	@Override
	public String toString() {
		return "Node [vertex=" + vertex + ", left=" + left + ", right=" + right + "]";
	}

}
