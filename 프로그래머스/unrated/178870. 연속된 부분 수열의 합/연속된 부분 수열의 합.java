
/*
    가장 짧은 구간합의 길이 => 최대한 큰 값을 이용해서 목표값을 만들기
    비내림차순으로 정렬되어 있으므로 뒤에서 부터 구간합을 구했을 때 목표 값이 나오면 가장 짧은 길이가 나온다.
    ->투포인터를 사용하여 구간합 풀이
    
    이때 길이가 짧은 수열이 여러개 일 수 도 있는데 이때 앞의 인덱스값을 더하고 뒤의 인덱스 값을 뺀 것을 더한 것이 안같아질 때 까지 인덱스를 앞으로 댕긴다
    
    ex) 2 2 2 2

    풀이 시간 : 16분 24초

*/
class Solution {
    public int[] solution(int[] sequence, int k) {
        int r = sequence.length-1;
        int l=r;
        int answer[]=new int[2];
        int sum = sequence[r];
        
        //왼쪽, 오른쪽 투포인터를 활용해서 구간합 구하기
        //현재 합이 목표값보다 작다->수열의 길이를 늘려야 한다 -> 왼쪽 포인터 감소
        //현재 합이 목표값보다 크다 -> 수열의 길이를 줄여야 한다-> 오른쪽 포인터 감소
        
        while(r>=0){
            while(l>=0){
                if(sum>=k){
                    break;
                }
                l--;
                sum+=sequence[l];
            }
            
            if(sum == k){
                break;
            }
            
            sum-=sequence[r];
            r--;
        }
        //수열이 여러개인 경우을 위해 앞의 인덱스를 이용한 것이 목표 값을 만들 수 있는지 확인하는 부분
        while(l>0 && r>0){
            if(sum + sequence[l-1] - sequence[r] != sum){
                break;
            }
            
            l--;
            r--;
        }
        
        //구한 인덱스를 리턴 배열에 저장
        answer[0]=l;
        answer[1]=r;
        
        return answer;
    }
}
