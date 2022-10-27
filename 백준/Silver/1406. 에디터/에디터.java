import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String input = br.readLine();
		LinkedList<Character> list = new LinkedList<>();

		for (int i = 0; i < input.length(); i++) {
			list.add(input.charAt(i));
		}

		int M = Integer.valueOf(br.readLine());
		ListIterator<Character> it = list.listIterator();

		while (it.hasNext()) {
			it.next();
		}

		while (M-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			if (command.equals("L")) {
				if (it.hasPrevious()) {
					it.previous();
				}
			} else if (command.equals("D")) {
				if (it.hasNext()) {
					it.next();
				}
			} else if (command.equals("B")) {
				if (it.hasPrevious()) {
					it.previous();
					it.remove();
				}
			} else if (command.equals("P")) {
				Character word = st.nextToken().charAt(0);
				it.add(word);
			}
		}

		for (char c : list) {
			bw.write(c);
		}

		bw.newLine();
		bw.flush();
		br.close();
		bw.close();
	}

}