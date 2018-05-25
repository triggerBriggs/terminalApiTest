package com.ineda.microtote.wagering;

public enum MtBetTypes {
    WIN			("WIN", 1, false, false, false, 1),
    PLACE		("PLC", 2, false,false, false, 1),

    EXACTA		("EXA", 8, true, false, false, 2),

    TRIFECTA	("TRI", 12, true, false, false, 3),

    PICK4		("PK4", 16, false, true, false, 4),

    SUPERFECTA	("SFC", 35, true, false, false, 4),

    PENTAFECTA	("E5N", 51, true, false, false, 5);

    private final String code;
    private final int betTypeID;
    private final boolean multiLeg;
    private final boolean multiRace;
    private final boolean boxed;
    private final int legs;

    MtBetTypes(final String code, int betTypeID, boolean multiLeg, boolean multiRace, boolean boxed, int legs) {
        this.code = code;
        this.betTypeID = betTypeID;
        this.multiLeg = multiLeg;
        this.multiRace = multiRace;
        this.boxed = boxed;
        this.legs = legs;
    }
    /**
     * Gets microtote name for bet types.
     *
     * @return Bet types.
     */
    public String getCode() {
        return code;
    }


    /**
     * Gets microtote name for bet types.
     *
     * @return Bet type ID that will be sent to and from microtote.
     */
    public int getBetTypeId() {
        return betTypeID;
    }

    /**
     * Is this a multi leg bet type. ??
     *
     * @return true Multi Leg pool, false for single leg pool.
     */
    public boolean isMultiLeg() {
        return multiLeg;
    }

    /**
     * Is this a multi race bet type. ??
     *
     * @return true Multi Race pool, false for single race.
     */
    public boolean isMultiRace() {
        return multiRace;
    }

    /**
     * Is this a boxed bet type. ??
     *
     * @return true if boxed bet, false if not.
     */
    public boolean isBoxed() {
        return boxed;
    }


    /**
     * How many legs in the bet type.
     *
     * @return int with number of legs for bet type.
     */
    public int getLegs() {
        return legs;
    }

    /**
     * Gets a bet type from the three letter code passed
     *
     * @param text pool Code eg "EXA", "WIN"
     * @return Bet types object or null if not found
     */
    public static MtBetTypes fromCode(String text) {
        if (text != null) {
            for (MtBetTypes b : MtBetTypes.values()) {
                if (text.equalsIgnoreCase(b.code)) {
                    return b;
                }
            }
        }
        return null;
    }

    /**
     * Do we know what to do with this bet type ??, only if its in the enum
     * <p/>
     * @param text pool Code eg "EXA", "WIN"
     * @return TRUE if available, por false if not.
     */
    public static boolean available(String text) {
        if (text != null) {
            for (MtBetTypes b : MtBetTypes.values()) {
                if (text.equalsIgnoreCase(b.code)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gets a bet type from the microtote betTypeID
     *
     * @param int of the betTypeID, eg. 1 for a WIN
     * @return Bet types object or null if not found
     */
    public static MtBetTypes fromBetTypeId(int betTypeID) {
        if (betTypeID>0) {
            for (MtBetTypes b : MtBetTypes.values()) {
                if (betTypeID==b.betTypeID) {
                    return b;
                }
            }
        }
        return null;
    }
}
