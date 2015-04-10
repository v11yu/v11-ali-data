package org.v11.dm.method;

import java.util.ArrayList;
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

public class MethodTest {
	TmpInfo tmpInfo = new TmpInfo();
	public void test(){
		tmpInfo.clas = 1;
		List<Action> ls = tmpInfo.ls;
		ls.add(new Action(3, 1));
		ls.add(new Action(5, 3));
		ls.add(new Action(10, 3));
		ls.add(new Action(24, 1));
		ls.add(new Action(7, 1));
		ls.add(new Action(16, 1));
		UTPair utp = new UTPair();
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
	public static void main(String[] args) {
		new MethodTest().test();
	}
}
