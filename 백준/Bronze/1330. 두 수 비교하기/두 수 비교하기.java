import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int a = Integer.valueOf(st.nextToken());
		int b = Integer.valueOf(st.nextToken());

		if (a > b) {
			bw.write(">");
		} else if (b > a) {
			bw.write("<");
		} else {
			bw.write("==");
		}
		bw.newLine();
		bw.flush();
		bw.close();
	}
}