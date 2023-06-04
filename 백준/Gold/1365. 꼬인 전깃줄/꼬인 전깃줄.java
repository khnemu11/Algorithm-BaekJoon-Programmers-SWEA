import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 *	풀이 과정
 *	
 *	잘라야 하는 최소 전선의 개수 = 전체 전선의 수 - 겹치지 않도록 하는 전선의 최대 개수
 *  -> 최장 증가 수열
 *  -> 이분탐색
 * 
 * */

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		List<Integer> list = new ArrayList<>();

		int N = Integer.valueOf(br.readLine());
		int poles[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();

		// 이분 탐색을 활용해 최장 증가 배열을 구하는 부분
		for (int pole : poles) {
			int idx = Collections.binarySearch(list, pole) * -1 - 1;

			// 모든 수 보다 크다면 == 배열에 추가할 수 있다면
			if (idx == list.size()) {
				list.add(pole);
			}
			// lower bound에 추가
			else {
				list.set(idx, pole);
			}
		}

		bw.write(N - list.size() + "\n");
		bw.close();
	}
}
