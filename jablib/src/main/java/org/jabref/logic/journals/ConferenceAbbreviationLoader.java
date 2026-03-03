package org.jabref.logic.journals;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.jabref.logic.conferences.ConferenceAbbreviationRepository;

public class ConferenceAbbreviationLoader extends AbbreviationLoader {


    private static final Logger LOGGER = LoggerFactory.getLogger(ConferenceAbbreviationLoader.class);

   public ConferenceAbbreviationRepository loadRepository(
        ConferenceAbbreviationPreferences preferences) {

    try {
        return ConferenceAbbreviationRepository.loadFromClasspath();
    } catch (IOException e) {
        LOGGER.error("Error while loading conference abbreviation repository", e);
        return new ConferenceAbbreviationRepository();
    }
}
    
    public static ConferenceAbbreviationRepository loadBuiltInRepository() {
    ConferenceAbbreviationLoader loader = new ConferenceAbbreviationLoader();
    return loader.loadRepository(new ConferenceAbbreviationPreferences(List.of()));
}
}
