import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 풀이 과정
 * 
 * 매번 정렬을해서 판단하면 n*nlogn으로 시간 초과 우려가 있음
 * 각 높이를 인덱스로 해서 높이의 개수가 담긴 배열을 이용해 투포인터로 이용해 작은값은 더해주고 큰값은 빼주는 형태로 순회
 * 
 * 예를 들어 1,1,5,7이라면 높이 배열은 0,2,0,0,0,1,0,1이 되고 왼쪽 포인터(최소값)는 1, 오른쪽 포인터는 7(최대값)이됨
 * 평탄화를 진행하면 최소값을 가지고 있는 높이의 개수를 1을 감소시키고 최소값+1의 높이의 개수를 1 중가
 * 똑같이 최대값을 가지고 있는 높이의 개수를 1을 감소시키고 최대값-1의 높이의 개수를 1 중가
 * 만약 최대값과 최소값이 같으면 더이상 평탄화가 불가능 하므로 반복문 탈출
 * */

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = 10;
		int MAX_HEIGHT = 100;
		int MIN_HEIGHT = 1;

		for (int test_case = 1; test_case <= T; test_case++) {
			int dump = Integer.valueOf(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int min = MAX_HEIGHT;
			int max = MIN_HEIGHT;
			int heights[] = new int[MAX_HEIGHT + 1];

			while (st.hasMoreTokens()) {
				int height = Integer.valueOf(st.nextToken());
				min = Math.min(min, height);
				max = Math.max(max, height);
				heights[height]++;
			}

			while (dump-- > 0) {
				heights[min]--;
				heights[min + 1]++;
				heights[max]--;
				heights[max - 1]++;

				while (heights[min] == 0) {
					min++;
				}
				while (heights[max] == 0) {
					max--;
				}

				if (min == max) {
					break;
				}
			}

			StringBuilder sb = new StringBuilder();

			sb.append("#").append(test_case).append(" ").append(max - min).append("\n");
			bw.write(sb.toString());
		}

		bw.flush();
	}
}
