import java.io.*;
import java.util.*;


/*
*
* dp[i] = dp[i-1] + dp[i-2]
*
*
* */
public class Main {
    final static int INF = Integer.MAX_VALUE;
    final static int MOD = 1_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));

        String ciphertext = br.readLine();

        long[] dp = new long[ciphertext.length()];
        boolean valid = true;

        for(int i = 0;i<ciphertext.length();i++){
            if(i == 0){
                int singleText = ciphertext.charAt(i)-'0';
                if(!canDecrpher(singleText)){
                    valid = false;
                    break;
                }else{
                    dp[i] = 1;
                }
            }else if(i==1){
                int singleText = ciphertext.charAt(i)-'0';
                int doubleText = ciphertext.charAt(i-1) == '0' ? INF : Integer.parseInt(ciphertext.substring(i-1,i+1));

                if(!canDecrpher(singleText)&& !canDecrpher(doubleText)){
                    valid=false;
                    break;
                }

                if(canDecrpher(singleText)){
                    dp[i]++;
                }
                if(canDecrpher(doubleText)){
                    dp[i]++;
                }
            }else{
                int singleText = ciphertext.charAt(i)-'0';
                int doubleText = ciphertext.charAt(i-1) == '0' ? INF : Integer.parseInt(ciphertext.substring(i-1,i+1));

                if(!canDecrpher(singleText)&& !canDecrpher(doubleText)){
                    valid=false;
                    break;
                }
                if(canDecrpher(singleText)){
                    dp[i] =(dp[i]+dp[i-1])%MOD;
                }
                if(canDecrpher(doubleText)){
                    dp[i] =(dp[i]+dp[i-2])%MOD;
                }
            }
        }

        if(valid){
            System.out.println(dp[dp.length-1]%MOD);
        }else{
            System.out.println(0);
        }
    }
    public static boolean canDecrpher(int num){
        if(num >=1 && num <=26){
            return true;
        }
        return false;
    }
}

