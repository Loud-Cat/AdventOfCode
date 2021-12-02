import re
with open("Inputs/input2.txt") as f:
  data = [i.strip() for i in f]

# PART ONE && PART TWO
x,y, x2,y2,aim = 0,0, 0,0,0
for i in data:
  stuff = re.search(r"(forward|up|down) (\d+)", i).groups()
  direction, amount = stuff
  amount = int(amount)

  if direction == "forward":
    x += amount
    x2 += amount
    y2 += (aim * amount)
  elif direction == "up":
    y -= amount
    aim -= amount
  if direction == "down":
    y += amount
    aim += amount
print("Part one:", x * y)
print("Part two:", x2 * y2)
