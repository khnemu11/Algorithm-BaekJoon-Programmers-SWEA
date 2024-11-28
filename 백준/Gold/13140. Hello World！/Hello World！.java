import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int D = 0;
    public static int E = 1;
    public static int H = 2;
    public static int L = 3;
    public static int O = 4;
    public static int R = 5;
    public static int W = 6;
    public static int N,hello,world;
    public static boolean[]visited = new boolean[10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        if(findNumber(0,new int[7])){
            System.out.println("  "+hello);
            System.out.println("+ "+world);
            System.out.println("-------");
            System.out.printf("%7d%n",N);
        }else{
            System.out.println("No Answer");
        }
    }
    public static boolean findNumber(int i, int[] selected){
        if(i >= selected.length){
            hello = selected[H] * 10000 + selected[E]*1000+ selected[L]*100 + selected[L]*10 + selected[O];
            world = selected[W] * 10000 + selected[O]*1000+ selected[R]*100 + selected[L]*10 + selected[D];

            if(hello + world == N){
                return true;
            }

            return false;
        }

        for(int num=0;num<10;num++){
            if((i == H || i == W) && num == 0){
                continue;
            }
            if(visited[num]){
                continue;
            }

            selected[i] = num;
            visited[num] = true;

            if(findNumber(i+1,selected)){
                return true;
            }

            visited[num] = false;
        }

        return false;
    }
}