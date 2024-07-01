import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        boolean valid = true;
        int count = 1;

        while(valid){
            valid = false;

            for(int i=2;i<=Math.sqrt(N);i++){
                if(N % i == 0){
                    count++;
                    N/=i;
                    valid= true;
                    break;
                }
            }
        }
        int digit = 0;

        while(Math.pow(2,digit) < count){
            digit++;
        }

        System.out.println(digit);
    }
}