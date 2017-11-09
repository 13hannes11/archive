
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hannes
 */
public class Map {

    private boolean burning = false;
    private ArrayList<Tile> closedList;
    private int width;
    private int height;
    //   private byte currentRound;
    private Tile[][] tileMap;

    protected Map(int w, int h, ArrayList<Tile> closed, Tile[][] map) {
        closedList = closed;
        tileMap = map;

        height = h;
        width = w;
    }

    public Map() {
        closedList = new ArrayList<Tile>();

        width = 0;
        height = 0;
        tileMap = null;
    }

    public void extinguish(int x, int y) {
        tileMap[x][y].extinguish();
        closedList.remove(tileMap[x][y]);
        // burnsIn(false);
    }

    public void ext() {
        int bestScore = Integer.MIN_VALUE;
        Map _map = this.clone();
        _map.burnsIn(true);
        int[] mainScore = _map.returnScoreArray();

        int bestX = 0, bestY = 0;
        for (int x = 0; x < tileMap.length; x++) {
            for (int y = 0; y < tileMap[x].length; y++) {
                if (!tileMap[x][y].isExt() && tileMap[x][y].getType() != 1) {
                    Map _tmpMap = this.clone();
                    _tmpMap.extinguish(x, y);
                    _tmpMap.burnsIn(true);


                    int _score = _tmpMap.calcScore(mainScore, mainScore);
                    if (_score >= bestScore && (!burning || tileMap[x][y].getType() == 2)) {
                        bestScore = _score;
                        bestX = x;
                        bestY = y;
                    }

                    tileMap[x][y].setScore(_score);
                }

            }
        }
        System.out.println(
                "[" + bestX + "|" + bestY + "|"+ bestScore + "]");

        extinguish(bestX, bestY);
    }

    public void next() {
        nextRound();
        ext();
    }

    public void clearRoundList() {
        for (int x = 0; x < tileMap.length; x++) {
            for (int y = 0; y < tileMap[x].length; y++) {
                if (closedList.contains(tileMap[x][y])) {
                    tileMap[x][y].setRound(0);
                } else {
                    tileMap[x][y].setRound(-1);
                }
            }
        }
    }

    public void burnsIn(boolean ignite) { //Fehlder liegt vermutlich in dieser Methode
        clearRoundList();
        ArrayList<Tile> setThisRound = new ArrayList<Tile>();
        ArrayList<Tile> open = new ArrayList<Tile>();
        for (Tile tile : closedList) {
            if (tile.getType() == 2) {
                open.add(tile);
            }
        }
        int i = 0;
        while (open.size() != 0) {
            ArrayList<Tile> _open = (ArrayList<Tile>) open.clone();
            open.clear();
            for (Tile tile : _open) {
                if (!contains(tile, setThisRound)) {
                    setThisRound.add(tile);
                    ArrayList<Tile> _n = new ArrayList<Tile>();
                    _n.add(getNeighbour(tile, 1, 0));
                    _n.add(getNeighbour(tile, 0, 1));
                    _n.add(getNeighbour(tile, -1, 0));
                    _n.add(getNeighbour(tile, 0, -1));
                    for (Tile _t : _n) {
                        if (_t != null && !contains(_t, setThisRound)) {
                            tileMap[_t.getX()][_t.getY()].setRound(i);
                            open.add(tileMap[_t.getX()][_t.getY()]);
                        }
                    }
                }
            }
            i++;
        }
        if (ignite) {
            for (int x = 0; x < tileMap.length; x++) {
                for (int y = 0; y < tileMap[x].length; y++) {
                    if (tileMap[x][y].getRound() == 0) {
                        tileMap[x][y].ignite();
                        if (!contains(tileMap[x][y], closedList)) {
                            closedList.add(tileMap[x][y]);
                        }
                    }

                }
            }
        }
        //System.out.println(open.size());

    }

    public Tile getNeighbour(Tile t, int offsetX, int offsetY) {
        if (t.isExt()) {
            return null;
        }
        int _x = t.getX() + offsetX;
        int _y = t.getY() + offsetY;
        if (_x < 0 || _x >= tileMap.length || _y < 0 || _y >= tileMap[_x].length) {
            return null;
        }
        if (tileMap[_x][_y].isExt() || tileMap[_x][_y].getType() == 1) {
            return null;
        }

        return tileMap[_x][_y];
    }

    private boolean contains(Tile t, ArrayList<Tile> list) {
        for (Tile tile : list) {
            if (t.getX() == tile.getX() && t.getY() == tile.getY()) {
                return true;
            }
        }
        return false;
    }

