package org.v11.dm.method.utp;

import java_cup.action_part;

import org.v11.dm.entity.Action;
import org.v11.dm.entity.TmpInfo;
import org.v11.dm.entity.UTPair;
import org.v11.dm.method.UTPairMethod;

public class HoursMethod implements UTPairMethod {

	@Override
	public void setAttribute(TmpInfo tmpInfo, UTPair utp) {
		// TODO Auto-generated method stub
		for(Action r : tmpInfo.ls){
			for (int i = 0; i < UTPair.Hours.length; i++) {
				if (r.dis <= UTPair.Hours[i]) {
					utp.hours[i][r.op]++;
					break;
				}
			}
		}
	}
}
