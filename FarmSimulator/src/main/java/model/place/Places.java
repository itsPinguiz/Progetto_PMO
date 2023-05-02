package model.place;

/**
 * Enum that represents the places an actor can be in
 */
public enum Places {
    /**
     * Enum values
     */
    BARN("Barn"),
    MARKET("Market"),
    ANIMAL_LAND("Animal Land"),
    ANIMAL_CHUNK("Animal Chunk"),
    PLANT_LAND("Plant Land"),
    PLANT_CHUNK("Plant Chunk");

    /**
     * Attributes
     */
    private String name;

    /**
     * Constructor
     * @param name Name of the place
     */
    Places(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the place
     * @return String Name of the place
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the name of the place
     * @return String Name of the place
     */
    @Override
    public String toString() {
        return getName();
    }
}
