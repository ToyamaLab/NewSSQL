package supersql.parser.org.antlr.v4.runtime;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.FailedPredicateException;
import org.antlr.v4.runtime.InputMismatchException;
import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.IntervalSet;

import supersql.common.GlobalEnv;
import supersql.common.Ssedit;

public class MyErrorStrategy extends DefaultErrorStrategy{

	@Override
	public void recover(Parser recognizer, RecognitionException e) {
////		System.out.println("recover in "+recognizer.getRuleInvocationStack()+
////						   " index="+recognizer.getInputStream().index()+
////						   ", lastErrorIndex="+
////						   lastErrorIndex+
////						   ", states="+lastErrorStates);
//		if ( lastErrorIndex==recognizer.getInputStream().index() &&
//			lastErrorStates != null &&
//			lastErrorStates.contains(recognizer.getState()) ) {
//			// uh oh, another error at same token index and previously-visited
//			// state in ATN; must be a case where LT(1) is in the recovery
//			// token set so nothing got consumed. Consume a single token
//			// at least to prevent an infinite loop; this is a failsafe.
////			System.err.println("seen error condition before index="+
////							   lastErrorIndex+", states="+lastErrorStates);
////			System.err.println("FAILSAFE consumes "+recognizer.getTokenNames()[recognizer.getInputStream().LA(1)]);
//			recognizer.consume();
//		}
//		lastErrorIndex = recognizer.getInputStream().index();
//		if ( lastErrorStates==null ) {
//			lastErrorStates = new IntervalSet();
//		}
//		lastErrorStates.add(recognizer.getState());
//		IntervalSet followSet = getErrorRecoverySet(recognizer);
//		consumeUntil(recognizer, followSet);
	}

	@Override
	public void reportError(Parser recognizer,
							RecognitionException e)
	{

		if (inErrorRecoveryMode(recognizer)) {
			return;
		}
		beginErrorCondition(recognizer);
		if ( e instanceof NoViableAltException ) {
			reportNoViableAlternative(recognizer, (NoViableAltException) e);
		}
		else if ( e instanceof InputMismatchException ) {
			reportInputMismatch(recognizer, (InputMismatchException)e);
		}
		else if ( e instanceof FailedPredicateException ) {
			reportFailedPredicate(recognizer, (FailedPredicateException)e);
		}
		else {
			System.err.println("unknown recognition error type: "+e.getClass().getName());
			recognizer.notifyErrorListeners(e.getOffendingToken(), e.getMessage(), e);
		}
	}

