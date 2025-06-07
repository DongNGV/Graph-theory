package label;

import java.awt.Color;
import java.awt.Label;
import java.awt.PopupMenu;
import java.awt.TextField;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Litener.KoNhap;
import Litener.inputTextField;
import main.duLieu;

public class LabelMaTran extends JLabel{
	duLieu dl;
	int soDiemDaTao = 0;
	public JScrollBar barH;
	public JScrollBar barV;
	JList li;
	public JTextField text[][] = new JTextField[100][100];

	public LabelMaTran(duLieu dl) {
		this.dl = dl;
		
		taoThanhCuon();
		
		this.setLayout(null);
		this.setBounds(0, 0, 7*30, 7*30);
		
		
		//soLuong = dl.soLuong;
		//setupMaTran();
	}
	

	
	public void updataMaTran() {
		
		if(dl.soLuong<=6 && soDiemDaTao > 6) {
			barH.setVisible(false);
			barV.setVisible(false);
		}
		// cột
		if(dl.soLuong > soDiemDaTao) {
			text[0][dl.soLuong] = new JTextField(""+dl.soLuong);
		}else {
			try {
				text[0][dl.soLuong].setText(""+dl.soLuong);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		if(dl.soLuong>0) {
		text[0][dl.soLuong].setBounds(dl.soLuong*30, 0, 30, 30);
		text[0][dl.soLuong].setForeground(Color.white);
		text[0][dl.soLuong].setBackground(Color.red);
		this.add(text[0][dl.soLuong]);
		}
		// dòng 
		if(dl.soLuong > soDiemDaTao) {
			text[dl.soLuong][0] = new JTextField(""+dl.soLuong);
		}else {
			try {
				text[dl.soLuong][0].setText(""+dl.soLuong);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		if(dl.soLuong>0) {
		text[dl.soLuong][0].setBounds(0, dl.soLuong*30, 30, 30);
		text[dl.soLuong][0].setForeground(Color.white);
		text[dl.soLuong][0].setBackground(Color.blue);
		this.add(text[dl.soLuong][0]);
		}
		// đường chéo
		if(dl.soLuong > soDiemDaTao)
		text[dl.soLuong][dl.soLuong] = new JTextField("0");
		if(dl.soLuong>0) {
		text[dl.soLuong][dl.soLuong].setBackground(Color.green);
		text[dl.soLuong][dl.soLuong].setBounds(dl.soLuong*30, dl.soLuong*30, 30, 30);
		text[dl.soLuong][dl.soLuong].addKeyListener(new KoNhap(text[dl.soLuong][dl.soLuong]));
		this.add(text[dl.soLuong][dl.soLuong]);
		}

		// add list
		if(dl.soLuong > 1) {
			for(int i=1; i<dl.soLuong; i++) {
				if(dl.soLuong > soDiemDaTao)
				text[i][dl.soLuong] = new JTextField("0");
				if(dl.soLuong>0)
				text[i][dl.soLuong].setText("0");
				text[i][dl.soLuong].setBounds(dl.soLuong*30, i*30, 30, 30);
				text[i][dl.soLuong].addKeyListener(new inputTextField(text[i][dl.soLuong]));
				this.add(text[i][dl.soLuong]);
			}
			for(int j=1; j<dl.soLuong; j++) {
				if(dl.soLuong > soDiemDaTao)
				text[dl.soLuong][j] = new JTextField("0");
				text[dl.soLuong][j].setText("0");
				if(dl.soLuong>0)
				text[dl.soLuong][j].setBounds(j*30, dl.soLuong*30, 30, 30);
				text[dl.soLuong][j].addKeyListener(new inputTextField(text[dl.soLuong][j]));
				this.add(text[dl.soLuong][j]);
			}
		}
		
		
		if(dl.soLuong > soDiemDaTao)
			soDiemDaTao = dl.soLuong;
		if(dl.soLuong > 6) {
			updataThanhCuon();
		}
		this.repaint();
	}
	
	public void taoThanhCuon() {
//		barH (Hàng dọc)
		barH = new JScrollBar();
		barH.setBounds(this.getX()+7*30, this.getX(), 13, 7*30);
		barH.setVisible(false);
		
//		barV (hàng ngang)
		barV = new JScrollBar(0);
		barV.setBounds(0, 7*30, 7*30, 15);
		barV.setVisible(false);


	}
	
	public void updataThanhCuon() {
		barH.setVisible(true); // thanh đứng
		barV.setVisible(true); // thanh ngang
		
		barH.setValues(barH.getValue(), 1, 0, dl.soLuong-5);
		barV.setValues(barV.getValue(), 1, 0, dl.soLuong-5);
		System.out.println(barH.getValue());
		barH.addAdjustmentListener((AdjustmentListener) new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent e) {
				for(int i=1; i<=dl.soLuong; i++)
					for(int j=0; j<=dl.soLuong; j++) {
						if(j!=0)
						text[i][j].setBounds(30*j - 30*barV.getValue(), 30*i - 30*barH.getValue(), 30, 30);
						else {
							text[i][j].setBounds(0, 30*i - 30*barH.getValue(), 30, 30);		
						}
						text[i][j].setVisible(true);
						if(text[i][j].getY() == 0 && j != 0) 
							text[i][j].setVisible(false);
							
						if(text[i][j].getX() == 0 && j != 0) 
							text[i][j].setVisible(false);
						
						if(text[i][0].getX()==0 && text[i][0].getY() == 0)
							text[i][0].setVisible(false);
					}
				
			}
		});
		
		barV.addAdjustmentListener((AdjustmentListener) new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent e) {
				for(int i=0; i<=dl.soLuong; i++)
					for(int j=1; j<=dl.soLuong; j++) {
						if(i!=0)
						text[i][j].setBounds(30*j - 30*barV.getValue(), 30*i - 30*barH.getValue(), 30, 30);
						else {
							text[i][j].setBounds(30*j - 30*barV.getValue(), 0, 30, 30);
						}
						text[i][j].setVisible(true);
						if(text[i][j].getY() == 0 && i != 0) 
							text[i][j].setVisible(false);

							
						if(text[i][j].getX() == 0 && i != 0) 
							text[i][j].setVisible(false);

						
						if(text[0][j].getX()==0 && text[0][j].getY() == 0)
							text[0][j].setVisible(false);
						
					}
			}
		});
	}
	
	
	public void xoaDiemDu() {
		if(soDiemDaTao > dl.soLuong) {
			for(int i=0; i<=soDiemDaTao; i++) 
				for(int j=dl.soLuong+1; j<=soDiemDaTao; j++) {
					this.remove(text[i][j]);
				}
			for(int j=0; j<=dl.soLuong; j++) 
				for(int i=dl.soLuong+1; i<=soDiemDaTao; i++) {
					this.remove(text[i][j]);
				}
			this.repaint();
		}
	}
	
