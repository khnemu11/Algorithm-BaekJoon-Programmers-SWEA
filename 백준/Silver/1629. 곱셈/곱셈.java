import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String arg[]) throws IOException {
		//a*b%c = (a%c*b%c)%c
		//a^10 = a^5*a^5 와 같은 지수 법칙을 이용해서 모든 값을 구하지 말고 절반의 값을 구하고 제곱을 하면 연산을 절반으로 줄일 수 있다.
		//이때 지수가 홀수인 경우 위의 나머지 법칙을 이용해서 한번 더 곱해준다.
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long a = Long.valueOf(st.nextToken());
		long b = Long.valueOf(st.nextToken());
		long c = Long.valueOf(st.nextToken());
		long rest = mutiplyRest(a,b,c);
		
		bw.write(String.valueOf(rest));
		br.close();
		bw.close();
	}

	public static long mutiplyRest(long num, long ex, long c) {
		long divideNum = 0;

		if (ex > 1) {
			divideNum = mutiplyRest(num, ex / 2, c); //지수가 2보다 큰 경우 밑을 절반으로 나눈다.
		} else {
			return num % c; //1인 경우 나머지 연산을 한 뒤 리턴해준다. 
		}

		if (ex % 2 == 1) { //홀수인 경우 divideNum*divideNum*num%c를 해주어야 하는데 divideNum*divideNum*num가 long범위를 넘길 수 있으므로 나머지 연산법칙을 이용해서 분할하여 계산한다.
			return (((divideNum * divideNum) % c) * (num % c))%c;
		} else { //짝수인 경우 divideNum*divideNum 은 2^31*2*31(int) < 2*63(long) 보다 작으므로 그냥 곱하고 나머지 연산을 한다.
			return (divideNum * divideNum) % c;
		}
	}
}