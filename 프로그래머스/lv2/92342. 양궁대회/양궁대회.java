/*
    풀이 알고리즘
    
    0~10점 중 n개를 선택
    -> 숫자 조합을 형성
    -> 숫자 조합의 결과와 어피치의 차이가 최대값보다 같거나 큰지 판별
    -> 만약 이미 해당 차이의 조합이 있다면 뒷자리부터 큰 숫자를 선별

*/
import java.util.*;
class Solution {
    static HashMap<Integer,ArrayList<Integer>> candidates;
    static int max=0;
    
    public int[] solution(int n, int[] info) {
        candidates = new HashMap<>();

        select(0,new ArrayList(),n,0,info);
        
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
        
        return answer;
    }
    public void select(int val,ArrayList<Integer> list,int rest,int sum,int [] info){
        if(val==11){    //10점짜리 과녁까지 모두 선별함
            int apeachSum =0;
            
            for(int i=0;i<info.length;i++){ //어피치의 점수 합계
                if(info[i]>0){
                    apeachSum = apeachSum+ (10-i);
                }
            }
            
            list.set(list.size()-1,list.get(list.size()-1) + rest); //아직 화살이 남았다면 가장 적은 과녁에 맞춘 화살이 많으사람이 이기므로 0점에 남은 화살 몰아주기

            int scoreSum =0;    //현재 라이언의 점수

            for(int i=0;i<list.size();i++){ 
                if(list.get(i)>0){  //과녁을 i점 점수에 맞추었으면 점수 증가
                    scoreSum = scoreSum+(10-i);
                }
                if(info[i]!=0 && info[i] < list.get(i)){ //어피치도 맞추었는데 라이언보다 적게 맞춘 경우 i점 점수 제거
                    apeachSum = apeachSum - (10-i);
                }
            }
            int differ = scoreSum - apeachSum; //점수 차이
                
            if(differ <=0 || differ < max){ //어피치가 이기거나 비기거나 차이의 최대값보다 작으면 연산 X
                return ;
            }
            
            ArrayList<Integer> copy = new ArrayList<>();   //과녁 리스트 저장
            
            for(int i=0;i<list.size();i++){ //복사
                copy.add(list.get(i));
            }
            
            if(candidates.get(differ)==null){   //아직 해당 차이의 조합이 없는경우 그냥 추가
                 candidates.put(differ,copy);
            }else{  //있는경우 끝 자리부터 확인하여 큰 값을 저장
                ArrayList<Integer> original = candidates.get(differ);
                for(int i=original.size()-1;i>=0;i--){
                    if(copy.get(i) > original.get(i)){  //현재 조합이 더 큰경우
                        candidates.put(differ,copy);
                        break;
                    }else if(copy.get(i) < original.get(i)){ //이전 조합이 더 큰경우
                        break;
                    }
                }
            }
           
            max = Math.max(max,differ); //최대값 최신화
        }else{    //n번재 과녁을 선별 중         
            for(int i=info[val]+1;i<=rest;i++){ //무조건 어피치를 이기는 과녁
                list.add(i);
                select(val+1,list,rest-i,sum+(10-val),info);
                list.remove(list.size()-1);
            }
            list.add(0);    //0점으로 어피치를 지는 경우(0점으로 져야 화살의 여분이 최대한 남아 최대값 차이를 구할 수 있다)
            select(val+1,list,rest,sum,info);
            list.remove(list.size()-1);
        }
    }
}
