package supersql.codegenerator.HTML;

import java.io.File;
import java.io.FileInputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;

import supersql.common.Log;

public class HTMLoptimizer {
	static int gcd(int n, int m) {
		int r = n % m;
		if (r == 0)
			return m;
		else
			return gcd(m, r);
	}

	static int lcm(int n, int m) {
		return n / gcd(n, m) * m;
	}

	int[] a = new int[1024];

	int[] b = new int[1024];

	StringBuffer htmlcode = new StringBuffer();

	int num = 0;

	int oldtr = 0;

	int tabletypeoldtr = 0;

	int tr;

	protected Element root;

	public HTMLoptimizer() {
	}

	public void countTrAndTd(Node n) {
		for (Node ch = n.getFirstChild(); ch != null; ch = ch.getNextSibling()) {
			// class
			if (ch.getNodeType() == Node.ELEMENT_NODE) {
				Element nowNode = (Element) ch;
				String s = null;
				if (nowNode.hasAttribute("class")) {
					s = nowNode.getAttribute("class");
				}
				Node pr = ch.getParentNode();
				if (pr != null && pr.getNodeName() != "#document") {
					Element parentNode = (Element) pr;
					if (parentNode.hasAttribute("class")) {
						if (s == null) {
							s = parentNode.getAttribute("class");
						} else {
							s = parentNode.getAttribute("class") + " " + s;
						}
						nowNode.setAttribute("class", s);
					}
				}
			}
			if (ch.getNodeName().equals("VALUE")
					|| ch.getNodeName().equals("EMBED")) {// �ͤΤȤ�
				// �ͤΤȤ���tr=1 td=1�Ǥ���
				Element nowNode = (Element) ch;
				nowNode.setAttribute("tr", Integer.toString(1));
				nowNode.setAttribute("td", Integer.toString(1));
			} else if (ch.getNodeType() == Node.ELEMENT_NODE) {// �ͤǤϤʤ��Ρ��ɤΤȤ�
				// �Ƶ�Ū�˸ƤӽФ��Ʋ������
				countTrAndTd(ch);

				Element nowNode = (Element) ch;

				// C1�ΤȤ��ν���
				if ((nowNode.getAttribute("type").equals("connect"))
						&& (nowNode.getAttribute("dimension").equals("1"))) {// C1
					int valueTr = 1, valueTd = 0;
					for (Node child = ch.getFirstChild(); child != null; child = child
							.getNextSibling()) {
						Element childNode = (Element) child;
						if (childNode.hasAttribute("td")) {// attr:td������Ȥ�
							// nest�ΤȤ��ν���
							if (childNode.getAttribute("tabletype").equals(
									"nest")) {
								valueTd++;
								// plus 1
							} else {
								valueTd += Integer.parseInt(childNode
										.getAttribute("td"));
								// �ҥΡ��ɤ�Td��­����碌
							}
						}
						if (childNode.hasAttribute("tr")) {
							int tmp;
							if (childNode.getAttribute("tabletype").equals(
									"nest")) {
								tmp = 1;
								// �ͥ��ȤΤȤ���1�Ȥ���
							} else {
								tmp = Integer.parseInt(childNode
										.getAttribute("tr"));
								// �ҥΡ��ɤ�TR���äƤ���
							}
							valueTr = lcm(tmp, valueTr);// �������礦�����Ф�������
						}
						if (!childNode.getAttribute("tabletype").equals("nest")
								&& !ch.getNodeName().equals("SSQL")) {
							childNode.setAttribute("oldtd",
									childNode.getAttribute("td"));
							childNode.setAttribute("oldtr",
									childNode.getAttribute("tr"));
							childNode.removeAttribute("td");
							childNode.removeAttribute("tr");
						}
					}
					nowNode.setAttribute("td", Integer.toString(valueTd));
					nowNode.setAttribute("tr", Integer.toString(valueTr));
				}

				// C2�ΤȤ��ν���
				if ((nowNode.getAttribute("type").equals("connect"))
						&& (nowNode.getAttribute("dimension").equals("2"))) {// C2
					int valueTr = 0, valueTd = 1;
					for (Node child = ch.getFirstChild(); child != null; child = child
							.getNextSibling()) {
						Element childNode = (Element) child;
						if (childNode.hasAttribute("tr")) {// attr:td������Ȥ�
							// nest�ΤȤ��ν���
							if (childNode.getAttribute("tabletype").equals(
									"nest")) {
								valueTr++;
								// plus 1
							} else {
								valueTr += Integer.parseInt(childNode
										.getAttribute("tr"));
								// �ҥΡ��ɤ�Tr��­����碌
							}
						}
						if (childNode.hasAttribute("td")) {
							int tmp;
							if (childNode.getAttribute("tabletype").equals(
									"nest")) {
								tmp = 1;
								// �ͥ��ȤΤȤ���1�Ȥ���
							} else {
								tmp = Integer.parseInt(childNode
										.getAttribute("td"));
								// �ҥΡ��ɤ�Td���äƤ���
							}
							valueTd = lcm(tmp, valueTd);// �������礦�����Ф�������
						}
						if (!childNode.getAttribute("tabletype").equals("nest")
								&& !ch.getNodeName().equals("SSQL")) {
							childNode.setAttribute("oldtd",
									childNode.getAttribute("td"));
							childNode.setAttribute("oldtr",
									childNode.getAttribute("tr"));
							childNode.removeAttribute("td");
							childNode.removeAttribute("tr");
						}
					}
					nowNode.setAttribute("tr", Integer.toString(valueTr));
					nowNode.setAttribute("td", Integer.toString(valueTd));
				}
			}
		}
	}

