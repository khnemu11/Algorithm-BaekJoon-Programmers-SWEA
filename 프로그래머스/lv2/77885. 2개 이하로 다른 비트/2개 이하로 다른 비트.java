import java.util.*;


class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
  
        for(int i=0;i<numbers.length;i++){
            String binary = "0"+Long.toBinaryString(numbers[i]);
            char [] arr = binary.toCharArray();
            // System.out.println(Arrays.toString(arr));
            
            if(arr[arr.length-1] == '0'){
                answer[i] = numbers[i]+1;
                continue;
            }
            
            for(int j=arr.length-1;j>=1;j--){
                String target = "" + arr[j-1] + arr[j];
                // System.out.println(target);
                if(!target.equals("01")){
                    continue;
                }
                
                arr[j-1]='1';
                arr[j]='0';
                // System.out.println(Arrays.toString(arr));
                answer[i] = Long.parseLong(new String(arr),2);
                // System.out.println(answer[i]);
                break;
            }
        }
        
        return answer;
    }
}