
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String []args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String num = br.readLine();
		long max = 0;
		int count = 0;
		num=num.replaceAll("6", "9");
		for(int i=0;i<10;i++) {
			if(i==6)	continue;
			if(i==9) {
				count = num.length()-num.replace(String.valueOf(i), "").length();
				count=(int) Math.round(count/2.0);
			} 
			else {
				 count = num.length()-num.replace(String.valueOf(i), "").length();
			}
			if(max < count) max = count;
		}
		System.out.println(max);
	}
}
