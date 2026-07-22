def solution(s):
    answer = ''
    first = True
    
    for i in s:
        if first == True:
            answer = answer + i.upper()
            first = False
        else:
            answer = answer + i.lower()
            
        if i == ' ':
            first = True
    return answer