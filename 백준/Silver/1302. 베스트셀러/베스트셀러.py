if __name__ == '__main__':
    N = int(input())

    sellList = dict()

    for i in range(N):
        book = input()
        sellList.update({book : sellList.get(book,0)+1})

    bestSellerList = []
    max = max(sellList.values())

    for k,v in sellList.items():
        if v == max:
            bestSellerList.append(k)

    sortedSellerList = sorted(bestSellerList)
    print(sortedSellerList[0])