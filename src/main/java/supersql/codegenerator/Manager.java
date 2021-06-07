package supersql.codegenerator;

import java.io.Serializable;

import supersql.extendclass.ExtList;

/**
 * ?象クラスManager。ソース生成にあたっての共通な機能をまとめた。 前処?、スキーマ生成、インスタンス移行、後処?。
 */
public abstract class Manager implements Serializable{
	/*
	 * 必要ならばコメントアウトを外す = 要?談 =
	 * 
	 * //前処? public void preProcess(TFE tfe_info, ExtList data_info, LocalEnv
	 * localenv ){ }
	 * 
	 * //クラス?義の生成 public void createSchema(TFE tfe_info, ExtList data_info,
	 * LocalEnv localenv ){ }
	 *  
	 */
	public DecorateList decos = new DecorateList();
	public void setDeco(DecorateList d) {
		decos = d;
	}

	public void addDeco(String key, Object val) {
		decos.put(key, val);
	}
	public void generateCode(ITFE tfe_info, ExtList data_info) {
	}
	
	public StringBuffer generateCode2(ITFE tfe_info, ExtList data_info) {
		StringBuffer code = new StringBuffer();
		return code;
	}
	
	//return css code for embed function & form
	public StringBuffer generateCode3(ITFE tfe_info, ExtList data_info) {
		StringBuffer css = new StringBuffer();
		System.out.println("cssfile ="+ css.toString());
		return css;
	}
	
	//return header code for form
	public StringBuffer generateCode4(ITFE tfe_info, ExtList data_info) {
		StringBuffer header = new StringBuffer();
		return header;
}
	public StringBuffer generateCodeNotuple(ITFE tfe_info) {
		StringBuffer code = new StringBuffer();
		return code;
	}

	//return cssfile for embed function
	public StringBuffer generateCssfile(ITFE tfe_info, ExtList data_info) {
		StringBuffer cssfile = new StringBuffer();		
		return cssfile;
	}
	
	/*
	 * 必要ならばコメントアウトを外す = 要?談 =
	 * 
	 * //インスタンス移行プ?前ラムの生成 public void generateCode2(TFE tfe_info, ExtList
	 * data_info){ }
	 * 
	 *  
	 */

	//後処?
	public abstract void finish();
}
