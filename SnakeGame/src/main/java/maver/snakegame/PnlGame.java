package maver.snakegame;

import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PnlGame extends JPanel implements ActionListener, KeyListener{
    
    private Timer timer;
    
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean gameOver=false;
    
    private int[] snakeXLenght = new int[750];
    private int[] snakeYLenght = new int[750];
    
    private Random random = new Random();
    int minXforApple = 1;
    int maxXforApple = 30;
    int minYforApple = 3;
    int maxYforApple = 22;
    
    private int appleX,appleY;
    private int move = 0;
    private int delay;
    private int lenghtOfSnake = 3;
    
    public PnlGame(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        this.delay = 80;
        timer = new Timer(this.delay, this);
        timer.start();
        newApple();
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        
        g.drawRect(25,10,750,55);
        g.drawRect(25,75,750,500);
        
        if(move == 0){
            snakeXLenght[0] = 100;
            snakeXLenght[1] = 75;
            snakeXLenght[2] = 50;
            
            snakeYLenght[0] = 100;
            snakeYLenght[1] = 100;
            snakeYLenght[2] = 100;
            
            move++;
        }
        
        
        g.setColor(Color.red);
        g.fillRect(snakeXLenght[0],snakeYLenght[0],25,25);
            
        g.setColor(Color.black);
        for(int i=1; i<lenghtOfSnake;i++)g.fillOval(snakeXLenght[i], snakeYLenght[i],25,25);
        
        g.setColor(Color.red);
        g.fillOval(appleX, appleY, 25, 25);
        
        if(gameOver){
            g.setColor(Color.red);
            g.setFont(new Font("ARIAL",Font.BOLD,50));
            g.drawString("GAME OVER",250,this.getHeight()/2-20);
            
            g.setFont(new Font("arial",Font.PLAIN,20));
            g.drawString("Press space to restart", 300,this.getHeight()/2+30);
        }
        
        g.dispose();
    }
    


    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=lenghtOfSnake;i>0;i--){
            snakeXLenght[i]=snakeXLenght[i-1];
            snakeYLenght[i]=snakeYLenght[i-1];
        }
        if(up){
            snakeYLenght[0] = snakeYLenght[0]-25;
        }
        else if(down){
            snakeYLenght[0] = snakeYLenght[0]+25;
        }
        else if(left){
            snakeXLenght[0] = snakeXLenght[0]-25;
        }
        else{
            snakeXLenght[0] = snakeXLenght[0]+25;
        }
        
        if(snakeXLenght[0]>750)snakeXLenght[0]=25;

        if(snakeXLenght[0]<25)snakeXLenght[0]=750;
        
        if(snakeYLenght[0]<75)snakeYLenght[0]=550;
        
        if(snakeYLenght[0]>550)snakeYLenght[0]=75;
        
        collidesWithApple();
        collidesWithBody();
        repaint();
    }

    

    @Override
    public void keyPressed(KeyEvent e) {
        
        if(e.getKeyCode()==KeyEvent.VK_SPACE && gameOver==true)restart();
        
        if(e.getKeyCode()==KeyEvent.VK_UP && down!=true){
            up=true;
            down=false;
            left=false;
            right=false;
            
        }
        else if(e.getKeyCode()==KeyEvent.VK_DOWN && up!=true){
            up=false;
            down=true;
            left=false;
            right=false;
        }
        else if(e.getKeyCode()==KeyEvent.VK_LEFT && right!=true){
            up=false;
            down=false;
            left=true;
            right=false;
        }
        else if(e.getKeyCode()==KeyEvent.VK_RIGHT && left!=true){
            up=false;
            down=false;
            left=false;
            right=true;
        }
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void newApple() {
        appleX = 25 * (random.nextInt((maxXforApple - minXforApple) + 1) + minXforApple);
        appleY = 25 * (random.nextInt((maxYforApple - minYforApple) + 1) + minYforApple);
        for(int i=lenghtOfSnake;i>=0;i--){
            if(snakeXLenght[i]==appleX && snakeYLenght[i]==appleY)newApple();
        }
    }

    private void collidesWithApple() {
        if(snakeXLenght[0]==appleX && snakeYLenght[0]==appleY){
            newApple();
            lenghtOfSnake++;
        }
    }

    private void collidesWithBody() {
        for(int i=lenghtOfSnake;i>0;i--){
            if(snakeXLenght[i]==snakeXLenght[0] && snakeYLenght[i]==snakeYLenght[0]){
                timer.stop();
                gameOver=true;
            }
        }
    }

    private void restart() {
        move=0;
        lenghtOfSnake=3;
        up=false;
        down=false;
        left=false;
        right=true;
        gameOver=false;
        timer.start();
    }
}