
/**
 * LispParser
 */
public class LispParser extends Parser
{
    public LispParser( Lexer input, int k )
    {
        super( input, k );
    }

//    public void lispString()
//    {
//        if(LA(1) == LispLexer.STRING)
//        {
//            match(LispLexer.STRING);
//        }
//    }

    public void lispList()
    {
       match( LispLexer.LPAREN);
       lispAtoms();
       match( LispLexer.RPAREN);
    }
    
    public void lispAtoms()
    {
        
    }

    public boolean lispAtom()
    {
        boolean didMatch = false;
        
        if(LA(1) == LispLexer.SYMBOL)
        {
            match( LispLexer.SYMBOL);
            didMatch = true;
        }
        else if(LA(1) == LispLexer.NUM)
        {
            match( LispLexer.NUM);
            didMatch = true;
        }
        else if(LA(1) == LispLexer.STRING)
        {
            match( LispLexer.STRING);
            didMatch = true;
        }
        
        return didMatch;
    }
    
    
    
}
