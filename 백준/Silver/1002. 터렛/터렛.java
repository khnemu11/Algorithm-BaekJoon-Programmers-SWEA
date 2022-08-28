import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
		public static void main(String []args) throws Exception{	
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
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
					bw.write("-1");
					bw.newLine();
				}
				else if(joR+baekR< distance){
					bw.write("0");
					bw.newLine();
				}
				else if(joR+baekR==distance|| Math.abs(joR-baekR)==distance){
					bw.write("1");
					bw.newLine();
				}
				else if((Math.abs(joR-baekR) > distance) || (joR+baekR < distance) || (joX==baekX && joY==baekY && baekR!=joR)) {
					bw.write("0");
					bw.newLine();
				}
				else {
					bw.write("2");
					bw.newLine();
				}
				
			}
			
			bw.flush();
			bw.close();
			br.close();
		}		
}
