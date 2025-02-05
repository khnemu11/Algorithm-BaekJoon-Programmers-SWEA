import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        StringBuilder T = new StringBuilder(br.readLine());

        int result = 0;

        Queue<StringBuilder> q = new LinkedList<>();
        q.add(T);

        while(!q.isEmpty()){
            StringBuilder str = q.poll();

            if(str.length() == S.length()){
                if(S.contentEquals(str)){
                    result=1;
                }

                continue;
            }

            if(str.charAt(str.length()-1) == 'A'){
                StringBuilder next = new StringBuilder(str.toString());
                next.deleteCharAt(next.length()-1);
                q.add(next);
            }
            if(str.charAt(0) == 'B'){
                StringBuilder next = new StringBuilder(str.toString());
                next.deleteCharAt(0).reverse();
                q.add(next);
            }
        }

        System.out.println(result);
    }
}
