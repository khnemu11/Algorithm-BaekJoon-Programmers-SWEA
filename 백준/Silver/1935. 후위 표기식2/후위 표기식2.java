import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int size = Integer.valueOf(br.readLine());
		char formula[] = br.readLine().toCharArray();
		Stack<Double> stack = new Stack<>();		
		HashMap<Character,Double> alphaMap = new HashMap<>();
		
		char key = 'A';
		
		for(int i=0;i<size;i++) {
			alphaMap.put(key, Double.valueOf(br.readLine()));
			key++;
		}
		
		for (int i = 0; i < formula.length; i++) {
			if (formula[i] == '*') {
				double b = stack.pop();
				double a = stack.pop();

				stack.push(a * b);
			} else if (formula[i] == '/') {
				double b = stack.pop();
				double a = stack.pop();

				stack.push(a / b);
			} else if (formula[i] == '+') {
				double b = stack.pop();
				double a = stack.pop();

				stack.push(a + b);
			} else if (formula[i] == '-') {
				double b = stack.pop();
				double a = stack.pop();

				stack.push(a - b);
			} else {
				stack.push(alphaMap.get(formula[i]));
			}
		}

		bw.write(String.format("%.2f",stack.pop()));
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}