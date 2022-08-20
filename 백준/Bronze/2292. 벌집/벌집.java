import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String []args) throws IOException{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int input = Integer.valueOf(br.readLine());
		int count=1;
		int totalRoom=1;
		while(totalRoom<input) {
			count++;
			totalRoom=totalRoom+6*(count-1);
		}
		System.out.println(count);
		
	}
}
