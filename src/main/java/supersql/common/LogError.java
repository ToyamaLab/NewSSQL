package supersql.common;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

public class LogError {
	// Loggerオブジェクト
	static Logger log = null;
	// 設定ファイル
	private final String ERR_XML = GlobalEnv.getworkingDir() + GlobalEnv.OS_FS + "log4j" + GlobalEnv.OS_FS + "log4j2_err.xml";


	public LogError(){
//		DOMConfigurator.configure(ERR_XML);
		if (log == null) {
			// (1)設定ファイルの読み込み
			File file = new File(ERR_XML);
			LoggerContext context = (LoggerContext) LogManager.getContext(false);
			context.setConfigLocation(file.toURI());
			
			// (2)Loggerオブジェクトの生成
			log = LogManager.getLogger(LogInfo.class.getName());
		}
	}

	public static void logErr() {
		if(GlobalEnv.isLogger()){
			GlobalEnv.queryInfo += " x " + " | " + GlobalEnv.queryLog + " | " + Ssedit.getautocorrectValue();
			GlobalEnv.errorText_main += GlobalEnv.getusername() + " | " + GlobalEnv.errorText;
			LogError error = new LogError();
			error.logErr(GlobalEnv.errorText_main);
			LogInfo.logInfo(false);
		}
	}

	private void logErr(String queryInfo) {
//		log.debug(queryInfo);
		log.trace(queryInfo);
	}
}

