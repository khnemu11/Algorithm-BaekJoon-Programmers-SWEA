import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int size = Integer.valueOf(br.readLine());
		Stack<Integer> s = new Stack<>();
		int heights[] = new int[size + 1];
		int idx = 0;
		int max = 0;
		while (idx < size) {
			int curr = Integer.valueOf(br.readLine());
			heights[idx] = curr;

			while (!s.isEmpty() && heights[s.peek()] > curr) {
				int height = heights[s.pop()];
				int width = s.isEmpty() == true ? idx : idx - 1 - s.peek();
				max = Math.max(max, width * height);
			}
			s.push(idx);
			idx++;
		}
		while (!s.isEmpty()) {
			int height = heights[s.pop()];
			int width = s.isEmpty() == true ? idx : idx - 1 - s.peek();
			max = Math.max(max, width * height);
		}
		bw.write(String.valueOf(max));
		bw.flush();
	}
}
