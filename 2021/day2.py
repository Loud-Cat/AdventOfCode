import re
with open("Inputs/input2.txt") as f:
  data = [i.strip() for i in f]

# PART ONE
x,y = 0,0
for i in data:
  stuff = re.search(r"(forward|up|down) (\d+)", i).groups()
  direction, amount = stuff
  amount = int(amount)

  if direction == "forward":
    x += amount
  else:
    a = amount
    y += {"up":-a, "down":a}.get(direction)
print("Part one:", x * y)

# PART TWO
x, y, aim = 0, 0, 0
for i in data:
  stuff = re.search(r"(forward|up|down) (\d+)", i).groups()
  direction, amount = stuff
  amount = int(amount)
  
  if direction == "down":
    aim += amount
  elif direction == "up":
    aim -= amount
  else:
    x += amount
    y += (aim * amount)
print("Part two:", x * y)
