import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        BigInteger N = new BigInteger(br.readLine());
        int mod = N.mod(new BigInteger("7")).intValue();

        if(mod == 2 || mod == 0){
            System.out.println("CY");
        }else{
            System.out.println("SK");
        }
    }
}

/*
*
* 1
* 상근 1
*
* 2
* 상근 1
* 청명 1
*
* 3
* 상근 3
*
* 4
* 상근 4
*
* 5
* 상근 3
* 청명 1
* 상근 1
*
* 6
* 상근 1
* 청명 4
* 상근 1
*
* 7
* 상근 4
* 청명 3
* */
