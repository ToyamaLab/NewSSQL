package supersql.codegenerator.VR;

import supersql.common.GlobalEnv;

public class VRcjoinarray {
	public static String query;

	private static String removeComment(){////クエリからコメントアウト除去
		StringBuffer tmp = new StringBuffer();
		String commentOutLetters = ""+GlobalEnv.COMMENT_OUT_LETTER+GlobalEnv.COMMENT_OUT_LETTER;
		try{
			String[] lines = query.split("[\\n\\r]");
					
			for (int lineNumber = 0; lineNumber < lines.length; lineNumber++){	
				String line = lines[lineNumber];
				while (line.contains("/*")){
					String line1 = line.substring(0, line.indexOf("/*"));
					while (!line.contains("*/")){
						if(lineNumber < lines.length) {
							line = lines[lineNumber];
							lineNumber++;
						} 
					}
					line = line1 + line.substring(line.indexOf("*/") + 2);
				}		
						
				if (line.contains(commentOutLetters) || line.contains("\\\"") || line.contains("\"\"")){	//commentOutLetters = "--"
					boolean dqFlg = false;
					int i = 0;
					for (i=0; i < line.length(); i++){
						if (!dqFlg && line.charAt(i) == '"' && i>0 && i<line.length()-1
						&& (line.charAt(i-1) != '\\' && line.charAt(i-1) != '"' && line.charAt(i+1) != '"'))	//omit \" and ""
							dqFlg = true;
						else if (dqFlg && line.charAt(i) == '"' && i>0 && i<line.length()-1
						&& (line.charAt(i-1) != '\\' && line.charAt(i-1) != '"' && line.charAt(i+1) != '"'))	//omit \" and ""
							dqFlg = false;
		
						if(dqFlg && i>0 && (line.charAt(i-1)=='\\' || line.charAt(i-1) == '"') && line.charAt(i)=='"'){	//if \" or ""
									line = line.substring(0,i-1)+"&quot;"+line.substring(i+1,line.length());
						} else if (!dqFlg && i < line.length()-1 && line.charAt(i)==GlobalEnv.COMMENT_OUT_LETTER && line.charAt(i+1)==GlobalEnv.COMMENT_OUT_LETTER){
							break;
						}
					}
					line = line.substring(0, i);
				}
					
				tmp.append(" " + line);
			}
					
		} catch (Exception e) {
		}
		String query1 = tmp.toString().trim();
		return query1;
	}
	
	
	public static String getTFE() {///クエリからgenerate VRとfrom〜除去
		int fromIndex = 0;///fromが始める位置
		String query2 = removeComment();///コメントアウト除去したクエリ代入
		query2 = query2.replaceAll("  "," ");
		java.util.regex.Pattern p = java.util.regex.Pattern.compile("[\\W]from[\\W]");
		java.util.regex.Matcher m = p.matcher(query2.toLowerCase());
		if (m.find()){
			fromIndex = m.start();	
		}
		String tfe = query2.substring("generate VR".length(), fromIndex);
		tfe = tfe.replaceAll("[\\n\\r]", "");
		return tfe;
	}
	
	public static void getJoin(){///ビルの繋げ方の記号を取って、配列に格納
		String c ="";
		int count = 0;
		boolean prevBrack = false;//Previous character is a closing bracket.
		String tfe = getTFE();
		String join = tfe.replaceAll(" ","");
		for(int i=0; i<join.length(); i++){
			c = join.substring(i,i+1);
			if(c.equals("[")) {
		       count++;
		    }
			if(count == 0){
		    	if(!prevBrack && (c.equals(",") || c.equals("!") || c.equals("%")))
		    	{
		    		VRAttribute.cjoinarray.add(c);
		    	}
		    }
			if(c.equals("]")) {
		       count--;
		       prevBrack = true;
			}else{
				prevBrack = false;
			}		    
		}
		

	}
	
	public static void getexhJoin(){///展示物の繋げ方の記号を取って、配列に格納　[name,name]のやつ
		String c ="";
		String s ="";
		int count = 0;
		int exhcount = 0;
		boolean prevBrack = false;//Previous character is a closing bracket bracket.
		String tfe = getTFE();
		String join = tfe.replaceAll(" ","");
		for(int i=0; i<join.length();i++){
			c = join.substring(i,i+1);
			if(c.equals("[")) {
		       count++;
		    }
			if(count == 2){
		    	if(!prevBrack && (c.equals(",") || c.equals("!") || c.equals("%")))
		    	{
		    		s += c;
		    		exhcount++;
		    	}
		    }
			if(c.equals("]")) {
		       count--;
		       prevBrack = true;
		       if(count == 0){
		    		VRAttribute.multiexhary.add(s);//groupごとにTFEを格納
		    		VRAttribute.multiexhcount.add(exhcount+1);///groupごとに何個nameがあるか
		    		s ="";
		    		exhcount = 0;
		       }
			}else{
				prevBrack = false;
			}		    
		}
	}	
}
