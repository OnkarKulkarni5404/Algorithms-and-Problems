#Naive bayes
library(caTools)
library(rpart)
library(rpart.plot)
library(caret)
library(e1071)
set.seed(125)
myd=read.csv('diabetes.csv')

myd$Outcome=factor(myd$Outcome,
                   c(0,1))


split=sample.split(myd,SplitRatio = 0.75)
train_data=subset(myd,split==TRUE)
test_data=subset(myd,split==FALSE)
classifier=naiveBayes(x=train_data,
                      y=train_data$Outcome)

pred=predict(classifier,newdata = test_data)
cm=table(test_data$Outcome,pred)
cm
result=confusionMatrix(pred,test_data$Outcome)
result

#Decision Tree

fit=rpart(train_data$Outcome~.,
          data=train_data,
          method="class")

#rpart.plot(fit)
pred1=predict(fit,newdata = test_data,type = "class")
cm1=table(test_data$Outcome,pred1)
result1=confusionMatrix(pred1,test_data$Outcome)
result1
