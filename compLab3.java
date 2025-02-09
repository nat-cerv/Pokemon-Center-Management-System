import java.io.File;
import java.util.Scanner;
import java.util.Random;


//Optional libraries to play music
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;

public class compLab3 
{
    public static void main(String[] args) throws Exception
	{
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // You can ignore this section, it is just to play music, if you don't want music to be played, you can delete this part
        try
		{ // If the .wav file is found it will play the song while running the program
            File musicPath = new File("pokemonCenter.wav");
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.loop(Clip.LOOP_CONTINUOUSLY);   
            clip.start();
            System.out.println("Now Playing: \"Pokémon Center\" from Pokémon Colosseum\n");
        }
        catch(Exception e){} // If the file is not found, then there won't be any music while running the program.
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        
        Scanner scnr1 = new Scanner(System.in);
        int option = 0;
        LLNode tempHead;

        //This one is to have the whole Pokemon List:
        String[][] pokemonList = pokemonListToArray();
            
        //To have your first Pokemon (Squirtle) assigned to your party:         
        Random rnd = new Random();
        int poke = rnd.nextInt(386) + 1;
        String[] randPoke = pokemonList[poke];
        LLNode pok1 = new LLNode(randPoke);
        LL yourParty = new LL(pok1);
        pokemonList[poke] = new String[13];

        //This is for when the user writes the name of a Pokemon they want to remove or add:
        String name = "";

        //This variable and while loop are to repeat the process and keep seeing the menu:
        boolean repeatMenu = true;
        while (repeatMenu)
        {
            //Main Menu:
            System.out.println();
            System.out.println("------------------------------------------");
            System.out.println("Welcome to Pokemon Center");
            System.out.println("Please select one of the following options:");
            System.out.println("1) View box");
            System.out.println("2) View party");
            System.out.println("3) Deposit Pokemon (Remove from party)");
            System.out.println("4) Withdraw Pokemon (Add to party)");
            System.out.println("5) View Pokemon stats");
            System.out.println("6) Log out");
            System.out.println("------------------------------------------");

            //scnr1.nextLine();
            //The user selects an option:
            option = Integer.parseInt(scnr1.nextLine());            
            
            //If the input is wrong, then let user select again:
            while (option<1 || option>6)
            {
                System.out.print("Invalid, please select option 1 to 6:");
                option = Integer.parseInt(scnr1.nextLine());     
            }


            //
            //The program does whatever the user asks for:
            if (option == 1)
            {
                //This is to have a filter: (I haven't started with that yet)
                System.out.println("Choose a filter criteria (only one), or type anything else to see everyone in the box:");
                System.out.println("- Type");
                System.out.println("- Level");
                System.out.println("- Generation");

                //scnr1.nextLine(); //to make the scanner below work.
                String filter = scnr1.nextLine();

                
                System.out.println("------------------------------------------");
                displayPokemon(pokemonList, filter);

            }
            else if (option == 2)
            {
                System.out.println("Your current party is:");
                tempHead = yourParty.head;

                yourParty.displayParty(tempHead);

            }
            else if (option == 3)
            {
                System.out.println("Who do you want to remove? (write the name of the Pokemon)");
                System.out.println("Your current party is:");
                tempHead = yourParty.head;
                yourParty.displayParty(tempHead);
                
                System.out.println("---");
                name = scnr1.nextLine();

                if (yourParty.inParty(name))
                {
                    String[] remPok = yourParty.removeFromParty(name);
                    
                    if (remPok[0] != "")
                    {
                        returnPokemon(pokemonList, remPok);
                        System.out.println(name + " was returned to the box.");
                    }
                }
                else
                {
                    System.out.println(name + " is not in your party.");
                }
            }
            else if (option == 4)
            {
                System.out.println("Who do you want to add?");
                System.out.println("---");
                name = scnr1.nextLine();

                if (yourParty.inParty(name))
                {
                    System.out.println(name + " is already in your party.");
                }
                else
                {
                    //It will get the pokemon we want and then add it to the party:
                    String[] addPok = searchPokemon(pokemonList, name);
                    removeFromList(pokemonList, addPok); //This doesn't remove from the party, it removes it from the list
                    LLNode addedPokemon = new LLNode(addPok);

                    yourParty.addToParty(addedPokemon); //Now it adds it :)
                }
            }
            else if (option == 5)
            {
                System.out.println("What Pokemon do you want to see? (Can be either your party or your box)");
                System.out.println("---");
                name = scnr1.nextLine();

                String[] pokList = searchPokemon(pokemonList, name);

                //This is to get the stats of a pokemon that is in the party:
                if(yourParty.inParty(name))
                {
                    tempHead = yourParty.head;
                    String[] stats = yourParty.getFromParty(tempHead, name);

                    System.out.println();
                    System.out.println("Dex Number: " + stats[0] + " | Name: " + stats[1] + " | Type 1: " + stats[2] + " | Type 2: " + stats[3] + " | Level: " + stats[4] + " | Total: " + stats[5] + " | HP: " + stats[6] + " | Attack: " + stats[7] + " | Defense: " + stats[8] + " | Sp. Atk: " + stats[9] + " | Sp. Def: " + stats[10] + " | Speed: " + stats[11] + " | Generation: " + stats[12]);
                }
                else if (pokList != null) //This other one is for if the Pokemon is in the list
                {
                    System.out.println();
                    System.out.println("Dex Number: " + pokList[0] + " | Name: " + pokList[1] + " | Type 1: " + pokList[2] + " | Type 2: " + pokList[3] + " | Level: " + pokList[4] + " | Total: " + pokList[5] + " | HP: " + pokList[6] + " | Attack: " + pokList[7] + " | Defense: " + pokList[8] + " | Sp. Atk: " + pokList[9] + " | Sp. Def: " + pokList[10] + " | Speed: " + pokList[11] + " | Generation: " + pokList[12]);
                }

            }
            else if (option == 6)
            {
                System.out.println("See you later!");
                repeatMenu = false;
            }

        }

        //The program finished, goodbye!
        scnr1.close();
    }

    
    
