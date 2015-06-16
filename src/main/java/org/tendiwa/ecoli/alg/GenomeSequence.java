package org.tendiwa.ecoli.alg;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Arrays;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $id$
 */
public final class GenomeSequence implements CharSequence {
    public static final int BUFFER_SIZE = 2048;
    private final transient char[] characters;

    public GenomeSequence(Readable source, int length) throws IOException {
        this.characters = new char[length];
        final CharBuffer buffer = CharBuffer.allocate(BUFFER_SIZE);
        final Flush flush = new Flush(buffer, characters);
        int bytesRead = source.read(buffer);
        while (bytesRead != -1) {
            buffer.flip();
            flush.populate();
            bytesRead = source.read(buffer);
        }
    }

    @Override
    public int length() {
        return characters.length;
    }

    @Override
    public char charAt(int i) {
        return characters[i];
    }

    @Override
    public String toString() {
        return new String(characters);
    }

    @Override
    public CharSequence subSequence(int index, int length) {
        return new String(Arrays.copyOfRange(characters, index, length));
    }

    private static class Flush {
        private final CharBuffer source;
        private final transient char[] dest;
        private transient int size;

        Flush(CharBuffer source, char[] dest) {
            this.source = source;
            this.dest = dest;
            this.size = 0;
        }

        private void populate() {
            final int length = source.length();
            System.arraycopy(
                source.array(),
                0,
                dest,
                size,
                length
            );
            this.size += length;
        }
    }
}
