import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


class Solution {
    public boolean solution(int x) {
		int degitSum = Arrays.stream(String.valueOf(x).split("")).mapToInt(n->Integer.valueOf(n)).sum();
		if(x % degitSum == 0) {
			return true;
		}
		else {
			return false;
		}
		
    }
}