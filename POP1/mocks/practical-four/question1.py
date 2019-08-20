def swap_min_max(lst):
    if lst:
        min_index = lst.index(min(lst))
        max_index = lst.index(max(lst))
        lst[min_index], lst[max_index] = lst[max_index], lst[min_index]

    return lst
