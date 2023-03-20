import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 
 * 풀이 알고리즘
 * 
 * 정해진 한도 이내로 얻을 수 있는 최대값 -> 냅색 문제
 * 현재 가능한 볼륨값 = (이전 가능한 볼륨값 + 현재 볼륨) or (이전 가능한 볼륨값 - 현재 볼륨) 
 * 0<= 가능한 볼륨값 <= M(볼륨 최대값)
 * 
 * 위의 점화식에 따라 dp 배열 생성
 * */

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken());
		int S = Integer.valueOf(st.nextToken());
		int M = Integer.valueOf(st.nextToken());

		int volumns[] = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i < volumns.length; i++) { //1번째의 볼륨부터 넣어 0번재의 볼륨(S)을 표현하기 편하게 함
			volumns[i] = Integer.valueOf(st.nextToken());
		}

		boolean dp[][] = new boolean[N + 1][M + 1];	//가능한 볼륨은 0~M이며 0번째(초기)~N번째의 볼륨이 있으므로 [N+1][M+1]크기로 배열 설정

		dp[0][S] = true; //초기 볼륨값 

		for (int volumnIdx = 1; volumnIdx < volumns.length; volumnIdx++) {	//첫번째 볼륨부터 N번째 볼륨까지 가능한 볼륨을 탐색
			int volumn = volumns[volumnIdx];	//현재 줄이거나 늘릴 수 있는 볼륨 값
			for (int i = 0; i < dp[0].length; i++) {	
				if (i - volumn >= 0 && dp[volumnIdx - 1][i]) { //이전 볼륨이 존재하면 이전 볼륨에서 볼륨을 줄일 수 있다. 
					dp[volumnIdx][i - volumn] = true;
				}
				if (i + volumn <= M && dp[volumnIdx - 1][i]) {//이전 볼륨이 존재하면 이전 볼륨에서 볼륨을 늘릴 수 있다. 
					dp[volumnIdx][i + volumn] = true;
				}
			}
		}

		int max = -1;	//값이 존재하지 않으면 -1

		for (int i = M; i >= 0; i--) {	//가장 큰 것부터  존재하는 볼륨 탐색
			if (dp[N][i]) {
				max = i;
				break;
			}
		}
		bw.write(max + "\n");
		bw.newLine();
		bw.flush();
		bw.close();
	}

}
