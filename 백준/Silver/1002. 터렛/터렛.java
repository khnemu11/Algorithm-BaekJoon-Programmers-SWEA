import java.util.Scanner;
 
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int x1, y1, r1, x2, y2, r2;
        int testcase;
        
        double d;
        testcase = sc.nextInt();
 
        for(int i=0; i<testcase; i++) {
            int result;
            
            x1 = sc.nextInt();
            y1 = sc.nextInt();
            r1 = sc.nextInt();
            x2 = sc.nextInt();
            y2 = sc.nextInt();
            r2 = sc.nextInt();
            //두 점의 길이
            d = Math.sqrt(Math.pow((x1-x2), 2) + Math.pow((y1-y2), 2));
            
 
            //원이 겹쳐서 무한인 경우 Math.abs()절대값 함수
            if(x1 ==x2 && y1 == y2 && r1 == r2) {
                result = -1;
            }
            else if(d == r1+r2 || Math.abs(r1-r2) == d  ) { //Math.abs(r1-r2) == d
                result = 1;
            }
            else if(Math.abs(r1-r2) > d || x1 ==x2 && y1 == y2 && r1 != r2 || d > r1+r2 ) {
                result = 0;
            }
            else {
                result = 2;
            }
            System.out.println(result);
        }
    }
}