	protected void reportNoViableAlternative(Parser recognizer,NoViableAltException e){
		TokenStream tokens = recognizer.getInputStream();
		String input;
		String before_offending = null;
		String offendingtoken = null;
		String after_offending = null;
		if ( tokens!=null ) {
			if ( e.getStartToken().getType()==Token.EOF ){
				input = "<EOF>";
				before_offending = tokens.getText().substring(0, e.getOffendingToken().getStartIndex());
				offendingtoken = e.getOffendingToken().getText();

			}else if(e.getOffendingToken().getText().equals("<EOF>")){
				before_offending = tokens.getText().substring(0, e.getOffendingToken().getStartIndex());
				offendingtoken = e.getOffendingToken().getText();
			}
			else {
				input = tokens.getText(e.getStartToken(), e.getOffendingToken());
				before_offending = tokens.getText().substring(0, e.getOffendingToken().getStartIndex());
				offendingtoken = tokens.getText().substring(e.getOffendingToken().getStartIndex(), e.getOffendingToken().getStartIndex()+ e.getOffendingToken().getText().length());
				after_offending = tokens.getText().substring(e.getOffendingToken().getStartIndex()+ e.getOffendingToken().getText().length());
			}
		}else {
			input = "<unknown input>";
		}
		String msg_detail = null;
		String msg = null;

		if(before_offending.toLowerCase().contains("from")){
			if(before_offending.trim().substring(before_offending.trim().length()-1).equals(";")){
				msg_detail = "****query is end with ';'.*****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"
				+ msg_detail + before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n"  ;
			}
			else if(offendingtoken.equals("{")){
				msg_detail = "****in from clause, use '( )' not '{ }'.*****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"
						+ msg_detail + before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n"  ;
			}
			else{
				msg_detail = "****error in FROM clause.*****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"
						+ msg_detail + before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n" ;
			}
		}
		else if(offendingtoken.matches("[0-9]+") && before_offending.trim().substring(before_offending.trim().length()-1).equals("]")){
			msg_detail = "****composite iterator wasn't described correctly : '[ ],NUMBER!' or '[ ],NUMBER!NUMBER%' or '[ ],NUMBER% or '[ ]!NUMBER,' or '[ ]!NUMBER,NUMBER%' or '[ ]!NUMBER%''*****";
			msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"
			+ msg_detail + before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n"  ;
		}
		else if(before_offending.trim().substring(before_offending.trim().length()-1).equals("]")){///////////////find error grouper//////////
			msg_detail = "*****did you mean ']!' or '],' ?*****";
			msg = "parse error detected. \nThe OffendingToken is : "+
			"'" + e.getOffendingToken().getText() + "'\n"
					+ before_offending.trim().substring(0, before_offending.trim().length()-1) + msg_detail +
					" >>>>>" + before_offending.trim().substring(before_offending.trim().length()-1) + "<<<<< "
					+ offendingtoken + after_offending + "\n" ;
		}
		else if(offendingtoken.contains("@{")){///////////////find error the place of decorator //////////////////////
			msg_detail = "*****decorator can't appear here*****";
			msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() +
					"'\n"+ msg_detail + before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n" ;
		}
		else if(offendingtoken.contains("@")){///////////////find error in decorator//////////////////////
			msg_detail = "*****decorator doesn't describe correctly : @{ }*****";
			msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() +
					"'\n"+ msg_detail + before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n" ;
		}
		else if(offendingtoken.equals("}") && before_offending.trim().substring(before_offending.trim().length()-1).equals("{")){
			msg_detail = "*****nothing described in '{ }'*****";
			msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n" + msg_detail
					+ before_offending.substring(0, before_offending.trim().lastIndexOf("{"))
					+ " >>>>>" + before_offending.substring(before_offending.trim().lastIndexOf("{"), before_offending.length()) + offendingtoken + "<<<<< "
					+ after_offending + "\n" ;
		}else if(offendingtoken.equals("]") && before_offending.trim().substring(before_offending.trim().length()-1).equals("[")){
			msg_detail = "*****nothing described in '[ ] : grouper'*****";
			msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n" + msg_detail
					+ before_offending.substring(0, before_offending.lastIndexOf("["))
					+ " >>>>>" + before_offending.substring(before_offending.trim().lastIndexOf("["), before_offending.length()) + offendingtoken + "<<<<< "
					+ after_offending + "\n" ;
		}else if(offendingtoken.equals(")") && before_offending.trim().substring(before_offending.trim().length()-1).equals("(")){
			msg_detail = "*****nothing described in '( )'*****";
			msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n" + msg_detail
					+ before_offending.substring(0, before_offending.lastIndexOf("("))
					+ " >>>>>" + before_offending.substring(before_offending.trim().lastIndexOf("("), before_offending.length()) + offendingtoken + "<<<<< "
					+ after_offending + "\n" ;
		}
		else if(offendingtoken.toLowerCase().equals("from")
				&& (before_offending.trim().substring(before_offending.trim().length()-1).equals(",")
						|| before_offending.trim().substring(before_offending.trim().length()-1).equals("!"))
				){
			if(before_offending.trim().substring(before_offending.trim().length()-2,before_offending.trim().length()-1).equals("]")){
				msg_detail = "*****lacks token before '"+ offendingtoken +"' : '}' or ']' or ')'*****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"  + msg_detail
						+ before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n" ;
			}
//			else{
//				msg_detail = "*****extra connector detected. before '"+ offendingtoken +"'*****";
//				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"
//				+ before_offending.trim().substring(0, before_offending.trim().length()-1) +
//				" >>>>>" + before_offending.trim().substring(before_offending.trim().length()-1) + "<<<<< " + offendingtoken + after_offending + "\n" + msg_detail ;
//			}
		}
		else if(
					(offendingtoken.equals("}")
						|| offendingtoken.equals("]")
						|| offendingtoken.equals(")")
					) &&
					(before_offending.trim().substring(before_offending.trim().length()-1).equals(",")
						|| before_offending.trim().substring(before_offending.trim().length()-1).equals("!")
					) &&
					(!before_offending.trim().substring(before_offending.trim().length()-2,before_offending.trim().length()-1).equals("]"))
				){
			msg_detail = "*****extra '" + before_offending.trim().substring(before_offending.trim().length()-1) + "' detected before '"+ offendingtoken +"'*****";
			msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"
			+ before_offending.trim().substring(0, before_offending.trim().length()-1) + msg_detail  +
			" >>>>>" + before_offending.trim().substring(before_offending.trim().length()-1) + "<<<<< " + offendingtoken + after_offending + "\n" ;
		}
		else if(offendingtoken.equals("}")){
			if(!before_offending.contains("{")){
				msg_detail = "*****dosen't exist the the pair of '}' : did you mean ']' or ')' ?*****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"
						+ msg_detail + before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n" ;
			}else{
				msg_detail = "*****extra parenthis detected : '}'. did you mean ']' or ')' ?*****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"
						+ msg_detail + before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n" ;
			}
		}
		else if(offendingtoken.equals("]")){
			if(!before_offending.contains("[")){
				msg_detail = "*****dosen't exist the the pair of ']' : did you mean '}' or ')' ?*****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"
						+ msg_detail + before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n" ;
			}else{
				msg_detail = "*****extra bracket detected : ']'. did you mean '}' or ')' ?*****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"
						+ msg_detail + before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n" ;
			}
		}
		else if(offendingtoken.equals(")")){
			if(!before_offending.contains("(")){
				msg_detail = "*****dosen't exist the the pair of '}' : did you mean ']' or '}' ?*****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"
						 + msg_detail + before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n" ;
			}else{
				msg_detail = "*****extra brace detected : ')'. did you mean ']' or '}' ?*****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"
						+ msg_detail + before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n"  ;
			}
		}
		else if((offendingtoken.equals("{") || offendingtoken.equals("[") || offendingtoken.equals("("))
				&& (before_offending.trim().substring(before_offending.trim().length()-1).equals(")"))){
			Pattern p = Pattern.compile("\\(asc[0-9]*\\)$");
			Pattern q = Pattern.compile("\\(desc[0-9]*\\)$");
			Matcher m_p = p.matcher(before_offending.trim());
			Matcher m_q = q.matcher(before_offending.trim());
			if(m_p.find() || m_q.find()){
				msg_detail = "****did you mistake the place of '(asc)' or '(desc)' ?*****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"
				+ before_offending.trim().substring(0, before_offending.trim().lastIndexOf("(")) + msg_detail
				+ " >>>>>" + before_offending.trim().substring(before_offending.trim().lastIndexOf("("), before_offending.trim().length()) + "<<<<< "
				+ offendingtoken + after_offending + "\n" ;
			}else if(before_offending.trim().substring(before_offending.trim().length()-1).equals(")")){
				Pattern q1 = Pattern.compile("[a-zA-Z0-9]+\\(.+\\)$");
				Pattern q2 = Pattern.compile("[a-zA-Z0-9]+\\(\\)$");
				Matcher m_q1 = q1.matcher(before_offending.trim());
				Matcher m_q2 = q2.matcher(before_offending.trim());
				if(m_q1.find() || m_q2.find()){
					msg_detail = "*****lack ',' or '!' before '" + offendingtoken + "' *****";
					msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"
							+ msg_detail + before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n";
				}else{
				msg_detail = "*****did you mean '(asc)' or '(desc)' or 'IF function:( )?' ?*****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"
						+ msg_detail  + before_offending.trim().substring(0, before_offending.trim().lastIndexOf("("))
				+ " >>>>>" + before_offending.trim().substring(before_offending.trim().lastIndexOf("("), before_offending.trim().length()) + "<<<<< "
				+ offendingtoken + after_offending + "\n" ;
				}
			}
		}
		else if(offendingtoken.equals("{")){
			Pattern d = Pattern.compile(".+=.+}");
			Matcher m_d = d.matcher(after_offending.substring(0,after_offending.indexOf("}")+1));
			if(m_d.find()){
				msg_detail = "****decorator should describe : '@" + offendingtoken + after_offending.substring(0, after_offending.indexOf("}")+1) +"'*****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"
						+ msg_detail + before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n" ;
			}else{
				msg_detail = "****lack a connector before '" + offendingtoken +"'*****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"
						+ msg_detail  + before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n";
			}
		}
		else if(offendingtoken.equals("{") || offendingtoken.equals("[") || offendingtoken.equals("(")){
			if(!before_offending.trim().substring(before_offending.trim().length()-1).equals(",")
				|| !before_offending.trim().substring(before_offending.trim().length()-1).equals("!")){
				msg_detail = "****lack a connector before '" + offendingtoken +"'*****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"
						 + msg_detail + before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n" ;
			}else if(before_offending.trim().matches(".*(asc[0-9]*)")
					|| before_offending.trim().matches(".*(desc[0-9]*)")){
				msg_detail = "****did you mistake the place of '(asc)' or '(desc)' ?*****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"
				+ before_offending.trim().substring(0, before_offending.trim().lastIndexOf("(")) + msg_detail
				+ " >>>>>" + before_offending.trim().substring(before_offending.trim().lastIndexOf("("), before_offending.trim().length()) + "<<<<< "
				+ offendingtoken + after_offending + "\n";
			}
		}
		else if(e.getOffendingToken().getText().equals("<EOF>")){
			msg_detail = "*****Input is not enough after " + tokens.getText(e.getStartToken(), e.getOffendingToken()) + "*****";
			msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "\n"+
					 msg_detail +"'\n" + before_offending;
		}
		else if(offendingtoken.equals(".")){
			msg_detail = "*****'.' is not need here. OR did you mistake ',' to '.' ? *****";
			msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "\n" +
					 msg_detail +"'\n" + before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending ;
		}
		else if(offendingtoken.equals("=") || offendingtoken.equals(">=") || offendingtoken.equals("=<")){
			msg_detail = "*****Inequality formula should appear in decorator:@{} or if_then_else.*****";
			msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "\n" + msg_detail  +
				"'\n" + before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n";
		}
		else if((offendingtoken.equals(",") || offendingtoken.equals("!") )&& before_offending.trim().substring(before_offending.trim().length()-1).matches("[0-9]+")){
			if(before_offending.substring(before_offending.lastIndexOf("]") + 1, before_offending.lastIndexOf("]") + 2).equals(",")){
				msg_detail = "****composite iterator wasn't described correctly : '[ ],NUMBER!' or '[ ],NUMBER!NUMBER%' or '[ ],NUMBER%'*****";
			}else if(before_offending.substring(before_offending.lastIndexOf("]") + 1, before_offending.lastIndexOf("]") + 2).equals("!")){
				msg_detail = "****composite iterator wasn't described correctly : '[ ]!NUMBER,' or '[ ]!NUMBER,NUMBER%' or '[ ]!NUMBER%'*****";
			}
			msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText()+ "\n" + msg_detail  +
					"'\n" + before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n";
		}
		else if(offendingtoken.equals("\"")){
			msg_detail = "****STRING LITERAL didn't describe correctly: ' \" string literal \" '*****";
			msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"  + msg_detail
			+ before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n";
		}
		else if(offendingtoken.equals(";")
				|| offendingtoken.equals(":")
				|| offendingtoken.equals("/")
				|| offendingtoken.equals("\\")
				|| offendingtoken.equals("+")
				|| offendingtoken.equals("-")
				|| offendingtoken.equals("~")
				|| offendingtoken.equals("|")
				|| offendingtoken.equals("*")
				){
			msg_detail = "****this input may not be need here.*****";
			msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"  + msg_detail
			+ before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n" ;
		}
		else if(offendingtoken.matches("[^a-zA-Z0-9]+")){
			msg_detail = "****this token should be in string literal.*****";
			msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"  + msg_detail
			+ before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n" ;
		}
		else{
			Pattern p1 = Pattern.compile("\\(asc[0-9]*\\)$");
			Pattern p2 = Pattern.compile("\\(desc[0-9]*\\)$");
			Matcher m_p1 = p1.matcher(before_offending.trim());
			Matcher m_p2 = p2.matcher(before_offending.trim());

			Pattern p3 = Pattern.compile("asc[0-9]*$");
			Pattern p4 = Pattern.compile("desc[0-9]*$");
			Matcher m_p3 = p3.matcher(before_offending.trim());
			Matcher m_p4 = p4.matcher(before_offending.trim());
			if(m_p1.find() || m_p2.find()){
				msg_detail = "****error around (asc) or (desc).*****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"  + msg_detail
				+ before_offending.trim().substring(0, before_offending.trim().lastIndexOf("("))
				+ " >>>>>" + before_offending.trim().substring(before_offending.trim().lastIndexOf("("), before_offending.trim().length()) + "<<<<< "
				+ offendingtoken + after_offending + "\n";
			}
			else if(before_offending.trim().substring(before_offending.trim().length()-1).equals(")")){
				Pattern q1 = Pattern.compile("[a-zA-Z0-9]+\\(.+\\)\\$");
				Matcher m_q1 = q1.matcher(before_offending.trim());
				if(m_q1.find()){
					msg_detail = "*****lack ',' or '!' before '" + offendingtoken + "' *****";
					msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"  + msg_detail
							+ before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n";
				}else{
				msg_detail = "*****did you mean '(asc)' or '(desc)' or 'IF function:( )?' ?*****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"  + msg_detail
				+ before_offending.trim().substring(0, before_offending.trim().lastIndexOf("("))
				+ " >>>>>" + before_offending.trim().substring(before_offending.trim().lastIndexOf("("), before_offending.trim().length()) + "<<<<< "
				+ offendingtoken + after_offending + "\n" ;
				}
			}
			else if(m_p3.find() || m_p4.find()){
				msg_detail = "****did you mean '(asc)' or '(desc)' before '" + offendingtoken + "' *****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"  + msg_detail
						+ before_offending +
						" >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n";
			}else if((offendingtoken.equals(",") || offendingtoken.equals("%")) &&
						(before_offending.trim().substring(before_offending.trim().length()-1).equals(",")
							|| before_offending.trim().substring(before_offending.trim().length()-1).equals("!")
							|| before_offending.trim().substring(before_offending.trim().length()-1).equals("%")
						)
					){
				msg_detail = "*****extra '" + offendingtoken + "' detected.*****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"  + msg_detail
				+ before_offending.trim().substring(0, before_offending.trim().length()-1) +
				" >>>>>" + before_offending.trim().substring(before_offending.trim().length()-1) + "<<<<< " + offendingtoken + after_offending + "\n";
			}
			else if(!before_offending.trim().substring(before_offending.trim().length()-1).equals(",")
					|| !before_offending.trim().substring(before_offending.trim().length()-1).equals("!")){
				msg_detail = "****lack a connector before '" + offendingtoken +"'*****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"  + msg_detail
				+ before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n";
			}else{
			msg_detail = "*****query is wrong around the token*****";
			msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() +
				"'\n" + msg_detail  + before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n";

			}
		}
		//161109 yhac
		Ssedit.sseditInfo("?be is:" + before_offending + "?");
		Ssedit.sseditInfo("?ot is:" + offendingtoken + "?");
		Ssedit.sseditInfo("?ao is:" + after_offending + "?");

		GlobalEnv.addErr(msg);
		recognizer.notifyErrorListeners(e.getOffendingToken(), msg, e);
		GlobalEnv.errorText += msg;
	}

