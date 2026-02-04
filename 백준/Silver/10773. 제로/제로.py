if __name__ == '__main__':
    N = int(input())
    stack = []

    for i in range(N):
        num = int(input())

        if num == 0 and len(stack) != 0:
            stack.pop()
        else:
            stack.append(num)

    print(sum(stack))