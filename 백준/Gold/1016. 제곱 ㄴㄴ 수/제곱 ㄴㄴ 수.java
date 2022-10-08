import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long min = Long.valueOf(st.nextToken());
		long max = Long.valueOf(st.nextToken());
		int size = (int) (max - min + 1);
//		System.out.println(size);
		boolean visited[] = new boolean[size + 1];

		int result = size;
		for (int i = 2; (long)i * i <= max; i++) {
			long start = 0;
			long next = (long)i * i;
			
			if (min % next == 0) {
				start = min;
			} else {
				start = min + (next - min % (next));
			}

			if (start > max) {
				continue;
			}
//			System.out.println("i : " + i);
//			System.out.println("next : " + next);
//			System.out.println("min : " + min);
//			System.out.println("start : " + start);

			while (start <= max) {
				if (visited[(int) (start - min)] == true) {
				} else {
					visited[(int) (start - min)] = true;
					result--;
//					System.out.println(start + " 제곱 수");
//					System.out.println(start - min + " index");
//					System.out.println(max + " 최대값");
//					System.out.println("result  : " + result);
				}

				start = start + next;
			}
		}
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}
}
