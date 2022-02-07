import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GameWin extends JFrame {/*创建窗口以监听鼠标事件*/

    /*存储note*/
    List<Note> noteList=new ArrayList<>();

    Bg bg=new Bg();
    Line line=new Line(this);
    Image offScreenImage;/*双缓存解决图片闪动问题*/

    {
        /*循环添加click。for循环不能直接写在类中，所以需要代码块或无参构造*/
        /*防止重叠*/
        boolean isPlace=true;
        for(int i=0;i<1;i++){
            Click click=new Click();
            for(Note n:noteList){
                if(click.getRec().intersects(n.getRec())){
                    /*叠加，需重新生成*/
                    isPlace=false;
                    break;
                }
            }
            if(isPlace){
                noteList.add(click);
            }else{
                isPlace=true;
                i--;
            }
        }
    }


    void launch(){/*初始化*/
        this.setSize(800,600);/*长宽*/
        this.setLocationRelativeTo(null);/*窗口居中*/
        this.setTitle("提瓦特矿工");
        setDefaultCloseOperation(EXIT_ON_CLOSE);/*右上角×号关闭*/
        this.setVisible(true);/*窗口可见，所有组件渲染，必须放最后*/

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);/*监听鼠标事件*/
                if(e.getButton()==1){/*左键1，滚轮2，右键3*/
                    line.state=1;
                }
            }
        });

        while(true){
            repaint();

            try{
                Thread.sleep(1000/60);/*延时效果*/
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void paint(Graphics g){

        offScreenImage=this.createImage(800,800);
        Graphics gImage=offScreenImage.getGraphics();/*调用画笔*/

        bg.paintSelf(gImage);
        for(Note n:noteList){
            n.paintSelf(gImage);
        }
        line.paintSelf(gImage);
        g.drawImage(offScreenImage,0,0,null);
    }

    public static void main(String[] args) {
        GameWin gameWin=new GameWin();
        gameWin.launch();
    }


}

