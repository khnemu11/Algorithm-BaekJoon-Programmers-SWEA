def solution(n):
    answer = 0
    
    fibonacci = []
    fibonacci.append(1)
    fibonacci.append(1)    
    
    for i in range (2,n):
        fibonacci.append((fibonacci[i-1] + fibonacci[i-2]) % 1234567)
    
    return fibonacci[n-1]
