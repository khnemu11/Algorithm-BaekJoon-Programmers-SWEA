import java.util.*;

/*
    풀이 알고리즘
    
    점과 점사이 -> 다익스트라 (노드의 개수가 1억이므로 제한시간내에 불가)
    
    움직여야 되는 횟수 : 각 자리수의 합
    움직일 수 있는 방향 : 위,아래 => (10-자리수), 자리수
    ex) 8인경우 8로 8번 바로 아래로 가거나
               2로로 2번 위로가서 10에서 1번 내려가는 것 2번이 있다
               
    => 각 자리수에서 위로 가거나, 아래로 가거나 할 때 최대 8자리수인 조합 
    시간 복잡도 : O(2^8)
    
    걸린 시간 : 40분
*/
class Solution {
    int min = Integer.MAX_VALUE;
    
    public int solution(int storey) {
        int answer =0;
        
        selectUpDown(0,storey,0);
        
        return min;
    }
        
    public void selectUpDown(int digit, int storey,int sum){    //위아래로 갈지 선택하는 메소드(자리수, 현재 값, 움직인 횟수의 합)
        if(digit==8 || storey==0){  //최대 자리인 8자리수거나 현재 값이 0이면 더이상 움직일 수 없음
            min = Math.min(Math.abs(0 - storey)+sum,min);   //이때 1억의 자리수가 1인 경우가 있으므로 0과 1인경우를 생각해서 반영함
                                                            //if(storey==0)  min = Math.min(sum,min);       //더이상 움직일 수 없느 경우
                                                            //if(digit==8)  min = Math.min(sum+1,min);     //1억 자리수인 경우
                                                            //과 동일
        }else{
            String num[] = String.valueOf(storey).split("");    //가장 끝의 자리수를 구하기 위해 모든 숫자를 스플릿
            int lastNum = Integer.valueOf(num[num.length-1]);   //끝자리 수
            if(min > sum+lastNum){  //최소값보다 이전까지의 이동횟수의 합과 현재 위치에서 내려가는 횟수가 작으면 다음 자리수로 이동
                selectUpDown(digit+1,storey/10,sum+lastNum);
            }
           
            int differ = 10 - lastNum;  //올라가는 횟수
                
            if(lastNum!=0 && sum+differ<min){   //현재 자리수가 0이 아니고 최소값일 수 있는 경우면 올라가고 다음 자리수로 이동
                selectUpDown(digit+1,(storey+differ)/10,sum+differ);
            }
        }
    }
}
