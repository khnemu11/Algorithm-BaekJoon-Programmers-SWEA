def solution(citations):
    hIndex = len(citations)
    
    while hIndex > 0 :
        uCnt = 0
        lCnt = 0
        
        for c in citations:
            if c >= hIndex:
                uCnt= uCnt +1
            if c <= hIndex:
                lCnt = lCnt + 1
                
        if hIndex <= uCnt and hIndex >= lCnt :
            break
            
        hIndex = hIndex - 1
    
    return hIndex