package supersql.extendclass;

import java.util.ArrayList;
import java.util.Collection;

public class ExtList<T> extends ArrayList<T>{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public ExtList() {
		super();
	}

	public ExtList(Collection<T> c) {
		super(c);
	}

	public ExtList<T> ExtsubList(int fromIndex, int toIndex) {
		return new ExtList<T>(this.subList(fromIndex, toIndex));
	}

	public int contain_itemnum() {
		return this.unnest().size();
	}

	public ExtList<T> unnest() {
		ExtList<T> list = new ExtList<T>();
		for (int i = 0; i < this.size(); i++) {
			T o = this.get(i);
			if (o instanceof ExtList) {
					list.addAll(((ExtList<T>) o).unnest());
			} else {
				list.add((T) o);
			}
		}
		return list;
	}

	public ExtList getExtList(ArrayList<Integer> array){
		int[] in = new int[array.size()];
		int i = 0;
		for (int v: array) {
			in[i] = v;
			i++;
		}
		ExtList result = this.getExtList(in);
		return result;
	}

	public ExtList getExtList(int... value_array){
		ExtList tmp = this;
		for(int i = 0; i < value_array.length; i++){
//			Log.info("tmp"+tmp);
			try{
				tmp = (ExtList)tmp.get(value_array[i]);
			}catch(ClassCastException castException){
				return null;
			}
		}
		return tmp;
	}

	public String getExtListString(ArrayList<Integer> array){
		int[] in = new int[array.size()];
		int i = 0;
		for (int v: array) {
			in[i] = v;
			i++;
		}
		String result = this.getExtListString(in);
		return result;
	}

	public String getExtListString(int... value_array){
		ExtList tmp = this;
		int length = value_array.length;
		for(int i = 0; i < length; i++){
//			Log.info("tmp"+tmp);
			if(tmp.get(value_array[i]) instanceof String || tmp.get(value_array[i]) instanceof Integer){
				String return_value = tmp.get(value_array[i]).toString();
				if(i >= length - 1){
					return return_value;
				}else{
//					Log.err("return value is "+return_value+".");
					break;
				}
			}else{
				try{
					tmp = (ExtList)tmp.get(value_array[i]);
				}catch(ClassCastException castException){
					castException.printStackTrace();
				}
			}
		}
//		Log.err("Index is wrong.");
		return null;
	}


	//remove target from extlist
	//only first target will be removed, and the available type is "String" and "Integer"
	public void removeContent(Object target){
		if(this instanceof ExtList){
			if (this.contains(target)){
				this.remove(this.indexOf(target));
			}
		}
		for(int i = 0; i < this.size(); i++){
			Object child = this.get(i);
			if(child instanceof String || child instanceof Integer ){
				continue;
			}else if(child instanceof ExtList){
				((ExtList)child).removeContent(target);
			}
		}
	}

	public boolean removeNull() {
		boolean flag = false;
		for (int i = 0; i < this.size(); i++) {
			if(this.get(i) instanceof ExtList){
				if(this.getExtList(i).size() == 0){
					this.remove(i);
					flag = true;
				}else{
					flag = (this.getExtList(i).removeNull() || flag);
				}
			}
		}
		return flag;
	}

	public void extRemoveRange(int from, int to){
		this.removeRange(from, to);
	}
}
