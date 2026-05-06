def solution(n):
    curr = n
    find = False
    bistr = bin(curr)
    ori_cnt = 0
    
    for i in bistr:
            if i == "1":
                ori_cnt = ori_cnt + 1
    
    while find == False:
        curr = curr + 1
        bistr = bin(curr)
        cnt = 0
        
        for i in bistr:
            if i == "1":
                cnt = cnt + 1
        
        if ori_cnt == cnt :
            find = True
        
    answer = curr
    return answer