##counts the lines of code in all sub directories of cwd

import os  
def countHere():
	files = []
	for fn in os.listdir('.'):
		 if os.path.isfile(fn):
		    files.append(fn)
	FilesText = []
	l = 0
	for k in files:
		f = open(k,"r")
		q = f.read()
		l+=len(q.split('\n'))
	return(l)	

l = 0
for fn in os.listdir('.'):
	if(os.path.isdir(fn)):
		os.chdir(fn)
		l+=countHere()
		os.chdir('..')

print('Project contains '+str(l)+' lines of Code.')
