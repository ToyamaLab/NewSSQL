package supersql.codegenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Asc_Desc {

	public static ArrayList<ArrayList<AscDesc>> asc_desc_Array1 = new ArrayList<>();	//order by strings
	public static ArrayList<String> asc_desc_Array2 = new ArrayList<>();				//asc,desc attributes	 //added by goto 20161113  for distinct order by
	public static ArrayList<AscDesc> asc_desc = new ArrayList<>();
	public static String asc_desc_attributes = "";

	//Asc_Desc
	public Asc_Desc() {

	}
	private static int dynamicTokenCount = 0;
	private static int dynamicCount = 0;
	private static int streamTokenCount = 0;
	private static int streamCount = 0;
	private static int ascCount = 0;
	private static int descCount = 0;

	//addOrderBy
	public void addOrderBy(String order, String token) {
		//Log.info("order="+order+", token="+token);
		int no = 0;
		if(order.toLowerCase().startsWith("asc")){
			try{
				no = Integer.parseInt(order.substring(3));
			}catch(Exception e){
				no = ++ascCount;
			}
			add_asc_desc(no, token+" ASC");
		}else{
			try{
				no = Integer.parseInt(order.substring(4));
			}catch(Exception e){
				no = ++descCount;
			}
			add_asc_desc(no, token+" DESC");
		}
		asc_desc_attributes += ", "+token;		//added by goto 20161113  for @dynamic: distinct order by
	}

	//dynamicTokenProcess
	//added by goto 170604 for asc/desc@dynamic
	public void dynamicTokenProcess() {
		dynamicTokenCount++;
		if(dynamicTokenCount%2==0){
			int previousDynamicCount = dynamicTokenCount/2-1;
			try {
				Asc_Desc.asc_desc_Array2.get(previousDynamicCount);
			} catch (Exception e) {
				asc_desc_Array1.add(previousDynamicCount, new ArrayList<AscDesc>());
				asc_desc_Array2.add(previousDynamicCount, "");	//added by goto 20161113  for @dynamic: distinct order by
				asc_desc = new ArrayList<AscDesc>();
				asc_desc_attributes = "";
				setDynamicCount(getDynamicCount()+1);
			}
		}
	}

	public static ArrayList<String> streamPeriod = new ArrayList<>();
	public void streamTokenProcess(String value) {
		streamTokenCount++;
		if(streamTokenCount%2==0){
			int previousStreamCount = streamTokenCount/2-1;
			try {
				Asc_Desc.asc_desc_Array2.get(previousStreamCount);
				streamPeriod.add(previousStreamCount, value);
			} catch (Exception e) {
				asc_desc_Array1.add(previousStreamCount, new ArrayList<AscDesc>());
				asc_desc_Array2.add(previousStreamCount, "");
				Asc_Desc.asc_desc_Array2.get(previousStreamCount);
				asc_desc = new ArrayList<AscDesc>();
				asc_desc_attributes = "";
				streamPeriod.add(previousStreamCount, value);
				setStreamCount(getStreamCount()+1);
			}
		}
	}

	//add1
	public void add_asc_desc_Array(String deco) {
		if(deco.contains("dynamic") && !asc_desc_attributes.isEmpty()){

			asc_desc_Array1.add(dynamicCount, asc_desc);
			asc_desc_Array2.add(dynamicCount, asc_desc_attributes);	//added by goto 20161113  for @dynamic: distinct order by
			asc_desc = new ArrayList<AscDesc>();
			asc_desc_attributes = "";
			setDynamicCount(getDynamicCount()+1);
		}

		if(deco.contains("stream") && !asc_desc_attributes.isEmpty()){

			asc_desc_Array1.add(streamCount, asc_desc);
			asc_desc_Array2.add(streamCount, asc_desc_attributes);
			asc_desc = new ArrayList<AscDesc>();
			asc_desc_attributes = "";
			setStreamCount(getStreamCount()+1);
		}
	}

	//setDynamicCount
	private void setDynamicCount(int num) {
		dynamicCount = num;
	}
	//getDynamicCount
	private int getDynamicCount() {
		return dynamicCount;
	}

	//setStreamCount
	private void setStreamCount(int num) {
		streamCount = num;
	}
	//getStreamCount
	private int getStreamCount() {
		return streamCount;
	}

	//add2
	private void add_asc_desc(int no, String AscDesc) {
		//System.out.println(no+" "+AscDesc);
		asc_desc.add(new AscDesc(no, AscDesc));
	}

	//get1
	public ArrayList<AscDesc> get_asc_desc_Array1(int ASC_DESC_ARRAY_COUNT) {
		try {
			return asc_desc_Array1.get(ASC_DESC_ARRAY_COUNT);
		} catch (Exception e) {
			return new ArrayList<AscDesc>();
		}
	}
	//get2
	public String get_asc_desc_Array2(int ASC_DESC_ARRAY_COUNT) {
		try {
			return asc_desc_Array2.get(ASC_DESC_ARRAY_COUNT);
		} catch (Exception e) {
			return "";
		}
	}

	//sorting for (asc1)/(desc1)
	public void sorting() {
		Collections.sort(asc_desc, new AscDescComparator());
	}


	//AscDesc
	public class AscDesc {
		private int no;
		private String ascDesc;

		public AscDesc(int no, String ascDesc) {
			this.no = no;
			this.ascDesc = ascDesc;
		}
		public int getNo(){
			return this.no;
		}
		public String getAscDesc(){
			return this.ascDesc;
		}
	}
	//AscDescComparator
	public class AscDescComparator implements Comparator<AscDesc> {
		public int compare(AscDesc a, AscDesc b) {
			int no1 = a.getNo();
			int no2 = b.getNo();
			//asc sort
			if (no1 > no2) {
				return 1;
			} else if (no1 == no2) {
				return 0;
			} else {
				return -1;
			}
		}
	}

}
