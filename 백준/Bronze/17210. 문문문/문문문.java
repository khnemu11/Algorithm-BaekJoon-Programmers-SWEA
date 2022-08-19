import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String []args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Long n = Long.valueOf(br.readLine());
		int start = Integer.valueOf(br.readLine());
		if(n>5) {
			System.out.println("Love is open door");
		}
		else {
			for(int i=1;i<n;i++) {
				if(start>0) {
					start =  0;
				}
				else {
					start = 1;
				}
				System.out.println(start);
			}
		}
	}
}
