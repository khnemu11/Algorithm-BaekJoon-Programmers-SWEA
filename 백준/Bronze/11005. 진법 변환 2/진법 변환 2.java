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

		int num = Integer.valueOf(st.nextToken());
		int radix = Integer.valueOf(st.nextToken());
		String result = Integer.toString(num, radix).toUpperCase();

		bw.write(result);
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}

}