	public void createTable(Node n, Node h) {
		int valuenum = 0;
		for (Node ch = n.getFirstChild(); ch != null; ch = ch.getNextSibling()) {
			if (ch.getNodeType() == Node.ELEMENT_NODE) {
				Element nowNode = (Element) ch;
				Node par = h;
				/* �������֤η׻� */
				int x = 0, y = 0;
				if (num != 0) {
					for (int i = 0; i < num; i++) {
						y -= b[i];
					}
				}
				// �ơ��������η���򤪤äƤ���
				for (Node ch2 = ch.getParentNode(); ch2 != null; ch2 = ch2
						.getParentNode()) {
					Element parentNode;
					if (ch2.getNodeType() == Node.ELEMENT_NODE) {
						parentNode = (Element) ch2;
					} else {
						continue;
					}
					int fr = 0;
					// �⤷���˷��郎�����餽�Τ֤�����������֤򤺤餹��
					if (parentNode.hasAttribute("frontval")) {
						for (Node tmp = parentNode.getPreviousSibling(); tmp != null; tmp = tmp
								.getPreviousSibling()) {
							Element brotherNode = null;
							if (tmp.getNodeType() == Node.ELEMENT_NODE) {
								brotherNode = (Element) tmp;
							} else {
								continue;
							}
							Element tmp2 = (Element) tmp.getParentNode();
							if (brotherNode.hasAttribute("colspan")
									|| brotherNode.hasAttribute("rowspan")) {
								if (tmp2.getAttribute("dimension").equals("1")
										&& !brotherNode.getAttribute("colspan")
												.equals("")) {
									fr += Integer.parseInt(brotherNode
											.getAttribute("colspan"));
								} else if (tmp2.getAttribute("dimension")
										.equals("2")
										&& !brotherNode.getAttribute("rowspan")
												.equals("")) {
									fr += Integer.parseInt(brotherNode
											.getAttribute("rowspan"));
								}
							} else {
								fr++;
							}
						}
					}
					Element grandP = null;
					if (parentNode.getParentNode().getNodeType() == Node.ELEMENT_NODE)
						grandP = (Element) parentNode.getParentNode();
					if (grandP != null
							&& grandP.getAttribute("type").equals("connect")
							&& grandP.getAttribute("dimension").equals("2")) {// ���Τ��䤬C2�ΤȤ�
						y += fr;
					}
				}
				// ���ޤΥΡ��ɤ����ΥΡ���
				for (Node ch2 = ch.getPreviousSibling(); ch2 != null
						&& ch2.getNodeName() != "#document"
						&& ch2.getNodeName() != "#text"; ch2 = ch2
						.getPreviousSibling()) {
					int fr = 0;
					Element brotherNode = (Element) ch2;
					if (ch2.getNodeName().equals("VALUE")
							&& brotherNode.getAttribute("tabletype").equals(
									"nest")) {
						Element parentNode = (Element) ch2.getParentNode();
						if (parentNode.getAttribute("type").equals("connect")
								&& parentNode.getAttribute("dimension").equals(
										"2")) {
							if (brotherNode.hasAttribute("rowspan")) {
								y += Integer.parseInt(brotherNode
										.getAttribute("rowspan"));
							}
						}
					} else if (brotherNode.getAttribute("type").equals(
							"connect")
							&& brotherNode.getAttribute("dimension")
									.equals("1")) {// ���礦����C1�ΤȤ�
						if (brotherNode.hasAttribute("rowspan")
								&& brotherNode.getAttribute("rowspan") != ""
								&& !brotherNode.getAttribute("tabletype")
										.equals("nest")) {
							fr = Integer.parseInt(brotherNode
									.getAttribute("rowspan"));
						}
						y += fr;
					} else if (brotherNode.getAttribute("type").equals(
							"connect")
							&& brotherNode.getAttribute("dimension")
									.equals("2")) {// ���礦����C2�ΤȤ�
						if (brotherNode.hasAttribute("colspan")
								&& brotherNode.getAttribute("colspan") != ""
								&& !brotherNode.getAttribute("tabletype")
										.equals("nest")) {
							fr = Integer.parseInt(brotherNode
									.getAttribute("colspan"));
						}
					}
				}

				Element parentNode = (Element) ch.getParentNode();
				if (parentNode.getAttribute("type").equals("connect")
						&& parentNode.getAttribute("dimension").equals("2")) {/*
																			 * C2
																			 * VALUE
																			 */
					y += valuenum;
				}

				if (nowNode.getAttribute("tabletype").equals("nest")
						&& !ch.getNodeName().equals("VALUE")) {
					a[num] = x;
					b[num] = y;
					num++;
				}

				/*
				 * disp for(int i=0;i<num;i++){ Log.out(a[i]+", " + b[i]+", " +
				 * i + "/" + num); }
				 */

				/* �������֤η׻������ */

				// rowspan,colspan�η���

				if (nowNode.getAttribute("type").equals("connect")
						&& nowNode.getAttribute("dimension").equals("1")) {// node��C1�ΤȤ�
					// childnode��rowspan:���ߤΥΡ��ɤ�rowspan(̵������tr)
					// childnode��colspan:
					// �����ߤΥΡ��ɤ�colspan��⤿�ʤ��Ȥ��ϻҥΡ���td
					// ���ҥΡ��ɤ�td�����¤����ߤΥΡ��ɤ�colspan�ΤȤ��ϻҥΡ���td
					// ������ʳ��ΤȤ��ϼ�
					for (Node tmp = ch.getFirstChild(); tmp != null; tmp = tmp
							.getNextSibling()) {
						Element childNode = (Element) tmp;
						int maxtd = 0;
						if (nowNode.hasAttribute("tr")) {
							childNode.setAttribute("rowspan",
									nowNode.getAttribute("tr"));
							maxtd = Integer
									.parseInt(nowNode.getAttribute("td"));
						} else if (nowNode.hasAttribute("rowspan")) {
							childNode.setAttribute("rowspan",
									nowNode.getAttribute("rowspan"));
							maxtd = Integer.parseInt(nowNode
									.getAttribute("colspan"));
						}
						int needTd = 0;
						int nowTd = 0;
						for (Node tmp2 = ch.getFirstChild(); tmp2 != null; tmp2 = tmp2
								.getNextSibling()) {
							Element brotherNode = (Element) tmp2;
							if (tmp2 == tmp) {
								if (brotherNode.hasAttribute("oldtd")
										&& !brotherNode.getAttribute("oldtd")
												.isEmpty()) {
									nowTd += Integer.parseInt(brotherNode
											.getAttribute("oldtd"));
								} else if (brotherNode
										.getAttribute("tabletype").equals(
												"nest")) {
									nowTd++;
								} else if (tmp2.getNodeName().equals("VALUE")
										|| tmp2.getNodeName().equals("EMBED")) {
									nowTd++;
								}
								continue;
							}
							if (brotherNode.hasAttribute("oldtd")
									&& !brotherNode.getAttribute("oldtd")
											.isEmpty()) {
								needTd += Integer.parseInt(brotherNode
										.getAttribute("oldtd"));
							} else if (brotherNode.getAttribute("tabletype")
									.equals("nest")) {
								needTd++;
							} else if (tmp2.getNodeName().equals("VALUE")
									|| tmp2.getNodeName().equals("EMBED")) {
								needTd++;
							}
						}
						if (nowTd + needTd == maxtd) {
							if (childNode.hasAttribute("oldtd"))
								childNode.setAttribute("colspan",
										childNode.getAttribute("oldtd"));
							else
								childNode.setAttribute("colspan", "1");

						} else {
							int oldTd = 1;
							int choldTd = 1;
							if (nowNode.hasAttribute("oldtd")) {
								oldTd = Integer.parseInt(nowNode
										.getAttribute("oldtd"));
							}
							if (childNode.hasAttribute("oldtd")) {
								choldTd = Integer.parseInt(childNode
										.getAttribute("oldtd"));
							}
							int set = maxtd / oldTd * choldTd;
							childNode.setAttribute("colspan",
									Integer.toString(set));
							// Log.out(maxtd +" "+ oldTd + " " +choldTd
							// +"set colspan is " +set);
						}
					}
				}
				if (nowNode.getAttribute("type").equals("connect")
						&& nowNode.getAttribute("dimension").equals("2")) {// node��C2�ΤȤ�
					// childnode��rowspan:
					// �����ߤΥΡ��ɤ�rowspan��⤿�ʤ��Ȥ��ϻҥΡ���tr
					// ���ҥΡ��ɤ�tr�����¤����ߤΥΡ��ɤ�rowspan�ΤȤ��ϻҥΡ���tr
					// ������ʳ��ΤȤ��ϼ�
					// childnode��colspan:���ߤΥΡ��ɤ�colspan(̵������td)
					for (Node tmp = ch.getFirstChild(); tmp != null; tmp = tmp
							.getNextSibling()) {
						Element childNode = (Element) tmp;
						;
						int maxtr = 0;
						if (nowNode.hasAttribute("td")) {
							childNode.setAttribute("colspan",
									nowNode.getAttribute("td"));
							maxtr = Integer
									.parseInt(nowNode.getAttribute("tr"));
						} else if (nowNode.hasAttribute("colspan")) {
							childNode.setAttribute("colspan",
									nowNode.getAttribute("colspan"));
							maxtr = Integer.parseInt(nowNode
									.getAttribute("rowspan"));
						}

						int needTr = 0;
						int nowTr = 0;
						for (Node tmp2 = ch.getFirstChild(); tmp2 != null; tmp2 = tmp2
								.getNextSibling()) {
							Element brotherNode = (Element) tmp2;
							if (tmp2 == tmp) {
								if (brotherNode.hasAttribute("oldtr")
										&& !brotherNode.getAttribute("oldtr")
												.equals("")) {
									nowTr += Integer.parseInt(brotherNode
											.getAttribute("oldtr"));
								} else if (brotherNode
										.getAttribute("tabletype").equals(
												"nest")) {
									nowTr++;
								} else if (tmp2.getNodeName().equals("VALUE")
										|| tmp2.getNodeName().equals("EMBED")) {
									nowTr++;
								}
								continue;
							}
							if (brotherNode.hasAttribute("oldtr")
									&& !brotherNode.getAttribute("oldtr")
											.equals("")) {
								needTr += Integer.parseInt(brotherNode
										.getAttribute("oldtr"));
							} else if (brotherNode.getAttribute("tabletype")
									.equals("nest")) {
								needTr++;
							} else if (tmp2.getNodeName().equals("VALUE")
									|| tmp2.getNodeName().equals("EMBED")) {
								needTr++;
							}
						}
						if ((nowTr + needTr) == maxtr) {
							if (childNode.hasAttribute("oldtr"))
								childNode.setAttribute("rowspan",
										childNode.getAttribute("oldtr"));
							else
								childNode.setAttribute("rowspan", "1");
						} else {
							int oldTr = 1;
							int choldTr = 1;
							if (nowNode.hasAttribute("oldtr")) {
								oldTr = Integer.parseInt(nowNode
										.getAttribute("oldtr"));
							}
							if (childNode.hasAttribute("oldtr")) {
								choldTr = Integer.parseInt(childNode
										.getAttribute("oldtr"));
							}
							int set = maxtr / oldTr * choldTr;
							childNode.setAttribute("rowspan",
									Integer.toString(set));
						}
					}
				}

				/*** tr�κ��� ***/

				nowNode.getAttribute("td");
				if (nowNode.hasAttribute("tr")) {// tr������ʤ�ơ��֥�<TR>�����
					tr = Integer.parseInt(nowNode.getAttribute("tr"));
					if (!nowNode.hasAttribute("td")) {
						nowNode.setAttribute("td",
								nowNode.getAttribute("child"));
						nowNode.getAttribute("child");
					}
					if (h.getNodeName() == "TR") {// TR��Ĥ��ä����Ȥ�����
						par = h.getParentNode();
						if (ch.getPreviousSibling() != null) {
							Element brNode = (Element) ch.getPreviousSibling();
							if (brNode.hasAttribute("tr")) {
								oldtr = Integer.parseInt(brNode
										.getAttribute("tr"));
							}
						} else {
							oldtr = 0;
						}
					} else {
						par = h;
						oldtr = 0;
					}
					// tabletype=nest�ΤȤ�

					if (nowNode.getAttribute("tabletype").equals("nest")
							&& !nowNode.getNodeName().equals("VALUE")) {
						int i = 0;
						for (Node tmp = h; tmp != null; tmp = tmp
								.getNextSibling()) {
							if (i == y) {
								par = tmp;
								break;
							}
							i++;
						}
						Document doc = par.getOwnerDocument();
						Element strtd = null;
						if (!parentNode.getAttribute("tabletype")
								.equals("none")) {
							strtd = doc.createElement("TD");
							par.appendChild(strtd);
						}
						if (nowNode.hasAttribute("valign")) {
							strtd.setAttribute("valign",
									nowNode.getAttribute("valign"));
						}
						if (nowNode.hasAttribute("height")) {
							strtd.setAttribute("height",
									nowNode.getAttribute("height"));
						}
						Element strtable = doc.createElement("TABLE");
						if (nowNode.hasAttribute("tableborder")) {
							strtable.setAttribute("border",
									nowNode.getAttribute("tableborder"));
						} else {
							strtable.setAttribute("border", "1");
						}
						if (nowNode.hasAttribute("class")) {
							strtable.setAttribute("class",
									nowNode.getAttribute("class"));
						}
						if (nowNode.hasAttribute("align")) {
							strtable.setAttribute("align",
									nowNode.getAttribute("align"));
						}
						if (nowNode.hasAttribute("cellspacing")) {
							strtable.setAttribute("cellspacing",
									nowNode.getAttribute("cellspacing"));
						}
						if (nowNode.hasAttribute("cellpadding")) {
							strtable.setAttribute("cellpadding",
									nowNode.getAttribute("cellpadding"));
						}
						if (!parentNode.getAttribute("tabletype")
								.equals("none")) {
							par = strtd.appendChild(strtable);
						} else {
							par = par.appendChild(strtable);
						}
						tabletypeoldtr = oldtr;
						oldtr = 0;
					}

					for (int i = 0; i < tr; i++) {
						Document doc = par.getOwnerDocument();
						Element strtr = doc.createElement("TR");
						par.appendChild(strtr);
					}

					if (oldtr == 0) {
						if (!nowNode.getAttribute("tabletype").equals("nest"))
							h = h.getFirstChild();
					}
					for (int i = 0; i < oldtr; i++) {
						h = h.getNextSibling();
					}

				}
				/*** tr�κ�������� ***/
				if (ch.getNodeName() == "EMBED") {
					int colspan = Integer.parseInt(nowNode
							.getAttribute("colspan"));
					int rowspan = Integer.parseInt(nowNode
							.getAttribute("rowspan"));
					inputValue(ch, rowspan, colspan, x, y, h);
					valuenum++;
				}
				// �ͤν񤭹���
				if (ch.getNodeName() == "VALUE"
						|| nowNode.getAttribute("tabletype").equals("nest")) {
					if (parentNode.getAttribute("type").equals("connect")
							&& parentNode.getAttribute("dimension").equals("1")) {/*
																				 * C1
																				 * VALUE
																				 */
						int rowspan = 1, colspan = 1;
						if (nowNode.hasAttribute("colspan")) {// maxtd������ʤ�
							colspan = Integer.parseInt(nowNode
									.getAttribute("colspan"));
						}
						if (parentNode.hasAttribute("plustd")) {
							if (ch.getParentNode().getLastChild() == ch
									&& nowNode.getAttribute("child").equals(
											nowNode.getAttribute("val"))) {
								colspan += Integer.parseInt(parentNode
										.getAttribute("plustd"));
							}
						}
						if (nowNode.hasAttribute("rowspan")) {
							rowspan = Integer.parseInt(nowNode
									.getAttribute("rowspan"));
						}
						if (nowNode.getAttribute("tabletype").equals("nest")
								&& !ch.getNodeName().equals("VALUE")) {
							Element tmp = (Element) par.getParentNode();
							if (rowspan > 1) {
								tmp.setAttribute("rowspan",
										Integer.toString(rowspan));
							}
							if (colspan > 1) {
								tmp.setAttribute("colspan",
										Integer.toString(colspan));
							}
						} else {
							inputValue(ch, rowspan, colspan, x, y, h);
						}
						if (colspan == 0)
							colspan = 1;

						if (!nowNode.getAttribute("tabletype").equals("nest")) {
							valuenum += colspan;
							nowNode.setAttribute("colspan",
									Integer.toString(colspan));
							nowNode.setAttribute("rowspan",
									Integer.toString(rowspan));
						}
					}
					if (parentNode.getAttribute("type").equals("connect")
							&& parentNode.getAttribute("dimension").equals("2")) {/*
																				 * C2
																				 * VALUE
																				 */
						int rowspan = 0, colspan = 0;
						if (nowNode.hasAttribute("rowspan")) {// oldtr������ʤ�
							rowspan = Integer.parseInt(nowNode
									.getAttribute("rowspan"));
						}
						if (nowNode.hasAttribute("colspan")) {// oldtr������ʤ�
							colspan = Integer.parseInt(nowNode
									.getAttribute("colspan"));
						}
						if (nowNode.getAttribute("tabletype").equals("nest")
								&& !ch.getNodeName().equals("VALUE")) {
							Element tmp = (Element) par.getParentNode();
							if (rowspan > 1) {
								tmp.setAttribute("rowspan",
										Integer.toString(rowspan));
							}
							if (colspan > 1) {
								tmp.setAttribute("colspan",
										Integer.toString(colspan));
							}
						} else {
							inputValue(ch, rowspan, colspan, x, y, h);
						}
						if (rowspan == 0)
							rowspan = 1;
						if (!nowNode.getAttribute("tabletype").equals("nest")) {
							valuenum += rowspan;
							nowNode.setAttribute("colspan",
									Integer.toString(colspan));
							nowNode.setAttribute("rowspan",
									Integer.toString(rowspan));
						} else {
							// add 200909
							if (parentNode.getAttribute("type").equals(
									"connect")
									&& parentNode.getAttribute("dimension")
											.equals("2")) {
								valuenum++;
							}
						}
					}
					if (ch.getParentNode().getNodeName() == "SSQL") {
						if (!nowNode.getAttribute("tabletype").equals("nest")) {
							inputValue(ch, 0, 0, x, y, h);
						}
					}

				}

				// add 200909
				if (nowNode.getNodeName().contains("form")) {
					Document doc = par.getOwnerDocument();
					Element str = doc.createElement(nowNode.getNodeName()
							.toString());
					Log.out(nowNode.getNodeName().toString());
					if (nowNode.getNodeName().contains("start")) {
						h.appendChild(str);
					}
					if (nowNode.getNodeName().contains("end")) {
						h.getParentNode().appendChild(str);
					}
				}

				// saiki
				if (nowNode.getAttribute("tabletype").equals("nest")
						&& !ch.getNodeName().equals("VALUE")) {
					createTable(ch, par.getFirstChild());
				} else {
					createTable(ch, h);
				}

				if (nowNode.getAttribute("type").equals("connect")
						&& nowNode.getAttribute("dimension").equals("1")) {// node��C1�ΤȤ�
					// rowspan�Τ�������
					if (nowNode.getAttribute("child").equals(
							nowNode.getAttribute("val"))) {
						Element e2 = (Element) (ch.getFirstChild());
						nowNode.setAttribute("rowspan",
								e2.getAttribute("rowspan"));
					}

				}
				if (nowNode.getAttribute("type").equals("connect")
						&& nowNode.getAttribute("dimension").equals("2")) {// node��C2�ΤȤ�
					// colspan�Τ�������
					if (nowNode.getAttribute("child").equals(
							nowNode.getAttribute("val"))) {
						Element e2 = (Element) (ch.getFirstChild());
						nowNode.setAttribute("colspan",
								e2.getAttribute("colspan"));
					}
				}

				if (nowNode.getAttribute("tabletype").equals("nest")
						&& !ch.getNodeName().equals("VALUE")) {
					a[num] = 0;
					b[num] = 0;
					num--;
					/*
					 * for(int i=0;i<num;i++){
					 * //System.out.println("----"+a[i]+", " + b[i]+", " + i); }
					 */
				}
			}
		}
	}

