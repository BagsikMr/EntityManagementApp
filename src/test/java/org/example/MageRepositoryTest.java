package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class MageRepositoryTest {
    private MageRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new MageRepository();
    }

    @Test
    public void testFindExistingMage() {
        // Arrange
        Mage mage = new Mage("Merlin", 10);
        repository.save(mage);

        // Act
        Optional<Mage> result = repository.find("Merlin");

        // Assert
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(mage, result.get());
    }

    @Test
    public void testFindNonExistingMage() {
        // Act
        Optional<Mage> result = repository.find("Gandalf");

        // Assert
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void testDeleteExistingMage() {
        // Arrange
        Mage mage = new Mage("Gandalf", 15);
        repository.save(mage);

        // Act
        repository.delete("Gandalf");

        // Assert
        Optional<Mage> result = repository.find("Gandalf");
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void testDeleteNonExistingMage() {
        // Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            // Act
            repository.delete("Dumbledore");
        });
    }

    @Test
    public void testSaveNewMage() {
        // Arrange
        Mage mage = new Mage("Dumbledore", 20);

        // Act
        repository.save(mage);

        // Assert
        Optional<Mage> result = repository.find("Dumbledore");
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(mage, result.get());
    }

    @Test
    public void testSaveExistingMage() {
        // Arrange
        Mage mage = new Mage("Gandalf", 15);
        repository.save(mage);

        // Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            // Act
            repository.save(mage);
        });
    }
}

