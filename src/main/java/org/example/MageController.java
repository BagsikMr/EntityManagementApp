package org.example;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MageController {
    private MageRepository repository;

    public MageController(MageRepository repository) {
        this.repository = repository;
    }

    public String find(String name) {
        Optional<Mage> mageOptional = repository.find(name);
        return mageOptional.map(this::convertMageToString).orElse("not found");
    }

    public String delete(String name) {
        try {
            repository.delete(name);
            return "done";
        } catch (IllegalArgumentException e) {
            return "not found";
        }
    }

    public String save(String name, String level) {
        try {
            int mageLevel = Integer.parseInt(level);
            Mage mage = new Mage(name, mageLevel);
            repository.save(mage);
            return "done";
        } catch (IllegalArgumentException e) {
            return "bad request";
        }
    }

    private String convertMageToString(Mage mage) {
        return "Mage[name=" + mage.getName() + ", level=" + mage.getLevel() + "]";
    }
}
