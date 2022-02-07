import java.awt.*;

public class Line{

    /*起点坐标*/
    int x=390;
    int y=50;
    /*终点坐标*/
    int endx;
    int endy;

    /*线长*/
    double length=150;
    double angle=0;
    /*方向*/
    int dir=1;
    /*状态:0摇摆，1抓取，2收回，3抓取返回*/
    int state;

    /*添加hook图片*/
    Image hook=Toolkit.getDefaultToolkit().getImage("imgs/hook.png");

    /*接收主窗口元素*/
    GameWin frame;
    Line(GameWin frame){
        this.frame=frame;
    }

    /*碰撞检测，检测是否已抓取*/
    void logic(){
        for(Note n:this.frame.noteList){
            if(n.flag){
            }
            if(endx>n.x&&endx<n.x+n.width
                    &&endy>n.y&&endy<n.y+n.height){
                state=3;
                n.flag=true;
            }

        }
    }



    void paintSelf(Graphics g) {
        logic();
        switch(state){
            case 0:
                if(angle<0.2) {
                    dir = 1;
                }
                else if(angle>0.8) {
                    dir = -1;
                }
                angle=angle+0.01*dir;/*此处改摇摆速度*/
                lines(g);
                break;
            case 1:
                if(length<500){
                    length=length+10;
                    lines(g);
                }else {
                    state =2;
                }
                break;
            case 2:
                if(length>150){
                    length=length-10;
                    lines(g);
                }else {
                    state=0;
                }
                break;
            case 3:
                if(length>150){
                    length=length-10;
                    lines(g);
                    for(Note n: this.frame.noteList){
                        if(n.flag){
                            n.img=null;
                            n.width=0;
                            n.height=0;
                            if(length<=150){
                                n.flag=false;
                                state=0;
                            }

                        }
                    }
                }

        }


    }
    /*绘制方法*/
    void lines(Graphics g){
        endx =(int)(x+length * Math.cos(angle*Math.PI));
        endy=(int)(y+length*Math.sin(angle*Math.PI));
        g.setColor(Color.darkGray);
        g.drawImage(hook,endx-30,endy-30,null);
        /*为保证line与hook的连接，绘制line时length-30*/
        endx =(int)(x+(length-30) * Math.cos(angle*Math.PI));
        endy=(int)(y+(length-30)*Math.sin(angle*Math.PI));
        g.drawLine(x-1, y, endx-1, endy);
        g.drawLine(x, y, endx, endy);
        g.drawLine(x+1, y, endx+1, endy);

    }

}
