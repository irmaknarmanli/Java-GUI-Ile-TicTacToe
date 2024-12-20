package yeniOlsun;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TicTacToe extends JFrame implements ActionListener {

	static int counter = 1;
	static boolean[] clicked = new boolean[9];
	static String[] names = new String[9];
	static String board;
	static JButton[] buttons;
	static JPanel panel = new JPanel();
	static JLabel label1 = new JLabel();
	static JLabel label2 = new JLabel();
	static char currentplayersym;
	
	public static void main(String[] args) {
	
		JFrame frame = new JFrame();
		frame.setTitle("TicTacToe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		frame.setLayout(new BorderLayout());
		
		ImageIcon bayyengec = new ImageIcon("bayyengec.png");
		frame.setIconImage(bayyengec.getImage());
		
		panel.setBackground(new Color(255,153,153) );
		panel.setPreferredSize(new Dimension(100,100));
		
		frame.add(panel, BorderLayout.NORTH);
		
		JPanel gridPanel = new JPanel(new GridLayout(3, 3, 5, 5));
		label1.setHorizontalAlignment(JLabel.CENTER); //the whole label
		label1.setVerticalAlignment(JLabel.TOP);
		label1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		label1.setText("click to start (x's  turn)");
		
		buttons = new JButton[9];
		
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton();
			buttons[i].setFocusable(false);
			buttons[i].setBackground(Color.red);
			gridPanel.add(buttons[i]);
			buttons[i].addActionListener(new TicTacToe());
		}
		
		frame.add(gridPanel, BorderLayout.CENTER);
		panel.add(label1, BorderLayout.NORTH);
		panel.add(label2, BorderLayout.SOUTH);
		frame.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		for(int i = 0; i < buttons.length; i++) {
			if(e.getSource() == buttons[i] && clicked[i] == false) {
				currentplayersym = (counter % 2 != 0) ? 'x' : 'y';
				char notcurrentsym = (counter % 2 == 0) ? 'x' : 'y';
				label1.setText(notcurrentsym + "'s turn");
				label1.setHorizontalTextPosition(JLabel.CENTER); // text to the image
				label1.setVerticalTextPosition(JLabel.TOP);
				label1.setHorizontalAlignment(JLabel.CENTER); //the whole label
				label1.setVerticalAlignment(JLabel.TOP);
				label1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
				
				clicked[i] = true;
				buttons[i].setText(Character.toString(currentplayersym));
				buttons[i].setForeground(Color.black);
				buttons[i].setFont(new Font("Comic Sans MS", Font.PLAIN, 60));
				counter++;
				if(checkWin()) {
					label1.setText("congrats! " + currentplayersym + " has won");
					startResetTimer();
	                
				}
				else if (allButtonsPressed()) {
	                label1.setText("it's a draw! all buttons have been pressed");
	                startResetTimer();
	            }
			}
		}	
} 
	public static boolean checkWin() {
		 for (int i = 0; i < 3; i++) {
		        if (checkLine(i, 0, i, 1, i, 2)) {
		            return true;
		        }
		    }

		    for (int i = 0; i < 3; i++) {
		        if (checkLine(0, i, 1, i, 2, i)) {
		            return true;
		        }
		    }
		    if (checkLine(0, 0, 1, 1, 2, 2) || checkLine(0, 2, 1, 1, 2, 0)) {
		        return true;
		    }

		    return false;
		}
	
	private static boolean checkLine(int x1, int y1, int x2, int y2, int x3, int y3) {
	    return clicked[x1 * 3 + y1] && clicked[x2 * 3 + y2] && clicked[x3 * 3 + y3] &&
	           buttons[x1 * 3 + y1].getText().equals(Character.toString(currentplayersym)) &&
	           buttons[x2 * 3 + y2].getText().equals(Character.toString(currentplayersym)) &&
	           buttons[x3 * 3 + y3].getText().equals(Character.toString(currentplayersym));
	}
	private static void resetGame() {
        counter = 1;
        clicked = new boolean[9];
        names = new String[9];
        board = "";
        currentplayersym = ' ';
        label1.setText("Click to start (X's turn)");
        label2.setText("");

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText("");
            buttons[i].setBackground(Color.red);
            clicked[i] = false;
        }
    }
	private static boolean allButtonsPressed() {
		for (int i = 0; i < clicked.length; i++) {
	        if (!clicked[i]) {
	            return false;
	        }
	    }
	    return true;
	}
	private static void startResetTimer() {
	    Timer timer = new Timer(1000, new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
	            resetGame();
	            ((Timer) evt.getSource()).stop();
	        }
	    });
	    timer.setRepeats(false);
	    timer.start();
	}
}
	