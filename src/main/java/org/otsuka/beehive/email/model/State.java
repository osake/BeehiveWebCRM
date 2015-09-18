package org.otsuka.beehive.email.model;


import java.util.Map;
import java.util.TreeMap;
 
public enum State {
 
	ALABAMA("Alabama", "Alabama"),
	ALASKA("Alaska", "Alaska"),
	ARIZONA("Arizona", "Arizona"),
	ARKANSAS("Arkansas", "Arkansas"),
	CALIFORNIA("California", "California"),
	COLORADO("Colorado", "Colorado"),
	CONNECTICUT("Connecticut", "Connecticut"),
	DELAWARE("Delaware", "Delaware"),
	DISTRICT_OF_COLUMBIA("District of Columbia", "District of Columbia"),
	FLORIDA("Florida", "Florida"),
	GEORGIA("Georgia", "Georgia"),
	HAWAII("Hawaii", "Hawaii"),
	IDAHO("Idaho", "Idaho"),
	ILLINOIS("Illinois", "Illinois"),
	INDIANA("Indiana", "Indiana"),
	IOWA("Iowa", "Iowa"),
	KANSAS("Kansas", "Kansas"),
	KENTUCKY("Kentucky", "Kentucky"),
	LOUISIANA("Louisiana", "Louisiana"),
	MAINE("Maine", "Maine"),
	MARYLAND("Maryland", "Maryland"),
	MASSACHUSETTS("Massachusetts", "Massachusetts"),
	MICHIGAN("Michigan", "Michigan"),
	MINNESOTA("Minnesota", "Minnesota"),
	MISSISSIPPI("Mississippi", "Mississippi"),
	MISSOURI("Missouri", "Missouri"),
	MONTANA("Montana", "Montana"),
	NEBRASKA("Nebraska", "Nebraska"),
	NEVADA("Nevada","Nevada"),
	NEW_HAMPSHIRE("New Hampshire", "New Hampshire"),
	NEW_JERSEY("New Jersey", "New Jersey"),
	NEW_MEXICO("New Mexico", "New Mexico"),
	NEW_YORK("New York", "NY"),
	NORTH_CAROLINA("North Carolina", "NC"),
	NORTH_DAKOTA("North Dakota", "ND"),
	OHIO("Ohio", "Ohio"),
	OKLAHOMA("Oklahoma", "Oklahoma"),
	OREGON("Oregon", "Oregon"),
	PENNSYLVANIA("Pennsylvania", "Pennsylvania"),
	RHODE_ISLAND("Rhode Island", "Rhode Islan"),
	SOUTH_CAROLINA("South Carolina", "South Carolina"),
	SOUTH_DAKOTA("South Dakota", "South Dakota"),
	TENNESSEE("Tennessee", "Tennessee"),
	TEXAS("Texas", "Texas"),
	UTAH("Utah", "Utah"),
	VERMONT("Vermont", "Vermont"),
	VIRGINIA("Virginia", "VA"),
	WASHINGTON("Washington", "Washington"),
	WEST_VIRGINIA("West Virginia", "West Virginia"),
	WISCONSIN("Wisconsin", "Wisconsin"),
	WYOMING("Wyoming", "Wyoming");
	
