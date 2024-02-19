import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    final static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        boolean[] notPrimeNumber = getNotPrimeNumbers();

        for(int testcase=1;testcase<=T;testcase++){
            String[] input = br.readLine().split(" ");

            int start = Integer.parseInt(input[0]);
            int target = Integer.parseInt(input[1]);

            Queue<Prime> q = new LinkedList<>();
            q.add(new Prime(start,0));

            boolean[] visited = new boolean[10000];
            int time = INF;

            while(!q.isEmpty()){
                Prime curr = q.poll();

                if(visited[curr.value]){
                    continue;
                }

                visited[curr.value] = true;

                if(curr.value == target){
                    time = curr.time;
                    break;
                }

                String primeStr = String.valueOf(curr.value);

                for(int i=0;i<primeStr.length();i++){
                    for(int j=0;j<10;j++){
                        char[] primeStrArr = primeStr.toCharArray();
                        primeStrArr[i] = (char)(j + '0');

                        int num = Integer.parseInt(String.valueOf(primeStrArr),10);

                        if(num < 1000){
                            continue;
                        }
                        if(visited[num]){
                            continue;
                        }
                        if(notPrimeNumber[num]){
                            continue;
                        }

                        q.add(new Prime(num,curr.time+1));
                    }
                }
            }

            if(time == INF){
                System.out.println("Impossible");
            }else{
                System.out.println(time);
            }
        }
    }

    public static boolean[] getNotPrimeNumbers(){
        boolean[] notPrimeNum = new boolean[10000];

        for(int i=2;i<notPrimeNum.length;i++){
            if(notPrimeNum[i]){
                continue;
            }

            for(int j=i*2;j < notPrimeNum.length; j+=i){
                notPrimeNum[j] = true;
            }
        }

        return notPrimeNum;
    }
}
class Prime{
    int time;
    int value;

    public Prime(int value, int time){
        this.time = time;
        this.value = value;
    }
}