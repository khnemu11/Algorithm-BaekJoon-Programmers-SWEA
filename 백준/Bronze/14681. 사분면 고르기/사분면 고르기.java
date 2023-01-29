import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int x = Integer.valueOf(br.readLine());
		int y = Integer.valueOf(br.readLine());

		if (x > 0) {
			if (y > 0) {
				bw.write("1");
			} else {
				bw.write("4");
			}
		} else {
			if (y > 0) {
				bw.write("2");
			} else {
				bw.write("3");
			}
		}
		bw.newLine();
		bw.flush();
	}
}
