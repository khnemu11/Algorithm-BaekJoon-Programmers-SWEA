import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.valueOf(st.nextToken());
		int length = Integer.valueOf(st.nextToken());

		int fruits[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).sorted().toArray();
		for (int fruit : fruits) {
			if (length < fruit) {
				break;
			}
			length++;
		}
		bw.write(String.valueOf(length));
		bw.newLine();
		bw.flush();
	}
}
