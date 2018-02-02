#!/usr/bin/python
#
# CIS 472/572 -- Programming Homework #1
#
# Starter code provided by Daniel Lowd, 1/25/2018
#
#
import sys
import re
# Node class for the decision tree
import node
import math


train=None
varnames=None
test=None
testvarnames=None
root=None

# Helper function computes entropy of Bernoulli distribution with
# parameter p
def entropy(p):
        if(p < 1 and p > 0):
                return (-p * math.log(p, 2)) - ((1 - p) * math.log((1 - p), 2));
        return 0;
		
# Compute information gain for a particular split, given the counts
# py_pxi : number of occurences of y=1 with x_i=1 for all i=1 to n
# pxi : number of occurrences of x_i=1
# py : number of ocurrences of y=1
# total : total length of the data
def infogain(py_pxi, pxi, py, total):
        x = 0
        y = 0
        if pxi != 0:
                x = entropy(float(py) / total)
        if total != pxi:
                y = entropy(float(py-py_pxi)/(total-pxi))
                
        return (entropy(float(py) / total) - ((float(pxi) / total) * x + (float(total - pxi) / total) * y))

# OTHER SUGGESTED HELPER FUNCTIONS:
# - collect counts for each variable value with each class label
def count(data, varnames):
        px_count = {}
        px_py_count = {}

        for attr in varnames:
                attr_count = 0
                attr_py_count = 0
                i = varnames.index(attr)
                for row in data:
                        attr_count += row[i];
                        if row[i] == 1 and row[-1] == 1:
                                attr_py_count += 1;
                px_count[attr] = attr_count
                px_py_count[attr] = attr_py_count

        return (px_count, px_py_count, varnames[-1], len(data))
                                        
# - find the best variable to split on, according to mutual information
def find_node(data, varnames):
        counts = count(data, varnames)
        maxIG = 0
        target = varnames[-1]
        bestAttr = None

        for attr in varnames:
                if attr != target:
                        pxi = counts[0][attr]
                        py = counts[0][counts[2]]
                        py_pxi = counts[1][attr]
                        total = counts[3]
                        attrGain = infogain(py_pxi, pxi, py, total)
                        if maxIG < attrGain:
                                maxIG = attrGain
                                bestAttr = attr
        return bestAttr
        
# - partition data based on a given variable
def partition(data, varnames, attr):
        if attr == None:
                return
        
        true = []
        false = []
        for col in data:
                if col[varnames.index(attr)] == 1:
                        true.append(col)
                else:
                        false.append(col)

        return (true, false)
	
	
# Load data from a file
def read_data(filename):
        f = open(filename, 'r')
        p = re.compile(',')
        data = []
        header = f.readline().strip()
        varnames = p.split(header)
        namehash = {}
        for l in f:
                data.append([int(x) for x in p.split(l.strip())])

        return (data, varnames)

# Saves the model to a file.  Most of the work here is done in the
# node class.  This should work as-is with no changes needed.
def print_model(root, modelfile):
        f = open(modelfile, 'w+')
        root.write(f, 0)

# Build tree in a top-down manner, selecting splits until we hit a
# pure leaf or all splits look bad.
def build_tree(data, varnames):
        if len(data) == 0:
                return node.Leaf(varnames, 1)
        
        counts = count(data, varnames)
        
        if counts[0][counts[2]] == counts[3]:
                return node.Leaf(varnames, 1)
        
        elif counts[0][counts[2]] == 0:
                return node.Leaf(varnames, 0)
        
        else:
                split = find_node(data, varnames)
                
                if split == None:
                        return node.Leaf(varnames, 1)
                part = partition(data, varnames, split)
                attr = varnames.index(split)
                return node.Split(varnames, attr, build_tree(part[0], varnames), build_tree(part[1], varnames))
                
        


# "varnames" is a list of names, one for each variable
# "train" and "test" are lists of examples.
# Each example is a list of attribute values, where the last element in
# the list is the class value.
def loadAndTrain(trainS,testS,modelS):
	global train
	global varnames
	global test
	global testvarnames
	global root
	(train, varnames) = read_data(trainS)
	(test, testvarnames) = read_data(testS)
	modelfile = modelS
	
	# build_tree is the main function you'll have to implement, along with
    # any helper functions needed.  It should return the root node of the
    # decision tree.
	root = build_tree(train, varnames)
	print_model(root, modelfile)
	
def runTest():
	correct = 0
	# The position of the class label is the last element in the list.
	yi = len(test[0]) - 1
	for x in test:
		# Classification is done recursively by the node class.
        # This should work as-is.
		pred = root.classify(x)
		if pred == x[yi]:
			correct += 1
	acc = float(correct)/len(test)
	return acc	
	
	
# Load train and test data.  Learn model.  Report accuracy.
def main(argv):
    if (len(argv) != 3):
            print 'Usage: id3.py <train> <test> <model>'
            sys.exit(2)

    loadAndTrain(argv[0],argv[1],argv[2]) 
                    
    acc = runTest()             
    print "Accuracy: ",acc                      

if __name__ == "__main__":
    main(sys.argv[1:])
