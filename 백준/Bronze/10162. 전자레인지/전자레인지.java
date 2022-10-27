import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int timers[] = { 300, 60, 10 };
		int input = Integer.valueOf(br.readLine());
		int count[] = new int[3];

		for (int i = 0; i < 3; i++) {
			count[i] = input / timers[i];
			input = input % timers[i];
		}
		StringBuilder sb = new StringBuilder();
		
		if (input != 0) {
			sb.append(-1);
		}
		else {
			sb.append(Arrays.stream(count).mapToObj(x->String.valueOf(x)).collect(Collectors.joining(" ", "", "")));
		}
		
		bw.write(sb.toString());
		bw.newLine();
		bw.flush();
		br.close();
		bw.close();
	}

}