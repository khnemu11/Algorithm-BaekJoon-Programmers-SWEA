import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.valueOf(br.readLine());

		Node tree = new Node("A");

		for (int i = 0; i < num; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String value = st.nextToken();
			String leftValue = st.nextToken();
			String rightValue = st.nextToken();

			Queue<Node> nodes = new LinkedList<>();
			nodes.add(tree);
			Node curr = new Node("");
			while (!nodes.isEmpty()) {
				int size = nodes.size();
				for (int j = 0; j < size; j++) {
					curr = nodes.poll();

					if (curr.value.equals(value)) {
						nodes.clear();
						break;
					}
					if (curr.left != null) {
						nodes.add(curr.left);
					}
					if (curr.right != null) {
						nodes.add(curr.right);
					}
				}
			}
			if (!leftValue.equals(".")) {
				curr.left = new Node(leftValue);
			}
			if (!rightValue.equals(".")) {
				curr.right = new Node(rightValue);
			}
		}

		StringBuilder pre = new StringBuilder();
		StringBuilder in = new StringBuilder();
		StringBuilder post = new StringBuilder();
		
		traversal(tree,pre,in,post);
		
		bw.write(pre.toString());
		bw.newLine();
		bw.write(in.toString());
		bw.newLine();
		bw.write(post.toString());
		bw.newLine();
		
		bw.flush();
		bw.close();
		br.close();
	}

	public static void traversal(Node curr, StringBuilder pre, StringBuilder in, StringBuilder post) {
		pre.append(curr.value);
		if (curr.left != null) {
			traversal(curr.left, pre, in, post);
		}
		in.append(curr.value);
		if (curr.right != null) {
			traversal(curr.right, pre, in, post);
		}
		post.append(curr.value);
	}
}

class Node {
	String value;
	Node left;
	Node right;

	public Node(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Node [value=" + value + ", left=" + left + ", right=" + right + "]";
	}
}