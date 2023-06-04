import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 *	풀이 과정
 *	
 *	자신 보다 작은 것만 먹기 가능
 * -> lower bound
 * -> 이분 탐색
 * 
 * */

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sizeA = Integer.parseInt(st.nextToken());
			int sizeB = Integer.parseInt(st.nextToken());

			int a[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();
			int b[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();

			// 이분 탐색을 위한 정렬
			Arrays.sort(b);
			int total = 0;
			for (int num : a) {
				int lowerCnt = lowerBound(b, num);
				total += lowerCnt;
			}
			bw.write(total + "\n");
		}

		bw.close();
	}
	//lower bound 이분 탐색 메소드
	public static int lowerBound(int arr[], int target) {
		int l = 0;
		int r = arr.length - 1;
		
		//모든 b의 값보다 크다면 b의 길이만큼 반환
		if (arr[r] < target) {
			return arr.length;
		}
		//lower bound 의 인덱스 = 자신보다 작은 숫자의 개수
		while (l < r) {
			int mid = (l + r) / 2;
			if (arr[mid] < target) {
				l = mid + 1;
			} else {
				r = mid;
			}
		}

		return l;
	}
}
