import java.math.BigInteger;
import java.util.*;
import java.io.*;
/*
*
* 1 = 1
* 2 = 3
* 3 = 7
* 4 = 15
* 5 = 31
* 6 = 63
* */
public class Main {
    static List<Move> route = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));

        int N = Integer.parseInt(br.readLine());

        BigInteger count = new BigInteger("1");

        for(int i=2;i<=N;i++){
            count = count.multiply(new BigInteger("2")).add(new BigInteger("1"));
        }

        System.out.println(count.toString());

        if(N <=20){
            setHanoiRoute(1,3,2,N);
            for(Move m : route){
                System.out.println(m.start+" "+m.end);
            }
        }
    }

    public static void setHanoiRoute(int start, int end, int mid, int depth){
        if(depth == 1){
            route.add(new Move(start,end));
        }else{
            setHanoiRoute(start,mid,end,depth-1);
            route.add(new Move(start,end));
            setHanoiRoute(mid,end,start,depth-1);
        }
    }
}
class Move{
    int start;
    int end;

    public Move(int start, int end){
        this.start = start;
        this.end = end;
    }
}