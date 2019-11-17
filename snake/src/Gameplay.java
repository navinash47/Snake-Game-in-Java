
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author avinash
 */
class Gameplay extends JPanel implements KeyListener,ActionListener {
    private ImageIcon titleImage;
    
    private int[] snakexlength= new int[750];
    private int[] snakeylength= new int[750];
    
    private boolean left=false;
    private boolean right= false;
    private boolean up= false;
    private boolean down= false;
    private int moves=0;
    private ImageIcon rightmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;
    private ImageIcon leftmouth;
    
    private int score=0;
    
    private int[] enemyxpos={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private int[] enemyypos={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
    
    private Random random= new Random();
    private int xpos=random.nextInt(34);
    private int ypos=random.nextInt(23);
    private ImageIcon enemyimage;
    
    private boolean hit=false;
    private int lengthofsnake=3;
    private Timer timer;
    private int delay=150;
    private ImageIcon snakeimage;
    
    private boolean gameover=false;
    
    public Gameplay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusableTraversalKeyEnabled(false);
        
        timer=new Timer(delay,this);
        timer.start();
    }
    public void paint (Graphics g){
        if(moves==0){
            snakexlength[2]=50;
            snakexlength[1]=75;
            snakexlength[0]=100;

            snakeylength[2]=100;
            snakeylength[1]=100;
            snakeylength[0]=100;            
            
        }
        g.setColor(Color.white);
        g.drawRect(24,10,851,55);
        
        titleImage =new ImageIcon("/home/avinash/Desktop/snake/snaketitle.jpg");
        titleImage.paintIcon(this,g,25,11);
        
        //for borders
        g.setColor(Color.WHITE);
        g.drawRect(0,49,901,627);
        
        //for backgorund
        
        g.setColor(Color.BLACK);
        g.fillRect(1,50,900,625);
        
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial",Font.PLAIN,14));
        g.drawString("Scores: "+score,780,30); 
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial",Font.PLAIN,14));
        g.drawString("Length: "+lengthofsnake,780,50);  
       
        rightmouth=new ImageIcon("/home/avinash/Desktop/snake/rightmouth.png");
        rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
        for(int a=0;a<lengthofsnake;a++){
            if(a==0 && right){
                 rightmouth=new ImageIcon("/home/avinash/Desktop/snake/rightmouth.png");
                 rightmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }
            if(a==0 && left){
                 leftmouth=new ImageIcon("/home/avinash/Desktop/snake/leftmouth.png");
                 leftmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }
            if(a==0 && up){
                 upmouth=new ImageIcon("/home/avinash/Desktop/snake/upmouth.png");
                 upmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }
            if(a==0 && down){
                 downmouth=new ImageIcon("/home/avinash/Desktop/snake/downmouth.png");
                 downmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }
            if(a!=0){
                snakeimage=new ImageIcon("/home/avinash/Desktop/snake/snakeimage.png");
                snakeimage.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }
            
        }
        enemyimage=new ImageIcon("/home/avinash/Desktop/snake/enemy.png");
        if(enemyxpos[xpos]==snakexlength[0]&&enemyypos[ypos]==snakeylength[0]){
            lengthofsnake++;
            score++;
            xpos=random.nextInt(34);
            ypos=random.nextInt(23);
        }
        enemyimage.paintIcon(this, g, enemyxpos[xpos], enemyypos[ypos]);
        
        for(int b=1;b<lengthofsnake;b++){
            if(snakexlength[b]==snakexlength[0]&&snakeylength[b]==snakeylength[0]){
                right=false;
                left= false;
                up=false;
                down=false;
                hit=false;
                gameover=true;
                moves=0;
                g.setColor(Color.red);
                g.setFont(new Font ("arial",Font.BOLD,50));
                g.drawString("GAME OVER",300 ,300);
                 g.setColor(Color.white);
                g.setFont(new Font ("arial",Font.BOLD,20));
                g.drawString("Space to RESTART",350 ,340);
                
            
            }
            if(hit){
                right=false;
                left= false;
                up=false;
                down=false;
                hit=false;
                moves=0;
                gameover=true;
                g.setColor(Color.red);
                g.setFont(new Font ("arial",Font.BOLD,50));
                g.drawString("GAME OVER",300 ,300);
                 g.setColor(Color.white);
                g.setFont(new Font ("arial",Font.BOLD,20));
                g.drawString("Space to RESTART",350 ,340);
               
            }
            
        }
        g.dispose();
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
      
    }

    @Override
    public void keyPressed(KeyEvent e) {
    
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            moves=0;
            score=0;
            lengthofsnake=3;
            gameover=false;
            repaint();
       
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT&&!gameover){
            moves++;
            right=true;
            if(!left){
                right=true;
            }
            else{
                right=false;
                left=true;
            }
            up=false;
            down=false;
        }
        
        if(e.getKeyCode()==KeyEvent.VK_LEFT&&!gameover){
            moves++;
            left=true;
            if(!right){
                left=true;
            }
            else{
                left=false;
                right=true;
            }
            up=false;
            down=false;
        }
        
        if(e.getKeyCode()==KeyEvent.VK_UP&&!gameover){
            moves++;
            up=true;
            if(!down){
                up=true;
            }
            else{
                up=false;
                down=true;
            }
            right=false;
            left=false;
        }
        
        if(e.getKeyCode()==KeyEvent.VK_DOWN&&!gameover){
            moves++;
            down=true;
            if(!up){
                down=true;
            }
            else{
                down=false;
                up=true;
            }
            right=false;
            left=false;
        }

        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(right){
            for(int r= lengthofsnake-1;r>=0;r--){
                snakeylength[r+1]=snakeylength[r];
            }
            for(int r=lengthofsnake;r>=0;r--){
                if(r==0){
                    snakexlength[r]=snakexlength[r]+25;
                }
                else{
                    snakexlength[r]=snakexlength[r-1];
                }
                if(snakexlength[r]>850){
                    hit=true;
                }
              }
           repaint();   
        }
        if(left){
            for(int r= lengthofsnake-1;r>=0;r--){
                snakeylength[r+1]=snakeylength[r];
            }
            for(int r=lengthofsnake;r>=0;r--){
                if(r==0){
                    snakexlength[r]=snakexlength[r]-25;
                }
                else{
                    snakexlength[r]=snakexlength[r-1];
                }
                if(snakexlength[r]<25){
                    hit=true;
                }
              }
           repaint();
        }
        if(up){
            for(int r= lengthofsnake-1;r>=0;r--){
                snakexlength[r+1]=snakexlength[r];
            }
            for(int r=lengthofsnake;r>=0;r--){
                if(r==0){
                    snakeylength[r]=snakeylength[r]-25;
                }
                else{
                    snakeylength[r]=snakeylength[r-1];
                }
                if(snakeylength[r]<75){
                    hit=true;
                }
              }
           repaint();
        }
        if(down){
            for(int r= lengthofsnake-1;r>=0;r--){
                snakexlength[r+1]=snakexlength[r];
            }
            for(int r=lengthofsnake;r>=0;r--){
                if(r==0){
                    snakeylength[r]=snakeylength[r]+25;
                }
                else{
                    snakeylength[r]=snakeylength[r-1];
                }
                if(snakeylength[r]>625){
                    hit=true;
                }
              }
           repaint();
        }
        
    }

    private void setFocusableTraversalKeyEnabled(boolean b) {
        //To change body of generated methods, choose Tools | Templates.
    }
    
}