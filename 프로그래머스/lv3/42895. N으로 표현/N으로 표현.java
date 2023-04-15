import java.util.*;

class Solution {
    int minCnt=9;

    public int solution(int N, int number) {
        if(N == number){
            return 1;
        }
        
        int MAX_LENGTH=8;
        HashSet<Integer> set[] = new HashSet[MAX_LENGTH+1];
        
        for(int i=1;i<=MAX_LENGTH;i++){
            set[i] = new HashSet<>();
        }
        
        StringBuilder sb = new StringBuilder();
        
        for(int i=0;i<MAX_LENGTH;i++){
            sb.append(N);
            set[i+1].add(Integer.valueOf(sb.toString()));
        }

        int cnt=1;
        
        while(cnt<=MAX_LENGTH){
            for(int i=1;i<=cnt/2;i++){
                for(int first : set[i]){
                    for(int second : set[cnt-i]){
                        set[cnt].add(first*second);
                        set[cnt].add(first+second);
                        set[cnt].add(first-second);
                        set[cnt].add(second-first);
                        if(second!=0){
                            set[cnt].add(first/second);    
                        }
                        if(first!=0){
                            set[cnt].add(second/first);    
                        }
                    }     
                }         
            }
            if(set[cnt].contains(number)){
                return cnt;
            }
            cnt++;
        }
        
        return -1;
    }

}