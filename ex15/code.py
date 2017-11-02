# Suppose the cover price of a book is $24.95, but bookstores get a 40% discount.
# Shipping costs $3 for the first copy and 75 cents for each additional copy.
# What is the total wholesale cost for 60 copies?

print(round((24.95 - (24.95 * (40 / 100))) * 60 + 3 + 0.75 * 59, 2))
