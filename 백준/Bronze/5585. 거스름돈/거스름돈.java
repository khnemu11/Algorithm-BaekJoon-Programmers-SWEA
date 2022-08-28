import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
		public static void main(String []args) throws Exception{	
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
			int cost = 1000-Integer.valueOf(br.readLine());
			int rest = cost;
			int chargeCount=0;
			
			chargeCount+=rest/500;
			rest = rest%500;
			chargeCount+=rest/100;
			rest = rest%100;
			chargeCount+=rest/50;
			rest = rest%50;
			chargeCount+=rest/10;
			rest = rest%10;
			chargeCount+=rest/5;
			rest = rest%5;
			chargeCount+=rest/1;
			rest = rest%1;
			
			
		
			bw.write(String.valueOf(chargeCount));
			bw.newLine();
			
			bw.flush();
			bw.close();
			br.close();
		}		
}
