lexer grammar ScriptLexer;

// Ignore whitespace and line comment
WS: [ \t\n]+ -> skip;
LINE_COMMENT: '//' ~[\r\n]* -> skip;
MULTILINE_COMMENT: '/*' .*? '*/' -> skip;

// Separators
LPAREN: '(';
RPAREN: ')';
LBRACKET: '[';
RBRACKET: ']';
LCURLY: '{';
RCURLY: '}';
SEMICOLON: ';';
COLON: ':';
COMMA: ',';
PERIOD: '.';
PIPE: '|';
QUESTION: '?';

// Assignment
ASSIGN: '=';
INCREMENT: '++';
DECREMENT: '--';
ADD_ASSIGN: '+=';
SUB_ASSIGN: '-=';
MUL_ASSIGN: '*=';
DIV_ASSIGN: '/=';
MOD_ASSIGN: '%=';
AND_ASSIGN: '&=';
OR_ASSIGN: '|=';

// Function
ARROW: '->';
DEF: '::';
EXTENSION: '$';

// Binary Operators
EQUAL: '==';
NOT_EQUAL: '!=';
GT: '>';
LT: '<';
GEQ: '>=';
LEQ: '<=';
RAISE: '^';
PLUS: '+';
MINUS: '-';
TIMES: '*';
DIVIDE: '/';
MOD: '%';
AND: '&&';
OR: '||';

// Unary Operators
NOT: '!';
SIZE: '#|';
// negative handled as minus

// Keywords
IN: 'in';
FINAL: 'final' | '~';
BOOL: 'bool';
FLOAT: 'float';
INT: 'int';
CHAR: 'char';
COLOR: 'color';
IMAGE: 'image';
STRING: 'string';
RETURN: 'return';
DO: 'do';
WHILE: 'while';
FOR: 'for';
IF: 'if';
ELSE: 'else';
TRUE: 'true';
FALSE: 'false';
NEW: 'new';

// numbers
fragment DIGIT: '0'..'9';
fragment HEX_DIGIT: DIGIT | 'a'..'f';
fragment CHANNEL: HEX_DIGIT HEX_DIGIT;
FLOAT_LIT: (DIGIT+ '.' DIGIT+) | (DIGIT+ 'f');
DEC_LIT: DIGIT+;
HEX_LIT: '0x' HEX_DIGIT+;
COL_LIT: '#' CHANNEL CHANNEL CHANNEL CHANNEL?;

CHAR_QUOTE: '\'';
STR_QUOTE: '"';

fragment RESTRICTED_ASCII: ~('\\' | '\'' | '"');

STRING_LIT: '"' (RESTRICTED_ASCII | ESC_CHAR | CHAR_QUOTE)* '"';
fragment CHARACTER: RESTRICTED_ASCII | ESC_CHAR;
CHAR_LIT: '\'' CHARACTER '\'';

ESC_CHAR: '\\' ('0' | 'b' | 't' | 'n' | 'f' | 'r' | '"' | '\'' | '\\');

// Identifier
IDENTIFIER: [_A-Za-z] [_A-Za-z0-9]*;
SUB_IDENT: PERIOD IDENTIFIER;
