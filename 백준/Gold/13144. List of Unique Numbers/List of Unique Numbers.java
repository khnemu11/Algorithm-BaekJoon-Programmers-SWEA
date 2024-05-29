import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[]num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Set<Integer> set = new HashSet<>();

        int l = 0;
        int r = 0;
        long count = 0;

        while(r < num.length){
            while(set.contains(num[r])){
                set.remove(num[l]);
                l++;
            }

            set.add(num[r]);

            count = count + r - l + 1;
            r++;
        }

        System.out.println(count);
    }
}