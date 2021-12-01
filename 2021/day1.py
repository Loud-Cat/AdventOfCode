# Read input data
with open("Inputs/input1.txt") as f:
  data = [int(i) for i in f]

# PART ONE
total = 0
for i in range(1, len(data)):
  if data[i-1] < data[i]:
    total += 1
print("PART ONE:", total)

# PART TWO
total = 0
window = data[:3]
for i in range(1, len(data)):
  if sum(window) < sum(window := data[i:i+3]):
    total += 1
print("PART TWO:", total)
