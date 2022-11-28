import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = "";
		// 입력 받고
		Node root = null;

		while ((input = br.readLine()) != null && !input.equals("")) {
			int value = Integer.valueOf(input);
			if (root == null) {
				root = new Node(value);
				continue;
			}
			addTree(root, value);

		}

		postOrder(root, bw);
		bw.flush();
	}

	public static void addTree(Node curr, int value) {
		if (curr.value > value) {
			if (curr.left == null) {
				curr.left = new Node(value);
			} else {
				addTree(curr.left, value);
			}
		} else {
			if (curr.right == null) {
				curr.right = new Node(value);
			} else {
				addTree(curr.right, value);
			}
		}

	}

	public static void postOrder(Node curr, BufferedWriter bw) throws IOException {
		if (curr.left != null) {
			postOrder(curr.left, bw);
		}
		if (curr.right != null) {
			postOrder(curr.right, bw);
		}
		bw.write(String.valueOf(curr.value));
		bw.newLine();
	}
}

class Node {
	int value;
	Node left;
	Node right;

	public Node(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Node [value=" + value + ", left=" + left + ", right=" + right + "]";
	}

}
