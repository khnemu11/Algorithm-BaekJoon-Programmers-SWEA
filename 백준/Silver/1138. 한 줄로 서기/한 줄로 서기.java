import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    static int N =0;
    static int[] leftTallerNum;
    static int[] result;
    static int[] heights;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        leftTallerNum = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        result = new int[N];
        heights = new int[N];

        findLine(1);

        String result = Arrays.stream(heights).mapToObj(String::valueOf).collect(Collectors.joining(" ","",""));

        System.out.println(result);
    }
    public static boolean findLine(int height){
        if(height > N){
            for(int target = 0;target < heights.length;target++){
                int tallCount = 0;

                for(int left =0; left<target;left++){
                   if(heights[left] > heights[target]){
                        tallCount++;
                   }
                }

                if(tallCount != leftTallerNum[heights[target]-1]){
                    return false;
                }
            }

            return true;
        }

        for(int i=0;i<heights.length;i++){
            if(heights[i] != 0){
                continue;
            }

            heights[i] = height;

            if(findLine(height+1)){
                return true;
            }

            heights[i] = 0;
        }

        return false;
    }
}