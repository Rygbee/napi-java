package com.maluuba.napi.client;

public enum NAPILanguage {
    /**
     * For English, all {@link NAPICategory}'s are supported except for
     * {@link NAPICategory#TVCHANNEL} and {@link NAPICategory#TVVOLUME}.
     */
    ENGLISH,
    /**
     * Only {@link NAPICategory#ALARM}, {@link NAPICategory#SEARCH},
     * {@link NAPICategory#TVCHANNEL}, {@link NAPICategory#TVVOLUME},
     * {@link NAPICategory#and WEATHER} are supported for French.
     */
    FRENCH
}
