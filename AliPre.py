__author__ = 'jiangnan'
__author__ = 'jiangnan'


import numpy as np
import pandas as pd
from sklearn.externals import joblib
from sklearn.metrics import log_loss
from sklearn.ensemble import RandomForestClassifier
f = open("user_merge_submit_div.csv")
#f.readline() # skip the header
df = pd.read_csv(f)
df = df.drop(['utp_la21', 'utp_la22', 'utp_la23',  'utp_at1',  'utp_at3',  'utp_atdup1', 'utp_atdup2', 'utp_atdup3']+['Unnamed: 0.1'],axis = 1)

df = df.dropna()

#data = np.loadtxt(f,dtype=np.str,delimiter=",")
mat = df.values

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
clf = joblib.load('./tree/RF_100_sub411.pkl')
print("model load finished!")
prob = clf.predict_proba(X)
print("predict finished!")
print("total count:" + str(sum(y)))

tmp = zip(prob[:,1], mat[:,1], mat[:, 2])
tmp.sort(reverse=True)
umap = {}
count = 0
for tup in tmp:
    if count == 600:
        break
    if tup[1] in umap:
        if len(umap[tup[1]])< 100:
            umap[tup[1]].append(tup[2])
            print tup[0]
            count+=1
    else:
        umap[tup[1]]= []
        umap[tup[1]].append(tup[2])
        count+=1

f = open('result_4_11div_jn.csv','w')
f.writelines("user_id,item_id\n")
for key,values in umap.items():
    for val in values:
        f.write(str(int(key))+","+str(int(val))+"\n")

pre = [1 if prob[i][1] >= 0.5 else 0 for i in range(len(y))]
print("predict count:" + str(sum(pre)))

f = open('result_4_11.csv','w')

f.writelines("user_id,item_id\n")
for i in range(len(mat)):
    if pre[i] == 1:
        f.write(str(int(mat[i,1]))+","+str(int(mat[i,2]))+"\n")

#clf_probs = clf.predict_proba(X_test)
#score = log_loss(y_test, clf_probs)
