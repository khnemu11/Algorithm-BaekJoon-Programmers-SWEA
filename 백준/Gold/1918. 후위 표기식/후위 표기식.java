package defalut;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Stack;

/*
	풀이 알고리즘
	
	알파벳의 순서는 그대로 입력됨
	연산자의 우선순위만 따지면 됨
	
	연산자의 우선순위 : 괄호의 개수 > *,/ > + ,-
	만약 현재 연산자가 가장 최근의 연산자의 우선순위보다 높을경우 연산자 저장
	만약 현재 연산자가 가장 최근의 연산자의 우선순위보다 작거나 같을경우 가장 최근의 연산자가 우선순위가 가장 높다
	-> 바로 최종 식에 해당 연산자 반영
	
	가장 최근의 연산자와 비교해야함 -> 스택으로 구현
	
	문자열을 각 한번씩 방문하므로 O(N)의 시간 복잡도
*/

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] input = br.readLine().split("");
		StringBuilder sBuilder = new StringBuilder();
		Stack<Operator> stack = new Stack<>();

		int blanketCnt = 0;

		for (int i = 0; i < input.length; i++) {
			if (input[i].equals("(")) {	//현재 연산자의 괄호 개수 증가
				blanketCnt++;
				continue;
			} else if (input[i].equals(")")) { //현재 연산자의 괄호 개수 감소
				blanketCnt--;
				continue;
			}

			if (input[i].equals("*") || input[i].equals("/") || input[i].equals("+") || input[i].equals("-")) {	//연산자인 경우
				Operator currOperator = new Operator(input[i], blanketCnt);	
				while (!stack.isEmpty() && stack.peek().compareTo(currOperator) <= 0) { //가장 최근의 연산자가 현재 연산자보다 우선순위가 높거나 같은경우 가장 우선순위가 높은 연산자이다.
					sBuilder.append(stack.pop().op);	//가장 우선순위가 높은 최근 연산자를 정답에 추가
				}
				stack.add(currOperator);	//현재 연산자를 스택에 추가
			} else {
				sBuilder.append(input[i]);	//문자는 그냥 추가
			}
		}
		while (!stack.isEmpty()) {
			sBuilder.append(stack.pop().op);
		}
		bw.write(sBuilder.toString() + "\n");
		bw.flush();
	}
}

class Operator implements Comparable<Operator> {
	String op;
	int blanketCnt;
	int opPriority;

	public Operator(String op, int blanketCnt) {
		this.op = op;
		this.blanketCnt = blanketCnt;

		if (op.equals("*") || op.equals("/")) {	//*,/가 +,- 보다 우선순위가 높다는 것을 표현
			this.opPriority = 1;
		} else {
			this.opPriority = 0;
		}
	}

	@Override
	public int compareTo(Operator o) {
		if (this.blanketCnt == o.blanketCnt) {
			return o.opPriority - this.opPriority;
		}
		return o.blanketCnt - this.blanketCnt;
	}

}
