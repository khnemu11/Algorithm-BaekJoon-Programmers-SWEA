import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

/* 
 * 	풀이 알고리즘
 * 
 * 	문자열 중복 확인 알고리즘 : kmp
 *
 * */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String target = br.readLine();
		String pattern = br.readLine();

		int prefixLng[] = makeTable(pattern);// 각 맞은 문자열의 길이 별 스킵할 수 있는 길이

		int sameCnt = 0;
		Queue<Integer> startIdx = new LinkedList<>();
		int idxAfterSkip = 0; // 현재 패턴의 맞는 처음 위치

		for (int start = 0; start < target.length(); start++) {

			// 현재 값과 이전까지 패턴 문자열과 맞은 문자열이 다른경우
			// 맞은 문자열 중에 접두/접미가 같아서 스킵할 수 있는 부분이 있는 경우를 찾는다.
			while (idxAfterSkip > 0 && target.charAt(start) != pattern.charAt(idxAfterSkip)) {
				// 이전까진 다 맞았으므로 이전까지 문자열 중에 스킵할 수 있는 개수를 저장한다.
				idxAfterSkip = prefixLng[idxAfterSkip - 1];
			}
			if (target.charAt(start) == pattern.charAt(idxAfterSkip)) { // 현재 문자와 패턴 문자가 같다면
				if (idxAfterSkip == pattern.length() - 1) { // 패턴 문자 끝까지 갔는데 같다면 동일한 패턴의 문자열이다
					startIdx.add(start - pattern.length() + 2); // 해당 문자열의 시작 위치 저장
					idxAfterSkip = prefixLng[idxAfterSkip]; // 현재 문자열 중에 접두/접미가 같아서 스킵할 수 있는 만큼 저장
					sameCnt++;
				} else {
					idxAfterSkip++; // 현재 위치가 맞았으므로 다음 패턴 문자 인덱스 위치로 이동
				}
			}
			// 같지 않다면 다음 문자부터 탐색 시작
		}

		bw.write(sameCnt + "\n");
		while (!startIdx.isEmpty()) {
			bw.write(startIdx.poll() + "\n");
		}
		bw.newLine();
		bw.flush();
	}

	public static int[] makeTable(String pattern) { // 스킵할 수 있는 길이의 테이블을 만드는 메소드
		int table[] = new int[pattern.length()];
		int idxAfterSkip = 0; // 스킵 하고나서 나온 인덱스
		// 첫번째 문자는 무조건 접두/접미가 없으므로 2번째 문자부터 시작
		for (int i = 1; i < pattern.length(); i++) {
			while (idxAfterSkip > 0 && pattern.charAt(i) != pattern.charAt(idxAfterSkip)) {
				// 이전까진 다 같았으므로 이전 문자열 중 스킵할 수 있는 개수를 저장
				idxAfterSkip = table[idxAfterSkip - 1];
			}
			//현재 위치의 문자와 패턴 위치의 문가가 같으면 해당 문자열은 접두/접미가 같기 때문에 
			//(탐색한 문자열 길이 - 접두/접미의 길이) 탐색을 하지 않아도 된다.
			
			if (pattern.charAt(i) == pattern.charAt(idxAfterSkip)) {
				idxAfterSkip++;
				table[i] = idxAfterSkip;	//접두/접미 길이를 저장
			}
		}

		return table;
	}
}
