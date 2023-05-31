import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	/*
	 * 
	 * 가장 긴 증가수열 값을 제외한 모든 값을 이동하면 최소 이동이 된다.
	 * 가장 긴 증가수열 -> 이분탐색
	 */
	public static void main(String[] args) throws Exception {
		//autoclosable 사용을 위한 try-with-resources
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			List<Integer> longestIncreasingNums = new ArrayList<>();	//증가 수열을 넣을 리스트
			int N = Integer.valueOf(br.readLine());		//들어올 숫자의 개수
			
			//이분 탐색을 이용해 가장 긴 증가수열을 구하는 부분
			for (int i = 0; i < N; i++) {
				int no = Integer.valueOf(br.readLine());	//현재 값

				int idx = Collections.binarySearch(longestIncreasingNums, no) * -1 -1;	//lower bound 인덱스 위치를 이분탐색으로 가져오는 부분
				if (idx >= longestIncreasingNums.size()) {	//뒤에 추가할 수 있는 숫자라면
					longestIncreasingNums.add(no);
				} else {	//중간에 교체되는 숫자라면
					longestIncreasingNums.set(idx, no);
				}
			}
			
			bw.write((N-longestIncreasingNums.size()) + "\n");	//가장 증가하는 수열의 개수를 제외한 나머지를 이동한 값 출력
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}