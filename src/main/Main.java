package main;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(1120, 700);
		frame.setLayout(null);
		
		frame.setTitle("Đồ thị");
		
		duLieu data = new duLieu();
		frame.add(data.khungCongCu);
		frame.add(data.khungBangVe);
		frame.setMenuBar(data.menuBar);
		frame.add(data.pMaTran);
		

		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}
	
}
