import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;

/*
	풀이 알고리즘
	
	a의 모든 누적합 경우의 수와 b의 모든 누적합 경우의 수를 구한다
	-> n*n/2 *2 = n*n -> 1_000_000
	
	각각 hashmap으로 (누적합 값 : 개수)로 저장 최대 
	
	누적합의 최대값 = 1,000,000 * 1,000 > 20억
	개수의 최대값 = 1,000,000 * 10,000,000 > 20억
	long형으로 선언
 */
public class Main {
	static boolean airArea[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		long target = Long.valueOf(br.readLine());

		int aSize = Integer.valueOf(br.readLine()); // A의 모든 누적합을 누하고 해당 누적합을 (누적합 : 누적합의 개수)의 맵에 저장하는 부분
		int numA[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		HashMap<Long, Long> aMap = new HashMap<>();
		for (int i = 0; i < numA.length; i++) {
			long sum = 0;
			for (int j = i; j < numA.length; j++) {
				sum += numA[j];
				aMap.put(sum, aMap.getOrDefault(sum, 0L) + 1);
			}
		}

		int bSize = Integer.valueOf(br.readLine());// B의 모든 누적합을 누하고 해당 누적합을 (누적합 : 누적합의 개수)의 맵에 저장하는 부분
		int numB[] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		HashMap<Long, Long> bMap = new HashMap<>();

		for (int i = 0; i < numB.length; i++) {
			long sum = 0;
			for (int j = i; j < numB.length; j++) {
				sum += numB[j];
				bMap.put(sum, bMap.getOrDefault(sum, 0L) + 1);
			}
		}

		long cnt = 0; // 총 부 배열 쌍의 개수

		for (long valA : aMap.keySet()) { // a의 누적합을 하나씩 꺼내기
			if (bMap.get(target - valA) == null) { // 목표값 - a의 누적합이 b에 없으면 a로는 목표값 생성 불가
				continue;
			}
			cnt = cnt + aMap.get(valA) * bMap.get(target - valA); // a의 누적합 개수와 b의 누적합 개수를 곱하는 것이 a와 b의 조합의 수
		}

		bw.write(cnt + "\n");
		bw.flush();
	}
}