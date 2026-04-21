from collections import deque
import math

def solution(progresses, speeds):
    answer = []
    times = deque()
    
    for i in range(0, len(progresses)):
        times.append(math.ceil(float((100 - progresses[i]) / speeds[i] )))
    
    curr = times.popleft()
    cnt = 1
    
    while(len(times) > 0):
        next = times.popleft()
        
        if curr < next:
            answer.append(cnt)
            cnt = 1
            curr = next
        else:
            cnt = cnt + 1
        
    if cnt > 0:
        answer.append(cnt)
    
    return answer