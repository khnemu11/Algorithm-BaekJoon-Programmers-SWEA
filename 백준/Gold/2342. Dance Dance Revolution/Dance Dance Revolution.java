import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    final static int UP = 1;
    final static int LEFT= 2;
    final static int MIDDLE = 0;
    final static int RIGHT = 4;
    final static int DOWN = 3;

    final static int INF = 4 * 100_000 + 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] command = new int[input.length+1];
        System.arraycopy(input,0,command,1,input.length);

        int[][][] dp = new int[command.length-1][5][5];

        for(int i=0;i<dp.length;i++){
            for(int j=0;j<dp[i].length;j++){
                Arrays.fill(dp[i][j],INF);
            }
        }

        dp[0][0][0] = 0;

        for(int time=1;time<command.length-1;time++){
            for(int left = 0; left < 5; left++){
                for(int right = 0; right < 5; right++){
                    if(right == 0){
                        dp[time][left][command[time]] = Math.min(dp[time][left][command[time]],dp[time-1][left][right]+2);
                    }else if(right == command[time]){
                        dp[time][left][command[time]] = Math.min(dp[time][left][command[time]],dp[time-1][left][right]+1);
                    }else if(Math.abs(command[time] - right) == 2){
                        dp[time][left][command[time]] = Math.min(dp[time][left][command[time]],dp[time-1][left][right]+4);
                    }else{
                        dp[time][left][command[time]] = Math.min(dp[time][left][command[time]],dp[time-1][left][right]+3);
                    }
                }
            }
            for(int right = 0; right < 5; right++){
                for(int left = 0; left < 5; left++){
                    if(left == 0){
                        dp[time][command[time]][right] = Math.min(dp[time][command[time]][right],dp[time-1][left][right]+2);
                    }else if(left == command[time]){
                        dp[time][command[time]][right] = Math.min(dp[time][command[time]][right],dp[time-1][left][right]+1);
                    }else if(Math.abs(command[time] - left) == 2){
                        dp[time][command[time]][right] = Math.min(dp[time][command[time]][right],dp[time-1][left][right]+4);
                    }else{
                        dp[time][command[time]][right] = Math.min(dp[time][command[time]][right],dp[time-1][left][right]+3);
                    }
                }
            }

        }

        int min = INF;

        for(int left = 0; left < 5; left++){
            for(int right = 0; right < 5; right++){
                min = Math.min(dp[command.length-2][left][right],min);
            }
        }

        System.out.println(min);
    }
}
