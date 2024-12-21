package main.game;

import main.geometry.Point;

public final class Tunnel extends Obstacle implements EffetObstacle {  
    private Point sortie; 

    public Tunnel(Point position, Point sortie, double largeur, double hauteur) {
        super(position, largeur, hauteur, "Tunnel");
        this.sortie = sortie;
    }

    @Override
    public void appliquerEffet(Balle balle) {
        balle.getPosition().setX(sortie.getX());
        balle.getPosition().setY(sortie.getY());
    }
}
