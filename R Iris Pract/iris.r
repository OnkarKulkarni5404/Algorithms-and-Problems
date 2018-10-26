#get data from csv to dataframe
data=read.csv("IRIS.csv")
#get column names
print(colnames(data))
#count number of features
ncol(data)
#count number of rows
nrow(data)
#Types of features
for(i in 1:ncol(data)){
  print(colnames(data[i]))
}
#mean,max,avg,min,range,sd,variance,percentile
for(i in 1:(ncol(data)-1))
  {
    print(colnames(data[i]))
    min(data[,i])
    max(data[,i])
    sum(data[,i])/ncol(data)    
    mean(data[,i])
    sd(data[,i])
    var(data[,i])
    median(data[,i])
    range(data[,i])
    
}

#plotting histograms
hist(data$petal_length)
boxplot(data)
