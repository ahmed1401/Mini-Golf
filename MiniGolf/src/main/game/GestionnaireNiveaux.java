package main.game;

import main.geometry.Point;
import java.util.List;
import java.util.ArrayList;


public class GestionnaireNiveaux {
    private final List<Niveau> niveaux;
    private int niveauActuel;

    public GestionnaireNiveaux() {
        this.niveaux = new ArrayList<>();
        this.niveauActuel = 0;
        initialiserNiveaux(); 
    }
    
    public List<Niveau> getNiveaux(){
        return niveaux;
    }
    public void ajouterNiveau(Niveau niveau) {
        niveaux.add(niveau);
    }

    public Niveau getNiveauActuel() {
        return niveaux.get(niveauActuel);
    }
    public int getNiveauActuelNumber(){
        return niveauActuel;
    }

    public void setNiveauActuel (int n){
        this.niveauActuel = n;
    }

    public boolean passerAuNiveauSuivant() {
        if (niveauActuel < niveaux.size() - 1) {
            niveauActuel++;
            return true;
        }
        return false; 
    }

    public void reinitialiser() {
        niveauActuel = 0;
    }

    private void initialiserNiveaux() {

        Niveau niveau1 = new Niveau(
            new Point(100, 100),  // Position initiale de la balle
            new Point(700, 200),  // Position du trou
            List.of(               // Liste des obstacles
                new Mur(new Point(0, 0), 800.0, 10.0, "horizontal"), // Mur haut
                new Mur(new Point(0, 590), 800.0, 10.0, "horizontal"), // Mur bas
                new Mur(new Point(0, 0), 10.0, 600.0, "vertical"),  // Mur gauche
                new Mur(new Point(790, 0), 10.0, 600.0, "vertical"), // Mur droit
                new Sable(new Point(350, 400), 100, 190),
                new Mur(new Point(400, 000), 10.0, 500.0, "vertical"), // Petit mur au centre
                new Tunnel(new Point(200, 150), new Point(100, 400), 50.0, 200.0), // Tunnel 1
                new Tunnel(new Point(300, 300), new Point(50,400), 60.0, 120.0)
                  
            ),
            0
        );
        
ajouterNiveau(niveau1);


    

        Niveau niveau2 = new Niveau(
            new Point(150, 150),  // Position initiale de la balle
            new Point(700, 120),  // Position du trou
            List.of(               // Liste des obstacles
                new Mur(new Point(0, 0), 800.0, 10.0, "horizontal"),  // Mur haut
                new Mur(new Point(0, 590), 800.0, 10.0, "horizontal"), // Mur bas
                new Mur(new Point(0, 0), 10.0, 600.0, "vertical"),     // Mur gauche
                new Mur(new Point(790, 0), 10.0, 600.0, "vertical"),   // Mur droit
                new Mur(new Point(200, 200), 100.0, 10.0, "horizontal"), // Small random wall 1
                new Mur(new Point(500, 250), 200.0, 10.0, "horizontal"),  // Small random wall 2
                new Mur(new Point(400, 0), 10.0, 400.0, "vertical"),
                new Mur(new Point(550, 450), 500.0, 10.0, "horizontal"),  // Small random wall 3
                new Tunnel(new Point(200, 250), new Point(50,400), 60.0, 120.0),
                new Sable(new Point(350, 300), 200, 200)
            ),
            1
        );
        ajouterNiveau(niveau2);
        
        // Niveau 3: Complex level with walls, sand, and more challenges
        Niveau niveau3 = new Niveau(
            new Point(100, 100),  // Position initiale de la balle (new position)
            new Point(700, 375),  // Position du trou
            List.of(               // Liste des obstacles
                new Mur(new Point(0, 0), 800.0, 10.0, "horizontal"),   // Mur haut
                new Mur(new Point(0, 590), 800.0, 10.0, "horizontal"),  // Mur bas
                new Mur(new Point(0, 0), 10.0, 600.0, "vertical"),      // Mur gauche
                new Mur(new Point(790, 0), 10.0, 600.0, "vertical"),    // Mur droit

                // Adding challenging walls that form barriers
                new Mur(new Point(300, 200), 150.0, 10.0, "horizontal"), // Horizontal wall near the center
                new Mur(new Point(500, 300), 150.0, 10.0, "horizontal"), // Another horizontal wall crossing the center
                new Mur(new Point(200, 400), 10.0, 300.0, "vertical"),   // Vertical wall blocking path near ball
                new Mur(new Point(600, 000), 10.0, 400.0, "vertical"),   // Vertical wall near the hole

                // Adding sand in an annoying place that slows the ball
                new Sable(new Point(50, 350), 300, 50),                 // Sable in the middle, annoying obstacle
                new Sable(new Point(550, 250), 200, 50),                 // Sable near the hole, creating a challenge

                // A few more small random walls
                new Mur(new Point(400, 400), 300, 10.0, "horizontal"),  // Horizontal wall blocking path near the hole
                new Mur(new Point(450, 100), 10.0, 400.0, "vertical")    // Small vertical wall near the top-right corner
                 ),
                2
                );
                ajouterNiveau(niveau3);



                Niveau niveau4 = new Niveau(
                    new Point(50, 50),  // Position initiale de la balle (dans un coin, difficile d'y accéder)
                    new Point(750, 550), // Position du trou, plus éloignée pour augmenter la difficulté
                    List.of(             // Liste des obstacles
                        // Murs de base (haut, bas, gauche, droite)
                        new Mur(new Point(0, 0), 800.0, 10.0, "horizontal"),   // Mur haut
                        new Mur(new Point(0, 590), 800.0, 10.0, "horizontal"),  // Mur bas
                        new Mur(new Point(0, 0), 10.0, 600.0, "vertical"),      // Mur gauche
                        new Mur(new Point(790, 0), 10.0, 600.0, "vertical"),    // Mur droit
                
                        // Murs supplémentaires formant des obstacles complexes
                        new Mur(new Point(200, 100), 100.0, 10.0, "horizontal"), // Mur horizontal près de la balle
                        new Mur(new Point(600, 200), 100.0, 10.0, "horizontal"), // Mur horizontal près du trou
                        new Mur(new Point(150, 300), 10.0, 400.0, "vertical"),   // Mur vertical bloquant la route à gauche
                        new Mur(new Point(650, 400), 10.0, 300.0, "vertical"),   // Mur vertical près du trou
                
                        // Ajouter des murs formant un labyrinthe complexe
                        new Mur(new Point(300, 150), 200.0, 10.0, "horizontal"),   // Petit mur horizontal
                        new Mur(new Point(400, 250), 100.0, 10.0, "horizontal"),   // Petit mur horizontal supplémentaire
                        new Mur(new Point(500, 350), 50.0, 10.0, "horizontal"),   // Mur horizontal près du centre
                
                        // Sable dans des endroits stratégiques pour ralentir la balle
                        new Sable(new Point(350, 200), 300, 50),                  // Sable proche du centre
                        new Sable(new Point(200, 300), 400, 50),                  // Sable juste avant le trou
                        new Sable(new Point(600, 100), 300, 50),                  // Sable à proximité du trou pour créer un challenge supplémentaire
                
                        // Ajouter quelques murs courts pour des obstacles supplémentaires
                        new Mur(new Point(250, 400), 100.0, 10.0, "horizontal"),   // Mur horizontal près de la partie inférieure
                        new Mur(new Point(550, 100), 10.0, 100.0, "vertical"),    // Mur vertical près du haut à droite
                        new Mur(new Point(450, 500), 10.0, 100.0, "vertical")      // Petit mur vertical près du bas droit
                    ),
                    3  // Nombre de vies limitées à 3 pour augmenter la difficulté
                );
                
                ajouterNiveau(niveau4);


                Niveau niveau5 = new Niveau(
    new Point(50, 50),  // Position initiale de la balle (coin de l'écran)
    new Point(750, 550), // Position du trou, très loin et difficile d'accès
    List.of(             // Liste des obstacles
        // Murs de base (haut, bas, gauche, droite)
        new Mur(new Point(0, 0), 800.0, 10.0, "horizontal"),   // Mur haut
        new Mur(new Point(0, 590), 800.0, 10.0, "horizontal"),  // Mur bas
        new Mur(new Point(0, 0), 10.0, 600.0, "vertical"),      // Mur gauche
        new Mur(new Point(790, 0), 10.0, 600.0, "vertical"),    // Mur droit

        // Murs formant un labyrinthe
        new Mur(new Point(000, 100), 150.0, 10.0, "horizontal"), // Mur horizontal en haut à gauche
        new Mur(new Point(200, 150), 10.0, 100.0, "vertical"),   // Mur vertical à gauche
        new Mur(new Point(300, 100), 10.0, 100.0, "vertical"),   // Mur vertical au centre
        new Mur(new Point(400, 200), 100.0, 10.0, "horizontal"),  // Mur horizontal au centre
        new Mur(new Point(500, 150), 10.0, 100.0, "vertical"),   // Mur vertical à droite
        new Mur(new Point(600, 250), 100.0, 10.0, "horizontal"),  // Mur horizontal à droite
        new Mur(new Point(700, 300), 10.0, 100.0, "vertical"),   // Mur vertical à droite

        // Murs fermant des passages et créant des bifurcations
        new Mur(new Point(200, 300), 10.0, 100.0, "vertical"),  // Mur vertical fermant un passage
        new Mur(new Point(300, 400), 10.0, 100.0, "vertical"),  // Mur vertical bloquant l'accès
        new Mur(new Point(400, 450), 100.0, 10.0, "horizontal"), // Mur horizontal près du bas
        new Mur(new Point(500, 500), 10.0, 100.0, "vertical"),  // Mur vertical au bas à droite
        new Mur(new Point(600, 450), 100.0, 10.0, "horizontal"), // Mur horizontal au bas à droite
        new Mur(new Point(700, 450), 10.0, 100.0, "vertical"),  // Mur vertical à l'extrême droite
        new Mur(new Point(50, 500), 150.0, 10.0, "horizontal"), // Mur horizontal près du bas

        // Zones de sable massives ralentissant la balle
        new Sable(new Point(200, 200), 150, 100),                 // Sable dans la partie centrale du labyrinthe
        new Sable(new Point(400, 300), 150, 100),                 // Sable créant des embûches vers la fin
        new Sable(new Point(600, 400), 150, 100),                 // Sable près de l'entrée du trou
        new Sable(new Point(500, 100), 100, 50),                  // Sable ralentissant près du mur central
        new Sable(new Point(100, 400), 100, 50),                  // Sable gênant au bas à gauche

        // Ajouter des murs supplémentaires pour créer plus de bifurcations
        new Mur(new Point(250, 300), 100.0, 10.0, "horizontal"),  // Mur horizontal au centre
        new Mur(new Point(150, 350), 10.0, 100.0, "vertical"),   // Mur vertical entre les passages
        new Mur(new Point(350, 150), 10.0, 100.0, "vertical"),   // Mur vertical dans la partie centrale
        new Mur(new Point(550, 00), 10.0, 400.0, "vertical"),    // Mur vertical à droite
        new Mur(new Point(650, 200), 10.0, 400.0, "vertical"),   // Mur vertical empêchant d'accéder au trou

        // Ajouter des tunnels
        new Tunnel(new Point(100, 150), new Point(600, 500), 100.0, 40.0), // Tunnel 1
        new Tunnel(new Point(400, 100), new Point(700, 400), 100.0, 60.0), // Tunnel 2
        new Tunnel(new Point(600, 300), new Point(200, 450), 100.0, 50.0)  // Tunnel 3
    ),
    3  // Nombre de vies limitées à 3 pour augmenter la difficulté
);

ajouterNiveau(niveau5);

                        
        
        }
}
