import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
	풀이 알고리즘
	인덱스가 주어지므로 링크드 리스트를 이용
*/

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = 10;
		
		for (int test_case = 1; test_case <= T; test_case++) {
			LinkedList<String> encrypt = new LinkedList<>();
			int length = Integer.valueOf(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < length; i++) {
				encrypt.add(st.nextToken());
			}

			int N = Integer.valueOf(br.readLine());

			st = new StringTokenizer(br.readLine());

			while (st.hasMoreTokens()) {
				String command = st.nextToken();
				int index = Integer.valueOf(st.nextToken());
				int len = Integer.valueOf(st.nextToken());

				String[] inputs = new String[len];

				for (int i = inputs.length - 1; i >= 0; i--) {
					inputs[i] = st.nextToken();
				}

				for (String input : inputs) {
					encrypt.add(index, input);
				}
			}

			StringBuilder sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ");

			for (int i = 0; i < 10; i++) {
				sb.append(encrypt.get(i) + " ");
			}
			bw.write(sb.deleteCharAt(sb.length() - 1).toString());
			bw.newLine();
		}

		bw.flush();
	}
}
