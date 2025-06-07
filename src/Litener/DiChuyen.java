package Litener;

import java.awt.*;
import java.awt.event.*;

import main.duLieu;
import node.diem;

public class DiChuyen implements MouseListener, MouseMotionListener {
	duLieu dl;

//	public int diemDiChuyen = 0;
//	public boolean bDiChuyen = false;
	public DiChuyen(duLieu dl) {
		this.dl = dl;
	}

// set up GUI and register mouse event handlers

// MouseListener event handlers
// handle event when mouse released immediately
// after press
	public void mouseClicked(MouseEvent event) {
		if (dl.pMaTran.diemDau != -1) {
			if(dl.khungCongCu.bXoaDiem == true) {		//Xóa điểm
				dl.lbMaTran.xoaDiemTrucTiep(dl.pMaTran.diemDau);
			}else
			if (dl.khungBangVe.bDiChuyen == false) {
				dl.khungBangVe.bDiChuyen = true;
				dl.khungBangVe.diemDiChuyen = dl.pMaTran.diemDau;
//					System.out.println(diemDiChuyen);
			} else {
				dl.khungBangVe.bDiChuyen = false;
			}
			dl.pMaTran.diemDau = -1;
		} else {
			// thêm đỉnh trực tiếp
			if (dl.khungCongCu.bThemDinhTT == true && dl.pMaTran.diemDau == -1 && dl.soLuong < 99) {
				dl.soLuong++;
				dl.lbMaTran.updataMaTran();
				dl.diem[dl.soLuong] = new diem(dl, event.getX() - 20, event.getY() - 20);
				dl.updata();
			}
		}

//		System.out.println(bDiChuyen);
		
	}

// handle event when mouse pressed
	public void mousePressed(MouseEvent event) {
		for (int i = 1; i <= dl.soLuong; i++) {
			if (event.getX() > dl.diem[i].x && event.getX() < dl.diem[i].x + 40 && event.getY() > dl.diem[i].y
					&& event.getY() < dl.diem[i].y + 40) {
				dl.pMaTran.diemDau = i;
			}
		}
	}

// handle event when mouse released after dragging
	public void mouseReleased(MouseEvent event) {
		if (dl.pMaTran.noiCanh == true) {
			for (int i = 1; i <= dl.soLuong; i++)
				if (event.getX() > dl.diem[i].x && event.getX() < dl.diem[i].x + 40 && event.getY() > dl.diem[i].y
						&& event.getY() < dl.diem[i].y + 40) {
					dl.pMaTran.diemCuoi = i;
					if (dl.pMaTran.diemCuoi != dl.pMaTran.diemDau)
						dl.capNhatMaTran(dl.pMaTran.diemDau, dl.pMaTran.diemCuoi);

					// reset
				}
			dl.pMaTran.diemDau = -1;
			dl.pMaTran.noiCanh = false;
			dl.lbMaTran.repaint();
			dl.khungBangVe.repaint();
		}
	}

// handle event when mouse enters area
	public void mouseEntered(MouseEvent event) {
//		statusBar.setText("Mouse in window");
//		System.out.println("hello");
	}

// handle event when mouse exits area
	public void mouseExited(MouseEvent event) {
//		statusBar.setText("Mouse outside window");
//		System.out.println("hello");
	}

// MouseMotionListener event handlers
// handle event when user drags mouse with button pressed
	public void mouseDragged(MouseEvent event) {
		if (dl.pMaTran.diemDau != -1) {
			dl.pMaTran.noiCanh = true;
			dl.pMaTran.chuotX = event.getX();
			dl.pMaTran.chuotY = event.getY();
			dl.khungBangVe.repaint();
		}
	}

// handle event when user moves mouse
	public void mouseMoved(MouseEvent event) {
		if (dl.khungBangVe.bDiChuyen == true) {
			dl.diem[dl.khungBangVe.diemDiChuyen].x = event.getX() - 20;
			dl.diem[dl.khungBangVe.diemDiChuyen].y = event.getY() - 20;
			dl.khungBangVe.repaint();

			dl.updata();
		}
//		System.out.println(diemDiChuyen);
	}
}