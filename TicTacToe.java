import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToe implements ActionListener, ItemListener
{
	JFrame jf ;
	JPanel jp1,jp2;
	JButton exit ;
	JButton[] arr = new JButton[9];
	JLabel jl1,jl2;
	JComboBox<String> mode , selectPlayer;
	int flag =0,startFlag1=0,easyFlag=0,hardFlag=0,counter=0,refreshflag=0,check =0,infoCheck =0,resultCounter1 =0,resultCounter2 =0,check1=0,check3=0,null1 =1,null2=0;
	int cpuStart =0;
	String [] player={"cpu","player"};
	String[] p = {"X","O"};
	int k,r,a=9,b=9,c=9;
	int [] array1 ={0,1,2};
	int [] array2 ={0,1,2,3,4,5,6,7,8};
	int [] array3 ={0,2,6,8};
	int [] array4 ={1,3,5,7};
	String str[]= new String[9];
	TicTacToe( String s)
	{
		jf = new JFrame(s);
		jp1 = new JPanel();
		jp2 = new JPanel();
		exit = new JButton("EXIT");
		jp1.setLayout(new GridLayout(3,3,0,0));
		jp2.setLayout(new GridLayout(5,1,10,10));
		jf.setSize(700,750);

		exit.addActionListener(this);
		
		for(int i =0;i<9;i++)
		{
			arr[i] = new JButton();
			arr[i].addActionListener(this);
			arr[i].setBackground(Color.gray);
			arr[i].setForeground(Color.white);
			jp1.add(arr[i]);
			str[i]="";
		}
				
		String[] sList = new String[] { "SELECT PLAYER" ,"TWO PLAYER","CPU" };
   		selectPlayer = new JComboBox<String>(sList);
    	selectPlayer.addItemListener(this);
		jp2.add(selectPlayer);
		
		String[] sList1 = new String[] { "SELECT LEVEL","EASY","HARD" };
   		mode = new JComboBox<String>(sList1);
    	mode.addItemListener(this);
		jp2.add(mode);
		mode.setVisible(false);
		
		jl1 = new JLabel("");
		jp2.add(jl1);
		jl2 = new JLabel("");
		jp2.add(jl2);
  		jp2.add(exit);		
		try 
		{
   			   UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());	 
		} 
		catch(Exception e) 
		{
      			System.out.println("Error setting Java LAF: " + e);
    	}
		jf.add(jp2 , BorderLayout.EAST);
		jf.add(jp1 , BorderLayout.CENTER);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		//jf.pack();
		//jf.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		jf.setExtendedState(JFrame.MAXIMIZED_VERT);
		//jf.setUndecorated(true);
		jf.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == exit)
		{
			System.exit(0);
		}
		if(startFlag1 ==1)
		{
			mech(e);
		}
		if(easyFlag ==1)
		{
			
			easy(e);
		}
		if(hardFlag ==1)
		{
			hard(e);
		}
		if(null1==1)
		{
			JOptionPane.showMessageDialog(null, "Choose Player First","WARNING", JOptionPane.INFORMATION_MESSAGE);
		}
		if(null2==1)
		{
			JOptionPane.showMessageDialog(null, "Choose Level First","WARNING", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	
	public  void itemStateChanged(ItemEvent  e)
{

		if(selectPlayer.getSelectedIndex() == 0)
		{
			startFlag1=0;
			hardFlag=0;
			easyFlag =0;
			resultCounter1 =0;
			resultCounter2 =0;
			check=0;
			check1=0;
			null1=1;
			null2=0;
			jl1.setText("");
			jl2.setText("");
			refresh();
		}
		if(selectPlayer.getSelectedIndex() == 1)
		{
			startFlag1=1;
			hardFlag=0;
			easyFlag =0;
			resultCounter1 =0;
			resultCounter2 =0;
			check1=0;
			null1=0;
			null2=0;
			if(check==0)
			{
			
				getTwoName();
				jl1.setText(player[0]+"\t"+resultCounter1);
				jl2.setText(player[1]+"\t"+resultCounter2);
			}
			refresh();
		}
		
		if(selectPlayer.getSelectedIndex() == 2)
		{
			mode.setVisible(true);
			check=0;
			resultCounter1 =0;
			resultCounter2 =0;
			null1=0;
			null2=0;
			if(check1==0)
			{
					getName();
					jl1.setText(player[0]+"\t"+resultCounter1);
					jl2.setText(player[1]+"\t"+resultCounter2);
				
			}
			refresh();
			if(mode.getSelectedIndex() == 0)
			{
				startFlag1=0;
				hardFlag=0;
				easyFlag =0;
				null2=1;
				refresh();
				
				
			}
			if(mode.getSelectedIndex() == 1)
			{
				refresh();
				hardFlag=0;
				startFlag1 =0;
				easyFlag =1;
				null2=0;
				
			}
			if(mode.getSelectedIndex() == 2)
			{
				refresh();
				hardFlag=1;
				startFlag1 =0;
				easyFlag =0;
				null2=0;
				
				
			}
		}
		else
		{
			mode.setVisible(false);
			refresh();
		}
       
      
    }

	public void mech(ActionEvent e)
	{
		for(int i=0;i<9;i++)
		{	
				if(e.getSource() == arr[i] )
				{
					if(flag == 0)
					{
						
						if(str[i]=="")
						{
							arr[i].setText("X");
							str[i]="X";
							flag=1;
							check();
						}
					}
					else
					{
						if(str[i]=="")
						{
							arr[i].setText("O");
							str[i]="O";
							flag=0;
							check();
						}
					}
				}
		}
	}
	public void easy(ActionEvent e)
	{
		for(int i=0;i<9;i++)
		{	int fl=0;
				if(e.getSource() == arr[i] )
				{
						if(str[i]=="")
						{
							arr[i].setText(p[k]);
							str[i]=p[k];
							refreshflag=0;
							check();
							fl=1;
						}
						if(fl==1)
						{
							if(refreshflag == 0)
							{	cpuPlayEasy();
								check();
							}
							fl=0;
						}
					
				}
		}
	}
	public void hard(ActionEvent e)
	{		
		for(int i=0;i<9;i++)
		{	
				if(e.getSource() == arr[i] )
				{
						if(str[i]=="")
						{
							arr[i].setText(p[k]);
							str[i]=p[k];
							refreshflag=0;
							check();
						}
						if(refreshflag == 0)
						{	cpuPlayHard();
							refreshflag =1;
							check();
						}
					
				}
		}
		
	}
	
	public void cpuPlayEasy()
	{
		int y= 0;
		while(y == 0)
		{
			int x =getRandom(array2);
			
			if(str[x]== "")
			{
				str[x] = p[r];
				arr[x].setText(p[r]);
				y=1;
			}
			
		}
	}
	
	public void cpuPlayHard()
	{
		if(p[r]=="X" && cpuStart ==0)
		{
			
			
				a = getRandom(array1);
				b = getRandom(array3);
				c = getRandom(array4);
			
			if(a==0)
			{
				if(str[4] == "")
				{
					str[4]=p[r];
					arr[4].setText(p[r]);
				}
				
			}
			if(a==1)
			{
				if(str[b] == "")
				{
					str[b]=p[r];
					arr[b].setText(p[r]);
				}
				
			}
			if(a==2)
			{
				
				if(str[c] == "")
				{
					str[c]=p[r];
					arr[c].setText(p[r]);
				}
			}
			cpuStart =1;
		}
		else
		{
			Board b = new Board();
			int p1 =0;
			for(int i=0;i<3;i++)
			{
				for(int j =0;j<3;j++)
				{ 
					if(str[p1] == p[r])
					{
						b.board[i][j] =1;
						
					}
					if(str[p1] == p[k])
					{
						b.board[i][j] =2;
					}
					p1++;
				}
			}
			b.minimax(0, 1); 
			b.placeAMove(b.computersMove, 1);
			
			if(b.computersMove.x == b.computersMove.y)
			{
				if(b.computersMove.x == 0)
				{
					str[b.computersMove.x] =p[r];
					arr[b.computersMove.x].setText(p[r]);
				}
				if(b.computersMove.x == 1)
				{
					str[4] =p[r];
					arr[4].setText(p[r]);
				}
				if(b.computersMove.x == 2)
				{
					str[8] =p[r];
					arr[8].setText(p[r]);
				}
				
			}
			if(b.computersMove.x == 0 &&( b.computersMove.y == 1 || b.computersMove.y == 2))
			{
				str[b.computersMove.y] =p[r];
				arr[b.computersMove.y].setText(p[r]);
			}
			if(b.computersMove.x == 1 && (b.computersMove.y == 0 || b.computersMove.y == 2))
			{
				if(b.computersMove.y == 0)
				{
					str[3] =p[r];
					arr[3].setText(p[r]);
				}
				if(b.computersMove.y == 2)
				{
					str[5] =p[r];
					arr[5].setText(p[r]);
				}
			}
			if(b.computersMove.x == 2 && (b.computersMove.y == 0 || b.computersMove.y == 1))
			{
				if(b.computersMove.y == 0)
				{
					str[6] =p[r];
					arr[6].setText(p[r]);
				}
				if(b.computersMove.y == 1)
				{
					str[7] =p[r];
					arr[7].setText(p[r]);
				}
			}
		}				
		
	}
	
	public void check()
	{
		if((str[0]== "X"&& str[1]== "X"&& str[2]== "X")||(str[0]== "X"&& str[4]== "X"&& str[8]== "X")||(str[0]== "X"&& str[3]== "X"&& str[6]== "X")
			||(str[3]== "X"&& str[4]== "X"&& str[5]== "X")||(str[6]== "X"&& str[4]== "X"&& str[2]== "X")||(str[7]== "X"&& str[4]== "X"&& str[1]== "X")
			||(str[8]== "X"&& str[5]== "X"&& str[2]== "X")||(str[6]== "X"&& str[7]== "X"&& str[8]== "X"))
		{
			if(infoCheck==1)
			{	
				String infoMessage = player[0] + " WIN";
				resultCounter1 ++ ;
				JOptionPane.showMessageDialog(null, infoMessage,"WIN", JOptionPane.INFORMATION_MESSAGE);
				jl1.setText(player[0]+resultCounter1);
				jl2.setText(player[1]+resultCounter2);
				refresh();
				infoBox();
			}
			else
			{
				String infoMessage = player[1] + " WIN";
				resultCounter2 ++ ;
				JOptionPane.showMessageDialog(null, infoMessage,"WIN", JOptionPane.INFORMATION_MESSAGE);
				jl1.setText(player[0]+resultCounter1);
				jl2.setText(player[1]+resultCounter2);
				refresh();
				infoBox();
			}
			
			
		}

		if((str[0]== "O"&& str[1]== "O"&& str[2]== "O")||(str[0]== "O"&& str[4]== "O"&& str[8]== "O")||(str[0]== "O"&& str[3]== "O"&& str[6]== "O")
			||(str[3]== "O"&& str[4]== "O"&& str[5]== "O")||(str[6]== "O"&& str[4]== "O"&& str[2]== "O")||(str[7]== "O"&& str[4]== "O"&& str[1]== "O")
			||(str[8]== "O"&& str[5]== "O"&& str[2]== "O")||(str[6]== "O"&& str[7]== "O"&& str[8]== "O"))
		{
			if(infoCheck==1)
			{	
				String infoMessage = player[1] + " WIN";
				resultCounter2 ++ ;
				JOptionPane.showMessageDialog(null, infoMessage,"WIN", JOptionPane.INFORMATION_MESSAGE);
				jl1.setText(player[0]+resultCounter1);
				jl2.setText(player[1]+resultCounter2);
				refresh();
				infoBox();
			}
			else
			{
				String infoMessage = player[0] + " WIN";
				resultCounter1 ++ ;
				JOptionPane.showMessageDialog(null, infoMessage,"WIN", JOptionPane.INFORMATION_MESSAGE);
				jl1.setText(player[0]+resultCounter1);
				jl2.setText(player[1]+resultCounter2);
				refresh();
				infoBox();
				
			}
		}
		for(int i=0;i<9;i++)
		{
			if(str[i]== "X"|| str[i] == "O")
			{
				if(i==8)
				{
					jl1.setText(player[0]+resultCounter1);
					jl2.setText(player[1]+resultCounter2);
					JOptionPane.showMessageDialog(null, "MATCH DRAW ","DRAW", JOptionPane.INFORMATION_MESSAGE);
					refresh();
					infoBox();
				}
			}
			else
			{
				break;
			}
		}
	}
	public void getTwoName()
	{
		JTextField xField = new JTextField(5);
		JTextField yField = new JTextField(5);

		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("Player one "));
		myPanel.add(xField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Player two"));
		myPanel.add(yField);

		int result = JOptionPane.showConfirmDialog(null, myPanel,"Please Enter PLAYERS name", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) 
		{
			
			if(xField.getText().equals(""))
			{
				player[0]= "Player one ";
			}
			else
			{
				player[0]= xField.getText()+" " ;
			}
			if(yField.getText().equals(""))
			{
				player[1]= "Player Two ";
			}
			else
			{
				player[1]= yField.getText()+" ";
			}
			
			
		}
		else
		{
			player[0]= "Player one ";
			player[1]= "Player Two ";
		}
		check=1;
		infoBox();
	}
	public int getRandom(int[] array) 
	{
		int rnd = new Random().nextInt(array.length);
		return array[rnd];
	}

	public void refresh()
	{
		for(int i= 0;i<9;i++)
		{
			str[i]="";
			arr[i].setText("");
			flag=0;			
		}
		refreshflag =1;
	}
	
	public void infoBox( )
    {
		
		if (infoCheck ==0)
		{
			k =0 ;
			r = 1;
			infoCheck=1;
		}
		else
		{
			k=1;
			r=0;
			infoCheck =0;
		}
		String infoMessage = player[0]+" = " +p[k]+"\n"+ player[1]+" = " +p[r];
		
		String titleBar = "Get Ready ";
		
        JOptionPane.showMessageDialog(null, infoMessage,titleBar, JOptionPane.INFORMATION_MESSAGE);
		if(p[r]=="X" && check1 == 1)
		{
			if(hardFlag == 1)
			{
				cpuPlayHard();
				cpuStart=0;
			}
			else
				cpuPlayEasy();
		}
    }
	
	public void getName()
	{
		player[1]= "CPU ";
		player[0]= "Player One ";
		
					jl1.setText(player[0]+"\t"+resultCounter1);
					jl2.setText(player[1]+"\t"+resultCounter2);
				
		check1 = 1;
		infoCheck =0;
		infoBox();
	}
	

	public static void main(String... as)
	{
		Board b = new Board();
		 new TicTacToe("GAME");
	}
}

class Point {

    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

   
}

class Board  {
 
    List<Point> availablePoints;
    int[][] board = new int[3][3];

    public boolean isGameOver() 
	{
         return (hasXWon() || hasOWon() || getAvailableStates().isEmpty());
    }

    public boolean hasXWon() {
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == 1) || (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == 1)) {
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if (((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == 1)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == 1))) {
                return true;
            }
        }
        return false;
    }
	
	public boolean hasOWon() {
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == 2) || (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == 2)) {
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if ((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == 2)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == 2)) {
                return true;
            }
        }

        return false;
    }

    public List<Point> getAvailableStates() {
        availablePoints = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (board[i][j] == 0) {
                    availablePoints.add(new Point(i, j));
                }
            }
        }
        return availablePoints;
    }

    public void placeAMove(Point point, int player) {
        board[point.x][point.y] = player;  
    } 
        
    Point computersMove; 
    
    public int minimax(int depth, int turn) {  
        if (hasXWon()) return +1; 
        if (hasOWon()) return -1;

        List<Point> pointsAvailable = getAvailableStates();
        if (pointsAvailable.isEmpty()) return 0; 
 
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
         
        for (int i = 0; i < pointsAvailable.size(); ++i) {  
            Point point = pointsAvailable.get(i);   
            if (turn == 1) { 
                placeAMove(point, 1); 
                int currentScore = minimax(depth + 1, 2);
                max = Math.max(currentScore, max);
                
               // if(depth == 0)System.out.println("Score for position "+(i+1)+" = "+currentScore);
                if(currentScore >= 0){ if(depth == 0) computersMove = point;} 
                if(currentScore == 1){board[point.x][point.y] = 0; break;} 
                if(i == pointsAvailable.size()-1 && max < 0){if(depth == 0)computersMove = point;}
            } else if (turn == 2) {
                placeAMove(point, 2); 
                int currentScore = minimax(depth + 1, 1);
                min = Math.min(currentScore, min); 
                if(min == -1){board[point.x][point.y] = 0; break;}
            }
            board[point.x][point.y] = 0; 
        } 
        return turn == 1?max:min;
    }  
}



	