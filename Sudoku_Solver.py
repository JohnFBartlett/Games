#!/usr/bin/python

import math, re, sys

BOX_NUMBER = 0
FILLED = []
OPTIONS = []

def main(size, filled):
	# check that size is a square
	# print "sqrt is " + str(math.sqrt(int(size))**2)
	if int(size) != int(math.sqrt(int(size))**2):
		raise ValueError("Inputted size is not a square.")

	# enter number of boxes
	global BOX_NUMBER
	BOX_NUMBER = int(size)
	print "Map is " + str(BOX_NUMBER) + " by " + str(BOX_NUMBER)

	# -------------------------------
	# 2. Create OPTIONS double array
	# -------------------------------
	optionsString = ''
	for i in xrange(BOX_NUMBER):
		optionsString = optionsString + str(i+1)
	filler = [optionsString] * int(size)
	print "string of options is " + optionsString
	# put options string in every node
	global OPTIONS
	FILLED = [0] * BOX_NUMBER
	for i, s in enumerate(FILLED):
		optionsString = ''
		filler = []
		for i in xrange(BOX_NUMBER):
			optionsString = optionsString + str(i+1)
		filler = [optionsString] * int(size)
		FILLED[i] = filler
	print "Options board\n" + str(OPTIONS)
	printable('o')
	OPTIONS[0][1] = "9"
	printable('o')
	OPTIONS[3][1] = "8"
	printable('o')
	OPTIONS[0][2] = "7"
	printable('o')
	OPTIONS[1][1] = "6"
	printable('o')

	# ---------------------
	# 3. Fill in the board 
	# ---------------------
	# first do the initial board
	recent_filled = create_initial_board(filled)

	# then iterate through and do the rest
	while recent_filled:
		toBeFilled = []
		toBeFilled = toBeFilled + check_rows_cols_boxes(recent_filled)
		print "to be filled: " + str(toBeFilled)
		recent_filled = False

# gets type of input (either list or 2D list),
# puts input into FILLED
def create_initial_board(filled):
	recent_filled = []
	# filler = ['-'] * BOX_NUMBER
	# print "BOX_NUMBER is " + str(BOX_NUMBER)
	print "input " + str(filled)
	global FILLED
	global OPTIONS
	FILLED = [0] * BOX_NUMBER
	for i, s in enumerate(FILLED):
		FILLED[i] = ['-'] * BOX_NUMBER
	print "Before setup: "
	printable('f')
	for elem in filled:
		try:
			[xcoor, ycoor, val] = elem
		except ValueError:
			raise ValueError("Inputted list is not formatted correctly.\n " +
			 elem + "Can't be split up.")
		# print str(FILLED)
		print("setting value to " + val + " at " + xcoor + ", " + ycoor)
		FILLED[int(xcoor)][int(ycoor)] = val
		print str(OPTIONS)
		OPTIONS[int(xcoor)][int(ycoor)] = val
		print str(OPTIONS)
		# print str(FILLED)
	print "After Setup: "
	printable('f')
	printable('o')
	return filled

def update_board(toBeFilled):
	recent_filled = []

def check_rows_cols_boxes(recent):
	# recent is a list of tuples
	to_be_filled = []
	global OPTIONS
	for elem in recent:
		[xcoor, ycoor, val] = elem

		for i in xrange(BOX_NUMBER):
			# first check rows
			# --------------------
			node = OPTIONS[i][int(ycoor)]
			if len(node) == 1:
				if node == val and str(i) != xcoor:
					print('val: '+val+' i: ' + str(i) + ' xcoor: ' + xcoor + ' ycoor: ' + ycoor)
					raise ValueError("PUZZLE SOLVED INCORRECTLY. Repeat found in same row.")
				else:
					# in this case, the node has already been solved and can be ignored
					continue
			# else remove val from node if possible
			else:
				print "before row " + node
				node = re.sub(val, '', node)
				printable('o')
				print str(OPTIONS[i][int(ycoor)])
				print "after row " + node
				OPTIONS[i][int(ycoor)] = node
				printable('o')
				# if node length is now 1, add it and its coords to to_be_filled
				if len(node) == 1:
					to_be_filled.append([str(i), ycoor, node])

			# then check cols
			# --------------------
			node = OPTIONS[int(xcoor)][i]
			if len(node) == 1:
				if node == val and i != ycoor:
					raise ValueError("PUZZLE SOLVED INCORRECTLY. Repeat found in same row.")
				else:
					# in this case, the node has already been solved and can be ignored
					continue
			# else remove val from node if possible
			else:
				print "before col " + node
				node = re.sub(val, '', node)
				printable('o')
				print "after col " + node
				OPTIONS[i][int(ycoor)] = node
				printable('o')
				# if node length is now 1, add it and its coords to to_be_filled
				if len(node) == 1:
					to_be_filled.append([xcoor, str(i), node])

		# now check boxes
		interval = int(round(math.sqrt(BOX_NUMBER)))
		row = int(xcoor) % interval
		col = int(ycoor) % interval
		for i in xrange(interval):
			for j in xrange(interval):
				node = OPTIONS[row+i][col+j]
				if len(node) == 1:
					if node == val:
						raise ValueError("PUZZLE SOLVED INCORRECTLY. Repeat found in same row.")
					else:
						# in this case, the node has already been solved and can be ignored
						continue
				# else remove val from node if possible
				else:
					print "before box " + node
					node = re.sub(val, '', node)
					printable('o')
					print "after box " + node
					OPTIONS[i][int(ycoor)] = node
					printable('o')
					# if node length is now 1, add it and its coords to to_be_filled
					if len(node) == 1:
						to_be_filled.append([xcoor, str(i), node])

	return to_be_filled

def printable(inputted):
	out = ''
	board = []
	if inputted == 'o':
		board = OPTIONS
	else:
		board = FILLED
	interval = int(round(math.sqrt(BOX_NUMBER)))
	# print "interval is " + str(interval)
	for j in xrange(BOX_NUMBER):
		for i in xrange(BOX_NUMBER):
			# print str(i)
			if (i+1) == BOX_NUMBER:
				if (j+1) % interval == 0 and (j+1) != BOX_NUMBER:
					out = out + board[i][j] + '\n' + '_______________\n'
					# print "out is a " + out
				else:
					out = out + board[i][j] + '\n'
					# print "out is b " + out
			elif (i+1) % interval == 0:
				out = out + board[i][j] + ' | '
				# print "out is c " + out
			else:
				out = out + board[i][j] + ','
				# print "out is d " + out
	if inputted == 'o':
		print "printable OPTIONS: \n\n" + out
	else: 
		print "printable FILLED: \n\n" + out

# put filled args into a list
filled = sys.argv[2:]
# print filled
for i, s in enumerate(filled):
    filled[i] = s.split()
print "formatted filled " + str(filled)

main(sys.argv[1], filled)
