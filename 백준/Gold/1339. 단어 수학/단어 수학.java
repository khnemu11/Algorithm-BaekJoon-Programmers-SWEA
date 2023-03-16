import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/*
	풀이 알고리즘
	
	등장한 알파벳의 자리수를 기억해서 자리수의 합이 가장 큰것을 구해야함
	즉 모든 자리수*10을 더한 알파벳이 가장 큰 알파벳이 가장 큰 수를 가진다.
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		Alpha alpha[] = new Alpha[26];
		int N = Integer.valueOf(br.readLine());
		String input[] = new String[N];

		for (int i = 0; i < alpha.length; i++) {
			alpha[i] = new Alpha((char) ('A' + i));
		}

		for (int i = 0; i < N; i++) { // 알파벳이 등장하는 인덱스를 추가하는 메소드
			input[i] = br.readLine();
			for (int idx = 0; idx < input[i].length(); idx++) {
				char word = input[i].charAt(idx);
				alpha[word - 'A'].location.add(input[i].length() - idx);
			}
		}

		Arrays.sort(alpha); // 내림차순으로 정렬
		HashMap<Character, Integer> map = new HashMap<>(); // 알파벳으로 숫자를 찾는 메소드

		int value = 9; // 숫자 최대값

		for (int i = 0; i < alpha.length; i++) {
			if (alpha[i].location.size() == 0) { // 알파벳이 등장하지 않았으면 더이상 찾을 가치가 없으므로 반목문 탈출
				break;
			}
			map.put(alpha[i].alpha, value); // 가장 큰 알파벳에 가장 큰 숫자를 대입
			value--;
		}

		int sum = 0;

		for (int i = 0; i < N; i++) {
			int ex = 0;
			for (int j = input[i].length() - 1; j >= 0; j--) {
				sum = (int) (sum + map.get(input[i].charAt(j)) * Math.pow(10, ex)); // 각 알파벳에 10의 자리수를 따져서 총합 계산
				ex++;
			}
		}

		bw.write(sum + "\n");
		bw.flush();
	}

}

class Alpha implements Comparable<Alpha> { // 알파벳과 알파벳이 등장한 인덱스를 저장하는 객체
	char alpha;
	ArrayList<Integer> location;

	public Alpha(char alpha) {
		this.alpha = alpha;
		location = new ArrayList<>();
	}

	@Override
	public int compareTo(Alpha o) { // 각 알파벳이 등장한 인덱스를 지수로 10을 제곱하여 더한 것 중 큰것을 찾는 메소드
		int thisSum = 0;
		int oSum = 0;

		for (int loc : this.location) {
			thisSum += Math.pow(10, loc);
		}
		for (int loc : o.location) {
			oSum += Math.pow(10, loc);
		}

		return oSum - thisSum;
	}
}
