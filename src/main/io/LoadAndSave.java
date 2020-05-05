package io;

import models.Book;
import models.BookType;
import models.Library;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoadAndSave implements LoadLibrary, SaveLibrary {

    @Override
    //MODIFIES: library
    //EFFECTS: Loads library from file designated by string onto library object
    public void load(Library shelf, String file) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(file));
            for (String line : lines) {
                ArrayList<String> pol = splitOnComma(line);
                shelf.storeBook(new Book(pol.get(0), pol.get(1), BookType.getByName(pol.get(2)),
                        Integer.parseInt(pol.get(3)), Integer.parseInt(pol.get(4))));
            }
        } catch (IOException e) {
            System.out.println("Attempted to load but failed");
        }
    }

    @Override
    //MODIFIES: file
    //EFFECTS: Saves library data to file in readable format
    public void save(List shelf, String file) throws FileNotFoundException {
        try {
            PrintWriter writer = new PrintWriter(file);
            for (int i = 0; i < shelf.size(); i++) {
                String line;
                line = ((Book)shelf.get(i)).getTitle() + ", " + ((Book)shelf.get(i)).getAuthor() + ", "
                        + ((Book)shelf.get(i)).getType().getName() + ", "
                        + ((Book)shelf.get(i)).getYear() + ", "
                        + ((Book)shelf.get(i)).getEdition();
                writer.println(line);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }


    //EFFECTS: Splits String into array list segments based on commas, parses from file
    public static ArrayList<String> splitOnComma(String line) {
        String[] splits = line.split(", ");
        return new ArrayList<>(Arrays.asList(splits));
    }
}