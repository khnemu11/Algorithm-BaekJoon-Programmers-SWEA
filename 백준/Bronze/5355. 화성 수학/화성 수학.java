import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main { 
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int testNum = Integer.parseInt(br.readLine());

      for(int i=0;i<testNum;i++){
        String [] test = br.readLine().split(" ");
        float result = Float.parseFloat(test[0]);
        for(int j=1;j<test.length;j++){
            if(test[j].equals("@")){
                result = result *3;
            }
            else if(test[j].equals("#")){
                result = result - 7;
            }
            else if(test[j].equals("%")){
                result = result + 5;
            }
        }
        System.out.println(String.format("%.2f", result));
      }
      br.close();
    } 
}