	public static final Map<String, String> STATE_MAP;
	static {
	    STATE_MAP = new TreeMap<String, String>();
	    STATE_MAP.put("AL", "Alabama");
	    STATE_MAP.put("AK", "Alaska");
	    STATE_MAP.put("AZ", "Arizona");
	    STATE_MAP.put("AR", "Arkansas");
	    STATE_MAP.put("CA", "California");
	    STATE_MAP.put("CO", "Colorado");
	    STATE_MAP.put("CT", "Connecticut");
	    STATE_MAP.put("DE", "Delaware");
	    STATE_MAP.put("DC", "District Of Columbia");
	    STATE_MAP.put("FL", "Florida");
	    STATE_MAP.put("GA", "Georgia");
	    STATE_MAP.put("HI", "Hawaii");
	    STATE_MAP.put("ID", "Idaho");
	    STATE_MAP.put("IL", "Illinois");
	    STATE_MAP.put("IN", "Indiana");
	    STATE_MAP.put("IA", "Iowa");
	    STATE_MAP.put("KS", "Kansas");
	    STATE_MAP.put("KY", "Kentucky");
	    STATE_MAP.put("LA", "Louisiana");
	    STATE_MAP.put("ME", "Maine");
	    STATE_MAP.put("MD", "Maryland");
	    STATE_MAP.put("MA", "Massachusetts");
	    STATE_MAP.put("MI", "Michigan");
	    STATE_MAP.put("MN", "Minnesota");
	    STATE_MAP.put("MS", "Mississippi");
	    STATE_MAP.put("MO", "Missouri");
	    STATE_MAP.put("MT", "Montana");
	    STATE_MAP.put("NE", "Nebraska");
	    STATE_MAP.put("NV", "Nevada");
	    STATE_MAP.put("NH", "New Hampshire");
	    STATE_MAP.put("NJ", "New Jersey");
	    STATE_MAP.put("NM", "New Mexico");
	    STATE_MAP.put("NY", "New York");
	    STATE_MAP.put("NC", "North Carolina");
	    STATE_MAP.put("ND", "North Dakota");
	    STATE_MAP.put("OH", "Ohio");
	    STATE_MAP.put("OK", "Oklahoma");
	    STATE_MAP.put("OR", "Oregon");
	    STATE_MAP.put("PA", "Pennsylvania");
	    STATE_MAP.put("RI", "Rhode Island");
	    STATE_MAP.put("SC", "South Carolina");
	    STATE_MAP.put("SD", "South Dakota");
	    STATE_MAP.put("TN", "Tennessee");
	    STATE_MAP.put("TX", "Texas");
	    STATE_MAP.put("UT", "Utah");
	    STATE_MAP.put("VT", "Vermont");
	    STATE_MAP.put("VA", "Virginia");
	    STATE_MAP.put("WA", "Washington");
	    STATE_MAP.put("WV", "West Virginia");
	    STATE_MAP.put("WI", "Wisconsin");
	    STATE_MAP.put("WY", "Wyoming");
	}
	
    /**
     * The state's name.
     */
    private String name;
 
    /**
     * The state's abbreviation.
     */
    private String abbreviation;
 
    /**
     * The set of states addressed by abbreviations.
     */
    private static final Map<String, State> STATES_BY_ABBR = new TreeMap<String, State>();
 
    /* static initializer */
    static {
        for (State state : values()) {
            STATES_BY_ABBR.put(state.getAbbreviation(), state);
        }
    }
 
    /**
     * Constructs a new state.
     *
     * @param name the state's name.
     * @param abbreviation the state's abbreviation.
     */
    State(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }
 
    /**
     * Returns the state's abbreviation.
     *
     * @return the state's abbreviation.
     */
    public String getAbbreviation() {
        return abbreviation;
    }
    
    public static Map<String, State> getStatesMap() {
    	
    	return STATES_BY_ABBR;
    }
    
    public static Map<String, String> getStates() {
    	return STATE_MAP;
    }
 
    /**
     * Gets the enum constant with the specified abbreviation.
     *
     * @param abbr the state's abbreviation.
     * @return the enum constant with the specified abbreviation.
     * @throws SunlightException if the abbreviation is invalid.
     */
    public static State valueOfAbbreviation(final String abbr) {
        final State state = STATES_BY_ABBR.get(abbr);
        if (state != null) {
            return state;
        } else {
            return State.ALABAMA;
        }
    }
    
    public static State valueOfName(final String name) {
        final String enumName = name.toUpperCase().replaceAll(" ", "_");
        try {
            return valueOf(enumName);
        } catch (final IllegalArgumentException e) {
            return State.ALASKA;
        }
    }
 
    @Override
    public String toString() {
        return name;
    }
}