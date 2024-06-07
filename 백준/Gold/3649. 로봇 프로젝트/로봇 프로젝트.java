import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = "";

        while((input = br.readLine())!=null && !input.isEmpty()){
            long x = Integer.parseInt(input) * 1_000_000_000L / 100;
            int n = Integer.parseInt(br.readLine());

            long[] lego = new long[n];

            for(int i=0;i<lego.length;i++){
                lego[i] = Long.parseLong(br.readLine());
            }

            Arrays.sort(lego);

            int[] answer = new int[]{0,0};
            boolean valid = false;
            int l = 0;
            int r = n-1;

            while(l<r){
                long sum = lego[l]+lego[r];

                if(sum == x){
                    valid = true;

                    if(lego[answer[1]] - lego[answer[0]]  <= lego[r] - lego[l]){
                        answer[0] = l;
                        answer[1] = r;
                    }
                }

                if(sum > x){
                    r--;
                }else{
                    l++;
                }
            }

            if(!valid){
                System.out.println("danger");
            }else{
                System.out.println(String.format("yes %d %d",lego[answer[0]],lego[answer[1]]));
            }
        }
    }
}