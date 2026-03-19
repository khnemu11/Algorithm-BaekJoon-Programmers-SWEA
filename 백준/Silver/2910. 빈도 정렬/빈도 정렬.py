class Status:
    def __init__(self, key, seq, cnt):
        self.key = key
        self.seq = seq
        self.cnt = int(cnt)

    def __str__(self):
        return f"{self.key}:{self.seq}:{self.cnt}"

if __name__ == '__main__':
    row = list(map(int, input().split()))
    N = row[0]
    C = row[1]

    messages = list(map(int, input().split()))
    info = {}
    seq = 0

    for message in messages:
        if info.get(message) is None:
            info[message] = Status(message, seq, 1)
        else:
            newMessage = Status(message, info.get(message).seq, info.get(message).cnt + 1)
            info[message] = newMessage

        seq += 1

    result = sorted(list(info.values()),key=lambda x:(-x.cnt,x.seq))
    resultList = []

    for status in result:
        for i in range(0,status.cnt):
            resultList.append(str(status.key))


    print(' '.join(resultList))
