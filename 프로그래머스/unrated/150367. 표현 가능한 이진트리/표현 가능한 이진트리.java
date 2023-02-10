import java.util.*;
/*풀이 과정
    각 문자열의 가장 중앙의 위치의 값이 루트 값
    각 중앙을 기준으로 왼쪽, 오른쪽을 분할하여 부모의 값이 0인데 자식을 가지고 있는 경우를 확인    
    이때 트리의 크기는 2^n - 1이므로 문자열의 길이가 해당 값보다 작으면 2^n - 1 숫자 중 문자열의 길이보다 크면서 가장 작은 숫자로 길이를 맞추기 위해 로 앞에 0추가
    ex) 1111 -> 0001111
*/

class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        int idx=0;
        
        for(long number : numbers){
            String binary = Long.toBinaryString(number);//숫자를 이진수로 변경
            boolean isValid = true;
            
            int pow = 1;
            
            while(binary.length() > Math.pow(2,pow)-1){ //문자열의 길이와 가장 가까우면서 큰 숫자 2^pow-1 탐색
                pow++;
            }
            while(binary.length() < Math.pow(2,pow)-1){ //문자열의 길이가 2^pow-1가 되도록 0 추가
                binary = "0"+binary;
            }
            
            if(check(binary.toCharArray(),0,binary.length()-1,1)){ //올바른 트리가 맞다면 1, 아니면 0 저장
                answer[idx++]=1;
            }else{
                answer[idx++]=0;
            }
        }
        return answer;
    }
    public boolean check(char[] str, int l, int r,int parent){ //올바른 트리(부모가 0인데 자식이 1이 있는 경우가 없는 트리)인지 확인하는 메소드
        int mid = (l+r)/2;
        
        if(str[mid]=='1' && parent == '0'){ //부모의 값이 인데 자식이 있는 경우
            return false;
        }else if(l==r){ //값과 상관없이 리프 노드이므로 참
            return true;
        }
        else{
            return check(str,l,mid-1,str[mid]) && check(str,mid+1,r,str[mid]); //자식들 판단
        }
    }
}