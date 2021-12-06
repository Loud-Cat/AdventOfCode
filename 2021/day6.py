with open("Inputs/input6.txt", "r") as f:
  data = [i.strip() for i in f]
  times = [int(i) for i in data[0].strip().split(",")]

class Fish:
  def __init__(self, t=9):
    self.timer = t

  def __repr__(self):
    return f"{self.timer}"
  
  def wait(self):
    self.timer -= 1
    if self.timer < 0:
      self.timer = 6
      fish.append( Fish() )

fish = [Fish(t) for t in times]
for i in range(80):
  for f in fish:
    f.wait()
print("Part one:", len(fish))
