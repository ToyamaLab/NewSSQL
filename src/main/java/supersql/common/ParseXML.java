package supersql.common;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ParseXML {
//	// ユーザのホームディレクトリ
//	final static String USER_HOME = System.getProperty("user.home");
//	// OSごとのファイル区切り文字(Windows:"\" , MacとLinux:"/"等)
//	final static String OS_FS = System.getProperty("file.separator");
//	// ファイル名
//	static String decorationsList = "decoration_list.xml";
//	static String mediasList = "medias_list.xml";
//	// parseしたいxmlファイルの絶対パス
//	static String uri = USER_HOME + OS_FS + decorationsList;
//	static String uri2 = USER_HOME + OS_FS + mediasList;
//	// 指定したターゲットのテキストを格納する配列
//	static String[] str_target = new String[100];
//	// 指定したエレメントの属性を格納する配列
//	static String[] str_attr = new String[100];

//	public static void main(String[] args) {
//		try {
//			// ドキュメントビルダーファクトリを生成
//			DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
//			// ドキュメントビルダーを生成
//			DocumentBuilder builder = dbfactory.newDocumentBuilder();
//			// パースを実行してDocumentオブジェクトを取得
//			Document document = builder.parse(uri2);// Document
//			// getTagTexts(document, "connector");
//			getAttributes(document, "media", "name");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
//	// xmlファイルのドキュメントから指定したタグの中身のデータを取得し、配列に格納
//	public static void getTagTexts(Document doc, String target) {
//		NodeList elements = doc.getElementsByTagName(target);
//		for (int i = 0; i < elements.getLength(); i++) {
////			System.out.println(elements.item(i).getTextContent());
//		}
//	}

	// xmlファイルのドキュメントから指定したエレメントの属性を取得し、配列に格納
	public static ArrayList<String> getAttributes(String file, String tagName, String attrName) {
		ArrayList<String> names = new ArrayList<String>();
		Document doc = null;
		try {
			DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbfactory.newDocumentBuilder();
			doc = builder.parse(file);
		
			NodeList elements = doc.getElementsByTagName(tagName);
			for (int i = 0; i < elements.getLength(); i++) {
				names.add(((Element) elements.item(i)).getAttribute(attrName));
			}
		} catch (Exception e) {	}
		return names;
	}
}
