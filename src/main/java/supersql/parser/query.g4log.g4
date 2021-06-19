
grammar query ;

@header{
package supersql.parser;

import java.util.*;
import java.io.*;
}

////////////////////////////////////////////////////Parse rules/////////////////////////////////////////////
query :
    
    media
    
    root 
    
    from_where?

    ;

root  : 
    (
      operand 
      |exp
    )
      (DECORATOR )?
    ;

media : K_GENERATE IDENTIFIER ;

operand :
  (
  (sorting)?attribute
  | (sorting)?join_string
  | function
  | sqlfunc
  | OPEN_BRACE exp CLOSE_BRACE
  | grouper
  | composite_iterator
  | if_then_else
  | NUMERIC_LITERAL
  | (sorting)?aggregate
  | arithmetics
  | sl
  )(DECORATOR)?
;

attribute :
  (table_alias '.')? column_name
  ;

join_string :
  (
    (attribute | NUMERIC_LITERAL | arithmetics | sl)
    ('||'
    (attribute | NUMERIC_LITERAL | arithmetics | sl)
    )+
  )
  ;

grouper :
    OPEN_BRACKET
    exp
    CLOSE_BRACKET
    C1 
  | 
    OPEN_BRACKET
    exp
    CLOSE_BRACKET
    C2
  | 
    OPEN_BRACKET
    exp
    CLOSE_BRACKET
    C3
  ;
  /**grouper is [exp], | ! | % */
  
  

 /* 
composite_iterator  :
  (
    OPEN_BRACKET 
    exp
    CLOSE_BRACKET
    C1
    (
    NUMERIC_LITERAL //NUMERIC_LITERAL is number
      (
        (C1 | C3)
        | 
        (C2 (NUMERIC_LITERAL C3)?) //()? There is or Nothing. Either ok.
      )
    )//[],2! or [],2!3% or [],2% or [],2,
  )
  | 
  (
    OPEN_BRACKET 
    exp
    CLOSE_BRACKET 
    C2
    (
    NUMERIC_LITERAL  
      (
        (C2 | C3)
        |
        (C1 (NUMERIC_LITERAL C3)?)
      )
    )
  )//[]!2, or []!2% or []!2,3% or []!2!
  ;
*/

composite_iterator  :
  OPEN_BRACKET 
  exp
  CLOSE_BRACKET
  C1
  (
  NUMERIC_LITERAL C2 (NUMERIC_LITERAL C3)?
  | 
  NUMERIC_LITERAL C3 (NUMERIC_LITERAL C2)?
  )//[],2! or [],2!3% or [],2% or [],2%3!
  | 
  OPEN_BRACKET 
  exp
  CLOSE_BRACKET 
  C2
  (
  NUMERIC_LITERAL C1 (NUMERIC_LITERAL C3)? 
  | 
  NUMERIC_LITERAL C3 (NUMERIC_LITERAL C1)?
  )//[]!2, or []!2,3! or []!2% or []!2%3,
  | 
  OPEN_BRACKET 
  exp
  CLOSE_BRACKET 
  C3
  (
  NUMERIC_LITERAL C1 (NUMERIC_LITERAL C2)? 
  | 
  NUMERIC_LITERAL C2 (NUMERIC_LITERAL C1)?
  )//[]%2, or []%2,3! or []%2! or []%2!3,
  ;


exp : 
  d_exp
  ;

d_exp :
  v_exp
  (C3 (v_exp | operand) )*
    ;
    
v_exp :
  h_exp
  (C2 (h_exp | operand))*
    ;
    
h_exp :
  (operand | n_exp)
  (C1 (operand | n_exp) )*
  ;

n_exp :
  operand C0 operand
    ;

sorting :
    OPEN_PARENTHESE
    K_ASC
    CLOSE_PARENTHESE
    | 
    OPEN_PARENTHESE
    K_DESC
    CLOSE_PARENTHESE
    ;

//func(~)
function  :
  (
    ('$')?function_name
    OPEN_PARENTHESE
    (
      (
        operand
        | exp
        //| expr 
      )
      ((',')
        (
          operand
          | exp
          //| expr
        )
      )* 
    )
    CLOSE_PARENTHESE
  )
  ;

