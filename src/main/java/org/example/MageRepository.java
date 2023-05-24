package org.example;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MageRepository {
    private Map<String, Mage> collection;

    public MageRepository() {
        collection = new HashMap<>();
    }

    public Optional<Mage> find(String name) {
        Mage mage = collection.get(name);
        return Optional.ofNullable(mage);
    }

    public void delete(String name) {
        if (!collection.containsKey(name)) {
            throw new IllegalArgumentException("Mage not found: " + name);
        }
        collection.remove(name);
    }

    public void save(Mage mage) {
        String name = mage.getName();
        if (collection.containsKey(name)) {
            throw new IllegalArgumentException("Mage already exists: " + name);
        }
        collection.put(name, mage);
    }
}
