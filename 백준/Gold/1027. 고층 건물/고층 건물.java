import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*풀이 알고리즘
1) 한 빌딩이 볼 수 있는 고층 빌딩의 개수 = 볼 수 있는 왼쪽 빌딩의 개수 + 볼 수 있는 오른쪽 빌딩의 개수
2) 두 빌딩을 볼 수 있다 == 두 빌딩 시선에 빌딩이 없다 -> 시선 == 두 빌딩 간의 점선 -> 
      두 점을 지나는 일차 함수의 y값(빌딩 높이)보다 두점 사이의 y값이 모두 작으면 두 빌딩은 서로 볼 수 있음
3) 가운데 기준점을 바탕으로 왼쪽 점들과의 일차함수, 오른쪽 점들과의 일차함수를 구하고 두 점사이의 점들이 모두 각 함수의 y값보다 작으면 개수더해서 최대값 출력
*/

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int size = Integer.valueOf(br.readLine());
		int heights[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		int max = 0;
		//가운데 점 탐색
		for (int mid = 0; mid < heights.length; mid++) {
			int l = mid - 1;
			int r = mid + 1;
			int left = 0;
			int right = 0;
			//볼 수 있는 왼쪽 빌딩 탐색
			while (l >= 0) {
				boolean isValid = true;
				//선정된 왼쪽 빌딩과 가운데 빌딩의 일차함수 기울기
				double gradient = ((double) heights[mid] - heights[l]) / (mid - l);
				
				//일차함수의 y값과 사이의 건물의 y값을 비교
				for (int curr = mid - 1; curr > l; curr--) {
					if (gradient * (curr - l) + heights[l] <= heights[curr]) {
						isValid = false;
						break;
					}
				}

				if (isValid) {
					left++;
				}
				l--;
			}
			//볼 수 있는 오른쪽 빌딩 탐색, 왼쪽과 동일
			while (r < heights.length) {
				boolean isValid = true;
				double gradient = ((double) heights[r] - heights[mid]) / (r - mid);
				for (int curr = mid + 1; curr < r; curr++) {
					if (gradient * (curr - mid) + heights[mid] <= heights[curr]) {
						isValid = false;
						break;
					}
				}

				if (isValid) {
					right++;
				}
				r++;
			}
			//볼 수 있는 왼쪽 건물의 개수와 오른쪽 건물의 수를 합쳐 최대값 설정
			max = Math.max(max, left + right);
		}

		System.out.println(max);
	}

}