	protected void reportInputMismatch(Parser recognizer, InputMismatchException e){
		TokenStream tokens = recognizer.getInputStream();
		String before_offending = null;
		String offendingtoken = null;
		String after_offending = null;
		String msg_detail = null;
		String msg = null;

		before_offending = tokens.getText().substring(0, e.getOffendingToken().getStartIndex());
		offendingtoken = tokens.getText().substring(e.getOffendingToken().getStartIndex(), e.getOffendingToken().getStartIndex()+ e.getOffendingToken().getText().length());
		after_offending = tokens.getText().substring(e.getOffendingToken().getStartIndex()+ e.getOffendingToken().getText().length());
		if(before_offending.toLowerCase().contains("from")){
			if(before_offending.trim().substring(before_offending.trim().length()-1).equals(";")){
				msg_detail = "****query is end with ';'.*****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n" + msg_detail
				+ before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n";
			}else{
				msg_detail = "****error in FROM clause.*****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n" + msg_detail
				+ before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n" ;
			}
		}
		else if(offendingtoken.toLowerCase().contains("from")){
			String from[] = offendingtoken.toLowerCase().split("from");
			msg_detail = "*****did you mean'" + from[0] +" FROM "+ from[1] +"'*****";
			msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n" + msg_detail
			+ before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n";
		}
		else if(before_offending.trim().substring(before_offending.trim().length()-1).equals("]")){///////////////find error grouper//////////
			msg_detail = "*****did you mean ']!' or '],' ?*****";
			msg = "parse error detected. \nThe OffendingToken is : "+
			"'" + e.getOffendingToken().getText() + "'\n" + msg_detail
					+ before_offending.trim().substring(0, before_offending.trim().length()-1) +
					" >>>>>" + before_offending.trim().substring(before_offending.trim().length()-1) + "<<<<< "
					+ offendingtoken + after_offending + "\n";
		}
		else if(offendingtoken.equals("{")){
			Pattern d = Pattern.compile(".+=.+}");
			Matcher m_d = d.matcher(after_offending);
			if(m_d.find()){
				msg_detail = "****decorator should describe : '@" + offendingtoken + after_offending.substring(0, after_offending.indexOf("}")+1) +"'*****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n" + msg_detail
				+ before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n";
			}
		}
		else if(e.getOffendingToken().getText().equals("{") || e.getOffendingToken().getText().equals("[")){
			msg_detail = "*****',' or '!' is lack before " + "'" + e.getOffendingToken().getText() + "'*****";
			msg = "unexpected token detected.\nThe Offending token is : " + "'" + e.getOffendingToken().getText() + "'\n" + msg_detail
					+ before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n";
		}
		else if(offendingtoken.toLowerCase().equals("where") && !before_offending.toLowerCase().contains("from")){
			msg_detail = "*****FROM clause is need before WHERE clause.";
			msg = "parse error detected.\nThe Offending token is : " + "'" + e.getOffendingToken().getText() + "'\n"+ msg_detail
					+ before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n";
		}
		else if(before_offending.trim().substring(before_offending.trim().length()-4).toLowerCase().equals("from")
				&&
				(
				before_offending.trim().substring(0, before_offending.trim().length()-4).trim().substring(before_offending.trim().substring(0, before_offending.trim().length()-4).trim().length()-1).equals(",")
				||
				before_offending.trim().substring(0, before_offending.trim().length()-4).trim().substring(before_offending.trim().substring(0, before_offending.trim().length()-4).trim().length()-1).equals("!")
				||
				before_offending.trim().substring(0, before_offending.trim().length()-4).trim().substring(before_offending.trim().substring(0, before_offending.trim().length()-4).trim().length()-1).equals("%")
				)
				){
			msg_detail = "*****extra '"+ before_offending.trim().substring(0, before_offending.trim().length()-4).trim().substring(before_offending.trim().substring(0, before_offending.trim().length()-4).trim().length()-1) +"'before FROM" ;
			msg = "parse error detected.\nThe Offending token is : " + "'" + e.getOffendingToken().getText() + "'\n" + msg_detail
					+ before_offending.trim().substring(0, before_offending.trim().length()-4).trim().substring(0, before_offending.trim().substring(0, before_offending.trim().length()-4).trim().length()-1) +
					" >>>>>" + before_offending.trim().substring(0, before_offending.trim().length()-4).trim().substring(before_offending.trim().substring(0, before_offending.trim().length()-4).trim().length()-1) + "<<<<< " +
					before_offending.trim().substring(before_offending.trim().length()-4) + " " + offendingtoken + " " + after_offending +
					"\n";
		}
		else if(offendingtoken.equals("}")){
			if(!before_offending.contains("{")){
				msg_detail = "*****dosen't exist the the pair of '}' : did you mean ']' or ')' ?*****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n" + msg_detail
						+ before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n";
			}else{
				msg_detail = "*****extra parenthis detected : '}'. did you mean ']' or ')' ?*****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n" + msg_detail
						+ before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n" ;
			}
		}
		else if(offendingtoken.equals("]")){
			if(!before_offending.contains("[")){
				msg_detail = "*****dosen't exist the the pair of ']' : did you mean '}' or ')' ?*****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"  + msg_detail
						+ before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n";
			}else{
				msg_detail = "*****extra bracket detected : ']'. did you mean '}' or ')' ?*****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"  + msg_detail
						+ before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n" ;
			}
		}
		else if(offendingtoken.equals(")")){
			if(!before_offending.contains("(")){
				msg_detail = "*****dosen't exist the the pair of '}' : did you mean ']' or '}' ?*****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"  + msg_detail
						+ before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n";
			}else{
				msg_detail = "*****extra brace detected : ')'. did you mean ']' or '}' ?*****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"  + msg_detail
						+ before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n" ;
			}
		}
		else{
			Pattern p = Pattern.compile("\\(asc[0-9]*\\)$");
			Pattern q = Pattern.compile("\\(desc[0-9]*\\)$");
			Matcher m_p = p.matcher(before_offending.trim());
			Matcher m_q = q.matcher(before_offending.trim());
			if(m_p.find() || m_q.find()){
				msg_detail = "****error around (asc) or (desc).*****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"  + msg_detail
				+ before_offending.trim().substring(0, before_offending.trim().lastIndexOf("("))
				+ " >>>>>" + before_offending.trim().substring(before_offending.trim().lastIndexOf("("), before_offending.trim().length()) + "<<<<< "
				+ offendingtoken + after_offending + "\n";
			}
			else if(before_offending.trim().substring(before_offending.trim().length()-1).equals(")")){
				Pattern q1 = Pattern.compile("[a-zA-Z0-9]+\\(.+\\)$");
				Matcher m_q1 = q1.matcher(before_offending.trim());
				if(m_q1.find()){
					msg_detail = "*****lack ',' or '!' before '" + offendingtoken + "' *****";
					msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"  + msg_detail
							+ before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n";
				}else{
				msg_detail = "*****did you mean '(asc)' or '(desc)' or 'IF function:( )?' ?*****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"  + msg_detail
				+ before_offending.trim().substring(0, before_offending.trim().lastIndexOf("("))
				+ " >>>>>" + before_offending.trim().substring(before_offending.trim().lastIndexOf("("), before_offending.trim().length()) + "<<<<< "
				+ offendingtoken + after_offending + "\n" ;
				}
			}
			else if(!before_offending.trim().substring(before_offending.trim().length()-1).equals(",")
					|| !before_offending.trim().substring(before_offending.trim().length()-1).equals("!")){
				msg_detail = "****lack a connector before '" + offendingtoken +"'*****";
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() + "'\n"  + msg_detail
				+ before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n";
			}else{
			msg_detail = "*****query is wrong around the token*****";
			msg = "parse error detected. \nThe OffendingToken is : "+ "'" + e.getOffendingToken().getText() +
				"'\n" + msg_detail + before_offending + " >>>>>" + offendingtoken + "<<<<< " + after_offending + "\n";

			}
		}
		//161109 yhac
		Ssedit.sseditInfo("?be is:" + before_offending + "?");
		Ssedit.sseditInfo("?ot is:" + offendingtoken + "?");
		Ssedit.sseditInfo("?ao is:" + after_offending + "?");


		GlobalEnv.addErr(msg);
		recognizer.notifyErrorListeners(e.getOffendingToken(), msg, e);
		GlobalEnv.errorText += msg;

	}

//	protected void reportFailedPredicate(Parser recognizer, FailedPredicateException e){
//		String ruleName = recognizer.getRuleNames()[recognizer._ctx.getRuleIndex()];
//		String msg = "rule "+ruleName+" "+e.getMessage();
//		recognizer.notifyErrorListeners(e.getOffendingToken(), msg, e);
//	}

