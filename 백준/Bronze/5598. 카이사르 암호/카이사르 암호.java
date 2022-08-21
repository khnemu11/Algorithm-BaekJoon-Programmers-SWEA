import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
	
	public class Main {
		public static void main(String []args) throws IOException{	
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			char[]words = br.readLine().toCharArray();
			
			for(int i=0;i<words.length;i++) {
				if(words[i]-3>=65) {
					System.out.print((char)(words[i]-3));
				}
				else {
					System.out.print((char)(words[i]-3+26));
				}
			}
			
			br.close();
		}
	}
