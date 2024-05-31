import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] skill = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            int val = Integer.parseInt(st.nextToken());
            skill[i] = val;
        }

        Arrays.sort(skill);
        long count = 0;

        for(int i=0;i<skill.length;i++){
            if(skill[i] > 0){
                break;
            }

            int left = i+1;
            int right = N-1;

            while(left < right){
                int sum = skill[i] + skill[left] + skill[right];
                long leftSameCount = 1;
                long rightSameCount = 1;

                if(sum == 0){
                    if(skill[left] == skill[right]){
                        long length = right - left + 1;

                        count += length*(length-1)/2;
                        break;
                    }
                    while(left + 1 < right && skill[left]  == skill[left+1]){
                        leftSameCount++;
                        left++;
                    }
                    while(right -1 > left && skill[right]  == skill[right-1] ){
                        rightSameCount++;
                        right--;
                    }

                    count += leftSameCount*rightSameCount;
                }

                if(sum > 0){
                    right--;
                }else{
                    left++;
                }
            }
        }

        System.out.println(count);
    }
}