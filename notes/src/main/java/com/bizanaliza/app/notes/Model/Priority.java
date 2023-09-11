package com.bizanaliza.app.notes.Model;

import jakarta.persistence.Embeddable;

public enum Priority {

    HIGH("High Priority"),
    MEDIUM("Medium Priority"),
    LOW("Low Priority");

    private final String description;

    Priority(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
