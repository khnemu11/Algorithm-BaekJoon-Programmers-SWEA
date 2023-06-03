import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 *	풀이 과정
 *
 *	블루레이 최대값 = 모든 강의의 수 / 블루레이 개수 * 10,000 
 *				= 100,000 / 1 * 10,000 = 100억 -> long형 필요
 *	절반씩 줄면서 블루레이 수 안에 가능한지 확인
 *	->이분 탐색
 *
 * */

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken());
		int M = Integer.valueOf(st.nextToken());

		int lectures[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();

		long max = 0;

		for (int i = 0; i < lectures.length; i++) {
			max += lectures[i];
		}

		long result = max;
		long min = 1;

		while (min <= max) {
			if (min == 15) {
				break;
			}
			long mid = (min + max) / 2;

//			System.out.println("min : " + min + " max : " + max + " mid : " + mid);
			int cnt = 1;
			long sum = 0;

			for (int i = 0; i < lectures.length; i++) {
				if(lectures[i] > mid) {
					cnt = M+1;
					break;
				}
				if (sum + lectures[i] > mid) {
					cnt++;
					if(cnt>M) {
						break;
					}
					sum = lectures[i];
				} else {
					sum += lectures[i];
				}
			}
//			System.out.println("cnt : " + cnt);
			if (cnt > M) {
				min = mid + 1;
			} else {
				result = Math.min(mid, result);
				max = mid - 1;
			}
		}
		bw.write(result+"\n");
		bw.close();
	}
}