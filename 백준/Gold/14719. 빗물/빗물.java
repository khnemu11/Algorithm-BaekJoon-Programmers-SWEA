import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int H = Integer.valueOf(input[0]);
        int W = Integer.valueOf(input[1]);

        int[] blocks = Arrays.stream(br.readLine().split(" ")).mapToInt(x->Integer.valueOf(x)).toArray();
        int maxHeight = Arrays.stream(blocks).max().getAsInt();
        int sum = 0;

        for(int h=H;h>0;h--){
            Queue<Integer> q= new LinkedList<>();
            for(int i=0;i<blocks.length;i++){
                if(blocks[i] >= h ){
                    q.add(i);
                }

            }
            if(!q.isEmpty()) {
                int start = q.poll();
                while(!q.isEmpty()){
                    int end = q.poll();
                    sum += end - start -1;
                    start = end;
                }
            }
        }

        bw.write(String.valueOf(sum)+"\n");
        bw.flush();
    }
}
