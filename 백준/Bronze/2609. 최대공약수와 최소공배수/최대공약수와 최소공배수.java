import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		

		int gcd = gcd(Math.max(n, m),Math.min(n, m));
		int lcm = n*m/gcd;
		
		System.out.println(gcd);
		System.out.println(lcm);
		
		br.close();
	}
	
	public static int gcd(int n, int m) {
		int next = n%m;
		if(next>0) {
			return gcd(m,next);
		}
		else{
			return m;
		}
	}
}
