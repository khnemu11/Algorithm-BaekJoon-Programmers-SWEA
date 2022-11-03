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

		int no = 1;

		while (true) {

			StringTokenizer st = new StringTokenizer(br.readLine());

			int L = Integer.valueOf(st.nextToken());
			int P = Integer.valueOf(st.nextToken());
			int V = Integer.valueOf(st.nextToken());
			int count = 0;

			if (L == 0 && L == P && P == V) {
				break;
			}

			count = V / P * L;

			if (V % P > L) {
				count += L;
			} else if (V % P > 0) {
				count += V % P;
			}

			StringBuilder sb = new StringBuilder();
			sb.append("Case ");
			sb.append(no);
			sb.append(": ");
			sb.append(count);

			bw.write(sb.toString());
			bw.newLine();

			no++;
		}

		bw.newLine();
		bw.flush();
		br.close();
		bw.close();
	}

}