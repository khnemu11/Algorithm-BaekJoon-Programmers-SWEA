import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
		public static void main(String []args) throws Exception{	
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int A = Integer.valueOf(st.nextToken());
			int B = Integer.valueOf(st.nextToken());
			
			int [] arr = new int [B];
			
			int count=1;
			for(int i=0;i<B;) {
				for(int j=0;j<count && i<B;j++,i++) {
					arr[i]=count;
				}
				count++;
			}
			count=0;
			for(int i=A-1;i<B;i++) {
				count+=arr[i];
			}
			
			bw.write(String.valueOf(count));
			bw.newLine();
			
			bw.flush();
			bw.close();
			br.close();
		}		
}
