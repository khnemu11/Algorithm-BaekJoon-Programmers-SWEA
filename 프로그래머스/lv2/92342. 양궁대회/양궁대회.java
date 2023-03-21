/*
    풀이 알고리즘
    
    0~10점 중 n개를 선택
    -> 숫자 조합 형성
    -> 가장 큰 조합부터 가능한지 판단
    -> 가능하다면 해당 depth에서 가장 낮은 점수를 맞춘 개수가 많은 것을 탐색
*/
import java.util.*;
class Solution {
    static HashMap<Integer,ArrayList<Integer>> candidates;
    static int max=0;
    
    public int[] solution(int n, int[] info) {
        candidates = new HashMap<>();
        int apeachMaxScore=0;
        int MAX =55;
        
        for(int i=0;i<info.length;i++){
            if(info[i]>0){
                 apeachMaxScore += (10-i);
            }
        }
        
        // System.out.println(apeachMaxScore);
        select(0,new ArrayList(),n,0,info);
    
        // System.out.println("max : "+max);
        // System.out.println(candidates.get(max));
        
        int answer[];
        
        if(max == 0){
            answer = new int[1];
            answer[0] = -1; 
        }else{
            answer = new int[11];
            for(int i=0;i<11;i++){
                answer[i] = candidates.get(max).get(i);
            }
        }
        
       
        boolean isFind = false; 
        
        if(!isFind){
          
        }
        return answer;
    }
    public void select(int val,ArrayList<Integer> list,int rest,int sum,int [] info){
        if(val==11){
            int apeachSum =0;
            
            for(int i=0;i<info.length;i++){
                if(info[i]>0){
                    apeachSum = apeachSum+ (10-i);
                }
            }
            
            list.set(list.size()-1,list.get(list.size()-1) + rest);

            int scoreSum =0;

            for(int i=0;i<list.size();i++){
                if(list.get(i)>0){
                    scoreSum = scoreSum+(10-i);
                }
                if(info[i]!=0 && info[i] < list.get(i)){
                    apeachSum = apeachSum - (10-i);
                }
            }
            int differ = scoreSum - apeachSum;
                
            if(differ <=0 || differ < max){
                return ;
            }
            
            ArrayList<Integer> copy = new ArrayList<>();
            
            for(int i=0;i<list.size();i++){
                copy.add(list.get(i));
            }
             // System.out.println(list+" rest : "+rest+" max : "+max+" differ : "+differ);
            if(candidates.get(differ)==null){
                 candidates.put(differ,copy);
            }else{
                ArrayList<Integer> original = candidates.get(differ);
                // System.out.println(copy+" vs " + original);
                for(int i=original.size()-1;i>=0;i--){
                    if(copy.get(i) > original.get(i)){
                        // System.out.println("list is bigger than original" + copy.get(i)+" "+original.get(i));
                        candidates.put(differ,copy);
                        // System.out.println("change : "+candidates.get(differ));
                        break;
                    }else if(copy.get(i) < original.get(i)){
                        break;
                    }
                }
            }
           
            max = Math.max(max,differ);
        }else{                    
            for(int i=info[val]+1;i<=rest;i++){
                list.add(i);
                select(val+1,list,rest-i,sum+(10-val),info);
                list.remove(list.size()-1);
            }
            list.add(0);
            select(val+1,list,rest,sum,info);
            list.remove(list.size()-1);
        }
    }
}