//for sql function
sqlfunc  :
  ('&' function_name
    OPEN_PARENTHESE
    (
      (
      operand
      | exp
      //| expr 
      ) 
      (',' 
        (
        operand
        | exp
        //| expr
        )
      )* 
    )*
    CLOSE_PARENTHESE
  )
  ;

aggregate :
    ag_function_name 
    OPEN_BRACKET
    attribute
    CLOSE_BRACKET
    ;

//if then else
if_then_else  : 
    (
      (
      K_IF
      OPEN_PARENTHESE
      expr 
      CLOSE_PARENTHESE
      K_THEN
      OPEN_PARENTHESE
      exp
//      operand 
//      (',' operand)* 
      CLOSE_PARENTHESE
      K_ELSE 
      OPEN_PARENTHESE
      exp
//      operand 
//      (',' operand)* 
      CLOSE_PARENTHESE
      )
      |
      (OPEN_PARENTHESE
      expr 
      CLOSE_PARENTHESE '?'
      exp':'exp
//      ( operand  (',' operand)* ) 
//      ':' ( operand (',' operand)* )
      )
    )
    ;

arithmetics :
  OPEN_PARENTHESE 
    arithmetics ( '*' | '/' | '%' | '+' | '-' ) arithmetics
  CLOSE_PARENTHESE
  | arithmetics ( '*' | '/' | '%' | '+' | '-' ) arithmetics
  | arith
  ;

arith :
  attribute
  | NUMERIC_LITERAL
  ;

//////////////////////////////////////for from ////////////////////////////////////////////
from_where
   :
    ( sql_stmt_list | error )
   ;
   
error
  : UNEXPECTED_CHAR 
  { 
  
  throw new RuntimeException("UNEXPECTED_CHAR=" + $UNEXPECTED_CHAR.text); 
  }
  ;

sql_stmt_list
   : 
    sql_stmt ( ';'+ sql_stmt )* ';'*
   ;

sql_stmt
  :  
  ( factored_select_stmt
  | select_stmt )
  
  ;


factored_select_stmt
   : 
   ( K_WITH K_RECURSIVE? common_table_expression ( ',' common_table_expression )* )?
     select_core ( compound_operator select_core )*
     ( K_ORDER K_BY ordering_term ( ',' ordering_term )* )?
     ( K_LIMIT expr ( ( K_OFFSET | ',' ) expr )? )?
    
   ;

select_core
  :
  (K_SELECT ( K_DISTINCT | K_ALL )? result_column ( ',' result_column )*)?
  ( K_FROM ( table_or_subquery ( ',' table_or_subquery )* | join_clause ) )
  where_clause ?
  ;
  
where_clause  :
(
  ( K_WHERE expr )
  ( K_GROUP K_BY expr ( ',' expr )* ( K_HAVING expr )? )?
  | K_VALUES '(' expr ( ',' expr )* ')' ( ',' '(' expr ( ',' expr )* ')' )*
)
|
(
  ( K_WHERE expr )?
  ( K_GROUP K_BY expr ( ',' expr )* ( K_HAVING expr )? )
  | K_VALUES '(' expr ( ',' expr )* ')' ( ',' '(' expr ( ',' expr )* ')' )*
)
  ;
  
result_column
  : 
  '*'
  | table_name '.' '*'
  | expr ( K_AS? column_alias )?
  
  ;


table_or_subquery
  : 
  ( database_name '.' )? table_name ( K_AS? table_alias )?
  ( K_INDEXED K_BY index_name
  | K_NOT K_INDEXED )?
  | '(' ( table_or_subquery ( ',' table_or_subquery )*
     | join_clause )
  ')' ( K_AS? table_alias )?
  | '(' select_stmt ')' ( K_AS? table_alias )?
  
  ;

