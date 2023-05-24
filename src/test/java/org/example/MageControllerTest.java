package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class MageControllerTest {
    private MageController controller;
    private MageRepository repository;

    @BeforeEach
    public void setUp() {
        repository = Mockito.mock(MageRepository.class);
        controller = new MageController(repository);
    }

    @Test
    public void testFindExistingMage() {
        // Arrange
        Mage mage = new Mage("Merlin", 10);
        Mockito.when(repository.find("Merlin")).thenReturn(Optional.of(mage));

        // Act
        String result = controller.find("Merlin");

        // Assert
        Assertions.assertEquals("Mage[name=Merlin, level=10]", result);
    }

    @Test
    public void testFindNonExistingMage() {
        // Arrange
        Mockito.when(repository.find("Gandalf")).thenReturn(Optional.empty());

        // Act
        String result = controller.find("Gandalf");

        // Assert
        Assertions.assertEquals("not found", result);
    }

    @Test
    public void testDeleteExistingMage() {
        // Arrange
        Mockito.doNothing().when(repository).delete("Gandalf");

        // Act
        String result = controller.delete("Gandalf");

        // Assert
        Assertions.assertEquals("done", result);
        Mockito.verify(repository, Mockito.times(1)).delete("Gandalf");
    }

    @Test
    public void testDeleteNonExistingMage() {
        // Arrange
        Mockito.doThrow(IllegalArgumentException.class).when(repository).delete("Dumbledore");

        // Act
        String result = controller.delete("Dumbledore");

        // Assert
        Assertions.assertEquals("not found", result);
        Mockito.verify(repository, Mockito.times(1)).delete("Dumbledore");
    }

    @Test
    public void testSaveNewMage() {
        // Arrange
        Mockito.doNothing().when(repository).save(Mockito.any(Mage.class));

        // Act
        String result = controller.save("Dumbledore", "20");

        // Assert
        Assertions.assertEquals("done", result);
        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(Mage.class));
    }

    @Test
    public void testSaveInvalidLevel() {
        // Act
        String result = controller.save("Gandalf", "abc");

        // Assert
        Assertions.assertEquals("bad request", result);
        Mockito.verify(repository, Mockito.never()).save(Mockito.any(Mage.class));
    }

    @Test
    public void testSaveExistingMage() {
        // Arrange
        Mockito.doThrow(IllegalArgumentException.class).when(repository).save(Mockito.any(Mage.class));

        // Act
        String result = controller.save("Gandalf", "15");

        // Assert
        Assertions.assertEquals("bad request", result);
        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(Mage.class));
    }
}