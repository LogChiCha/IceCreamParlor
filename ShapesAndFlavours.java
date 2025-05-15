import java.awt.*;
import java.awt.geom.Ellipse2D;


abstract public interface ShapesAndFlavours 
{
    ///
    /// Flavour
    ///     Holds the colors, name for customer printing, and bounds for clicking
    ///     Equals override that checks names, and print override
    /// 
    public class Flavour
    {
        Color clr1;
        Color clr2;
        String name;
        Ellipse2D bounds;

        public Flavour(Color clr1, Color clr2, String name)
        {
            this.clr1 = clr1;
            this.clr2 = clr2;
            this.name = name;
        }

        public void setBounds(int x, int y)
        {
            // Create bounds for clicking flavour
            bounds = new Ellipse2D.Double(x,y,90,90);
        }

        public boolean equals(Flavour other)
        {
            if (this.name.equals(other.name))
                return true;
            return false;
        }
        
        @Override
        public String toString()
        {
            return this.name;
        }
    }

    ///
    /// FlavourSelect
    ///     Draws the specified flavour at the specified location
    /// 
    public class FlavourSelect
    {
        int x,y;
        Flavour fl;
        public FlavourSelect(int x,int y,Flavour fl)
        {
            this.x = x; 
            this.y=y;
            this.fl=fl;
            fl.setBounds(x,y);
        }
        
        public void draw(Graphics g)
        {
            // Outline
            new Circle(x,y,90,90,Color.BLACK).draw(g);
            // Background
            new Circle(x,y,90,90,fl.clr1).draw(g,true);
            //Spots
            new Circle(x+20,y+25,10,10,fl.clr2).draw(g,true); //tl
            new Circle(x+55,y+25,15,15,fl.clr2).draw(g,true);//tr
            new Circle(x+22,y+55,15,15,fl.clr2).draw(g,true);//bl
            new Circle(x+62,y+60,10,10,fl.clr2).draw(g,true);//br
            new Circle(x+10,y+43,5,5,fl.clr2).draw(g,true);//l
            new Circle(x+42,y+15,5,5,fl.clr2).draw(g,true);//t
            new Circle(x+75,y+45,5,5,fl.clr2).draw(g,true);//r
            new Circle(x+45,y+73,5,5,fl.clr2).draw(g,true); //b
        }
        
    }

    ///
    /// Scoop
    ///     Adds a new Scoop drawing when a flavour is selected
    /// 
    public class Scoop
    {
        int scoopNumber;
        Flavour fl;
        public Scoop(int sN, Flavour fl)
            {this.scoopNumber = sN; this.fl = fl;}

        public void draw(Graphics g)
        {   
            // Base Scoop of Color 1
            new Circle(770,(230-(73*(scoopNumber-1))),160,103,fl.clr1).draw(g,true);
            new Circle(755,(230-(73*(scoopNumber-1)))+65,60,50,fl.clr1).draw(g,true);
            new Circle(798,(230-(73*(scoopNumber-1)))+65,60,50,fl.clr1).draw(g,true);
            new Circle(842,(230-(73*(scoopNumber-1)))+65,60,50,fl.clr1).draw(g,true);
            new Circle(885,(230-(73*(scoopNumber-1)))+65,60,50,fl.clr1).draw(g,true);
            // Sprinkles of Color 2
            new Circle(842,(230-(73*(scoopNumber-1)))+40,15,15,fl.clr2).draw(g,true); // center
            new Circle(805,(230-(73*(scoopNumber-1)))+20,10,10,fl.clr2).draw(g,true); //tl
            new Circle(885,(230-(73*(scoopNumber-1)))+20,10,10,fl.clr2).draw(g,true); //tr
            new Circle(815,(230-(73*(scoopNumber-1)))+65,10,10,fl.clr2).draw(g,true); //bl
            new Circle(875,(230-(73*(scoopNumber-1)))+65,10,10,fl.clr2).draw(g,true); //br
            new Circle(847,(230-(73*(scoopNumber-1)))+10,5,5,fl.clr2).draw(g,true); //top
            new Circle(785,(230-(73*(scoopNumber-1)))+45,5,5,fl.clr2).draw(g,true); //l
            new Circle(910,(230-(73*(scoopNumber-1)))+45,5,5,fl.clr2).draw(g,true); //r
            new Circle(770,(230-(73*(scoopNumber-1)))+80,5,5,fl.clr2).draw(g,true); //bbl
            new Circle(925,(230-(73*(scoopNumber-1)))+80,5,5,fl.clr2).draw(g,true); //bbr
        }
    }

