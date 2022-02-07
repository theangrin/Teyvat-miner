import java.awt.*;

public class Click extends Note{

    Click(){
//        this.x=(int)(Math.random()*500+150);
//        this.y=(int)(Math.random()*250+250);
        this.x=190;
        this.y=220;
        this.width=60;
        this.height=60;
        this.flag=false;
        this.img= Toolkit.getDefaultToolkit().getImage("imgs/click.png");

    }
}
