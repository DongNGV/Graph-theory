package Litener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class KoNhap implements KeyListener{
	JTextField textEven;
	public KoNhap(JTextField textEven) {
		this.textEven = textEven;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		if (textEven.getText().length() >= 1 ) {	// giới hạn ký tự nhập
			e.consume();
		}
		if (e.getKeyChar() != '0') { // giới hạn ký tự từ 0 > 9
			e.consume();
		}
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	
}
