package supersql.codegenerator;
import java.util.ArrayList;


public class CSS {
	private ArrayList<String> selector = new ArrayList<String>();
	private ArrayList<ArrayList<String>> element = new ArrayList<ArrayList<String>>();
	
	public CSS() {
		/* コンストラクタ */
	}
	
	public CSS(ArrayList<String> selector, ArrayList<ArrayList<String>> element) {
		this.selector = selector;
		this.element = element;
	}
	
	public int selectorSize() {
		return selector.size();
	}
	
	public String getSelector(int i) {
		return selector.get(i);
	}
	
	public int elementSize() {
		return element.size();
	}
	
	public String getProperty(int i) {
		return element.get(i).get(0);
	}
	
	public String getValue(int i) {
		return element.get(i).get(1);
	}
}
