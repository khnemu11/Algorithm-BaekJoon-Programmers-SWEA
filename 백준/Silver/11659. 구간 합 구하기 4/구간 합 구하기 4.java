import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] tree;

	public static void main(String[] args) throws Exception {
		//누적합은 시간복잡도가 가장 작은 세그먼트 트리로 해결한다.
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int length = Integer.valueOf(st.nextToken());
		int range = Integer.valueOf(st.nextToken());

		int num[] = new int[length + 1];

		st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= length; i++) {
			num[i] = Integer.valueOf(st.nextToken());
		}

		//세그먼트 트리의 높이와 노드의 개수를 구하는 과정
		double height = Math.ceil((Math.log(length) / Math.log(2))) + 1;
		int count = (int) Math.round(Math.pow(2, height));

		tree = new int[count + 1];

		//세그먼트 트리 생성 과정
		init(num, 1, 1, length);
		
		for(int i=0;i<range;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.valueOf(st.nextToken());
			int end = Integer.valueOf(st.nextToken());
			
			bw.write(String.valueOf(sum(1, 1, length,start,end)));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
	//세그먼트 트리 생성 메소드 arr: 숫자 배열, node 현재 트리의 인덱스, left 현재 노드의 숫자 범위 시작, right : 현재 노드의 숫자범위 끝
	public static int init(int[] arr, int node, int left, int right) {
		if (left == right) {//맨 끝의 노드인경우 해당 위치의 배열의 값을 넣는다.
			tree[node] = arr[left]; 
			return tree[node];
		} else { //만약 맨 끝 노드가 아닌 경우 절반으로 나누어 다음 노드들의 2개의 합으로 구한다.
			tree[node] = init(arr, node * 2, left, (left + right) / 2)
					+ init(arr, node * 2 + 1, (left + right) / 2 + 1, right);

			return tree[node];
		}
	}
	//세그먼트 트리 구간 합 메소드 node 현재 트리의 인덱스, currLeft 현재 노드의 숫자 범위 시작, currRight : 현재 노드의 숫자범위 끝 , finalLeft: 최종 구간합의 시작, finalRight: 최종 구간합의 끝
	public static int sum(int node, int currLeft, int currRight, int finalLeft, int finalRight) {
		if(currRight < finalLeft || currLeft > finalRight) {
			return 0;
		}
		else if (currLeft >= finalLeft && currRight <= finalRight) {
			return tree[node];
		}
		else {
			return sum(node * 2, currLeft, (currLeft + currRight) / 2,finalLeft,finalRight)
					+ sum(node * 2 + 1, (currLeft + currRight) / 2 + 1,currRight,finalLeft,finalRight);
		}
	}
}
