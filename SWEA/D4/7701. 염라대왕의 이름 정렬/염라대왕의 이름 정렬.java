import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.TreeSet;

/*
	풀이 알고리즘
	
	셋을 이용해 중복 제거
	트리셋을 이용해 정렬
 */

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.valueOf(br.readLine());

		for (int testcase = 1; testcase <= T; testcase++) {
			int N = Integer.valueOf(br.readLine());

			TreeSet<Node> set = new TreeSet<>();

			for (int i = 0; i < N; i++) {
				set.add(new Node(br.readLine()));
			}
			bw.write("#" + testcase + "\n");

			for (Node val : set) {
				bw.write(val.val + "\n");
			}
		}

		bw.flush();
	}
}

class Node implements Comparable<Node> {
	String val;

	public Node(String val) {
		this.val = val;
	}

	@Override
	public int compareTo(Node o) {
		if (this.val.length() == o.val.length()) {
			return this.val.compareTo(o.val);
		}

		return this.val.length() - o.val.length();
	}

}