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

		int size = Integer.valueOf(br.readLine());
		int buttons[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		int count = 0;
		for (int i = 0; i < buttons.length; i++) {
			if (buttons[i] == 0) {
				continue;
			}
			buttons[i] = 0;
			count++;
			if (i + 1 < buttons.length) {
				if (buttons[i + 1] == 0) {
					buttons[i + 1] = 1;
				} else {
					buttons[i + 1] = 0;
				}
			}
			if (i + 2 < buttons.length) {
				if (buttons[i + 2] == 0) {
					buttons[i + 2] = 1;
				} else {
					buttons[i + 2] = 0;
				}
			}
		}
		bw.write(String.valueOf(count) + "\n");
		bw.flush();

	}
}