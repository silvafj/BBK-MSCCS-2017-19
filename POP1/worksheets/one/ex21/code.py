def do_twice(f, v):
	f(v)
	f(v)

def print_twice(s):
	print(s)
	print(s)

do_twice(print_twice, 'spam')
