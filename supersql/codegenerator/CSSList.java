package supersql.codegenerator;

public class CSSList {
	private String selector;
	private String property;
	private String value;
	
	public CSSList() {
		/* コンストラクタ */
	}
	
	public CSSList(String selector, String property, String value) {
		this.selector = selector;
		this.property = property;
		this.value = value;
	}
	
	public String getSelector() {
		return selector;
	}
	
	public String getProperty() {
		return property;
	}
	
	public String getValue() {
		return value;
	}
	
}
