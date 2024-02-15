import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
/*
*  0 0 0
*  1 0 0
*
*  i + 1, i+2
*
*
*
* */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        char[] bulbs = br.readLine().toCharArray();
        char[] pressFirstBulbs = Arrays.copyOf(bulbs,bulbs.length);
        pressFirstBulbs[0] = bulbs[0] == '1' ? '0' : '1';;
        pressFirstBulbs[1] = bulbs[1] == '1' ? '0' : '1';;

        char[] target = br.readLine().toCharArray();

        int count = 0;
        int pressFirstCount = 1;

        for(int i=1;i<bulbs.length;i++){
            if(bulbs[i-1] != target[i-1]){
                bulbs[i-1] = bulbs[i-1] == '1' ? '0' : '1';
                bulbs[i] = bulbs[i] == '1' ? '0' : '1';

                if(i+1 < bulbs.length){
                    bulbs[i+1] = bulbs[i+1] == '1' ? '0' : '1';
                }

                count++;
            }

            if(pressFirstBulbs[i-1] != target[i-1]){
                pressFirstBulbs[i-1] = pressFirstBulbs[i-1] == '1' ? '0' : '1';
                pressFirstBulbs[i] = pressFirstBulbs[i] == '1' ? '0' : '1';

                if(i+1 < pressFirstBulbs.length){
                    pressFirstBulbs[i+1] = pressFirstBulbs[i+1] == '1' ? '0' : '1';
                }

                pressFirstCount++;
            }
        }

        if(isSame(bulbs,target)){
            System.out.println(count);
        }else if(isSame(pressFirstBulbs,target)){
            System.out.println(pressFirstCount);
        }else{
            System.out.print(-1);
        }
    }

    public static boolean isSame(char[]a, char[]b){
        for(int i=0;i<a.length;i++){
            if(b[i] != a[i]){
                return false;
            }
        }

        return true;
    }
}