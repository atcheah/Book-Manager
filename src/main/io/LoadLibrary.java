package io;

import exceptions.ContainsException;
import exceptions.NegativeNumException;
import models.Library;

import java.io.IOException;

public interface LoadLibrary {
    void load(Library lib, String file) throws IOException, NegativeNumException, ContainsException;
}
