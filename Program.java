import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

class Program extends JPanel implements ShapesAndFlavours
{
    JPanel p = new JPanel();

    // Flavours
    Flavour cc = new Flavour(new Color(239,215,170),new Color(122,85,52),"CC"); // Cookies & Cream
    Flavour vn = new Flavour(new Color(244,237,204),new Color(244,237,204),"VN"); // Vanilla
    Flavour ch = new Flavour(new Color(126,74,44),new Color(126,74,44),"CH"); // Chocolate
    Flavour vb = new Flavour(new Color(230,190,233),new Color(136,121,217),"VB"); // Very Berry
    Flavour mc = new Flavour(new Color(128,234,191),new Color(107,64,37),"MC"); // Mint Choco Chip
    Flavour rr = new Flavour(new Color(162,108,72),new Color(244,225,204),"RR"); // Rocky Road
    Flavour bg = new Flavour(new Color(255,174,201),new Color(255,174,201),"BG"); // Bubblegum
    Flavour bc = new Flavour(new Color(91,181,247),new Color(253,172,223),"BC"); // Birthday Cake
    Flavour gt = new Flavour(new Color(196,230,117),new Color(196,230,117),"GT"); // Matcha Green Tea
    Flavour te = new Flavour(new Color(247,154,70),new Color(79,44,34),"TE"); // Tiger's Eye
    Flavour sc = new Flavour(new Color(251,159,159),new Color(250,219,184),"SC"); // StrawCheesecake
    Flavour lm = new Flavour(new Color(249,242,125),new Color(249,242,125),"LM"); // Lemon

    // Panels-----------------------
    JPanel spacer;

    // Flavours and Button Panels
    JPanel flavourtext;
    JPanel texthold;
    JPanel texthold2;
    JPanel buttonholder;
    JLabel label;
    JLabel label2;

    // Customer Panels
    JPanel custspacer;
    JPanel custorderpanel;
    JLabel custorder;
    JPanel completedorderspanel;
    JLabel completedorders;


    // Buttons & Their Listeners
    JButton start;
    StartListener stl;
    JButton serve;
    ServeListener sel;
    JButton scrap;
    ScrapListener scl;

    // Starting Game Boolean, prevent pre-game inputs
    boolean gamestart = false;

    // How many flavours selected
    int selected = 0;
    // How many correct orders completed
    int correct = 0;

    // Easy int to convert chart
    Flavour[] convert = {cc,mc,vn,ch,sc,gt,bc,bg,te,vb,lm,rr};

    //Mechanics
    Random rand = new Random();
    // Holds the Customer's Random ORder
    ArrayList<Flavour> customerorder = new ArrayList<>();
    // Holds the Players Orders to compare
    ArrayList<Flavour> playerorder = new ArrayList<>();

    // Holds the Scoop drawing objects that represent the player's order
    ArrayList<Scoop> scoopsCone = new ArrayList<>();
    // Holds all components of the customer drawing, and marks
    ArrayList<Object> customerDrawings = new ArrayList<>();


    public Program()
    {
        // Make window
        p.setSize(1000,800);
        this.setPreferredSize(new Dimension(1000,800));
        p.setVisible(true);
        
        // Set up mouse listener
        MouseClicker ml = new MouseClicker();
        this.addMouseListener(ml);

        // Large spacer that covers the top panel, for text placement
        spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(1000,525));
        spacer.setOpaque(false);

        // CUSTOMER SECTION --------------------------------------------------
        // Spacing for customer text and correct text
        custspacer = new JPanel();
        custspacer.setPreferredSize(new Dimension(1000,190));
        custspacer.setOpaque(false);
        
        // Holds text in order
        custorderpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        custorderpanel.setPreferredSize(new Dimension(1000,260));
        custorderpanel.setOpaque(false);

        // Text in order
        custorder = new JLabel();
        custorder.setBounds(50,0,1000,100); 
        custorder.setHorizontalAlignment(SwingConstants.LEFT); 
        custorder.setForeground(Color.BLACK);
        custorder.setFont(new Font("Monospaced",Font.BOLD,50));
                            // Monospaced for even spacing

        // Holds completed text
        completedorderspanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        completedorderspanel.setPreferredSize(new Dimension(1000,70));
        completedorderspanel.setOpaque(false);

        // Completed text
        completedorders = new JLabel();
        completedorders.setBounds(0,0,1000,100);
        completedorders.setText(" Completed Orders: 0");
        completedorders.setHorizontalAlignment(SwingConstants.LEFT); 
        completedorders.setFont(new Font("Arial",Font.BOLD,25));
        // END OF CUSTOMER SECTION ---------------------------------------------------


        // FLAVOURS SECTION -------------------------------------------
        // Panel that holds both texts, left panel
        flavourtext = new JPanel();
        flavourtext.setPreferredSize(new Dimension(700,530));
        flavourtext.setOpaque(false);
        //flavourtext.setBackground(Color.ORANGE);

