import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		Fraction a = new Fraction(Long.valueOf(st.nextToken()), Long.valueOf(st.nextToken()));
		st = new StringTokenizer(br.readLine());
		Fraction b = new Fraction(Long.valueOf(st.nextToken()), Long.valueOf(st.nextToken()));
		Fraction result = new Fraction();

		result.dinominator = a.dinominator * b.dinominator;
		result.numerator = result.dinominator / a.dinominator * a.numerator
				+ result.dinominator / b.dinominator * b.numerator;

		long gcd = gcd(Math.max(result.dinominator, result.numerator), Math.min(result.dinominator, result.numerator));

		result.dinominator /= gcd;
		result.numerator /= gcd;
		
		StringBuilder sb = new StringBuilder();
		sb.append(result.numerator);
		sb.append(" ");
		sb.append(result.dinominator);

		bw.write(sb.toString());
		bw.newLine();

		br.close();
		bw.close();
	}

	public static long lcm(long a, long b) {
		return a * b / gcd(a, b);
	}

	public static long gcd(long a, long b) {
		if (a % b == 0) {
			return b;
		} else {
			return gcd(b, a % b);
		}
	}
}

class Fraction {
	long numerator;
	long dinominator;

	public Fraction() {
	};

	public Fraction(long numerator, long dinominator) {
		this.numerator = numerator;
		this.dinominator = dinominator;
	}
}