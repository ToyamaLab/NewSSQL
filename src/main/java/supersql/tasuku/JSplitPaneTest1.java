package supersql.tasuku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import supersql.FrontEnd;
import supersql.codegenerator.HTML.HTMLEnv;
import supersql.common.GlobalEnv;

public class JSplitPaneTest1 extends JFrame {

	static JPanel layout_panel = new JPanel();
	static int yoko_cnt = 0;

	public static int osShortcutKey = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

	public static ArrayList<JPanel> jPanel_tmp = new ArrayList<JPanel>();
	public static ArrayList<JPanel> jPanel = new ArrayList<JPanel>();
	public static ArrayList<JButton> jButton = new ArrayList<JButton>();
	public static ArrayList<String> jPanel_name = new ArrayList<String>();
	public static int[] font_size = new int[64];
	public static JSplitPane jsp_tmp = null;
	static int selectId = 0;
	static int cnt = 0;
	static int nest_cnt = 1;
	static int button_index = 0;
	static String code;
	static JFrame frame = new JFrame();
	static JFrame frame2 = new JFrame();
	static JFrame frame3 = new JFrame();
	static JFrame frame4 = new JFrame();
	int cn = 0;
	static int yoko_num_cnt = 0;
	public static int yoko_num[] = new int[16];
	public static boolean panel_option_flag;

	static JPanel swap_panel = new JPanel();;
	static JPanel swap_panel2 = new JPanel();
	static int swap_num = 0;

	static String pre_html = "";

	static JFrame color_frame = new JFrame();
	static JPanel color_panel = new JPanel();
	JPanel p = new JPanel();
	JPanel q = new JPanel();
	JPanel r = new JPanel();
	JPanel s = new JPanel();
	JPanel t = new JPanel();
	JPanel u = new JPanel();
	JPanel v = new JPanel();
	JPanel x = new JPanel();
	JPanel y = new JPanel();
	JButton btn_createSSQL;
	JButton btn1;
	JButton btn2;
	JButton btn3;
	JButton btn4;
	JButton btn5;
	JButton btn6;
	JButton btn7;
	JButton btn8;
	JButton btn9;
	JButton btn10;
	JButton btn11;

	static JTextField w_input;
	static JTextField h_input;
	static JTextField f_input;
	static JTextField mt_input;
	static JTextField mr_input;
	static JTextField mb_input;
	static JTextField ml_input;
	JLabel w_label = new JLabel("w :");
	JLabel h_label = new JLabel("h :");
	JLabel f_label = new JLabel("font-size :");
	JCheckBox checkbox;
	JButton btn_change_panel;
	public static ArrayList<String> size_fixed_name = new ArrayList<String>();
	static JCheckBox panel_option;
	static JButton dmy_Button = new JButton();

	JPanel table_panel = new JPanel();
	JTextField table_border_text = new JTextField();
	JButton btn_pbgcolor;
	JButton btn_left_table;
	JButton btn_center_table;
	JButton btn_right_table;

	static JPanel panel_name;
	static JLabel label_name;
	JLabel label_name2;
	JPanel size_panel = new JPanel();
	public static JLabel size_label = new JLabel();
//	static JCheckBox debugCheckBox = new JCheckBox("debug");

	ImageIcon icon1 = null;
	ImageIcon icon2 = null;
	static ImageIcon icon3 = null;
	ImageIcon icon4 = null;

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static int w = screenSize.width;
	static int h = screenSize.height;

	static boolean tate_flag = false;
	public static JSplitPane tate_tmp = new JSplitPane();
	public static JPanel tate_tmp2 = new JPanel();
	static String panelName = "";
	static ArrayList<String> multi_select_name = new ArrayList<String>();
	static ArrayList<JCheckBox> multi_select = new ArrayList<JCheckBox>();
	static boolean multi_select_flag = false;
	static String multi_panel_name = "";

	public static ArrayList<JSplitPane> nest_tmp = new ArrayList<JSplitPane>();
	static JPanel nest_tmp2 = new JPanel();
	public static ArrayList<JSplitPane> check_tmp = new ArrayList<JSplitPane>();

	String filename = GlobalEnv.getfilename();
	String html_file = FrontEnd.html_file;



	static JFrame main_frame;

	static JFrame frame_first;
	static JPanel panel_first;
	static JScrollPane scrollPane1;
	static JScrollPane scrollPane2;
	static JTextArea textArea1;
	static JTextArea textArea2;
	static JButton btn_first_go;
	static JButton btn_first_add;

	static JPanel table_line_Panel;
	static JScrollPane scrollPane;
	static JPanel line_panel;
	static JPanel line_panel2;
	static String[] line_size;
	static String[] line_color;
	static JTextField[] line_input;
	static JButton[] btn_line_color;

	static int undo_cnt = 0;
	static JButton undo_btn = new JButton();
	static JButton redo_btn = new JButton();
	static ArrayList<String> textArea_undo = new ArrayList<String>();
	//static ArrayList<String> //html_undo = new ArrayList<String>();
	static ArrayList<String> css_undo = new ArrayList<String>();
	static ArrayList<String[]> w_undo = new ArrayList<String[]>();
	static ArrayList<String[]> h_undo = new ArrayList<String[]>();
	static ArrayList<String[]> f_undo = new ArrayList<String[]>();
	static ArrayList<String[]> mt_undo = new ArrayList<String[]>();
	static ArrayList<String[]> mr_undo = new ArrayList<String[]>();
	static ArrayList<String[]> mb_undo = new ArrayList<String[]>();
	static ArrayList<String[]> ml_undo = new ArrayList<String[]>();
	static ArrayList<String[]> l_undo = new ArrayList<String[]>();
	static boolean line_action_flag = true;
	//static ArrayList<String> l_undo = new ArrayList<String>();


	static JPanel savePanel = new JPanel();
	static JButton saveFinish_Button = new JButton("保存して終了");
	static JButton nosaveFinish_Button = new JButton("保存しないで終了");
	static JButton save_Button = new JButton("保存");

	static JTextField preview_text = new JTextField(FrontEnd.Preview_URL);

	public JSplitPaneTest1() {
		//nest_tmp = null;
		nest_tmp2 = null;
		tate_tmp = null;
		tate_tmp2 = null;
		frame.pack();
		First_panel();



	}

	public void First_panel() {

		main_frame = new JFrame();
		main_frame.setSize(new Dimension(w / 2, h / 2));
		main_frame.setLayout(new GridLayout(1, 2));
		main_frame.addWindowListener(new WindowClosing());
		main_frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		//frame_first.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame_first = new JFrame();
		panel_first = new JPanel();
		scrollPane1 = new JScrollPane();
		scrollPane2 = new JScrollPane();
		btn_change_panel = new JButton("パネル切り替え");
		btn_change_panel.addActionListener(new panel_change_Listener());
		btn_change_panel.setPreferredSize(new Dimension(w / 8, h / 20));
		//textArea1 = new JTextArea(8, 30);
		textArea2 = new JTextArea();
		//textArea2.setPreferredSize(new Dimension(w / 4 - 10, h / 16));
		textArea2.setText(FrontEnd.ssql);
		//textArea1.setLineWrap(true);
		textArea2.setLineWrap(true);
		textArea2.setEditable(false);
		scrollPane2.setViewportView(textArea2);
		scrollPane2.setPreferredSize(new Dimension(w / 4 - 10, h / 4));
		save_Button.addActionListener(new saveListener());
		saveFinish_Button.addActionListener(new saveListener());
		nosaveFinish_Button.addActionListener(new saveListener());
		save_Button.setPreferredSize(new Dimension(w/25, h/32));
		saveFinish_Button.setPreferredSize(new Dimension(w/12, h/32));
		nosaveFinish_Button.setPreferredSize(new Dimension(w/10, h/32));
		savePanel.add(nosaveFinish_Button);
		savePanel.add(save_Button);
		savePanel.add(saveFinish_Button);

		undo_btn.setText("<");
		redo_btn.setText(">");
		undo_btn.setPreferredSize(new Dimension(w/32, h/20));
		redo_btn.setPreferredSize(new Dimension(w/32, h/20));
		undo_btn.addActionListener(new undo_redo_Listener());
		redo_btn.addActionListener(new undo_redo_Listener());

		panel_first.add(undo_btn);
		panel_first.add(redo_btn);

//		debugCheckBox.addActionListener(new debugListener());

		panel_option = new JCheckBox("サイズパネル", false);
		panel_option.addActionListener(new panel_optionListener());
		panel_option.setPreferredSize(new Dimension(w/12, h/32));
//		debugCheckBox.setPreferredSize(new Dimension(w/20, h/32));
		panel_first.add(panel_option);
//		panel_first.add(debugCheckBox);
		panel_first.add(btn_change_panel);
		panel_first.add(scrollPane2);

		savePanel.setPreferredSize(new Dimension(w/4, h/32));
		panel_first.add(savePanel);

		JLabel previewLabel = new JLabel();
		previewLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 10));
		previewLabel.setPreferredSize(new Dimension(w/23,h/32));
		previewLabel.setText("<html> <center>" + "プレビュー" + "<br>" + "パス :" + "</center> </html>");
		//previewLabel.setFont(font);

		preview_text.setPreferredSize(new Dimension(w/5, h/20));
		preview_text.addActionListener(new previewListener());
		panel_first.add(previewLabel);
		panel_first.add(preview_text);

		savePanel.setBackground(Color.GRAY);

		if(Preview.isLinux()) {
		main_frame.setSize(new Dimension(w / 2 *2/3, h / 2 *2/3));
		save_Button.setPreferredSize(new Dimension(w/22 *2/3, h/29 *2/3));
		saveFinish_Button.setPreferredSize(new Dimension(w/12 *2/3, h/29 *2/3));
		nosaveFinish_Button.setPreferredSize(new Dimension(w/10 *2/3, h/29 *2/3));
		save_Button.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 10));
		saveFinish_Button.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 10));
		nosaveFinish_Button.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 10));
		undo_btn.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 8));
		redo_btn.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 8));
		undo_btn.setPreferredSize(new Dimension(w/30 *2/3, h/20 *2/3));
		redo_btn.setPreferredSize(new Dimension(w/30 *2/3, h/20 *2/3));
		panel_option.setPreferredSize(new Dimension(w/12 *2/3, h/32 *2/3));