	protected void reportUnwantedToken(Parser recognizer) {
		if (inErrorRecoveryMode(recognizer)) {
			return;
		}

		beginErrorCondition(recognizer);
		TokenStream tokens = recognizer.getInputStream();
		Token t = recognizer.getCurrentToken();
		int index = t.getStartIndex();
		String before_offending = tokens.getText().substring(0, index);
		String after_offending = tokens.getText().substring(index+t.getText().length());

		String tokenName = getTokenErrorDisplay(t);

		String msg = null;
		IntervalSet expecting = getExpectedTokens(recognizer);
		if(t.getText().equals(":")){
			String msg_detail = "did you mistake ';' to ':' ?";
			msg = "unexpected input is detected : " + "'" + tokenName + "\n*****"+msg_detail+"*****"
			+"'\n" + before_offending + " >>>>>" + t.getText() + "<<<<< " + after_offending;
		}
		if(before_offending.toLowerCase().contains("from")){
			if(before_offending.trim().substring(before_offending.trim().length()-1).equals(";")){
				msg = "parse error detected. \nThe OffendingToken is : "+ "'" + tokenName + "'\n" + "****query is end with ';'.'\n*****"
				+ before_offending + " >>>>>" + t.getText() + "<<<<< " + after_offending + "\n" ;
			}
		}else{
			msg = "unexpected input is detected : " + "'" + tokenName+ "\n*****This input may not be necessary. OR This input should not be writen here*****"
		+"'\n" + before_offending + " >>>>>" + t.getText() + "<<<<< " + after_offending
				;
		}
		//161109 yhac
		Ssedit.sseditInfo("?be is:" + before_offending + "?");
//		Ssedit.sseditInfo("?ot is:" + offendingtoken);
		Ssedit.sseditInfo("?ao is:" + after_offending + "?");

		GlobalEnv.addErr(msg);
		recognizer.notifyErrorListeners(t, msg, null);
		GlobalEnv.errorText += msg;

	}

	protected String escapeWSAndQuote(String s) {
//		if ( s==null ) return s;
		s = s.replace("\n","\\n");
		s = s.replace("\r","\\r");
		s = s.replace("\t","\\t");
		return s;
	}
}