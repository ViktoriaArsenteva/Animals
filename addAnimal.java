import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class addAnimal {
    String [] commandsArray = new String[] {"GO","STOP","RUN AWAY","COME UP","LIE","JUMP","TRUP","STAND UP"};

    public void add() {
        Scanner in = new Scanner(System.in);
        System.out.print("Id = ");
        int id = in.nextInt();
        System.out.print("Name = ");
        String name = in.next();
        String[] commands = commands();
        for(int i = 0; i<commands.length;i++){System.out.println(commands[i]);}
        CreateFile(id, name, commands);

        
    }

    public String[] commands(){
        Scanner in = new Scanner(System.in);
        System.out.println("Select the command that the animal perfoms:");
        for (int i = 0; i<commandsArray.length; i++)
        {
            System.out.print(i + 1);
            System.out.print(". ");
            System.out.println(commandsArray[i]);
        }
        System.out.print("Enter numbers without spaces: ");
        String commandNumbers = in.next();
        String [] num = commandNumbers.split("");
        Arrays.sort(num);
        try {
            System.out.println(num.length);
            for (int j = 0; j<num.length; j++){
                int number = Integer.parseInt(num[j]);
                num[j] = commandsArray[number-1];
            }
        } catch (Exception e) {
            System.out.println("Try again");
            commands();
        }
        return num;
    }

    public void CreateFile(int id, String name, String[]commands) {
        String filename = "animals/"+ name + ".txt";
        Path newFilePath = Paths.get(filename);
        
        try {
            Files.createFile(newFilePath);
            String text = id + "\n" + name + "\n";
            for (int i = 0; i < commands.length; i++){text += commands[i] + ";";}
            try {
                Files.write(newFilePath, text.getBytes(), StandardOpenOption.APPEND);

            } catch (IOException e) {
                e.printStackTrace();
        }
        } catch (Exception e) {
            System.out.println("Такое животное уже сущетсвует, введите другое имя: ");
            add();
        }
        
        
    }

    public ArrayList<String> ToyList() {
        String rootDir = "animals/";
        ArrayList<String> animals = new ArrayList<String>();
            try {
                Files.walkFileTree(Paths.get(rootDir), new SimpleFileVisitor<Path>()
                {
                    @Override
                    public FileVisitResult visitFile(Path filePath,
                                                    BasicFileAttributes attributes)
                    {
                        String filename = filePath.getFileName().toString();
                        String name = filename.substring(0, filename.lastIndexOf('.'));
                        animals.add(name);
                        return FileVisitResult.CONTINUE;
                        
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            return animals;
        }
    
    public void change(String name) {
        Gui gui = new Gui();
        Scanner in = new Scanner(System.in);
        String comms = "";
        try (FileReader fReader = new FileReader("animals/" + name + ".txt")) {
            Scanner scanner = new Scanner(fReader);
            while (scanner.hasNextLine()){comms = scanner.nextLine();}
        } catch (IOException e) {
            System.out.println("Try again");
            gui.changeCommands();

        }
        String [] commands = comms.split(";");
        System.out.println("Добавленные команды:");
        for(int i = 0; i<commands.length; i++){System.out.println(commands[i]);}
        int len = commandsArray.length - commands.length;
        if (len == 0){System.out.println("Все доступные команды ужe добавлены");}

        else{
            String free = "";
            boolean check = false;
            for(int j = 0;j<commandsArray.length;j++){
                for(int k = 0;k<commands.length;k++){
                    if (Objects.equals(commandsArray[j], commands[k])){
                        check = true;
                        break;
                    }
                    else{
                        check = false;
                    }  
            }
            if (check == false){
                free += commandsArray[j];
                free += ";";
            }
        }
        String[]  freeCommands = free.split(";");
        System.out.println("Доступные команды: ");
        for (int l = 0; l < freeCommands.length;l++){
            System.out.println(l + "."+freeCommands[l]);
        }
        System.out.print("Введите номера команд без пробела: ");
        String commandNumbers = in.next();
        String [] com = commandNumbers.split("");
        try {
            for (int j = 0; j<com.length; j++){
                int number = Integer.parseInt(com[j]);
                com[j] = freeCommands[number];
            }
        } catch (Exception e) {
            System.out.println("Try again");
            change(name);
        }

        String filePath = "animals/"+ name + ".txt";
        String text = "";
        for (int g = 0; g<com.length;g++){
        text += com[g]+";";
        }
        try {
            Files.write(Paths.get(filePath), text.getBytes(), StandardOpenOption.APPEND);
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
    }
    
}