keyword
  : K_ABORT
  | K_ALL
  | K_AND
  | K_AS
  | K_ASC
  | K_BETWEEN
  | K_BY
  | K_CASE
  | K_CAST
  | K_COLLATE
  | K_CROSS
  | K_CURRENT_DATE
  | K_CURRENT_TIME
  | K_CURRENT_TIMESTAMP
  | K_DESC
  | K_DISTINCT
  | K_ELSE
  | K_END
  | K_ESCAPE
  | K_EXCEPT
  | K_EXISTS
  | K_FAIL 
  | K_FROM
  | K_FULL
  | K_GLOB
  | K_GROUP
  | K_HAVING
  | K_IF
  | K_IGNORE
  | K_IN
  | K_INDEXED
  | K_INNER
  | K_INTERSECT
  | K_IS
  | K_ISNULL
  | K_JOIN
  | K_LEFT
  | K_LIKE
  | K_LIMIT
  | K_MATCH
  | K_NATURAL
  | K_NO
  | K_NOT
  | K_NOTNULL
  | K_NULL
  | K_OFFSET
  | K_ON
  | K_OR
  | K_ORDER
  | K_OUTER
  | K_RAISE
  | K_RECURSIVE
  | K_REGEXP
  | K_RIGHT
  | K_ROLLBACK
  | K_SELECT
  | K_THEN
  | K_UNION
  | K_USING
  | K_VALUES
  | K_WHEN
  | K_WHERE
  | K_WITH
  | K_GENERATE
  | K_MAX
  | K_MIN
  | K_AVG
  | K_SUM
  | K_COUNT
  ;

select_stmt
  : ( K_WITH K_RECURSIVE? common_table_expression ( ',' common_table_expression )* )?
  select_or_values ( compound_operator select_or_values )*
  ( K_ORDER K_BY ordering_term ( ',' ordering_term )* )?
  ( K_LIMIT expr ( ( K_OFFSET | ',' ) expr )? )?
  ;

select_or_values
  : (K_SELECT ( K_DISTINCT | K_ALL )? result_column ( ',' result_column )*)?
  ( K_FROM ( table_or_subquery ( ',' table_or_subquery )* | join_clause ) )
  where_clause ?
  ;

compound_operator
  : K_UNION
  | K_UNION K_ALL
  | K_INTERSECT
  | K_EXCEPT
  ;

join_clause
  : table_or_subquery ( join_operator (join_clause | table_or_subquery) join_constraint )*
  ;

join_operator
  : ','
  | K_NATURAL? ( K_LEFT K_OUTER? | K_RIGHT K_OUTER? | K_FULL K_OUTER?  | K_INNER | K_CROSS )? K_JOIN
  ;

join_constraint
  : ( K_ON expr
  | K_USING '(' column_name ( ',' column_name )* ')' )?
  ;

common_table_expression
  : table_name ( '(' column_name ( ',' column_name )* ')' )? K_AS '(' select_stmt ')'
  ;

ordering_term
  : expr ( K_COLLATE collation_name )? ( K_ASC | K_DESC )?
  ;

expr
  : operand
  | BIND_PARAMETER
  | ( ( database_name '.' )? table_alias '.' )? column_name
  | unary_operator expr
  | expr '||' expr
  | expr ( '*' | '/' | '%' ) expr
  | expr ( '+' | '-' ) expr
  | expr ( '<<' | '>>' | '&' | '|' ) expr
  | expr ( '<' | '<=' | '>' | '>=' ) expr
  | expr ( '=' | '==' | '!=' | '<>' | K_IS | K_IS K_NOT | K_IN | K_LIKE | K_GLOB | K_MATCH | K_REGEXP ) expr
  | expr K_AND  expr
  | expr K_OR  expr
  | '(' expr ')'
  | K_CAST '(' expr K_AS type_name ')'
  | expr K_COLLATE collation_name
  | expr K_NOT? ( K_LIKE | K_GLOB | K_REGEXP | K_MATCH ) expr ( K_ESCAPE expr )?
  | expr ( K_ISNULL | K_NOTNULL | K_NOT K_NULL )
  | expr K_IS K_NOT? expr
  | expr K_NOT? K_BETWEEN expr K_AND expr
  | expr K_NOT? K_IN ( '(' ( select_stmt
                        | expr ( ',' expr )*
                        )? 
                    ')'
                  | ( database_name '.' )? table_name )
  | ( ( K_NOT )? K_EXISTS )? '(' select_stmt ')'
  | K_CASE expr? ( K_WHEN expr K_THEN expr )+ ( K_ELSE expr )? K_END
  | raise_function
  ;

