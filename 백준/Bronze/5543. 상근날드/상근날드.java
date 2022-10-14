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
		int hamburger[] = new int[3];
		for (int i = 0; i < 3; i++) {
			hamburger[i] = Integer.valueOf(br.readLine());
		}
		int drink[] = new int[2];
		for (int i = 0; i < 2; i++) {
			drink[i] = Integer.valueOf(br.readLine());
		}
		int total = Arrays.stream(hamburger).min().getAsInt() + Arrays.stream(drink).min().getAsInt() - 50;

		bw.write(String.valueOf(total));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}