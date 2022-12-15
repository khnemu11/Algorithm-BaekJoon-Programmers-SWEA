import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int num[] = new int[5];

		for (int i = 0; i < num.length; i++) {
			num[i] = Integer.valueOf(br.readLine());
		}
		Arrays.sort(num);
		bw.write(String.valueOf((int) Arrays.stream(num).average().getAsDouble()));
		bw.newLine();
		bw.write(String.valueOf(num[2]));
		bw.newLine();
		bw.flush();

		br.close();
		bw.close();
	}
}
