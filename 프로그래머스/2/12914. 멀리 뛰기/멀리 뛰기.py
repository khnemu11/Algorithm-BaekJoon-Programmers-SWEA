def solution(n):
    MOD = 1234567
    
    count = [0]*(2001)
    count[1] = 1
    count[2] = 2
    
    if n > 2:
        for curr in range(3,n+1):
            count[curr] = (count[curr-1] % MOD)+ (count[curr-2])%MOD

    answer = count[n] % MOD
    return answer