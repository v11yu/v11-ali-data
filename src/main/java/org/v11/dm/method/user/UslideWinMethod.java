package org.v11.dm.method.user;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.v11.dm.entity.Action;
import org.v11.dm.entity.TmpInfo;
import org.v11.dm.entity.User;
import org.v11.dm.method.UserMethod;

public class UslideWinMethod implements UserMethod{

	@Override
	public void setAttribute(TmpInfo tmpInfo, User u) {
		// TODO Auto-generated method stub
		Set<Long> st = new HashSet<Long>();
		int k=0;
		for(int t:User.SlideWins){
			int c = 0;
			double sum = 0;
			for(int i=0;i<30;i++){
				int cnt = 0;
				int cnt1 = 0;
				st.clear();
				for(int j = tmpInfo.ls.size()-1;j>=0;j--){
					Action r = tmpInfo.ls.get(j);
					if(r.dis >=i*24 && r.dis<(i+1)*24 && r.op == 3&& st.contains(r.tid)){
						cnt ++;
					}
					if(r.dis >=(i+1)*24 && r.dis<(i+t+1)*24 ){
						st.add(r.tid);
						cnt1++;
					}
				}
				if(cnt1!=0){
					sum += (1.0*cnt/st.size());
					c++;
				}
			}
			if(c!=0){
				u.slideWin[k++] = sum/c;
			}
		}
		
	}

}
