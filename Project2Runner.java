import javax.swing.JFrame;

public class Project2Runner 
{
    
    /*
     * Name: Logan Toy
     * Student ID: 501 289 053
     

     ******** Project Description ********
     * 
     * Describe in plain English the overall program/program in a paragraph or 2.
     * 
     * The program should create a simulation of an ice cream parlor.
     * The user must respond to customers with randomized orders of 4 flavours.
     * The user may then add flavours in the correct order to a cone on screen, and hit a "Serve" button to give it to the customer.
     * If the order was correct, it will increase a "Correct Orders" counter, otherwise it will not.
     * It should inform the player if they did the order correctly
     * There is also a "Scrap" button that allows the order to be reset.

     
     ******** Swing Requirement ********
     * 
     * Describe in 1 paragraph how your program satisfies the requirement that
     * there is at least 3 unique components. Be clear to identify in what
     * files and the lines number (just the starting line is fine) that the
     * components are defined on.
     * 
     * 'spacer' (JPanel, Program, 26)
     * 'custorder' (JLabel, Program, 39)
     * 'serve' (JButton, Program, 47)
     * 
     * These three components satisfy the requirements by being used to lay out various sections
     * on the screen, display text for the user to read, and take in a input from the user when they're ready to serve an order
     * 

      
     
     ******** 2D Graphics Requirement ********
     *
     * Describe in 1 paragraph how your program satisfies the requirement that
     * there is at least 1 JPanel used for drawing something. Be clear to
     * identify in what files and the line numbers that this panel is defined on.
     * 
     * 'p' (Program, 9)
     * 
     * On this JPanel, the visuals for the flavour selections, the customer's order, and the ice cream cone are drawn.
     * The visuals communicate to the user what the given order is, and what their current selections are.
     * 
     
     
     ******** Event Listener Requirement ********
     *
     * Describe in 1 paragraph how your program satisfies the requirement that
     * there is at least one ActionListener, and there is additionally at least
     * one MouseListener or ActionListener. Be clear to identify in what file
     * and the line numbers that these listeners are defined in.
     * 
     * 'ServeListener' (ActionListener, Program, 349)
     * 'MouseClicker' (MouseListener, Program, 277)
     * 
     * The ActionListener gives function to the 'Serve' button and registers if the user clicks the button.
     * If done, it will serve the order to the customer, check if it matches, and increment the correct counter accordingly.
     * The MouseListener listens to see if the user clicks inside one of the 12 flavour at the bottom of the screen, 
     * and adds the corresponding flavour it to the cone on the screen if one is clicked.
     * 
     * 
     * 
     ******** HOW TO USE THE PROGRAM ********
     * After running, click the 'Start' button.
     * A customer will appear with their order.
     * It is your task to fulfill this order, going from left to right.
     * Hit 'Serve' to give it to the customer, or 'Scrap' if a mistake was made.
     * There is no end point, so play to your heart's content.
     * 
     */

    public static void main(String[] args) 
    {
        JFrame f = new JFrame("Hundreds & Thousands");
            // Tell the frame to obey the close button
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.add(new Program());
            
            f.pack();
            f.setVisible(true);  
    }
}
