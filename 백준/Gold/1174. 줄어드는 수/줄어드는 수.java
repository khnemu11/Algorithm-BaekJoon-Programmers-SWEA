import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static ArrayList<Long> list = new ArrayList<>();
    static int[] arr = {9,8,7,6,5,4,3,2,1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        setDecreasedNumList(0,0);
        list.sort(null);

        try{
            System.out.println(list.get(N-1));
        }catch (Exception e){
            System.out.println(-1);
        }
    }

    public static void setDecreasedNumList(long num, int idx){
        if(!list.contains(num)){
            list.add(num);
        }

        if(idx >= arr.length){
            return;
        }

        setDecreasedNumList(num*10 + arr[idx], idx+1);
        setDecreasedNumList(num, idx+1);
    }
}
