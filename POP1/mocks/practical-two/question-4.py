def is_end(record):
    return record[0] == "X0000"


def is_regular(record):
    return record[3] == "R"


def compute_regular(record):
    charge = 10.00

    minutes = record[4]
    if minutes > 50:
        charge += (minutes - 50) * 0.20

    return charge

def compute_premium(record):
    charge = 25.00

    daytime = record[4]
    if daytime > 75:
        charge += (daytime - 75) * 0.10

    offpeak = record[5]
    if offpeak > 100:
        charge += (offpeak - 100) * 0.05

    return charge


def print_record(record, charge_month):
    print(record[0], record[1], record[3], charge_month, record[2] + charge_month)


def load_records(file):
    records = []
    with open(file) as source:
        for row in source:
            row = row.split()
            if len(row) > 1:
                row[2] = float(row[2])
                row[4] = int(row[4])
                
            if len(row) == 6:
                row[5] = int(row[5])
                
            records.append(row)

    return records


for record in load_records("question-4-data.txt"):
    if is_end(record):
        break

    charge_month = 0
    if is_regular(record):
        charge_month = compute_regular(record)
    else:
        charge_month = compute_premium(record)

    print_record(record, charge_month)
