import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static ArrayList<Long> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        setDecreasedNumList(0,9);
        list.sort(Long::compare);

        if(list.size() < N){
            System.out.println(-1);
        }else{
            System.out.println(list.get(N-1));
        }
    }

    public static void setDecreasedNumList(long num, int val){
        if(!list.contains(num)){
            list.add(num);
        }

        if(val < 0){
            return;
        }
        setDecreasedNumList(num*10 + val, val-1);
        setDecreasedNumList(num, val-1);
    }
}
