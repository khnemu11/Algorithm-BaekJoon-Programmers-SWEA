from collections import deque

def solution(numbers):
    answer = [-1] * len(numbers)
    q = deque()
    
    for i in range(len(numbers) -1 , -1, -1):
        while len(q) != 0:
            target = q.pop()
            
            if target > numbers[i]:
                answer[i] = target
                q.append(target)
                break
        
        q.append(numbers[i])
    
    return answer