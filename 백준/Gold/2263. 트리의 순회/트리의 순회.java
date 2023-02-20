import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 풀이 알고리즘
 * 포스트오더의 맨 끝 -> 각 노드의 부모
 * 인오더의 인덱스 노드의 왼쪽 -> 왼쪽 자식, 인오더의 인덱스 노드의 오른쪽 -> 오른쪽 자식
 * 프리오더의 배열 : 루트, 루트의 왼쪽 자식, 루트의 오른쪽 자식 -->루트, 루트의 왼쪽 자식의 루트, ....(루트의 왼쪽자식의 개수),
 * 													루트의 오른쪽 자식의 루트
*/
public class Main {
	static int preorder[], inorder[], postorder[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.valueOf(br.readLine());
		inorder = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		postorder = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		preorder = new int[N];
		makePreOrder(0, 0, N - 1, 0, N - 1);

		StringBuilder result = new StringBuilder();

		for (int pre : preorder) {
			result.append(pre).append(" ");
		}

		System.out.println(result.deleteCharAt(result.length() - 1).toString());
	}

	public static void makePreOrder(int preidx, int inl, int inr, int postl, int postr) {
		preorder[preidx] = postorder[postr]; // 포스트 오더의 맨 오른쪽 값은 노드의 부모의 값이다.

		if (inl == inr && postl == postr) { // 자식이 없다면
			return;
		}

		int parent = postorder[postr];
		int mid = inl;

		for (int i = inl; i <= inr; i++) { // 인오더에서 부모 노드를 찾아 왼쪽,오른쪽을 나누기 위해 부모노드를 찾는 부분
			if (inorder[i] == parent) {
				mid = i;
				break;
			}
		}
		int leftLength = mid - inl; // 왼쪽 노드의 자식 개수
		int rightLength = inr - mid; // 오른쪽 노드의 자식 개수

		if (leftLength > 0) {
			makePreOrder(preidx + 1, inl, mid - 1, postl, postl + leftLength - 1);
		}
		if (rightLength > 0) {
			makePreOrder(preidx + leftLength + 1, mid + 1, inr, postl + leftLength, postr - 1);
		}
	}
}