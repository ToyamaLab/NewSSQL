package supersql.common;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

public class LogInfo {
	// Loggerオブジェクト
	static Logger log = null;
	// 設定ファイル
	private final String INFO_XML = GlobalEnv.getworkingDir() + GlobalEnv.OS_FS + "log4j" + GlobalEnv.OS_FS + "log4j2_info.xml";


	public LogInfo(){
//		DOMConfigurator.configure(INFO_XML);
		if (log == null) {
			// (1)設定ファイルの読み込み
			File file = new File(INFO_XML);
			LoggerContext context = (LoggerContext) LogManager.getContext(false);
			context.setConfigLocation(file.toURI());
			
			// (2)Loggerオブジェクトの生成
			log = LogManager.getLogger(LogInfo.class.getName());
		}
	}

	public static void logInfo(boolean succeeded) {
		if(GlobalEnv.isLogger() && !GlobalEnv.queryInfo.trim().isEmpty()){
			if(succeeded)
				GlobalEnv.queryInfo +=  "o" + " | " + GlobalEnv.queryLog + " | " + Ssedit.getautocorrectValue();
			LogInfo info = new LogInfo();
			info.logInfo(GlobalEnv.queryInfo);
		}
	}

	private void logInfo(String queryInfo) {
//		log.debug(queryInfo);
		log.trace(queryInfo);
	}
}

