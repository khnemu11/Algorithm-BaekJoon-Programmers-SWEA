import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int fibo[];
	static int countRe=0;
	static int countDp=0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.valueOf(br.readLine());
		
		fibo = new int[N+1];
		fibo[1]=fibo[2]=1;
		
		recursiveFibo(N);
		dpFibo(N);
		bw.write(String.valueOf(countRe));
		bw.write(" ");
		bw.write(String.valueOf(countDp));
		bw.newLine();
		
		bw.flush();
		bw.close();
		br.close();
	
	}
	
	public static int recursiveFibo(int n) {
		if(n==1 || n==2) {
			countRe++;
			return 1;
		}
		else {
			
			return recursiveFibo(n-1)+recursiveFibo(n-2);
		}
	}
	public static int dpFibo(int n) {
		for(int i=3;i<=n;i++) {
			countDp++;
			fibo[i]=fibo[i-2]+fibo[i-1];
		}
		
		return fibo[n];
	}
}
