import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.valueOf(br.readLine());

		int arr[] = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.valueOf(br.readLine());
		}

		int index = 0,push=0,pop=0,num=0;
		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack = new Stack<Integer>();
		List <Integer> result = new ArrayList<>();
		while (true) {
			if(num>=arr[index]) {
				while(!stack.empty()) {
					sb.append("-\n");
					
					int element = stack.pop();
					
					if(arr[index] == element) {
						result.add(element);
						index++;
						break;
					}
				}
			}
			else {
				num++;
				stack.push(num);
				sb.append("+\n");
			}
			if(index == n || (stack.empty() && num>arr[index])) {
				break;
			}
		}
		
		if(index!=n) {
			bw.write("NO");
			bw.newLine();
		}
		else {
			bw.write(sb.toString());
		}
		
		bw.flush();
		br.close();
		bw.close();
	}
}