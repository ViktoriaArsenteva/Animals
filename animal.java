import java.util.List;

public abstract class animal {
    public int id;
    public String name;
    public List<String> commands;

    public animal(int id, String name, List<String> commands){
        this.id = id;
        this.name = name;
        this.commands = commands;
    }
}
