import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int no = 0;
		while (true) {
			no++;
			int num = Integer.valueOf(br.readLine());
			if (num == 0) {
				break;
			}
			HashMap<Integer, String> list = new HashMap<>();
			int arr[] = new int[num + 1];
			for (int i = 1; i <= num; i++) {
				list.put(i, br.readLine());
			}
			for (int i = 0; i < 2 * num - 1; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int seq = Integer.valueOf(st.nextToken());

				arr[seq]++;
			}
			for (int i = 1; i <= num; i++) {
				if (arr[i] == 1) {
					StringBuilder sb = new StringBuilder();

					sb.append(no);
					sb.append(" ");
					sb.append(list.get(i));

					bw.write(sb.toString());
					bw.newLine();

					break;
				}
			}
		}

		bw.flush();
		bw.close();
		br.close();
	}
}
