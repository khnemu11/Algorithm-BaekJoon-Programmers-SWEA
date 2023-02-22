import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*풀이 알고리즘
 * m의 위치 -> 왼쪽 m 시작위치, 가운데 m 시작 위치, 오른쪽 m 시작 위치로 나눌 수 있다.
 * -> 시작위치, 시작위치 + S(n-1)의 길이, 시작 위치+S(n-1)의 길이 + n+2(가운데 o의 개수)+1(가운데 m의 개수) 
 * 
 * 위의 규칙대로 S(n)의 길이를 임의값의 넣어 구해보니 27이 최대였음 (약 10억)
 * 기본 정답을 o로 하고 만약 목표의 위치가 m이 시작하는 위치라면 m을 출력하도록 알고리즘 설정 
 * 
 * */
public class Main {
	static char answer = 'o';
	static int target;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		target = Integer.valueOf(br.readLine());
		getLength(27, 1);

		bw.write(answer + "\n");
		bw.flush();
		bw.close();
	}

	public static int getLength(int n, long ptr) { // s(n)의 길이를 구하는 메소드 (n, 왼쪽 m 시작 위치)
		if (n == 0) { // S(0) = moo이므로 길이는 3이다.
			return 3;
		} else {
			int left = getLength(n - 1, ptr); // 왼쪽 길이
			int mid = n + 2 + 1; // 중간 길이
			int right = getLength(n - 1, ptr + mid + left); // 오른쪽 길이

			if (ptr == target || ptr + left == target || ptr + left + mid == target) {
				answer = 'm'; // 왼쪽 m 시작 위치, 가운데 m 시작 위치, 오른쪽 m 시작 위치가 목표 위치와 같다면 m 출력
			}

			return left + mid + right; // s(n)의 길이 리턴
		}
	}
}