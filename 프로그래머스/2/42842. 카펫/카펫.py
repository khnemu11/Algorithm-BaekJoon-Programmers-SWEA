#    갈색 영역의 너비 = (전체 넓이의 가로 - 2(끝테두리))*2(2개) + (전체 넓이의 세로 - 2(끝테두리))*2(2개) + 4개(양 끝 테두리)
#    if 갈색 영역의 너비 == 주어진 brown값 ==> 카펫의 너비
    
#    카펫의 너비는 최대 sqrt(전체 카펫의 넓이) 까지 된다

#    시간 복잡도 = O(sqrt(brown + yellow))
import math

def solution(brown, yellow):
    total = brown + yellow
    sqrtTotal = int(math.sqrt(total))
    answer = [0,0]

    for h in range (3,sqrtTotal + 1):
        if total % h != 0:
            continue
        
        w = total / h
        
        if (w-2)*2 + (h-2)*2+ 4 == brown :
            answer
            
        if total % h !=0:
            continue;
    

        w = total / h;

        if (w-2)*2 + (h-2)*2+ 4 == brown:
            answer[0]=w;
            answer[1]=h;
            break

    return answer



