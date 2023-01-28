import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.valueOf(br.readLine());
		int nums[] = new int[size];
		Map<Integer, Integer> countMap = new HashMap<>();

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < nums.length; i++) {
			nums[i] = Integer.valueOf(st.nextToken());
			countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);
		}

		Stack<Integer> rightStack = new Stack<>();
		Stack<Integer> resultStack = new Stack<>();
		for (int i = nums.length - 1; i >= 0; i--) {
			while (!rightStack.isEmpty()) {
				if (countMap.get(nums[i]) < countMap.get(rightStack.peek())) {
					resultStack.add(rightStack.peek());
					break;
				} else {
					rightStack.pop();
				}
			}
			if (rightStack.isEmpty()) {
				resultStack.add(-1);
			}
			rightStack.add(nums[i]);
		}

		StringBuilder sb = new StringBuilder();

		while (!resultStack.isEmpty()) {
			sb.append(resultStack.pop() + " ");
		}

		System.out.println(sb.deleteCharAt(sb.length() - 1).toString());
	}
}
