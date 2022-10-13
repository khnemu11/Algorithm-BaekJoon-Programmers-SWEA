import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.valueOf(br.readLine());

		while (T-- > 0) {
			int size = Integer.valueOf(br.readLine());
			int arr[] = new int[size];
			boolean visited[] = new boolean[size];
			int count = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int i = 0; i < size; i++) {
				arr[i] = Integer.valueOf(st.nextToken()) - 1;
			}
			for (int i = 0; i < size; i++) {
				if (visited[i]) {
					continue;
				}

				int index = i;

				do {
					visited[index] = true;
					index = arr[index];
				} while (index != i);

				count++;
			}
			bw.write(String.valueOf(count));
			bw.newLine();
		}

		br.close();
		bw.close();
	}
}
