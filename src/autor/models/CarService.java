package models;

public class CarService {
    Integer SId;
    String Name;
    TypeEnum Type;

    public CarService(Integer SId, String name, TypeEnum type) {
        this.SId = SId;
        this.Name = name;
        this.Type = type;
    }

    public CarService() {

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

    public TypeEnum getType() {
        return Type;
    }

    public void setType(TypeEnum type) {
        Type = type;
    }
}
