import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));

        int N = Integer.parseInt(br.readLine());

        boolean[] notPrime = new boolean[N*10];
        List<Integer> primeList = new ArrayList<>();

        for(int i=2; i<notPrime.length; i++){
            if(notPrime[i]){
                continue;
            }
            if(i>=N){
                primeList.add(i);
            }
            
            for(int j=i*2; j<notPrime.length;j+=i){
                notPrime[j] = true;
            }
        }

        int min = Integer.MAX_VALUE;

        for(int prime : primeList){
            String primeStr = String.valueOf(prime);

            int l = 0;
            int r = primeStr.length()-1;
            boolean pallindrome = true;

            while(l < r){
                if(primeStr.charAt(l) != primeStr.charAt(r)){
                    pallindrome = false;
                    break;
                }
                l++;
                r--;
            }

            if(pallindrome){
                min = Math.min(prime,min);
            }
        }

        System.out.println(min);
    }
}
