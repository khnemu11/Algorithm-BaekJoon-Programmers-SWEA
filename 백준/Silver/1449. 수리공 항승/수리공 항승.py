if __name__ == '__main__':
    N ,L = map(int, input().split())
    pipe = list(map(int, input().split()))
    sorted_pipe = sorted(pipe)

    cnt = 0
    start = 0

    for loc in range(1,len(sorted_pipe)):
        if sorted_pipe[loc] - sorted_pipe[start]  + 1 > L :
            cnt += 1
            start = loc

    cnt += 1

    print(cnt)