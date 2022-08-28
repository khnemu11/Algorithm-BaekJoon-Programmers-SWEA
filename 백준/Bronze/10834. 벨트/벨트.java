import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
		public static void main(String []args) throws Exception{	
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
			int T = Integer.valueOf(br.readLine());
			double beforeRotate=1;
			int clockDirection =-1;
			
			for(int i=0;i<T;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int currRotate = Integer.valueOf(st.nextToken());
				int nextRotate =  Integer.valueOf(st.nextToken());
				int thisClockDirectoin =  Integer.valueOf(st.nextToken());
				
				beforeRotate = beforeRotate*nextRotate/currRotate;
				if(clockDirection==-1) {
					clockDirection=thisClockDirectoin;
				}
				else if(thisClockDirectoin==1) {
					if(clockDirection==0) {
						clockDirection=1;
					}
					else {
						clockDirection=0;
					}
				}
			}
			bw.write(String.valueOf(clockDirection));
			bw.write(" ");
			bw.write(String.valueOf((int)beforeRotate));;
			bw.newLine();
			
			bw.flush();
			bw.close();
			br.close();
		}		
}
