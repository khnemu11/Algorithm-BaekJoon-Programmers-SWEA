import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] soldier = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] select = new int[N];
        int l = 0;
        int r = 0;
        select[0] = soldier[0];

        for(int i=1;i<soldier.length;i++){
            if(select[r] > soldier[i]){
                r++;
                select[r] = soldier[i];
            }else{
                for(int j=l;j<=r;j++){
                    if(select[j] <= soldier[i]){
                        select[j] = soldier[i];
                        break;
                    }
                }
            }
        }

        System.out.println(N-r-1);
    }
}