literal_value
  : NUMERIC_LITERAL
  | STRING_LITERAL
  | BLOB_LITERAL
  | K_NULL
  | K_CURRENT_TIME
  | K_CURRENT_DATE
  | K_CURRENT_TIMESTAMP
  ;

unary_operator
  : '-'
  | '+'
  | '~'
  | K_NOT
  ;

name
  : any_name
  ;

type_name
  : name+ ( '(' signed_number ')'
       | '(' signed_number ',' signed_number ')' )?
  ;

function_name
  : 
  any_name
  ;

ag_function_name
  :
  ag_keyword
  ;

ag_keyword
  :
  K_MAX
  |K_MIN
  |K_SUM
  |K_AVG
  |K_COUNT
  ;

collation_name 
  : any_name
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
  : keyword
  | IDENTIFIER 
//  | STRING_LITERAL 
//  | '(' any_name ')'
  ;

sl : STRING_LITERAL;

signed_number
  : ( '+' | '-' )? NUMERIC_LITERAL
  ;

raise_function
  : K_RAISE '(' ( K_IGNORE 
             | ( K_ROLLBACK | K_ABORT | K_FAIL ) ',' error_message )
         ')'
  ;

error_message
  : STRING_LITERAL
  ;


//////////////////////////////////////////Lexer rules/////////////////////////////////////////////////////
K_ABORT : A B O R T;    
K_ADD : A D D;      
K_ALL : A L L;
K_AND : A N D;        
K_AS : A S;       
K_ASC : A S C DIGIT*;
K_BETWEEN : B E T W E E N;  
K_BY : B Y;       
K_CASE : C A S E;
K_CAST : C A S T;     
K_COLLATE : C O L L A T E;
K_CROSS : C R O S S;    
K_CURRENT_DATE : C U R R E N T '_' D A T E;
K_CURRENT_TIME : C U R R E N T '_' T I M E;     
K_CURRENT_TIMESTAMP : C U R R E N T '_' T I M E S T A M P;
K_DESC : D E S C DIGIT*;  
K_DISTINCT : D I S T I N C T;
K_ELSE : E L S E;     
K_END : E N D;      
K_ESCAPE : E S C A P E; 
K_EXCEPT : E X C E P T;   
K_EXISTS : E X I S T S; 
K_FAIL : F A I L;
K_FULL  : F U L L ;   
K_FROM : F R O M;   
K_GLOB : G L O B;
K_GROUP : G R O U P;    
K_HAVING : H A V I N G; 
K_IF : I F;
K_IGNORE : I G N O R E;   
K_IN : I N;       
K_INDEXED : I N D E X E D;
K_INNER : I N N E R;    
K_INTERSECT : I N T E R S E C T;
K_IS : I S;         
K_ISNULL : I S N U L L; 
K_JOIN : J O I N;
K_LEFT : L E F T;     
K_LIKE : L I K E;   
K_LIMIT : L I M I T;
K_MATCH : M A T C H;    
K_NATURAL : N A T U R A L;
K_NO : N O;         
K_NOT : N O T;      
K_NOTNULL : N O T N U L L;
K_NULL : N U L L;     
K_OFFSET : O F F S E T; K_ON : O N;
K_OR : O R;         
K_ORDER : O R D E R;  
K_OUTER : O U T E R;
K_RAISE : R A I S E;    
K_RECURSIVE : R E C U R S I V E;
K_REGEXP : R E G E X P; 
K_RIGHT : R I G H T ; 
K_ROLLBACK : R O L L B A C K;
K_SELECT : S E L E C T;   
K_THEN : T H E N;   
K_UNION : U N I O N;
K_USING : U S I N G;    
K_VALUES : V A L U E S;
K_WHEN : W H E N;     
K_WHERE : W H E R E;  
K_WITH  : W I T H;
K_GENERATE  : G E N E R A T E ;         
K_MAX : M A X ;
K_MIN : M I N ;   
K_AVG : A V G ; 
K_COUNT : C O U N T ;     
K_SUM : S U M ;

