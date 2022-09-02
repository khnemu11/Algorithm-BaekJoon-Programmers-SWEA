import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//0으로 끝나는 수 -> 10 =2*5 -> n!에서 1~까지 2와 5가 몇번 나왔는지 확인하면 0의 개수 파악 가능
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		
		int power_2 = divider(n,2) - divider(n-m,2) - divider(m,2);
		int power_5 = divider(n,5) - divider(n-m,5) - divider(m,5);
		
		bw.write(String.valueOf(Math.min(power_2, power_5)));
		bw.flush();
		br.close();
		bw.close();
	}
	public static int divider (int num, int square) {
		int count=0;
		
		while(num>1) {
			count=count+num/square;
			num=num/square;
		}
		
		return count;
	}
}