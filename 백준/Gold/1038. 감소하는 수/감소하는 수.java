import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*풀이 알고리즘
	가장 큰 자릿수 감소 수 : 9876543210
	1씩 증가해서 구하는 경우 최악의 계산 시간 = 9,876,543,210 (약 90억으로 시간초과)
	0~9까지 숫자를 고르는 최악의 계산 시간 = 10P10= 10! = 3,628,800
 * */

public class Main {
	static int N;
	static int sequence = 0;
	static String val = "";

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.valueOf(br.readLine());
		int length = 1;

		while (true) {
			if (length == 11) { // 선택할 수 있는 숫자가 0~9개 10개밖에 없으므로 11자리수는 없다.
				val = "-1";
				break;
			}

			int selected[] = new int[length + 1];
			selected[0] = 10; // 첫번째 선택되는 수에 0~9값을 넣을 수 있게(첫번째 선택되는 수가 무조건 작게 만들게) 초기값 10 설정

			if (pick(selected, 1)) {
				break;
			}

			length++;
		}

		bw.write(val + "\n");
		bw.flush();
	}

	public static boolean pick(int selected[], int idx) { // (선택된 수, 현재 인덱스)
		if (idx == selected.length) {
			if (sequence == N) { // 목표 번째 숫자라면
				StringBuilder sb = new StringBuilder();
				for (int i = 1; i < selected.length; i++) {
					sb.append(selected[i]); // 배열을 연속된 숫자로 만들기
				}
				val = sb.toString();
				return true;
			} else {
				sequence++; // 순서 더하기
				return false;
			}
		}
		for (int num = 0; num < selected[idx - 1]; num++) { // 이전 수보다 작은 수를 선택
			selected[idx] = num;
			if (pick(selected, idx + 1)) {
				return true;
			}
		}

		return false;
	}
}