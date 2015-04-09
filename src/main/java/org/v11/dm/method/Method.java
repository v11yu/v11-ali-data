package org.v11.dm.method;

import java.util.Map;

public interface Method{
	<T> void addAttributr(Map<String,T> mp,String str);
	<T> void dealWithAllRecord(Map<String,T> mp);
}
