with open("Inputs/input6.txt", "r") as f:
  data = [i.strip() for i in f]
  timers = [int(i) for i in data[0].strip().split(",")]

def get_population(initial_pop, days):
  def day():
    births = arr.pop(0)
    arr[6] += births
    arr.append(births)

  arr = [0] * 9
  for count in initial_pop:
    arr[count] += 1

  for i in range(days):
    day()
  return sum(arr)

print("Part one:", get_population(timers, 80))
print("Part two:", get_population(timers, 256))
