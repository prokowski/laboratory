package com.laboratory.sample.domain.exceptions;


public class NoSpaceInLaboratoryException extends RuntimeException {

    public NoSpaceInLaboratoryException() {
        super("There is no space in Laboratory!");
    }

}