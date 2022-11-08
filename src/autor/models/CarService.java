package models;

public class CarService {
    Integer SId;
    String Name;
    String Type;

    public CarService(Integer SId, String name, String type) {
        this.SId = SId;
        this.Name = name;
        this.Type = type;
    }

    public CarService(Integer SId, String name) {
        this.SId = SId;
        this.Name = name;
    }

    public Integer getSId() {
        return SId;
    }

    public void setSId(Integer SId) {
        this.SId = SId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}