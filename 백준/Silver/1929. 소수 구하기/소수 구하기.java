import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
	
	public class Main {
		public static void main(String []args) throws IOException{	
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.valueOf(st.nextToken());
			int N = Integer.valueOf(st.nextToken());
			
			boolean [] prime = new boolean[N+1];
			
			prime[0]=true;
			prime[1]=true;
			
			for(int i=2;i<=N;i++) {
				if(prime[i]==false) {
					for(int j=i+i;j<=N;j=j+i) {
						prime[j]=true;
					}
				}
			}
			
			for(int i=M;i<=N;i++) {
				if(!prime[i]) {
					System.out.println(i);
				}
			}
			
			br.close();
		}
	}