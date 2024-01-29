package com.app.dc.fix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

 
public class BaseMessage extends HashMap{

	/** for group **/
	public boolean hasGroup(String groupName){
		return this.get(groupName)!=null;
	}
	
	public Map getGroup(int index,String groupName){
		if(index<0){
			return null;
		}
		if(!hasGroup(groupName)){
			return null;
		}
		List list=DataTypeConverter.toList(this,groupName);
		if(index>=list.size()){
			return null;
		}
		Object map=list.get(index);
		if(map instanceof Map){
			return (Map)map;
		}
		return null;
	}
	
	public int getGroupCount(String groupName){
		if(!hasGroup(groupName)){
			return 0;
		}
		return DataTypeConverter.toList(this,groupName).size();
	}
	
	public void addGroup(Map group,String groupName){
		if(!hasGroup(groupName)){
			this.put(groupName, new ArrayList());
		}
		List list=DataTypeConverter.toList(this,groupName);
		list.add(group);
	}
	
	public boolean removeGroup(Map group,String groupName){
		if(!hasGroup(groupName)){
			return false;
		}
		List list=DataTypeConverter.toList(this,groupName);
		return list.remove(group);
	}
	
	public Map removeGroup(int index,String groupName){
		if(index<0){
			return null;
		}
		if(!hasGroup(groupName)){
			return null;
		}
		List list=DataTypeConverter.toList(this,groupName);
		if(index>=list.size()){
			return null;
		}
		Object map=list.get(index);
		if(map instanceof Map){
			return (Map)list.remove(index);
		}
		return null;
	}
	
	public Iterator getAllGroup(String groupName){
		if(!hasGroup(groupName)){
			return null;
		}
		List list=DataTypeConverter.toList(this,groupName);
		return list.iterator();
	}
}
