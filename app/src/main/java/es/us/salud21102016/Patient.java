package es.us.salud21102016;

public class Patient {

    public String id;
    public String name;
    public Long age;
    public Long timestamp;

    public Patient(){}

    public Patient(String id, String name, Long age, Long timestamp) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.timestamp = timestamp;
    }
}
