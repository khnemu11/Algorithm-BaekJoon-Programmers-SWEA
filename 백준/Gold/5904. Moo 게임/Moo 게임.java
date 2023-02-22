import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//n최대값 : 27
public class Main {
	static char answer = 'o';
	static int target;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		target = Integer.valueOf(br.readLine());
		getLength(27, 1);
		
		bw.write(answer + "\n");
		bw.flush();
		bw.close();
	}

	public static int getLength(int n, long ptr) {
		if (n == 0) {
			return 3;
		} else {
			int left = getLength(n - 1, ptr);
			int mid = n + 2 + 1;
			int right = getLength(n - 1, ptr + mid + left);

			if (ptr == target || ptr + left == target || ptr + left + mid == target) {
				answer = 'm';
			}
			return left + mid + right;
		}
	}
}