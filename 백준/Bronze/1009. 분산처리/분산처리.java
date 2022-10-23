import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.valueOf(br.readLine());

		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.valueOf(st.nextToken());
			int b = Integer.valueOf(st.nextToken());

			List<Integer> num = new ArrayList<>();

			int digitOfOne = a % 10;
			int digit = digitOfOne;

			while (!num.contains(digit)) {
				num.add(digit);
				digit = digit * digitOfOne % 10;
			}
			int count = b % num.size();
			int result = 0;

			if (count == 0) {
				result = num.get(num.size() - 1);
			} else {
				result = num.get(count - 1);
			}

			if (result == 0) {
				result = 10;
			}

			bw.write(String.valueOf(result));
			bw.newLine();
		}

		bw.close();
		br.close();
	}

}
