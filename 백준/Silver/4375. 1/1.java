import java.io.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));

        String input = "";

        while((input = br.readLine()) != null && input.length() !=0){
            BigInteger N = new BigInteger(input);
            BigInteger num = new BigInteger("1");

            while(!num.mod(N).equals(new BigInteger("0"))){
                num = num.multiply(new BigInteger("10")).add(new BigInteger("1"));
            }

            System.out.println(num.toString().length());
        }
    }
}