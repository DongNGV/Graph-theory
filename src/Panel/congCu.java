package Panel;

import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.duLieu;
import node.diem;

public class congCu extends JPanel {
	duLieu dl;
	public JTextField soDiem;
	public JTextField dinhBFS;
	public JButton hienBFS;
	public JTextField batDau;
	public JTextField ketThuc;
	public JLabel lbKoLienThong;
	public JLabel lbKoTimDuong;
	public boolean bThemDinhTT = false;
	public boolean bVoHuong = true;
	public boolean bCoHuong = false;
	public boolean bXoaDiem = false;

	public congCu(duLieu dl) {
		this.setBackground(Color.gray);
		this.setBounds(0, 0, 1108, 70+30);
		this.dl = dl;
		this.setLayout(null);
		itemKhungCongCu();
	}

	public void itemKhungCongCu() {
		// Hướng
		JPanel pHuong = new JPanel();
		pHuong.setLayout(null);
		pHuong.setBounds(8, 2, 140+8, 96);
		pHuong.setBackground(Color.white);
		this.add(pHuong);
		
		JLabel lbHuong = new JLabel("Hướng");
		lbHuong.setBounds(5, 0, 100, 25);
		lbHuong.setFont(lbHuong.getFont().deriveFont(Font.BOLD, 20F));
		pHuong.add(lbHuong);
		
		// cong cu
		JPanel pCongCu = new JPanel();
		pCongCu.setLayout(null);
		pCongCu.setBounds(150+8, 2, 198+8, 96);
		pCongCu.setBackground(Color.white);
		this.add(pCongCu);
		
		JLabel lbChucNang = new JLabel("Chức năng");
		lbChucNang.setBounds(5, 0, 150, 25);
		lbChucNang.setFont(lbChucNang.getFont().deriveFont(Font.BOLD, 20F));
		pCongCu.add(lbChucNang);
		
		JCheckBox voHuong = new JCheckBox("Vô hướng", true);
		JCheckBox coHuong = new JCheckBox("Có hướng");
		voHuong.setBounds(2, 30, 90+50, 25);
		voHuong.setFont(voHuong.getFont().deriveFont(Font.BOLD, 14F));
		coHuong.setBounds(2, 64, 90+50, 25);
		coHuong.setFont(coHuong.getFont().deriveFont(Font.BOLD, 14F));
		ButtonGroup checkBoxHuong = new ButtonGroup();
		checkBoxHuong.add(voHuong);
		checkBoxHuong.add(coHuong);
//		this.add(voHuong);
//		this.add(coHuong);
		pHuong.add(voHuong);
		pHuong.add(coHuong);
		
		voHuong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bVoHuong = true;
				bCoHuong = false;
				dl.pMaTran.bLoad.doClick();
				dl.khungBangVe.repaint();
			}
		});
		coHuong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bCoHuong = true;
				bVoHuong = false;
				dl.pMaTran.bLoad.doClick();
				dl.khungBangVe.repaint();
			}
		});

		// duyệt đồ thị
		JPanel pDuyetDT = new JPanel();
		pDuyetDT.setLayout(null);
		pDuyetDT.setBounds(150+200+16, 2, 198+8, 96);
		pDuyetDT.setBackground(Color.white);
		this.add(pDuyetDT);
		
		JLabel lbDuyetDT = new JLabel("Duyệt đồ thị");
		lbDuyetDT.setBounds(5, 0, 150, 25);
		lbDuyetDT.setFont(lbDuyetDT.getFont().deriveFont(Font.BOLD, 20F));
		pDuyetDT.add(lbDuyetDT);
		
		JLabel lbBatDau = new JLabel("Đỉnh duyệt:");
		lbBatDau.setBounds(2, 30, 95, 25);
		lbBatDau.setFont(lbBatDau.getFont().deriveFont(Font.BOLD, 17F));
		pDuyetDT.add(lbBatDau);
		
		// liên thông
		JPanel pLienThong = new JPanel();
		pLienThong.setLayout(null);
		pLienThong.setBounds(550+24, 2, 198+8, 96);
		pLienThong.setBackground(Color.white);
		this.add(pLienThong);
		
		JLabel lbLienThong = new JLabel("Tính liên thông");
		lbLienThong.setBounds(5, 0, 150, 25);
		lbLienThong.setFont(lbDuyetDT.getFont().deriveFont(Font.BOLD, 20F));
		pLienThong.add(lbLienThong);
		
		lbKoLienThong = new JLabel("     Đồ thị liên thông");
		lbKoLienThong.setBounds(2, 64, 194, 25);
		lbKoLienThong.setVisible(false);
		lbKoLienThong.setForeground(Color.red);
		lbKoLienThong.setFont(lbKoLienThong.getFont().deriveFont(Font.BOLD, 18F));
		pLienThong.add(lbKoLienThong);
		
		// Đường đi
		JPanel pDuongDi = new JPanel();
		pDuongDi.setLayout(null);
		pDuongDi.setBounds(550+200+32, 2, 198+100+8, 96);
		pDuongDi.setBackground(Color.white);
		this.add(pDuongDi);
		
		JLabel lbDuongDi = new JLabel("Đường đi 2 đỉnh");
		lbDuongDi.setBounds(5, 0, 180, 25);
		lbDuongDi.setFont(lbDuongDi.getFont().deriveFont(Font.BOLD, 20F));
		pDuongDi.add(lbDuongDi);
		
		
		
		
		
		// Thêm đỉnh
		JButton themDinh = new JButton("Thêm đỉnh");
		themDinh.setBounds(2, 30, 95, 25);
		pCongCu.add(themDinh);
		themDinh.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (dl.soLuong < 99) {
					dl.soLuong++;
					dl.lbMaTran.updataMaTran();
					dl.diem[dl.soLuong] = new diem(dl, (dl.soLuong%17) * 50, (dl.soLuong/17 + 1)*50);

					dl.updata();
				}
			}
		});
		
		JButton xoaCanh = new JButton("Xóa cạnh");
		xoaCanh.setBounds(100, 30, 95, 25);
		pCongCu.add(xoaCanh);
		xoaCanh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String strXoa = JOptionPane.showInputDialog(dl.khungCongCu, "Nhập cạnh cần xóa (cánh nhau một khoảng trống) \nVD: \"1 3; 2 5;...\" -> xóa cạnh 1-3 và 2-5...");
				try {
					String mangXoa[] = strXoa.split("; ");
					for(int i=0; i<mangXoa.length; i++) {
						try {
							String diemXoa[] = mangXoa[i].split(" ");
							System.out.println(diemXoa[0]+" "+diemXoa[1]);
							if(dl.khungCongCu.bVoHuong == true) {
								dl.lbMaTran.text[Integer.parseInt(diemXoa[0])][Integer.parseInt(diemXoa[1])].setText("0");
								dl.lbMaTran.text[Integer.parseInt(diemXoa[1])][Integer.parseInt(diemXoa[0])].setText("0");
							}
							else {
								dl.lbMaTran.text[Integer.parseInt(diemXoa[0])][Integer.parseInt(diemXoa[1])].setText("0");
							}
						} catch (Exception e2) {
							// TODO: handle exception
						}
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
				dl.pMaTran.bLoad.doClick();
			}
		});

		// Thêm đỉnh trên khung vẽ (Thêm đỉnh trực tiếp)
		JCheckBox themDinhTT = new JCheckBox("Thêm Đỉnh");
		themDinhTT.setBounds(2, 64, 95, 25);
		pCongCu.add(themDinhTT);
		themDinhTT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (bThemDinhTT == false) {
					themDinhTT.setBackground(Color.red);
					bThemDinhTT = true;
				} else {
					themDinhTT.setBackground(Color.white);
					bThemDinhTT = false;
				}
			}
		});

		// Xóa đỉnh trực tiếp
		JCheckBox xoaDinhTT = new JCheckBox("Xóa Đỉnh");
		xoaDinhTT.setBounds(100, 64, 95, 25);
		pCongCu.add(xoaDinhTT);
		xoaDinhTT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (bXoaDiem == false) {
					xoaDinhTT.setBackground(Color.red);
					bXoaDiem = true;
				} else {
					xoaDinhTT.setBackground(Color.white);
					bXoaDiem = false;
				}
			}
		});

		// BFS

		// Nhập đỉnh BFS
		dinhBFS = new JTextField("1");
		dinhBFS.setBounds(100, 30, 95, 25);
		pDuyetDT.add(dinhBFS);

		// Hiện cây
		hienBFS = new JButton("Hiện BFS");
		hienBFS.setBounds(100, 64, 95, 25);
		pDuyetDT.add(hienBFS);
		hienBFS.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(dl.soLuong == 0) {
					JOptionPane.showMessageDialog(dl.khungBangVe, "Bạn chưa nhập bất kỳ điểm nào!!!");
				}
				else {
					try {
						dl.duyetDoThi(Integer.parseInt(dl.khungCongCu.dinhBFS.getText()));
						dl.khungBangVe.repaint();
					} catch (Exception e2) {
						System.out.println("Nhập sai đỉnh");
					}
				}
				

			}
		});

		// Thành phần liên thông
		JButton lienThong = new JButton("Xét Liên Thông");
		lienThong.setBounds(2, 30, 194, 25);
		pLienThong.add(lienThong);
		lienThong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(dl.soLuong == 0) {
					JOptionPane.showMessageDialog(dl.khungBangVe, "Bạn chưa nhập bất kỳ điểm nào!!!");
				}
				else
					dl.thanhPhanLienThong();

			}
		});
		
		

		// Đường đi
		JLabel lbDinhDau = new JLabel("Bắt đầu:");
		lbDinhDau.setBounds(2, 30, 95, 25);
		lbDinhDau.setFont(lbDinhDau.getFont().deriveFont(Font.BOLD, 17F));
		pDuongDi.add(lbDinhDau);
		
		JLabel lbDinhCuoi = new JLabel("Kết thúc:");
		lbDinhCuoi.setBounds(130, 30, 95, 25);
		lbDinhCuoi.setFont(lbDinhCuoi.getFont().deriveFont(Font.BOLD, 17F));
		pDuongDi.add(lbDinhCuoi);
		
		batDau = new JTextField();
		batDau.setBounds(72, 30, 55, 25);
		pDuongDi.add(batDau);

		ketThuc = new JTextField();
		ketThuc.setBounds(206, 30, 55, 25);
		pDuongDi.add(ketThuc);
		
		lbKoTimDuong = new JLabel("Không có đường đi!");
		lbKoTimDuong.setBounds(110, 64, 200, 25);
		lbKoTimDuong.setVisible(false);
		lbKoTimDuong.setForeground(Color.red);
		lbKoTimDuong.setFont(lbKoTimDuong.getFont().deriveFont(Font.BOLD, 18F));
		pDuongDi.add(lbKoTimDuong);
//		this.add(ketThuc);



		JButton hienDuongDi = new JButton("Tìm đường");
		hienDuongDi.setBounds(2, 64, 100, 25);
		pDuongDi.add(hienDuongDi);
		hienDuongDi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dl.duongDiHaiDiem(Integer.parseInt(batDau.getText()), Integer.parseInt(ketThuc.getText()));
			}
		});
	}

}
