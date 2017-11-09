
import java.util.HashMap;
import processing.core.PApplet;

/**
 *
 */
public class Main extends PApplet {

    Map map = new Map();
    int recSize;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PApplet.main(new String[]{"Main"});
    }

    public void setup() {
        recSize = 50;
        map.load("C:\\Users\\Hannes\\Desktop\\map.txt");//scanner.next());
        size(map.getWidth() * recSize, map.getHeight() * recSize);
        map.burnsIn(false);
    }
    // TODO: setup application


    public void draw() {
//Drawing
        Tile[][] tmpMap = map.getTileMap();
        for (int x = 0; x < tmpMap.length; x++) {
            for (int y = 0; y < tmpMap[x].length; y++) {
                switch (tmpMap[x][y].getType()) {
                    case 0: //Normaler Wald
                        fill(0, 255, 0);
                        break;
                    case 1: //Blockierter Wald
                        fill(120);
                        break;
                    case 2: //Feuer
                        fill(255, 0, 0);
                        break;
                }
                rect(x * recSize, y * recSize, recSize, recSize);
                if (tmpMap[x][y].isExt()) {
                    fill(0, 0, 255);
                    line(x * recSize, y * recSize, (x + 1) * recSize, (y + 1) * recSize);
                    line((x + 1) * recSize, y * recSize, x * recSize, (y + 1) * recSize);
                }
                fill(0);
                text(tmpMap[x][y].getScore(), recSize * x + 1, recSize * y + 11);
                text((int) tmpMap[x][y].getRound(), recSize * x + 1, recSize * (y + 1) - 1);
            }
        }
    }

    public void keyReleased() {
        System.out.println("Key Released: " + keyCode);
        if (keyCode == 39) {
            map.next();
        }
    }
}
