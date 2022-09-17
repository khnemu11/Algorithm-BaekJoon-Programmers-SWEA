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

		int Ur = Integer.valueOf(st.nextToken());
		int Tr = Integer.valueOf(st.nextToken());
		int Uo = Integer.valueOf(st.nextToken());
		int To = Integer.valueOf(st.nextToken());
		int result = 56 * Ur + 24 * Tr + 14 * Uo + 6 * To;

		bw.write(String.valueOf(result));
		bw.newLine();

		bw.flush();
		bw.close();
		br.close();
	}
}
