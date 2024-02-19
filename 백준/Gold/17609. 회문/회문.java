import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int testcase=1;testcase<=T;testcase++){
            String str = br.readLine();

            if(str.equals(new StringBuilder(str).reverse().toString())){
                System.out.println(0);
                continue;
            }

            int l = 0;
            int r = str.length()-1;
            boolean isSimilarPallindrome = true;


            while(l < r){
                if(str.charAt(l) != str.charAt(r)){
                    StringBuilder left = new StringBuilder(str);
                    StringBuilder right = new StringBuilder(str);

                    left.deleteCharAt(l);
                    right.deleteCharAt(r);

                    if(!left.toString().equals(left.reverse().toString()) && !right.toString().equals(right.reverse().toString())){
                        isSimilarPallindrome = false;
                    }
                    break;
                }

                l++;
                r--;
            }

            System.out.println(isSimilarPallindrome ? 1 : 2);
        }
    }
}