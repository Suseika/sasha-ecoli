package org.tendiwa.ecoli.alg;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $id$
 */
public final class Word {

    private final transient CharSequence charSequence;

    public Word(CharSequence charSequence) {
        this.charSequence = charSequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return !(charSequence != null ? !charSequence.equals(word.charSequence) : word.charSequence != null);
    }

    @Override
    public int hashCode() {
        return charSequence.hashCode();
    }

    @Override
    public String toString() {
        return charSequence.toString();
    }
}
