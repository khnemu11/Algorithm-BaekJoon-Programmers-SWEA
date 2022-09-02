import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		///a1 = M*x1+r1 a2= = M*x2+r2 라고할때 나머지가 가다면 r2-r1 =0 이므로 a2-a1 = M*(x2-x1)과 같다.
		//모든 입력된 같은 M을 공유하기 때문에 M의 최대값인 최대공약수를 구하고 해당 값의 약수를 구하면 답이 나온다.
		
		int N = Integer.valueOf(br.readLine());
		int [] arr = new int [N];
		
		
		for(int i=0;i<N;i++) {
			arr[i] = Integer.valueOf(br.readLine());
		}
		Arrays.sort(arr);
		
		int totalGcd = arr[1]-arr[0];
		
		for(int i=1;i<N-1;i++) {
			totalGcd = gcd(totalGcd,arr[i+1]-arr[i]);
		}
		
		for(int i=2;i<=totalGcd;i++) {
			if(totalGcd%i==0) {
				bw.write(String.valueOf(i)+" ");
			}
		}
		
		bw.newLine();
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	public static int gcd(int a,int b) {
		if(a%b!=0) {
			return gcd(b,a%b);
		}
		else {
			return b;
		}
	}
}