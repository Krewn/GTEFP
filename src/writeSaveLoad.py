##

## Reads variable declairation for a class and writes serial read write methods...

## Does not handle kVecs

## writes method bodies not method handle 

## Requires slinput in current directory

##
import sys
InFile = sys.argv[1]
f = open(InFile,"r")
q = f.read()
q = q.split("\n")
q = q[0:len(q)-1]
#print q
class member():
	def __init__(self,t,n):
		self.t = t    # type 
		self.n = n    # name
	def pSave(self):
		return("out.writeObject("+self.n+");\n")
	def pLoad(self):
		return(self.n+"=("+self.t+")in.readObject();\n")

members = []
for k in q:
	p = 0
	while(p<len(k) and (k[p]=="\t" or k[p]==" ")):#skip tabs
		p+=1
	while(p<len(k) and (k[p]!="\t" and k[p]!=" ")):#skip scope
		p+=1
	while(p<len(k) and(k[p]=="\t" or k[p]==" ")):#skip spaces
		p+=1	
	r=""
	while(p<len(k) and k[p]!="\t" and k[p]!=" " and k[p]!=";"): # get the type
		r+=k[p]
		p+=1
	while(p<len(k) and (k[p]=="\t" or k[p]==" ") and k[p]!=";"):#skip spaces
				p+=1
	n = ""
	while(p<len(k) and k[p]!="\t" and k[p]!=" " and k[p]!=";"): # get the className
		n+=k[p]
		p+=1
		if(k[p]==","):
			members.append(member(r,n))
			n = ""
	    	while(p<len(k) and (k[p]=="\t" or k[p]==" " or k[p]==",")):#skip spaces
				p+=1
	members.append(member(r,n))
sb = "super.writeObject(out);\n"
lb = "super.readObject(in);\n"
for m in members:
	sb+=m.pSave()
	lb+=m.pLoad()
print("\n\n")
print(sb)
print("\n\n")
print(lb)



