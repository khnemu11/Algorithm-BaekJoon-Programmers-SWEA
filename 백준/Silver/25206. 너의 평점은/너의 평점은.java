import java.io.*;
import java.util.*;

public class Main {
    final static int T = 20;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));

        double total = 0;

        Map<String,Double> gradeMap = new HashMap<>();

        gradeMap.put("F",0.0);
        gradeMap.put("D0",1.0);
        gradeMap.put("D+",1.5);
        gradeMap.put("C0",2.0);
        gradeMap.put("C+",2.5);
        gradeMap.put("B0",3.0);
        gradeMap.put("B+",3.5);
        gradeMap.put("A0",4.0);
        gradeMap.put("A+",4.5);

        double majorSum = 0;
        double sum = 0;

        for(int i=0;i<T;i++){
            String[] input = br.readLine().split(" ");

            double grade = Double.parseDouble(input[1]);

            if(input[2].equals("P")){
                continue;
            }

            majorSum+= grade * gradeMap.get(input[2]);
            sum += grade;
        }

        System.out.println(majorSum/sum);
    }
}

