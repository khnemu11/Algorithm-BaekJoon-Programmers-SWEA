import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
		public static void main(String []args) throws Exception{	
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int N = Integer.valueOf(br.readLine());
			
			for(int i=0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int joX = Integer.valueOf(st.nextToken());
				int joY = Integer.valueOf(st.nextToken());
				int joR = Integer.valueOf(st.nextToken());
				int baekX = Integer.valueOf(st.nextToken());
				int baekY = Integer.valueOf(st.nextToken());
				int baekR = Integer.valueOf(st.nextToken());
				double distance = Math.sqrt(Math.pow(baekX-joX,2) + Math.pow(baekY-joY,2));
				
				if(joX==baekX && joY==baekY && baekR==joR){
					System.out.println(-1);
				}
				else if(joR+baekR< distance){
					System.out.println(0);
				}
				else if(joR+baekR==distance|| Math.abs(joR-baekR)==distance){
					System.out.println(1);
				}
				else if((Math.abs(joR-baekR) > distance) || (joR+baekR < distance) || (joX==baekX && joY==baekY && baekR!=joR)) {
					System.out.println(0);
				}
				else {
					System.out.println(2);
				}
				
			}
			
			br.close();
		}		
}
