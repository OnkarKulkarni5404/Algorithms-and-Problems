import pandas as pd
import numpy as np
import math


def entropy(data,name='Profit'):
    elements,counts=np.unique(data[name],return_counts=True)
    # print(elements)
    # print(counts)
    entropy=0
    for i in range(0,len(elements)):
        entropy+=((-counts[i]/sum(counts))*math.log2(counts[i]/sum(counts)))
    return entropy


def looseEntropy(sub):
    flag=1
    for i in sub:
        if i==0:
            flag=0
    if flag==0:
        return 0
    else:
        sum1 = sum(sub);
        try:
            entro = np.sum([(-sub[i] / sum1) * np.log2(sub[i] / sum1) for i in range(len(sub))])
        except ZeroDivisionError:
            pass
        return entro


def split(data,attrib):
    target_name='Profit'
    elements_of_attrib=np.unique(data[attrib])
    elements_of_target,counts=np.unique(data[target_name],return_counts=True)
    calculated_values=[]
    returned_values=[]
    for i in range(len(elements_of_attrib)):
        calculated_values.append([0]* len(elements_of_target))

    for k in range(0,data.shape[0]):
        for i in range(0, len(elements_of_attrib)):
            for j in range(0, len(elements_of_target)):
                if (data[target_name][k] == elements_of_target[j] and data[attrib][k]==elements_of_attrib[i]):
                    calculated_values[i][j]+=1

    # print(calculated_values)
    for i in calculated_values:
        returned_values.append(looseEntropy(i))
    # print(returned_values)
    sum1=0
    for i in range(0,len(calculated_values)):
            fract=(sum(calculated_values[i])/sum(counts))
            sum1+=fract*returned_values[i]
    #InfoGain of Splitted
    return entropy(data)-sum1





def infogain(data,attrib_name):
    target_name='Profit'
    totat_entropy=entropy(data,target_name)
    vals,counts=np.unique(data[attrib_name],return_counts=True)
    weighted_entropy=entropy(data,attrib_name)
    return (totat_entropy-weighted_entropy)



def main():
    data=pd.read_csv('data.csv')
    # print(data)
    column_names=list(data.head(0))
    for i in column_names[:-1]:
        print(split(data,i))










if __name__ == "__main__":
    main()