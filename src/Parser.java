/**
 *
 * 
 * Parr's LL(k) RecDescent Parser.
 * Prolly overkill for this language though.
 * Fuck it, you can kill more Zombies w/ it,
 * and you'll need it if the Ninjas are late.
 */
public class Parser
{
    Lexer input;
    Token[] lookahead;
    int k;
    int p = 0;
    
    public Parser(Lexer input, int k)
    {
        this.input = input;
        this.k = k;
        
        // this here is the lookahead buffer
        lookahead = new Token[k];
        // fire up the buffer with k lookahead;
        for(int i =1; i <= k; i++)
        {
            consume();
        }
    }

    private void consume()
    {
      // fill next pos w/ token
      lookahead[p] = input.nextToken();
      // incr circular idx
       p = (p+1) % k;
    }
    
    public Token LT(int i)
    {
        // circular fetch
        return lookahead[(p+i-1) % k];
    }
    
    public int  LA(int i)
    {
        return LT(i).type;
    }
    
    public void match(int x)
    {
        if(LA(1) == x)
        {
            consume();
        }
        else
        {
            throw new Error("Hey, jackhole, I expected: " + input.getTokenName( x ) + " but got: " + LT(1));
        }
    }

}
