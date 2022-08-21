import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
	
	public class Main {
		public static void main(String []args) throws IOException{	
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int num = Integer.valueOf(br.readLine());
			int divisor = 2;
			
			while(num>1) {
				for(int i=divisor;i<=num;i++) {
					if(num%i == 0) {
						divisor=i;
						break;
					}
				}
				System.out.println(divisor);
				num=num/divisor;
			}
			br.close();
		}
	}
