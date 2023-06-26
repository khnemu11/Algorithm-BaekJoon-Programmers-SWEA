import java.util.*;
/*
아래에서 위로 쌓인다 -> 스택
*/
class Solution {
    Stack<Integer> stack = new Stack<>();   //재료 순서
    int sequence[] = {1,3,2,1}; //햄버거 순서
    public int solution(int[] ingredients) {
        int count = 0;  //완성된 햄버거 개수
        
        //재료를 하나씩 올려서 햄버거를 만드는 과정
        for(int ingredient : ingredients){
            stack.add(ingredient);
            
            //포장이 가능한만큼 햄버거 제거
            while(doPack()){
                //완성된 햄버거 개수 증가
                count++;
            }
        }
        
        return count;
    }
    //포장이 가능한 햄버거 있으면 포장하는 메소드
    public boolean doPack(){
        //햄버거 재료 수보다 현재 재료수가 적으면 불가능
        if(stack.size() < sequence.length){
            return false;
        }
        //확인할 햄버거 재료를 임시 저장하는 스택
        Stack <Integer>temp = new Stack<>();
        boolean valid = true;
        
        //위에서 필요한 재료만큼 꺼내서 확인하는 과정
        for(int i=0;i<sequence.length;i++){
            temp.add(stack.pop());
            //불가능 하면 더이상 탐색 X
            if(sequence[i] != temp.peek()){
                valid=false;
                break;
            }
        }
        
        //불가능 하면 임시 배열의 값을 스택에 저장
        if(!valid){
            while(!temp.isEmpty()){
                stack.add(temp.pop());
            }
        }
        
        return valid;
    }
}