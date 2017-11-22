def triangle(n):
    """Generates Pascal's triangle with `n` rows."""
    if n == 1:
        return [[1]]
    else:
        rows = triangle(n - 1)
        last_row = rows[-1]

        new_row = [1]
        new_row.extend([last_row[i] + last_row[i + 1] for i in range(len(last_row) - 1)])
        new_row.extend([1])

        rows.append(new_row)
        return rows