    //This method just turns the whole Pokemon list into an array:
    public static String[][] pokemonListToArray() throws Exception
    {
        File pokemonList = new File("C:\\Users\\Natalia\\OneDrive\\Documentos\\.UTEP\\Computer Science\\Lab\\Comp Lab 3\\pokemonList.txt");
        Scanner scnrPok = new Scanner(pokemonList);

        int rows = 0;
        int colms = 0;

        if (scnrPok.hasNextLine()) 
        {
            String[] firstLine = scnrPok.nextLine().split(",");
            colms = firstLine.length;
            rows++;
        }
        
        while (scnrPok.hasNextLine()) 
        {
            scnrPok.nextLine();
            rows++;
        }
        scnrPok.close();
        
        String[][] pokemonArray = new String[rows][colms];
        scnrPok = new Scanner(pokemonList);
        int r = 0;

        while (scnrPok.hasNextLine()) 
        {
            String[] line = scnrPok.nextLine().split(",");

            for (int c=0 ; c<line.length; c++) 
            {
                pokemonArray[r][c] = line[c];
            }
            
            r++;
        }
        scnrPok.close();

        return pokemonArray;
    }


    /**
     * This method displays all the Pokemon, given the type of filter, if no valid filter is given, display all Pokemon
     * 
     * @param pokemonBox
     * @param filter
     * @return void
     */
    public static void displayPokemon(String[][] pokemonBox, String filter) throws Exception
    {
        Scanner scnr2 = new Scanner(System.in);
        
        //This one is just if the user selected the type as filter:
        if (filter.equals("Type") || filter.equals("type"))
        {
            //To ask user the first filter:
            System.out.println("Please type the first type you want to filter by:");
            String type1 = scnr2.nextLine();

            //To ask user the second filter:
            System.out.println("Please type the second type you want to filter by (if you don't want a second type, write -1):");
            String type2 = scnr2.nextLine();
            
            for (int i=1 ; i<pokemonBox.length ; i++) 
            {
            
                //In case the user didn't ask for a second type of filter:
                if (type2.equals("-1"))
                {
                    if (pokemonBox[i][0]==null)
                    { continue; }
                    else if (type1.equals(pokemonBox[i][2]))
                    {
                        System.out.println(pokemonBox[i][0] + ") " + pokemonBox[i][1] + "   Type: " + pokemonBox[i][2]);
                    }
                }

                //If the user did select for a second type of filter:
                if (type1.equals(pokemonBox[i][2]) && type2.equals(pokemonBox[i][3]))
                {
                    if (pokemonBox[i][0]==null)
                    { continue; }
                    else
                    {
                        System.out.println(pokemonBox[i][0] + ") " + pokemonBox[i][1] + "   Type1: " + pokemonBox[i][2] + " | Type2: " + pokemonBox[i][3]);
                    }
                }
                
            }
        }
        //This other one is if the user selected the level as filter:
        else if (filter.equals("Level") || filter.equals("level"))
        {
            //To ask user the first filter: (and then the wrong input)
            System.out.println("Please input the lower bound level you want to filter by (min 1, max 100):");
            int level1 = scnr2.nextInt();

            while (level1<=0 || level1>100)
            {
                System.out.println("Please input the lower bound level you want to filter by (min 1, max 100):");
                level1 = scnr2.nextInt();
            }


            //To ask user the second filter: (and then the wrong input)
            System.out.println("Please input the upper bound level you want to filter by (must be equal or greater than your lower bound, max 100):");
            int level2 = scnr2.nextInt();

            while (level2<=0 || level2>100)
            {
                System.out.println("Please input the upper bound level you want to filter by (must be equal or greater than your lower bound, max 100):");
                level2 = scnr2.nextInt();
            }
        

            //The variable that will convert the String to Int:
            int levelOfArray = 0;
            
            for (int i=1 ; i<pokemonBox.length ; i++) 
            {
                if (pokemonBox[i][0] == null)
                { continue; }
                else
                {
                    levelOfArray = Integer.parseInt(pokemonBox[i][4]);
                }

                if (level1<=levelOfArray && levelOfArray<=level2)
                {
                    System.out.println(pokemonBox[i][0] + ") " + pokemonBox[i][1] + "  (lvl. " + pokemonBox[i][4] + ")");
                }
            }

        }
        //This other one is if the user selected the generation as filter:
        else if (filter.equals("Generation") || filter.equals("generation"))
        {
            //To ask user 
            System.out.println("Please input the generation by which you want to filter by (only gens 1, 2, and 3 are available):");
            String gen = scnr2.nextLine();

            //If user selected the wrong generation:
            while (Integer.parseInt(gen)<1 || Integer.parseInt(gen)>3)
            {
                System.out.println("Please input the generation by which you want to filter by (only gens 1, 2, and 3 are available):");
                gen = scnr2.nextLine();
            }
            
            for (int i=1 ; i<pokemonBox.length ; i++) 
            {
                if (pokemonBox[i][0] == null)
                {}
                else if (gen.equals(pokemonBox[i][12])) //The 12 is to get the generation box of the array
                {
                    System.out.println(pokemonBox[i][0] + ") " + pokemonBox[i][1] + "  (lvl. " + pokemonBox[i][4] + ")");
                }
            }

        }
        //Lastly, this is for no filter:
        else
        {
            for (int i=1 ; i<pokemonBox.length ; i++) 
            {
                if (pokemonBox[i][0]==null)
                {}
                else
                {
                System.out.println(pokemonBox[i][0] + ") " + pokemonBox[i][1] + "  (lvl. " + pokemonBox[i][4] + ")");
                }
            }
        }

    } 


