import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int size = Integer.valueOf(br.readLine());
		Stack<Num> stack = new Stack<>();

		long cnt = 0;
		while (size-- > 0) {
			Num next = new Num(Integer.valueOf(br.readLine()), 1);

			while (!stack.isEmpty()) {
				if (stack.peek().val <= next.val) {
					Num curr = stack.pop();
					cnt += curr.cnt;
					if (curr.val == next.val) {
						next.cnt += curr.cnt;
					}
				} else {
					break;
				}
			}

			if (!stack.isEmpty()) {
				cnt++;
			}
			stack.add(next);
		}

		System.out.println(cnt);
	}
}

class Num {
	int val;
	int cnt;

	public Num(int val, int cnt) {
		this.val = val;
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "Num [val=" + val + ", cnt=" + cnt + "]";
	}
}