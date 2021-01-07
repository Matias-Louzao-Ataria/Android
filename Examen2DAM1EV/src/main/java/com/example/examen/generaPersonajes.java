package com.example.examen;

import java.util.ArrayList;

public class generaPersonajes{
    public static ArrayList<Personaje> generarPersonajes(){
        // parametros en el contructor: nombre, valoracion, imagen, favorito, actorOriginal, imagenActor, pelicula
        ArrayList<Personaje> datos = new ArrayList<Personaje>();

        datos.add(new Personaje("Dracula", 5f, R.drawable.dracula, true,"Adam Sandler",R.drawable.adam_sandler,"Hotel transilvania 1"));
        datos.get(0).setDescripcion("Count Drac Dracula  is Mavis' widowed overprotective father and is away on \"official vampire business\"[9] at the Vampire Council. He is replaced by his elder sister Lydia as the head staff of the hotel until his return. His cape is tinted purple instead of blood-red");
        datos.add(new Personaje("Mavis", 4.5f, R.drawable.mavis, false,"Selena Gomez",R.drawable.selena_gomez,"Hotel transilvania 1"));
        datos.get(1).setDescripcion("Mavis Dracula is the late Martha and Dracula's 114-year-old daughter who wants to have fun with her best friends. Her boundless curiosity and carefree spirit frequently leads her into trouble with her aunt Lydia. She turns 115 in the Season 1 finale and earns her own family cape.");
        datos.add(new Personaje("Johnny", 3.5f, R.drawable.johnny, false, "Andy Samberg",R.drawable.andy_samberg,"Hotel transilvania 1"));
        datos.get(2).setDescripcion("Is Mavis' husband, Dracula's son-in-law, and Dennis' human father.");
        datos.add(new Personaje("Blobby", 2f, R.drawable.blobby, false, "No hay",R.drawable.sin_imagen,"Hotel transilvania 1"));
        datos.get(3).setDescripcion("A green blob monster");
        datos.add(new Personaje("Dennis", 4.5f, R.drawable.dennis, false, "Asher Blinkoff",R.drawable.asher_blinkoff,"Hotel transilvania 2"));
        datos.get(4).setDescripcion("Mavis and Johnny's five-year-old human/vampire hybrid son, Dracula's grandson, and Winnie's love interest. He has his mother's blue eyes and extraordinarily strong vampire abilities, and his father's orange hair and freckles.");
        datos.add(new Personaje("Ericka van helsing", 2f, R.drawable.ericka_van_helsing, false, "Kathryn Hahn",R.drawable.kathryn_hahn,"Hotel transilvania 3"));
        datos.get(5).setDescripcion("The captain of the Legacy cruise, Drac's love interest, and the great granddaughter of Abraham Van Helsing.");
        datos.add(new Personaje("Frank", 4f, R.drawable.frank, false, "Kevin James",R.drawable.kevin_james,"Hotel transilvania 1"));
        datos.get(6).setDescripcion("Dracula's best friend who acts as an uncle to Mavis and mostly hangs out with Murray. Is the father of Hank N. Stein");
        datos.add(new Personaje("Griffin", 4.5f, R.drawable.griffin, false,"David Spade",R.drawable.david_spade,"Hotel transilvania 1"));
        datos.get(7).setDescripcion("a ghost and Dracula's best friend.");
        datos.add(new Personaje("Kraken", 5f, R.drawable.kraken, true, "Joe Jonas",R.drawable.joe_jonas,"Hotel transilvania 3"));
        datos.get(8).setDescripcion("A giant music-loving sea monster that lives near Atlantis");
        datos.add(new Personaje("Murray", 4f, R.drawable.murray, false, "Keegan-Michael Key",R.drawable.keegan_michael_key,"Hotel transilvania 1"));
        datos.get(9).setDescripcion("A short, fat mummy who is Dracula's best friend and mostly hangs out with Frankenstein.");
        datos.add(new Personaje("Tinkles", 2.2f, R.drawable.tinkles, false, "No hay",R.drawable.sin_imagen,"Hotel transilvania 2"));
        datos.get(10).setDescripcion("Dennis' giant pet puppy");
        datos.add(new Personaje( "Vlad", 2.5f, R.drawable.vlad, false, "Mel Brooks",R.drawable.mel_brooks,"Hotel transilvania 3"));
        datos.get(11).setDescripcion("an ancient, more experienced and traditional vampire who is Drac's father, the late Martha's father-in-law, Marvis' paternal grandfather, Johnathon's grandfather-in-law, and Dennis' maternal great-grandfather.");
        datos.add(new Personaje("Wanda", 2.3f, R.drawable.wanda, false, "Molly Shannon",R.drawable.molly_shannon,"Hotel transilvania 1"));
        datos.get(12).setDescripcion("A female werewolf, Wayne's heavily pregnant wife and Eunice's best friend");
        datos.add(new Personaje("Wayne", 3f, R.drawable.wayne, false, "Steve Buscemi",R.drawable.steve_buscemi,"Hotel transilvania 1"));
        datos.get(13).setDescripcion("A male werewolf who is also Dracula's best friend and Wanda's husband");
        datos.add(new Personaje( "Winnie", 4.5f, R.drawable.winnie, false, "Sadie Sandler",R.drawable.sadie_sandler,"Hotel transilvania 3"));
        datos.get(14).setDescripcion("The eldest werewolf daughter of Wayne and Wanda. She is Dennis' best friend and has a mutual crush on him");
        datos.add(new Personaje( "Eunice", 4.0f, R.drawable.eunice, false, "Fran Drescher",R.drawable.fran_drescher,"Hotel transilvania 1"));
        datos.get(15).setDescripcion("Frankenstein's wife and Wanda's best friend. Is the mother of Hank N. Stein.");
        return datos;
    }
}