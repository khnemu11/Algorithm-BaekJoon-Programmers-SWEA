import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String []args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int total = Integer.valueOf(br.readLine());
		int count=0;
		
		for(int i=0;i<total;i++) {
			count=count+Integer.valueOf(br.readLine());
		}
		System.out.println(count-total+1);
	}
}
