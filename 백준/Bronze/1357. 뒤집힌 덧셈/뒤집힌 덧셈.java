import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		String a = sc.next();
		String b = sc.next();
		int sum = Integer.valueOf(rev(a)) + Integer.valueOf(rev(b));
		System.out.println(rev(String.valueOf(sum)));

	}

	public static String rev(String num) {
		StringBuilder sb = new StringBuilder(num);
		sb = sb.reverse();
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < sb.length(); i++) {
			if (sb.charAt(i) == '0') {
				continue;
			} else {
				result.append(sb.substring(i, sb.length()));
				break;
			}
		}
		return result.toString();
	}
}
