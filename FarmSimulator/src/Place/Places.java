package Place;

public enum Places {
    // List of places an actor can be in
    BARN("Barn"),
    ANIMAL_LAND("Animal Land"),
    PLANT_LAND("Plant Land"),
    PLANT_CHUNK("Plant Chunk");

    private String name;

    Places(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
