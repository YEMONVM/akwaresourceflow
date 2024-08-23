package com.akwaresourceflow.enums;

public enum TableStatus {
    AVAILABLE, // 0
    OCCUPIED,  // 1
    RESERVED,  // 2
    // Add other statuses as needed
    ;

    public static TableStatus fromValue(short value) {
        switch (value) {
            case 0:
                return AVAILABLE;
            case 1:
                return OCCUPIED;
            case 2:
                return RESERVED;
            default:
                throw new IllegalArgumentException("Unknown status value: " + value);
        }
    }
}
