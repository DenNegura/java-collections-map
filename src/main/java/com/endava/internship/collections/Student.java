package com.endava.internship.collections;

import java.time.LocalDate;
import java.util.Objects;

/**
 * The class that defines the element that will be contained by your collection
 */
public class Student //TODO consider implementing any interfaces necessary for your collection
{
    private final String name;
    private final LocalDate dateOfBirth;
    private final String details;

    public Student(String name, LocalDate dateOfBirth, String details) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.details = details;
    }

    public String getName() { return name; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }

    public String getDetails() { return details; }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Student) {
            return Objects.equals(this.name, ((Student) obj).getName()) &&
                    (Objects.equals(this.dateOfBirth, ((Student) obj).getDateOfBirth()));
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.name, this.dateOfBirth);
    }
    @Override
    public final String toString() {
        return "Student : " + name + ", " + dateOfBirth + ". More details : " + details;
    }

    /*
    TODO consider overriding any methods for this object to function properly within a collection:
        1. A student is considered unique by a combination of their name and dateOfBirth
        2. Student names are sorted alphabetically, if two students have the same name, then the older one is
        placed before the younger student in an ordered student list.
    */
}
