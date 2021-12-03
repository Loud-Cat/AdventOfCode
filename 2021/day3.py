with open("Inputs/input3.txt") as f:
  data = [i.strip() for i in f]

# PART TWO
def get_stuff(d, mostly, part):
  data = d.copy()
  binary = ""
  for i in range(12):
    bits = [*zip(*data)][i]
    common = [(i, bits.count(i)) for i in set(bits)]
    most = sorted(common, key=lambda x: x[1], reverse=mostly)

    if most[0][1] == most[1][1]: most = "01"[mostly]
    else: most = most[0][0]

    if part == 2:
      data = [j for j in data if j[i] == most]
      if len(data) == 1:
        return int(data[0], 2)

    binary += most
  return int(binary, 2)

gamma = get_stuff(data, True, 1)
epsilon = get_stuff(data, False, 1)
print("Part one:", gamma * epsilon)

o2 = get_stuff(data, True, 2)
co2 = get_stuff(data, False, 2)
print("Part two:", o2 * co2)
