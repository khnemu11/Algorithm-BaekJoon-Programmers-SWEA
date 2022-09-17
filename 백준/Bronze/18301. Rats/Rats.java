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

		int n1 = Integer.valueOf(st.nextToken());
		int n2 = Integer.valueOf(st.nextToken());
		int n3 = Integer.valueOf(st.nextToken());

		int result = (n1 + 1) * (n2 + 1) / (n3 + 1) - 1;

		bw.write(String.valueOf(result));
		bw.newLine();

		bw.flush();
		bw.close();
		br.close();
	}
}
