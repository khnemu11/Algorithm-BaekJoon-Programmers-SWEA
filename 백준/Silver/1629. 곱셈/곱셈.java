import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long a = Long.valueOf(st.nextToken());
		long b = Long.valueOf(st.nextToken());
		long c = Long.valueOf(st.nextToken());
		long rest = mutiplyRest(a,b,c);
		
		bw.write(String.valueOf(rest));
		br.close();
		bw.close();
	}

	public static long mutiplyRest(long num, long ex, long c) {
		long divideNum = 0;

		if (ex > 1) {
			divideNum = mutiplyRest(num, ex / 2, c);
		} else {
			return num % c;
		}

		if (ex % 2 == 1) {
			return (((divideNum * divideNum) % c) * (num % c))%c;
		} else {
			return (divideNum * divideNum) % c;
		}

	}
}