        // texthold = Top Text   texthold2 = Bottom Text
        texthold = new JPanel(new FlowLayout(FlowLayout.LEFT));
        texthold2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        texthold.setPreferredSize(new Dimension(680,220));
        texthold.setOpaque(false);
        texthold2.setPreferredSize(new Dimension(680,220));
        texthold2.setOpaque(false);

        // Both labels are on the corresponding text holders
        label = new JLabel();
        label.setText("       Cookies & Cream      Mint Chocolate              Vanilla                   Chocolate     Strawberry Cheesecake     Green Tea");   
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setBounds(50,300,100,50);
        label.setFont(new Font("Arial",Font.BOLD,11));

        label2 = new JLabel();
        label2.setText("         Birthday Cake            Bubblegum             Tiger's Eye               Very Berry                 Lemon                  Rocky Road");   
        label2.setHorizontalAlignment(SwingConstants.LEFT);
        label2.setBounds(50,300,100,50);
        label2.setFont(new Font("Arial",Font.BOLD,11));
        // END OF FLAVOURS SECTION -----------------------------------
        
        /// BUTTON SECTION -----------------------------------
        // Holds all buttons, bottom right panel
        buttonholder = new JPanel();
        buttonholder.setOpaque(false);
        buttonholder.setPreferredSize(new Dimension(290,525));

        // Start button
        start = new JButton();
        start.setText("Start!");
        start.setPreferredSize(new Dimension(250,100));
        start.setFont(new Font("Arial",Font.BOLD,25));
        start.setBackground(new Color(255,242,118));
        stl = new StartListener(); // Add its listener
        start.addActionListener(stl);

        // Serve button
        serve = new JButton();
        serve.setText("Serve");
        serve.setPreferredSize(new Dimension(200,80));
        serve.setBounds(50, 0,200,80);
        serve.setFont(new Font("Arial",Font.BOLD,25));
        serve.setBackground(new Color(117,242,183));
        sel = new ServeListener(); // Add its listener
        serve.addActionListener(sel);
        
        // Scrap button
        scrap = new JButton();
        scrap.setText("Scrap");
        scrap.setPreferredSize(new Dimension(200,80));
        scrap.setFont(new Font("Arial",Font.BOLD,25));
        scrap.setBounds(50,100,200,80);
        scrap.setBackground(new Color(255,122,149));
        scl = new ScrapListener();
        scrap.addActionListener(scl);
        // END OF BUTTON SECTION ----------------------------------

        
        // Adding all top panel items
        this.add(spacer);
        spacer.add(custspacer);
        spacer.add(custorderpanel);
        custorderpanel.add(custorder);
        spacer.add(completedorderspanel);
        completedorderspanel.add(completedorders);

