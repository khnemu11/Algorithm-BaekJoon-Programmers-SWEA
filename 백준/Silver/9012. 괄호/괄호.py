
if __name__ == '__main__':
    N = int(input())

    for i in range(N):
        row = list(input())
        stack = []

        result="YES"

        for j in range(len(row)):
            if row[j] == '(':
                stack.append(row[j])
            else:
                if len(stack) == 0:
                    result="NO"
                    break
                elif stack[-1] == '(':
                    stack.pop()
                else:
                    result="NO"
                    break
        if len(stack) != 0:
            result="NO"

        print(result)