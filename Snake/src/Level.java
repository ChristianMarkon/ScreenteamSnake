public class Level {
    Game gam;
public Level(Game g){
    gam=g;
}

public void doIt(){
    gam.blocks.add(new Entity("Block", 5, 7));
    gam.Cells[5][7]="Block";
}

}
