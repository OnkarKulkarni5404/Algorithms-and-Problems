import matplotlib.pyplot as mp
import numpy as np
from numpy.polynomial.polynomial import polyfit


def calxbar(x):
    return sum(x)/len(x)

def calybar(y):
    return sum(y)/len(y)

def calxybar(x,y):
    sum=0
    for i in range(0,len(x)):
        sum=sum+(x[i]*y[i])
    return sum/len(x)

def calxsqbar(x):
    sum=0;
    for i in x:
        sum=sum+i**2
    return sum/len(x)






def main():
    x = [10, 9, 2, 15, 10, 16, 11, 16]
    y = [95, 80, 10, 50, 45, 98, 38, 93]
    xbar=calxbar(x)
  # print(xbar)
    ybar=calybar(y)
  # print(ybar)
    xybar=calxybar(x,y)
  # print(xybar)
    xsqbar=calxsqbar(x)
  # print(xsqbar)
    m=((xbar*ybar)-xybar)/((xbar**2)-xsqbar)
  # print(m)
    yinter=ybar-m*xbar
  # print(yinter)

    mp.scatter(x,y)
    npx=np.array(x)
    npy=np.array(y)

    ypred=m*npx+yinter

    b, mi = polyfit(npx, npy, 1)
    mp.plot(npx, b + mi * npx, '-',color="r")
    mp.show()

if __name__ == "__main__":
    main()

