package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import Litener.DiChuyen;
import Panel.bangVe;
import Panel.congCu;
import Panel.menu;
import Panel.panelMaTran;
import Panel.veBFS;
import Panel.veDuongDi;
import Panel.veLienThong;
import label.LabelMaTran;
import node.diem;

public class duLieu {
	public congCu khungCongCu = new congCu(this);
	public bangVe khungBangVe = new bangVe(this);
	public panelMaTran pMaTran = new panelMaTran(this);
	menu menuBar = new menu(this);
	public int maTran[][];
	public int Canh[][] = new int[100][100];
	public int soLuong = 0;
	public LabelMaTran lbMaTran = new LabelMaTran(this);
	public diem diem[] = new diem[100];
	public String listBFS = "";
	public String listVeBFS = "";
	
//	veLienThong vLienThong = new veLienThong(this);
//	public DiChuyen diChuyen = new DiChuyen(this);
//	ArrayList<JTextField> listMaTran = new ArrayList<>();

	public duLieu() {
		pMaTran.add(lbMaTran);
		pMaTran.add(lbMaTran.barH);
		pMaTran.add(lbMaTran.barV);

//		System.out.println(Integer.parseInt(text1.getText()));
//		if(Integer.parseInt(text1.getText()) == 0) {
//			System.out.println("0 = 0");
//		}

//		
//		int a;
//		try {
//            a = Integer.parseInt(text1.getText());
//            System.out.println(a);
//            
//        } catch (Exception e) {
//            System.out.println("Khong phai so");
//            text1.setText("0");
//        }  

	}

	public void setup() {
//		try {
//			soLuong = Integer.parseInt(khungCongCu.soDiem.getText());
//			pMaTran.add(lbMaTran);
//			lbMaTran.setupMaTran();
////			if(soLuong > 7) {
//				pMaTran.add(lbMaTran.barH);
//				pMaTran.add(lbMaTran.barV);
////			}
//			lbMaTran.barH.setVisible(true);
//			lbMaTran.barV.setVisible(true);
//			for (int i = 0; i < soLuong; i++)
//				for (int j = 0; j < soLuong; j++) {
//					lbMaTran.text[i][j].setVisible(true);
//				}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}

	}

	public void updata() {
		// reset canh xet
		for (int i = 0; i <= soLuong; i++)
			for (int j = 0; j <= soLuong; j++) {
				Canh[i][j] = 0; // reset Cạnh trước khi vẽ
			}
		// reset điểm kề
		for (int i = 1; i <= soLuong; i++)
			for (int j = 1; j <= soLuong; j++) {
				diem[i].dinhKe[j] = false;
			}
		// Vẽ Cạnh
		if (soLuong > 1) {
			if (khungCongCu.bVoHuong == true) { // Vô hướng
				for (int i = 1; i < soLuong; i++)
					for (int j = i + 1; j <= soLuong; j++) {
						if (!lbMaTran.text[i][j].getText().equals("")
								&& lbMaTran.text[i][j].getText().equals(lbMaTran.text[j][i].getText())
								&& !lbMaTran.text[i][j].getText().equals("0")) {
							// dl.pMaTran.drawCanh(g2, dl.lbMaTran.text[i][j], dl.lbMaTran.text[j][i]);
							// Vô hướng
							Canh[i][j] = 1;
							Canh[j][i] = 1;
							diem[i].dinhKe[j] = true;
							diem[j].dinhKe[i] = true;
						}
					}
			} else { // Có hướng
				for (int i = 1; i <= soLuong; i++)
					for (int j = 1; j <= soLuong; j++) {
						if (i == j)
							continue;
						else {
							if (!lbMaTran.text[i][j].getText().equals("")
									&& !lbMaTran.text[i][j].getText().equals("0")) {
								// dl.pMaTran.drawCanh(g2, dl.lbMaTran.text[i][j], dl.lbMaTran.text[j][i]);
								// Vô hướng
								Canh[i][j] = 1;
								diem[i].dinhKe[j] = true;
							}
						}

					}
			}
			pMaTran.bDrawCanh = true;
			khungBangVe.repaint();
		} else {
			khungBangVe.repaint();
			System.out.println("new");
		}
		lbMaTran.repaint();
		khungBangVe.repaint();
	}

