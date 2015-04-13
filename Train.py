__author__ = 'jiangnan'
from sklearn.externals import joblib
import numpy as np
import pandas as pd
from sklearn.metrics import log_loss
from sklearn.ensemble import RandomForestClassifier

f = open("user_merge_train_div.csv")
#f.readline() # skip the header
df = pd.read_csv(f)

# get p collection
f = open("tianchi_mobile_recommend_train_item.csv")
#f.readline() # skip the header
df1 = pd.read_csv(f)
item = set(df1.values[:,0])
print "pre total line: " + str(len(df))

df = df[df['tid'].isin(item)]
df = df.drop_duplicates()

#sample
buyMat = df[df.clas == 1]
noBuyMat = df[df.clas == 0]
df = []
sampler = np.random.permutation(len(noBuyMat))
noBuyMat.take(sampler)

noBuyMat =noBuyMat.head(len(buyMat)*200)
sample = buyMat.append(buyMat).append(noBuyMat).append(buyMat).append(buyMat)
print "total count: " + str(len(sample))

sample = sample.drop(['utp_la21', 'utp_la22', 'utp_la23',  'utp_at1',  'utp_at3',  'utp_atdup1', 'utp_atdup2', 'utp_atdup3']+['Unnamed: 0.1'],axis = 1)
mat = sample.values
X = mat[:, 3:-1]
y = mat[:,-1]
print "load data finished!"
print "buy count: "+ str(sum(y))

#f.readline() # skip the header
clf = RandomForestClassifier(n_estimators=100,max_features='sqrt',n_jobs=-1)
clf.fit(X, y)
print "train finished! writing..."
joblib.dump(clf, './tree/RF_100_div.pkl')