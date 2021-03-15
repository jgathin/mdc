package com.magnolia.mdc.models.partModels;

public enum PartType {
    SCREWSANDBOLTS("Screws & Bolts"),
    WIRE("Coax, Cat-6, Speaker, etc."),
    WIREMANAGEMENT("Wire Management"),
    CONNECTORS("Connectors & Ends"),
    CABLES("Patch Cables"),
    ADHESIVES("Tape, Silicone, Glue, etc."),
    OTHER("Random Parts");

    private final String partType;

    PartType(String partType) {
        this.partType = partType;
    }

    public String getPartType() {
        return partType;
    }
}