	public void xetLoi() {
//đưa về màu mặc định
		for (int i = 1; i <= soLuong; i++)
			for (int j = 1; j <= soLuong; j++) {
				if (i == j)
					continue;
				else {
					lbMaTran.text[i][j].setBackground(Color.WHITE);
				}
			}

//thông báo lỗi
		if (khungCongCu.bVoHuong == true) {
			int yesNo = 0;
			int bBreak = 0;
			for (int i = 1; i < soLuong; i++) {
				for (int j = i + 1; j <= soLuong; j++) {
					if (i == j)
						continue;
					else {
						if (!lbMaTran.text[i][j].getText()
								.equals(lbMaTran.text[j][i].getText()) /*
																		 * !lbMaTran.text[i][j].getText().equals("") &&
																		 * !lbMaTran.text[j][i].getText().equals("") &&
																		 */) {
							yesNo = JOptionPane.showConfirmDialog(khungBangVe, "Có một vài giá trị trong ma trận chưa đúng \nBạn cón muốn chuyển chúng về giá trị 1");
							bBreak = 1;
							break;
						}
					}
				}
				if(bBreak == 1)
					break;
			}
			
			if(yesNo == JOptionPane.YES_OPTION) {
				for (int i = 1; i < soLuong; i++)
					for (int j = i + 1; j <= soLuong; j++) {
						if (i == j)
							continue;
						else {
							if (!lbMaTran.text[i][j].getText()
									.equals(lbMaTran.text[j][i].getText()) && (lbMaTran.text[i][j].getText().equals("1") || lbMaTran.text[j][i].getText().equals("1")) /*
																			 * !lbMaTran.text[i][j].getText().equals("") &&
																			 * !lbMaTran.text[j][i].getText().equals("") &&
																			 */) {
								lbMaTran.text[i][j].setText("1");
								lbMaTran.text[j][i].setText("1");
							}
						}
					}
				updata();
			}
			
			if(yesNo == JOptionPane.NO_OPTION) {
				for (int i = 1; i < soLuong; i++)
					for (int j = i + 1; j <= soLuong; j++) {
						if (i == j)
							continue;
						else {
							if (!lbMaTran.text[i][j].getText()
									.equals(lbMaTran.text[j][i].getText()) && (lbMaTran.text[i][j].getText().equals("1") || lbMaTran.text[j][i].getText().equals("1")) /*
																			 * !lbMaTran.text[i][j].getText().equals("") &&
																			 * !lbMaTran.text[j][i].getText().equals("") &&
																			 */) {
								lbMaTran.text[i][j].setBackground(Color.orange);
								lbMaTran.text[j][i].setBackground(Color.orange);
							}
						}
					}
			}
			
		}

	}

	public void capNhatMaTran(int diemDau, int diemCuoi) {
		if (khungCongCu.bVoHuong == true) {
			lbMaTran.text[diemDau][diemCuoi].setText("" + 1);
			lbMaTran.text[diemCuoi][diemDau].setText("" + 1);
		} else {
			lbMaTran.text[diemDau][diemCuoi].setText("" + 1);
		}

		updata();
	}

