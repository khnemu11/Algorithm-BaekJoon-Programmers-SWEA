import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.valueOf(br.readLine());

		for (int test_case = 0; test_case < T; test_case++) {
			char commandArr[] = br.readLine().toCharArray();

			Stack<Character> stack = new Stack<>();
			Stack<Character> rest = new Stack<>();
			for (char command : commandArr) {
				if (command == '<') {
					if (!stack.isEmpty()) {
						rest.add(stack.pop());
					}
				} else if (command == '>') {
					if (!rest.isEmpty()) {
						stack.add(rest.pop());
					}
				} else if (command == '-') {
					if (!stack.isEmpty()) {
						stack.pop();
					}
				} else {
					stack.add(command);
				}
			}
			while (!rest.isEmpty()) {
				stack.add(rest.pop());
			}

			StringBuilder sb = new StringBuilder();
			while (!stack.isEmpty()) {
				sb.append(stack.pop());
			}
			bw.write(sb.reverse().toString() + "\n");
		}

		bw.flush();
	}
}
