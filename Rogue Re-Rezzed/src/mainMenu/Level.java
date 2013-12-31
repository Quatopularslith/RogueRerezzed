
package mainMenu;

import core.GameLoop;
import core.Rogue;
import static dungeon.Level.renderlevel;
import dungeon.LevelMode;
import dungeon.LevelType;
import dungeon.Room;
import entity.Stairway;
import entity.mob.MortuusTrabajos;
import entity.mob.Quatopularslith;
import entity.npc.Trader;
import entity.npc.Warrior;
import entity.player.Player;
import java.awt.Image;
import render.Sprite;
import ui.GamePlay;

/**
 *
 * @author Torri
 */
public class Level extends dungeon.Level{
    @Override
    public void generateLevel(int sx,int sy,int lvl,LevelMode mode1,LevelType type1,int render1){
        GameLoop.pause();
        if(lvl==0){
            lvl=1;
        }
        mode=mode1;
        type=type1;
        nument=0;
        int roomnum=0;
        board=new boolean[2*sx][2*sy];
        maxRoomSX=(sx/rows);
        maxRoomSY=(sy/cols);
        numRooms=(rows)*(cols);
        rooms=new Room[numRooms+3];
        //Mode Selection
        if(mode1==LevelMode.STORY){
            renderlevel=Math.round(Rogue.numLevels/5)*16;
            if(renderlevel>48){
                renderlevel=48;
            }else if(renderlevel<16 && Rogue.numLevels>3){
                renderlevel=16;
            }
        }else{
            renderlevel=render1;
        }
        this.lvl=lvl;
        if(Rogue.mm != null) Rogue.mm.ki.turn = type1 != LevelType.EVOLVED;
        //Sprite Change
        GamePlay.fsp = new Sprite("DungeonFloor");
        GamePlay.dialogue = new Sprite("Dialogue",256);
        GamePlay.floorimg=new Sprite("DungeonFloor",8);
        GamePlay.pimg=new Sprite("Player",8);
        GamePlay.stimg=new Sprite("Stairway",8);
        //Roomgen
        for(int x=0;x<sx;x+=maxRoomSX){
            for(int y=0;y<sy;y+=maxRoomSY){
                rooms[roomnum]=new Room(x,y,maxRoomSX,maxRoomSY,lvl,this);
                roomnum++;
            }
        }
        //Player
        Player p=new Player(this);
        if(Rogue.mm.mmp.l.get(Rogue.mm.mmp.l.size()-2)!=null){
            p.currinv=Rogue.getLastLevel().getPlayer().currinv;
            p.inv=Rogue.getLastLevel().getPlayer().inv;
            p.gold=Rogue.getLastLevel().getPlayer().gold;
            p.health=Rogue.getLastLevel().getPlayer().health;
            p.lvl=Rogue.getLastLevel().getPlayer().lvl;
            p.xp=Rogue.getLastLevel().getPlayer().xp;
            p.mana=Rogue.getLastLevel().getPlayer().mana;
            p.rep=Rogue.getLastLevel().getPlayer().rep;
            p.kills=Rogue.getLastLevel().getPlayer().kills;
        }
        p.updateStats();
        pl.add(p);
        //Stariway
        st = new Stairway(this);
        //Safety Rooms
        rooms[roomnum]=new Room(rooms[p.roomnum].area[0][0][0]-2,rooms[p.roomnum].area[0][0][1]-2,sx,3,lvl,this);
        rooms[roomnum+1]=new Room(0,rooms[st.room].area[0][0][1],sx,3,lvl,this);
        rooms[roomnum+2]=new Room(rooms[st.room].area[0][0][0],0,3,sy,lvl,this);
        //Board
        for(boolean[] b1:board){
            for(boolean b:b1){
                b=false;
            }
        }
        for(Room r:rooms){
            for(int[][] a:r.area){
                for(int[] a1:a){
                    board[a1[0]][a1[1]]=true;
                }
            }
        }
        //Bosses
        MortuusTrabajos mt = new MortuusTrabajos(lvl,this.rooms[rand.nextInt(rooms.length)],this);
        this.addEntity(mt);
        
        Quatopularslith qt = new Quatopularslith(lvl,this.rooms[rand.nextInt(rooms.length)],this);
        this.addEntity(qt);
        
        //NPCs
        Trader t = new Trader(this.rooms[rand.nextInt(rooms.length)],this);
        this.addEntity(t);
        
        Warrior w = new Warrior(this.rooms[rand.nextInt(rooms.length)], this);
        this.addEntity(w);
        //Non-turn-based
        if(type1==LevelType.EVOLVED){
            GameLoop.start();
        }else{
            GameLoop.pause();
        }
        //Favicon
        Image s = la.createBufferedImage("Quatopularslith"+renderlevel+".png", 64, 64);
        if(Rogue.mm!=null) Rogue.mm.setIconImage(s);
    }
}
