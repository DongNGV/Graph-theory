package node;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import main.duLieu;

public class diem {
	duLieu dl;
	public int x, y;
	public String name;
	public boolean dinhKe[] = new boolean[100];
	
	public diem(duLieu dl, int x, int y) {
		this.dl = dl;
		this.x = x;
		this.y = y;
		this.name = ""+dl.soLuong;
	}

	public void drawDiem(Graphics2D g2) {
		// Hinh
		g2.setColor(new Color(255, 131, 250));
		g2.fillOval(x, y, 40, 40);
		// Viền
		BasicStroke bs1 = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
        g2.setStroke(bs1);
		g2.setColor(new Color(0, 245, 255));
		g2.drawOval(x, y, 40, 40);
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 25F));
		g2.setColor(Color.black);
		int strX = InChuGiuaManHinh(name, g2);
		g2.drawString(name, x+strX, y+28);
		
		
	}
	
	public void drawDiemDuongDi(Graphics2D g2) {
		// Hinh
		g2.setColor(Color.red);
		g2.fillOval(x, y, 40, 40);
		// Viền
		BasicStroke bs1 = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
        g2.setStroke(bs1);
		g2.setColor(new Color(0, 245, 255));
		g2.drawOval(x, y, 40, 40);
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 25F));
		g2.setColor(Color.black);
		int strX = InChuGiuaManHinh(name, g2);
		g2.drawString(name, x+strX, y+28);
		
		
	}
	
	public int InChuGiuaManHinh(String text, Graphics2D g2) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = 40/2 - length/2;
		return x;
	}
}
