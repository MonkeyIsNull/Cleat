package org.cleat;

/**
 *  org.cleat.LispLexer for Lisp
 * 
 */
public class LispLexer extends Lexer
{

    public static int LPAREN = 2;
    public static int RPAREN = 3;   
// screw the back tick, man. Look at [ and ] for literals
//    public static int BACKTICK = 4;
    public static int LBRACK = 4;
    public static int RBRACK = 5;
    public static int SYMBOL = 6;
    public static int STRING = 7;    
    public static int DQUOTE = 8;
    public static int NUM = 10;
    
    public static String[] tokenNames =
            { "n/a", "<EOF>", "SYMBOL", "LPAREN", "RPAREN", "NUM", "RBRACK", "LBRACK", "STRING", "DQUOTE"};
    
    public LispLexer( String input )
    {
        super( input );
    }

    void whiteSpace()
    {
        while ( c == ' ' ||
                c == '\t' ||
                c == '\n' ||
                c == '\r'
                )
        {
            consume();
        }
    }

    private boolean isLETTER()
    {
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
    }

    private boolean isNUMBER()
    {
        return c >= '0' && c <= '9';
    }
    
    private boolean isDQUOTE()
    {
        if(c == '"')
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    private Token STRING()
    {
        StringBuilder buf = new StringBuilder();
        do
        {
            buf.append( c );
            consume( );
        }
        while ( !isDQUOTE() );
        return new Token( STRING, buf.toString() ); 
    }
    
    private Token SYMBOL()
    {
        StringBuilder buf = new StringBuilder();
        do
        {
            buf.append( c );
            consume( );
        }
        while ( isLETTER() || isNUMBER() || isDQUOTE() );
        return new Token( SYMBOL, buf.toString() );
    }
    
    
    private Token NUM()
    {
        StringBuilder buf = new StringBuilder();
        do
        {
            buf.append( c );
            consume( );
        }
        while ( isNUMBER() );
        return new Token( NUM, buf.toString() );  
    }
    
    private Token DQUOTE()
    {
        StringBuilder buf = new StringBuilder();
        do
        {
            buf.append( c );
            consume( );
        }
        while ( isDQUOTE() );
        return new Token( DQUOTE, buf.toString() );  
    }

    @Override
    public Token nextToken()
    {
        while ( c != EOF )
        {
            switch ( c )
            {
                case ' ':
                case '\t':
                case '\n':
                case '\r':
                    whiteSpace();
                    continue;
                case '(':
                    consume();
                    return new Token( LPAREN, "(" );
                case ')':
                    consume();
                    return new Token( RPAREN, ")" );
                case '[':
                    consume();
                    return new Token( LBRACK, "[");
                case ']':
                     consume();
                    return new Token( RBRACK, "]");
                default:
                    if ( isLETTER() )
                    {
                        return SYMBOL();
                    }
                    else if(isNUMBER())
                    {
                        return NUM();
                    }
                    else if( isDQUOTE())
                    {
                        return STRING();  
                    }
            }
        }
        return new Token(EOF_TYPE, "<EOF>");
    }

    @Override
    public String getTokenName( int tokenType )
    {
        return tokenNames[tokenType];
    }
}
