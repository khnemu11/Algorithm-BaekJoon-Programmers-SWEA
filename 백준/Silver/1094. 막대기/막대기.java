import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int length = Integer.valueOf(br.readLine());
		
		Stack<Integer> stack = new Stack<>();
		stack.add(64);
		int sum = 64;
		
		while(sum>length) {
			int curr = stack.pop()/2;
			stack.add(curr);
			stack.add(curr);
			if(sum-curr>=length) {
				sum-=curr;
				stack.pop();
			}
//			System.out.println(stack.toString()+" sum : "+sum);
		}
//		System.out.println(stack.toString()+" sum : "+sum);
		bw.write(String.valueOf(stack.size()));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}
