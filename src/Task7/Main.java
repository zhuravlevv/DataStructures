package Task7;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ChooseYourAdventure chooseYourAdventure = new ChooseYourAdventure();
        creator(chooseYourAdventure);
        play(chooseYourAdventure);
    }

    public static void menu(){
        System.out.println("1. Show elements");
        System.out.println("2. Add element");
        System.out.println("3. Update element");
        System.out.println("4. Remove element");
        System.out.println("5. Add link");
        System.out.println("6. Remove link");
        System.out.println("7. Finish creation");
    }

    public static void play(ChooseYourAdventure chooseYourAdventure){
        System.out.println("\n\n\nBegin\n");
        chooseYourAdventure.play();
    }

    public static void creator(ChooseYourAdventure chooseYourAdventure){
        chooseYourAdventure.init();
        int choice = 0;
        while(choice!=7){
            menu();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter: ");
            choice = scanner.nextInt();
            switch (choice){
                case 1:{
                    chooseYourAdventure.printNodes();
                    break;
                }
                case 2:{
                    chooseYourAdventure.addElement();
                    break;
                }
                case 3:{
                    chooseYourAdventure.update();
                    break;
                }
                case 4:{
                    chooseYourAdventure.removeElement();
                    break;
                }
                case 5:{
                    chooseYourAdventure.addLink();
                    break;
                }
                case 6:{
                    chooseYourAdventure.removeLink();
                    break;
                }
                default: {
                    break;
                }
            }
        }
    }
}
