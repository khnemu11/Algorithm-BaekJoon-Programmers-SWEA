class Person:
    def __init__(self, name, age, seq):
        self.name = name
        self.age = age
        self.seq = seq

if __name__ == '__main__':
    N = int(input())
    list = []

    for i in range(N):
        row = input().split(" ")
        list.append(Person(row[1],int(row[0]),i))

    slist = sorted(list,key=lambda x: (x.age,x.seq))

    for person in slist:
        print(str(person.age)+" "+person.name)