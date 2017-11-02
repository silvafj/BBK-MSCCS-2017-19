s = input()

h1 = s.find('h') + 1
h2 = s.rfind('h')

print(s[:h1] + s[h1:h2].replace('h', 'H') + s[h2:])
