
import java.io.BufferedReader;
import java.io.InputStreamReader;
/*
*   
*   1번 모양 세트)
*    ||  ㅡㅡ  ㅡㅡ
*    ||  ||   ㅡㅡ
*   ㅡㅡ  ||   ㅡㅡ
* 
*   1번 모양의 개수 : 3
*   
*   2번 모양 세트)
* 
*   ㅡㅡㅡㅡ   |ㅡㅡ|
*   |ㅡㅡ|    |ㅡㅡ|
*   |ㅡㅡ|    ㅡㅡㅡㅡ
* 
*   2번 모양의 개수 : 2
* */

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] dp = new long[N+1];
        dp[0] = 1;
        
        //2보다 큰 경우 1번 모양의 초기값 세팅
        if(N >= 2){
            dp[2] = 3;
        }

        for(int n=4;n<dp.length;n+=2){
            dp[n] = dp[n-2] * 3;    //n번째 위치에서 1번 모양을 쓰는 경우
            for(int i=2;n-i*2 >=0; i++){
                dp[n] = dp[n] + dp[n-i*2]*2;    //n번재 위치에서 2번 모양을 i길이만큼 쓰는 경우
           }
        }

        System.out.println(dp[N]);
    }
}