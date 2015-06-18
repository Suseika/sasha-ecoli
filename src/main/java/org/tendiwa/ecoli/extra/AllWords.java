package org.tendiwa.ecoli.extra;

import com.google.common.collect.ForwardingSet;
import com.sun.istack.internal.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 */
public final class AllWords extends ForwardingSet<CharSequence> {

    private static final char[] ATGC = new char[]{'A', 'T', 'G', 'C'};
    private final Set<CharSequence> words;

    public AllWords(int length) {
        final int permutations = (int) Math.round(Math.pow(4, length));
        words = new HashSet<>(permutations);
        for (long i = 0; i < permutations; i++) {
            words.add(
                new StringBuilder(
                    new UniqueWord(i, length)
                ).toString()
            );
        }
    }

    @Override
    protected Set<CharSequence> delegate() {
        return words;
    }

    private static class UniqueWord implements CharSequence {
        private final long index;
        private final int length;

        public UniqueWord(long index, int length) {
            this.index = index;
            this.length = length;
        }

        @Override
        @NotNull
        public String toString() {
            final StringBuilder stringBuilder = new StringBuilder();
            for (int i=0; i<length; i++) {
                stringBuilder.append(this.charAt(i));
            }
            return stringBuilder.toString();
        }

        @Override
        public int length() {
            return length;
        }

        @Override
        public char charAt(int i) {
            return ATGC[((int) (this.index & (0b11 << (i * 2))) >> (i*2))];
        }

        @Override
        public CharSequence subSequence(int i, int i1) {
            throw new UnsupportedOperationException();
        }
    }
}