	/*-- delete nested tfe --*/
	public void front(Node n) {
		for (Node ch = n.getFirstChild(); ch != null; ch = ch.getNextSibling()) {
			if (ch.getNodeType() == Node.ELEMENT_NODE) {
				Element nowNode = (Element) ch;
				Element parentNode = (Element) ch.getParentNode();
				if (ch.getNodeName().equals("EMBED")) {
					continue;
				}
				/* delete C1-C1, C2-C2 */
				if (nowNode.getAttribute("type").equals("connect")
						&& parentNode.getAttribute("type").equals("connect")
						&& parentNode.getAttribute("dimension").equals(
								nowNode.getAttribute("dimension"))) {

					if (!nowNode.getAttribute("tabletype").equals("nest")) {
						Node brother = ch.getNextSibling();
						for (Node ch2 = ch.getFirstChild(); ch2 != null; ch2 = ch
								.getFirstChild()) {
							ch.getParentNode().appendChild(ch2);
							if (brother == null) {
								ch.getParentNode().appendChild(ch2);
							} else {
								ch.getParentNode().insertBefore(ch2, brother);
							}
						}
						ch.getParentNode().removeChild(ch);
						ch = n;
					}
				}
				/* delete C1-G1,C2-G2 */
				if (nowNode.getAttribute("type").equals("repeat")
						&& parentNode.getAttribute("type").equals("connect")
						&& nowNode.getAttribute("dimension").equals(
								parentNode.getAttribute("dimension"))) {
					if (!nowNode.getAttribute("tabletype").equals("nest")) {
						if (nowNode.hasAttribute("border")) {
							for (Node tmp = ch.getFirstChild(); tmp != null; tmp = tmp
									.getNextSibling()) {
								Element childNode = (Element) tmp;
								childNode.setAttribute("border",
										nowNode.getAttribute("border"));
							}
						}
						Node brother = ch.getNextSibling();
						for (Node ch2 = ch.getFirstChild(); ch2 != null; ch2 = ch
								.getFirstChild()) {
							if (brother == null) {
								ch.getParentNode().appendChild(ch2);
								ch.getParentNode();
							} else {
								ch.getParentNode().insertBefore(ch2, brother);
							}
						}
						ch.getParentNode().removeChild(ch);
						ch = n;
					}
				}
				/* delete G1,G2 */
				nowNode = (Element) ch;
				if (nowNode.getAttribute("type").equals("repeat")) {
					Node brother = ch.getNextSibling();
					for (Node ch2 = ch.getFirstChild(); ch2 != null; ch2 = ch
							.getFirstChild()) {
						Element previousNode = null;
						if (ch.getPreviousSibling() != null
								&& ch.getPreviousSibling().getNodeName()
										.equals("tfe")) {
							previousNode = (Element) ch.getPreviousSibling();
						}

						if ((previousNode != null
								&& previousNode.getAttribute("dimension")
										.equals(nowNode
												.getAttribute("dimension"))
								&& previousNode.getAttribute("type").equals(
										"connect") && previousNode
								.getAttribute("oldtype").equals("repeat"))) {
							if (nowNode.hasAttribute("border")) {
								for (Node tmp = ch.getFirstChild(); tmp != null; tmp = tmp
										.getNextSibling()) {
									Element childNode = (Element) tmp;
									childNode.setAttribute("border",
											nowNode.getAttribute("border"));
								}
							}
							ch.getPreviousSibling().appendChild(ch2);
						} else {
							Document doc = ch.getOwnerDocument();
							Element strtfe = doc.createElement("tfe");
							strtfe.setAttribute("oldtype", "repeat");
							strtfe.setAttribute("type", "connect");
							if (nowNode.getAttribute("tabletype")
									.equals("nest")) {
								strtfe.setAttribute("tabletype", "nest");
							}
							if (nowNode.hasAttribute("class")) {
								strtfe.setAttribute("class",
										nowNode.getAttribute("class"));
							}
							if (nowNode.hasAttribute("cellspacing")) {
								strtfe.setAttribute("cellspacing",
										nowNode.getAttribute("cellspacing"));
							}
							if (nowNode.hasAttribute("cellpadding")) {
								strtfe.setAttribute("cellpadding",
										nowNode.getAttribute("cellpadding"));
							}
							if (nowNode.hasAttribute("align")) {
								strtfe.setAttribute("align",
										nowNode.getAttribute("align"));
							}
							if (nowNode.hasAttribute("valign")) {
								strtfe.setAttribute("valign",
										nowNode.getAttribute("valign"));
							}
							if (nowNode.hasAttribute("border")) {
								strtfe.setAttribute("border",
										nowNode.getAttribute("border"));
								for (Node tmp = ch.getFirstChild(); tmp != null; tmp = tmp
										.getNextSibling()) {
									Element childNode = (Element) tmp;
									childNode.setAttribute("border",
											nowNode.getAttribute("border"));
								}
							}
							nowNode = (Element) ch;
							strtfe.setAttribute("dimension",
									nowNode.getAttribute("dimension"));
							strtfe.appendChild(ch2);
							ch.getParentNode().insertBefore(strtfe, brother);
						}
					}
					ch.getParentNode().removeChild(ch);
					ch = n;
				}
				front(ch);
			}
		}
	}

