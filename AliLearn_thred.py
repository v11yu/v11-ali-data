__author__ = 'jiangnan'


import numpy as np
import pandas as pd
from sklearn.externals import joblib
from sklearn.metrics import log_loss
from sklearn.ensemble import RandomForestClassifier
f = open("user_merge_validate_div.csv")
#f.readline() # skip the header
df = pd.read_csv(f, engine='c')
#data = np.loadtxt(f,dtype=np.str,delimiter=",")
mat = df.values
df = df.drop(['utp_la21', 'utp_la22', 'utp_la23',  'utp_at1',  'utp_at3',  'utp_atdup1', 'utp_atdup2', 'utp_atdup3']+['Unnamed: 0.1'],axis = 1)

df = df.dropna()
f = open("tianchi_mobile_recommend_train_item.csv")
#f.readline() # skip the header
df1 = pd.read_csv(f)
item = set(df1.values[:,0])
print "pre total line: " + str(len(df))

mat = df[df['tid'].isin(item)].values
#mat = df.values
X = mat[:, 3:-1]
y = mat[:,-1]
print "total line: " + str(len(y))
print "total buy: " + str(sum(y))
print("data load finished!")
clf = joblib.load('./tree/RF_100_div.pkl')
print("model load finished!")
prob = clf.predict_proba(X)
print("predict finished!")
print("total count:" + str(sum(y)))

for j in range(10):
    thred = 0.5+ j*0.03
    print "thredhold: "+ str(thred)
    pre = [1 if prob[i][1] >= thred else 0 for i in range(len(y))]
    #print pre[:200]

    print("predict count:" + str(sum(pre)))


    hit=[1 if pre[i] == y[i] and y[i]==1 else 0 for i in range(len(y))]
    print("hit count: "+   str(sum(hit)))
    p = float(sum(hit))/sum(pre)
    r = float(sum(hit))/sum(y)
    print("precious: " + str(p))
    print("recall: " + str(r))
    print("f value: " + str(2*p*r/(p+r)))

#clf_probs = clf.predict_proba(X_test)
#score = log_loss(y_test, clf_probs)
