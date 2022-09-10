import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int[] tmp;
	static int count = 0;
	static int target;

	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int length = Integer.valueOf(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());

		int nums[] = new int[length];

		for (int i = 0; i < length; i++) {
			nums[i] = Integer.valueOf(st.nextToken());
		}

		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < length; i++) {
			while (!stack.isEmpty()) {
				if (nums[i] > nums[stack.peek()]) {
					nums[stack.pop()] = nums[i];
				} else {
					break;
				}
			}
			stack.add(i);
		}
		
		while(!stack.isEmpty()) {
			nums[stack.pop()] = -1;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<nums.length;i++) {
			sb.append(nums[i]);
			sb.append(" ");
		}
		sb.append("\n");
		
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}

}