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
	
	앞자리를 가장 크게해야함
	
	가장 먼저 등장한 숫자를 남은 숫자중 제일 큰 숫자로
	만약 등장한 알파벳의 숫자가 같으면 다음 알파벳으로
	해당 알파벳과 동일한 위치에 등장한 알파벳 중 이미 등장한 알파벳이 있다면 가장 큰값으로
	
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

		for (int i = 0; i < N; i++) {
			input[i] = br.readLine();
			for (int idx = 0; idx < input[i].length(); idx++) {
				char word = input[i].charAt(idx);
				alpha[word - 'A'].location.add(input[i].length() - idx);
			}
		}

		Arrays.sort(alpha);
		HashMap<Character, Integer> map = new HashMap<>();

		int value = 9;

		for (int i = 0; i < alpha.length; i++) {
			if (alpha[i].location.size() == 0) {
				break;
			}
			map.put(alpha[i].alpha, value);
			value--;
		}

//		System.out.println(Arrays.toString(alpha));
//		System.out.println(map);

		int sum = 0;

		for (int i = 0; i < N; i++) {
			int ex = 0;
			for (int j = input[i].length() - 1; j >= 0; j--) {
				sum = (int) (sum + map.get(input[i].charAt(j)) * Math.pow(10, ex));
				ex++;
			}
		}

		bw.write(sum + "\n");
		bw.flush();
	}

}

class Alpha implements Comparable<Alpha> {
	char alpha;
	ArrayList<Integer> location;

	public Alpha(char alpha) {
		this.alpha = alpha;
		location = new ArrayList<>();
	}

	@Override
	public int compareTo(Alpha o) {
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

	@Override
	public String toString() {
		return "Alpha [alpha=" + alpha + ", location=" + location + "]";
	}
}