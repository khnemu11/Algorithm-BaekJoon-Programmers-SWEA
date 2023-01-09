import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        scan.nextLine();
        List<String> word = new ArrayList<>();

        for(int i=0;i<num;i++){
            word.add(scan.nextLine());
        }

        for(int i=0;i<num;i++){
            System.out.println(String.format("String #%d",i+1));
            for(int j=0;j<word.get(i).length();j++){
                if(Character.isLetter(word.get(i).charAt(j))==true){
                    if(word.get(i).charAt(j)=='Z')  System.out.print('A');
                    else System.out.print((char)(word.get(i).charAt(j)+1));
                }  else{
                    System.out.print(word.get(i).charAt(j));
                } 
            }
            System.out.println("");
            if(i!=num-1)    System.out.println("");
        }
        scan.close();
    } 
}