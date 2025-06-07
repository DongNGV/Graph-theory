package Panel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.QuadCurve2D;
import java.util.regex.Matcher;

import javax.swing.JPanel;

import main.duLieu;
import node.diem;

public class veDuongDi extends JPanel {

	duLieu dl;
	String list;
	int diemDau;
	int diemCuoi;

	public veDuongDi(duLieu dl, String list, int diemDau, int diemCuoi) {
		this.setPreferredSize(new Dimension(885, 600));
//		this.setBounds(223, 70, 885, 600);
		this.dl = dl;

		this.list = list;
		this.diemDau = diemDau;
		this.diemCuoi = diemCuoi;
		this.repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		if (dl.khungCongCu.bVoHuong == true) {
			for (int i = 1; i < dl.soLuong; i++)
				for (int j = i + 1; j <= dl.soLuong; j++) {
					if (dl.Canh[i][j] > 0 && dl.Canh[i][j] == dl.Canh[j][i]) {

						// Viền
						BasicStroke bs2 = new BasicStroke(8, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
						g2.setStroke(bs2);
						g2.setColor(Color.black);
						g2.drawLine(dl.diem[i].x + 20, dl.diem[i].y + 20, dl.diem[j].x + 20, dl.diem[j].y + 20);

						// Cạnh
						BasicStroke bs1 = new BasicStroke(6, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
						g2.setStroke(bs1);
						g2.setColor(Color.red);
						g2.drawLine(dl.diem[i].x + 20, dl.diem[i].y + 20, dl.diem[j].x + 20, dl.diem[j].y + 20);

					}
				}

		} else { // Có hướng
			for (int i = 1; i <= dl.soLuong; i++)
				for (int j = 1; j <= dl.soLuong; j++) {
					if (i == j)
						continue;
					else {
						if (dl.Canh[i][j] > 0) {

							// Mũi tên
							BasicStroke bs2 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
							g2.setStroke(bs2);
							if (dl.Canh[j][i] > 0) { // đường cong
								
								
								
								BasicStroke bs3 = new BasicStroke(8, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
								g2.setStroke(bs3);
								g2.setColor(Color.black);
								veVienDuongCong(g2, dl.diem[i], dl.diem[j]);
								
							}else {
								g2.setColor(Color.black);
								drawVienArrow(g, dl.diem[i].x + 20, dl.diem[i].y + 20, dl.diem[j].x + 20,
										dl.diem[j].y + 20);
								
								
								// Viền
								BasicStroke bs3 = new BasicStroke(8, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
								g2.setStroke(bs3);
								g2.setColor(Color.black);
								g2.drawLine(dl.diem[i].x + 20, dl.diem[i].y + 20, dl.diem[j].x + 20, dl.diem[j].y + 20);
							}



							// Cạnh
							BasicStroke bs1 = new BasicStroke(6, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
							g2.setStroke(bs1);
							g2.setColor(Color.red);
							if (dl.Canh[j][i] > 0) {
//								drawArrow1(g, dl.diem[i].x + 20, dl.diem[i].y + 20, dl.diem[j].x + 20,
//										dl.diem[j].y + 20);
								veDuongCong(g2, dl.diem[i], dl.diem[j]);
								BasicStroke bs5 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
								g2.setStroke(bs5);
								g2.setColor(Color.black);
								veMuiTenDuongCong1(g2, dl.diem[i], dl.diem[j]);
							} else {
								g2.drawLine(dl.diem[i].x + 20, dl.diem[i].y + 20, dl.diem[j].x + 20, dl.diem[j].y + 20);
								BasicStroke bs7 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
								g2.setStroke(bs7);
								g2.setColor(Color.red);
								drawArrow(g, dl.diem[i].x + 20, dl.diem[i].y + 20, dl.diem[j].x + 20, dl.diem[j].y + 20);
							}
						}
					}
				}

		}
		
		
		

		String daoDiem[] = list.split(" ");
		String tungDiem[] = list.split(" ");

		int veDiem[] = new int[dl.soLuong];
		int coutVeDiem = 0;

		int viTri = -1;
		int coutDiem = 0;
		// Tìm điểm cuối trong cây khung
		for (int i = 0; i < tungDiem.length; i++)
			if (Integer.parseInt(tungDiem[i]) == diemCuoi) {
				veDiem[coutVeDiem++] = Integer.parseInt(tungDiem[i]);
				coutDiem++;
				viTri = i;
				break;
			}

		// Tìm cạnh kề của điểm cuối
		if (viTri != -1) {
			int tmpDiemCuoi = diemCuoi;
			while (true) {
				for (int i = 0; i < viTri; i++) {
					if (dl.diem[Integer.parseInt(tungDiem[i])].dinhKe[tmpDiemCuoi] == true) {
						viTri = i;
						veDiem[coutVeDiem++] = Integer.parseInt(tungDiem[i]);
						coutDiem++;
						tmpDiemCuoi = Integer.parseInt(tungDiem[i]);
						if (tmpDiemCuoi == diemDau) {
							break;
						}
					}
				}
				if (tmpDiemCuoi == diemDau) {
					break;
				}
			}
		}

		// đảo lại đường đi
		int demDuong = 0;
		int duongDi[] = new int[coutDiem];
		for (int i = coutDiem - 1; i >= 0; i--) {
			duongDi[demDuong++] = veDiem[i];
		}

//		for(i=tungDiem.length-1; i>=0; i--) {
//			if(Integer.parseInt(tungDiem[i]) == diemCuoi) {
//				veDiem[coutVeDiem] = diemCuoi;
//				
//				for(int j = i-1; j>=0; j--) {
//					for(int k=1; k <= dl.soLuong; k++) {
//							if(dl.diem[k].dinhKe[diemCuoi] == true) {
//								veDiem[++coutVeDiem] = j;
//								diemCuoi = j;
//								break;
//						}
//					}
////					if(dl.diem[j].dinhKe[diemCuoi] == true){
////						veDiem[++coutVeDiem] = j;
////						diemCuoi = j;
////					}
//				}
//			}
//				
//				
//			
//		}

		String text = "";
		System.out.print("Duong di 2 diem: ");
		for (int k = 0; k < duongDi.length; k++) {
			text += duongDi[k] + " ";
			System.out.print(duongDi[k] + " ");
		}

		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));
		if (text.equals("")) {
			g2.setColor(Color.red);
			g2.drawString("Không có đường đi từ " + diemDau + " đến " + diemCuoi, 10, 560);
		} else {
			g2.setColor(Color.black);
			g2.drawString("Đường đi từ " + diemDau + " đến " + diemCuoi + ": " + text, 10, 560);
		}
			

		// draw BFS cạnh
		for (int i = 0; i < duongDi.length - 1; i++) {

			if (dl.khungCongCu.bVoHuong == true) {
				// Viền
				BasicStroke bs2 = new BasicStroke(10, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
				g2.setStroke(bs2);
				g2.setColor(Color.black);
				g2.drawLine(dl.diem[duongDi[i]].x + 20, dl.diem[duongDi[i]].y + 20,
						dl.diem[duongDi[i+1]].x + 20, dl.diem[duongDi[i+1]].y + 20);
				// Cạnh
				BasicStroke bs1 = new BasicStroke(8, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
				g2.setStroke(bs1);
				g2.setColor(Color.green);
				g2.drawLine(dl.diem[duongDi[i]].x + 20, dl.diem[duongDi[i]].y + 20,
						dl.diem[duongDi[i+1]].x + 20, dl.diem[duongDi[i+1]].y + 20);
			} else {	// có hướng
				// Mũi tên
				BasicStroke bs2 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
				g2.setStroke(bs2);
				if (dl.Canh[duongDi[i+1]][duongDi[i]] > 0) { // đường cong

					

					BasicStroke bs3 = new BasicStroke(8, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
					g2.setStroke(bs3);
					g2.setColor(Color.black);
					veVienDuongCong(g2, dl.diem[duongDi[i]], dl.diem[duongDi[i+1]]);

				} else {
					g2.setColor(Color.black);
					drawVienArrow(g, dl.diem[duongDi[i]].x + 20, dl.diem[duongDi[i]].y + 20,
							dl.diem[duongDi[i+1]].x + 20, dl.diem[duongDi[i+1]].y + 20);
					

					// Viền
					BasicStroke bs3 = new BasicStroke(8, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
					g2.setStroke(bs3);
					g2.setColor(Color.black);
					g2.drawLine(dl.diem[duongDi[i]].x + 20, dl.diem[duongDi[i]].y + 20,
							dl.diem[duongDi[i+1]].x + 20, dl.diem[duongDi[i+1]].y + 20);
				}

				// Cạnh
				BasicStroke bs1 = new BasicStroke(6, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
				g2.setStroke(bs1);
				g2.setColor(Color.green);
				if (dl.Canh[duongDi[i+1]][duongDi[i]] > 0) {
					veDuongCong(g2, dl.diem[duongDi[i]], dl.diem[duongDi[i+1]]);
					BasicStroke bs5 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
					g2.setStroke(bs5);
					g2.setColor(Color.black);
					veMuiTenDuongCong2(g2, dl.diem[duongDi[i]], dl.diem[duongDi[i+1]]);
				} else {
					g2.drawLine(dl.diem[duongDi[i]].x + 20, dl.diem[duongDi[i]].y + 20,
							dl.diem[duongDi[i+1]].x + 20, dl.diem[duongDi[i+1]].y + 20);
					BasicStroke bs6 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
					g2.setStroke(bs6);
					g2.setColor(Color.red);
					drawArrow1(g, dl.diem[duongDi[i]].x + 20, dl.diem[duongDi[i]].y + 20,
							dl.diem[duongDi[i+1]].x + 20, dl.diem[duongDi[i+1]].y + 20);
				}
			}
		}

// draw Diem
		for (int i = 1; i <= dl.soLuong; i++) {
			dl.diem[i].drawDiem(g2);
		}

		g2.dispose();
	}

	public void veMuiTenDuongCong1(Graphics2D g2, diem d1, diem d2) {
		float x = 0, y = 0;
		int xt = 0, yt = 0;
		Point mt1 = new Point();
		Point mt2 = new Point();
		if (d1.y < d2.y) {
			float rangeX = d2.x - d1.x;
			float rangeY = d2.y - d1.y;
			float giuaX = d1.x + rangeX / 2;
			float giuaY = d1.y + rangeY / 2;
			y = giuaY + rangeX / 2;
			x = giuaX - rangeY / 2;
			yt = (int) ((giuaY + rangeX / 2 / 2) + 10); // + range x
			xt = (int) ((giuaX - rangeY / 2 / 2) + 10); // + range y

			mt1.x = (int) (xt - rangeX / 2 / 2 + rangeY / 2 / 2);
			mt1.y = (int) (yt - rangeX / 2 / 2 - rangeY / 2 / 2 / 2);
			mt2.x = (int) (xt - rangeX / 2 / 2 - rangeY / 2 / 2 / 2);
			mt2.y = (int) (yt + rangeX / 2 / 2 - rangeY / 2 / 2);
		} else {
			float rangeX = d2.x - d1.x;
			float rangeY = d1.y - d2.y;
			float giuaX = d1.x + rangeX / 2;
			float giuaY = d1.y - rangeY / 2;
			y = giuaY + rangeX / 2;
			x = giuaX + rangeY / 2;
			yt = (int) ((giuaY + rangeX / 2 / 2) + 10);
			xt = (int) ((giuaX + rangeY / 2 / 2) + 10);
			mt1.x = (int) (xt - rangeX / 2 / 2 + rangeY / 2 / 2);
			mt1.y = (int) (yt + rangeX / 2 / 2 + rangeY / 2 / 2);
			mt2.x = (int) (xt - rangeX / 2 / 2 - rangeY / 2 / 2);
			mt2.y = (int) (yt - rangeX / 2 / 2 + rangeY / 2 / 2);
		}

//g2.fill(arrowHead);
//g2.drawPolygon(arrowHead);
//g2.setColor(Color.red);
//g2.drawRect(mt1.x, mt1.y, 3, 3);

//g2.fillRect(xt, yt, 5, 5);

		diem d3 = new diem(dl, xt, yt);
		veMuiTenDuongCong1_1(g2, d3, d2);
//drawArrow1(g2, xt, yt, d2.x, d2.y);
	}

	public void veVienDuongCong(Graphics2D g2, diem d1, diem d2) {
		float x = 0, y = 0;
		int xt = 0, yt = 0;
		Point mt1 = new Point();
		Point mt2 = new Point();
		if (d1.y < d2.y) {
			float rangeX = d2.x - d1.x;
			float rangeY = d2.y - d1.y;
			float giuaX = d1.x + rangeX / 2;
			float giuaY = d1.y + rangeY / 2;
			y = giuaY + rangeX / 2;
			x = giuaX - rangeY / 2;
			yt = (int) ((giuaY + rangeX / 2 / 2) + 10); // + range x
			xt = (int) ((giuaX - rangeY / 2 / 2) + 10); // + range y

			mt1.x = (int) (xt - rangeX / 2 / 2 + rangeY / 2 / 2);
			mt1.y = (int) (yt - rangeX / 2 / 2 - rangeY / 2 / 2 / 2);
			mt2.x = (int) (xt - rangeX / 2 / 2 - rangeY / 2 / 2 / 2);
			mt2.y = (int) (yt + rangeX / 2 / 2 - rangeY / 2 / 2);
		} else {
			float rangeX = d2.x - d1.x;
			float rangeY = d1.y - d2.y;
			float giuaX = d1.x + rangeX / 2;
			float giuaY = d1.y - rangeY / 2;
			y = giuaY + rangeX / 2;
			x = giuaX + rangeY / 2;
			yt = (int) ((giuaY + rangeX / 2 / 2) + 10);
			xt = (int) ((giuaX + rangeY / 2 / 2) + 10);
			mt1.x = (int) (xt - rangeX / 2 / 2 + rangeY / 2 / 2);
			mt1.y = (int) (yt + rangeX / 2 / 2 + rangeY / 2 / 2);
			mt2.x = (int) (xt - rangeX / 2 / 2 - rangeY / 2 / 2);
			mt2.y = (int) (yt - rangeX / 2 / 2 + rangeY / 2 / 2);
		}

// draw
		QuadCurve2D shape = new QuadCurve2D.Double();
		shape.setCurve(d1.x + 20, d1.y + 20, x, y, d2.x + 20, d2.y + 20);
		g2.draw(shape);

	}

	public void veMuiTenDuongCong1_1(Graphics2D g2, diem d1, diem d2) {
		float x = 0, y = 0;
		int xt = 0, yt = 0;
		Point mt1 = new Point();
		Point mt2 = new Point();
		if (d1.y < d2.y) {
			float rangeX = d2.x - d1.x;
			float rangeY = d2.y - d1.y;
			float giuaX = d1.x + rangeX / 2;
			float giuaY = d1.y + rangeY / 2;
			y = giuaY + rangeX / 2;
			x = giuaX - rangeY / 2;
			yt = (int) ((giuaY + rangeX / 2 / 2)); // + range x
			xt = (int) ((giuaX - rangeY / 2 / 2)); // + range y

			mt1.x = (int) (xt - rangeX / 2 / 2 + rangeY / 2 / 2);
			mt1.y = (int) (yt - rangeX / 2 / 2 - rangeY / 2 / 2 / 2);
			mt2.x = (int) (xt - rangeX / 2 / 2 - rangeY / 2 / 2 / 2);
			mt2.y = (int) (yt + rangeX / 2 / 2 - rangeY / 2 / 2);
		} else {
			float rangeX = d2.x - d1.x;
			float rangeY = d1.y - d2.y;
			float giuaX = d1.x + rangeX / 2;
			float giuaY = d1.y - rangeY / 2;
			y = giuaY + rangeX / 2;
			x = giuaX + rangeY / 2;
			yt = (int) ((giuaY + rangeX / 2 / 2));
			xt = (int) ((giuaX + rangeY / 2 / 2));
			mt1.x = (int) (xt - rangeX / 2 / 2 + rangeY / 2 / 2);
			mt1.y = (int) (yt + rangeX / 2 / 2 + rangeY / 2 / 2);
			mt2.x = (int) (xt - rangeX / 2 / 2 - rangeY / 2 / 2);
			mt2.y = (int) (yt - rangeX / 2 / 2 + rangeY / 2 / 2);
		}
		g2.setColor(Color.black);
		drawVienArrow1(g2, d1.x, d1.y, xt, yt);
		g2.setColor(Color.red);
		drawDuongCongArrow1(g2, d1.x, d1.y, xt, yt);
	}
	
	public void veMuiTenDuongCong2(Graphics2D g2, diem d1, diem d2) {
		float x = 0, y = 0;
		int xt = 0, yt = 0;
		Point mt1 = new Point();
		Point mt2 = new Point();
		if (d1.y < d2.y) {
			float rangeX = d2.x - d1.x;
			float rangeY = d2.y - d1.y;
			float giuaX = d1.x + rangeX / 2;
			float giuaY = d1.y + rangeY / 2;
			y = giuaY + rangeX / 2;
			x = giuaX - rangeY / 2;
			yt = (int) ((giuaY + rangeX / 2 / 2) + 10); // + range x
			xt = (int) ((giuaX - rangeY / 2 / 2) + 10); // + range y

			mt1.x = (int) (xt - rangeX / 2 / 2 + rangeY / 2 / 2);
			mt1.y = (int) (yt - rangeX / 2 / 2 - rangeY / 2 / 2 / 2);
			mt2.x = (int) (xt - rangeX / 2 / 2 - rangeY / 2 / 2 / 2);
			mt2.y = (int) (yt + rangeX / 2 / 2 - rangeY / 2 / 2);
		} else {
			float rangeX = d2.x - d1.x;
			float rangeY = d1.y - d2.y;
			float giuaX = d1.x + rangeX / 2;
			float giuaY = d1.y - rangeY / 2;
			y = giuaY + rangeX / 2;
			x = giuaX + rangeY / 2;
			yt = (int) ((giuaY + rangeX / 2 / 2) + 10);
			xt = (int) ((giuaX + rangeY / 2 / 2) + 10);
			mt1.x = (int) (xt - rangeX / 2 / 2 + rangeY / 2 / 2);
			mt1.y = (int) (yt + rangeX / 2 / 2 + rangeY / 2 / 2);
			mt2.x = (int) (xt - rangeX / 2 / 2 - rangeY / 2 / 2);
			mt2.y = (int) (yt - rangeX / 2 / 2 + rangeY / 2 / 2);
		}

//g2.fill(arrowHead);
//g2.drawPolygon(arrowHead);
//g2.setColor(Color.red);
//g2.drawRect(mt1.x, mt1.y, 3, 3);

//g2.fillRect(xt, yt, 5, 5);

		diem d3 = new diem(dl, xt, yt);
		veMuiTenDuongCong2_2(g2, d3, d2);
//drawArrow1(g2, xt, yt, d2.x, d2.y);
	}
	
	public void veMuiTenDuongCong2_2(Graphics2D g2, diem d1, diem d2) {
		float x = 0, y = 0;
		int xt = 0, yt = 0;
		Point mt1 = new Point();
		Point mt2 = new Point();
		if (d1.y < d2.y) {
			float rangeX = d2.x - d1.x;
			float rangeY = d2.y - d1.y;
			float giuaX = d1.x + rangeX / 2;
			float giuaY = d1.y + rangeY / 2;
			y = giuaY + rangeX / 2;
			x = giuaX - rangeY / 2;
			yt = (int) ((giuaY + rangeX / 2 / 2)); // + range x
			xt = (int) ((giuaX - rangeY / 2 / 2)); // + range y

			mt1.x = (int) (xt - rangeX / 2 / 2 + rangeY / 2 / 2);
			mt1.y = (int) (yt - rangeX / 2 / 2 - rangeY / 2 / 2 / 2);
			mt2.x = (int) (xt - rangeX / 2 / 2 - rangeY / 2 / 2 / 2);
			mt2.y = (int) (yt + rangeX / 2 / 2 - rangeY / 2 / 2);
		} else {
			float rangeX = d2.x - d1.x;
			float rangeY = d1.y - d2.y;
			float giuaX = d1.x + rangeX / 2;
			float giuaY = d1.y - rangeY / 2;
			y = giuaY + rangeX / 2;
			x = giuaX + rangeY / 2;
			yt = (int) ((giuaY + rangeX / 2 / 2));
			xt = (int) ((giuaX + rangeY / 2 / 2));
			mt1.x = (int) (xt - rangeX / 2 / 2 + rangeY / 2 / 2);
			mt1.y = (int) (yt + rangeX / 2 / 2 + rangeY / 2 / 2);
			mt2.x = (int) (xt - rangeX / 2 / 2 - rangeY / 2 / 2);
			mt2.y = (int) (yt - rangeX / 2 / 2 + rangeY / 2 / 2);
		}
		g2.setColor(Color.black);
		drawVienArrow1(g2, d1.x, d1.y, xt, yt);
		g2.setColor(Color.green);
		drawDuongCongArrow1(g2, d1.x, d1.y, xt, yt);
	}

	void drawVienArrow1(Graphics g1, double x1, double y1, double x2, double y2) {
		Graphics2D ga = (Graphics2D) g1.create();
//ga.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
		double l = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));// line length
		double d = l; // arrowhead distance from end of line. you can use your own value.

		double newX = ((x2 + (((x1 - x2) / (l) * d)))); // new x of arrowhead position on the line with d distance from
														// end of the line.
		double newY = ((y2 + (((y1 - y2) / (l) * d)))); // new y of arrowhead position on the line with d distance from
														// end of the line.

		double dx = x2 - x1, dy = y2 - y1;
		double angle = (Math.atan2(dy, dx)); // get angle (Radians) between ours line and x vectors line. (counter
												// clockwise)
		angle = (-1) * Math.toDegrees(angle);// cconvert to degree and reverse it to round clock for better understand
												// what we need to do.
		if (angle < 0) {
			angle = 360 + angle; // convert negative degrees to posative degree
		}
		angle = (-1) * angle; // convert to counter clockwise mode
		angle = Math.toRadians(angle);// convert Degree to Radians
		AffineTransform at = new AffineTransform();
		at.translate(newX, newY);// transport cursor to draw arrowhead position.
		at.rotate(angle);
		ga.transform(at);

		Polygon arrowHead = new Polygon();
		arrowHead.addPoint(22, 0);
		arrowHead.addPoint(-20, 20);
		arrowHead.addPoint(-10, -0);
		arrowHead.addPoint(-20, -20);
		ga.fill(arrowHead);
		ga.drawPolygon(arrowHead);
	}

	void drawDuongCongArrow1(Graphics g1, double x1, double y1, double x2, double y2) {
		Graphics2D ga = (Graphics2D) g1.create();
//ga.drawLine((int)x1, (int)y1, (int)x2, (int)y2);

		double l = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));// line length
		l = l / 2;
		double d = l; // arrowhead distance from end of line. you can use your own value.

		double newX = ((x2 + (((x1 - x2) / (l) * d)))); // new x of arrowhead position on the line with d distance from
														// end of the line.
		double newY = ((y2 + (((y1 - y2) / (l) * d)))); // new y of arrowhead position on the line with d distance from
														// end of the line.

		double dx = x2 - x1, dy = y2 - y1;
		double angle = (Math.atan2(dy, dx)); // get angle (Radians) between ours line and x vectors line. (counter
												// clockwise)
		angle = (-1) * Math.toDegrees(angle);// cconvert to degree and reverse it to round clock for better understand
												// what we need to do.
		if (angle < 0) {
			angle = 360 + angle; // convert negative degrees to posative degree
		}
		angle = (-1) * angle; // convert to counter clockwise mode
		angle = Math.toRadians(angle);// convert Degree to Radians
		AffineTransform at = new AffineTransform();
		at.translate(newX, newY);// transport cursor to draw arrowhead position.
		at.rotate(angle);
		ga.transform(at);

		Polygon arrowHead = new Polygon();
		arrowHead.addPoint(20, 0);
		arrowHead.addPoint(-18, 18);
		arrowHead.addPoint(-8, -0);
		arrowHead.addPoint(-18, -18);
		ga.fill(arrowHead);
		ga.drawPolygon(arrowHead);
	}

	public void veDuongCong(Graphics2D g2, diem d1, diem d2) {
		float x = 0, y = 0;
		int xt = 0, yt = 0;
		Point mt1 = new Point();
		Point mt2 = new Point();
		if (d1.y < d2.y) {
			float rangeX = d2.x - d1.x;
			float rangeY = d2.y - d1.y;
			float giuaX = d1.x + rangeX / 2;
			float giuaY = d1.y + rangeY / 2;
			y = giuaY + rangeX / 2;
			x = giuaX - rangeY / 2;
			yt = (int) ((giuaY + rangeX / 2 / 2) + 10); // + range x
			xt = (int) ((giuaX - rangeY / 2 / 2) + 10); // + range y

			mt1.x = (int) (xt - rangeX / 2 / 2 + rangeY / 2 / 2);
			mt1.y = (int) (yt - rangeX / 2 / 2 - rangeY / 2 / 2 / 2);
			mt2.x = (int) (xt - rangeX / 2 / 2 - rangeY / 2 / 2 / 2);
			mt2.y = (int) (yt + rangeX / 2 / 2 - rangeY / 2 / 2);
		} else {
			float rangeX = d2.x - d1.x;
			float rangeY = d1.y - d2.y;
			float giuaX = d1.x + rangeX / 2;
			float giuaY = d1.y - rangeY / 2;
			y = giuaY + rangeX / 2;
			x = giuaX + rangeY / 2;
			yt = (int) ((giuaY + rangeX / 2 / 2) + 10);
			xt = (int) ((giuaX + rangeY / 2 / 2) + 10);
			mt1.x = (int) (xt - rangeX / 2 / 2 + rangeY / 2 / 2);
			mt1.y = (int) (yt + rangeX / 2 / 2 + rangeY / 2 / 2);
			mt2.x = (int) (xt - rangeX / 2 / 2 - rangeY / 2 / 2);
			mt2.y = (int) (yt - rangeX / 2 / 2 + rangeY / 2 / 2);
		}

// draw
		QuadCurve2D shape = new QuadCurve2D.Double();
		shape.setCurve(d1.x + 20, d1.y + 20, x, y, d2.x + 20, d2.y + 20);
		g2.draw(shape);
//g2.fillRect((int) x, (int) y, 30, 30);
		g2.setColor(Color.blue);

		Polygon arrowHead = new Polygon();
		arrowHead.addPoint(xt, yt);
		arrowHead.addPoint(mt1.x, mt1.y);
		arrowHead.addPoint(mt2.x, mt2.y);

//g2.fill(arrowHead);
//g2.drawPolygon(arrowHead);
//g2.setColor(Color.red);
//g2.drawRect(mt1.x, mt1.y, 3, 3);

//g2.fillRect(xt, yt, 5, 5);

//diem d3 = new diem(dl, xt, yt);
//veDuongCong1(g2, d3, d2);
//drawArrow1(g2, xt, yt, d2.x, d2.y);
	}

	void drawArrow(Graphics g1, double x1, double y1, double x2, double y2) {
		Graphics2D ga = (Graphics2D) g1.create();
//ga.drawLine((int)x1, (int)y1, (int)x2, (int)y2);

		double l = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));// line length
		l = l / 2;
		double d = l / 2; // arrowhead distance from end of line. you can use your own value.

		double newX = ((x2 + (((x1 - x2) / (l) * d)))); // new x of arrowhead position on the line with d distance from
														// end of the line.
		double newY = ((y2 + (((y1 - y2) / (l) * d)))); // new y of arrowhead position on the line with d distance from
														// end of the line.

		double dx = x2 - x1, dy = y2 - y1;
		double angle = (Math.atan2(dy, dx)); // get angle (Radians) between ours line and x vectors line. (counter
												// clockwise)
		angle = (-1) * Math.toDegrees(angle);// cconvert to degree and reverse it to round clock for better understand
												// what we need to do.
		if (angle < 0) {
			angle = 360 + angle; // convert negative degrees to posative degree
		}
		angle = (-1) * angle; // convert to counter clockwise mode
		angle = Math.toRadians(angle);// convert Degree to Radians
		AffineTransform at = new AffineTransform();
		at.translate(newX, newY);// transport cursor to draw arrowhead position.
		at.rotate(angle);
		ga.transform(at);

		Polygon arrowHead = new Polygon();
		arrowHead.addPoint(20, 0);
		arrowHead.addPoint(-18, 18);
		arrowHead.addPoint(-8, -0);
		arrowHead.addPoint(-18, -18);
		ga.fill(arrowHead);
		ga.drawPolygon(arrowHead);
	}
	
	void drawArrow1(Graphics g1, double x1, double y1, double x2, double y2) {
		Graphics2D ga = (Graphics2D) g1.create();
//ga.drawLine((int)x1, (int)y1, (int)x2, (int)y2);

		double l = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));// line length
		l = l / 2;
		double d = l / 2; // arrowhead distance from end of line. you can use your own value.

		double newX = ((x2 + (((x1 - x2) / (l) * d)))); // new x of arrowhead position on the line with d distance from
														// end of the line.
		double newY = ((y2 + (((y1 - y2) / (l) * d)))); // new y of arrowhead position on the line with d distance from
														// end of the line.

		double dx = x2 - x1, dy = y2 - y1;
		double angle = (Math.atan2(dy, dx)); // get angle (Radians) between ours line and x vectors line. (counter
												// clockwise)
		angle = (-1) * Math.toDegrees(angle);// cconvert to degree and reverse it to round clock for better understand
												// what we need to do.
		if (angle < 0) {
			angle = 360 + angle; // convert negative degrees to posative degree
		}
		angle = (-1) * angle; // convert to counter clockwise mode
		angle = Math.toRadians(angle);// convert Degree to Radians
		AffineTransform at = new AffineTransform();
		at.translate(newX, newY);// transport cursor to draw arrowhead position.
		at.rotate(angle);
		ga.transform(at);

		Polygon arrowHead = new Polygon();
		arrowHead.addPoint(20, 0);
		arrowHead.addPoint(-18, 18);
		arrowHead.addPoint(-8, -0);
		arrowHead.addPoint(-18, -18);
		ga.setColor(Color.green);
		ga.fill(arrowHead);
		ga.drawPolygon(arrowHead);
	}

