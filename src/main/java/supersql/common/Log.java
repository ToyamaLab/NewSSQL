package supersql.common;
import supersql.codegenerator.Ehtml;
import supersql.codegenerator.Incremental;

public class Log {

	private static int flag = 0; // Log.outを出力するかしないかのフラグ(デフォルトはしない)
	private static int infoflag = 1; // Log.infoを出力するかしないかのフラグ(デフォルトはする)

	private static int dotcount = 0; // TODO

	private static StringBuffer buf = new StringBuffer(); // TODO

	public static void setLog(int f) {
		flag = f;
	}

	public static void setinfoLog(int f) {
		infoflag = f;
	}

	public static void out(Object o) {

		switch (flag) {
		case 0:
			// do nothing
			break;
		case 1:
			// ログ出力
			System.out.println(o.toString());
			break;
		case 2:
			// エラー出力
			Log.err(o.toString());
			break;
		case 3:
			// TODO
			if (dotcount >= 600) {
				dotreturn();
			}
			if (dotcount % 10 == 9) {
				System.out.print(".");
			}
			dotcount++;
			break;
		case 10:
			// TODO
			buf.append(o.toString()).append('\n');
			break;
		}

	}

	private static void dotreturn() {
		if (flag == 3 && dotcount >= 9) {
			System.out.print("\n");
			dotcount = 0;
		}
	}

	public static void initBuffer() {
		buf = new StringBuffer();
	}

	public static String getBuffer() {
		return buf.toString();
	}

	// ログ出力 (-quietで出力しない)
	public static void info(Object o) {
		// add 20151118 masato for ehtml
		// TODO 
		if (Ehtml.flag || Ehtml.isEhtml2() || Incremental.flag || Incremental.flag2) {
			// System.out.println(o.toString());	//for Debug
			return;
		}
		switch (infoflag) {
		case 0:
			// do nothing
			break;
		case 1:
			// ログ出力
			dotreturn();
			System.out.println(o.toString());
			break;
		case 2:
			// エラー出力
			Log.err(o.toString());
			break;
		case 10:
			// buffer // TODO
			buf.append(o.toString()).append('\n');
			break;
		}
	}

	public static void err(Object o) {
		if (Ehtml.flag || Incremental.flag || Ehtml.isEhtml2()) {
			System.out.println(o.toString());	//for Debug
		}else{
			System.err.println(o.toString());
			GlobalEnv.errorText += o.toString();
		}
	}

	public static void ehtmlInfo(Object o) {
		// add 20141204 masato for ehtml
		System.out.println(o.toString());
	}

	// added by goto 20130415
	public static void i(Object o) {
		info(o);
	}

	public static void o(Object o) {
		out(o);
	}

	public static void e(Object o) {
		err(o);
	}
}
