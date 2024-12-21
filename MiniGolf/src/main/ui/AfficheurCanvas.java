package main.ui;

import main.geometry.Point;
import main.game.Balle;
import main.game.Trou;
import main.game.Obstacle;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import java.util.List;

public class AfficheurCanvas {
    private final Canvas canvas;
    private final GraphicsContext contexteGraphique;
    private final Balle balle;
    private final Trou trou;
    private final Image background;

    private List<Obstacle> obstacles;

    public AfficheurCanvas(double largeur, double hauteur, Balle balle, Trou trou, List<Obstacle> obstacles) {
        this.canvas = new Canvas(largeur, hauteur);
        this.contexteGraphique = canvas.getGraphicsContext2D();
        this.balle = balle;
        this.trou = trou;
        this.obstacles = obstacles;

        Image tempBackground = null;
        try {
            tempBackground = new Image(getClass().getResource("/main/ressources/grass.png").toExternalForm());
        } catch (NullPointerException | IllegalArgumentException e) {
            System.err.println("Erreur : Impossible de charger l'image de fond. " + e.getMessage());
        }
        this.background = tempBackground;

        dessiner();
    }

    public void setObstacles(List<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void dessiner() {
        try {
            dessinerBackground();
            dessinerTrou();
            dessinerFlag();
            dessinerObstacles();
            dessinerBalle();
        } catch (Exception e) {
            System.err.println("Erreur lors du dessin sur le canvas : " + e.getMessage());
        }
    }

    private void dessinerBackground() {
        try {
            if (background != null) {
                contexteGraphique.drawImage(background, 0, 0, canvas.getWidth(), canvas.getHeight());
            } else {
                throw new NullPointerException("L'image de fond est nulle.");
            }

            contexteGraphique.setFill(new Color(1, 1, 1, 0.1)); // Blanc semi-transparent
            contexteGraphique.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        } catch (NullPointerException e) {
            System.err.println("Erreur : Impossible de dessiner le fond. " + e.getMessage());

            // Fallback avec dégradé
            LinearGradient gradient = new LinearGradient(0, 0, 0, canvas.getHeight(),
                    false, null,
                    new Stop(0, Color.CYAN),
                    new Stop(1, Color.GREEN)
            );
            contexteGraphique.setFill(gradient);
            contexteGraphique.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        }
    }

    public void dessinerFlag() {
        try {
            double flagPoleX = trou.getPosition().getX();
            double flagPoleY = trou.getPosition().getY() - trou.getRayon();
            double flagHeight = 50; // Hauteur du mât
            double flagWidth = 20;  // Largeur du drapeau

            contexteGraphique.setStroke(Color.BLACK);
            contexteGraphique.setLineWidth(2);
            contexteGraphique.strokeLine(flagPoleX, flagPoleY, flagPoleX, flagPoleY - flagHeight);

            double[] xPoints = {
                    flagPoleX,
                    flagPoleX + flagWidth,
                    flagPoleX
            };
            double[] yPoints = {
                    flagPoleY - flagHeight,
                    flagPoleY - flagHeight + flagWidth / 2,
                    flagPoleY - flagHeight + flagWidth
            };

            contexteGraphique.setFill(Color.RED);
            contexteGraphique.fillPolygon(xPoints, yPoints, 3);
        } catch (NullPointerException e) {
            System.err.println("Erreur : Le drapeau ne peut pas être dessiné car le trou est invalide. " + e.getMessage());
        }
    }

    public void dessinerBalle() {
        try {
            double centerX = balle.getPosition().getX();
            double centerY = balle.getPosition().getY();
            double rayon = balle.getRayon();

            RadialGradient gradient = new RadialGradient(
                    0, 0, centerX, centerY, rayon, false, null,
                    new Stop(0, Color.WHITE),
                    new Stop(1, Color.LIGHTGRAY)
            );

            contexteGraphique.setFill(gradient);
            contexteGraphique.fillOval(centerX - rayon, centerY - rayon, rayon * 2, rayon * 2);
        } catch (NullPointerException e) {
            System.err.println("Erreur : La balle ne peut pas être dessinée car ses données sont invalides. " + e.getMessage());
        }
    }

    public void dessinerTrou() {
        try {
            contexteGraphique.setFill(Color.BLACK);
            contexteGraphique.fillOval(
                    trou.getPosition().getX() - trou.getRayon(),
                    trou.getPosition().getY() - trou.getRayon(),
                    trou.getRayon() * 2,
                    trou.getRayon() * 2
            );
        } catch (NullPointerException e) {
            System.err.println("Erreur : Impossible de dessiner le trou. " + e.getMessage());
        }
    }

    public void dessinerObstacles() {
        try {
            for (Obstacle obstacle : obstacles) {
                if (obstacle == null) continue;

                contexteGraphique.setFill(Color.BROWN);

                if (obstacle.getType().equals("Sable")) {
                    contexteGraphique.setFill(Color.SANDYBROWN);
                } else if (obstacle.getType().equals("Tunnel")) {
                    contexteGraphique.setFill(Color.DARKBLUE);
                }

                contexteGraphique.fillRect(
                        obstacle.getPosition().getX(),
                        obstacle.getPosition().getY(),
                        obstacle.getLargeur(),
                        obstacle.getHauteur()
                );
            }
        } catch (NullPointerException e) {
            System.err.println("Erreur : Impossible de dessiner les obstacles. " + e.getMessage());
        }
    }

    public void dessinerTrajectoire(Point debut, Point fin) {
        try {
            dessiner();
            contexteGraphique.setStroke(Color.GRAY);
            contexteGraphique.strokeLine(debut.getX(), debut.getY(), fin.getX(), fin.getY());
        } catch (Exception e) {
            System.err.println("Erreur : Impossible de dessiner la trajectoire. " + e.getMessage());
        }
    }
}