	// normal optimizer input:string filename (create xml file)
	public String generateHtml(String filename) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc;
			try {
				doc = db.parse(new FileInputStream(filename));
				root = doc.getDocumentElement();
			} catch (Exception e) {
				Log.err("XML File not found : " + e);
//				GlobalEnv.errorText += "XML File not found : " + e;
				return null;
			}
			/* delete file */
			File del_file = new File(filename);
			if (del_file.exists()) {
				// in debug, comment out under line
				// if (del_file.delete()) {}
			} else {
				Log.err(del_file + "does not exist");
//				GlobalEnv.errorText += del_file + "does not exist";
			}
			return startOptimizer();
		} catch (Exception e) {
			Log.err("XML File not found : " + e);
			return null;
		}
	}

	// invoke optimizer input:stringbuf (not create xml file)
	public String generateHtml(StringBuffer filestring) {
		try {

			// System.out.println(filestring); //commented out by goto 20120620
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc;
			StringReader strReader = new StringReader(filestring.toString());
			doc = db.parse(new InputSource(strReader));
			root = doc.getDocumentElement();
			return startOptimizer();

		} catch (Exception e) {
//			System.err.println(e);
			Log.err(e);
//			GlobalEnv.errorText += e;
			return null;
		}
	}

	public void inputValue(Node ch, int rowspan, int colspan, int x, int y,
			Node n) {
		String s = null;
		String imgsrc = null;
		Element nowNode = (Element) ch;
		// System.out.println("NODE: "+nowNode.getFirstChild().getNodeValue());
		if (nowNode.getAttribute("type").equals("img")) {// image�ΤȤ�
			s = null;
			imgsrc = nowNode.getAttribute("src");
		} else if (nowNode.getAttribute("type").equals("form")) {
			// added 200905
			// form�ΤȤ�
			// XML NODE -> String
			Node f = nowNode.getFirstChild();
			DOMSource source = new DOMSource(f);
			StringWriter swriter = new StringWriter();
			StreamResult result = new StreamResult(swriter);
			try {
				Transformer transformer = TransformerFactory.newInstance()
						.newTransformer();
				transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,
						"yes");

				for (; f != null; f = f.getNextSibling()) {
					source = new DOMSource(f);
					transformer.transform(source, result);
				}
				// string
				s = swriter.toString();
			} catch (TransformerException te) {
			}

		} else {
			s = ch.getFirstChild().getNodeValue();
		}
		Node h = n;
		int i = -1;
		for (Node n1 = n; n1 != null && i < y; n1 = n1.getNextSibling()) {
			if (n1.getNodeName() == "TR") {
				i++;
				h = n1;
			}
		}
		i = 0;
		Document doc = h.getOwnerDocument();
		Element td = doc.createElement("TD");
		h.appendChild(td);

		if (ch.getNodeName().equals("EMBED")) {
			if (ch.getFirstChild().getNodeType() != Node.TEXT_NODE) {
				for (Node tmp = ch.getFirstChild(); tmp != null; tmp = tmp
						.getNextSibling()) {
					Element table = (Element) doc.importNode(tmp, true);
					td.appendChild(table);
				}
			} else if (ch.getFirstChild().getNodeValue() != null) {
				String data = ch.getFirstChild().getNodeValue();
				// System.out.println("ppp" + data);
				Text table = doc.createTextNode(data);
				td.appendChild(table);
			}
			if (rowspan > 1) {
				td.setAttribute("rowspan", Integer.toString(rowspan));
			}
			if (colspan > 1) {
				td.setAttribute("colspan", Integer.toString(colspan));
			}
			return;
		}

		if (nowNode.getAttribute("tabletype").equals("nest")) {
			Element strtable = doc.createElement("TABLE");
			if (nowNode.hasAttribute("border")) {
				strtable.setAttribute("border", nowNode.getAttribute("border"));
			} else {
				strtable.setAttribute("border", "1");
			}
			strtable.setAttribute("cellspacing", "0");
			td.appendChild(strtable);
			Element strtr = doc.createElement("TR");
			strtable.appendChild(strtr);
			Element strtd = doc.createElement("TD");
			td = (Element) strtr.appendChild(strtd);
		}

		if (nowNode.hasAttribute("href")) {// href������ʤ�
			Element href = doc.createElement("A");
			td.appendChild(href);
			href.setAttribute("href", nowNode.getAttribute("href"));
			td = href;
			if (nowNode.hasAttribute("target")) {
				href.setAttribute("target", nowNode.getAttribute("target"));
			}
			if (nowNode.hasAttribute("aclass")) {
				href.setAttribute("class", nowNode.getAttribute("aclass"));
			}
		}
		if (imgsrc != null) {
			Element img = doc.createElement("img");
			img.setAttribute("src", imgsrc);
			if (nowNode.hasAttribute("width")) {
				img.setAttribute("width", nowNode.getAttribute("width"));
			}
			if (nowNode.hasAttribute("height")) {
				img.setAttribute("height", nowNode.getAttribute("height"));
			}
			h.getLastChild().appendChild(img);
		} else {
			Text txt = doc.createTextNode(s);
			td.appendChild(txt);
			Log.out("write(" + x + "," + y + ") : " + s);
		}
		if (rowspan > 1) {
			Element at = (Element) h.getLastChild();
			at.setAttribute("rowspan", Integer.toString(rowspan));
		}
		if (colspan > 1) {
			Element at = (Element) h.getLastChild();
			at.setAttribute("colspan", Integer.toString(colspan));
			Log.out("colspan=" + colspan);
		}
		if (nowNode.hasAttribute("class")) {
			Element at = (Element) h.getLastChild();
			at.setAttribute("class", nowNode.getAttribute("class"));
		}
	}

	public String startOptimizer() {
		try {

			/* optimizer start */
			System.out.println("******** OPTIMIZER *********");

			front(root);

			walk(root);

			countTrAndTd(root);

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			TransformerFactory tfactory = TransformerFactory.newInstance();
			Transformer transformer = tfactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "Shift_JIS");
			// transformer.setOutputProperty(OutputKeys.ENCODING, "EUC_JP");
			Document html = db.newDocument();
			Element htmlroot = html.createElement("TABLE");
			/*
			 * if(r.getAttribute("tabletype").equals("none")) htmlroot =
			 * html.createElement("BODY");
			 */
			Element firstChild = null;
			for (Node tmp = root.getFirstChild(); tmp != null; tmp = tmp
					.getNextSibling()) {
				if (tmp.getNodeName().equals("tfe")) {
					firstChild = (Element) tmp;
					break;
				}
			}
			if (firstChild != null && firstChild.getNodeName().equals("tfe") /*
																			 * &&
																			 * !
																			 * r
																			 * .
																			 * getAttribute
																			 * (
																			 * "tabletype"
																			 * )
																			 * .
																			 * equals
																			 * (
																			 * "none"
																			 * )
																			 */) {
				if (firstChild.hasAttribute("border")) {
					htmlroot.setAttribute("border",
							firstChild.getAttribute("border"));
				} else {
					htmlroot.setAttribute("border", "1");
				}
				if (firstChild.hasAttribute("class")) {
					htmlroot.setAttribute("class",
							firstChild.getAttribute("class"));
				}
			}

			html.appendChild(htmlroot);

			// transformer.transform(new DOMSource(root.getOwnerDocument()), new
			// StreamResult(new File("C:/ssqltest/bbb.xml")));
			createTable(root, htmlroot);

			StringWriter outWriter = new StringWriter();

			DOMSource source = new DOMSource(html);
			StreamResult result = new StreamResult(outWriter);
			/* output debug xml file */
			// transformer.transform(new DOMSource(root.getOwnerDocument()), new
			// StreamResult(new File("C:/ssqltest/bbb.xml")));

			transformer.transform(source, result);
			String xml_str = outWriter.toString();

			// create html file
			/*
			 * if(root.getAttribute("tabletype").equals("none")){ xml_str =
			 * xml_str.replaceFirst("<BODY>",""); xml_str =
			 * xml_str.replaceFirst("</BODY>",""); }
			 */
			xml_str = xml_str.replaceFirst("<?.*?>", "");
			xml_str = xml_str.replace("<TR/>", "<TR></TR>");
			xml_str = xml_str.replace("</TR>", "</TR>\n");

			// Log.out(xml_str);
			while (xml_str.contains("&amp;"))
				xml_str = xml_str.replace("&amp;", "&");
			while (xml_str.contains("&lt;"))
				xml_str = xml_str.replace("&lt;", "<");
			while (xml_str.contains("&gt;"))
				xml_str = xml_str.replace("&gt;", ">");

			// add 200909 form
			if (xml_str.contains("<form")) {
				for (int i = 1; i < HTMLEnv.getFormNumber(); i++) {
					if (xml_str.contains("<form" + i + "start/>")) {
						xml_str = xml_str.replace("<form" + i + "start/>",
								HTMLEnv.getFormDetail(i));
					}
					if (xml_str.contains("<form" + i + "end/>")) {
						xml_str = xml_str.replace("<form" + i + "end/>",
								"</form>");
					}
				}
				int st = 0;
				while (true) {
					if (xml_str.indexOf("<textarea", st) > 0) {
						st = xml_str.indexOf("<textarea", st);
						int en = xml_str.indexOf(">", st);
						if (xml_str.charAt(en - 1) == '/') {
							String a = xml_str.substring(0, en - 1);
							String b = xml_str.substring(en, en + 1);
							String c = xml_str.substring(en + 1,
									xml_str.length());
							xml_str = a + b + "</textarea>" + c;
						}
						st++;
					} else {
						break;
					}
				}
			}

			return xml_str;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void walk(Node n) {
		int inval = 0, frval = 0, tmp = 0;
		for (Node ch = n.getFirstChild(); ch != null; ch = ch.getNextSibling()) {
			if (ch.getNodeType() == Node.ELEMENT_NODE) {
				Element nowNode = (Element) ch;
				tmp++;
				if (ch.getNodeName().equals("VALUE")
						|| ch.getNodeName().equals("EMBED")) {
					nowNode.setAttribute("frontval",
							Integer.toString(inval + frval));
					inval++;
				} else {
					nowNode.setAttribute("child",
							Integer.toString((ch.getChildNodes()).getLength()));
					nowNode.setAttribute("frontval",
							Integer.toString(inval + frval));
					walk(ch);
				}
				if ((nowNode.getAttribute("type").equals("connect"))) {
					frval++;
				}
			}
		}
		Element e = (Element) n;
		e.setAttribute("val", Integer.toString(inval));
		e.setAttribute("child", Integer.toString(tmp));
	}

	// search and count dimension1
	public void walkC1(Node n) {
		for (Node ch = n.getFirstChild(); ch != null; ch = ch.getNextSibling()) {
			if (ch.getNodeType() == Node.ELEMENT_NODE) {
				Element nowNode = (Element) ch;
				Element parentNode = (Element) ch.getParentNode();
				int value = 0;
				if (nowNode.hasAttribute("val"))
					value = Integer.parseInt(nowNode.getAttribute("val"));
				if ((nowNode.getAttribute("type").equals("connect"))
						&& (nowNode.getAttribute("dimension").equals("1"))
						&& (Integer.parseInt(nowNode.getAttribute("child")) == value)) {// C1�ǻҤ��ͤ����ʤ��Ȥ�
					if (parentNode.getAttribute("type").equals("connect")
							&& parentNode.getAttribute("dimension").equals("2")) {// �Ƥ�C2
						int max;
						if (!parentNode.hasAttribute("td")) {// null�ʤ�
							max = value;
						} else {
							int tmp = Integer.parseInt(parentNode
									.getAttribute("td"));
							max = lcm(tmp, value);
						}
						if (nowNode.getAttribute("tabletype").equals("nest")) {
							if (!parentNode.hasAttribute("td")) {
								parentNode.setAttribute("td", "1");
							}
							nowNode.setAttribute("td", Integer.toString(value));
						} else {
							parentNode
									.setAttribute("td", Integer.toString(max));
							nowNode.setAttribute("oldtd",
									Integer.toString(value));
						}
					}
				} else if (nowNode.getAttribute("type").equals("connect")
						&& nowNode.getAttribute("dimension").equals("1")) {// �Ҥ��Ͱʳ��Τ�Τ�����Ȥ�
					walkC1(ch);
					int max;
					if (!nowNode.hasAttribute("td")) {// null�ʤ�
						max = value;
						for (Node ch2 = ch.getFirstChild(); ch2 != null; ch2 = ch2
								.getNextSibling()) {
							Element childNode;
							if (ch2.getNodeType() == Node.ELEMENT_NODE) {
								childNode = (Element) ch2;
							} else {
								continue;
							}
							if (childNode.getAttribute("type")
									.equals("connect")) {
								if (childNode.getAttribute("tabletype").equals(
										"nest")) {
									max += 1;
								} else if (childNode.hasAttribute("td")) {
									max += Integer.parseInt(childNode
											.getAttribute("td"));
									childNode.setAttribute("oldtd",
											childNode.getAttribute("td"));
									childNode.removeAttribute("td");
								}
							}
						}
					} else {
						max = value
								+ Integer.parseInt(nowNode.getAttribute("td"));
					}

					nowNode.setAttribute("td", Integer.toString(max));
					if (parentNode.getAttribute("type").equals("connect")
							&& parentNode.getAttribute("dimension").equals("1")) { // �Ƥ�C1�ΤȤ�
						if (nowNode.getAttribute("tabletype").equals("nest")) {
							max++;
						} else if (parentNode.hasAttribute("td")) {
							max = max
									+ Integer.parseInt(parentNode
											.getAttribute("td"));
						}
						parentNode.setAttribute("td", Integer.toString(max));
					}
				} else {// if node is not C1 -> next children
					walkC1(ch);
					for (Node ch2 = ch.getFirstChild(); // �Ҷ��򤿤ɤꤽ�Τʤ���td����������
					ch2 != null; ch2 = ch2.getNextSibling()) {
						Element childNode;
						if (ch2.getNodeType() == Node.ELEMENT_NODE) {
							childNode = (Element) ch2;
						} else {
							continue;
						}
						if (childNode.getAttribute("type").equals("connect")) {
							if (childNode.hasAttribute("td")) {// ���ɤ��td������ʤ�
								int tmp = 0;
								if (!nowNode.hasAttribute("td")) {// nowNode��td���̥�ʤ�
									tmp = Integer.parseInt(childNode
											.getAttribute("td"));

								} else {// �̥뤸��ʤ��ʤ�Ǿ����ܿ�
									if (nowNode.getAttribute("type").equals(
											"connect")
											&& nowNode
													.getAttribute("dimension")
													.equals("2")) {// C2�ʤ��
										if (!childNode
												.getAttribute("tabletype")
												.equals("nest")) {
											tmp = lcm(
													Integer.parseInt(nowNode
															.getAttribute("td")),
													Integer.parseInt(childNode
															.getAttribute("td")));
										} else {
											tmp = Integer.parseInt(nowNode
													.getAttribute("td"));
										}
									}
								}
								if (nowNode.hasAttribute("td")) {
									int aaa = Integer.parseInt(nowNode
											.getAttribute("td"));
									int tmp2 = lcm(tmp, aaa);
									nowNode.setAttribute("td",
											Integer.toString(tmp2));
								} else {
									nowNode.setAttribute("td",
											Integer.toString(tmp));
								}
								if (!childNode.getAttribute("tabletype")
										.equals("nest")) {
									childNode.setAttribute("oldtd",
											childNode.getAttribute("td"));
									childNode.removeAttribute("td");
								}
							}
						}
						if (ch2.getNodeName().equals("VALUE")
								|| ch2.getNodeName().equals("EMBED")) {
							if (nowNode.hasAttribute("td")) {// nowNode��td������
								int tmp = 0;
								if (nowNode.getAttribute("type").equals(
										"connect")
										&& nowNode.getAttribute("dimension")
												.equals("2")) {// C2�ʤ�
									tmp = Integer.parseInt(nowNode
											.getAttribute("td"));
								}
								if (nowNode.getAttribute("type").equals(
										"connect")
										&& nowNode.getAttribute("dimension")
												.equals("1")) {// C1�ʤ�
									tmp = Integer.parseInt(nowNode
											.getAttribute("td")) + 1;
								}
								nowNode.setAttribute("td",
										Integer.toString(tmp));
							} else {
								nowNode.setAttribute("td", "1");
							}
						}
					}
				}
				if (nowNode.getAttribute("tabletype").equals("nest")
						&& nowNode.hasAttribute("oldtd")) {
					nowNode.setAttribute("td", nowNode.getAttribute("oldtd"));
					nowNode.removeAttribute("oldtd");
				}
			}
		}

		if (n.getNodeName().equals("SSQL")) {
			int max = 0;
			for (Node ch = n.getFirstChild(); ch != null; ch = ch
					.getNextSibling()) {
				if (ch.getNodeType() == Node.ELEMENT_NODE) {
					Element childNode = (Element) ch;
					if (childNode.hasAttribute("td")) {
						int tmp = Integer
								.parseInt(childNode.getAttribute("td"));
						if (tmp > max)
							max = tmp;
					}
				}
			}

			for (Node ch = n.getFirstChild(); ch != null; ch = ch
					.getNextSibling()) {
				if (ch.getNodeType() == Node.ELEMENT_NODE) {
					Element childNode = (Element) ch;
					if (childNode.hasAttribute("td")) {
						if (!childNode.getAttribute("td").equals(
								Integer.toString(max))) {
							int tmp = Integer.parseInt(childNode
									.getAttribute("td"));
							tmp = max - tmp;
							childNode.setAttribute("plustd",
									Integer.toString(tmp));
						}
						childNode.setAttribute("td", Integer.toString(max));
					}
				}
			}
		}

	}
}
