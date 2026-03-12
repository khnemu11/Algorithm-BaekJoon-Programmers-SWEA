if __name__ == '__main__':
    N = int(input())

    inputs = []

    for i in range(N):
        inputs.append(input())

    sortedlist = sorted(list(dict.fromkeys(inputs)), key = lambda x : (len(x),x))

    print("\n".join(sortedlist))