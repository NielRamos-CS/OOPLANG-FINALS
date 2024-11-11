import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/////////////////////////////////////////////////////////PERSON CLASS///////////////////////////////////////////////

class Person
{
	protected String agentId = "20860132";
	protected String password = "20020729" ;
	protected String address;

	public Person(String agentId,String password, String address)
	{
		this.agentId = agentId;
		this.password = password;
		this.address = address;
	}
}

class Agent extends Person
{
	public Agent(String agentId, String password, String address) throws java.io.IOException
	{
        super(agentId, password, address);
    }

    public static void agent_menu() throws IOException
    {
        try(Scanner input = new Scanner(System.in))
        {
        System.out.println("[1]ADD CAR");
        System.out.println("[2]CHECK RECORDS");
        int choice2 = input.nextInt();

        if(choice2 == 1)
        {
            Vehicle.add_car();
        }

        else if(choice2 == 2)
        {
            Vehicle.check_records();
        }
        }

    }

}

class Customer extends Person
{
	private String customerId;

    public Customer(String agentId, String password, String address, String customerId)
	{
	super(agentId, password, address);
	this.customerId = customerId;
	}

    public static void customer_menu() throws IOException
    {
        Scanner input = new Scanner(System.in);
        System.out.println("[1]RENT CAR");
        int num = input.nextInt();

        if (num == 1)
        {
            Vehicle.rent_car();
        }
    }
}

/////////////////////////////////////////////////////////END OF PERSON CLASS///////////////////////////////////////////////



//////////////////////////////////////////////////////////////MAIN CLASS////////////////////////////////////////////////////

public class Vehicle
{
    public static void main(String[] args) throws IOException
    {
		Agent agent = new Agent("20860132", "h208f32", "San luis");
        Scanner input = new Scanner(System.in);
        System.out.println("[1]AGENT");
        System.out.println("[2]CUSTOMER");
        int choice = input.nextInt();

        if(choice == 1)
        {
            System.out.println("[LOGIN]");
            System.out.print("ENTER AGENT ID:");
            int id = input.nextInt();
            System.out.print("ENTER PASSWORD:");
            int pass = input.nextInt();
            if(id == 20860132 && pass == 20020729)
            {
                Agent.agent_menu();
            }
        }
        if(choice == 2)
        {
            System.out.println("[LOGIN]");
            System.out.print("ENTER CUSTOMER ID:");
            int id = input.nextInt();
            System.out.print("ENTER PASSWORD:");
            int pass = input.nextInt();
            if(id == 20860132 && pass == 20020729)
            {
                Customer.customer_menu();
            }
        }

    }
/////////////////////////////////////////////////////////END OF MAIN CLASS///////////////////////////////////////////////



/////////////////////////////////////////////////////////AGENTS MENU ALGORITHM///////////////////////////////////////////////

    public static void add_car() throws IOException
    {

            byte car_availability = 127;

            Scanner input = new Scanner(System.in);
            System.out.println("Enter the car's plate number:");
            String car_plate = input.nextLine();

            System.out.println("Enter the car's model: ");
            String car_model = input.nextLine();

            System.out.println("Enter the car's color:");
            String car_color = input.nextLine();

            System.out.println("Enter the car's available seats: ");
            int car_seats = input.nextInt();


            if (car_seats == 4)
            {
                String car_type = "Seddan";
                add_algo(car_type, car_model, car_plate, car_color, car_seats, car_availability);
            }

            else if (car_seats > 4)
            {
                String car_type = "SUV";
                add_algo(car_type, car_model, car_plate, car_color, car_seats, car_availability);
            }

            else if (car_seats < 3)
            {
                String car_type = "Sports_Car";
                add_algo(car_type, car_model, car_plate, car_color, car_seats, car_availability);
            }

            System.out.println("[1]ADD ANOTHER CAR\n[2]BACK TO MAIN MENU");
            int choice = input.nextInt();

            if (choice == 1)
            {
                add_car();
            }
            else if (choice == 2)
            {
                Agent.agent_menu();
            }

    }

    public static void check_records() throws IOException
    {
        Scanner input = new Scanner(System.in);
        System.out.println("CHECK RECORDS FOR:\n[1]Seddan\n[2]SUV\n[3]Sports Car");
        int num = input.nextInt();

        if (num  == 1 )
        {
            open_folder("Seddan");
        }
        else if (num == 2)
        {
            open_folder("SUV");
        }
        else if (num == 3)
        {
            open_folder("Sports_Car");
        }
    }


    public static void open_folder(String car_type) throws IOException
    {
        File path = new File("Records\\Car\\" + car_type );
        String cars[] = path.list();
        System.out.println(car_type + " record list:");
        for (int i = 0; i < cars.length; i++)
        {
            System.out.println(cars[i]);
        }
        System.out.println("[1]Check other recods\n[2]Back to menu");

        Scanner input = new Scanner(System.in);
        int num1 = input.nextInt();

        if (num1 == 1)
        {
            check_records();
        }
        else if (num1 == 2)
        {
            Agent.agent_menu();
        }
    }

