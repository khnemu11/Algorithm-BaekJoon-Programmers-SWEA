import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		int order =0;
		int num =1;
		while(order<n) {
			if(String.valueOf(num).contains("666")) {
				order++;
			}
			if(order!=n)	num++;
			
		}
		
		System.out.println(num);
			
		br.close();
	}
}