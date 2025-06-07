package Panel;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Litener.LoadMaTran;
import main.duLieu;

public class panelMaTran extends JPanel{
	duLieu dl;
	public JButton bLoad = new JButton("Cập nhật");
	public boolean bDrawCanh = false;
	public int diemDau = -1;
	public int diemCuoi = -1;
	public boolean noiCanh = false;
	public int chuotX = -1;
	public int chuotY = -1;

	
	public panelMaTran(duLieu dl) {
		this.dl = dl;
		//this.setBackground(Color.black);
		this.setBounds(0, 70+30, 223, 630);
		this.setLayout(null);
		
		bLoad.setBounds(0, 7*30+15, 90, 30);
		bLoad.addActionListener(new LoadMaTran(dl));
		this.add(bLoad);
	}
	

}