//		debugCheckBox.setPreferredSize(new Dimension(w/20 *2/3, h/32 *2/3));
			btn_change_panel.setPreferredSize(new Dimension(w / 8 *2/3, h / 20*2/3));
			scrollPane2.setPreferredSize(new Dimension(w / 4*2/3 - 10, h / 4*2/3));
			undo_btn.setPreferredSize(new Dimension(w/32*2/3, h/20*2/3));
			redo_btn.setPreferredSize(new Dimension(w/32*2/3, h/20*2/3));
			savePanel.setPreferredSize(new Dimension(w/4*2/3, h/28*2/3));
			previewLabel.setPreferredSize(new Dimension(w/22 *2/3,h/20 *2/3));
			previewLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 8));
			preview_text.setPreferredSize(new Dimension(w/5*2/3 -20, h/32*2/3));
		}


		panel_create();
		main_frame.getContentPane().add(panel_first);
		main_frame.getContentPane().add(layout_panel);
		//main_frame.getContentPane().add(swap_panel);
		swap_panel = table_line_Panel;

		main_frame.setResizable(false);
		//main_frame.setVisible(true);

		panel_first.setBackground(Color.gray);

	}

	public void c() {

		for (int i = 0; i < 32; i++)
			font_size[i] = 16;

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int w = screenSize.width;
		int h = screenSize.height;
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowClosing());
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);

		//frame3.setPreferredSize(new Dimension(300,300));
		frame3.setBounds(w / 4, h / 2, 220, 200);

		frame3.setTitle("Size View");

		size_panel = new JPanel();
		size_label = new JLabel();
		size_panel.add(size_label);
		frame3.getContentPane().add(size_panel, BorderLayout.CENTER);

	}

	void createTab(String name) {

		int nest_color = T_parser.nest_cnt[nest_cnt++];

		int width = 0;
		int height = 0;

		if (T_parser.layout_load_name.contains(name)) {

			width = T_parser.w_load[cnt];
			height = T_parser.h_load[cnt];
			cnt++;

		}

		//System.out.println("w=== " + width + "  h=== " + height);

		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(20, 10));


		//panel.setSize(new Dimension(50, 50));
		JButton button = new JButton(name);

		//panel.setPreferredSize(new Dimension());

		if (panel_option.isSelected())
			if (width != 0 && height != 0)
				panel.setPreferredSize(new Dimension(width, height));
			else if (width != 0 && height == 0)
				panel.setPreferredSize(new Dimension(width, 60));
			else if (width == 0 && height != 0)
				panel.setPreferredSize(new Dimension(150, height));
			else
				panel.setPreferredSize(new Dimension(150, 60));

		if (nest_color == 1)
			panel.setBackground(Color.RED);
		else if (nest_color == 2)
			panel.setBackground(Color.PINK);
		else if (nest_color == 3)
			panel.setBackground(Color.YELLOW);
		else if (nest_color == 4)
			panel.setBackground(Color.GREEN);
		else if (nest_color == 5)
			panel.setBackground(Color.BLUE);

		JCheckBox checkBox = new JCheckBox();
		//checkBox.setSize(5,5);
		checkBox.addActionListener(new myListener6());


		button.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 12));

		button.addActionListener(new myListener());

		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

		checkBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);

		if(Preview.isLinux()) {
			if(name.length() < 4)
				button.setPreferredSize(new Dimension(w/38, h/40));
			/*else if(name.length() >= 4 && name.length() < 6)
				button.setPreferredSize(new Dimension(w/32, h/32));*/

				else
					button.setPreferredSize(new Dimension(w/20, h/40));

					button.setMaximumSize(new Dimension(w/10,h/8));
		}
		else {

			if(name.length() < 4)
				button.setPreferredSize(new Dimension(w/30, h/32));
				else
					button.setPreferredSize(new Dimension(w/16, h/32));

					button.setMaximumSize(new Dimension(w/8,h/8));
		}



		//JPanel panel2 = new JPanel();
		panel.add(checkBox);
		panel.add(button);

		jPanel_tmp.add(panel);
		jPanel.add(panel);
		jButton.add(button);
		jPanel_name.add(name);
		multi_select.add(checkBox);

		System.out.println(name);

	}

	void yoko() {

		//jPanel_tmp.get(0).setSize(new Dimension(w/4, 1000));

		JSplitPane splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitpane.setDividerSize(6);
		splitpane.setDividerLocation(0.5);
		splitpane.setBorder(new LineBorder(Color.black, 0));

		if (tate_flag == false) {

			if (jPanel_tmp.size() == 2) {
				splitpane.setLeftComponent(jPanel_tmp.get(0));
				//jPanel_tmp.get(1).setSize(new Dimension(w/4, 1000));
				splitpane.setRightComponent(jPanel_tmp.get(1));
			} else if (jsp_tmp != null) {
				splitpane.setRightComponent(jPanel_tmp.get(0));
				//jsp_tmp.setSize(new Dimension(w/8, 1000));
				splitpane.setLeftComponent(jsp_tmp);
			} else {
				return;
			}
			//if(jPanel_tmp.size() == 2)
			//splitpane.setLeftComponent(jPanel_tmp.get(1));

			jPanel_tmp.clear();

			jsp_tmp = splitpane;

			frame.getContentPane().add(splitpane);

		}

		else {

			if (jPanel_tmp.size() == 2) {
				splitpane.setLeftComponent(jPanel_tmp.get(0));
				splitpane.setRightComponent(jPanel_tmp.get(1));
			} else {
				splitpane.setRightComponent(jPanel_tmp.get(0));
				splitpane.setLeftComponent(jsp_tmp);
			}

			//if(jPanel_tmp.size() == 2)
			//splitpane.setLeftComponent(jPanel_tmp.get(1));

			jPanel_tmp.clear();
			//tate_tmp = null;

			jsp_tmp = splitpane;

			frame.getContentPane().add(splitpane);

		}

		System.out.println("-");

	}

	void tate() {

		JSplitPane splitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitpane.setDividerSize(5);
		splitpane.setBorder(new LineBorder(Color.black, 0));

		yoko_num_cnt++;

		if (tate_flag == false) {

			if (jPanel_tmp.size() == 2) {
				splitpane.setLeftComponent(jPanel_tmp.get(0));
				splitpane.setRightComponent(jPanel_tmp.get(1));
			}

			else if (jsp_tmp != null) {
				splitpane.setRightComponent(jPanel_tmp.get(0));
				splitpane.setLeftComponent(jsp_tmp);

			} else {
				return;
			}

			jPanel_tmp.clear();

			jsp_tmp = splitpane;
			//tate_tmp = splitpane;

			frame.getContentPane().add(splitpane);

			System.out.println("|");

		} else {

			if (jPanel_tmp.size() != 0) {
				nest_tmp2 = jPanel_tmp.get(0);
				nest_match(5);
				//nest_tmp.add(jsp_tmp);
				//jsp_tmp = null;
				/*tate_tmp2 = jPanel_tmp.get(0);
				nest_match(5);*/
				tate_tmp = null;

			} else {
				tate_tmp = jsp_tmp;
				nest_tmp.add(jsp_tmp);
				tate_tmp2 = null;
			}

			jPanel_tmp.clear();

			System.out.println("|2");
		}


	}

	void tate_match() {

		yoko_num_cnt++;

		JSplitPane splitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitpane.setDividerSize(5);
		splitpane.setBorder(new LineBorder(Color.black, 0));

		splitpane.setLeftComponent(nest_tmp.get(nest_tmp.size() - 1));
		nest_tmp.remove(nest_tmp.size() - 1);
		nest_tmp.add(splitpane);

		splitpane.setRightComponent(jsp_tmp);
		jsp_tmp = splitpane;

		frame.getContentPane().add(splitpane);

		tate_tmp = null;
		tate_tmp2 = null;

		System.out.println("+");
	}

	public void nest_match(int k) {

		if (k == 1) {
			yoko_num_cnt++;
			JSplitPane splitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
			splitpane.setDividerSize(5);
			splitpane.setBorder(new LineBorder(Color.black, 0));

			 if (nest_tmp2 != null) {
				splitpane.setLeftComponent(nest_tmp2);
				nest_tmp2 = null;
				System.out.println("+1212121212");
			}
			 else if (nest_tmp.size() != 0 /*&& nest_tmp2 == null*/) {
					splitpane.setLeftComponent(nest_tmp.get(nest_tmp.size() - 1));
					nest_tmp.remove(nest_tmp.size() - 1);
					//System.out.println("+90909090");
				}

			if (jsp_tmp != null)
				splitpane.setRightComponent(jsp_tmp);
			else if (jPanel_tmp.size() != 0) {
				splitpane.setRightComponent(jPanel_tmp.get(0));
				jPanel_tmp.clear();
			}
			jsp_tmp = splitpane;

			frame.getContentPane().add(splitpane);

			System.out.println("+|+");

		} else if (k == 2) {
			//yoko_num[yoko_num_cnt]++;
			JSplitPane splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
			splitpane.setDividerSize(5);
			splitpane.setBorder(new LineBorder(Color.black, 0));

			if(nest_tmp2 != null){
				splitpane.setLeftComponent(nest_tmp2);
				nest_tmp2 = null;
				//System.out.println("+121212121");
			}
			else if (nest_tmp.size() != 0 /*&& nest_tmp2 == null*/) {
				splitpane.setLeftComponent(nest_tmp.get(nest_tmp.size() - 1));
				nest_tmp.remove(nest_tmp.size() - 1);
				//System.out.println("+900909090");
			}

			if (jsp_tmp != null)
				splitpane.setRightComponent(jsp_tmp);
			else {
				splitpane.setRightComponent(jPanel_tmp.get(0));
				jPanel_tmp.clear();
			}

			jsp_tmp = splitpane;

			frame.getContentPane().add(splitpane);

			System.out.println("+-+");
		} else if (k == 3) {
			JSplitPane splitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
			splitpane.setDividerSize(5);
			splitpane.setBorder(new LineBorder(Color.black, 0));

			if (nest_tmp2 != null) {
				splitpane.setLeftComponent(nest_tmp2);
				nest_tmp2 = null;
				//System.out.println("+1212121212");
			}
			else if (nest_tmp.size() != 0 /*&& nest_tmp2 == null*/) {
					splitpane.setLeftComponent(nest_tmp.get(nest_tmp.size() - 1));
					nest_tmp.remove(nest_tmp.size() - 1);
					//System.out.println("+90909090");
				}

			jsp_tmp = splitpane;

			frame.getContentPane().add(splitpane);

			System.out.println("+3+");
		} else if (k == 4) {
			JSplitPane splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
			splitpane.setDividerSize(5);
			splitpane.setBorder(new LineBorder(Color.black, 0));

			if (nest_tmp.size() != 0 /*&& nest_tmp2 == null*/) {
				splitpane.setLeftComponent(nest_tmp.get(nest_tmp.size() - 1));
				nest_tmp.remove(nest_tmp.size() - 1);
				//System.out.println("+90909090");
			} else if (nest_tmp2 != null) {
				splitpane.setLeftComponent(nest_tmp2);
				nest_tmp2 = null;
				//System.out.println("+1212121212");
			}

			jsp_tmp = splitpane;

			frame.getContentPane().add(splitpane);

			System.out.println("+4+");
		}
		else if (k == 5) {
			JSplitPane splitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
			splitpane.setDividerSize(0);
			splitpane.setBorder(new LineBorder(Color.black, 0));

			if (nest_tmp2 != null) {
				splitpane.setLeftComponent(nest_tmp2);
				nest_tmp2 = null;
				//System.out.println("+1212121212");
			}

			nest_tmp.add(splitpane);
			jsp_tmp = splitpane;

			frame.getContentPane().add(splitpane);

			System.out.println("+5+");
		}
		else if (k == 6) {
			JSplitPane splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
			splitpane.setDividerSize(0);
			splitpane.setBorder(new LineBorder(Color.black, 0));

			if (nest_tmp2 != null) {
				splitpane.setLeftComponent(nest_tmp2);
				nest_tmp2 = null;
				//System.out.println("+1212121212");
			}

			nest_tmp.add(splitpane);
			jsp_tmp = splitpane;

			frame.getContentPane().add(splitpane);

			System.out.println("+6+");
		}
	}


	void panel_create() {

		p.setLayout(new BorderLayout());
		q.setLayout(new GridLayout(3, 1));
		r.setLayout(new GridLayout(1, 5));
		s.setLayout(new GridLayout(1, 4));

		icon1 = new ImageIcon("./images/font_color.png");
		icon2 = new ImageIcon("./images/back_color.png");
		icon3 = new ImageIcon("./images/back_color.png");
		icon4 = new ImageIcon("./images/line_color.png");

		int cnt_tmp = 0;

		for (int i = 0; i < jPanel_name.size(); i++) {
			if (jPanel_name.get(i).equals(panelName))
				cnt_tmp = i;
		}
		int w = T_parser.w_load[cnt_tmp];
		int h = T_parser.h_load[cnt_tmp];

		System.out.println(w);

		w_input = new JTextField();
		h_input = new JTextField();
		w_input.addActionListener(new sizeinputListener());
		h_input.addActionListener(new sizeinputListener());
		w_input.setText(String.valueOf(w));
		h_input.setText(String.valueOf(h));

		JPanel pane = new JPanel();
		JLabel l1 = new JLabel("    width : ");
		JLabel l2 = new JLabel("    height : ");
		pane.setLayout(new GridLayout(1, 4));

		pane.add(l1);
		pane.add(w_input);
		pane.add(l2);
		pane.add(h_input);
		p.add(pane, BorderLayout.NORTH);
		/*}*/

		f_input = new JTextField();
		mt_input = new JTextField();
		mr_input = new JTextField();
		mb_input = new JTextField();
		ml_input = new JTextField();
		int cnt_tmp2 = 999;
		int f = 9990;
		int mt = 9990;
		int mr = 9990;
		int mb = 9990;
		int ml = 9990;
		f_input.setText("default");
		mt_input.setText("default");
		mr_input.setText("default");
		mb_input.setText("default");
		ml_input.setText("default");

		for (int i = 0; i < jPanel_name.size(); i++) {
			if (jPanel_name.get(i).equals(panelName))
				cnt_tmp2 = i;
		}

		if (cnt_tmp2 != 999 && T_parser.f_load[cnt_tmp2] != 0)
			f = T_parser.f_load[cnt_tmp2];
		if (cnt_tmp2 != 999 && T_parser.mt_load[cnt_tmp2] != 0)
			mt = T_parser.mt_load[cnt_tmp2];
		if (cnt_tmp2 != 999 && T_parser.mr_load[cnt_tmp2] != 0)
			mr = T_parser.mr_load[cnt_tmp2];
		if (cnt_tmp2 != 999 && T_parser.mb_load[cnt_tmp2] != 0)
			mb = T_parser.mb_load[cnt_tmp2];
		if (cnt_tmp2 != 999 && T_parser.ml_load[cnt_tmp2] != 0)
			ml = T_parser.ml_load[cnt_tmp2];

		f_input.addActionListener(new sizeinputListener());
		mt_input.addActionListener(new sizeinputListener());
		mr_input.addActionListener(new sizeinputListener());
		mb_input.addActionListener(new sizeinputListener());
		ml_input.addActionListener(new sizeinputListener());

		if (f != 9990)
			f_input.setText(String.valueOf(f));
		if (mt != 9990)
			mt_input.setText(String.valueOf(mt));
		if (mr != 9990)
			mr_input.setText(String.valueOf(mr));
		if (mb != 9990)
			mb_input.setText(String.valueOf(mb));
		if (ml != 9990)
			ml_input.setText(String.valueOf(ml));

		int wid = JSplitPaneTest1.w;
		int hei = JSplitPaneTest1.h;

		p.setPreferredSize(new Dimension(w/4, h/32));

		JPanel font_pane = new JPanel();
		JPanel margin_pane = new JPanel();
		JLabel l11 = new JLabel(" font-size : ");
		JLabel l12 = new JLabel("margin");
		font_pane.setLayout(new FlowLayout());
		margin_pane.setLayout(new FlowLayout());

		f_input.setPreferredSize(new Dimension(wid/16, hei/16));
		font_pane.add(l11);
		l11.setPreferredSize(new Dimension(wid/16, hei/32));;
		font_pane.add(f_input);
		p.add(font_pane);

		x = new JPanel();
		x.setLayout(new FlowLayout());
		l12.setPreferredSize(new Dimension(wid/28, hei/50));
		mt_input.setPreferredSize(new Dimension(wid/24, hei/32));
		ml_input.setPreferredSize(new Dimension(wid/24, hei/32));
		mr_input.setPreferredSize(new Dimension(wid/24, hei/32));
		mb_input.setPreferredSize(new Dimension(wid/24, hei/32));

		x.add(ml_input);
		x.add(l12);
		x.add(mr_input);
		margin_pane.add(mt_input);
		margin_pane.add(x);
		margin_pane.add(mb_input);

		r.add(margin_pane);

		dmy_Button.addActionListener(new myListener2());

		JLabel la = new JLabel("*** table option ***");
		la.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 16));
		/*la.setBackground(Color.darkGray);
		la.setOpaque(true);*/
		la.setPreferredSize(new Dimension(w / 4, h / 60));
		btn_left_table = new JButton("■ □ □");
		btn_center_table = new JButton("□ ■ □");
		btn_right_table = new JButton("□ □ ■");
		btn_pbgcolor = new JButton(icon3);
		//table_border_text = new JTextField();

		if(Preview.isLinux()) {
			p.setPreferredSize(new Dimension(w/4 *2/3, h/32 *2/3));

			f_input.setPreferredSize(new Dimension(wid/16 *2/3, hei/16 *2/3));

			l11.setPreferredSize(new Dimension(wid/16 *2/3, hei/34));;

			l12.setPreferredSize(new Dimension(wid/28 *2/3, hei/55));
			l12.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 10));
			mt_input.setPreferredSize(new Dimension(wid/24 *2/3, hei/32 *2/3));
			ml_input.setPreferredSize(new Dimension(wid/24 *2/3, hei/32 *2/3));
			mr_input.setPreferredSize(new Dimension(wid/24 *2/3, hei/32 *2/3));
			mb_input.setPreferredSize(new Dimension(wid/24 *2/3, hei/32 *2/3));
			la.setPreferredSize(new Dimension(w / 4 *2/3, h / 60 *2/3));
		}



		btn_left_table.addActionListener(new myListener2());
		btn_center_table.addActionListener(new myListener2());
		btn_right_table.addActionListener(new myListener2());
		btn_pbgcolor.addActionListener(new myListener5());
		//table_border_text.addActionListener(new sizeinputListener());

		btn_left_table.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 18));
		btn_center_table.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 18));
		btn_right_table.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 18));

		create_line();

		table_line_Panel = new JPanel();
		table_line_Panel.setLayout(new GridLayout(2, 1));

		table_panel.setLayout(new GridLayout(3, 1));
		y = new JPanel();
		y.setLayout(new BorderLayout());
		y.add(btn_center_table, BorderLayout.CENTER);
		y.add(btn_left_table, BorderLayout.WEST);
		y.add(btn_right_table, BorderLayout.EAST);
		//y.setPreferredSize(new Dimension());
		table_panel.add(la);
		table_panel.add(y);
		table_panel.add(btn_pbgcolor);
		//table_panel.add(table_border_text);
		table_line_Panel.add(table_panel);
		table_line_Panel.add(scrollPane);

		//main_frame.add(table_line_Panel);

		//btn_createSSQL = new JButton("OK!");
		btn1 = new JButton("A □ □");
		btn2 = new JButton("□ A □");
		btn3 = new JButton("□ □ A");
		btn4 = new JButton("Bold");
		btn5 = new JButton("Italic");
		btn6 = new JButton("Normal");
		btn7 = new JButton("A↑");
		btn8 = new JButton("A↓");
		btn9 = new JButton(icon1);
		btn10 = new JButton(icon2);

		//btn_createSSQL.addActionListener(new myListener2());
		btn1.addActionListener(new myListener2());
		btn2.addActionListener(new myListener2());
		btn3.addActionListener(new myListener2());
		btn4.addActionListener(new myListener2());
		btn5.addActionListener(new myListener2());
		btn6.addActionListener(new myListener2());
		btn7.addActionListener(new myListener2());
		btn8.addActionListener(new myListener2());
		btn9.addActionListener(new myListener3());
		btn10.addActionListener(new myListener4());

		//btn_createSSQL.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 20));
		btn1.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 16));
		btn2.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 16));
		btn3.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 16));
		btn4.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 16));
		btn5.setFont(new Font("ＭＳ ゴシック", Font.ITALIC, 16));
		btn6.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 16));
		btn7.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 20));
		btn8.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 12));

		checkbox = new JCheckBox("サイズ固定", false);

		u = new JPanel();
		u.setLayout(new GridLayout(1, 2));
		JPanel v = new JPanel();
		v.setLayout(new GridLayout(2, 1));

		p.add(btn1, BorderLayout.WEST);
		p.add(btn2, BorderLayout.CENTER);
		p.add(btn3, BorderLayout.EAST);
		q.add(btn4);
		q.add(btn5);
		q.add(btn6);
		u.add(margin_pane);
		u.add(q);
		v.add(btn7);
		v.add(btn8);
		r.add(v);
		r.add(font_pane);
		r.add(btn9);
		r.add(btn10);

		if (panel_option_flag == true) {

			checkbox.addActionListener(new myListener2());
			p.add(checkbox, BorderLayout.NORTH);

		}

		panel_name = new JPanel();
		//panel_name.setLayout(new GridLayout(2, 1));
		label_name = new JLabel();
		label_name.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 16));
		label_name.setBackground(new Color(204,204, 0));
		label_name.setOpaque(true);
		label_name.setSize(new Dimension(w/4, h/32));
		panel_name.add(label_name);
		layout_panel.setLayout(new GridLayout(4, 1));
		layout_panel.add(panel_name, 0);

		layout_panel.add(p);
		layout_panel.add(r);
		layout_panel.add(u);

	}

	void create_line() {

		//JLabel label3;
		JLabel label = new JLabel("*** line option ***");
		label.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 16));
		/*label.setBackground(Color.darkGray);
		label.setOpaque(true);*/
		label.setPreferredSize(new Dimension(w / 4, h / 30));
		line_panel = new JPanel();

		line_panel.add(label);
		scrollPane = new JScrollPane(line_panel);
		line_panel.setPreferredSize(new Dimension(w / 4, T_parser.line_list.size() * h / 20 * 3 / 2 + h / 20));
		//scrollPane.setPreferredSize(new Dimension(w/4, h/60));
		line_panel.setLayout(new FlowLayout());
		//scrollPane = new JScrollPane(line_panel);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportView(line_panel);

		line_size = new String[T_parser.line_list.size()];
		line_color = new String[T_parser.line_list.size()];
		line_input = new JTextField[T_parser.line_list.size()];
		btn_line_color = new JButton[T_parser.line_list.size()];

		for (int i = 0; i < T_parser.line_list.size(); i++) {
			String tmp = T_parser.line_list.get(i);
			if (tmp.contains(",")) {
				line_size[i] = tmp.substring(0, tmp.indexOf(","));
				if (tmp.contains("'")) {
					int n = tmp.indexOf("'") + 1;
					int m = tmp.indexOf("'", n);
					line_color[i] = tmp.substring(n, m);
				} else if (tmp.contains("\"")) {
					int n = tmp.indexOf("\"") + 1;
					int m = tmp.indexOf("\"", n);
					line_color[i] = tmp.substring(n, m);
				}

				line_input[i] = new JTextField(line_size[i]);
				btn_line_color[i] = new JButton(icon4);
			} else if (!tmp.contains(",") && (tmp.contains("'") || tmp.contains("\""))) {
				line_size[i] = "default";
				if (tmp.contains("'")) {
					int n = tmp.indexOf("'") + 1;
					int m = tmp.indexOf("'", n);
					line_color[i] = tmp.substring(n, m);
				} else if (tmp.contains("\"")) {
					int n = tmp.indexOf("\"") + 1;
					int m = tmp.indexOf("\"", n);
					line_color[i] = tmp.substring(n, m);
				}
				line_input[i] = new JTextField(line_size[i]);
				btn_line_color[i] = new JButton(icon4);
			} else if (!tmp.contains(",") && !tmp.contains("'") && !tmp.contains("\"")) {
				line_size[i] = tmp;
				line_color[i] = "default";
				line_input[i] = new JTextField(line_size[i]);
				btn_line_color[i] = new JButton(icon4);
			}
		}

		for (int i = 0; i < T_parser.line_list.size(); i++) {
			int a = i + 1;
			String b = String.valueOf(a);
			JLabel label3 = new JLabel();
			label3.setText(b + " : ");
			label3.setPreferredSize(new Dimension(w / 30, h / 20));
			line_panel2 = new JPanel();
			//line_panel2.setLayout(new GridLayout(1,3));
			line_input[i].addActionListener(new myListener8());
			btn_line_color[i].addActionListener(new myListener7());
			line_input[i].setPreferredSize(new Dimension(w / 12, h / 20));
			btn_line_color[i].setPreferredSize(new Dimension(w / 12, h / 20));

			if(Preview.isLinux()) {
				label3.setPreferredSize(new Dimension(w / 30 *2/3, h / 20 *2/3));
				line_input[i].setPreferredSize(new Dimension(w / 12 *2/3, h / 20 *2/3));
				btn_line_color[i].setPreferredSize(new Dimension(w / 12 *2/3, h / 20 *2/3));
			}

			line_panel2.add(label3);
			line_panel2.add(line_input[i]);
			line_panel2.add(btn_line_color[i]);
			line_panel.add(line_panel2);
		}

		if(Preview.isLinux()) {
			label.setPreferredSize(new Dimension(w / 4 *2/3, h / 30 *2/3));
			line_panel.setPreferredSize(new Dimension(w / 4 *2/3, (T_parser.line_list.size() * h / 20 * 3 / 2 + h / 20)*2/3));

		}

	}

	class WindowClosing extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {

			JFrame frame = new JFrame();
			frame.setAlwaysOnTop(true);
			String selectvalues[] = { "保存して終了", "保存しないで終了", "キャンセル" };

			int select = JOptionPane.showOptionDialog(frame,
					"選択してください",
					"確認",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					selectvalues,
					selectvalues[0]);

			if (select == 1) {
				try {
					int n = 0;
					int m = 0;
					n = GlobalEnv.getfilename().lastIndexOf("/") + 1;
					m = GlobalEnv.getfilename().lastIndexOf(".ssql") + 5;

					String cssname = GlobalEnv.getfilename().substring(n, m);
					cssname = cssname.replace(".ssql", ".css");

					String csspass = GlobalEnv.getOutputDirPath() + "/jscss/" + cssname;
					System.out.println(GlobalEnv.EXE_FILE_PATH );

					FileOutputStream fos = new FileOutputStream(csspass);//出力ファイル
					OutputStreamWriter osw = new OutputStreamWriter(fos, "SJIS");
					PrintWriter pw = new PrintWriter(osw);

					pw.println(FrontEnd.pre_css);

					pw.close();
					Preview.preview(FrontEnd.Preview_URL);

				} catch (Exception ex) {
					System.out.println(ex);

				}


				System.exit(0);
			} else if (select == 0) {
				write_SSQL(textArea2.getText());
				System.exit(0);
			}
		}
	}

	public class previewListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			FrontEnd.Preview_URL = preview_text.getText();
			Preview.preview(FrontEnd.Preview_URL);
		}
	}

	public class undo_click_Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			undo_btn.doClick();
		}
	}

	public class undo_redo_Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();

			if (cmd == "<") {

				undo_cnt--;
				for(int i = 0; i < Tasuku.tfe_name_arr.size(); i++) {
					T_parser.w_load[i] =  Integer.parseInt( w_undo.get(undo_cnt)[i]);
					T_parser.h_load[i] =  Integer.parseInt( h_undo.get(undo_cnt)[i]);
					T_parser.f_load[i] =  Integer.parseInt( f_undo.get(undo_cnt)[i]);
					T_parser.mt_load[i] =  Integer.parseInt( mt_undo.get(undo_cnt)[i]);
					T_parser.mr_load[i] =  Integer.parseInt( mr_undo.get(undo_cnt)[i]);
					T_parser.mb_load[i] =  Integer.parseInt( mb_undo.get(undo_cnt)[i]);
					T_parser.ml_load[i] =  Integer.parseInt( ml_undo.get(undo_cnt)[i]);
				}

				textArea2.setText(textArea_undo.get(undo_cnt));
//				if(textArea2.getText().contains("debug='on"))
//					debugCheckBox.setSelected(true);
//				else if(textArea2.getText().contains("debug='off"))
//					debugCheckBox.setSelected(false);

				try {
					int n = 0;
					int m = 0;
					n = GlobalEnv.getfilename().lastIndexOf("/") + 1;
					m = GlobalEnv.getfilename().lastIndexOf(".ssql") + 5;

					String cssname = GlobalEnv.getfilename().substring(n, m);
					cssname = cssname.replace(".ssql", ".css");

					String csspass = GlobalEnv.getOutputDirPath() + "/jscss/" + cssname;

					FileOutputStream fos = new FileOutputStream(csspass);//出力ファイル
					OutputStreamWriter osw = new OutputStreamWriter(fos, "SJIS");
					PrintWriter pw = new PrintWriter(osw);

					pw.write(css_undo.get(undo_cnt));
					pw.flush();
					//System.out.println("?<><><><><><><><><>");
					HTMLEnv.css.delete(0, HTMLEnv.css.length());
					HTMLEnv.css.append(css_undo.get(undo_cnt));
					//System.out.println(JSplitPaneTest1.jPanel_name.get(0));
					osw.close();
					pw.close();
					//Preview.preview(FrontEnd.Preview_URL);

				} catch (Exception ex) {
					System.out.println(ex);
				}

				Preview.preview(FrontEnd.Preview_URL);

				redo_btn.setEnabled(true);
				jButton.get(button_index).doClick();



				if (undo_cnt == 0) {
					undo_btn.setEnabled(false);
					return;
				}
			}

			else if (cmd == ">") {
				undo_cnt++;

				for(int i = 0; i < Tasuku.tfe_name_arr.size(); i++) {
					T_parser.w_load[i] =  Integer.parseInt( w_undo.get(undo_cnt)[i]);
					T_parser.h_load[i] =  Integer.parseInt( h_undo.get(undo_cnt)[i]);
					T_parser.f_load[i] =  Integer.parseInt( f_undo.get(undo_cnt)[i]);
					T_parser.mt_load[i] =  Integer.parseInt( mt_undo.get(undo_cnt)[i]);
					T_parser.mr_load[i] =  Integer.parseInt( mr_undo.get(undo_cnt)[i]);
					T_parser.mb_load[i] =  Integer.parseInt( mb_undo.get(undo_cnt)[i]);
					T_parser.ml_load[i] =  Integer.parseInt( ml_undo.get(undo_cnt)[i]);
				}

				textArea2.setText(textArea_undo.get(undo_cnt));
//				if(textArea2.getText().contains("debug='on"))
//					debugCheckBox.setSelected(true);
//				else if(textArea2.getText().contains("debug='off"))
//					debugCheckBox.setSelected(false);

				try {
					int n = 0;
					int m = 0;
					n = GlobalEnv.getfilename().lastIndexOf("/") + 1;
					m = GlobalEnv.getfilename().lastIndexOf(".ssql") + 5;

					String cssname = GlobalEnv.getfilename().substring(n, m);
					cssname = cssname.replace(".ssql", ".css");

					String csspass = GlobalEnv.getOutputDirPath() + "/jscss/" + cssname;
					//System.out.println(csspass);

					FileOutputStream fos = new FileOutputStream(csspass);//出力ファイル
					OutputStreamWriter osw = new OutputStreamWriter(fos, "SJIS");
					PrintWriter pw = new PrintWriter(osw);

					pw.write(css_undo.get(undo_cnt));
					pw.flush();
					//System.out.println("?<><><><><><><><><>");
					HTMLEnv.css.delete(0, HTMLEnv.css.length());
					HTMLEnv.css.append(css_undo.get(undo_cnt));
					//System.out.println(JSplitPaneTest1.jPanel_name.get(0));
					osw.close();
					pw.close();
					//Preview.preview(FrontEnd.Preview_URL);

				} catch (Exception ex) {
					System.out.println(ex);
				}

				Preview.preview(FrontEnd.Preview_URL);

				undo_btn.setEnabled(true);
				jButton.get(button_index).doClick();



				if (undo_cnt == textArea_undo.size() - 1) {
					redo_btn.setEnabled(false);
					return;
				}
			}
		}
	}

	public class panel_change_Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			if (swap_num == 0) {
				main_frame.remove(layout_panel);
				main_frame.getContentPane().add(table_line_Panel);
				main_frame.repaint();
				main_frame.revalidate();
				swap_num = 1;
			} else {
				main_frame.remove(table_line_Panel);
				main_frame.getContentPane().add(layout_panel);
				main_frame.repaint();
				main_frame.revalidate();
				swap_num = 0;
			}
		}
	}

	public class firstListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			Tasuku tasuku = new Tasuku();

			if (frame.isActive())
				frame.dispose();
			if (frame2.isActive())
				frame2.dispose();
			if (frame3.isActive())
				frame3.dispose();
			if (tasuku.isActive())
				tasuku.dispose();

			panel_option_flag = panel_option.isSelected();

			String ssql = textArea1.getText();
			Tasuku.Html_tfe_add();

			T_parser.parser(ssql);
			tasuku = new Tasuku();

		}
	}

	public class sizeinputListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (multi_select_flag == false)
				size_direct_change(e);
			else {
				for (int i = 0; i < multi_select_name.size(); i++) {
					for (int j = 0; j < Tasuku.tfe_name_arr.size(); j++) {
						if (multi_select_name.get(i).equals(Tasuku.tfe_name_arr.get(j))) {
							button_index = j;
							break;
						}
					}
					selectId = Tasuku.tfe_id_arr.get(button_index);
					panelName = multi_select_name.get(i);
					size_direct_change(e);

				}

			}
			Tasuku.Query_layout_add();
			Preview.preview(FrontEnd.Preview_URL);
		}
	}

	public void size_direct_change(ActionEvent e) {

		String input_w = w_input.getText();
		String input_h = h_input.getText();
		String input_f = f_input.getText();
		String input_mt = mt_input.getText();
		String input_mr = mr_input.getText();
		String input_mb = mb_input.getText();
		String input_ml = ml_input.getText();
		//String input_b = _input.getText();

		String a = e.getActionCommand();

		try {
			Integer.parseInt(input_w);
		} catch (NumberFormatException nfex) {
			if (a.equals("w_input"))
				return;
		}

		try {
			Integer.parseInt(input_h);
		} catch (NumberFormatException nfex) {
			if (a.equals("h_input"))
				return;
		}

		try {
			Integer.parseInt(input_f);
		} catch (NumberFormatException nfex) {
			if (a.equals("f_input"))
				return;
		}

		try {
			Integer.parseInt(input_mt);
		} catch (NumberFormatException nfex) {
			if (a.equals("mt_input"))
				return;
		}
		try {
			Integer.parseInt(input_mr);
		} catch (NumberFormatException nfex) {
			if (a.equals("mr_input"))
				return;
		}
		try {
			Integer.parseInt(input_mb);
		} catch (NumberFormatException nfex) {
			if (a.equals("mb_input"))
				return;
		}
		try {
			Integer.parseInt(input_ml);
		} catch (NumberFormatException nfex) {
			if (a.equals("ml_input"))
				return;
		}
		/*try {
			Integer.parseInt(input_b);
		} catch (NumberFormatException nfex) {
			if (a.equals("b_input"))
				return;
		}*/

		/*if(!NumberUtils.isNumber(input_w) && a.equals("w_input"))
			return;
		if(!NumberUtils.isNumber(input_h) && a.equals("h_input"))
			return;*/

		int cnt_tmp = 999;

		for (int i = 0; i < jPanel_name.size(); i++) {
			if (jPanel_name.get(i).equals(panelName))
				cnt_tmp = i;
		}

		if (cnt_tmp == 999)
			return;

		/*if(cnt_tmp != 999) {
		w = T_parser.w_load[cnt_tmp];
		h = T_parser.h_load[cnt_tmp];

		}*/

		StringBuffer sb = HTMLEnv.css;

		try {
			Integer.parseInt(input_w);

			T_parser.w_load[cnt_tmp] = Integer.valueOf(input_w);

			if(input_w.equals("0") || input_w.equals("00")) {
				int n = sb.indexOf(".TFE" + String.valueOf(selectId) + "{ width");
				int m = sb.indexOf("}", n);
				if(n != -1 && m != -1)
				sb.delete(n, m + 1);
			}

			else if (!sb.toString().contains(String.valueOf(selectId) + "{ width")) {

				sb.append(".TFE" + String.valueOf(selectId) + "{ width:" + input_w + "px; }");

			} else {

				int n = sb.indexOf(".TFE" + String.valueOf(selectId) + "{ width");
				int m = sb.indexOf("}", n);
				sb.delete(n, m + 1);
				sb.append(".TFE" + String.valueOf(selectId) + "{ width:" + (input_w) + "px; }");

			}
		} catch (NumberFormatException nfex) {

		}

		try {
			Integer.parseInt(input_h);

			T_parser.h_load[cnt_tmp] = Integer.valueOf(input_h);

			if(input_h.equals("0") || input_h.equals("00")) {
				int n = sb.indexOf(".TFE" + String.valueOf(selectId) + "{ height");
				int m = sb.indexOf("}", n);
				if(n != -1 && m != -1)
				sb.delete(n, m + 1);
			}

			else if (!sb.toString().contains(String.valueOf(selectId) + "{ height")) {

				sb.append(".TFE" + String.valueOf(selectId) + "{ height:" + input_h + "px; }");

			} else {
				int n = sb.indexOf(".TFE" + String.valueOf(selectId) + "{ height");
				int m = sb.indexOf("}", n);
				sb.delete(n, m + 1);
				sb.append(".TFE" + String.valueOf(selectId) + "{ height:" + input_h + "px; }");
			}
		}

		catch (NumberFormatException nfex) {

		}

		try {
			Integer.parseInt(input_f);

			T_parser.f_load[cnt_tmp] = Integer.valueOf(input_f);

			if(input_f.equals("0") || input_f.equals("00")) {
				int n = sb.indexOf(".TFE" + String.valueOf(selectId) + "{ font-size");
				int m = sb.indexOf("}", n);
				if(n != -1 && m != -1)
				sb.delete(n, m + 1);
			}

			else if (!sb.toString().contains(String.valueOf(selectId) + "{ font-size")) {

				sb.append(".TFE" + String.valueOf(selectId) + "{ font-size:" + input_f + "px; }");

			} else {
				int n = sb.indexOf(".TFE" + String.valueOf(selectId) + "{ font-size");
				int m = sb.indexOf("}", n);
				sb.delete(n, m + 1);
				sb.append(".TFE" + String.valueOf(selectId) + "{ font-size:" + input_f + "px; }");
			}
		} catch (NumberFormatException nfex) {

		}

		try {
			Integer.parseInt(input_mt);

			T_parser.mt_load[cnt_tmp] = Integer.valueOf(input_mt);

			if (!sb.toString().contains(String.valueOf(selectId) + "{ margin-top:")) {

				sb.append(".TFE" + String.valueOf(selectId) + "{ margin-top:" + input_mt + "px; }");

			} else {
				int n = sb.indexOf(".TFE" + String.valueOf(selectId) + "{ margin-top:");
				int m = sb.indexOf("}", n);
				sb.delete(n, m + 1);
				sb.append(".TFE" + String.valueOf(selectId) + "{ margin-top:" + input_mt + "px; }");
			}
		} catch (NumberFormatException nfex) {

		}

		try {
			Integer.parseInt(input_mr);

			T_parser.mr_load[cnt_tmp] = Integer.valueOf(input_mr);

			if (!sb.toString().contains(String.valueOf(selectId) + "{ margin-right:")) {

				sb.append(".TFE" + String.valueOf(selectId) + "{ margin-right:" + input_mr + "px; }");

			} else {
				int n = sb.indexOf(".TFE" + String.valueOf(selectId) + "{ margin-right:");
				int m = sb.indexOf("}", n);
				sb.delete(n, m + 1);
				sb.append(".TFE" + String.valueOf(selectId) + "{ margin-right:" + input_mr + "px; }");
			}
		} catch (NumberFormatException nfex) {

		}

		try {
			Integer.parseInt(input_mb);

			T_parser.mb_load[cnt_tmp] = Integer.valueOf(input_mb);

			if (!sb.toString().contains(String.valueOf(selectId) + "{ margin-bottom:")) {

				sb.append(".TFE" + String.valueOf(selectId) + "{ margin-bottom:" + input_mb + "px; }");

			} else {
				int n = sb.indexOf(".TFE" + String.valueOf(selectId) + "{ margin-bottom:");
				int m = sb.indexOf("}", n);
				sb.delete(n, m + 1);
				sb.append(".TFE" + String.valueOf(selectId) + "{ margin-bottom:" + input_mb + "px; }");
			}
		} catch (NumberFormatException nfex) {

		}

		try {
			Integer.parseInt(input_ml);

			T_parser.ml_load[cnt_tmp] = Integer.valueOf(input_ml);

			if (!sb.toString().contains(String.valueOf(selectId) + "{ margin-left:")) {

				sb.append(".TFE" + String.valueOf(selectId) + "{ margin-left:" + input_ml + "px; }");

			} else {
				int n = sb.indexOf(".TFE" + String.valueOf(selectId) + "{ margin-left:");
				int m = sb.indexOf("}", n);
				sb.delete(n, m + 1);
				sb.append(".TFE" + String.valueOf(selectId) + "{ margin-left:" + input_ml + "px; }");
			}
		} catch (NumberFormatException nfex) {

		}

		try {
			int n = 0;
			int m = 0;
			n = GlobalEnv.getfilename().lastIndexOf("/") + 1;
			m = GlobalEnv.getfilename().lastIndexOf(".ssql") + 5;

			String cssname = GlobalEnv.getfilename().substring(n, m);
			cssname = cssname.replace(".ssql", ".css");

			String csspass = GlobalEnv.getOutputDirPath() + "/jscss/" + cssname;
			//System.out.println(csspass);

			FileOutputStream fos = new FileOutputStream(csspass);//出力ファイル
			OutputStreamWriter osw = new OutputStreamWriter(fos, "SJIS");
			PrintWriter pw = new PrintWriter(osw);

			pw.println(sb.toString());

			pw.flush();

			pw.close();

		} catch (Exception e1) {
			System.out.println(e1);

		}

	}

	public class debugListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (filename != null) {
				try {
					//Preview.preview2();
					FileWriter file1 = new FileWriter("tmp.html", true);
					BufferedWriter pw1 = new BufferedWriter(new BufferedWriter(file1));
					//FileWriter file2 = new FileWriter("tmp.html", false);
					//BufferedWriter pw2 = new BufferedWriter(new BufferedWriter(file2));
					String tmp = "";
					StringBuffer a = new StringBuffer();

					try (BufferedReader in = new BufferedReader(new FileReader(new File(html_file)))) {
						String line = "";
						int f = 0;
						int l = 0;
						int l2 = 0;
						//String //html_tmp = "";//

						while ((line = in.readLine()) != null) {
							if (line.contains("<table") && line.contains("border")) {
								f = line.indexOf("border") + 7;
								l2 = line.indexOf("\"", f);
								l = line.indexOf("\"", l2);
								if (f < 0 || l2 < 0) {
									pw1.write(line);
									//html_tmp += line + "\n";
									continue;
								}
//								if (debugCheckBox.isSelected())
//									tmp = line.substring(0, f) + "\"1" + line.substring(l + 2);
//								else
//									tmp = line.substring(0, f) + "\"0" + line.substring(l + 2);

								pw1.write(tmp);
								pw1.newLine();
								//html_tmp += tmp + "\n";
							} else {
								pw1.write(line);
								pw1.newLine();
								//html_tmp += line + "\n";
							}
						}

						pw1.close();

						Path sourcePath = Paths.get("tmp.html");
						Path destinationPath = Paths.get(html_file);

						try {
							Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
						} catch (IOException e1) {
							e1.printStackTrace();
						}

						File newdir = new File("tmp.html");
						newdir.delete();

//						if (debugCheckBox.isSelected())
//							T_parser.table_at_flag2 = true;
//						else {
							T_parser.table_at_flag2 = false;
//						}
						line_action_flag = true;
						//html_undo.add(//html_tmp);
						Tasuku.Query_layout_add();

						Preview.preview(FrontEnd.Preview_URL);

					} catch (FileNotFoundException e2) {
						e2.printStackTrace();
						System.exit(-1); // 0 以外は異常終了
					}
				} catch (IOException e3) {
					e3.printStackTrace();
					//System.exit(-1);
				}
			}
		}
	}

	public class saveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String command = e.getActionCommand();

			switch (command) {

			case "保存しないで終了":

				JFrame frame = new JFrame();
				frame.setAlwaysOnTop(true);
				int option = JOptionPane.showConfirmDialog(frame, "保存せずに終了しますか？", "確認", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);

				if (option == JOptionPane.YES_OPTION) {
					try {
						int n = 0;
						int m = 0;
						n = GlobalEnv.getfilename().lastIndexOf("/") + 1;
						m = GlobalEnv.getfilename().lastIndexOf(".ssql") + 5;

						String cssname = GlobalEnv.getfilename().substring(n, m);
						cssname = cssname.replace(".ssql", ".css");

						String csspass = GlobalEnv.getOutputDirPath() + "/jscss/" + cssname;
						//System.out.println(csspass);

						FileOutputStream fos = new FileOutputStream(csspass);//出力ファイル
						OutputStreamWriter osw = new OutputStreamWriter(fos, "SJIS");
						PrintWriter pw = new PrintWriter(osw);

						pw.println(FrontEnd.pre_css);
						//System.out.println(HTMLEnv.css.toString());
						//System.out.println(JSplitPaneTest1.jPanel_name.get(0));

						pw.close();
						Preview.preview(FrontEnd.Preview_URL);

					} catch (Exception ex) {
						System.out.println(ex);

					}

					System.exit(0);
				}
				break;

			case "保存":

				write_SSQL(textArea2.getText());
				FrontEnd.pre_css = HTMLEnv.css.toString();

				break;

			case "保存して終了":

				write_SSQL(textArea2.getText());
				System.exit(0);

				break;

			default:
				break;
			}

		}
	}

	public void write_SSQL(String text) {

		try {
			String filename = GlobalEnv.getfilename();
			BufferedWriter in = new BufferedWriter(new FileWriter(new File(filename)));

			in.write(text);
			//in.flush();
			in.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public class myListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			label_name.setEnabled(true);
			panelName = e.getActionCommand();

			label_name.setText(panelName);

			if (multi_select_flag == true) {
				//multi_select.clear();
				multi_select_name.clear();
				for (int i = 0; i < multi_select.size(); i++) {
					multi_select.get(i).setSelected(false);
				}
				multi_select_flag = false;
			}

			/*if (panel_option_flag == true) {

				checkbox.addActionListener(new myListener2());
				p.add(checkbox, BorderLayout.NORTH);
			}*/

			int cnt_tmp = 999;
			int w = 9990;
			int h = 9990;
			w_input.setText("default");
			h_input.setText("default");

			for (int i = 0; i < jPanel_name.size(); i++) {
				if (jPanel_name.get(i).equals(panelName))
					cnt_tmp = i;
			}
			if (cnt_tmp != 999 && T_parser.w_load[cnt_tmp] != 0) {
				w = T_parser.w_load[cnt_tmp];
			}
			if (cnt_tmp != 999 && T_parser.h_load[cnt_tmp] != 0) {
				h = T_parser.h_load[cnt_tmp];
			}


			if (w != 9990)
				w_input.setText(String.valueOf(w));
			if (h != 9990)
				h_input.setText(String.valueOf(h));

			int cnt_tmp2 = 999;
			int f = 9990;
			int mt = 9990;
			int mr = 9990;
			int mb = 9990;
			int ml = 9990;
			f_input.setText("default");
			mt_input.setText("default");
			mr_input.setText("default");
			mb_input.setText("default");
			ml_input.setText("default");

			for (int i = 0; i < jPanel_name.size(); i++) {
				if (jPanel_name.get(i).equals(panelName))
					cnt_tmp2 = i;
			}

			if (cnt_tmp2 != 999 && T_parser.f_load[cnt_tmp2] != 0)
				f = T_parser.f_load[cnt_tmp2];
			if (cnt_tmp2 != 999 && T_parser.mt_load[cnt_tmp2] != 0)
				mt = T_parser.mt_load[cnt_tmp2];
			if (cnt_tmp2 != 999 && T_parser.mr_load[cnt_tmp2] != 0)
				mr = T_parser.mr_load[cnt_tmp2];
			if (cnt_tmp2 != 999 && T_parser.mb_load[cnt_tmp2] != 0)
				mb = T_parser.mb_load[cnt_tmp2];
			if (cnt_tmp2 != 999 && T_parser.ml_load[cnt_tmp2] != 0)
				ml = T_parser.ml_load[cnt_tmp2];


			if (f != 9990)
				f_input.setText(String.valueOf(f));
			if (mt != 9990)
				mt_input.setText(String.valueOf(mt));
			if (mr != 9990)
				mr_input.setText(String.valueOf(mr));
			if (mb != 9990)
				mb_input.setText(String.valueOf(mb));
			if (ml != 9990)
				ml_input.setText(String.valueOf(ml));


			for (int i = 0; i < size_fixed_name.size(); i++)
				if (size_fixed_name.get(i).equals(panelName)) {
					checkbox.setSelected(true);
					//checkbox.doClick();
					break;
				} else {
					checkbox.setSelected(false);
				}

			for (int i = 0; i < Tasuku.tfe_name_arr.size(); i++)
				if (panelName.equals(Tasuku.tfe_name_arr.get(i))) {
					button_index = i;
					break;
				}
			selectId = Tasuku.tfe_id_arr.get(button_index);

			frame.setAlwaysOnTop(true);
			main_frame.setAlwaysOnTop(false);
			main_frame.setAlwaysOnTop(true);

			cn++;
		}
	}

	public class myListener2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String cmd = e.getActionCommand();

			if (multi_select_flag == false)
				layout_exe(cmd);
			else {
				for (int i = 0; i < multi_select_name.size(); i++) {
					for (int j = 0; j < Tasuku.tfe_name_arr.size(); j++)
						if (multi_select_name.get(i).equals(Tasuku.tfe_name_arr.get(j))) {
							button_index = j;
							break;
						}
					selectId = Tasuku.tfe_id_arr.get(button_index);
					panelName = multi_select_name.get(i);
					layout_exe(cmd);

				}

			}
			Tasuku.Query_layout_add();
			Preview.preview(FrontEnd.Preview_URL);

		}
	}

	public void layout_exe(String cmd) {

		if (multi_select_flag == false)
			label_name.setText("<html> <center>" + panelName + "<br>" + "</center> </html>");

		if (panel_option_flag == true) {

			if (checkbox.isSelected() == true) {
				size_fixed_name.add(panelName);
			} else if (checkbox.isSelected() == false) {
				for (int j = 0; j < size_fixed_name.size(); j++)
					if (size_fixed_name.get(j).equals(panelName)) {
						size_fixed_name.remove(j);
					}
			}
		}

		//panelName = label_name.getText();
		if (multi_select_flag)
			panelName = multi_panel_name;

		StringBuffer sb = HTMLEnv.css;

		if (cmd.equals("A □ □")) {

			label_name.setText("<html> <center>" + panelName + "<br>" + "align = LEFT" + "</center> </html>");
			panel_name.add(label_name);

			//frame2.getContentPane().add(panel_name, 0);

			if (!sb.toString().contains(String.valueOf(selectId) + "{ text-align"))
				sb.append(".TFE" + String.valueOf(selectId) + "{ text-align:left; }");
			else {
				int n = sb.indexOf(".TFE" + String.valueOf(selectId) + "{ text-align");
				int m = sb.indexOf("}", n);
				//System.out.println(n + "   " + m);
				sb.delete(n, m + 1);
				sb.append(".TFE" + String.valueOf(selectId) + "{ text-align:left; }");
			}

		} else if (cmd.equals("□ A □")) {

			label_name.setText("<html> <center>" + panelName + "<br>" + "align = CENTER" + "</center> </html>");
			panel_name.add(label_name);

			if (!sb.toString().contains(String.valueOf(selectId) + "{ text-align"))
				sb.append(".TFE" + String.valueOf(selectId) + "{ text-align:center; }");
			else if (sb.toString().contains(String.valueOf(selectId) + "{ text-align")) {
				int n = sb.indexOf(".TFE" + String.valueOf(selectId) + "{ text-align");
				int m = sb.indexOf("}", n);
				//System.out.println(n + "   " + m);
				//if(n != -1 && m != -1)
				sb.delete(n, m + 1);
				sb.append(".TFE" + String.valueOf(selectId) + "{ text-align:center; }");
			}

		} else if (cmd.equals("□ □ A")) {

			label_name.setText("<html> <center>" + panelName + "<br>" + "align = RIGHT" + "</center> </html>");
			panel_name.add(label_name);

			if (!sb.toString().contains(String.valueOf(selectId) + "{ text-align"))
				sb.append(".TFE" + String.valueOf(selectId) + "{ text-align:right; }");
			else if (sb.toString().contains(String.valueOf(selectId) + "{ text-align")) {
				int n = sb.indexOf(".TFE" + String.valueOf(selectId) + "{ text-align");
				int m = sb.indexOf("}", n);
				//System.out.println(n + "   " + m);
				sb.delete(n, m + 1);
				sb.append(".TFE" + String.valueOf(selectId) + "{ text-align:right; }");
			}

		} else if (cmd.equals("Bold")) {

			label_name.setText("<html> <center>" + panelName + "<br>" + "→ BOLD" + "</center> </html>");
			panel_name.add(label_name);

			if (!sb.toString().contains(String.valueOf(selectId) + "{ font-weight"))
				sb.append(".TFE" + String.valueOf(selectId) + "{ font-weight: bold; }");
			else if (sb.toString().contains(String.valueOf(selectId) + "{ text-align")) {
				int n = sb.indexOf(".TFE" + String.valueOf(selectId) + "{ font-weight");
				int m = sb.indexOf("}", n);
				//System.out.println(n + "   " + m);
				sb.delete(n, m + 1);
				sb.append(".TFE" + String.valueOf(selectId) + "{ font-weight: bold; }");

			}
		} else if (cmd.equals("Italic")) {

			label_name.setText("<html> <center>" + panelName + "<br>" + "→ ITALIC" + "</center> </html>");
			panel_name.add(label_name);

			if (!sb.toString().contains(String.valueOf(selectId) + "{ font-style"))
				sb.append(".TFE" + String.valueOf(selectId) + "{ font-style: Italic; }");
			else if (sb.toString().contains(String.valueOf(selectId) + "{ font-style")) {
				int n = sb.indexOf(".TFE" + String.valueOf(selectId) + "{ font-style");
				int m = sb.indexOf("}", n);
				//System.out.println(n + "   " + m);
				sb.delete(n, m + 1);
				sb.append(".TFE" + String.valueOf(selectId) + "{ font-style: Italic; }");

			}
		} else if (cmd.equals("Normal")) {

			label_name.setText("<html> <center>" + panelName + "<br>" + "→ NORMAL" + "</center> </html>");
			panel_name.add(label_name);

			if (sb.toString().contains(String.valueOf(selectId) + "{ font-weight")) {
				int n = sb.indexOf(".TFE" + String.valueOf(selectId) + "{ font-weight");
				int m = sb.indexOf("}", n);
				//System.out.println(n + "   " + m);
				sb.delete(n, m + 1);
			}
			if (sb.toString().contains(String.valueOf(selectId) + "{ font-style")) {
				int n = sb.indexOf(".TFE" + String.valueOf(selectId) + "{ font-style");
				int m = sb.indexOf("}", n);
				//System.out.println(n + "   " + m);
				sb.delete(n, m + 1);
			}
		} else if (cmd.equals("A↑")) {

			if (!sb.toString().contains(String.valueOf(selectId) + "{ font-size")) {
				sb.append(".TFE" + String.valueOf(selectId) + "{ font-size: 18px; }");
				T_parser.f_load[button_index] = 18;
			} else if (sb.toString().contains(String.valueOf(selectId) + "{ font-size")) {
				int n = sb.indexOf(".TFE" + String.valueOf(selectId) + "{ font-size");
				int m = sb.indexOf("}", n);
				//System.out.println(n + "   " + m);
				sb.delete(n, m + 1);
				T_parser.f_load[button_index] += 4;
				sb.append(".TFE" + String.valueOf(selectId) + "{ font-size: "
						+ String.valueOf(T_parser.f_load[button_index]) + "px; }");
			}

			label_name.setText("<html> <center>" + panelName + "<br>" + "Font-Size = " + T_parser.f_load[button_index]
					+ "px</center> </html>");
			panel_name.add(label_name);
			f_input.setText(String.valueOf(T_parser.f_load[button_index]));

		} else if (cmd.equals("A↓")) {

			if (!sb.toString().contains(String.valueOf(selectId) + "{ font-size")) {
				sb.append(".TFE" + String.valueOf(selectId) + "{ font-size: 14px; }");
				T_parser.f_load[button_index] = 14;
			} else if (sb.toString().contains(String.valueOf(selectId) + "{ font-size")) {
				int n = sb.indexOf(".TFE" + String.valueOf(selectId) + "{ font-size");
				int m = sb.indexOf("}", n);
				//System.out.println(n + "   " + m);
				sb.delete(n, m + 1);
				if (T_parser.f_load[button_index] > 4)
					T_parser.f_load[button_index] -= 4;
				sb.append(".TFE" + String.valueOf(selectId) + "{ font-size: "
						+ String.valueOf(T_parser.f_load[button_index]) + "px; }");
			}
			label_name.setText("<html> <center>" + panelName + "<br>" + "Font-Size = " + T_parser.f_load[button_index]
					+ "px</center> </html>");
			panel_name.add(label_name);
			f_input.setText(String.valueOf(T_parser.f_load[button_index]));
		} else if (cmd.equals("□ ■ □")) {

			label_name.setText("<html> <center>" + "TABLE" + "<br>" + "→ CENTER" + "</center> </html>");
			panel_name.add(label_name);

			int n1 = sb.indexOf("body {");
			int m1 = sb.indexOf("}", n1);
			int t = sb.indexOf("text-align");
			int n2 = sb.indexOf("#ssql_body_contents");
			int m2 = sb.indexOf("}", n2);

			sb.delete(n2, m2 + 1);
			sb.insert(m1 + 1, "\n#ssql_body_contents {\n" +
					"	display: inline-block;\n" +
					"	text-align: left;\n" +
					"}");

			if (t == -1 || (t != -1 && t > m1))
				sb.insert(m1 - 1, "\ntext-align: center;");
			else if ((n1 < t && t < m1) && sb.toString().contains("{ text-align")) {
				int m = sb.indexOf(";", t);
				sb.delete(t, m + 1);
				sb.insert(n1 + 7, "text-align: center;");
			}
		} else if (cmd.equals("□ □ ■")) {

			label_name.setText("<html> <center>" + "TABLE" + "<br>" + "→ RIGHT" + "</center> </html>");
			panel_name.add(label_name);

			int n1 = sb.indexOf("body {");
			int m1 = sb.indexOf("}", n1);
			int t = sb.indexOf("text-align");
			int n2 = sb.indexOf("#ssql_body_contents");
			int m2 = sb.indexOf("}", n2);

			sb.delete(n2, m2 + 1);
			sb.insert(m1 + 1, "\n#ssql_body_contents {\n" +
					"	display: inline-block;\n" +
					"	text-align: left;\n" +
					"}");

			if (t == -1 || (t != -1 && t > m1))
				sb.insert(m1 - 1, "\ntext-align: right;");
			else if ((n1 < t && t < m1) && sb.toString().contains("{ text-align")) {
				int m = sb.indexOf(";", t);
				sb.delete(t, m + 1);
				sb.insert(n1 + 7, "text-align: right;");
			}
		} else if (cmd.equals("■ □ □")) {

			label_name.setText("<html> <center>" + "TABLE" + "<br>" + "→ LEFT" + "</center> </html>");
			panel_name.add(label_name);

			int n1 = sb.indexOf("body {");
			int m1 = sb.indexOf("}", n1);
			int t = sb.indexOf("text-align");
			int n2 = sb.indexOf("#ssql_body_contents");
			int m2 = sb.indexOf("}", n2);

			sb.delete(n2, m2 + 1);
			sb.insert(m1 + 1, "\n#ssql_body_contents {\n" +
					"	display: inline-block;\n" +
					"	text-align: left;\n" +
					"}");

			if ((n1 < t && t < m1) && sb.toString().contains("{ text-align")) {
				int m = sb.indexOf(";", t);
				sb.delete(t, m + 1);
			}
		}


		try {
			int n = 0;
			int m = 0;
			n = GlobalEnv.getfilename().lastIndexOf("/") + 1;
			m = GlobalEnv.getfilename().lastIndexOf(".ssql") + 5;

			String cssname = GlobalEnv.getfilename().substring(n, m);
			cssname = cssname.replace(".ssql", ".css");

			String csspass = GlobalEnv.getOutputDirPath() + "/jscss/" + cssname;
			System.out.println(csspass);

			FileOutputStream fos = new FileOutputStream(csspass);//出力ファイル
			OutputStreamWriter osw = new OutputStreamWriter(fos, "SJIS");
			PrintWriter pw = new PrintWriter(osw);

			pw.println(sb.toString());
			pw.flush();

			pw.close();

		} catch (Exception ex) {
			System.out.println(ex);

		}

	}

	public class myListener3 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			//main_frame.setAlwaysOnTop(false);
			code = "";

			JColorChooser colorchooser = new JColorChooser();
			JFrame colorFrame = new JFrame();
			colorFrame.add(colorchooser);
			colorFrame.setAlwaysOnTop(true);
			//colorchooser.setPreferredSize(new Dimension(300, 300));

			Color selectedColor = JColorChooser.showDialog(colorchooser, "Color Select", Color.black);
			colorchooser.setLocation(w / 4, h / 2);
			colorchooser.setSize(w / 8, h / 8);

			if (selectedColor == null) {
				//return;
			} else {
				int dec = selectedColor.getRed();
				String hex = Integer.toHexString(dec);
				if (hex.length() == 1)
					code += "0";
				code += hex;
				dec = selectedColor.getGreen();
				hex = Integer.toHexString(dec);
				if (hex.length() == 1)
					code += "0";
				code += hex;
				dec = selectedColor.getBlue();
				hex = Integer.toHexString(dec);
				if (hex.length() == 1)
					code += "0";
				code += hex;
			}

			if (multi_select_flag == false)
				Color_font();
			else {
				for (int i = 0; i < multi_select_name.size(); i++) {
					for (int j = 0; j < Tasuku.tfe_name_arr.size(); j++)
						if (multi_select_name.get(i).equals(Tasuku.tfe_name_arr.get(j))) {
							button_index = j;
							break;
						}
					selectId = Tasuku.tfe_id_arr.get(button_index);
					panelName = multi_select_name.get(i);
					Color_font();

				}

			}

			//main_frame.setAlwaysOnTop(true);
			Tasuku.Query_layout_add();
			Preview.preview(FrontEnd.Preview_URL);
		}
	}

	public class myListener4 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			//main_frame.setAlwaysOnTop(false);
			code = "";

			JColorChooser colorchooser = new JColorChooser();
			//colorchooser.setPreferredSize(new Dimension(300, 300));
			JFrame colorFrame = new JFrame();
			colorFrame.add(colorchooser);
			colorFrame.setAlwaysOnTop(true);
			Color selectedColor = JColorChooser.showDialog(colorchooser, "Color Select", Color.white);
			colorchooser.setLocation(w / 4, h / 2);
			colorchooser.setSize(w / 8, h / 8);

			if (selectedColor == null) {

			} else {

				int dec = selectedColor.getRed();
				String hex = Integer.toHexString(dec);
				if (hex.length() == 1)
					code += "0";
				code += hex;
				dec = selectedColor.getGreen();
				hex = Integer.toHexString(dec);
				if (hex.length() == 1)
					code += "0";
				code += hex;
				dec = selectedColor.getBlue();
				hex = Integer.toHexString(dec);
				if (hex.length() == 1)
					code += "0";
				code += hex;
				//System.out.println(code);
			}

			if (multi_select_flag == false)
				Color_background();
			else {
				for (int i = 0; i < multi_select_name.size(); i++) {
					for (int j = 0; j < Tasuku.tfe_name_arr.size(); j++)
						if (multi_select_name.get(i).equals(Tasuku.tfe_name_arr.get(j))) {
							button_index = j;
							break;
						}
					selectId = Tasuku.tfe_id_arr.get(button_index);
					panelName = multi_select_name.get(i);
					Color_background();

				}

			}
			//main_frame.setAlwaysOnTop(true);

			Tasuku.Query_layout_add();
			Preview.preview(FrontEnd.Preview_URL);
		}
	}

	public class myListener5 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			//main_frame.setAlwaysOnTop(false);
			code = "";

			JColorChooser colorchooser = new JColorChooser();
			//colorchooser.setPreferredSize(new Dimension(300, 300));
			JFrame colorFrame = new JFrame();
			colorFrame.add(colorchooser);
			colorFrame.setAlwaysOnTop(true);
			Color selectedColor = JColorChooser.showDialog(colorchooser, "Color Select", Color.white);
			colorchooser.setLocation(w / 4, h / 2);
			colorchooser.setSize(w / 8, h / 8);

			if (selectedColor == null) {

			} else {

				int dec = selectedColor.getRed();
				String hex = Integer.toHexString(dec);
				if (hex.length() == 1)
					code += "0";
				code += hex;
				dec = selectedColor.getGreen();
				hex = Integer.toHexString(dec);
				if (hex.length() == 1)
					code += "0";
				code += hex;
				dec = selectedColor.getBlue();
				hex = Integer.toHexString(dec);
				if (hex.length() == 1)
					code += "0";
				code += hex;
				//System.out.println(code);
			}

			Color_page();
			//main_frame.setAlwaysOnTop(true);

			Tasuku.Query_layout_add();
			Preview.preview(FrontEnd.Preview_URL);
		}
	}


	public class myListener6 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String label = "";
			multi_select_name.clear();

			for (int i = 0; i < multi_select.size(); i++)
				if (multi_select.get(i).isSelected() == true) {
					multi_select_name.add(jPanel_name.get(i));
					if (label == "")
						label += jPanel_name.get(i);
					else
						label += " , " + jPanel_name.get(i);
				}

			label_name.setText(label);

			if (multi_select_name.size() != 0) {
				multi_select_flag = true;
				multi_panel_name = label;
			} else
				multi_select_flag = false;

			if (multi_select_name.size() != 1) {
				w_input.setText("");
				h_input.setText("");
				f_input.setText("");
				mt_input.setText("");
				mr_input.setText("");
				mb_input.setText("");
				ml_input.setText("");
			} else {
				int cnt_tmp = 999;
				int w = 9990;
				int h = 9990;
				int f = 9990;

				int mt = 9990;
				int mr = 9990;
				int mb = 9990;
				int ml = 9990;

				w_input.setText("default");
				h_input.setText("default");
				f_input.setText("default");
				mt_input.setText("default");
				mr_input.setText("default");
				mb_input.setText("default");
				ml_input.setText("default");

				for (int i = 0; i < jPanel_name.size(); i++) {
					if (jPanel_name.get(i).equals(label))
						cnt_tmp = i;
				}
				if (cnt_tmp != 999 && T_parser.w_load[cnt_tmp] != 0) {
					w = T_parser.w_load[cnt_tmp];
				}
				if (cnt_tmp != 999 && T_parser.h_load[cnt_tmp] != 0) {
					h = T_parser.h_load[cnt_tmp];
				}
				if (cnt_tmp != 999 && T_parser.f_load[cnt_tmp] != 0) {
					f = T_parser.f_load[cnt_tmp];
				}

				if (cnt_tmp != 999 && T_parser.mt_load[cnt_tmp] != 0)
					mt = T_parser.mt_load[cnt_tmp];
				if (cnt_tmp != 999 && T_parser.mr_load[cnt_tmp] != 0)
					mr = T_parser.mr_load[cnt_tmp];
				if (cnt_tmp != 999 && T_parser.mb_load[cnt_tmp] != 0)
					mb = T_parser.mb_load[cnt_tmp];
				if (cnt_tmp != 999 && T_parser.ml_load[cnt_tmp] != 0)
					ml = T_parser.ml_load[cnt_tmp];

				//w_input.addActionListener(new sizeinputListener());
				//h_input.addActionListener(new sizeinputListener());

				if (w != 9990)
					w_input.setText(String.valueOf(w));
				if (h != 9990)
					h_input.setText(String.valueOf(h));
				if (f != 9990)
					f_input.setText(String.valueOf(f));
				if (mt != 9990)
					mt_input.setText(String.valueOf(mt));
				if (mr != 9990)
					mr_input.setText(String.valueOf(mr));
				if (mb != 9990)
					mb_input.setText(String.valueOf(mb));
				if (ml != 9990)
					ml_input.setText(String.valueOf(ml));

				if (multi_select_name.size() == 0)
					layout_panel.setEnabled(false);

			}
		}
	}

	public class panel_optionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			panel_option_flag = panel_option.isSelected();

			if (panel_option_flag == true) {
				JFrame frame1 = new JFrame();
				frame1.setAlwaysOnTop(true);
				int option = JOptionPane.showConfirmDialog(frame1, "すでに幅が指定されている場合，サイズが変更されます。\nよろしいですか？", "確認",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);

				if (option == 0) {
					frame.setResizable(true);
					panel_option_flag = true;

					Tasuku.Time_reload();
					resizePanel();
				} else {
					frame.setResizable(false);
					panel_option.setSelected(false);
					panel_option_flag = false;
				}
			}
			if (panel_option_flag == false) {
				frame.setResizable(false);
				//resizePanel();
			}

		}
	}

	public class myListener7 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			//main_frame.setAlwaysOnTop(false);

			int num = 0;
			for (int i = 0; i < btn_line_color.length; i++)
				if (btn_line_color[i] == e.getSource())
					num = i;
			//System.out.println(num);
			code = "";

			JColorChooser colorchooser = new JColorChooser();
			JFrame colorFrame = new JFrame();
			colorFrame.add(colorchooser);
			colorFrame.setAlwaysOnTop(true);
			//colorchooser.setPreferredSize(new Dimension(300, 300));

			Color selectedColor = JColorChooser.showDialog(colorchooser, "Color Select", Color.white);
			colorchooser.setLocation(w / 4, h / 2);
			colorchooser.setSize(w / 8, h / 8);

			if (selectedColor == null) {

			} else {

				int dec = selectedColor.getRed();
				String hex = Integer.toHexString(dec);
				if (hex.length() == 1)
					code += "0";
				code += hex;
				dec = selectedColor.getGreen();
				hex = Integer.toHexString(dec);
				if (hex.length() == 1)
					code += "0";
				code += hex;
				dec = selectedColor.getBlue();
				hex = Integer.toHexString(dec);
				if (hex.length() == 1)
					code += "0";
				code += hex;
				//System.out.println(code);
			}

			Color_line(num);
			line_color[num] = "#" + code;

			//main_frame.setAlwaysOnTop(true);


			Tasuku.Query_layout_add();
			Preview.preview(FrontEnd.Preview_URL);
		}
	}

	public class myListener8 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			int num = 0;
			for (int i = 0; i < line_input.length; i++)
				if (line_input[i] == e.getSource())
					num = i;

			try {
				Integer.parseInt(line_input[num].getText());
			} catch (NumberFormatException nfex) {
				return;
			}
			String line_s = String.valueOf(line_input[num].getText());
			T_parser.line_list.remove(num);
			T_parser.line_list.add(num, String.valueOf(line_input[num].getText()));

			if (filename != null) {
				try {
					FileWriter file1 = new FileWriter("tmp.html", true);
					BufferedWriter pw1 = new BufferedWriter(new BufferedWriter(file1));
					//FileWriter file2 = new FileWriter("tmp.html", false);
					//BufferedWriter pw2 = new BufferedWriter(new BufferedWriter(file2));
					String tmp = "";
					StringBuffer a = new StringBuffer();

					try (BufferedReader in = new BufferedReader(new FileReader(new File(html_file)))) {
						String line = "";
						String pre_line = "";
						int f = 0;
						int l = 0;
						int l2 = 0;

						String tfe = String.valueOf(Tasuku.rrr.get(num));
					//	String //html_tmp = "";
						while ((line = in.readLine()) != null) {
							if (line.contains("<hr") && pre_line.contains(tfe)) {
								f = line.indexOf("size") + 6;
								l2 = line.indexOf("\"", f);
								l = line.indexOf("\"", l2);
								if (f < 0 || l < 0) {
									pre_line = line;
									//html_tmp += line + "\n";
									continue;
								}
								tmp = line.substring(0, f) + line_s + line.substring(l);
								pw1.write(tmp);
								pw1.newLine();
								//html_tmp += tmp + "\n";
							} else {
								pw1.write(line);
								pw1.newLine();
								//html_tmp += line + "\n";
							}
							pre_line = line;

						}

						pw1.close();

						Path sourcePath = Paths.get("tmp.html");
						Path destinationPath = Paths.get(html_file);

						try {
							Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
							//html_undo.add(//html_tmp);
						} catch (IOException e3) {
							e3.printStackTrace();
						}

						File newdir = new File("tmp.html");
						newdir.delete();

					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
						System.exit(-1); // 0 以外は異常終了
					}
				} catch (IOException e2) {
					e2.printStackTrace();
					//System.exit(-1);
				}

				line_size[num] = line_s;

				line_action_flag = true;
				Tasuku.Query_layout_add();
				line_action_flag = false;
				Preview.preview(FrontEnd.Preview_URL);
			}
		}
	}

	void resizePanel() {


	}

	//カラーチャート
	static void Color_font() {

		if (multi_select_flag)
			panelName = multi_panel_name;

		//System.out.println(code);

		if (code.length() > 2) {

			if (!HTMLEnv.css.toString().contains(String.valueOf(selectId) + "{ color:"))
				HTMLEnv.css.append(".TFE" + String.valueOf(selectId) + "{ color:#" + code + "; }");
			else {
				int n = HTMLEnv.css.indexOf(".TFE" + String.valueOf(selectId) + "{ color:");
				int m = HTMLEnv.css.indexOf("}", n);
				//System.out.println(n + "   " + m);
				HTMLEnv.css.delete(n, m + 1);
				HTMLEnv.css.append(".TFE" + String.valueOf(selectId) + "{ color:#" + code + "; }");
			}

			label_name
					.setText("<html> <center>" + panelName + "<br>" + "Font-COLOR = #" + code + "</center> </html>");
			panel_name.add(label_name);

			try {
				int n = 0;
				int m = 0;
				n = GlobalEnv.getfilename().lastIndexOf("/") + 1;
				m = GlobalEnv.getfilename().lastIndexOf(".ssql") + 5;

				String cssname = GlobalEnv.getfilename().substring(n, m);
				cssname = cssname.replace(".ssql", ".css");

				String csspass = GlobalEnv.getOutputDirPath() + "/jscss/" + cssname;
				//System.out.println(csspass);

				FileOutputStream fos = new FileOutputStream(csspass);//出力ファイル
				OutputStreamWriter osw = new OutputStreamWriter(fos, "SJIS");
				PrintWriter pw = new PrintWriter(osw);

				pw.println(HTMLEnv.css.toString());

				pw.close();

			} catch (Exception ex) {
				System.out.println(ex);

			}
		}

	}

	static void Color_background() {

		//panelName = label_name.getText();
		if (multi_select_flag)
			panelName = multi_panel_name;

		if (code.length() > 2) {
			if(code.length() > 2)
			if (!HTMLEnv.css.toString().contains(String.valueOf(selectId) + "{ background-color:"))
				HTMLEnv.css.append(".TFE" + String.valueOf(selectId) + "{ background-color:#" + code + "; }");
			else {
				int n = HTMLEnv.css.indexOf(".TFE" + String.valueOf(selectId) + "{ background-color:");
				int m = HTMLEnv.css.indexOf("}", n);
				//System.out.println(n + "   " + m);
				HTMLEnv.css.delete(n, m + 1);
				HTMLEnv.css.append(".TFE" + String.valueOf(selectId) + "{ background-color:#" + code + "; }");
			}

			label_name.setText(
					"<html> <center>" + panelName + "<br>" + "Background-COLOR = #" + code + "</center> </html>");
			panel_name.add(label_name);

			try {
				int n = 0;
				int m = 0;
				n = GlobalEnv.getfilename().lastIndexOf("/") + 1;
				m = GlobalEnv.getfilename().lastIndexOf(".ssql") + 5;

				String cssname = GlobalEnv.getfilename().substring(n, m);
				cssname = cssname.replace(".ssql", ".css");

				String csspass = GlobalEnv.getOutputDirPath() + "/jscss/" + cssname;
				//System.out.println(csspass);

				FileOutputStream fos = new FileOutputStream(csspass);//出力ファイル
				OutputStreamWriter osw = new OutputStreamWriter(fos, "SJIS");
				PrintWriter pw = new PrintWriter(osw);

				pw.println(HTMLEnv.css.toString());

				pw.close();

			} catch (Exception ex) {
				System.out.println(ex);

			}
		}
	}

	static void Color_page() {

		int n1 = HTMLEnv.css.indexOf("body {");
		int m1 = HTMLEnv.css.indexOf("}", n1);
		int t = HTMLEnv.css.indexOf("background-color");


		if (t == -1 || (t != -1 && t > m1))
			HTMLEnv.css.insert(m1 - 1, " background-color: #" + code + "; ");
		else {
			int m = HTMLEnv.css.indexOf(";", t);
			HTMLEnv.css.delete(t, m + 1);
			HTMLEnv.css.insert(n1 + 7, "text-align: center;");
			HTMLEnv.css.insert(n1 + 7, " background-color: #" + code + "; ");
		}

		label_name.setText(
				"<html> <center>" + "TABLE" + "<br>" + "Background-COLOR = #" + code + "</center> </html>");
		panel_name.add(label_name);

		try {
			int n = 0;
			int m = 0;
			n = GlobalEnv.getfilename().lastIndexOf("/") + 1;
			m = GlobalEnv.getfilename().lastIndexOf(".ssql") + 5;

			String cssname = GlobalEnv.getfilename().substring(n, m);
			cssname = cssname.replace(".ssql", ".css");

			String csspass = GlobalEnv.getOutputDirPath() + "/jscss/" + cssname;
			//System.out.println(csspass);

			FileOutputStream fos = new FileOutputStream(csspass);//出力ファイル
			OutputStreamWriter osw = new OutputStreamWriter(fos, "SJIS");
			PrintWriter pw = new PrintWriter(osw);

			pw.println(HTMLEnv.css.toString());
			//System.out.println(HTMLEnv.css.toString());
			//System.out.println(JSplitPaneTest1.jPanel_name.get(0));

			pw.close();

		} catch (Exception ex) {
			System.out.println(ex);

		}

	}

	void Color_line(int num) {

		if (filename != null) {
			try {
				FileWriter file1 = new FileWriter("tmp.html", true);
				BufferedWriter pw1 = new BufferedWriter(new BufferedWriter(file1));
				String tmp = "";
				StringBuffer a = new StringBuffer();

				try (BufferedReader in = new BufferedReader(new FileReader(new File(html_file)))) {
					String line = "";
					String pre_line = "";
					String tfe_id = "";
					String line_tmp = "";
					int f = 0;
					int l = 0;

					String tfe = String.valueOf(Tasuku.rrr.get(num));

					while ((line = in.readLine()) != null) {
						if (line.contains("<hr") && pre_line.contains(tfe)) {
							f = line.indexOf("color") + 7;
							l = line.indexOf(">", f);
							if (f < 0 || l < 0) {
								pre_line = line;
								line_tmp += line + "\n";
								continue;
							}
							tmp = line.substring(0, f) + "#" + code + "\"" + line.substring(l);
							pw1.write(tmp);
							pw1.newLine();
							line_tmp += tmp + "\n";
						} else {
							pw1.write(line);
							pw1.newLine();
							line_tmp += line + "\n";
						}
						pre_line = line;
					}

					pw1.close();

					Path sourcePath = Paths.get("tmp.html");
					Path destinationPath = Paths.get(html_file);

					try {
						Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e) {
						e.printStackTrace();
					}

					line_action_flag = true;
					//html_undo.add(line_tmp);
					File newdir = new File("tmp.html");
					newdir.delete();

				} catch (FileNotFoundException e) {
					e.printStackTrace();
					System.exit(-1); // 0 以外は異常終了
				}
			} catch (IOException e) {
				e.printStackTrace();
				//System.exit(-1);
			}
		}
	}
}

