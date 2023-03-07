import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

/* 풀이 알고리즘
*
*  0) 목표
*  규칙에 맞게 조합한 수 중 K번째로 큰 수를 출력
*  
*  1) 알고리즘
*  연속된 16진수 숫자를 균등한 크기로 4개로 나누어 숫자로 변형
*  4개의 숫자를 모두 얻었다면 맨 앞의 수를 뒤로 이동 -> 큐를 이용해서 구현
*  이때 중복한 숫자는 없애야 한다 -> set사용
*  K번째의 수를 출력 -> treemap을 사용해서 역정렬
*/

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.valueOf(br.readLine());

		for (int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.valueOf(st.nextToken());
			int K = Integer.valueOf(st.nextToken());

			String[] input = br.readLine().split("");
			Queue<String> q = new LinkedList<>();

			for (int i = 0; i < input.length; i++) { // 입력값을 큐에 넣기
				q.add(input[i]);
			}

			TreeSet<Integer> set = new TreeSet<>(Collections.reverseOrder()); // 암호값을 내림차순으로 정렬한 set

			while (true) {
				int beforeSize = set.size();

				for (int i = 0; i < 4; i++) {
					StringBuilder sb = new StringBuilder();

					for (int j = 0; j < N / 4; j++) {	//4개의 크기로 잘라 하나의 String로 변환
						String val = q.poll();
						sb.append(val);
						q.add(val);
					}

					set.add(Integer.parseInt(sb.toString(), 16));	//16진수로 변환하여 set에 저장
				}

				if (beforeSize == set.size()) {	//이전 set 크기와 현재 set크기가 변함이 없다면 새롭게 추가되는 값이 없음 -> 사이클 형성 -> 더이상 값이 생성되지 않음
					break;
				}

				q.add(q.poll());
			}

			int cnt = 1;	//n번째

			for (int val : set) {
				if (cnt == K) {	//K번째 값 출력
					bw.write("#" + testcase + " " + val + "\n");
					break;
				}
				cnt++;
			}
		}

		bw.flush(); // 결과 출력
		bw.close();
	}

}