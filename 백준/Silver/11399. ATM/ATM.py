if __name__ == '__main__':
    N = int(input())
    people = list(map(int, input().split()))
    sortedPeople = sorted(people)

    sum = 0
    total = 0

    for item in sortedPeople:
        total += sum + item
        sum += item

    print(total)