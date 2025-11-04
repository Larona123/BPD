package com.bitri.co.bw.Bitri_Projects_Dash.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RiskAndIssueSeverity {
    EXTREME,
    HIGH,
    MEDIUM,
    LOW,
    SOLVED;

    @JsonCreator
    public static RiskAndIssueSeverity fromString(String value) {
        if (value == null) {
            return null;
        }
        try {
            return RiskAndIssueSeverity.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Invalid severity value: '" + value + "'. Must be one of: " +
                            java.util.Arrays.toString(RiskAndIssueSeverity.values())
            );
        }
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
