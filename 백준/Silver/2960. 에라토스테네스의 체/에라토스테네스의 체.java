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

		StringTokenizer st = new StringTokenizer(br.readLine());
		int size = Integer.valueOf(st.nextToken());
		int target = Integer.valueOf(st.nextToken());
		boolean visited[] = new boolean[size + 1];
		int count = 0;
		int result = 0;
		boolean find = false;

		for (int i = 2; i <= size; i++) {
			if (visited[i] == false) {
				count++;
				if (count == target) {
					find = true;
					result = i;
					break;
				}
				for (int j = i + i; j <= size; j = j + i) {
					if(visited[j]==false) {
						visited[j] = true;
						count++;
					}
					if (count == target) {
						find = true;
						result = j;
						break;
					}
				}
			}
			if (find == true) {
				break;
			}
		}

		bw.write(String.valueOf(result));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}