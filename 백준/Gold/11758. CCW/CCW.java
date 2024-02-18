import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int x1 = Integer.parseInt(input[0]);
        int y1 = Integer.parseInt(input[1]);

        input = br.readLine().split(" ");
        int x2 = Integer.parseInt(input[0]);
        int y2 = Integer.parseInt(input[1]);

        input = br.readLine().split(" ");
        int x3 = Integer.parseInt(input[0]);
        int y3 = Integer.parseInt(input[1]);

        int p1p2x = x2-x1;
        int p2p2y = y2-y1;
        int p3p2x = x3-x2;
        int p3p2y = y3-y2;

        int comp = p1p2x*p3p2y - p2p2y*p3p2x;

        if(comp > 0){
            System.out.println(1);
        }else if(comp == 0){
            System.out.println(0);
        }else{
            System.out.println(-1);
        }
    }
}