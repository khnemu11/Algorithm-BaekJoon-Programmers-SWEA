import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        boolean valid = true;
        int count = 1;
        
        //소인수 분해 횟수 구하기
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

        //완전 이진트리의 높이 구하기
        int height = (int)Math.ceil(Math.log(count)/Math.log(2));

        System.out.println(height);
    }
}