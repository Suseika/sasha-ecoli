package org.tendiwa.ecoli.alg;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 */
public interface SubsequenceableSequence extends CharSequence {
    default Stream<SubsequenceableSequence> subsequences(int length) {
        if (length > this.length()) {
            throw new IllegalArgumentException(
                "Length of subsequences must not exceed " +
                    "the length of the sequence"
            );
        }
        final int lastStart = this.length() - length + 1;
        return IntStream.range(0, lastStart)
            .mapToObj(i -> new Basic(this.subSequence(i, i+length)));
    }

    /**
     * Wrapper for a {@link CharSequence} to implement
     * {@link SubsequenceableSequence}'s default methods.
     */
    class Basic implements SubsequenceableSequence {
        private final CharSequence sequence;

        Basic(CharSequence sequence) {
            this.sequence = sequence;
        }

        @Override
        public int length() {
            return this.sequence.length();
        }

        @Override
        public char charAt(int i) {
            return this.sequence.charAt(i);
        }

        @Override
        public CharSequence subSequence(int start, int end) {
            return this.sequence.subSequence(start, end);
        }
    }
}
