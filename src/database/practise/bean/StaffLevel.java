package database.practise.bean;

public class StaffLevel {
    private int id;
    private String name;
    private char type;
    private short level;

    public StaffLevel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public StaffLevel(String name) {
        this(0, name);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short level) {
        this.level = level;
    }
}