import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		char[] input = br.readLine().toCharArray();
		boolean zeroExist = false;
		int sum = 0;

		for (int i = 0; i < input.length; i++) {
			if (input[i] == '0') { //10의 배수이려면 끝자리가 0이어야 한다.
				zeroExist = true;
			}
			sum = sum + input[i] - '0'; //3의 배수이면 모든 자리수의 합이 3의 배수여야 한다.
		}
		if (sum % 3 != 0 || !zeroExist) { //3의 배수이면서 10의 배수인지 확인하는 곳
			bw.write("-1");
		}
		else {
			Arrays.sort(input); //작은 순서대로 정렬(char형이라 Collections.reverseOrder사용 불가)
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<input.length;i++) {
				sb.insert(0,input[i]); //큰수를 앞에 붙힌다.
			}
			bw.write(sb.toString());
		}

		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}
