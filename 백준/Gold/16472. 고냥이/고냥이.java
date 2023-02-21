import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

/*풀이 알고리즘
 *  현재까지 나온 알파벳의 종류의 개수를 파악
 *  만약 종류가 최대 개수 N보다 큰경우 가장 처음 등장한 알파벳을 제거(가장 멀리 떨어진 알파벳이기 때문) -> 큐를 이용
 *  시간 복잡도 = O(2N) <- 큐에 알파벳을 넣는 시간 N, 뺴는 시간 N
 *  가장 긴 길이를 출력
 * */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());
		char[] input = br.readLine().toCharArray();
		Queue<Character> queue = new LinkedList<>();
		int typeCnt = 0;
		int alphaCnt[] = new int[26];
		int max = 0;
		int length = 0;

		for (char alpha : input) {
			queue.add(alpha);

			if (alphaCnt[alpha - 'a'] == 0) { // 새로운 알파벳이 들어오면 알파벳 종류 증가
				typeCnt++;
			}
			alphaCnt[alpha - 'a']++; // 알파벳 개수 증가

			while (typeCnt > N) {	//현재 알파벳 종류가 N보다 많은 경우 같아질 때 까지 종류 감소
				char currAlpha = queue.poll();	
				alphaCnt[currAlpha - 'a']--;
				if (alphaCnt[currAlpha - 'a'] == 0) {
					typeCnt--;
				}
			}

			length = queue.size();
			max = Math.max(max, length);
		}
		bw.write(max + "\n");
		bw.flush();
	}
}