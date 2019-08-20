students = []
for i in range(int(input())):
    students.append([])
    for _ in range(int(input())):
        students[i].append(input())

languages = set(l for s in students for l in s)
spoken_by_all = languages.copy()
spoken_by_one = set()

for s in students:
    spoken_by_all &= set(s)
    if languages == set(s):
        spoken_by_one = set(s)

print(len(spoken_by_all))
print(*sorted(spoken_by_all))

print(len(spoken_by_one))
print(*sorted(spoken_by_one))

# I spent one hour trying to optimise this and missed
# the obvious!
# students = [{input() for j in range(int(input()))} for i in range(int(input()))]
# known_by_everyone, known_by_someone = set.intersection(*students), set.union(*students)
# print(len(known_by_everyone), *sorted(known_by_everyone), sep='\n')
# print(len(known_by_someone), *sorted(known_by_someone), sep='\n')
