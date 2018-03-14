public abstract class PokemonAbstract implements PokemonInterface {
    public Pokemon createPokemon(String name, int health, String type) {
        Pokemon pokemon = new Pokemon(name, health, type);
        return pokemon;
    }
    public void attackPokemon(Pokemon pokemon) {
        int health = pokemon.getHealth();
        pokemon.setHealth(health - 10);
    }
}