	public void xoaPhanTuCuoi() {
		dl.lbMaTran.remove(text[dl.soLuong][dl.soLuong]);
		for(int i=0; i<dl.soLuong; i++) {
			dl.lbMaTran.remove(text[i][dl.soLuong]);
			dl.lbMaTran.remove(text[dl.soLuong][i]);
		}
	}
	
	public void xoaDiemTrucTiep(int xDinh) {
		int tmpXDinh = xDinh;
		System.out.println("Đã xóa đỉnh "+xDinh);
		int lanLap = dl.soLuong-xDinh;
		for(int k=0; k<lanLap; k++) {
			for(int i=1; i<xDinh; i++) {
				
				if(k==0) {
					dl.lbMaTran.text[i][xDinh].setText(dl.lbMaTran.text[i][xDinh+1].getText());
					dl.lbMaTran.text[xDinh][i].setText(dl.lbMaTran.text[xDinh+1][i].getText());
				}else {
					if(i<tmpXDinh) {
						dl.lbMaTran.text[i][xDinh].setText(dl.lbMaTran.text[i][xDinh+1].getText());
						dl.lbMaTran.text[xDinh][i].setText(dl.lbMaTran.text[xDinh+1][i].getText());
					}else {
						dl.lbMaTran.text[i][xDinh].setText(dl.lbMaTran.text[i+1][xDinh+1].getText());
						dl.lbMaTran.text[xDinh][i].setText(dl.lbMaTran.text[xDinh+1][i+1].getText());
					}
					
				}
				
				
				
				System.out.println("đỉnh "+i+":"+xDinh+" => đỉnh "+i+":"+(xDinh+1));
				System.out.println(i+":"+xDinh+" = "+dl.lbMaTran.text[i][xDinh].getText());
			}
			dl.diem[xDinh] = dl.diem[xDinh+1];
			dl.diem[xDinh].name = ""+xDinh;			
			
			xDinh++;
		}
		System.out.println("val trc: "+barH.getValue());
		if(dl.soLuong > 6) {
			barH.setValue(barH.getValue()-1);
			barV.setValue(barV.getValue()-1);
		}
		System.out.println("val sau: "+barH.getValue());
		dl.lbMaTran.xoaPhanTuCuoi();
		dl.soLuong--;
		dl.updata();
		dl.lbMaTran.updataThanhCuon();
		if(dl.soLuong<=6 && soDiemDaTao > 6) {
			barH.setVisible(false);
			barV.setVisible(false);
		}
		dl.lbMaTran.repaint();
		
	}
	public void kiemTra() {
		for(int i=1; i<=dl.soLuong; i++) {
			for(int j=1; j<=dl.soLuong; j++)
				System.out.print(dl.lbMaTran.text[i][j].getText()+" ");
			System.out.println();
		}
		System.out.println("---------------------------");
	}
	
}

