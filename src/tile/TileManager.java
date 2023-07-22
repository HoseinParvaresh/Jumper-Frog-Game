package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int[][][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[100];
        mapTileNum = new int[gp.maxMap][gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        loadMap("/maps/map1.txt",0); //لود کردن مپ نقشه
        loadMap("/maps/map2.txt",1);
        loadMap("/maps/map3.txt",2);
        loadMap("/maps/map4.txt",3);
    }

    public void getTileImage() {

        //map 1 :
        setup(0,"grass01",false);
        setup(1,"jadval b",false);
        setup(2,"jadval y",false);
        setup(3,"sup",false);
        setup(4,"sup b",false);
        setup(5,"sup y",false);
        setup(6,"sdown b",false);
        setup(7,"sdown y",false);
        setup(8,"grass b",false);
        setup(9,"grass y",false);
        setup(10,"water08",false);
        setup(11,"water00",false);
        setup(12,"021",false);
        setup(13,"suy",false);
        setup(14,"sub",false);
        setup(15,"sdy",false);
        setup(16,"sdb",false);
        setup(17,"street",false);
        setup(18,"tree",true);
        setup(19,"grass01",true);
        setup(20,"jadval b",true);
        setup(21,"jadval y",true);
        setup(22,"grass b",true);
        setup(23,"grass y",true);
        setup(24,"water08",true);
        setup(25,"021",true);

        //map 2 :
        setup(44,"water1",true);
        setup(45,"water2",true);
        setup(46,"water3",true);
        setup(47,"water4",true);
        setup(48,"water05",false);
        setup(49,"grass02",false);
        setup(91,"grass02",true);
        setup(50,"grass11",true);
        setup(51,"grass12",true);
        setup(52,"grass13",true);
        setup(53,"wood",false);
        setup(68,"soil01",false);
        setup(69,"soil02",true);
        setup(70,"soil05",false);
        setup(71,"soil06",true);
        setup(72,"soil07",false);
        setup(73,"soil09",true);
        setup(74,"soil10",true);
        setup(75,"soil11",true);
        setup(76,"soil12",true);

        //map 3 & 4:
        setup(81,"floor06",false);
        setup(82,"floor07",false);
        setup(83,"floor09",false);
        setup(84,"floor08",false);
        setup(85,"floor11",false);
        setup(86,"floor10",false);
        setup(87,"floor12",false);
        setup(88,"floor13",false);
        setup(89,"floor14",false);
        setup(90,"floor15",false);
        setup(92,"lava01",true);
        setup(93,"lava02",true);
        setup(94,"lava03",true);
    }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + imageName + ".png")));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath,int map) { //ترسیم مپ

        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();

                while (col < gp.maxScreenCol) {
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
        }
    }
    public void draw(Graphics2D g2) { //چیدمان مپ

        int col = 0;
        int row = 0;
        int y = 0;
        int x = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

            int tileNum = mapTileNum[gp.currentMap][col][row];

            g2.drawImage(tile[tileNum].image, x, y, null);
            col++;
            x += gp.tileSize;

            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
