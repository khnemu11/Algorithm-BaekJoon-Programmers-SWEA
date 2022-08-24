import java.util.Scanner;

public class Main { 
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int powNum=0;
        int N = sc.nextInt();
        int result = 0;
        while(true){
            if(N<=Math.pow(2, powNum)){
                break;
            }
            powNum++;
        }
        if(powNum==0){
            System.out.println(1);
        }
        else if(N==Math.pow(2, powNum)){
            System.out.println(N);
        }
        else{
            for(double i=Math.pow(2, powNum-1)+1;i<=N;i++){
                result+=2;
            }
            System.out.println(result);
        }

    } 
}