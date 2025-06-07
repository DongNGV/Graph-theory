package Litener;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.duLieu;

public class LoadMaTran implements ActionListener{
	duLieu dl;

	public LoadMaTran(duLieu dl) {
		this.dl = dl;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		dl.updata();
		dl.xetLoi();
		
	}

}