    public void nextRound() {
        burnsIn(true);
        //extinguish(5, 5);
        //extinguish(4, 4);
        //System.out.println("Score: " + calcScore(new int[]{5, 4, 3, 2, 1}, returnScoreArray()));
    }

    public int[] returnScoreArray() {
//        ArrayList<Integer> list = new ArrayList<Integer>();
//        for (int x = 0; x < tileMap.length; x++) {
//            for (int y = 0; y < tileMap[x].length; y++) {
//                int r = tileMap[x][y].getRound();
//                while (r >= list.size()) {
//                    list.add(0);
//                }
//                if (r >= 0) {
//                    list.set(r, list.get(r) + 1);
//                }
//            }
//        }
        //int[] i = new int[list.size()];
//        for (int l = 0; l < i.length; l++) {
//            i[l] = list.get(l);
//        }
        int[] i = new int[10];
        for (int z = 0; z < i.length; z++) {
            if (z == 0) {
                //System.out.println("");
            }
            for (int x = 0; x < tileMap.length; x++) {
                if (z == 0) {
                    //System.out.println("");
                }
                for (int y = 0; y < tileMap.length; y++) {

                    if (z == 0) {
                        //System.out.print(tileMap[x][y].getRound() + ";");
                    }

                    if (tileMap[x][y].getRound() == z) {
                        i[z]++;
                    }
                }
            }
        }

        return i;
    }

    public int calcScore(int[] factor, int[] mainScore) {
        //burnsIn(false);
        int[] arr = returnScoreArray();
        int score = 0;
        int length = (arr.length < factor.length) ? arr.length : factor.length;
        for (int i = 0; i < length; i++) {
            int mainS = (i < mainScore.length) ? mainScore[i] : 0;
            score += (mainS - arr[i]) * factor[i];
        }
        return score;
    }
    // <editor-fold defaultstate="collapsed" desc="File Management">

    public void load(String path) {
        Tile[][] tmp = null;
        BufferedReader br = null;
        try {
            StringBuilder stringB = new StringBuilder();
            br = new BufferedReader(new FileReader(path));
            width = readInt(br);
            height = readInt(br);
            tmp = new Tile[width][height];

            for (int y = 0; y < width; y++) {
                for (int x = 0; x < height; x++) {
                    switch ((char) br.read()) {
                        case '0': //Normaler Wald
                            tmp[x][y] = new Tile(x, y, (byte) 0);
                            break;
                        case '1': //Blockierter Wald
                            tmp[x][y] = new Tile(x, y, (byte) 1);
                            break;
                        case '2': //Feuer
                            tmp[x][y] = new Tile(x, y, (byte) 2);
                            closedList.add(tmp[x][y]);
                            break;
                        default:
                            x--;
                            break;
                    }
                }
            }
            setTileMap(tmp);

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    private int readInt(BufferedReader br) throws IOException {
        char c;
        int i;

        c = readNonwhiteChar(br);
        i = 0;
        do {
            i = i * 10 + c - '0';
            c = (char) br.read();
        } while (c >= '0' && c <= '9');
        return i;
    }

    private static char readNonwhiteChar(BufferedReader bf) throws IOException {
        char c;

        do {
            c = (char) bf.read();
        } while (c == ' ' || c == '\t' || c == '\n' || c == '\r');

        return c;
    }
    // </editor-fold>

    @Override
    public Map clone() {
        ArrayList<Tile> _tmpClosed = new ArrayList<Tile>();
        for (Tile tile : closedList) {
            _tmpClosed.add(tile.clone());
        }
        Tile[][] _tmpMap = new Tile[tileMap.length][tileMap[0].length];
        for (int x = 0; x < tileMap.length; x++) {
            for (int y = 0; y < tileMap[x].length; y++) {
                _tmpMap[x][y] = tileMap[x][y].clone();
            }
        }
        return new Map(width, height, _tmpClosed, _tmpMap);
    }

    @Override
    public String toString() {
        String str = "";
        for (int y = 0; y < tileMap[0].length; y++) {
            for (int x = 0; x < tileMap.length; x++) {
                str = str + tileMap[x][y].getRound() + ";";
            }
            str = str + "\n";
        }
        return str;
    }
    // <editor-fold defaultstate="collapsed" desc="Getter and Setter">

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Tile[][] getTileMap() {
        return tileMap;
    }

    /**
     * @param tileMap the tileMap to set
     */
    public void setTileMap(Tile[][] tileMap) {
        this.tileMap = tileMap;
    }
    // </editor-fold>
}
