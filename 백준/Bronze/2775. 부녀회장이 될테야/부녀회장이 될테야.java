import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int count = scan.nextInt();
        List<Integer> row = new ArrayList<>();
        List<Integer> col = new ArrayList<>();
        int rowMax =0;
        int colMax =0;

        for(int cnt = 0; cnt<count;cnt++){
            int num = scan.nextInt();
            row.add(num);
            if(rowMax<num)   rowMax = num;

            num = scan.nextInt();
            col.add(num);
            if(colMax<num)   colMax = num;
        }

            int [][] floor = new int [rowMax+1][colMax];
            floor[0][0] = 1;
    
            for(int i=0;i<=rowMax;i++){
                for(int j=0;j<colMax;j++){
                    if (i==0){
                        floor[i][j] = j+1;
                    }
                    else{
                        floor[i][j] = 0;
                        for(int x=0;x<j+1;x++){
                            floor[i][j] += floor[i-1][x];
                        }
                    }
                }        
            }
    
            for(int i=0;i<row.size();i++){
                System.out.println(floor[row.get(i)][col.get(i)-1]);
            }
        
    } 
}