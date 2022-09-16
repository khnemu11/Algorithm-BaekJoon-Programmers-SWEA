import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int n = Integer.valueOf(br.readLine());
		int set = 0;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			if (command.equals("add")) {
				set = set | (1 << Integer.valueOf(st.nextToken()));
			} else if (command.equals("remove")) {
				set = set & ~(1 << Integer.valueOf(st.nextToken()));
			} else if (command.equals("check")) {
				int num = Integer.valueOf(st.nextToken());
				if ((set & (1 << num)) > 0) {
					sb.append(1);
				} else {
					sb.append(0);
				}
				sb.append("\n");
			} else if (command.equals("toggle")) {
				set = set ^ (1 << Integer.valueOf(st.nextToken()));
			} else if (command.equals("all")) {
				for (int j = 1; j < 21; j++) {
					set = set | (1 << j);
				}
			} else if (command.equals("empty")) {
				set = 0;
			}

		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}