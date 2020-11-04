/**Project: Jerry Tac Toe
* Author: Casey Sobecks
*
* This project works by doing the following. First the gui is set up with the proper background, button, restart, and exit buttons. 
* It then initializes to ask the user who should go first. Following this, The game will begin. 
*
* For this code, the AI is very stupid. It will check if 1 is open if not then 2 and so on until it finds and open position. It is possible to lose, but very difficult.
* 
* Following a move, the button handler will check for a win conditions. If there is one, the winner will be diplayed and no more moves will be made.
* If there are no win conditions, the player will switch. This will go on until a win condition or a tie. The restart and exit buttons will work similarly.
* If pressed, the handler will either reinitialize the game (restart) or will exit successfully. That's the whole thing. Have fun!
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JTTGUI extends JFrame
{
    //declare variables
    private JLabel result;
    private static JLabel user=new JLabel(new ImageIcon("User.png"));
    private static JLabel computer=new JLabel(new ImageIcon("comp.png"));
    private static JButton[] buttons;
    private JButton exitbutton;
    private JButton startbutton;
    private ButtonHandler[] buttonhandler;
    private ExitHandler exithandler;
    private StartHandler starthandler;
    private ImageIcon unpressed=new ImageIcon("unpressed.png");
    private ImageIcon userpress=new ImageIcon("userpress.png");
    private ImageIcon comppress=new ImageIcon("comppress.png");    
    private static boolean ohs;
    private boolean gameOver;

    //set up gui
    public JTTGUI()
    {   
        //set title and bounds and closing condition
        setTitle("JerryTacToe: MS Paint Edition");
        setSize(970,570);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //set background
        setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon("background.png")));
        setLayout(null);
        
        //set up buttons
        buttons= new JButton[9];
        buttonhandler=new ButtonHandler[9];
        for(int i=0; i<9; i++)
        {
            buttons[i]=new JButton("",unpressed);
            buttonhandler[i]=new ButtonHandler();
            buttons[i].addActionListener(buttonhandler[i]);
        }

        ImageIcon exit=new ImageIcon("exit.png");
        exitbutton=new JButton("",exit);
        exithandler=new ExitHandler();
        exitbutton.addActionListener(exithandler);

        ImageIcon startIcon=new ImageIcon("start.png");
        startbutton=new JButton("",startIcon);
        starthandler=new StartHandler();
        startbutton.addActionListener(starthandler);

        result=new JLabel("Game In Progress", SwingConstants.CENTER);
        result.setForeground(Color.black);

        //set words on buttons
        for(int i=0; i<9; i++)
        {
            add(buttons[i]);
            buttons[i].setHorizontalTextPosition(SwingConstants.CENTER);
            buttons[i].setOpaque(false);
            buttons[i].setContentAreaFilled(false);
            buttons[i].setBorderPainted(false);
        }

        //add user and computer win graphics
        add(user);
        add(computer);
        user.setBounds(70,200,80,80);
        user.setVisible(false);
        computer.setBounds(70,200,80,80);
        computer.setVisible(false);

        add(startbutton);
        add(result);
        add(exitbutton);

        //so many boundary conditions for buttons and stuff ugh
        result.setBounds(30,140,160,80);
		exitbutton.setBounds(700,150,220,90);
		startbutton.setBounds(700,250,220,100);

		buttons[0].setBounds(80,20,120,120);
		buttons[1].setBounds(390,20,120,120);
		buttons[2].setBounds(700,20,120,120);
		buttons[3].setBounds(220,200,120,120);
		buttons[4].setBounds(390,200,120,120);
		buttons[5].setBounds(550,200,120,120);
		buttons[6].setBounds(80,360,120,120);
		buttons[7].setBounds(390,360,120,120);
        buttons[8].setBounds(700,360,120,120);
        
        exitbutton.setHorizontalTextPosition(SwingConstants.CENTER);
        exitbutton.setOpaque(false);
        exitbutton.setContentAreaFilled(false);
        exitbutton.setBorderPainted(false);
        startbutton.setHorizontalTextPosition(SwingConstants.CENTER);
        startbutton.setOpaque(false);
        startbutton.setContentAreaFilled(false);
        startbutton.setBorderPainted(false);

        result.setFont(result.getFont().deriveFont(14.0f));

        //initialize program
        initialize();
    }

    public void initialize()
    {
        //set default conditions
        ohs=true;
        gameOver=false;

        user.setVisible(false);
        computer.setVisible(false);

        //set a value for each button
        for(int i=0;i<9;i++)
        {
            char c=(char)('0'+i+1);
            buttons[i].setText(""+c);
            buttons[i].setIcon(unpressed);
        }

        //set result empty
        result.setText("");
        //initialize user 
        startprompt();
        //make program visible
        setVisible(true);
    }

    public static void startprompt()
    {
        //get input on who should play first
        String input=JOptionPane.showInputDialog("Oh boy! a new game! Who's going first??\nA for user and B for computer");
        //check input conditions
        if(input==null||(input!=null&&("".equals(input)))) //check if cancel is pressed
        {
            System.exit(0);
        }
        else if(input.equals("A")||input.equals("a")) //user
        {
            ohs=true;
        }
        else if(input.equals("B")||input.equals("b")) //computer
        {
            ohs=false;
            computermove();
        }
        else //incorrect input
        {
            JOptionPane.showMessageDialog(null,"Just pick one of the options kid.","alert",JOptionPane.ERROR_MESSAGE);
            startprompt(); //recursion????
        }
    }

    //main that runs the gui
    public static void main(String[] args) throws InterruptedException
    {
        JTTGUI gui=new JTTGUI(); //main that runs the gui
    }

    //handler for button pressses
    private class ButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //check if game is over
            if(gameOver)
            {
                return;
            }

            //get button press
            JButton pressed=(JButton) e.getSource();
            String text=pressed.getText();
            
            //some error in button press
            if(text.equals("O")||text.equals("X")) {return;}

            if(ohs)//user press
            {
                pressed.setText("O");
                pressed.setIcon(userpress);
            }
            else //computer press
            {
                pressed.setText("X");
                pressed.setIcon(comppress);
            }

            if(checkwin())//if a win, check 
            {
                //set game over
                gameOver=true;
                
                //check for a draw
                if(!buttons[0].getText().equals("1")
                    && !buttons[1].getText().equals("2")
                    && !buttons[2].getText().equals("3")
                    && !buttons[3].getText().equals("4")
                    && !buttons[4].getText().equals("5")
                    && !buttons[5].getText().equals("6")
                    && !buttons[6].getText().equals("7")
                    && !buttons[7].getText().equals("8")
                    && !buttons[8].getText().equals("9")) 
                    {
                        result.setText("DRAW GAME");
                    }
                else //no draw
                {
                    if(ohs) //winner was user
                    {
                        user.setVisible(true);
                        computer.setVisible(false);
                        result.setText("User Wins!");
                    }
                    else //computer was winner
                    {
                        user.setVisible(false);
                        computer.setVisible(true);
                        result.setText("Computer Wins!");
                    }
                }
            }
            else //no win condition switch users
            {
                ohs=!ohs;
                if(ohs){}
                else{computermove();}
            }
        }

    }
    
    //exit button handler
    private class ExitHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0); //if button pressed, close game
        }
    }
    
    //start or restart handler
    private class StartHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            initialize(); //rerun initialize if the game is to restart
        }
    }

    //check for a win condition
    public boolean checkwin() 
    {
        //check if there is three in a row for any of the 9 ways to win
        if(buttons[0].getText().equals(buttons[1].getText()) 
            && buttons[1].getText().equals(buttons[2].getText()))
        {
            return true;
        }
        else if(buttons[0].getText().equals(buttons[4].getText()) 
                && buttons[4].getText().equals(buttons[8].getText()))
        {
            return true;
        }
        else if(buttons[0].getText().equals(buttons[3].getText()) 
                && buttons[3].getText().equals(buttons[7].getText()))
        {
            return true;
        }
        else if(buttons[1].getText().equals(buttons[3].getText()) 
                && buttons[3].getText().equals(buttons[6].getText()))
        {
            return true;
        }
        else if(buttons[1].getText().equals(buttons[5].getText()) 
                && buttons[5].getText().equals(buttons[8].getText()))
        {
            return true;
        }
        else if(buttons[2].getText().equals(buttons[4].getText()) 
                && buttons[4].getText().equals(buttons[6].getText()))
        {
            return true;
        }
        else if(buttons[2].getText().equals(buttons[5].getText()) 
                && buttons[5].getText().equals(buttons[7].getText()))
        {
            return true;
        }
        else if(buttons[3].getText().equals(buttons[4].getText()) 
                && buttons[4].getText().equals(buttons[5].getText()))
        {
            return true;
        }
        else if(buttons[6].getText().equals(buttons[7].getText()) 
                && buttons[7].getText().equals(buttons[8].getText()))
        {
            return true;
        }
        //if the board is full and there is a draw, also a 'win' condition
        else if(!buttons[0].getText().equals("1") 
                && !buttons[1].getText().equals("2")
                && !buttons[2].getText().equals("3")
                && !buttons[3].getText().equals("4")
                && !buttons[4].getText().equals("5")
                && !buttons[5].getText().equals("6")
                && !buttons[6].getText().equals("7")
                && !buttons[7].getText().equals("8")
                && !buttons[8].getText().equals("9")) 
        {
            return true;
        }
        else {return false;} //no win 
    }
    
    //very bad AI program
    public static void computermove()
    {
        if(buttons[0].getText().equals("1"))
        {buttons[0].doClick();}
        else if(buttons[1].getText().equals("2"))
        {buttons[1].doClick();}
        else if(buttons[2].getText().equals("3"))
        {buttons[2].doClick();}
        else if(buttons[3].getText().equals("4"))
        {buttons[3].doClick();}
        else if(buttons[4].getText().equals("5"))
        {buttons[4].doClick();}
        else if(buttons[5].getText().equals("6"))
        {buttons[5].doClick();}
        else if(buttons[6].getText().equals("7"))
        {buttons[6].doClick();}
        else if(buttons[7].getText().equals("8"))
        {buttons[7].doClick();}
        else if(buttons[8].getText().equals("9"))
        {buttons[8].doClick();}
        else {}
    }
}