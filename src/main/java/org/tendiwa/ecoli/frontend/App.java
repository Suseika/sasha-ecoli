package org.tendiwa.ecoli.frontend;

import com.google.common.base.Stopwatch;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import org.tendiwa.ecoli.alg.GenomeSequence;
import org.tendiwa.ecoli.alg.CloseOccurrences;

/**
 * @author Georgy Vlasov (suseika@tendiwa.org)
 * @version $Id$
 */
public final class App {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            throw new IllegalArgumentException(
                "You must provide filename as an argument"
            );
        }
        final File file = new File(
            args[0]
        );
        Stopwatch watch = Stopwatch.createStarted();
        final int fileSize = (int) Files.size(file.toPath());
        final Collection<CharSequence> words =
            new CloseOccurrences(
                    new GenomeSequence(
                        new FileReader(
                            file
                        ),
                        fileSize
                    )
            );
        System.out.println(
            "Thrice-occurring ninemeres found: " + words.size() + " items"
        );
        System.out.println(
            "Computation took " + watch.elapsed(TimeUnit.SECONDS) + " seconds"
        );
        System.out.println("Here they are:");
        words.forEach(System.out::println);
    }
}
