# Pokemon-Center-Management-System

1. Program Overview: The system simulates a Pokémon Center where users can...
- View Pokémon stored in a box and in their current party.
- Filter Pokémon based on type, level, or generation.
- Add and remove Pokémon from their party.
- Display Pokémon stats and manage storage efficiently using linked lists.

2. Core Functionalities
- Main Menu: Players interact with the Pokémon Center through a menu with six options:
    - View Box: Display all Pokémon stored in the box, with optional filters.
    - View Party: Display the current party of up to six Pokémon.
    - Deposit Pokémon: Remove a Pokémon from the party and return it to the box.
    - Withdraw Pokémon: Add a Pokémon from the box to the party.
    - View Pokémon Stats: Display detailed stats for any Pokémon in the party or box.
    - Log Out: Exit the program.
- Managing Pokémon Party with Linked Lists: The system represents the player’s party using a custom linked list implementation (LL class) with nodes (LLNode class). This structure allows dynamic additions and removals, making it efficient to manage the party.
- Storing Pokémon Information: A 2D array (pokemonList) holds details for each Pokémon, including:
    - Dex Number, Name, Type, Level, HP, Attack, Defense, Special Attack, Special Defense, Speed, and Generation.
- Filtering Pokémon: Players can filter Pokémon by:
    - Type: Filter by primary and/or secondary types.
    - Level: Set lower and upper level bounds.
    - Generation: Filter by generations 1, 2, or 3.
- Adding and Removing Pokémon:
    - Players can remove Pokémon from their party and store them back in the box.
    - Pokémon can be withdrawn from the box and added to the party if space is available.
- Viewing Pokémon Stats: The program displays comprehensive stats for any Pokémon, whether in the party or the box, allowing players to make informed decisions about their team.

3. Key Features
- Optional Background Music: The program plays the Pokémon Center theme if the pokemonCenter.wav file is available.
- Dynamic Party Management: Using linked lists, the party can grow and shrink dynamically up to a maximum of six Pokémon.
- Filter and Search Options: Players can apply filters to locate specific Pokémon in the box.
- Detailed Stat Display: The system presents all key stats, replicating the information typically found in Pokémon games.

4. Technical Details
- Custom Linked List (LL) Implementation: The player’s party is represented using a linked list, with nodes containing Pokémon details.
- File I/O for Pokémon Data: The system reads Pokémon data from an external file (pokemonList.txt) and loads it into a 2D array.
- Recursive and Iterative Methods: The program uses recursion to traverse the linked list when displaying the party and searching for Pokémon.
- User Input Handling: The program validates inputs for menu navigation, Pokémon selection, and filter criteria to ensure proper functionality.
