/* TFE abstract class */

package supersql.codegenerator;

import java.io.Serializable;

import supersql.extendclass.ExtList;

/**
 *
 *
 *
 */
public interface ITFE extends Serializable {

	/**
	 *
	 * @param count
	 */
	void debugout(int count);

	/**
	 *
	 * @return
	 */
	ExtList<Integer> makesch();

	ExtList makele0();

	String work(ExtList<ExtList<String>> data_info);
	Object createNode(ExtList<ExtList<String>> data_info);

	int countconnectitem();

	void addDeco(String key, Object val);
	void setDeco(DecorateList d);

	void setId(int id);
	int getId();

	void setOrderBy(String order);
	void setAggregate(String aggregate);
	void setGGplot(String ggplot);
	void setCtab(String ctab);

//	void setcheckdeco(boolean decocheck);
//	boolean getcheckdeco();

	ExtList makeschImage();

	void addDeco(String name, String value, String condition);
}