    //This method is to search for the name of the Pokemon we want to add to the party and have it in an array:
    public static String[] searchPokemon(String[][] pokemonBox, String pokemon) throws Exception
    {
        String[] returnPkmn;
        for (int i=1 ; i<pokemonBox.length ; i++) 
        {
            if(pokemonBox[i][0] == null){
                continue;
            }

            else if (pokemonBox[i][1].equals(pokemon))
            {
                returnPkmn =  pokemonBox[i];
                return returnPkmn;
            }
        }
        
        return new String[13];
    }


    //This method is to remove from the list the pokemon added to the party:
    public static void removeFromList(String[][] pokemonBox, String[] pokemon) throws Exception
    {
        int pok = Integer.parseInt(pokemon[0]); //This is the position of the Pokemon to remove
        pokemonBox[pok] = new String[13];
    }

    
    //This method is to return the removed Pokemon to the list:
    public static void returnPokemon(String[][] pokemonBox, String[] pokemon) throws Exception
    {
        int pok = Integer.parseInt(pokemon[0]); //This is the position of the Pokemon to return back
        pokemonBox[pok] = pokemon;

    }

}

/* Natalia Cervantes

   [CS1101] Comprehensive Lab 3
   This work is to be done individually. It is not permitted to. 
   share, reproduce, or alter any part of this assignment for any 
   purpose. Students are not permitted from sharing code, uploading 
   this assignment online in any form, or viewing/receiving/
   modifying code written from anyone else. This assignment is part. 
   of an academic course at The University of Texas at El Paso and 
   a grade will be assigned for the work produced individually by 
   the student.

*/
