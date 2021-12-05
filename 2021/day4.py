with open("Inputs/input4.txt") as f:
  lines = [i for i in f]
numbers = eval(f"[{lines[0]}]")

boards = []
for i in lines[1:]:
  if not i.strip():
    boards.append([])
  else: boards[-1].append( [int(n) for n in i.split()] )

windexes = [] # winning indexes haha
def search():
  for n in numbers:
    for b in boards:
      if boards.index(b) in windexes: continue
      for row in b:
        while n in row: row[row.index(n)] = "x"
        if row.count("x") == 5:
          windexes.append( boards.index(b) )
          return (b, n)

      for i in range(5):
        col = list( zip(*b) )[i]
        if col.count("x") == 5:
          windexes.append( boards.index(b) )
          return (b, n)

def score(board):
  total = 0
  for row in board:
    while "x" in row: row[row.index("x")] = 0
    total += sum(row)
  return total

winner, number = search()
total = score(winner)
print("Part one:", total * number)

for b in boards[1:]:
  winner, number = search()

total = score(winner)
print("Part two:", total * number)
