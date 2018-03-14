import java.util.ArrayList;

public class Pokedex extends PokemonAbstract implements PokemonInterface {
    public ArrayList<Object> pokemonInfo(Pokemon pokemon) {
        ArrayList<Object> xs = new ArrayList();
        xs.add(pokemon.getName());
        xs.add(pokemon.getHealth());
        xs.add(pokemon.getType());
        return xs;
    }
}