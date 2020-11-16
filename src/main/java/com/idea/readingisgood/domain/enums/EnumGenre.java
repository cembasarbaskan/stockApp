package com.idea.readingisgood.domain.enums;

public enum EnumGenre {

    ADVENTURE("adventure"),
    SCIENCE_FICTION("science_fiction"),
    ROMANCE("romance"),
    HISTORY("history"),
    ART("art"),
    HEALTH("health");

    private final String value;
    EnumGenre(String genre) {
        this.value = genre;
    }

    public String getValue() {
        return value;
    }
}
