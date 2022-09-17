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
		StringBuilder sb = new StringBuilder();

		int prize = Integer.valueOf(br.readLine());

		int a = (int) (prize * 0.78);
		int b = (int) (prize * 0.8 + prize * 0.2 * 0.78);

		sb.append(a);
		sb.append(" ");
		sb.append(b);
		
		bw.write(sb.toString());
		bw.newLine();

		bw.flush();
		bw.close();
		br.close();
	}
}
