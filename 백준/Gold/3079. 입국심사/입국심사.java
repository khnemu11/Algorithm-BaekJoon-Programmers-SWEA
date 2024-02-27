import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = input[0];
        int M = input[1];


        int[] processTimes = new int[N];

        for(int i=0;i<N;i++){
            processTimes[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(processTimes);

        long MAX = (long)processTimes[processTimes.length-1] * M;

        long left = 0;
        long right = MAX;
        long time = MAX;

        //해당(중간) 시간으로 모든 친구들을 검사 가능한지 판별
        while(left <= right){
            long mid = (left + right) / 2;

            long count = 0;

            for(int processTime : processTimes){
                count = count + mid / processTime;
                
                if(count >= M){
                    break;
                }
            }

            if(count < M){
                left = mid + 1;
            }else{
                time = Math.min(time,mid);
                right = mid-1;
            }
        }

        System.out.println(time);
    }
}