package daos;

import java.util.Map;

public abstract class Dao<T> {
    /**
     * Insert values into a table.
     * @param obj Entity to create and use in the database.
     * @return The ID of the entity in the table.
     */
    public abstract int create(T obj);

    /**
     * Read the entity matching the id.
     * @param id ID of the entity.
     * @return The object matching the id.
     */
    public abstract T read(int id);

    /**
     * Read all the entities of a table.
     * @return All objects in the table.
     */
    public abstract T[] readAll();

    /**
     * Update a specified element of the table.
     * @param obj Entity to update in the database.
     * @param key Column to update.
     * @return True if the modification worked.
     */
    public abstract boolean update(T obj, Map<String, Object> key);

    /**
     * Delete a specified element of the table.
     * @param id Entity to delete from the table.
     * @return True if the modification worked.
     */
    public abstract boolean delete(int id);
}