//MEDIA : K_GENERATE (WS)+ [a-zA-Z_-]+[0-9]* ;

//FUNC1 : '$'?[a-zA-Z]+[0-9]*(WS)*'(' ;
//FUNC2 : [a-zA-Z]+(WS)*'[' ;

C0  : '?' ;
C1  : ',' ;
C2  : '!' ;
C3  : '%' ;
DOT : '.' ;
OPEN_PARENTHESE : '(' ;
CLOSE_PARENTHESE  : ')' ;
OPEN_BRACKET  : '[' ;
CLOSE_BRACKET : ']' ;
OPEN_BRACE  : '{' ;
CLOSE_BRACE : '}' ;
SEMICOLON : ';' ;

DECORATOR :
  '@'(WS)*'{'
    (
      (WS)*[a-zA-Z_.-]+(WS)*[0-9]*
      | 
      (WS)*[a-zA-Z_0-9.-]+(WS)*'='
      (WS)*(
        '#'?[a-zA-Z_0-9.-]+ 
        | [0-9]+('%')? 
        | STRING_LITERAL 
        | (('~' | '.' | [a-zA-Z_0-9]+ | ':')+((WS)*('/' | '//')[a-zA-Z_0-9.]+)*)
      )
    )
      ((WS)*','
        (
          WS)*[a-zA-Z_.-]+(WS)*[0-9]* 
          | (WS)*[a-zA-Z_0-9.-]+(WS)*'='
          (WS)*(
            '#'?[a-zA-Z_0-9.-]+ 
            | [0-9]+('%')? 
            | STRING_LITERAL 
            | (('~' | '.' | [a-zA-Z_0-9]+)((WS)*'/'[a-zA-Z_0-9.]+)*)
          )
      )*(WS)*
  '}' 
      ;

NUMERIC_LITERAL
  : DIGIT+ ( '.' DIGIT* )? ( E [-+]? DIGIT+ )?
  | '.' DIGIT+ ( E [-+]? DIGIT+ )?
  ;

BLOB_LITERAL
  : X STRING_LITERAL
  ;

BIND_PARAMETER
  : '?' DIGIT*
  | [:@$] IDENTIFIER
  ;

IDENTIFIER
  : 
//  '"' (~'"' | '""')* '"'
//  | '`' (~'`' | '``')* '`'
//  | 
  [a-zA-Z_0-9]* [a-zA-Z_] [a-zA-Z_0-9]* // TODO check: needs more chars in set
  ;

STRING_LITERAL  : '\"' ( ~'\"')* '\"'  | '\'' (~'\'')* '\'' ;

MULTI_LINE_COMMENT  :
  '/*' .*? ( '*/' | EOF ) -> channel(HIDDEN)  ; 
SINGLE_LINE_COMMENT :
  '--' ~[\r\n]* -> channel(HIDDEN)  ;
WS  : [ \t\r\n　]+ -> channel(HIDDEN) ;

UNEXPECTED_CHAR
  : .
  ;

fragment DIGIT : [0-9];
fragment A : [aA];  fragment B : [bB];
fragment C : [cC];  fragment D : [dD];
fragment E : [eE];  fragment F : [fF];
fragment G : [gG];  fragment H : [hH];
fragment I : [iI];  fragment J : [jJ];
fragment K : [kK];  fragment L : [lL];
fragment M : [mM];  fragment N : [nN];
fragment O : [oO];  fragment P : [pP];
fragment Q : [qQ];  fragment R : [rR];
fragment S : [sS];  fragment T : [tT];
fragment U : [uU];  fragment V : [vV];
fragment W : [wW];  fragment X : [xX];
fragment Y : [yY];  fragment Z : [zZ];