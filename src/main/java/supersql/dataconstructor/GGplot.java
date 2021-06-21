package supersql.dataconstructor;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.rosuda.JRI.Rengine;

import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;
import supersql.parser.Preprocessor;
import supersql.dataconstructor.TreeGenerator;

public class GGplot {

//	private static int count;
	private static int count = 0;
	private ExtList result;

	public GGplot () {
		result = new ExtList();
	}

	/* navigate the whole schema in this method */
	public ExtList ggplot(ExtList criteria_set, ExtList info, ExtList sch, ExtList tuples) {

//		System.out.println();
//		System.out.println(tuples);
//		System.out.println(sch);
//		System.out.println(info);
		
//if (tuples.toString().equals("[[]]")) {
//	return tuples;
//}
		boolean is_ggplot = false;
		boolean is_ggplot_1 = false;
		boolean aethFlag = true;
		int aeth[] = {-1, -1, -1, -1, -1};
		int count = -1;

		ExtList criteria_set_buffer = new ExtList();
		ExtList process_set = new ExtList();
		ExtList deep_set = new ExtList();


		Log.out(" * ggplot at the schema level " + sch + " *");
		//add tbt 180727
				//For forest
				//not to use attributes to other trees
				boolean is_forest = true;
				//tbt end

		/* current schema level */
		//Log.out("sch: " + sch + " size:" + sch.size());
		for (int i = 0; i < sch.size(); i++) {
			if (is_ggplot_1) {
				if (criteria_set.size() == 0 && sch.size() == 3 && aethFlag) {
					aethFlag = false;
					aeth[count] = Integer.parseInt(sch.get(i + 1).toString());
					continue;
				}
				is_ggplot_1 = false;
				continue;
			}
			
			/* attribute found in this current level */
			if (!(sch.get(i) instanceof ExtList)) {
				is_forest = false;
				is_ggplot = false;

				for (int j = 0; j < info.size(); j++) {
					/* "ggplot functions" found */
					//Log.out("info.get(" + j + "): " + info.get(j).toString().split(" ")[0]);
					//Log.out("sch.get(" + i + "): " + sch.get(i));
					if (info.get(j).toString().split(" ")[0].equals(sch.get(i).toString())){

						Log.out("    ggplot found : " + sch.get(i) + " with " + info.get(j).toString().split(" ")[1]);
						
						//Changed by li to fix the sch level
						initializeSepSch(sch);
						//Log.out("break sch: " + sch.get(1));
						//Log.out("break:" + info.get(j).toString().split("ggplot")[1]);
						String tmp_info;
						tmp_info = sch.get(0).toString() + ' ' + sch.get(1) + ' ' + "ggplot" + info.get(j).toString().split("ggplot")[1];
						//Log.out("break:" + tmp_info);
						is_ggplot = true;
						is_ggplot_1 = true;
						//process_set.add(info.get(j));
						process_set.add(tmp_info);
						//
						
						Log.out("process_set: " + process_set + " size: " + process_set.size());
						count++;

					}
				}
				
				/* push it into criteria_set, if "ggplot functions" not found */
				//Log.out("is_ggplot: " + is_ggplot);
				//Log.out("is_ggplot1: " + is_ggplot_1);
				
				//Comment out by li 20210610
				/*
				if (!is_ggplot) {
					Log.out("the criteria_set before: " + criteria_set);
//				if (count>=0 && !is_ggplot) {
					if (criteria_set.size() >= 1) {
						if (Integer.parseInt(sch.get(i).toString()) == Integer.parseInt(criteria_set.get(criteria_set.size() - 1).toString()) + 1) {
							criteria_set.add(sch.get(i));
						} else {
//							System.out.println(count+" "+i);
						}
					} else {
						criteria_set.add(sch.get(i));
					}
					Log.out("the criteria_set after: " + criteria_set);
				}
				*/
			/* inner level found in this current level */
			} else {
				deep_set.add(sch.get(i));

			}

		}

		int cnt = 0;
		/* do "ggplot functions" in this current level, if there is any */
		while (process_set.size() > 0) {

			tuples = makeGraph(criteria_set, process_set.get(0), tuples, aeth[cnt], result);


			Log.out("    ggplot process : " + process_set.get(0).toString().split(" ")[0] + " with " + process_set.get(0).toString().split(" ")[1]);
			criteria_set_buffer.add(process_set.get(0).toString().split(" ")[0]);
			process_set.remove(0);
			cnt++;

		}

		/* update criteria_set */
		for (int i = 0; i < criteria_set_buffer.size(); i++) {
			criteria_set.add(criteria_set_buffer.get(i));
		}

		Log.out("    set : " + criteria_set);

		/* calculate each inner level of this current level by recursion */
		while (deep_set.size() > 0) {

			GGplot ggplot = new GGplot();
			//add tbt 180727
			//if forest, it should not use same criteria_set
			if(is_forest){
				criteria_set.clear();
			}
			//end tbt
			tuples = ggplot.ggplot(criteria_set, info, (ExtList)(deep_set.get(0)), tuples);
			result = ggplot.getResult();
			deep_set.remove(0);

		}

		return tuples;

	}

