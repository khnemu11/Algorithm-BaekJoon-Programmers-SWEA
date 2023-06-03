import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 *	풀이 과정
 *	
 *	두 집합 간 요소가 있는지 확인 -> 해시 셋을 활용
 *
 * */

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			Set<Integer> note1 = new HashSet<>();

			int note1Length = Integer.parseInt(br.readLine());

			StringTokenizer st = new StringTokenizer(br.readLine());

			while (st.hasMoreTokens()) {
				note1.add(Integer.valueOf(st.nextToken()));
			}

			int note2Length = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());

			while (st.hasMoreTokens()) {
				int val = Integer.valueOf(st.nextToken());
				
				if(note1.contains(val)) {
					bw.write("1\n");
				}else {
					bw.write("0\n");
				}
			}
		}
		bw.close();
	}
}