        // Adding all bottom panels items
        this.add(flavourtext);
        this.add(buttonholder);
        flavourtext.add(texthold);
        flavourtext.add(texthold2);
        texthold.add(label);
        texthold2.add(label2);
        buttonholder.add(start);

    }

    /// 
    /// Paint Component
    ///     Draws all parts
    /// 
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        
        // Menu Dividers
        Line menu = new Line(0,520,1000,520,Color.BLACK);
        menu.draw(g);
        Line menudiv = new Line(700,520,700,800,Color.BLACK);
        menudiv.draw(g);
        
        // Cone Drawing
        Triangle cone = new Triangle(775,310,925,310,852,505,new Color(226,195,109)); 
        cone.draw(g,true);
        new Line(787,335,913,335,new Color(216,176,95)).draw(g); // Lines On Cone
        new Line(819,335,852,497,new Color(216,176,95)).draw(g);
        new Line(881,335,852,497,new Color(216,176,95)).draw(g);

        // Adding all Flavour Selections
        new FlavourSelect(40,565, cc).draw(g); // Slot 1
        new FlavourSelect(40,670,bc).draw(g); // Slot 7
        
        new FlavourSelect(145,565,mc).draw(g); // 2
        new FlavourSelect(145,670,bg).draw(g); // 8

        new FlavourSelect(250,565,vn).draw(g); // 3
        new FlavourSelect(250,670,te).draw(g); // 9

        new FlavourSelect(355,565,ch).draw(g); // 4
        new FlavourSelect(355,670,vb).draw(g); // 10

        new FlavourSelect(460,565,sc).draw(g); // 5
        new FlavourSelect(460,670,lm).draw(g); // 11

        new FlavourSelect(565,565,gt).draw(g); // 6
        new FlavourSelect(565,670,rr).draw(g); // 12


        // Draw Scoops on cone
        for (Scoop s : scoopsCone)
            s.draw(g);

        // Draw Customer parts
        for (Object s : customerDrawings)
        {
            if (s instanceof RoundRect roundRect)
                roundRect.draw(g);
            else if (s instanceof Circle)
                ((Circle)s).draw(g);
            else if (s instanceof Line)
                ((Line)s).draw(g);
            else if (s instanceof FlavourOrder)
                ((FlavourOrder)s).draw(g);
        }
        
    }
    /////////////////////////////////////////////////////////////////


    ///
    /// MouseClicked
    ///     Tracks if Flavour Selections are clicked, and adds it to coreresponding parts
    /// 
    public class MouseClicker implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent m) 
        {
            int x = m.getX();
            int y = m.getY();
            if(selected < 4 && gamestart)
            {
                for (int i = 0; i<12;i++)
                {
                    if (convert[i].bounds.contains(x,y))
                    {
                        selected++;
                        playerorder.add(convert[i]);
                        scoopsCone.add( new Scoop(selected,convert[i]));
                    }
                }
                

                // scoop code here
                // base the y-axis off the selected counter
            }
            // Not less than 4? Does nothing
            
            repaint();
        }

        @Override
        public void mousePressed(MouseEvent m) {}

        @Override
        public void mouseReleased(MouseEvent m) {} 

        @Override
        public void mouseEntered(MouseEvent m) {}

        @Override
        public void mouseExited(MouseEvent m) {}
        
    }
    ///////////////////////////////////////////////////////

    ///
    /// StartListener
    ///     Activation when the Start button is clicked, loads in other buttons
    ///     and starts a customer run
    /// 
    private class StartListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            buttonholder.remove(start);
            buttonholder.add(serve);
            buttonholder.add(scrap);

            // run a customer class
            newCustomer();
            // allow click inputs
            gamestart = true;
            repaint();
        }
    }

    ///
    /// ServeLisstener
    ///     Submits the player's cone, and checks if it matches the order odff the customer's (must be 4 long too)
    ///     Adds the appropraite Check or X and updates the counter if correct
    ///     Then load in new customer
    /// 
    private class ServeListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // Starts as true
            boolean matches = true;

            if (selected == 4)
            {
                for (int i = 0; i<4;i++)
                {
                    if (! (customerorder.get(i)).equals((playerorder.get(i))))
                    {
                        // Toogle false if something doesn't match
                        matches = false;
                        break;
                    }
      
                }
            }
            else
            {matches = false;} // Toggle false if not 4 long

            // Reset values
            customerDrawings.clear();
            scoopsCone.clear();
            playerorder.clear();
            selected = 0;

            // Symbol Boundries: x=243, y= 430, w&h=30
            if (matches)
            {   
                // Inc. correct counter
                correct++;
                System.out.println("Correct");

                // Check Mark, add to new frame
                customerDrawings.add(new Line(243,450,256,460,new Color(27,195,117)));
                customerDrawings.add(new Line(273,430,256,460,new Color(27,195,117)));
             
            }
            else if (!matches)
            {
                System.out.println("Wrong");
                
                // X Mark, add to new frame
                customerDrawings.add(new Line(243,430,273,460,new Color(210,58,114)));
                customerDrawings.add(new Line(273,430,243,460,new Color(210,58,114)));
            }
            
            completedorders.setText(" Completed Orders: "+correct);
    
            // Load in new customer
            newCustomer();

            repaint();
        }
    }

    /// 
    /// ScrapListener
    ///     Resets the player's inputs
    ///     Including their current order, the scoop layout, and selected counter
    /// 
    private class ScrapListener implements ActionListener
    {   
        @Override
        public void actionPerformed(ActionEvent e)
        {
            playerorder.clear();
            selected = 0;
            // Remove all scoops
            scoopsCone.clear();

            repaint();
        }
    }

    ///
    /// newCustomer
    ///     Adds new customer to display
    ///     Adds the drawing components to the new display
    ///     Removes old order, randomizes new order, and adds it to matching, drawing, and text
    /// 
    /// 
    public void newCustomer()
    {  
        // Clear old order
        customerorder.clear();
        Flavour fl;
        String str = "       "; 

        // Order Bubble
        customerDrawings.add(new RoundRect(170,170,510,150,50,50,Color.BLACK));
        // Face
        customerDrawings.add(new Circle(30,195,110,110,Color.BLACK)); // head
        customerDrawings.add(new Circle(57,250,10,10,Color.BLACK)); // eyes
        customerDrawings.add(new Circle(103,250,10,10,Color.BLACK));
        customerDrawings.add(new Line(85,285,77,277,Color.BLACK)); // mouth
        customerDrawings.add(new Line(85,285,93,277,Color.BLACK));

        for (int i = 0; i<4;i++)
        {
            // Get rand flavour, bound = 12 means 0 to 11 inclusive
            fl = convert[rand.nextInt(12)];
            customerorder.add(fl);
            customerDrawings.add(new FlavourOrder(i,fl));
            str+=fl.name+"  ";//Add to string and space correctly
        }
        // Add into text display
        custorder.setText(str);

    }
}