	/* make graph in units of groups having the same contents in criteria_set */
	private ExtList makeGraph(ExtList criteria, Object process, ExtList tuples, int aeth, ExtList result) {
//		System.out.println("propa"+System.getProperty("java.library.path"));
		Log.out("criteria " + criteria);
		Log.out("process " + process);
		Log.out("tuples " + tuples);
		Log.out("aeth " + aeth);
		Log.out("result " + result);
		
		ExtList buffer = new ExtList();
		ExtList tuples_buffer = new ExtList();
		ExtList tmp = new ExtList();

		ExtList x;
		ExtList y;
		boolean flag = true;

		String target_x;
		String target_y;
		String aeth_type = "";



		Rengine engine;

		result.clear();

		if (!Preprocessor.isR()) {
			engine = new Rengine(new String[]{"--vanilla"}, false, null);
			new Preprocessor().setR();

		} else {
			engine = Rengine.getMainEngine();
		}
		engine.eval("setwd(\"" + GlobalEnv.getOutputDirPath() + "\")");
//		engine.eval(".libPaths(\"" + path + "/lib/site-library\")");
//		engine.eval(".libPaths(\"/Users/otawa/Documents/workspace/NewSSQL/lib/site-library\")");
//		engine.eval(".libPaths(\"/usr/local/lib/R/4.0/site-library\")");			//TODO_old -> おそらく無しでOK
		engine.eval("library(tidyverse)");
		engine.eval("library(plotly)");
		engine.eval("Sys.setlocale(\"LC_CTYPE\", \"ja_JP.UTF-8\")");

		target_x = process.toString().split(" ")[0];
		target_y = process.toString().split(" ")[1];
		Log.info("process: " + process.toString());
		while (tuples.size() > 0) {

			/* find tuples with the same criteria */

			x = (ExtList)(tuples.get(0));
			//System.out.println(x+" "+x.size()+" "+tuples.size());
//			if (x.size()<2) {
//				break;
//			}
			for (int i = 1; i < tuples.size(); i++) {
				y = (ExtList)(tuples.get(i));
				//Log.out("criteria: " + criteria);
				//Log.out("criteria_size: " + criteria.size());
				//Log.out("break x: " + x + " size: " + x.size() + " tuples.size" + tuples.size());
				//Log.out("break y: " + y + " size: " + y.size() + " tuples.size" + tuples.size());
				
				for (int k = 0; k < criteria.size(); k++) {
					
					if (!(x.get(Integer.parseInt(criteria.get(k).toString())).equals
					     (y.get(Integer.parseInt(criteria.get(k).toString()))))) {
						flag = false;
					}
				
				}
				if (flag) {
					buffer.add(y);
					tuples.remove(i--);
				} else {
					flag = true;
				}
			}
			buffer.add(x);
//			System.out.println("buffer:"+buffer);
			tuples.remove(0);


			List<String> buffer_x = new ArrayList<String>();
			List<String> buffer_y = new ArrayList<String>();
			List<String> buffer_aeth = new ArrayList<String>();

			for (int i = 0; i < buffer.size(); i++) {
				//Log.out("2.break i: " + i + " target_x: " + target_x);
				//Log.out("2.break i: " + i +  " target_y: " + target_y);
				//Log.out("2.break size of buffer " + buffer.size());
				buffer_x.add(buffer.getExtListString(i, Integer.parseInt(target_x)));
				buffer_y.add(buffer.getExtListString(i, Integer.parseInt(target_y)));
				if (aeth > 0)
					buffer_aeth.add(buffer.getExtListString(i, aeth));
			}

			try {
				int result_x[] = new int[buffer_x.size()];
				for (int i = 0; i < buffer_x.size(); i++) {
					result_x[i] = Integer.parseInt(buffer_x.get(i));
					Log.out("result_x(int): " + result_x);
				}
				engine.assign("result_x", result_x);
			} catch(NumberFormatException e) {
				try {
					double result_x[] = new double[buffer_x.size()];
					for (int i = 0; i < buffer_x.size(); i++) {
						result_x[i] = Double.parseDouble(buffer_x.get(i));
						Log.out("result_x(double): " + result_x);
					}
					engine.assign("result_x", result_x);
				} catch (NumberFormatException e1) {
					String result_x[] = new String[buffer_x.size()];
					
					for (int i = 0; i < buffer_x.size(); i++) {
						result_x[i] = buffer_x.get(i);
					}
					//Log.out("result_x(string): " + Arrays.toString(result_x));
					//added by li to check date
					try {
						SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
						for (int i = 0; i < buffer_x.size(); i++) {
							result_x[i] = buffer_x.get(i);
							Date date = sdFormat.parse(result_x[i]);
							Log.out("Is Date :" + date);
						}
						engine.assign("result_x", result_x);
						//added by li
						engine.eval("result_x <- as.Date(result_x)");
					} catch(ParseException e2) {
						//Log.out("Result_x is not a date.");
						engine.assign("result_x", result_x);
					}
				}
			}

			try {
				int result_y[] = new int[buffer_y.size()];
				for (int i = 0; i < buffer_y.size(); i++) {
					result_y[i] = Integer.parseInt(buffer_y.get(i));
				}
				engine.assign("result_y", result_y);
				//Log.out("result_y: " + result_y);
			} catch(NumberFormatException e) {
				try {
					double result_y[] = new double[buffer_y.size()];
					for (int i = 0; i < buffer_y.size(); i++) {
						result_y[i] = Double.parseDouble(buffer_y.get(i));
					}
					engine.assign("result_y", result_y);
					
				} catch (NumberFormatException e1) {
					String result_y[] = new String[buffer_y.size()];
					for (int i = 0; i < buffer_y.size(); i++) {
						result_y[i] = buffer_y.get(i);
					}
					//added by li to check date
					try {
						SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
						for (int i = 0; i < buffer_x.size(); i++) {
							result_y[i] = buffer_x.get(i);
							Date date = sdFormat.parse(result_y[i]);
							Log.out("Is Date :" + date);
						}
						engine.assign("result_y", result_y);
						//added by li
						engine.eval("result_y <- as.Date(result_y)");
					} catch(ParseException e2) {
						Log.out("Error!Result_y is not a date.");
						engine.assign("result_y", result_y);
					}
				}
			}

			if (aeth > 0) {
				try {
					int result_aeth[] = new int[buffer_aeth.size()];
					for (int i = 0; i < buffer_aeth.size(); i++) {
						result_aeth[i] = Integer.parseInt(buffer_aeth.get(i));
					}
					engine.assign("result_aeth", result_aeth);
				} catch(NumberFormatException e) {
					try {
						double result_aeth[] = new double[buffer_aeth.size()];
						for (int i = 0; i < buffer_aeth.size(); i++) {
							result_aeth[i] = Double.parseDouble(buffer_aeth.get(i));
						}
						engine.assign("result_aeth", result_aeth);
					} catch (NumberFormatException e1) {
						String result_aeth[] = new String[buffer_aeth.size()];
						for (int i = 0; i < buffer_aeth.size(); i++) {
							result_aeth[i] = buffer_aeth.get(i);
						}
						engine.assign("result_aeth", result_aeth);
					}
				}
			}

			String r_html_filename = GlobalEnv.getfilename3()+"_graph";
			try {
				r_html_filename += "_" + buffer.getExtListString(0, Integer.parseInt(criteria.getExtListString(0)));
				for (int i = 1; i < criteria.size(); i++) {
					r_html_filename += "_" + buffer.getExtListString(0, Integer.parseInt(criteria.getExtListString(i)));
				}
			} catch (Exception e) { }


//			System.out.println(process);
			int n = process.toString().split(":").length;



			if (aeth > 0) {
				engine.eval("frame <- data.frame(X=result_x, Y=result_y, AETH=result_aeth)");
				for (int i = 1; i < n; i++) {
					if (process.toString().split(":")[i].contains("color") && !process.toString().split(":")[i].contains("\"") ) {
						aeth_type = process.toString().split(":")[i].substring(0, process.toString().split(":")[i].indexOf("=") + 1);
					}
					if (process.toString().split(":")[i].contains("fill") && !process.toString().split(":")[i].contains("\"") ) {
						aeth_type = process.toString().split(":")[i].substring(0, process.toString().split(":")[i].indexOf("=") + 1);
					}
					if (process.toString().split(":")[i].contains("size") && !process.toString().split(":")[i].contains("\"") ) {
						aeth_type = process.toString().split(":")[i].substring(0, process.toString().split(":")[i].indexOf("=") + 1);
					}
					if (process.toString().split(":")[i].contains("group") && !process.toString().split(":")[i].contains("\"") ) {
						aeth_type = process.toString().split(":")[i].substring(0, process.toString().split(":")[i].indexOf("=") + 1);
					}
				}

				engine.eval("graph <- ggplot(data = frame, aes(x = X, y = Y, " + aeth_type +"AETH))");
				//engine.eval("graph <- ggplot(subset(data = frame, !is.na(x,y)), aes(x = X, y = Y, " + aeth_type +"AETH))");
			} else {
				engine.eval("frame <- data.frame(X=result_x, Y=result_y)");
				//engine.eval("frame <- subset(data.frame(X=result_x, Y=result_y), !is.na(x,y))");
				engine.eval("graph <- ggplot(data = frame, aes(x = X, y = Y))");
				//engine.eval("graph <- ggplot(data = subset(frame, !is.na(X), !is.na(Y)), aes(x = X, y = Y))");
			}

			for (int i = 1; i < n; i++) {
				if((process.toString().split(":")[i].contains("color") ||
					process.toString().split(":")[i].contains("fill") ||
					process.toString().split(":")[i].contains("size") ||
					process.toString().split(":")[i].contains("group") ||
					process.toString().split(":")[i].contains("width") ||
					process.toString().split(":")[i].contains("height"))
					&& !process.toString().split(":")[i].contains("\"")) {
					continue;
				}

				if (process.toString().split(":")[i].contains("geom_bar") ) {
					if (process.toString().split(":")[i].contains("=")) {
						engine.eval(" graph <- graph + geom_bar(stat = 'identity', " + process.toString().split(":")[i].split("\"")[1] + ")");
					} else {
						engine.eval(" graph <- graph + geom_bar(stat = 'identity')");
					}
					continue;
				}

				if (process.toString().split(":")[i].contains("=")) {
					engine.eval(" graph <- graph + " + process.toString().split(":")[i].split("=")[0] + "(" + process.toString().split(":")[i].split("\"")[1] + ")");
				} else {
					engine.eval(" graph <- graph + " + process.toString().split(":")[i] + "()");
				}



			/*	if (process.toString().split(":")[i].contains("geom_point") ) {
					if (process.toString().split(":")[i].contains("=")) {
						engine.eval(" graph <- graph + geom_point(" + process.toString().split(":")[i].split("\"")[1] + ")");
					} else {
						engine.eval(" graph <- graph + geom_point()");
					}
				}

				if (process.toString().split(":")[i].contains("geom_line") ) {
					if (process.toString().split(":")[i].contains("=")) {
						engine.eval(" graph <- graph + geom_line(" + process.toString().split(":")[i].split("\"")[1] + ")");
					} else {
						engine.eval(" graph <- graph + geom_line()");
					}
				}

				if (process.toString().split(":")[i].contains("geom_smooth") ) {
					if (process.toString().split(":")[i].contains("=")) {
						engine.eval(" graph <- graph + geom_smooth(" + process.toString().split(":")[i].split("\"")[1] + ")");
					} else {
						engine.eval(" graph <- graph + geom_smooth()");
					}
				}

				if (process.toString().split(":")[i].contains("geom_bar") ) {
					if (process.toString().split(":")[i].contains("=")) {
						engine.eval(" graph <- graph + geom_bar(stat = 'identity', " + process.toString().split(":")[i].split("\"")[1] + ")");
					} else {
						engine.eval(" graph <- graph + geom_bar(stat = 'identity')");
					}
				}

				if (process.toString().split(":")[i].contains("coord_flip") ) {
					engine.eval(" graph <- graph + coord_flip()");
				}

				if (process.toString().split(":")[i].contains("coord_polar") ) {
					engine.eval(" graph <- graph + coord_polar()");
				}

				if (process.toString().split(":")[i].contains("labs") ) {
					engine.eval(" graph <- graph + labs(" + process.toString().split(":")[i].split("\"")[1] + ")");
				}

////				if (process.toString().split(" ")[i].contains("errorbar") ) {
////					engine.eval(" graph <- graph + geom_errorbar()");
//				} */
			}

			engine.eval("graph <- ggplotly(graph)");
			Log.out("r: r_html_filename = "+r_html_filename + "_" + count + ".html");
			//engine.eval("htmlwidgets::saveWidget(as_widget(graph), \"" + r_html_filename + "_" + count + ".html\")");
			engine.eval("htmlwidgets::saveWidget(as_widget(graph), \"" + r_html_filename + "_" + count + ".html\", selfcontained=FALSE)");
	        engine.end();

//	        tmp = buffer.getExtList(0);
//	        tmp.set(Integer.parseInt(target_x), "ggplot" + name + ".html");
//	        tmp.set(Integer.parseInt(target_y), name);
//	        tuples_buffer.add(tmp);

	        for (int i = 0; i < buffer.size(); i++) {
	        		buffer.getExtList(i).set(Integer.parseInt(target_x), "ggplot" + r_html_filename + "_" + count + ".html");
	        		//buffer.getExtList(i).set(Integer.parseInt(target_y), r_html_filename);
	        		buffer.getExtList(i).set(Integer.parseInt(target_y), "");
//	        		System.out.println(buffer.getExtListString(i, Integer.parseInt(target_x)));
//	        		System.out.println(buffer.getExtListString(i, Integer.parseInt(target_y)));
	        }
//	        buffer.getExtList(0).set(Integer.parseInt(target_x), "ggplot" + name + ".html");
//	        buffer.getExtList(0).set(Integer.parseInt(target_y), name);
	        tuples_buffer.addAll(buffer);

	        result.add(buffer.getExtList(0));
			buffer.clear();

//			count++;
//			System.out.println(count);

		}
		count++;
		return tuples_buffer;

	}

	public ExtList getResult() {
		return result;
	}
	
	private void initializeSepSch(ExtList sep_sch){
		for (int i = 0; i < sep_sch.size(); i++) {
			try{
				ExtList sep_child = (ExtList)sep_sch.get(i);
				initializeSepSch(sep_child);
			}catch(ClassCastException e){
				sep_sch.remove(i);
				sep_sch.add(i, count);
				count++;
			}
		}
	}
}
