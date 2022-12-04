package com.example.task02;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> implements Serializable {
    private List<E> elements = new ArrayList<>();
    private File file;
    public SavedList(File file) {
        this.file = file;
        if (file.exists()) {
            Path path = file.toPath();
            List<E> elements;
            try (ObjectInputStream oos = new ObjectInputStream(Files.newInputStream(path))) {
                elements = (List) oos.readObject();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            this.elements = elements;
        }
    }

    @Override
    public E get(int index) {
        return elements.get(index);
    }

    @Override
    public E set(int index, E element) {
        E save = elements.get(index);
        elements.set(index, element);
        serialization();
        return save;
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public void add(int index, E element) {
        elements.add(index, element);
        serialization();
    }

    @Override
    public E remove(int index) {
        E save = elements.get(index);
        elements.remove(index);
        serialization();
        return save;
    }
    private void serialization() {
        Path path = file.toPath();
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))){
            oos.writeObject(elements);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
