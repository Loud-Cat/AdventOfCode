with open("Inputs/input7.txt", "r") as f:
  data = [i.strip() for i in f]
  positions = [int(i) for i in data[0].split(",")]
  pset = set(positions)

def get_fuel(a,b):
  dist = abs(b - a)
  step = 0
  for i in range(dist):
    dist += step
    step += 1
  return dist

print("WARNING: MAY TAKE A WHILE")
def part_one():
  fuel = []
  for pos in positions:
    distances = [abs(move - pos) for move in positions]
    fuel.append(sum(distances))
  return min(fuel)
print("Part one:", part_one())

def part_two():
  fuel = []
  avg = sum(pset) // len(pset)
  for pos in range(avg):
    distances = [get_fuel(pos, p2) for p2 in positions]
    fuel.append(sum(distances))
  return min(fuel)
print("Part two: ", part_two())

