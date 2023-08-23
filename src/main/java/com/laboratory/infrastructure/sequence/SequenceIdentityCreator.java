package com.laboratory
        .infrastructure.sequence;

class SequenceIdentityCreator {

    private static final String IDENTITY_SEQUENCED_FORMAT = "%s%d";

    static String createIdentity(String prefix, Long sequence) {
        return String.format(IDENTITY_SEQUENCED_FORMAT, prefix, sequence);
    }

}