    ///
    /// FlavourOrder
    ///     Adds the customer's visuals of flavours to the display
    /// 
    public class FlavourOrder
    {
        // Shift depending on which of the 4 slots it goes in
        int shift;
        Flavour fl;
        public FlavourOrder(int shift, Flavour fl)
            {this.shift = shift; this.fl = fl;}

        public void draw(Graphics g)
        {

            new Circle(200+(shift*120),200,90,90,Color.BLACK).draw(g);
            new Circle(202+(shift*120),202,86,86 ,fl.clr1).draw(g,true);
            new Circle(240+(shift*120),212,10,10 ,fl.clr2).draw(g,true); // Clr 2 spots
            new Circle(240+(shift*120),268,10,10 ,fl.clr2).draw(g,true);
        }
    }

    ///
    /// Circle Drawing
    /// 
    public class Circle
    {
        int x,y,w,h;
        Color c;
        public Circle(int x, int y,int w, int h, Color c){this.x=x; this.y=y; this.w=w;this.h=h;this.c = c;}
        
        // Boolean for fill
        public void draw(Graphics g,boolean fill)
        {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(c);
            if (fill)
                g2d.fillOval(x,y,w,h);
            else if (!fill)
                g2d.drawOval(x,y,w,h);
        }
        // Shorthand overload
        public void draw(Graphics g)
        {draw(g,false);}
    } // Circle ---------------------------------

    ///
    /// Line Drawing, uses two points (x1 y1 x2 y2)
    /// 
    public class Line
    {
        private int[] a,b;
        Color c;
        public Line(int p1x, int p1y, int p2x, int p2y,Color c)
        {
            this.a = new int[]{p1x,p1y};
            this.b = new int[]{p2x, p2y};
            this.c = c;
        }
        public void draw (Graphics g)
        {
            Graphics2D g2d = (Graphics2D)g;
            g2d.setColor(c);
            g2d.setStroke(new BasicStroke(4));
            g2d.drawLine(a[0],a[1],b[0],b[1]);

        }
        
    } // Line ----------------

    ///
    /// Rectangle Drawing
    /// 
    public class Rect
    {
        int x,y,w,h;
        Color c;
        public Rect(int x, int y,int w, int h,Color c){this.x=x; this.y=y; this.w=w;this.h=h;this.c=c;}
        
        public void draw(Graphics g, boolean fill)
        {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(c);
            if (fill)
                g2d.fillRect(x,y,w,h);
            else if (!fill)
                g2d.drawRect(x,y,w,h);
        }

        public void draw(Graphics g)
        {draw(g,false);}
    } // Rect --------------------------------

    ///
    /// Rounded Rectangle Drawing
    /// 
    public class RoundRect
    {
        int x,y,w,h;
        // Width and Height of rounded corner arcs
        int arcw, arch;
        Color c;
        public RoundRect(int x, int y,int w, int h, int arcw, int arch,Color c)
            {this.x=x; this.y=y; this.w=w; this.h=h; this.arcw=arcw; this.arch=arch; this.c=c;}

        public void draw(Graphics g, boolean fill)
        {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(c);
            if (fill)
                g2d.fillRoundRect(x, y, w, h, arcw, arch);
            else if (!fill)
                g2d.drawRoundRect(x,y,w,h,arcw,arch);
        }

        public void draw(Graphics g)
        {draw(g,false);}
    } // RoundRect ------------------------

    /// 
    /// Triangle Drawing, based on filly polygon with three points
    /// 
    public class Triangle
    {
        int[] x,y;
        Color c;
        public Triangle(int x1,int y1,int x2,int y2,int x3,int y3, Color c)
        {this.x = new int[] {x1,x2,x3};  this.y = new int[] {y1,y2,y3}; this.c=c;}

        public void draw(Graphics g, boolean fill)
        {
            Graphics2D g2d = (Graphics2D)g;
            g2d.setColor(c);
            if (fill)
                g2d.fillPolygon(x,y,3);
            else if (!fill)
                g2d.drawPolygon(x,y,3);
        }

        public void draw(Graphics g)
        {draw(g,false);}
    } // Triangle -------------------

}
