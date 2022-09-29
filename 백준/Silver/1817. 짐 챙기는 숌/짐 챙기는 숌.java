import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int size = Integer.valueOf(st.nextToken());
		int box = Integer.valueOf(st.nextToken());

		Stack<Integer> books = new Stack<>();

		if (size > 0) {
			st = new StringTokenizer(br.readLine());
		}
		for (int i = 0; i < size; i++) {
			books.add(Integer.valueOf(st.nextToken()));
		}

		int count = 0;
		while (!books.isEmpty()) {
			int currSize = 0;
			while (!books.isEmpty()) {
				if (currSize + books.peek() > box) {
					break;
				}
				currSize += books.pop();
			}
			count++;
		}
		System.out.println(count);
		br.close();
	}
}
