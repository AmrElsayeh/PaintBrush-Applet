import java.awt.*;
import java.util.Vector;

abstract class Shape{
	protected int x1;
    protected int x2;
    protected int y1;
    protected int y2;
    static Vector <Shape> shapesVec = new Vector <Shape>();
    static Vector <Shape> free = new Vector <Shape>();


    abstract public void draw(Graphics g);
    public int getX1(){return x1;}
    public int getY1(){return y1;}
    public void setX1(int x){x1 = x;}
    public void setY1(int y){y1 = y;}

}
class Rectangle extends Shape {
    Color color;
    int w,h,a,b,c,d;
    Boolean solid;
    public Rectangle(){}
    public Rectangle(int x1,int y1, int x2, int y2, Color color, Boolean solid){
        this.x1 =x1;
        this.y1 =y1;
        this.x2 =x2;
        this.y2 =y2;
        this.color = color;
        this.solid = solid;
    }
    public void draw(Graphics  g){
        w = Math.abs(x2-x1);
        h = Math.abs(y2-y1);
        if(x2>x1&y2<y1){
            c=y1;d=y2;
            y2=c;
            y1=d;
        }else if(x2<x1&y2>y1){
            a=x1; b=x2;
            x1=b;
            x2=a;
        }else if(x2<x1&y2<y1){
            a=x1; b=x2;c=y1;d=y2;
            x1=b;x2=a;y1=d;y2=c;
        }
        g.setColor(color);
        if(solid){
            g.fillRect(x1,y1,(x2-x1),(y2-y1));
        }else g.drawRect(x1,y1,(x2-x1),(y2-y1));
    }
}
class Line extends Shape {
    Color color;
    Boolean solid;
    public Line(){}
    public Line(int x1,int y1, int x2, int y2, Color color/*,Boolean solid*/){
        this.x1 =x1;
        this.y1 =y1;
        this.x2 =x2;
        this.y2 =y2;
        this.color = color;
        //this.solid = solid;
    }
    public void draw(Graphics g){
        g.setColor(color);
        g.drawLine(x1,y1,x2,y2);
    }
}
class Oval extends Shape {
    int w,h,a,b,c,d;
    Color color;
    Boolean solid;
    public Oval(){}
    public Oval(int x1,int y1, int x2, int y2, Color color, Boolean solid){
        this.x1 =x1; 
        this.y1 =y1;
        this.x2 =x2;
        this.y2 =y2;
        this.color = color;
        this.solid = solid;
    }
    public void draw(Graphics  g){
        w = Math.abs(x2-x1);
        h = Math.abs(y2-y1);
        if(x2>x1&y2<y1){
            c=y1;d=y2;
            y2=c;
            y1=d;
        }else if(x2<x1&y2>y1){
            a=x1; b=x2;
            x1=b;
            x2=a;
        }else if(x2<x1&y2<y1){
            a=x1; b=x2;c=y1;d=y2;
            x1=b;x2=a;y1=d;y2=c;
        }
        g.setColor(color);
        if(solid){
            g.fillOval(x1,y1,w,h);
        }else g.drawOval(x1,y1,w,h);
    }
}
class Erase extends Shape {
    public Erase(int x1, int y1) {
        setX1(x1);
        setY1(y1);
    }
    public Erase(){}
    public void draw(Graphics  g){
        g.setColor(Color.WHITE);
        g.fillOval(getX1(),getY1(),10,10);
    }
}
class FreeLine extends Shape {
    Color color;
    public FreeLine(){}
    public FreeLine(int x1, int y1,Color color) {
        setX1(x1);
        setY1(y1);
        this.color = color;
    }
        public void draw(Graphics  g){
            g.setColor(color);
            g.fillOval(getX1(),getY1(),10,10);
        }
}
