import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long num = Long.valueOf(br.readLine());
		int count = 0;
		long sum = 0;
		long next = 0;
		while (sum + next <= num) {
			sum = sum + next;
			next++;
			count++;
		}

		System.out.println(count - 1);
		br.close();
	}
}
