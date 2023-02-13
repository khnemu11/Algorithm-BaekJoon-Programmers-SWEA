import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

/*
	풀이 알고리즘
	큐로 푸는것이 정석이나 링크드 리스트로 해결
	노드를 만들어서 링크드리스트 구현
*/

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		int N = Integer.valueOf(input[0]);
		int K = Integer.valueOf(input[1]);

		Node root = new Node();
		Node curr = root;

		for (int i = 1; i <= N; i++) {
			curr.next = new Node(i);
			curr = curr.next;
		}

		Node prev = root.next;
		curr.next = root.next;

		int idx = 0;
		int nums[] = new int[N];

		while (idx < N) {
			for (int move = 0; move < K; move++) {
				prev = curr;
				curr = curr.next; //노드 이동
			}
			nums[idx] = curr.val; 
			prev.next = curr.next; //현재 노드 삭제 
			idx++;
		}

		bw.write(Arrays.stream(nums).mapToObj(x -> String.valueOf(x)).collect(Collectors.joining(", ", "<", ">"))); //joining을 이용한 문자열 만들기
		bw.flush();
	}
}

class Node {
	int val;
	Node next;

	public Node() {
	}

	public Node(int val) {
		this.val = val;
	}
}
