import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String []args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String number = br.readLine();
		
		String[]numberArray = number.split(" ");
		Double profit = 0.0;
		for(String num : numberArray) {
			profit = profit+ Math.pow(Double.valueOf(num), 2);
		}
		System.out.println((int) (profit%10));
	}
}
