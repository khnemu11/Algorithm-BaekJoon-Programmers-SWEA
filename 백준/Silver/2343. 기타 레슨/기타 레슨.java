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
		int N = Integer.valueOf(st.nextToken()); // 강의 최대 개수
		int M = Integer.valueOf(st.nextToken()); // 블루레이 개수

		int lectures[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();

		long max = 0; // 가능한 블루레이 최대값
		long min = 0; // 가능한 블루레이 최소값
		
		//블루레이 최대값과 최소값을 구하는 부분
		for (int i = 0; i < lectures.length; i++) {
			max += lectures[i];
			min = Math.max(min, lectures[i]);
		}
		
		//이분탐색을 이용해 블루레이 최소값을 구하는 부분
		while (min <= max) {
			long mid = (min + max) / 2;
			int cnt = 1;	//블루레이 개수
			long sum = 0;	//현재 블루레이 크기

			for (int i = 0; i < lectures.length; i++) {
				//현재 강의를 합치면 블루레이 크기를 넘어갔을 때 다음 블루레이로
				if (sum + lectures[i] > mid) {
					cnt++;
					sum = 0;
				}
				sum += lectures[i];
			}
			
			//현재 블루레이 크기로 만들 시 지정된 개수를 오버할 때 최소값 최신화
			if (cnt > M) {
				min = mid + 1;
			} 
			//현재 블루레이 크기로 만들 수 있을 때 최대값 최신화
			else {
				max = mid - 1;
			}
		}
		//최소값 출력
		bw.write(min + "\n");
		bw.close();
	}
}
