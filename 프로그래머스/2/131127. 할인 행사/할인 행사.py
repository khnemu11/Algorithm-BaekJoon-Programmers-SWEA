def solution(want, number, discount):
    buyDict = {};
    start = 0
    answer = 0
    
    while start + 9 < len(discount):
        if start == 0:    
            curr = 0
            
            while curr < 10:
                buyDict[discount[curr]] = buyDict.get(discount[curr], 0) + 1
                curr = curr + 1
        else:
            buyDict[discount[start - 1]] = buyDict.get(discount[start - 1], 0) - 1
            buyDict[discount[start + 9]] = buyDict.get(discount[start + 9], 0) + 1
        
        success = True
        
        for i in range(0,len(want)):
            if buyDict.get(want[i],0) < number[i]:
                success = False
                break
        
        if success == True:
            answer = answer + 1
        
        start = start + 1
    
    return answer