package supersql.tasuku;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import javax.swing.JOptionPane;

import org.openqa.selenium.WebDriver;

import supersql.common.GlobalEnv;

public class Preview {

	static WebDriver driver;
	//static Desktop desktop = Desktop.getDesktop();

	public static boolean isLinux() {
		if (GlobalEnv.OS.indexOf("Linux") >= 0)
			return true;
		return false;
	}

	public static void preview(String Path) {

		try {

			if (!Desktop.isDesktopSupported()) {
				JOptionPane.showMessageDialog(null, "サポートされていません", "警 告",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			try {

				if (isLinux()) { // Linuxだった場合
					if (new File(Path).isFile()) {
						// ファイルならそれをブラウザで開く
						// 現状では実習専用 コマンドを指定してLinuxのbashシェルスクリプトから実行してブラウザを起動
						try {

							Desktop.getDesktop().open(new File(Path));

						} catch (Exception e) {
							System.out.println(e);
						}

					} else {
						try {

							Runtime.getRuntime().exec("firefox " + Path);
						} catch (IOException e) {
							System.out.println(e);
						}
					}
				} else {
					if (new File(Path).isFile()) {
						try {
							//Desktop.getDesktop().browse(new URI(Path));
							Desktop.getDesktop().open(new File(Path));
							//System.out.println("Mac1");
						} catch (IOException e) {
							System.out.println(e);
						}
					} else {
						try {
							//Desktop.getDesktop().browse(new URI(Path));
							Desktop.getDesktop().browse(new URI(Path));
							//System.out.println("Mac2");
						} catch (IOException e) {
							System.out.println(e);
						}
					}
				}
				/*}*/

			} catch (java.lang.IllegalArgumentException e) {

			}

		} catch (Exception e) {
			//e.printStackTrace();
			JSplitPaneTest1.label_name.setText("パスが間違っています");

		}
	}


}
