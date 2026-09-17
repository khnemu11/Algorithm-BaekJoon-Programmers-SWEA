def solution(A,B):
    answer = 0

    A.sort()
    B.sort()
    
    for i in range(0,len(A)):
        answer = answer + A[i] * B[len(B)-1-i]
    
    return answer