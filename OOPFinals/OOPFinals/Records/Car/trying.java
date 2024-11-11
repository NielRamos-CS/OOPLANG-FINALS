package Records.Car;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class trying{

public static void main(String[] args) throws Exception{
    Scanner input = new Scanner(System.in);
    File path = new File("Records\\Car\\Seddan");
        String cars[] = path.list();
        List<String> available_cars = new ArrayList<>();

        for (int i = 0; i < cars.length; i++)
        {
            String file_name = cars[i];
            String checking = Files.readAllLines(Paths.get("Records\\Car\\Seddan\\" + file_name)).get(4);
            int number  = Integer.parseInt(checking);
            if (number == 127)
            {
                available_cars.add(file_name);
                System.out.println(available_cars);
            }
        }
        System.out.println("Which car would you like to rent?: ");
        int car_choice = input.nextInt() - 1;
        String for_edit = available_cars.get(car_choice);

        System.out.println("Enter the date today: ");
        System.out.println("MM: ");
        int mm = input.nextInt();
        System.out.println("DD: ");
        int dd = input.nextInt();
        System.out.println("YYYY: ");
        int yyyy= input.nextInt();
        System.out.println("Enter how many days you would like to rent: ");
        int rent_days = input.nextInt() + dd;
        String car_release = mm + "/" + rent_days + "/" + yyyy;

        edit_car_records("Seddan", for_edit, car_release);
    }

    public static void edit_car_records(String car_type, String for_edit, String car_releases) throws IOException
    {
        String filepath = ("Records\\Car\\" + car_type + "\\" + for_edit);
        Scanner sc = new Scanner(new File(filepath));
        StringBuffer buffer = new StringBuffer();

        while (sc.hasNextLine())
        {
            buffer.append(sc.nextLine() + System.lineSeparator());
        }
        String fileContents = buffer.toString();
        sc.close();
        String car_availability  = "127";
        fileContents = fileContents.replaceAll(car_availability, car_releases);
        FileWriter writer = new FileWriter(filepath);
        writer.append(fileContents);
        writer.flush();

    }
}
