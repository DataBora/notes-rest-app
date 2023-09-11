package com.bizanaliza.app.notes.Utils;

import com.bizanaliza.app.notes.Model.Priority;

import java.util.ArrayList;
import java.util.List;

public class PriorityUtil {

    public static List<PriorityDescription> getAllProperties(){

        List<PriorityDescription> priorities = new ArrayList<>();

        for (Priority priority: Priority.values()) {
            priorities.add(new PriorityDescription(priority.toString(), priority.getDescription()));
        }
        return priorities;
    }
}
