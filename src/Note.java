import java.awt.*;

public class Note {
    /*坐标*/
    int x;
    int y;
    /*宽高*/
    int width;
    int height;
    /*图片*/
    Image img;
    /*标记，防止一同变化*/
    boolean flag;
    /*质量*/
    int m;

    void paintSelf(Graphics g){
        g.drawImage(img,x,y,null);
    }

    /*获取圆形*/
    public Rectangle getRec(){
        return new Rectangle(x,y,width,height);
    }

}
