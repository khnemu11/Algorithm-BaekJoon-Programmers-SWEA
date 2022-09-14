import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	static int count0[];
	static int count1[];
	static int fibonaccis[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.valueOf(br.readLine());
		
		
		for(int i=0;i<n;i++) {	
			int size= Integer.valueOf(br.readLine());
			count0 = new int [size+1];
			count1 = new int [size+1];
			fibonaccis = new int [size+1];
			
			fibonacci(size);
			
			bw.write(String.valueOf(count0[size])+" "+String.valueOf(count1[size]));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}

	public static int fibonacci(int n) {

		if(fibonaccis[n]!=0) {
			return fibonaccis[n];
		}
		if(n==0) {
			fibonaccis[n]=0;
			count0[n] = 1;
			count1[n] = 0;
			return 0;
		}
		else if (n == 1) {
			fibonaccis[n]=1;
			count1[n] = 1;
			return 1;
		} else {
			fibonaccis[n] = fibonacci(n - 1) + fibonacci(n - 2);
			count0[n] = count0[n-1]+count0[n-2];
			count1[n] = count1[n-1]+count1[n-2];
			
			return fibonaccis[n];
		}
	}
}
