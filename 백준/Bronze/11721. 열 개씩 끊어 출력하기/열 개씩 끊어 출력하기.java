import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String []args) throws IOException{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String word = br.readLine();
		StringBuilder sb = new StringBuilder(word);
			
		if(word.length()%10!=0) {
			for(int i=0;i<10-word.length()%10;i++) {
				sb.append(" ");
			}
		}		
		for(int i=1; i<=sb.length()/10; i++) {
			System.out.println(sb.toString().substring(i*10-10, i*10).trim());
		}
	}
}
