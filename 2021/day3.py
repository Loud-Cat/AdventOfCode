with open("Inputs/input3.txt") as f:
  data = [i.strip() for i in f]

# PART ONE
def get_common(mostly):
  binary = ""
  for i in range(12):
    bits = [*zip(*data)][i]
    common = [(i, bits.count(i)) for i in set(bits)]
    most = sorted(common, key=lambda x: x[1], reverse=mostly)
    binary += most[0][0]
  return int(binary, 2)

gamma = get_common(True)
epsilon = get_common(False)
print("Part one:", gamma * epsilon)

# PART TWO
def get_rating(d, mostly):
  data = d.copy()
  for i in range(12):
    bits = [*zip(*data)][i]
    common = [(i, bits.count(i)) for i in set(bits)]
    most = sorted(common, key=lambda x: x[1], reverse=mostly)

    if most[0][1] == most[1][1]:
      most = "01"[mostly]
    else:
      most = most[0][0]

    data = [j for j in data if j[i] == most]
    if len(data) == 1:
      return int(data[0], 2)

o2 = get_rating(data, True)
co2 = get_rating(data, False)
print("Part two:", o2 * co2)
