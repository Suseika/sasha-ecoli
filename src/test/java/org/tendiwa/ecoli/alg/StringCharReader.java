package org.tendiwa.ecoli.alg;

import com.sun.xml.internal.messaging.saaj.util.CharReader;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 */
public final class StringCharReader extends CharReader implements CharSequence {
    private final String string;

    public StringCharReader(String string) {
        super(string.toCharArray(), string.length());
        this.string = string;
    }

    @Override
    public int length() {
        return string.length();
    }

    @Override
    public char charAt(int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        throw new UnsupportedOperationException();
    }
}
