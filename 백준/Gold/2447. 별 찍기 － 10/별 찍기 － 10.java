import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

		public static void main(String []args) throws Exception{	
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
			int num =Integer.valueOf(br.readLine());
			char[][] picture = new char[num][num];
			
			for(int i=0;i<num;i++) {
				for(int j=0;j<num;j++) {
					picture[i][j]=' ';
				}
			}
			
			draw(0,0,num,picture);
			for(int i=0;i<num;i++) {
				for(int j=0;j<num;j++) {
					bw.append(picture[i][j]);
				}
				bw.newLine();
			}
			bw.flush();
			bw.close();
			br.close();
		}
		public static void draw(int x,int y,int size,char[][]picture) {
	    	if(size==1) {
	    		picture[x][y]='*';
	    		return;
	    	}
	    	else {
	    		size=size/3;
	    		
	    		draw(x,y,size,picture);
	    		draw(x+size,y,size,picture);
	    		draw(x+size*2,y,size,picture);
	    		
	    		draw(x,y+size,size,picture);
	    		draw(x+size*2,y+size,size,picture);
	    		
	    		draw(x,y+size*2,size,picture);
	    		draw(x+size,y+size*2,size,picture);
	    		draw(x+size*2,y+size*2,size,picture);
	    	}
	    }
}
