package org.jabref.logic.journals;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.jabref.logic.conferences.ConferenceAbbreviationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbbreviationLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbbreviationLoader.class);
    

    public static Collection<Abbreviation> readAbbreviationsFromCsvFile(Path file) throws IOException {
        LOGGER.debug("Reading journal list from file {}", file);
        AbbreviationParser parser = new AbbreviationParser();
        parser.readJournalListFromFile(file);
        return parser.getAbbreviations();
    }

  protected Path copyResource(String resourcePath) throws IOException {
        try (InputStream resourceAsStream = getClass().getResourceAsStream(resourcePath)) {
            if (resourceAsStream == null) {
                return null;
            }
            Path tempDir = Files.createTempDirectory("jabref-abbreviation");
            Path tempFile = tempDir.resolve(Path.of(resourcePath).getFileName().toString());
            Files.copy(resourceAsStream, tempFile);

            tempDir.toFile().deleteOnExit();
            tempFile.toFile().deleteOnExit();

            LOGGER.debug("Loaded abbreviations from {}", tempFile.toAbsolutePath());
            return tempFile;
        }
    }

    protected void loadExternalList(AbbreviationPreferences abbreviationPreferences, AbbreviationRepository repository) {
        List<String> lists = abbreviationPreferences.getExternalLists();
        if (lists != null && !lists.isEmpty()) {
            Collections.reverse(lists);
            for (String filename : lists) {
                try {
                    repository.addCustomAbbreviations(readAbbreviationsFromCsvFile(Path.of(filename)));
                } catch (IOException | InvalidPathException e) {
                    LOGGER.error("Cannot read external list file {}", filename, e);
                }
            }
        }
    }

    
}
