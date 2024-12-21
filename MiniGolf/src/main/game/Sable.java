package main.game;

import main.geometry.Point;

public final class Sable extends Obstacle implements EffetObstacle {  

    public Sable(Point position, double largeur, double hauteur) {
        super(position, largeur, hauteur, "Sable");
    }

    @Override
    public void appliquerEffet(Balle balle) {
        balle.calculerTrajectoire(balle.getAngle(), balle.getVitesse() * 0.9);
    }
}
