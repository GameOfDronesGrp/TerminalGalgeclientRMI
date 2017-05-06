package terminalclientrmi;

import java.io.Console;
import logik.GalgeServiceI;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 *
 * @author mohammad
 */
public class TerminalClientRMI {
    
    String brugernavn;
    String password;
          
    //main run
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        GalgeServiceI gs ;
        try{
            gs =(GalgeServiceI) Naming.lookup("rmi://localhost/Galgelogiktjeneste");
            System.out.println(gs.sayHello());
            new TerminalClientRMI().run(gs,scan);
        }catch(Exception e){
            System.out.println("kunne ikke finde serveren");
        }  
        
        scan.close();
    }
    
    //menuerne
    void run(GalgeServiceI game, Scanner scan) throws RemoteException{
        
        boolean loggedIn = false;
        int choice;
        while(true){
            if(!loggedIn){
                System.out.println("1. Log ind");
                System.out.println("2. Afslut");
                try{
                    choice = (int)Integer.parseInt(scan.nextLine());
                }catch(NumberFormatException e){
                    System.out.println("Input skal være tal\n");
                    System.out.println();
                    choice = 0;
                }
                if(choice == 1){
                        try{
                            Console console = System.console();
                            brugernavn = console.readLine("Username: ");
                            char[] password = console.readPassword("Password: ");
                            this.password = new String(password);
                        }catch(Exception e){
                            System.out.println("Skriv brugernavn");
                            brugernavn = scan.nextLine();
                            System.out.println("Skriv kode");
                            password = scan.nextLine();
                        }
                        
                        if(game.hentBruger(brugernavn, password)){
                            loggedIn = true;
                            System.out.printf("Hej %s. Du er nu logget ind!\n\n", game.getFornavn(brugernavn,password));
                        }else{
                            loggedIn = false;
                            System.out.println("\nBrugerinformation var forkert! Prøv igen\n\n");
                        }
                }else if(choice == 2){
                    System.out.println("Programmet lukker ned...");
                    break;
                }else {
                    System.out.println("Skriv 1 eller 2");
                }
                
            }else{
                System.out.println("1. Nyt spil");
                System.out.println("2. Log ud");
                
                try{
                    choice = (int) Integer.parseInt(scan.nextLine());
                }catch(NumberFormatException e){
                    System.out.println("Input skal være tal");
                    choice = 0;
                }
                
                switch(choice){
                    case 1: {
                        System.out.println("Point hentet fra server: "+game.getScore(brugernavn, password));
                        spil(game, scan);
                    }  break;
                    case 2: {
                        System.out.println("Du er nu logget ud");
                        loggedIn = false;
                        break;
                    }
                    default: System.out.println("Skriv 1 eller 2");
                }
            }
        }
    }
    
    void spil(GalgeServiceI game, Scanner scan) throws RemoteException {
        
        String gaet;
        final int liv = 7;
        game.nulstil(brugernavn, password);
        System.out.println("\n\n- Spillet er startet -");
        
        while(!game.erSpilletSlut(brugernavn,password)){
            scan.reset();
            System.out.println("Dit ord "+ game.getSynligtOrd(brugernavn,password));
            System.out.println("Brugte tegn: "+game.getBrugteBogstaver(brugernavn, password).toString());
            System.out.println("Dine liv " + (liv-game.getAntalForkerteBogstaver(brugernavn, password)));
            System.out.println("Gæt på et bogstav");
            gaet= scan.nextLine();
            
            try{
                if(Integer.parseInt(gaet) > -1){
                    System.out.println("Ingen cifrer tak :)\n");
                }
            }catch(NumberFormatException e){
                if(game.getBrugteBogstaver(brugernavn, password).contains(gaet)){
                    System.out.println("Du har allerede gættet på "+gaet+"\n");
                }else if(gaet.length()>1 || gaet.length() == 0){
                    System.out.println("Du må kun indtaste ét tegn.\n");
                }else{
                    game.gætBogstav(brugernavn, password,gaet);
                    if (!game.getOrdet(brugernavn, password).contains(gaet)) {
                        System.out.println("Du gættede forkert!\n");
                    }else{
                        System.out.println("Du gættede rigtigt\n");
                    }
                }
                if(game.erSpilletTabt(brugernavn, password)){
                    System.out.println("Du har tabt, Ordet var: " + game.getOrdet(brugernavn, password));
                }else if(game.erSpilletVundet(brugernavn, password)){
                    System.out.println("Du har vundet! Ordet var: "+game.getOrdet(brugernavn, password));

                }
            }
        }
    }

}
