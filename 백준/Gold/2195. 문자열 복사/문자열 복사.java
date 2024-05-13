import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        String P = br.readLine();

        int count = 0;

        StringBuilder sb = new StringBuilder();

        for(int i=0;i<P.length();i++){
            sb.append(P.charAt(i));

            if(!S.contains(sb.toString())){
                count++;
                sb.setLength(0);
                sb.append(P.charAt(i));
            }
        }

        count++;

        System.out.println(count);
    }
}
