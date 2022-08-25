import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
		static int count=0;
		static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		public static void main(String []args) throws Exception{	
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int num =Integer.valueOf(br.readLine());
			int value = (int)Math.pow(2, num)-1;
			bw.append(String.valueOf(value));
			bw.newLine();
			
			draw(num,1,2,3);
			
			bw.flush();
			br.close();
			bw.close();
		}
		public static void draw(int size,int a,int b,int c) throws IOException {
			count++;
    		
			if(size==1) {
				bw.append(a+" "+c);
				bw.newLine();
	    		return;
	    	}
	    	else {
	    		draw(size-1,a,c,b);
	    		
	    		bw.append(a+" "+c);
				bw.newLine();
				
	    		draw(size-1,b,a,c);
	    	}
	    }
}
