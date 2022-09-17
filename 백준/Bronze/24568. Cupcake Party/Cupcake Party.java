import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int bigBox = Integer.valueOf(br.readLine());
		int smalBox = Integer.valueOf(br.readLine());
		int total = 8 * bigBox + 3 * smalBox;

		bw.write(String.valueOf(total - 28));
		bw.newLine();

		bw.flush();
		bw.close();
		br.close();
	}
}
