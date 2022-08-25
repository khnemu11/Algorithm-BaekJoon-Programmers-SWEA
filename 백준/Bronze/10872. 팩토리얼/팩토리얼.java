import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
		public static void main(String []args) throws Exception{	
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			int num = Integer.valueOf(br.readLine());
			
			Factorical factorial = new Factorical();
			
			System.out.println(factorial.getFactorical(num));
		
		}
}
class Factorical {
    public int getFactorical(int num) {
        if(num==0 || num==1) {
        	return 1;
        }
        else {
        	return num * getFactorical(num-1);
        }
    	
        
    }
}