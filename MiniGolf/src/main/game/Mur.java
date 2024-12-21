package main.game;

import main.geometry.Point;

public final class Mur extends Obstacle implements EffetObstacle {  
    private final String orientation; // "vertical" ou "horizontal"

    public Mur(Point position, double largeur, double hauteur, String orientation) {
        super(position, largeur, hauteur, "Mur");
        this.orientation = orientation;
    }

    @Override
    public void appliquerEffet(Balle balle) {
        if (orientation.equals("vertical")) {  // Utiliser equals pour la comparaison des chaînes
            balle.calculerTrajectoire(180 - balle.getAngle(), balle.getVitesse() * 0.8);
        } else if (orientation.equals("horizontal")) {
            balle.calculerTrajectoire(360 - balle.getAngle(), balle.getVitesse() * 0.8);
        }
    }
}
