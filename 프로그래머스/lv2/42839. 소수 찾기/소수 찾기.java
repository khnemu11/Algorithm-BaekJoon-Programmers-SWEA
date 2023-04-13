import java.util.*;

class Solution {
    static boolean isNotPrime[];
    static boolean numVisited[];
    static boolean idxVisited[];
    
    static int cnt;
    public int solution(String numbers) {
        int answer = 0;
        makePrimeArr(numbers); 
        numVisited = new boolean[isNotPrime.length];
        idxVisited = new boolean[numbers.length()];
        
        selectNum(new StringBuilder(),0,numbers);
        
        return cnt;
    }
    
    public void selectNum(StringBuilder sb,int idx,String numbers){
        if(idx >=numbers.length()){
            if(sb.length()==0){
                return;
            }
            int num = Integer.valueOf(sb.toString());
            
            if(!numVisited[num] && !isNotPrime[num]){
                cnt++;
            }
            
            numVisited[num]=true;
            
            return;
        }
            
            selectNum(sb,idx+1,numbers);
            
            for(int i=0;i<numbers.length();i++){
                if(idxVisited[i]){
                    continue;
                }
                idxVisited[i]=true;
                sb.append(numbers.charAt(i));
                
                selectNum(sb,idx+1,numbers);
                
                sb.deleteCharAt(sb.length()-1);
                idxVisited[i]=false;
            }            
    }
    
    static void makePrimeArr(String str){
        int end = 0;
        
        for(int i=0;i<str.length();i++){
            end = end*10 + 9;
        }
        isNotPrime= new boolean[end+1];
        
        isNotPrime[0]=true;
        isNotPrime[1]=true;
        
        for(int i=2;i<=end;i++){
            if(isNotPrime[i]){
                continue;
            }
            for(int j=i+i;j<=end;j=j+i){
                isNotPrime[j]=true;
            }
        }     
    }
}