def solution(s):
    answer = True
    stack = []
    
    for b in s:  
        if b == '(':
            stack.append(b)
        else:
            if len(stack) == 0:
                answer = False
                break
            elif stack[-1] == '(':
                stack.pop()
            else:
                answer = False
                break
    
    if len(stack) > 0 :
        answer = False
    
    return answer