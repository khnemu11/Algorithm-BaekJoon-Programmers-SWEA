import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        int start = scan.nextInt();
        double [] goodBad = new double[4];
        double goodResult = 0;
        for(int i=0;i<4;i++){
            goodBad[i] = scan.nextDouble();
        }
        scan.close();
        
        /*for(long i=0 ;i<Math.pow(2,num-1);i++){
            String cond = String.valueOf(start);
            double result = 1;

            for(int j=0;j<num-1-Long.toBinaryString(i).length();j++){
                cond += "0";
            }
            if (num!=1) cond+=Long.toBinaryString(i);
            cond+="0";

            for(int j=0;j<cond.length()-1;j++){
                if(cond.substring(j, j+2).equals("11")==true){
                    result = result*prop[2];
                }
                else if(cond.substring(j, j+2).equals("10")==true){
                    result = result*prop[3];
                }
                else if(cond.substring(j, j+2).equals("00")==true){
                    result = result*prop[0];
                }
                else if(cond.substring(j, j+2).equals("01")==true){
                    result = result*prop[1];
                }
            }
            goodResult += result;
        }*/
        //00 01 10 11
        double [][] prop = new double[2][num];
        if(start == 0){
            prop[0][0] = goodBad[0];
            prop[1][0] = goodBad[1];
        }
        else{
            prop[0][0] = goodBad[2];
            prop[1][0] = goodBad[3];
        }

        for(int i=1;i<num;i++){
            prop[0][i] = prop[0][i-1]*goodBad[0] + prop[1][i-1]*goodBad[2];
            prop[1][i] = prop[0][i-1]*goodBad[1] + prop[1][i-1]*goodBad[3];
        }

        

          //  goodResult= goodResult+main.goodBad(num-1,1,String.valueOf(start));
            System.out.println(String.format("%d", (int)(prop[0][num-1] *1000)));
            System.out.println(String.format("%d", 1000- (int)(prop[0][num-1] *1000)));
    } 
}