import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.valueOf(br.readLine());

		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.valueOf(st.nextToken());
			int goal = Integer.valueOf(st.nextToken());
			boolean find = false;
			Queue<Pattern> candidates = new LinkedList<>();
			boolean visited[] = new boolean[100001];
			candidates.add(new Pattern("", start));

			while (!candidates.isEmpty()) {
				int size = candidates.size();
				Pattern curr = new Pattern("", 0);
				while (size-- > 0) {
					curr = candidates.poll();

					if (curr.value == goal) {
						find = true;
						break;
					}

					StringBuilder sb = new StringBuilder();
					int d = curr.value * 2 % 10000;
					String path = sb.append(curr.path).append("D").toString();

					if (!visited[d]) {
						visited[d] = true;
						candidates.add(new Pattern(path, d));
					}

					int s = curr.value;
					if (s == 0) {
						s = 9999;
					} else {
						s = s - 1;
					}
					sb.setLength(0);
					path = sb.append(curr.path).append("S").toString();

					if (!visited[s]) {
						visited[s] = true;
						candidates.add(new Pattern(path, s));
					}
					int temp = curr.value;
					int digit[] = new int[4];

					for (int i = 3; i >= 0; i--) {
						digit[i] = temp % 10;
						temp /= 10;
					}

					int L = digit[1] * 1000 + digit[2] * 100 + digit[3] * 10 + digit[0];
					sb.setLength(0);
					path = sb.append(curr.path).append("L").toString();
					if (!visited[L]) {
						visited[L] = true;
						candidates.add(new Pattern(path, L));
					}

					temp = curr.value;
					int R = digit[3] * 1000 + digit[0] * 100 + digit[1] * 10 + digit[2];
					sb.setLength(0);
					path = sb.append(curr.path).append("R").toString();

					if (!visited[R]) {
						visited[R] = true;
						candidates.add(new Pattern(path, R));
					}
				}

				if (find) {
					bw.write(curr.path);
					bw.newLine();
					break;
				}
			}
		}

		bw.newLine();

		bw.flush();
		bw.close();
		br.close();
	}
}

class Pattern {
	String path;
	int value;

	public Pattern(String path, int value) {
		this.path = path;
		this.value = value;
	}
}
