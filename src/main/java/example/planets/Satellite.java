package example.planets;

public class Satellite extends SpaceObject {
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Satellite(String name, double mass, String color) {
        super(name, mass);
        this.color = color;
    }

    @Override
    public String toString() {
        return "Satellite{" +
                "name='"+getName()+
                "', color='" + color + '\'' +
                '}';
    }
}