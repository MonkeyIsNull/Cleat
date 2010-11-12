/*
 * $Id$
 * Copyright (C) 2010 ARIN
 */
package org.cleat;

import org.testng.annotations.Test;

/**
 * @version $Rev$
 */
public class HelloWorldTest
{
    @Test
    public void helloWorld()
    {
        String source = "(println \"hello world!\")";
        LispLexer lispLexer = new LispLexer(source);
        int lookaheadBufferSize = 10;
        LispParser lispParser = new LispParser(lispLexer,lookaheadBufferSize);
        lispParser.lispAtoms(); // todo add top level parser method
    }
}
