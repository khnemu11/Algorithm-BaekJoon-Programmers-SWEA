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

		// char 단위로 쪼개서 입력 받기
		char input[] = br.readLine().toCharArray();
		String bombStr = br.readLine();
		Stack<Character> resultStack = new Stack<>();

		for (char word : input) {
			resultStack.add(word);

			if (resultStack.peek() == bombStr.charAt(bombStr.length() - 1)) {
				Stack<Character> tempStack = new Stack<>();
				boolean isValid = true;
				for (int i = bombStr.length() - 1; i >= 0; i--) {
					if (resultStack.isEmpty()) {
						isValid = false;
						break;
					}

					char target = resultStack.pop();
					tempStack.add(target);

					if (target != bombStr.charAt(i)) {
						isValid = false;
						break;
					}
				}

				if (!isValid) {
					while (!tempStack.isEmpty()) {
						resultStack.add(tempStack.pop());
					}
				}
			}
		}

		if (resultStack.isEmpty()) {
			bw.write("FRULA");
		} else {
			StringBuilder sb = new StringBuilder();
			while (!resultStack.isEmpty()) {
				sb.append(resultStack.pop());
			}

			bw.write(sb.reverse().toString());
		}
		bw.newLine();
		bw.flush();
	}
}
