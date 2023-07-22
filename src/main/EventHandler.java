package main;

import java.awt.*;

public class EventHandler {

    GamePanel gp;
    Rectangle [] eventRect;
    int eventRectDefaultX,eventRectDefaultY;
    int previousEventX,previousEventY;
    boolean canTouchEvent = true;
    public EventHandler(GamePanel gp) {
        eventRect = new Rectangle[gp.maxMap];
        this.gp = gp;
        int map = 0;
        while (map < gp.maxMap) {

            eventRect[map] = new Rectangle();
            eventRect[map].x = 23;
            eventRect[map].y = 23;
            eventRect[map].width = 2;
            eventRect[map].height = 2;
            eventRectDefaultX = eventRect[map].x;
            eventRectDefaultY = eventRect[map].y;
            map++;
        }
    }
    public void checkEvent () {
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance,yDistance);
        if(distance > gp.tileSize -47) {
            canTouchEvent = true;
        }
        if(canTouchEvent) {
            if(hit(0, 12, 0, "up")) {teleport(1,12,16, gp.level2);} //map 0 to map 1
            else if(hit(0, 13, 0, "up")) {teleport(1,13,16,gp.level2);} //map 0 to map 1
            else if(hit(0, 11, 0, "up")) {teleport(1,11,16,gp.level2);} //map 0 to map 1
            else if(hit(0, 14, 0, "up")) {teleport(1,14,16,gp.level2);} //map 0 to map 1
            else if(hit(1, 12, 16, "down")) {teleport(0,12,0,gp.level1);} // map 1 to map 0
            else if(hit(1, 13, 16, "down")) {teleport(0,13,0,gp.level1);} // map 1 to map 0
            else if(hit(1, 14, 16, "down")) {teleport(0,14,0,gp.level1);} // map 1 to map 0
            else if(hit(1, 11, 16, "down")) {teleport(0,11,0,gp.level1);} // map 1 to map 0
            else if(hit(1, 15, 0, "up")) {teleport(2,15,16, gp.beforeFight);} //map 1 to map 2
            else if(hit(2, 15, 16, "down")) {teleport(1,15,0,gp.level2);} //map 2 to map 1
            else if(hit(2, 15, 13, "any")) {teleport(3,15,13,gp.fight);} //map 2 to map 3
        }
    }
    public boolean hit (int map,int col, int row, String reqDirection) {

        boolean hit = false;
        if(map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map].x = col * gp.tileSize + eventRect[map].x;
            eventRect[map].y = row * gp.tileSize + eventRect[map].y;

            if (gp.player.solidArea.intersects(eventRect[map])) {
                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;
                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map].x = eventRectDefaultX;
            eventRect[map].y = eventRectDefaultY;
        }
        return hit;
    }
    public void teleport (int map,int col,int row,int area) {

        gp.currentMap = map;
        gp.nextArea = area;
        gp.player.worldX = gp.tileSize * col ;
        gp.player.worldY = gp.tileSize * row;
        previousEventX = gp.player.worldX;
        previousEventY = gp.player.worldY;
        canTouchEvent = false;
    }
}
