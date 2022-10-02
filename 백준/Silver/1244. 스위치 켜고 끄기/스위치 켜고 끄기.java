import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int size = Integer.valueOf(br.readLine());

		int switches[] = new int[size];
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < size; i++) {
			switches[i] = Integer.valueOf(st.nextToken());
		}
		int num = Integer.valueOf(br.readLine());

		for (int i = 0; i < num; i++) {
			st = new StringTokenizer(br.readLine());
			int sex = Integer.valueOf(st.nextToken());
			int index = Integer.valueOf(st.nextToken()) - 1;

			if (sex == 1) {
				for (int j = index; j < size; j = j + index + 1) {
					if (switches[j] == 1) {
						switches[j] = 0;
					} else {
						switches[j] = 1;
					}
				}
			} else if (sex == 2) {
				int l = index - 1;
				int r = index + 1;

				while (true) {
					if (l < 0 || r >= size) {
						l++;
						r--;
						break;
					} else if (switches[l] != switches[r]) {
						l++;
						r--;
						break;
					}
					l--;
					r++;
				}

				while (l < index) {
					if (switches[l] == 1) {
						switches[l] = 0;
					} else {
						switches[l] = 1;
					}
					if (switches[r] == 1) {
						switches[r] = 0;
					} else {
						switches[r] = 1;
					}
					l++;
					r--;
				}

				if (switches[l] == 1) {
					switches[l] = 0;
				} else {
					switches[l] = 1;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= size; i++) {
			sb.append(switches[i - 1]);
			sb.append(" ");
			if (i % 20 == 0) {
				sb.deleteCharAt(sb.length() - 1);
				sb.append("\n");
			}
		}
		bw.write(sb.deleteCharAt(sb.length() - 1).toString());
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}
