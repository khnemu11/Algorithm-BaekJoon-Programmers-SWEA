import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
		public static void main(String []args) throws Exception{	
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
			
			int N = Integer.valueOf(br.readLine());
			int result=0;
			for(int i=1;i<=N;i++) {
				int curr=i;
				int	digit=i;
				while(digit>0) {
					curr+=digit%10;
					digit=digit/10;
				}
				if(curr==N) {
					result=i;
					break;
				}	
			}
			
			bw.write(String.valueOf(result));
			bw.close();
			br.close();
			
		}		
}