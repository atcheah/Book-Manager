package io;

import models.Library;

import java.io.IOException;
import java.util.List;

public interface SaveLibrary {
    void save(List lib, String file) throws IOException;
}
