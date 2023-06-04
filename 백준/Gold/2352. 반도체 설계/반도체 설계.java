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
 *	현재 포트를 사용했을 때 최장 길이 -> 최장 증가 배열
 * -> 이분탐색
 * */

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		List<Integer> list = new ArrayList<>();

		int N = Integer.valueOf(br.readLine());
		int ports[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();
		
		//이분 탐색을 활용해 최장 증가 배열을 구하는 부분
		for (int port : ports) {
			int idx = Collections.binarySearch(list, port) * -1 - 1;
			
			//모든 수 보다 크다면 == 배열에 추가할 수 있다면
			if (idx == list.size()) {
				list.add(port);
			} 
			//lower bound에 추가
			else {
				list.set(idx, port);
			}
		}
		
		bw.write(list.size() + "\n");
		bw.close();
	}
}