    public static void add_algo(String car_type,String car_model,String car_plate,String car_color,int car_seats,int car_availability)
    {
        try{
            BufferedWriter seddan = new BufferedWriter(new FileWriter("Records\\Car\\" + car_type + "\\"+ car_model + car_plate + ".txt"));
                seddan.write("Model: " + car_model + "\n");
                seddan.write("Plate Number: " + car_plate + "\n");
                seddan.write("Color: " + car_color + "\n");
                seddan.write("Seats: " + car_seats + "\n");
                seddan.write(car_availability + "\n");
                seddan.close();
            }catch(Exception ex)
            {
                return;
            }
    }
/////////////////////////////////////////////////////////END OF AGENTS MENU ALGORITHM///////////////////////////////////////////////

/////////////////////////////////////////////////////////CUSTOMER RENT ALGORITHM///////////////////////////////////////////////

    public static void rent_car() throws IOException
    {
        Scanner input = new Scanner(System.in);
        System.out.println("[1]RENT A SEDDAN\n[2]RENT A SUV\n[3]SPORTS CAR");
        int num = input.nextInt();

        if (num == 1)
        {
            check_available("Seddan");
        }
        else if (num == 2)
        {
            check_available("SUV");
        }
        else if (num == 3)
        {
            check_available("Sports_Car");
        }
    }

/////////////////////////////////////////////////////////END OF CUSTOMER RENT ALGORITHM///////////////////////////////////////////////



/////////////////////////////////////////////////////////CUSTOMER RENTS A CAR///////////////////////////////////////////////

    public static void check_available(String car_type) throws IOException
    {
        Scanner input = new Scanner(System.in);
        File path = new File("Records\\Car\\" + car_type );
        String cars[] = path.list();
        List<String> available_cars = new ArrayList<>();

        for (int i = 0; i < cars.length; i++)
        {
            String file_name = cars[i];
            String checking = Files.readAllLines(Paths.get("Records\\Car\\" + car_type + "\\" + file_name)).get(4);
            int number  = Integer.parseInt(checking);

            if (number == 127)
            {
                available_cars.add(file_name);
            }

        }

        System.out.println("Here are the available " + car_type + ":\n" + available_cars + "\n");

        System.out.println("Which car would you like to rent?: ");
        int car_choice = input.nextInt();
        String for_edit = available_cars.get(car_choice - 1);

        System.out.println("Enter the date today: ");
        System.out.print("MM: ");
        int mm = input.nextInt();
        System.out.print("DD: ");
        int dd = input.nextInt();
        System.out.print("YYYY: ");
        int yyyy= input.nextInt();
        System.out.println("Enter how many days you would like to rent: ");
        int rent_days = input.nextInt();
        int total_days = input.nextInt();
        String car_release = mm + "/" + total_days + "/" + yyyy;
        String customer_edit = "127";

        edit_car_records(car_type, for_edit, car_release, customer_edit, total_days);
    }

    public static void edit_car_records(String car_type, String for_edit, String car_releases, String car_availability, int total_days) throws IOException
    {
        Scanner input = new Scanner(System.in);
        String filepath = ("Records\\Car\\" + car_type + "\\" + for_edit);
        Scanner sc = new Scanner(new File(filepath));
        StringBuffer buffer = new StringBuffer();

        while (sc.hasNextLine())
        {
            buffer.append(sc.nextLine()+System.lineSeparator());
        }
        String fileContents = buffer.toString();
        sc.close();
        fileContents = fileContents.replaceAll(car_availability, car_releases);
        FileWriter writer = new FileWriter(filepath);
        writer.append(fileContents);
        writer.flush();

        pay_rent(car_type, total_days);

    }
/////////////////////////////////////////////////////////END OF CUSTOMER RENTS A CAR///////////////////////////////////////////////

/////////////////////////////////////////////////////////CUSTOMER PAYS RENT///////////////////////////////////////////////
    public static void pay_rent(String car_type, int total_days) throws IOException
    {
        Scanner input = new Scanner(System.in);

        int type_value, days_value;
        if (car_type == "Seddan")
        {
            type_value = 3000;
            days_value = total_days * type_value;
        }
        else if (car_type == "SUV")
        {
            type_value = 6000;
            days_value = total_days * type_value;
        }
        else if (car_type == "Sports_Car")
        {
            type_value = 10000;
            days_value = total_days * type_value;
        }

        System.out.println("Total amount to pay is : Php" + total_days);
        System.out.println("[1]RENT ANOTHER CAR\n[2]EXIT");
        int choice = input.nextInt();

        if (choice == 1)
        {
            rent_car();
        }
        else if (choice == 2)
        {
            System.exit(0);
        }

    }

}



