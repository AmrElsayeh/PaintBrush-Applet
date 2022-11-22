import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;


public class PaintBrush extends Applet{

    Button btnLine, btnOval, btnRect, btnHand, btnErase, btnCleanAll, btnRed, btnBlue, btnGreen, btnUndo, btnRedo;       //Creating Buttons
    Shape currLine,currOval,currRect,currHand,currErase;                      //Initialize references from an Shape class to use it in the live draw
    Vector <Shape> shapesVec,free,unDo;                //Initialize vector reference that holds Shape type 
    Color selectedColor = Color.BLACK;         //setting default color
    private Checkbox solid;               //Creating CheckBox
    char selectedShape;   
    int x1,x2,y1,y2;
    

    public void init(){
        shapesVec = new Vector <Shape>();         //Creating an object from the vector class that holds shape type. 
        MouseClick click = new MouseClick();       // creating an obj of the source class 
		this.addMouseListener(click);              //Registered Mouse clicks action to the listiner
		this.addMouseMotionListener(click);        //Registered Mouse motions action to the listiner

        //Initilize the 9 buttons
        btnLine = new Button("Line"); 
		btnOval = new Button("Oval");
        btnRect = new Button("Rect"); 
		btnHand = new Button("Free-Hand");
        solid = new Checkbox("Solid",false);
        btnErase = new Button("Erase"); 
		btnCleanAll = new Button("Clean-All");
        btnRed = new Button("Red"); 
		btnBlue = new Button("Blue");
        btnGreen = new Button("Green"); 
        btnUndo = new Button("Undo");
        btnRedo = new Button("Redo"); 

        //Adding the buttons to the Applet
		add(btnLine); add(btnOval); add(btnRect); add(btnHand); add(solid); add(btnErase);
        add(btnCleanAll); add(btnRed); add(btnBlue); add(btnGreen); add(btnUndo); add(btnRedo);
        
        //Registered the buttons 
        btnLine.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){selectedShape = 'l';}});
        btnOval.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){selectedShape = 'o';}});
        btnRect.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){selectedShape = 'r';}});
        btnHand.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){selectedShape = 'h';}});
        btnErase.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){selectedShape = 'e';}});
        btnCleanAll.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){shapesVec.clear();repaint();}});
        btnRed.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){selectedColor = Color.RED;}});
        btnGreen.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){selectedColor = Color.GREEN;}});
        btnBlue.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){selectedColor = Color.BLUE;}});
        btnUndo.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){if(!shapesVec.isEmpty()){shapesVec.remove(shapesVec.lastIndexOf(shapesVec.lastElement()));repaint();}}});
        //btnRedo.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){if(!shapesVec.isEmpty()&!unDo.isEmpty())shapesVec.add(unDo.lastIndexOf(unDo.lastElement()),unDo.lastElement());repaint();}});

    }
    public void paint(Graphics g){
        whichShape(selectedShape,g);
    }  
    public void reset(){
		x1=0; x2=0; y1=0; y2=0;
	}
    public void whichShape(char selectedShape,Graphics g){
        switch (selectedShape) {
            case 'l':
                currLine.draw(g);
                for(int i=0;i<shapesVec.size();i++){
                    shapesVec.get(i).draw(g);
                }
                break;
            case 'o':
                currOval.draw(g);
                for(int i=0;i<shapesVec.size();i++){
                    shapesVec.get(i).draw(g);
                }
                break;
            case 'r':
                currRect.draw(g);
                for(int i=0;i<shapesVec.size();i++){
                    shapesVec.get(i).draw(g);
                }
                break;
            case 'h':
                for(int i=0;i<shapesVec.size();i++){
                    shapesVec.get(i).draw(g);
                }
                break;
            case 'e':
                //currErase.draw(g);
                for(int i=0;i<shapesVec.size();i++){
                    shapesVec.get(i).draw(g);
                }
                break;
            default:
                break;
        }
    }
    class MouseClick extends MouseAdapter{
		public void mousePressed(MouseEvent e){
			x1 = e.getX();
			y1 = e.getY();
            switch (selectedShape) {
                case 'e':
                    shapesVec.add(new Erase(e.getX(), e.getY()));
                    break;
                case 'h':
                    shapesVec.add(new FreeLine(e.getX(), e.getY(),selectedColor));
                    break;
                default:
                    break;
            }
		}
		public void mouseDragged(MouseEvent e){
			x2 = e.getX();
			y2 = e.getY();
            //Just Objects to shows the drawing shapes live
            switch (selectedShape) {
                case 'l':
                    currLine=new Line(x1, y1, x2, y2, selectedColor);
                    break;
                case 'o':
                    currOval=new Oval(x1,y1,x2,y2,selectedColor,solid.getState());
                    break;
                case 'r':
                    currRect=new Rectangle(x1,y1,x2,y2,selectedColor,solid.getState());
                    break;
                case 'h':
                    shapesVec.add(new FreeLine(e.getX(), e.getY(),selectedColor));
                    break;
                case 'e':
                    shapesVec.add(new Erase(e.getX(), e.getY()));
                    break;
                default:
                    break;
            }         
			repaint();
		}	
		public void mouseReleased(MouseEvent e){ 
            if(x2==0&y2==0){
				x2 = e.getX();
				y2 = e.getY();
                repaint();
            }else if(x2!=0||y2!=0)
                switch (selectedShape) {
                    case 'l':
                        shapesVec.add(new Line(x1,y1,x2,y2,selectedColor));
                        repaint();
                        break;
                    case 'o':
                        shapesVec.add(new Oval(x1,y1,x2,y2,selectedColor,solid.getState()));
                        repaint();
                        break;
                    case 'r':
                        shapesVec.add(new Rectangle(x1,y1,x2,y2,selectedColor,solid.getState()));
                        repaint();
                        break;
                    default:
                        break;
                }
			reset();
		}
	}
}
