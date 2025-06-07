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

import javax.swing.JPanel;

import main.duLieu;
import node.diem;

public class veLienThong extends JPanel {
	duLieu dl;
	int tPLThong;
	String listThanhPhanLienThong[];
	int soThanhPhanLienThong;

	public veLienThong(duLieu dl, int tPLThong, String []listThanhPhanLienThong, int soThanhPhanLienThong) {
		this.setPreferredSize(new Dimension(885, 600));
//		this.setBounds(223, 70, 885, 600);
		this.listThanhPhanLienThong = listThanhPhanLienThong;
		this.dl = dl;
		this.tPLThong = tPLThong;
		this.soThanhPhanLienThong = soThanhPhanLienThong;
		this.repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));
		if (tPLThong > 1) {
			g2.setColor(Color.red);
			g2.drawString("Đồ thị không liên thông", 10, 540);
			g2.setColor(Color.black);
			g2.drawString("Số thành phần liên thông: " + tPLThong, 10, 565);
		}
		
		
		int max = 0, min = 0;
		min = listThanhPhanLienThong[0].length()/2;
		
		for(int k=0; k<soThanhPhanLienThong; k++) {
			String a[] = listThanhPhanLienThong[k].split(" ");
			if(a.length > max) {
				max = a.length;
			}
			if(a.length < min) {
				min = a.length;
			}
			for(int i=0; i<a.length; i++) {
				for(int j=1; j<=dl.soLuong; j++) {
						if(dl.diem[Integer.parseInt(a[i])].dinhKe[j] == true) {
							System.out.println("Diem dau: "+Integer.parseInt(a[i])+" Diem cuoi: "+ j);
							drawCanh(g, g2, Integer.parseInt(a[i]), j, k+1);
							
						}
					
				}
			}
		}
			
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));
		g2.setColor(Color.black);
		g2.drawString("Thành phần liên thông lớn nhất: " + max, 500, 540);
		g2.drawString("Thành phần liên thông nhỏ nhất: " + min, 500, 565);
		
		System.out.println("Max: "+max+" Min: "+min);

		// draw Diem
		for (int i = 1; i <= dl.soLuong; i++) {
			dl.diem[i].drawDiem(g2);
		}


		g2.dispose();
	}

	public void setMau(Graphics2D g2, int So) {
		int mauSo = So%8;
		System.out.println("Màu: "+mauSo);
		switch (mauSo) {
		case 1:
			g2.setColor(Color.red);
			break;
		case 2:
			g2.setColor(Color.cyan);
			break;
		case 3:
			g2.setColor(Color.yellow);
			break;
		case 4:
			g2.setColor(Color.green);
			break;
		case 5:
			g2.setColor(Color.blue);
			break;
		case 6:
			g2.setColor(Color.orange);
			break;
		case 7:
			g2.setColor(Color.pink);
			break;

		}
	}

	public void drawCanh(Graphics g, Graphics2D g2, int i, int j, int soMau) {
		if (dl.khungCongCu.bVoHuong == true) {
			if (dl.Canh[i][j] > 0 && dl.Canh[i][j] == dl.Canh[j][i]) {

				// Viền
				BasicStroke bs2 = new BasicStroke(8, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
				g2.setStroke(bs2);
				g2.setColor(Color.black);
				g2.drawLine(dl.diem[i].x + 20, dl.diem[i].y + 20, dl.diem[j].x + 20, dl.diem[j].y + 20);

				// Cạnh
				BasicStroke bs1 = new BasicStroke(6, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
				g2.setStroke(bs1);
				setMau(g2, soMau);
				g2.drawLine(dl.diem[i].x + 20, dl.diem[i].y + 20, dl.diem[j].x + 20, dl.diem[j].y + 20);

			}

		} else { // Có hướng
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
							setMau(g2, soMau);
							if (dl.Canh[j][i] > 0) {
//								drawArrow1(g, dl.diem[i].x + 20, dl.diem[i].y + 20, dl.diem[j].x + 20,
//										dl.diem[j].y + 20);
								veDuongCong(g2, dl.diem[i], dl.diem[j]);
								BasicStroke bs5 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
								g2.setStroke(bs5);
								g2.setColor(Color.black);
								veMuiTenDuongCong(g2, dl.diem[i], dl.diem[j], soMau);
							} else {
								g2.drawLine(dl.diem[i].x + 20, dl.diem[i].y + 20, dl.diem[j].x + 20, dl.diem[j].y + 20);
								BasicStroke bs6 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
								g2.setStroke(bs6);
								setMau(g2, soMau);
								drawArrow(g, dl.diem[i].x + 20, dl.diem[i].y + 20, dl.diem[j].x + 20, dl.diem[j].y + 20);
							}
						}
					
				}

		}
	
	public void veMuiTenDuongCong(Graphics2D g2, diem d1, diem d2, int soMau) {
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

//		g2.fill(arrowHead);
//		g2.drawPolygon(arrowHead);
//		g2.setColor(Color.red);
//		g2.drawRect(mt1.x, mt1.y, 3, 3);

//		g2.fillRect(xt, yt, 5, 5);

		diem d3 = new diem(dl, xt, yt);
		veMuiTenDuongCong1(g2, d3, d2, soMau);
//		drawArrow1(g2, xt, yt, d2.x, d2.y);
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

	public void veMuiTenDuongCong1(Graphics2D g2, diem d1, diem d2, int soMau) {
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
		setMau(g2, soMau);
		drawDuongCongArrow1(g2, d1.x, d1.y, xt, yt);
	}

	void drawVienArrow1(Graphics g1, double x1, double y1, double x2, double y2) {
		Graphics2D ga = (Graphics2D) g1.create();
//    ga.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
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
//    ga.drawLine((int)x1, (int)y1, (int)x2, (int)y2);

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
//		g2.fillRect((int) x, (int) y, 30, 30);
		g2.setColor(Color.blue);

		Polygon arrowHead = new Polygon();
		arrowHead.addPoint(xt, yt);
		arrowHead.addPoint(mt1.x, mt1.y);
		arrowHead.addPoint(mt2.x, mt2.y);

//		g2.fill(arrowHead);
//		g2.drawPolygon(arrowHead);
//		g2.setColor(Color.red);
//		g2.drawRect(mt1.x, mt1.y, 3, 3);

//		g2.fillRect(xt, yt, 5, 5);

//		diem d3 = new diem(dl, xt, yt);
//		veDuongCong1(g2, d3, d2);
//		drawArrow1(g2, xt, yt, d2.x, d2.y);
	}

	void drawArrow(Graphics g1, double x1, double y1, double x2, double y2) {
		Graphics2D ga = (Graphics2D) g1.create();
//    ga.drawLine((int)x1, (int)y1, (int)x2, (int)y2);

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

	void drawVienArrow(Graphics g1, double x1, double y1, double x2, double y2) {
		Graphics2D ga = (Graphics2D) g1.create();
//    ga.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
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

