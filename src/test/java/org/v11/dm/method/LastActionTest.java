package org.v11.dm.method;

import java.util.Collections;
import java.util.List;

import org.v11.dm.entity.Action;
import org.v11.dm.entity.TmpInfo;
import org.v11.dm.entity.UTPair;
import org.v11.dm.method.utp.HoursMethod;
import org.v11.dm.method.utp.IntervalMethod;
import org.v11.dm.method.utp.IsBuyMethod;
import org.v11.dm.method.utp.LastActionMethod;
import org.v11.dm.method.utp.LastDupActionMethod;
import org.v11.dm.method.utp.LastVsSumMethod;

public class LastActionTest {
	TmpInfo tmpInfo = new TmpInfo();
	UTPair utp = new UTPair();
	public void init(){
		tmpInfo.clas = 1;
		List<Action> ls = tmpInfo.ls;
		ls.add(new Action(3, 0));
		ls.add(new Action(5, 0));
		
		utp.clas = tmpInfo.clas;
		Collections.sort(tmpInfo.ls);
		UTPairMethod methods[] = {new HoursMethod(),
				new IntervalMethod(),
				new IsBuyMethod(),
				new LastActionMethod(),
				new LastDupActionMethod(),
				new LastVsSumMethod()};
		for(UTPairMethod method : methods){
			method.setAttribute(tmpInfo, utp);
		}
		System.out.println(utp.toString());
	}
	public void test(){
		init();
		System.out.println(utp.lastAction[0]);
		System.out.println(utp.lastDupAction[0]);
	}
	public static void main(String[] args) {
		new LastActionTest().test();
	}
}
