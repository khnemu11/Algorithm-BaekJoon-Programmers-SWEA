import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<String> list = new ArrayList<>();

        for(int i=0;i<N;i++){
            list.add(br.readLine());
        }

        String S = "";
        String T = "";
        int max = -1;

        for(int i=0;i< list.size();i++){
            String a = list.get(i);
            for(int j=i+1;j<list.size();j++){
                String b = list.get(j);
                int length = 0;

                for(int k=0;k<a.length() && k<b.length();k++){
                    if(a.charAt(k) != b.charAt(k)){
                        break;
                    }

                    length++;
                }

                if(max < length){
                    S = a;
                    T = b;
                    max = length;
                }
            }
        }

        System.out.println(S);
        System.out.println(T);
    }
}