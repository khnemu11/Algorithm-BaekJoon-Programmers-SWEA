import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;

/*
 * 풀이 알고리즘
 * 
 *  0) 문제 목표
 *    숫자 N을 N씩 더해서 0~9까지 모두 최소 한번씩 등장할 때 까지 연산 후 연산 횟수 구하기
 	
 	  숫자를 문자열로 파싱하고 hashMap에 넣어 사이즈가 10이 될때까지 연산
*/
public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.valueOf(br.readLine());

		for (int testcase = 1; testcase <= T; testcase++) {
			int N = Integer.valueOf(br.readLine());
			int num = N;
			HashSet<Integer> map = new HashSet<>();
			while (map.size() != 10) {
				String[] digits = String.valueOf(num).split("");

				for (String digit : digits) {
					map.add(Integer.valueOf(digit));
				}

				if (map.size() != 10) {
					num = num + N;
				}
			}

			bw.write("#" + testcase + " " + num + "\n");
		}

		bw.flush(); // 결과 출력
		br.close();
		bw.close();
	}

}