# Entity Management App

This is a simple Java application that allows you to manage entities in memory. It consists of a repository and a controller component.

## Repository
The repository (`MageRepository`) stores a collection of entities and provides methods for saving, deleting, and finding entities. The collection is stored in memory, and there is no need for external databases or persistence mechanisms.

The repository enforces the following rules:
- Trying to delete a non-existing entity will throw an `IllegalArgumentException`.
- Trying to retrieve a non-existing entity will return an empty `Optional`.
- Trying to retrieve an existing entity will return an `Optional` containing the entity.
- Trying to save an entity with a duplicate primary key in the repository will throw an `IllegalArgumentException`.

## Controller
The controller (`MageController`) utilizes the repository by injecting it as a dependency. It provides methods that accept and return string representations of entities.

The controller follows the following specifications:
- Trying to delete a non-existing entity will return the string "done".
- Trying to delete a non-existing entity will return the string "not found".
- Trying to retrieve a non-existing entity will return the string "not found".
- Trying to retrieve an existing entity will return a string representation of the found entity.
- Trying to save a new entity will invoke the corresponding method in the service with the correct parameter and return the string "done".
- Trying to save a new entity with a duplicate primary key will return the string "bad request".

## Usage
To use the application, you can instantiate the `MageController` and interact with it by calling its methods. The controller internally uses the `MageRepository` for data storage and retrieval.

You can clone the repository and import it into your preferred Java IDE. Run the unit tests provided in the project to verify the functionality of the repository and controller components.

Feel free to modify and extend the code according to your specific needs.

