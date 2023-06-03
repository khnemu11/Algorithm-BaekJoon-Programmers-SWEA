import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 *	풀이 과정
 *	
 *	1.
 *	두 집합 간 요소가 있는지 확인 -> 해시 셋을 활용
 *	걸린 시간 : 1652ms
 *
 *	2.
 *	두 집합 간 요소가 있는지 확인 -> 이분 탐색을 활용
 *  걸린 시간 : 1972ms
 * */

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		
		//해시를 활용한 문제 풀이
		
		/*
		for (int tc = 1; tc <= T; tc++) {
			Set<Integer> note1 = new HashSet<>();

			int note1Length = Integer.parseInt(br.readLine());

			StringTokenizer st = new StringTokenizer(br.readLine());

			while (st.hasMoreTokens()) {
				note1.add(Integer.valueOf(st.nextToken()));
			}

			int note2Length = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());

			while (st.hasMoreTokens()) {
				int val = Integer.valueOf(st.nextToken());
				
				if(note1.contains(val)) {
					bw.write("1\n");
				}else {
					bw.write("0\n");
				}
			}
		}
		*/
		//이분 탐색을 이용한 문제 풀이
		
		for (int tc = 1; tc <= T; tc++) {
			int note1Length = Integer.parseInt(br.readLine());
			int note1[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();

			int note2Length = Integer.parseInt(br.readLine());
			int note2[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();
			
			//이분 탐색을 위한 정렬
			Arrays.sort(note1);
			
			//이분 탐색을 통해 note2에 있는 값이 note1에 있는지 확인
			for (int val : note2) {
				int result = 0;
				if (Arrays.binarySearch(note1, val) >= 0) {
					result = 1;
				}
				bw.write(result + "\n");
			}
		}

		bw.close();
	}
}
