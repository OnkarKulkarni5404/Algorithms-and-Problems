import numpy as np
import pandas as pd
import math
import matplotlib
import numpy as np

def entropy(target_col):
    elements, counts = np.unique(target_col, return_counts=True)
    entropy = np.sum(
        [(-counts[i] / np.sum(counts)) * np.log2(counts[i] / np.sum(counts)) for i in range(len(elements))])
    return entropy


def InfoGain(data, split_attribute_name, target_name="Profit"):
    total_entropy = entropy(data[target_name])

    vals, counts = np.unique(data[split_attribute_name], return_counts=True)

    # Calculate the weighted entropy
    Weighted_Entropy = np.sum(
        [(counts[i] / np.sum(counts)) * entropy(data.where(data[split_attribute_name] == vals[i]).dropna()[target_name])
         for i in range(len(vals))])

    # Calculate the information gain
    Information_Gain = total_entropy - Weighted_Entropy
    return Information_Gain


def buildit(data,column_names,class_v):
    if(np.unique(data[class_v]).shape[0]<=1):
         return np.unique(data[class_v])[0]

    if(data.columns.shape[0]<=1):
        return np.unique(data[class_v])[0]

    findroot=[]
    for i in column_names:
        findroot.append(InfoGain(data,i,class_v))
    best_value_is=findroot.index(max(findroot))
    best_value_name_is=column_names[findroot.index(max(findroot))]

    column_names=[i for i in column_names if i!=best_value_name_is]
    tree={best_value_name_is:{}}
    elements=np.unique(data[best_value_name_is])


    for i in elements:
        subdata=data[data[best_value_name_is]==i]
        subdata = subdata.drop([best_value_name_is], axis=1)
        subtree=buildit(subdata,column_names,class_v)
        tree[best_value_name_is][i]=subtree
    return tree


def main():
    data=pd.read_csv('data.csv')
    column_names=list(data.head(0))
    class_v=column_names[len(column_names)-1]
    column_names=column_names[:-1]
    tree=buildit(data,column_names,class_v)
    print(tree)

if __name__ == "__main__":
    main()

