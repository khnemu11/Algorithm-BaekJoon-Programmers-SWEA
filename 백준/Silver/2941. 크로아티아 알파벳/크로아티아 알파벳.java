import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main { 
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
        String word = br.readLine();

        if(word.contains("c=")){
           word = word.replaceAll("c=", "1");
        }
        if(word.contains("c-")){
            word =word.replaceAll("c-", "1");
        }
        if(word.contains("dz=")){
            word =  word.replaceAll("dz=", "1");
        }
        if(word.contains("d-")){
            word = word.replaceAll("d-", "1");
        }
        if(word.contains("lj")){
            word =  word.replaceAll("lj", "1");
        }
        if(word.contains("nj")){
            word =  word.replaceAll("nj", "1");
        }
        if(word.contains("s=")){
            word =word.replaceAll("s=", "1");
        }
        if(word.contains("z=")){
            word = word.replaceAll("z=", "1");
        }

        System.out.println(word.length());
        br.close();
    } 
}