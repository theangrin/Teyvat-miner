import java.awt.*;
public class Bg{
    Image bg=Toolkit.getDefaultToolkit().getImage("imgs/bg2.png");/*获取背景图片*/
    Image peo=Toolkit.getDefaultToolkit().getImage("imgs/people.png");

    void paintSelf(Graphics g){
        g.drawImage(bg,0,0,null);/*添加图片*/
        g.drawImage(peo,300,40,null);

    }
}
