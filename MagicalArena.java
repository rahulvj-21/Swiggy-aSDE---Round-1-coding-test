import java.util.*;

class Player {
    int health;
    int strength;
    int attack;
    Random rand;

    public Player(int health, int strength, int attack) {
        this.health = health;
        this.strength = strength;
        this.attack = attack;
        this.rand = new Random();
    }
    // Attack another player
    public void attack(Player opponent) {
        // Roll attacking die (1-6)            
        int attackRoll = rand.nextInt(6) + 1;
        int attackDamage = attack * attackRoll;
        System.out.printf("Attacker roll: %d, attack damage: %d\n", attackRoll, attackDamage);
        opponent.defend(attackDamage);
    }
    // Defend against attack damage
    public void defend(int attackDamage) {
        // Roll defending die (1-6)
        int defendRoll = rand.nextInt(6) + 1;
        int defense = strength * defendRoll;
        System.out.printf("Defender roll: %d, defense: %d\n", defendRoll, defense);
        
        // Calculate damage
        int damage = attackDamage - defense;
        if (damage > 0) {
            health -= damage;
            System.out.printf("Damage taken: %d, remaining health: %d\n", damage, health);
        } else {
            System.out.println("No damage taken.");
        }
    }
    // Check if player is alive
    public boolean isAlive() {
        return health > 0;
    }
    // Getter for health
    public int getHealth() {
        return health;
    }

    // Getter for attack power
    public int getAttack() {
        return attack;
    }
    
    // Getter for strength
    public int getStrength() {
        return strength;
    }
}
class Game {
    private final Player player1;
    private final Player player2;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }
    // Run the fight between two players
    public void fight() {
        System.out.println("The battle begins!");
        
        Player currentPlayer = null;
        Player opponent = null;

        // Determine the initial order of attack based on health
        if (player1.getHealth() <= player2.getHealth()) {
            currentPlayer = player1;
            opponent = player2;
        } else {
            currentPlayer = player2;
            opponent = player1;
        }

        // Fight loop
        while (player1.isAlive() && player2.isAlive()) {
            currentPlayer.attack(opponent);
            
            // Swap turns
            Player temp = currentPlayer;
            currentPlayer = opponent;
            opponent = temp;
        }

        // Determine the winner
        if (player1.isAlive()) {
            System.out.println("Player 1 wins!");
        } else {
            System.out.println("Player 2 wins!");
        }
    }
}
class MagicalArena{
    public static void main(String[] args){
        // Create players with given attributes
        Player player1 = new Player(50, 5, 10);
        Player player2 = new Player(100, 10, 5);
        
        // Create a game
        Game game = new Game(player1, player2);

        // Start the fight
        game.fight();
    }
}