import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);
        int LION = 1;

        int[] dolls = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<Integer> lionList = new ArrayList<>();

        for(int i=0;i<dolls.length;i++){
            if(dolls[i] == LION){
                lionList.add(i);
            }
        }

        if(lionList.size() < K ){
            System.out.println(-1);
        }else{
            int size = Integer.MAX_VALUE;

            for(int i=0;i+K-1 < lionList.size();i++){
                int next = i + K - 1;
                int length = lionList.get(next)- lionList.get(i) + 1;

                size = Math.min(length,size);
            }

            System.out.println(size);
        }
    }
}