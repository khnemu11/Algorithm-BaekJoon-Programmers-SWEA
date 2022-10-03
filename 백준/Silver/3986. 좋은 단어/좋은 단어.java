import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.valueOf(br.readLine());
		int count = 0;
		for (int t = 0; t < T; t++) {
			char word[] = br.readLine().toCharArray();
			Stack<Character> stack = new Stack<>();

			for (int i = 0; i < word.length; i++) {
				if (stack.isEmpty()) {
					stack.add(word[i]);
				} else if (stack.peek() == word[i]) {
					stack.pop();
				} else {
					stack.add(word[i]);
				}
			}
			if (stack.isEmpty()) {
				count++;
			}
		}

		bw.write(String.valueOf(count));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}
