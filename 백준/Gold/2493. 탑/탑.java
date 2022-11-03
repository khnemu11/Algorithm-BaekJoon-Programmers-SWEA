import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());
		Stack<Tower> stack = new Stack<>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++) {
			int receive = 0;
			int height = Integer.valueOf(st.nextToken());

			while (!stack.isEmpty()) {
				Tower curr = stack.pop();
				if (curr.height > height) {
					stack.add(curr);
					receive = curr.seq;
					break;
				}
			}

			stack.add(new Tower(i + 1, height));
			sb.append(receive);
			sb.append(" ");
		}

		bw.write(String.valueOf(sb.toString()));
		bw.newLine();
		br.close();
		bw.close();
	}
}

class Tower {
	int seq;
	int height;

	public Tower(int seq, int height) {
		this.seq = seq;
		this.height = height;
	}
}