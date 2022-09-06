import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		// 누적합은 시간복잡도가 가장 작은 세그먼트 트리로 해결한다.
		// 이때 세그먼트 트리가 복잡해지는 경우 n^2의 알고리즘으로 푸는게 더 빠를 때가 있다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String word = br.readLine();
		
		int [][]count = new int [26][word.length()]; //각 알파벳 별 출현 개수
		
		count[word.charAt(0)-97][0] = 1; //i-1를 이용하기 위해 0번 인덱스 값 초기화
		
		for(int i=1;i<word.length();i++) { // 각 알파벳의 출현 개수를 dp로 누적합 계산
			for(int j=0;j<26;j++) {
				count[j][i] = count[j][i-1]; //기본적으로 이전 누적합과 동일하고 해당 알파벳이 출현한 인덱스를 추가로 +1 한다.
			}
			count[word.charAt(i)-97][i] ++;
		}
		
		
		int T = Integer.valueOf(br.readLine());
		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char alpha = st.nextToken().charAt(0);
			int start = Integer.valueOf(st.nextToken());
			int end = Integer.valueOf(st.nextToken());
			
			// 0~end의 누적합 - 0~(start-1)의 누적합이 정답이나 start가 0인경우 0~end가 답이다.
			
			if(start ==0) {
				bw.write(String.valueOf(count[alpha-97][end]));
			}
			else {
				bw.write(String.valueOf(count[alpha-97][end]-count[alpha-97][start-1]));
			}
			 
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
