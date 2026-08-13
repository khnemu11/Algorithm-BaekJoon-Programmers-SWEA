def solution(s):
    answer = 0
    
    list = []
    
    for i in s:
        if len(list) == 0:
            list.append(i)
        elif list[len(list)-1] == i:
            list.pop()
        else:
            list.append(i)
    
    if len(list) == 0:
        answer = 1
    
    return answer