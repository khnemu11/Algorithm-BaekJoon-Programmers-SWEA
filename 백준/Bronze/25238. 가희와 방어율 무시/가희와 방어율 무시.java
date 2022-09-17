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

		int guard = Integer.valueOf(st.nextToken());
		int ignore = Integer.valueOf(st.nextToken());

		int result = guard * (100 - ignore) / 100;
		bw.write(String.valueOf(result < 100 ? 1 : 0));

		br.close();
		bw.close();
	}
}