	public void duyetDoThi(int diemBatDau) {
		System.out.println("diembd: "+diemBatDau);
		boolean daXet[] = new boolean[soLuong + 1];
		diem tmpDiem;
		listBFS = "";
		listVeBFS = "";

		Queue<diem> queue = new LinkedList<>();
		queue.add(diem[diemBatDau]);
		daXet[diemBatDau] = true;
		while (!queue.isEmpty()) {
			tmpDiem = queue.remove();
			listVeBFS += tmpDiem.name+"k";
			System.out.print("name: "+tmpDiem.name+"   ");
			listBFS += tmpDiem.name + " "; // tham dinh
			for (int i = 1; i <= soLuong; i++) {
				if (tmpDiem.name.equals(i + "") == true)
					continue;
				else {
					if (diem[Integer.parseInt(tmpDiem.name)].dinhKe[i] == true && daXet[i] == false) {
						queue.add(diem[i]);
						daXet[i] = true;
						listVeBFS += i+" ";
					}
				}
			}
			listVeBFS += "l";
		}
		
		System.out.println("list: "+listVeBFS);

		
		
		
		if(listBFS.equals("")) {
			System.out.println("Không có cạnh duyệt");
		}
		
		System.out.println("đã duyệt xong");
		
		JFrame f = new JFrame("BFS");
		f.add(new veBFS(this));
		f.setLocationRelativeTo(null);
//		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
//		f.setBounds(scree, soLienThong, diemBatDau, soLienThong);
		f.pack();
		f.setVisible(true);
		
		JFileChooser fileDialog = new JFileChooser("E:");
		MenuBar mBar = new MenuBar();
		Menu save = new Menu("Save");
		MenuItem saveAnh = new MenuItem("Ảnh");
		saveAnh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { // nơi hiện khung chọn file
				FileNameExtensionFilter filter = new FileNameExtensionFilter(".png", "png", "png");
				fileDialog.setFileFilter(filter); // ép kiểu dữ liệu thành .txt
				
				int userSelection = fileDialog.showSaveDialog(f);

				if (userSelection == JFileChooser.APPROVE_OPTION) {
					File fileToSave = fileDialog.getSelectedFile();
					System.out.println("Save as file: " + fileToSave.getAbsolutePath() + ".png");
					saveImage((JPanel) f.getContentPane(), fileToSave);
				}
			}
		});
		
		save.add(saveAnh);
		mBar.add(save);
		f.setMenuBar(mBar);
	}
	
	
	public void thanhPhanLienThong() {
		int diemBatDau = 0;
		int soLienThong = 0;
		boolean daXet[] = new boolean[soLuong + 1];
		String listBFS[] = new String[soLuong+1];
		String listVeBFS[] = new String[soLuong+1];
		String listThanhPhanLienThong[] = new String[soLuong+1];
		for(int i=0; i<soLuong+1; i++)
			listThanhPhanLienThong[i] = "";
		int soThanhPhanLienThong = 0;
//		System.out.println("diembd: "+diemBatDau);
		for(int h=1; h<=soLuong; h++) {
			if(daXet[h] == false) {
				diemBatDau = h;
				soLienThong++;
				listBFS[h] = "";
				listVeBFS[h] = "";

				Queue<diem> queue = new LinkedList<>();
				queue.add(diem[diemBatDau]);
				daXet[diemBatDau] = true;
				listThanhPhanLienThong[soThanhPhanLienThong] += h + " ";
				
				while (!queue.isEmpty()) {			// Thăm các đỉnh còn lại
					diem tmpDiem = queue.remove();
					listVeBFS[h] += tmpDiem.name+"k";
//					System.out.print("name: "+tmpDiem.name+"   ");
					listBFS[h] += tmpDiem.name + " "; // tham dinh
					for (int i = 1; i <= soLuong; i++) {
						if (tmpDiem.name.equals(i + "") == true)
							continue;
						else {
							if ((diem[Integer.parseInt(tmpDiem.name)].dinhKe[i] == true || diem[i].dinhKe[Integer.parseInt(tmpDiem.name)] == true) && daXet[i] == false) {
								queue.add(diem[i]);
								daXet[i] = true;
								listVeBFS[h] += i+" ";
								listThanhPhanLienThong[soThanhPhanLienThong] += i+" ";
							}
						}
					}
					listVeBFS[h] += "l";
				}
				soThanhPhanLienThong++; 
			}
		}
		System.out.println("Số thành phần liên thông: " + soLienThong);
		System.out.println("Thanh phan lien thong");
		for(int i=0; i<soThanhPhanLienThong; i++) {
			System.out.println(listThanhPhanLienThong[i]);
		}
		
		if(soLienThong == 1) {
			khungCongCu.lbKoLienThong.setVisible(true);
		}
		else {
			khungCongCu.lbKoLienThong.setVisible(false);
			JFrame f = new JFrame("Đồ thị liên thông");
			f.add(new veLienThong(this, soLienThong, listThanhPhanLienThong, soThanhPhanLienThong));
			f.setLocationRelativeTo(null);
//			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setResizable(false);
//			f.setBounds(scree, soLienThong, diemBatDau, soLienThong);
			f.pack();
			f.setVisible(true);
			
			
			JFileChooser fileDialog = new JFileChooser("E:");
			MenuBar mBar = new MenuBar();
			Menu save = new Menu("Save");
			MenuItem saveAnh = new MenuItem("Ảnh");
			saveAnh.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) { // nơi hiện khung chọn file
					FileNameExtensionFilter filter = new FileNameExtensionFilter(".png", "png", "png");
					fileDialog.setFileFilter(filter); // ép kiểu dữ liệu thành .txt
					
					int userSelection = fileDialog.showSaveDialog(f);

					if (userSelection == JFileChooser.APPROVE_OPTION) {
						File fileToSave = fileDialog.getSelectedFile();
						System.out.println("Save as file: " + fileToSave.getAbsolutePath() + ".png");
						saveImage((JPanel) f.getContentPane(), fileToSave);
					}
				}
			});
			
			save.add(saveAnh);
			mBar.add(save);
			f.setMenuBar(mBar);
		}
		
	}
	
	public void duongDiHaiDiem(int diemBatDau, int diemCuoi) {
		System.out.println("diembd: "+diemBatDau);
		boolean daXet[] = new boolean[soLuong + 1];
		diem tmpDiem;
		listBFS = "";
		listVeBFS = "";

		Queue<diem> queue = new LinkedList<>();
		queue.add(diem[diemBatDau]);
		daXet[diemBatDau] = true;
		while (!queue.isEmpty()) {
			tmpDiem = queue.remove();
			listVeBFS += tmpDiem.name+"k";
			System.out.print("name: "+tmpDiem.name+"   ");
			listBFS += tmpDiem.name + " "; // tham dinh
			for (int i = 1; i <= soLuong; i++) {
				if (tmpDiem.name.equals(i + "") == true)
					continue;
				else {
					if (diem[Integer.parseInt(tmpDiem.name)].dinhKe[i] == true && daXet[i] == false) {
						queue.add(diem[i]);
						daXet[i] = true;
						listVeBFS += i+" ";
					}
				}
			}
			listVeBFS += "l";
		}
		
		System.out.println("list: "+listBFS);
		
		System.out.println("đã duyệt xong");
		System.out.println("list: "+listBFS);
		
		boolean bDau = false, bCuoi = false;
		String arrList[] = listBFS.split(" ");
		for(int i=0; i<arrList.length; i++) {
			if(arrList[i].equals(diemBatDau+"") == true)
				bDau = true;
			if(arrList[i].equals(diemCuoi+"") == true)
				bCuoi = true;
		}
		
	if(bDau == false || bCuoi == false) {
		khungCongCu.lbKoTimDuong.setVisible(true);
	}
	else {
		khungCongCu.lbKoTimDuong.setVisible(false);
		JFrame f = new JFrame("BFS");
		f.add(new veDuongDi(this, listBFS,diemBatDau, diemCuoi));
		f.setLocationRelativeTo(null);
//		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
//		f.setBounds(scree, soLienThong, diemBatDau, soLienThong);
		f.pack();
		f.setVisible(true);
		
		JFileChooser fileDialog = new JFileChooser("E:");
		MenuBar mBar = new MenuBar();
		Menu save = new Menu("Save");
		MenuItem saveAnh = new MenuItem("Ảnh");
		saveAnh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { // nơi hiện khung chọn file
				FileNameExtensionFilter filter = new FileNameExtensionFilter(".png", "png", "png");
				fileDialog.setFileFilter(filter); // ép kiểu dữ liệu thành .txt
				
				int userSelection = fileDialog.showSaveDialog(f);

				if (userSelection == JFileChooser.APPROVE_OPTION) {
					File fileToSave = fileDialog.getSelectedFile();
					System.out.println("Save as file: " + fileToSave.getAbsolutePath() + ".png");
					saveImage((JPanel) f.getContentPane(), fileToSave);
				}
			}
		});
		
		save.add(saveAnh);
		mBar.add(save);
		f.setMenuBar(mBar);
	}
	
	}
	
	public void saveImage(JPanel panel, File viTriLuu) {

		BufferedImage img = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
		panel.paint(img.getGraphics());
		try {
			ImageIO.write(img, "png", new File(viTriLuu.getAbsolutePath() + ".png"));
			System.out.println("Ảnh được lưu vào: " + viTriLuu.getAbsolutePath());

		} catch (Exception e) {
			System.out.println("panel not saved" + e.getMessage());
		}
	}
}
