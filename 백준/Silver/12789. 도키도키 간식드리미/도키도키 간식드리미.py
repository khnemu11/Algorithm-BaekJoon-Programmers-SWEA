if __name__ == '__main__':
    N = int(input())
    start = list(map(int,input().split(' ')))
    ready = []
    seq = 1

    for curr in start:
        isPass = False

        if curr == seq:
            seq += 1
            isPass = True

        while len(ready) > 0 and  ready[len(ready)-1]  == seq:
            ready.pop()
            seq += 1

        if curr == seq:
            seq += 1
            isPass = True

        if not isPass:
            ready.append(curr)

    if seq > N:
        print("Nice")
    else:
        print("Sad")