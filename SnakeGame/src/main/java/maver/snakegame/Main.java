/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maver.snakegame;

import java.awt.Color;
import java.awt.*;
import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        
        JFrame frame = new JFrame("Snake Game");
        frame.setBounds(0,0,815,635);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        
        ImageIcon icon = new ImageIcon("C:\\Users\\x\\Documents\\NetBeansProjects\\SnakeGame\\src\\main\\java\\maver\\snakegame\\snake-head-10-1104000.png");
        frame.setIconImage(icon.getImage());
        
        //frame.setResizable(false);
        
        PnlGame panel = new PnlGame();
        panel.setLayout(null);
        
        JLabel snakeTitle = new JLabel("nake");
        snakeTitle.setForeground(Color.black);
        snakeTitle.setFont(new Font("times new roman",Font.BOLD,50));
        snakeTitle.setBounds(355,-12,300,100);
        
        panel.add(snakeTitle);
        panel.setBackground(new Color(250,250,250));

        JLabel label = new JLabel("S");
        label.setForeground(Color.red);
        label.setBounds(330,-12,50,100);
        label.setFont(new Font("times new roman",Font.BOLD,50));
        panel.add(label);
        
        frame.add(panel);
        frame.setVisible(true);
    }
}
