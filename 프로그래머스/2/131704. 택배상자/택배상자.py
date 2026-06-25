def solution(order):
    answer = 0
    seq = 0
    
    subContainer = []
    
    for curr in range(1,len(order)+1):
        subContainer.append(curr)
        
        while len(subContainer) > 0 and subContainer[-1] == order[seq]:
            subContainer.pop()
            seq = seq + 1
            answer = answer + 1
        
    return answer