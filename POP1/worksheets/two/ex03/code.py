s = input()

first = s.find('f')
if first != -1:
    print(first, end=' ')

last = s.rfind('f')
if last != -1 and last != first:
    print(last, end=' ')
