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
