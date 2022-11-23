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

		String input = br.readLine();
		StringBuilder sb = new StringBuilder();
		Stack<Character> operator = new Stack<>();

		HashMap<Character, Integer> operatorPriority = new HashMap<>();
		operatorPriority.put('+', 1);
		operatorPriority.put('-', 1);
		operatorPriority.put('*', 2);
		operatorPriority.put('/', 2);
		operatorPriority.put('(', 0);
		operatorPriority.put(')', 0);

		// 연산자인경우
		// 우선순위가 높으면 스택에 추가
		// 아니면 연산자 모든 연산자 스택을 결과에 추가
		// )인 경우 (가 나올때까지 결과에 추가
		for (int i = 0; i < input.length(); i++) {
			Character curr = input.charAt(i);

			if (operatorPriority.get(curr) != null) {
				if (curr == '(') {
					operator.add(curr);
				} else if (curr == ')') {
					while (operator.peek() != '(') {
						sb.append(operator.pop());
					}
					operator.pop();
				} else if (!operator.isEmpty() && operatorPriority.get(operator.peek()) >= operatorPriority.get(curr)) {
					while (!operator.isEmpty() && operatorPriority.get(operator.peek()) >= operatorPriority.get(curr)) {
						sb.append(operator.pop());
					}
					operator.add(curr);
				} else {
					operator.add(curr);
				}
			} else {
				sb.append(curr);
			}

//			System.out.println(operator.toString());
//			System.out.println(sb.toString());
		}

		while (!operator.isEmpty()) {
			sb.append(operator.pop());
		}

		bw.write(sb.toString());
		br.close();
		bw.close();
	}
}
