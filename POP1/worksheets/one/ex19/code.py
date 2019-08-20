def do_twice(f, v):
	f(v)
	f(v)

def print_spam(v):
    print(v)

do_twice(print_spam, 'spam')
