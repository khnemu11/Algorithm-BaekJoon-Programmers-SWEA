import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
       String input = "";
       
       while(!(input = br.readLine()).equals("end")) {
    	   int oCount = 0;
    	   int xCount = 0;
    	   
    	   for(int i=0;i<input.length();i++) {
    		   if(input.charAt(i) == 'X') {
    			   xCount++;
    		   }else if(input.charAt(i) == 'O') {
    			   oCount++;
    		   }
    	   }
    	   
    	   if(oCount > xCount || xCount-oCount > 2) {
    		   System.out.println("invalid");
    		   continue;
    	   }
    	   
    	   boolean xFinish = finish(input,'X');
    	   boolean oFinish = finish(input,'O');
    	   
    	   if(oCount + xCount == 9 && xCount == oCount+1) {
    		   if(xFinish && oFinish) {
    			   System.out.println("invalid");
    		   }else if(oFinish) {
    			   System.out.println("invalid");
    		   }else {
    			   System.out.println("valid");
    		   }
    	   }else {
    		   if(xFinish && oFinish) {
    			   System.out.println("invalid");
    		   }else if(xFinish && xCount == oCount+1) {
    			   System.out.println("valid");
    		   }else if(oFinish && oCount == xCount) {
    			   System.out.println("valid");
    		   }else {
    			   System.out.println("invalid");
    		   }
    	   }
        }
	}
	public static boolean finish(String tictacto,char target) {
		//가로 확인
		for(int i=0;i<9;i+=3) {
			if(tictacto.charAt(i) == target && tictacto.charAt(i) == tictacto.charAt(i+1) && tictacto.charAt(i) ==tictacto.charAt(i+2)) {
				return true;
			}
		}
		//세로 확인
		for(int i=0;i<3;i++) {
			if(tictacto.charAt(i) == target && tictacto.charAt(i) == tictacto.charAt(i+3) && tictacto.charAt(i) ==tictacto.charAt(i+6)) {
				return true;
			}
		}
		//대각확인
		if(tictacto.charAt(4) == target && tictacto.charAt(4) == tictacto.charAt(2) && tictacto.charAt(4) ==tictacto.charAt(6)) {
			return true;
		}
		if(tictacto.charAt(4) == target && tictacto.charAt(4) == tictacto.charAt(0) && tictacto.charAt(4) ==tictacto.charAt(8)) {
			return true;
		}
		return false;
	}
}