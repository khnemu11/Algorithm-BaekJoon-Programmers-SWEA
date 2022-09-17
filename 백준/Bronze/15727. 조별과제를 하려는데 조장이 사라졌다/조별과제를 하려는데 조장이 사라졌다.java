import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int distance = Integer.valueOf(br.readLine());
		int result = distance / 5 + (distance % 5 == 0 ? 0 : 1);

		bw.write(String.valueOf(result));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}
