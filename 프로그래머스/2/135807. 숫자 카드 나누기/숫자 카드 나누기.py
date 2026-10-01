def solution(arrayA, arrayB):
    return max(divideCard(arrayA, arrayB),divideCard(arrayB, arrayA))

def divideCard(arrayA, arrayB):
    a = arrayA[0]
    
    for i in range(1,len(arrayA)):
        a = getGcd(a,arrayA[i])
    
    for b in arrayB:
        if b % a == 0:
            return 0
    
    return a
    
def getGcd(a,b):
    if a % b == 0:
        return b
    else:
        return getGcd(b,a % b)