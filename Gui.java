import java.util.ArrayList;
import java.util.Scanner;


public class Gui {
    addAnimal action = new addAnimal();

    public void menu() {
        Scanner in = new Scanner(System.in);
        System.out.println("Выберите действие: ");
        System.out.println("1.Посмотреть список животных \n2.Добавить животное");
        int num = in.nextInt();
        if (num == 1) ListOfAnimals();
        else if (num == 2) action.add();
        
    }

    public void ListOfAnimals() {
        Scanner in = new Scanner(System.in);
        addAnimal add = new addAnimal();
        ArrayList<String> animalslist = add.ToyList();
        for (int i = 0; i<animalslist.size();i++){System.out.println(animalslist.get(i));}
        System.out.println("1. Добавить команды \n2. Вернуться в меню \n3. Закрыть программу");
        int num = in.nextInt();
        if (num == 1) changeCommands();
        else if (num == 2) menu();
        else if (num == 3) System.exit(0);
    }

    public void changeCommands() {
        addAnimal action = new addAnimal();
        Scanner in = new Scanner(System.in);
        System.out.print("Введите название животного: ");
        String name = in.next();
        action.change(name);
        


    }
}

