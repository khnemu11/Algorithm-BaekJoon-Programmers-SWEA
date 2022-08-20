import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String []args) throws IOException{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.valueOf(br.readLine());
		int fiveCount=0;
		int threeCount=0;
		int count=-1;
		

		for(int i=N/5;i>=0;i--) {
			fiveCount =i;
			threeCount = (N-5*fiveCount)/3;
				
			if(5*fiveCount + 3*threeCount ==N) {
				count = fiveCount+threeCount;
				break;
			}
		}

		System.out.println(count);

	}
}
