import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
		public static void main(String []args) throws Exception{	
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
			int T = Integer.valueOf(br.readLine());
			
			for(int i=0;i<T;i++) {
				String num = br.readLine();
				String sum = String.valueOf(Integer.valueOf(num)+Integer.valueOf(new StringBuilder(num).reverse().toString()));
				String answer = "NO";
				int l=0,r=sum.length()-1;
				while(true) {
					if(l>=r) {
						answer="YES";
						break;
					}
					else if(sum.charAt(l)!=sum.charAt(r)) {
						break;
					}
					l++;
					r--;
				}
				
				bw.write(answer);
				bw.newLine();
			}
			
			bw.flush();
			bw.close();
			br.close();
		}		
}
