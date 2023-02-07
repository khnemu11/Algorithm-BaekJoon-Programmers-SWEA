import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 풀이 과정
 * 다각형의 넓이 공식 = 1/2(|x1 x2| + |x2 x3| ... + |xn-1 xn| + |xn x1|)
 * 						|y1 y2| + |y2 y3| ... + |yn-1 yn| + |yn y1|)
 * */

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());
		long x[] = new long[N + 1];
		long y[] = new long[N + 1];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			x[i] = Integer.valueOf(st.nextToken());
			y[i] = Integer.valueOf(st.nextToken());
		}

		long sum = 0;
		x[N] = x[0];
		y[N] = y[0];

		for (int i = 0; i < N; i++) {
			sum = sum + x[i] * y[i + 1] - x[i + 1] * y[i];
		}

		double area = Math.abs(sum) / 2.0;
		bw.write(String.format("%.1f\n", area));
		bw.flush();
	}
}
