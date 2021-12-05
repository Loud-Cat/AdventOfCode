with open("Inputs/input5.txt") as f:
  data = [i.strip().split(" -> ") for i in f]

def minmax(iterable):
  return range(min(iterable), max(iterable)+1)

def updown(a, b):
  if a > b:
    return range(a, b-1, -1)
  return range(a, b+1)

def get_stuff(one=False, two=False):
  for p1, p2 in data:
    x1,y1 = map(int, p1.split(","))
    x2,y2 = map(int, p2.split(","))

    all_x = updown(x1,x2)
    all_y = updown(y1,y2)
    if x1 != x2 and y1 != y2 and one: continue
    if x1 == x2 or y1 == y2 and one:
      all_x = minmax([x1,x2])
      all_y = minmax([y1,y2])

    if x1 != x2 and y1 != y2 and two:
      for x,y in zip(all_x, all_y):
        if (x,y) in points: points[(x,y)] += 1
        else: points[(x,y)] = 1

    if x1 == x2 and one:
      for y in all_y:
        if (x1, y) in points:
          points[(x1,y)] += 1
        else: points[(x1,y)] = 1
    elif y1 == y2 and one:
      for x in all_x:
        if (x, y1) in points:
          points[(x, y1)] += 1
        else: points[(x, y1)] = 1
  return sum(1 for i in points if points[i] > 1)

points ={}
print("Part one:", get_stuff(one=True))
print("Part two:", get_stuff(two=True))
