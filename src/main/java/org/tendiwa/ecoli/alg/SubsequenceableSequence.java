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
                "Length of subsequences ("+length+") must not exceed " +
                    "the length of the sequence ("+this.length()+")"
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
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Basic basic = (Basic) o;

            return !(sequence != null ? !sequence.equals(basic.sequence) : basic.sequence != null);

        }

        @Override
        public int hashCode() {
            return sequence != null ? sequence.hashCode() : 0;
        }

        @Override
        public int length() {
            return this.sequence.length();
        }

        @Override
        public String toString() {
            return sequence.toString();
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
