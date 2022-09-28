import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String num = br.readLine();
		int count = 0;
		while (num.length() > 1) {
			char digit[] = num.toCharArray();

			long sum = 0;
			for (int i = 0; i < digit.length; i++) {
				sum += digit[i] - '0';
			}
			num = String.valueOf(sum);
			count++;
		}
		System.out.println(count);

		if (Integer.valueOf(num) % 3 == 0) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
		br.close();
	}
}
