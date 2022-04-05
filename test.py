import random
import time
alpha = "abcdefghijklmnopqrstuvwxyz"
def main(n):
    with open("RandomTables/admins.csv",'w') as f:
        for i in range(1,n+1):
            f.write(str(i)+"," +"".join([alpha[random.randint(0,len(alpha)-1)] for m in range(50)])+","+"".join([alpha[random.randint(0,len(alpha)-1)] for m in range(50)])+"\n")
    with open("RandomTables/books.csv",'w') as f:
        for i in range(1,n+1):
            f.write(str(i)+',' +"".join([alpha[random.randint(0,len(alpha)-1)] for m in range(50)])+','+"".join([alpha[random.randint(0,len(alpha)-1)] for m in range(50)])+','+str(i)+","+str(i)+"\n")
    with open("RandomTables/librarians.csv",'w') as f:
        for i in range(1,n+1):
            f.write(str(i)+","+"".join([alpha[random.randint(0,len(alpha)-1)] for m in range(50)])+","+"".join([alpha[random.randint(0,len(alpha)-1)] for m in range(50)])+"\n")
    with open("RandomTables/students.csv",'w') as f:
        for i in range(1,n+1):
            f.write(str(i)+","+"".join([alpha[random.randint(0,len(alpha)-1)] for m in range(50)])+","+"".join([str(random.randint(1,31)),'/',str(random.randint(1,12)),"/",str(random.randint(1970,2020))])+","+"".join([alpha[random.randint(0,len(alpha)-1)] for m in range(50)])+"@gmail.com"+","+str(random.randint(1000000000,1299999999))+'\n')
    with open("RandomTables/issues.csv",'w') as f:
        for i in range(1,n+1):
            f.write(str(i)+","+str(random.randint(1,n))+","+str(random.randint(1,n))+','+("true" if random.randint(0,1)==1 else 'false')+"\n")
if __name__=="__main__":
    main(10000)
