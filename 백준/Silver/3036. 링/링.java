import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
		int N = Integer.valueOf(br.readLine());
		int [] arr = new int [N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<N;i++) {
			arr[i] = Integer.valueOf(st.nextToken());
		}
		StringBuilder sb= new StringBuilder();
		for(int i=1;i<N;i++) {
			int gcd = getGCD(Math.max(arr[0], arr[i]),Math.min(arr[0], arr[i]));
			
			
			sb.append(arr[0]/gcd);
			sb.append("/");
			sb.append(arr[i]/gcd);
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	public static int getGCD(int a,int b) {
		if(a%b == 0) {
			return b;
		}
		else {
			return getGCD(b,a%b);
		}
	}
}