import java.util.Scanner;

public class LL 
{
    LLNode head;
    LLNode tail;
    int size;

    public LL()
	{
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public LL(LLNode pkmn)
	{
        this.head = pkmn;
        this.tail = pkmn;
        this.size = 1;
    }

    
    /**
     * This method will recursively traverse the linked list and return the Pokémon we are searching for
     * if the Pokémon is not in the party, it will simply return an array with an empty string.
     * 
     * Must be done recursively
     *
     * @param currNode
     * @param getPokemon
     * @return the String array representing the pokemon we were looking for in the linked list
     *  or an empty String array if the Pokemon is not in the party
     */
    public String[] getFromParty(LLNode currNode, String getPokemon)
	{
        String[] here = new String[13];

        //If we find the Pokemon:
        if (currNode.pokemon[1].equals(getPokemon)) 
        {
            for (int i = 0; i < here.length; i++)
            {
                here[i] = currNode.pokemon[i];
            }
            
            return here;
        } 
        //This is to recursively find the Pokemon:
        return getFromParty(currNode.next, getPokemon);             
    }


    /**
     * This method will add a Pokémon to the party at the end and increase the size count of the linked list
     * Don't forget to update the size of the linked list when adding a Pokemon to the party
     * @param pkmn
     * @return void
     */
    public void addToParty(LLNode pkmn)
	{
        //This is to see if the party is complete:
        if (size == 6)
        {
            System.out.println("The party is full!");
        }
        else 
        {
            // if our party is empty:
            if (head == null) 
            {
                head = pkmn;
                tail = pkmn;
                size++;
            } 
            else // to add a Pokemon to the party
            {
                tail.next = pkmn;
                tail = pkmn;
                size++;
                System.out.println("This pokemon was added to the party!");
            }
        }

    } 


    /**
     * This method will remove a Pokémon from the party, regardless of its position in the list, and decrease the size count of the linked list
     * Don't forget to update the size of the linked list when removing a Pokemon
     * @param pkmnName
     * @return the String array representing the Pokemon being removed from the party (linked list)
     */
    public String[] removeFromParty(String pkmnName)
	{
        
        LLNode currNode = this.head;
        LLNode prevNode = null;

        //This is to not let the user remove the only Pokemon from the party:
        if (size == 1)
        {
            System.out.println("You must have at least one Pokemon in your party.");
            String [] noPok = new String[1];
            noPok[0] = "";
            return noPok;
        }
        else
        {
            // This is to iterate over each node in the linked list:
            while (currNode != null) 
            {
                // To remove the Pokemon from the linked list
                if (pkmnName.equals(this.head.pokemon[1]))  // quitando head
                {
                    // If the current node is the head, update head to the next node
                    this.head = this.head.next;
                    break;
                } 
                else if(currNode.pokemon[1].equals(pkmnName)) // quitandolo en medio
                {
                    // Otherwise, update the previous node's next reference to skip over the current node
                    prevNode.next = currNode.next;
                    break;
                }
                
                // If the current node is also the tail, update tail to the previous node
                else if (currNode.pokemon[1].equals(this.tail.pokemon[1])) { // quitandolo en la cabeza
                    tail = prevNode;
                    break;
                }


                // Update both nodes:
                prevNode = currNode;
                currNode = currNode.next;
            }
            
            this.size--;
            return currNode.pokemon;
        }
    }

 
    /**
     * This method will traverse through the linked list recursively and display all of the Pokémon's name 
     * and their level in it in order from first to last
     * 
     * Must be done recursively
     * 
     * @param headNode
     * @return void, the method will only print the Pokemon names and levels in order
     */
    public void displayParty(LLNode headNode)
	{

        if (headNode != null) 
        {
            System.out.println(headNode.pokemon[1] + " (lvl." + headNode.pokemon[4] + ")" );
            displayParty(headNode.next); //As you can imagine, this is to make it recursive and
        }
    }


    /**
     * This method checks if a given Pokémon is in the party or not
     * 
     * @param pokemonName
     * @return boolean representing whether the input Pokemon is in the party or not
     */
    public boolean inParty(String pokemonName)
	{
        LLNode currentPokemon = this.head;

        while (currentPokemon != null) 
        {
            if (currentPokemon.pokemon[1].equals(pokemonName)) 
            {
                return true;
            }

            currentPokemon = currentPokemon.next;
        }
        
        return false; // If we traverse the entire list without finding the Pokémon, return false
        
    }

}
