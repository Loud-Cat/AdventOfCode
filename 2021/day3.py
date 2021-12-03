with open("Inputs/input3.txt") as f:
  data = [i.strip() for i in f]

# PARTS ONE && TWO
def get_stuff(d:list, mostly:bool, part:int) -> int:
  """Read the input and return the appropriate result
  d: data - input list of binary strings
  mostly: True/False - sorted by most-common
  part: 1/2 - determines which part of the puzzle to solve for
  """

  data = d.copy()
  binary = ""
  for i in range(12):
    # Group the data into "every n-th item"
    bits = [*zip(*data)][i]
    # Sort the bits by frequency
    common = [(i, bits.count(i)) for i in set(bits)]
    most = sorted(common, key=lambda x: x[1], reverse=mostly)

    # If both numbers (0 and 1) are equally common...
    if most[0][1] == most[1][1]: most = "01"[mostly]
    else: most = most[0][0]

    if part == 2:
      # Filter the new list by the character per position
      data = [j for j in data if j[i] == most]
      # Stop searching
      if len(data) == 1:
        return int(data[0], 2)

    # Add the most common character to the new binary number
    binary += most
  # int(x, 2) turns binary into decimal (base 2)
  return int(binary, 2)

gamma = get_stuff(data, True, 1) # Using data from the file, sorted by most-common, part one
epsilon = get_stuff(data, False, 1) # Using data from the file, sorted by least-common, part on1
print("Part one:", gamma * epsilon)

o2 = get_stuff(data, True, 2) # Data from file, most-common, part 2
co2 = get_stuff(data, False, 2) # Data from file, least-common, part 2
print("Part two:", o2 * co2)
