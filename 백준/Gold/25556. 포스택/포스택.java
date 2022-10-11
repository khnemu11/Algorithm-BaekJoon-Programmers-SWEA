import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		List<Stack<Integer>> stackList = new ArrayList<>();
		boolean possible = true;
		
		for (int i = 0; i < 4; i++) {
			stackList.add(new Stack<Integer>());
		}

		int num = Integer.valueOf(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < num; i++) {
			int curr = Integer.valueOf(st.nextToken());

			for (int j = 0; j <= 4; j++) {
				if(j==4) {
					possible=false;
				}
				else if (stackList.get(j).isEmpty() || stackList.get(j).peek() < curr) {
					stackList.get(j).add(curr);
					break;
				}
			}
			if(!possible) {
				bw.write("NO");
				break;
			}
		}
		
		if(possible) {
			bw.write("YES");
		}

		bw.newLine();
		bw.flush();

		br.close();
		bw.close();
	}
}
