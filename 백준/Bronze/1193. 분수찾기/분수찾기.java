import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String []args) throws IOException{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int input = Integer.valueOf(br.readLine());
		int zigzag=1,top=1,under=1,count=0;
		
		while(true) {
			if(zigzag%2!=0) {
				for(int j=0;j<zigzag;j++) {
					if(count==input)	break;
					top=zigzag-j;
					under=j+1;
					count++;
				}
				zigzag++;
			}
			else {
				for(int j=0;j<zigzag;j++) {
					if(count==input)	break;
					under=zigzag-j;
					top=j+1;
					count++;
				}
				zigzag++;
			}
			if(count==input) break;
		}
		System.out.println(top+"/"+under);
	}
}
