grammar Formula;

@header {
    package formula;
}

@members {
    // This method makes the lexer or parser stop running if it encounters
    // invalid input and throw a RuntimeException.
    public void reportErrorsAsExceptions() {
        addErrorListener(new ExceptionThrowingErrorListener());
    }

    private static class ExceptionThrowingErrorListener extends BaseErrorListener {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer,
                Object offendingSymbol, int line, int charPositionInLine,
                String msg, RecognitionException e) {
            throw new RuntimeException(msg);
        }
    }
}


LITERAL : .+;
WHITESPACE : [ \t\r\n]+ -> skip;

program : statement*;
statement : create | exit | insert | load | print | select | store;

create : 'create' 'table' name '(' columns ')' ';';
exit : 'quit' ';' | 'exit' ';';
insert : 'insert' 'into' table 'values' rows ';';
load : 'load' name ';';
print : 'print' table ';';
select : 'select' columns 'from' tables 'where' condition ';';
store : 'store' table ';';

name : '[A-Z][a-z]'+ '[A-Z][a-z][0-9]_'*;
columns : (column ',')* column;
rows : (row ',')* row;
literal : '[^\n ,;]'+;
column : name;
table : name;
tables : table | table ',' table;
condition : bool ('and' bool)* ('or' bool)* | ;

row : '(' (literal ',')* literal ')';
bool : column relation column | column relation literal;
relation : '>' | '<' | '=' | '!=' | '>=' | '<=';