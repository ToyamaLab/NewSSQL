grammar	prefix;

@header{
package supersql.parser;
}

prefix	:	
fix (',' fix)*
;
fix :
foreach | function | expr ;

operand : (table_alias '.')? column_name ;

exdef	:	'#' K_IMPORT IDENTIFIER
			| '#' K_DEFINE	function_name any_name OPEN_BRACE DEF CLOSE_BRACE
		;
		
foreach :
( 
  (K_FOREACH | K_FOREACH1)
    (operand (',' operand)* 
    | OPEN_PARENTHESE operand (',' operand)* CLOSE_PARENTHESE 
    )
)
|
(
  K_PARAMETER
    (operand (',' operand)* 
    | OPEN_PARENTHESE operand (',' operand)* CLOSE_PARENTHESE 
    )
)
;

function	:
		function_name
			OPEN_PARENTHESE
			expr 
			(',' expr )* 
			CLOSE_PARENTHESE
		;

expr	:	STRING_LITERAL | ((table_alias '.')? column_name)	
				(',' STRING_LITERAL | ((table_alias '.')? column_name))*
		;

function_name
  : 
  any_name
  ;

database_name
  : any_name
  ;
  
table_name 
  : any_name
  ;

column_alias
  : IDENTIFIER
  | STRING_LITERAL
  ;
  
column_name 
  :
  any_name
  ;
  
table_alias 
  : 
  any_name
  ;

index_name 
  : 
  any_name
  ;

any_name
  : IDENTIFIER 
  | STRING_LITERAL
  | '(' any_name ')'
  ;
  
DEF	:	'{' .+? '}'
	;

K_FOREACH	:	F O R E A C H	;
K_FOREACH1 : K_FOREACH [0-9]+;
K_PARAMETER : P A R A M E T E R ;
K_IMPORT	:	I M P O R T	;
K_DEFINE	:	D E F I N E	;

OPEN_PARENTHESE	:	'('	;
CLOSE_PARENTHESE	:	')'	;
OPEN_BRACKET	:	'['	;
CLOSE_BRACKET	:	']'	;
OPEN_BRACE	:	'{'	;
CLOSE_BRACE	:	'}'	;

IDENTIFIER
  : 
//  '"' (~'"' | '""')* '"'
//  | '`' (~'`' | '``')* '`'
//  | 
  [a-zA-Z_0-9]* [a-zA-Z_] [a-zA-Z_0-9]* // TODO check: needs more chars in set
  ;

STRING_LITERAL  : '\"' ( ~'"')* '\"'  | '\'' (~'\'')* '\'' ;
MULTI_LINE_COMMENT	:
	'/*' .*? ( '*/' | EOF ) -> channel(HIDDEN)	; 
SINGLE_LINE_COMMENT	:
	'--' ~[\r\n]* -> channel(HIDDEN)	;
  
  
WS	:	[ \t\r\n]+ -> skip ;

UNEXPECTED_CHAR
  : .
  ;

fragment DIGIT : [0-9];

fragment A : [aA];
fragment B : [bB];
fragment C : [cC];
fragment D : [dD];
fragment E : [eE];
fragment F : [fF];
fragment G : [gG];
fragment H : [hH];
fragment I : [iI];
fragment J : [jJ];
fragment K : [kK];
fragment L : [lL];
fragment M : [mM];
fragment N : [nN];
fragment O : [oO];
fragment P : [pP];
fragment Q : [qQ];
fragment R : [rR];
fragment S : [sS];
fragment T : [tT];
fragment U : [uU];
fragment V : [vV];
fragment W : [wW];
fragment X : [xX];
fragment Y : [yY];
fragment Z : [zZ];