	void drawVienArrow(Graphics g1, double x1, double y1, double x2, double y2) {
		Graphics2D ga = (Graphics2D) g1.create();
//ga.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
		double l = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));// line length
		double d = l / 2; // arrowhead distance from end of line. you can use your own value.

		double newX = ((x2 + (((x1 - x2) / (l) * d)))); // new x of arrowhead position on the line with d distance from
														// end of the line.
		double newY = ((y2 + (((y1 - y2) / (l) * d)))); // new y of arrowhead position on the line with d distance from
														// end of the line.

		double dx = x2 - x1, dy = y2 - y1;
		double angle = (Math.atan2(dy, dx)); // get angle (Radians) between ours line and x vectors line. (counter
												// clockwise)
		angle = (-1) * Math.toDegrees(angle);// cconvert to degree and reverse it to round clock for better understand
												// what we need to do.
		if (angle < 0) {
			angle = 360 + angle; // convert negative degrees to posative degree
		}
		angle = (-1) * angle; // convert to counter clockwise mode
		angle = Math.toRadians(angle);// convert Degree to Radians
		AffineTransform at = new AffineTransform();
		at.translate(newX, newY);// transport cursor to draw arrowhead position.
		at.rotate(angle);
		ga.transform(at);

		Polygon arrowHead = new Polygon();
		arrowHead.addPoint(22, 0);
		arrowHead.addPoint(-20, 20);
		arrowHead.addPoint(-10, -0);
		arrowHead.addPoint(-20, -20);
		ga.fill(arrowHead);
		ga.drawPolygon(arrowHead);
	}

}
