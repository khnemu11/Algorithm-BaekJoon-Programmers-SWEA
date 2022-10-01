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
		int T = Integer.valueOf(br.readLine());

		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int size = Integer.valueOf(st.nextToken());
			int arr[] = new int[size];

			for (int i = 0; i < size; i++) {
				arr[i] = Integer.valueOf(st.nextToken());
			}
			Arrays.sort(arr);
			long result =0 ;
			for (int i = 0; i < size; i++) {
				for (int j = i + 1; j < size; j++) {
					int a = arr[j];
					int b = arr[i];
					result += gcd(a,b);
				}
			}
			bw.write(String.valueOf(result));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}

	public static int gcd(int a, int b) {
		if (b == 0) {
			return a;

		} else {
			return gcd(b, a % b);
		}
	}
}
