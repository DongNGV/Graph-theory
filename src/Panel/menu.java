package Panel;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import main.duLieu;
import node.diem;

public class menu extends MenuBar {
	duLieu dl;
	Menu menuFile, menuEdit, menuView;

	public menu(duLieu dl) {
		this.dl = dl;
		menuFile = new Menu("File");
		menuEdit = new Menu("Edit");
		menuView = new Menu("View");
		addMenu();
		addMenuItem();
		
	}

	public void addMenu() {
		this.add(menuFile);
		//this.add(menuEdit);
		//this.add(menuView);
	}

	public void addMenuItem() {
		// Menu File
		MenuItem itemNew = new MenuItem("Làm mới");
		MenuItem itemOpen = new MenuItem("Mở file");
		MenuItem itemSave = new MenuItem("Lưu ma trận");
		MenuItem itemSaveViTri = new MenuItem("Lưu ma trận + vị trí");
		MenuItem itemSaveImage = new MenuItem("Lưu ảnh");
		MenuItem itemExit = new MenuItem("Thoát");

// Load file
		JFileChooser fileDialog = new JFileChooser("D:");
		
		itemOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt", "text");
				fileDialog.setFileFilter(filter); // ép kiểu dữ liệu thành .txt
				
				dl.soLuong = 0;
				int returnVal = fileDialog.showOpenDialog(dl.khungBangVe); // nơi hiện khung chọn file
				if (returnVal == JFileChooser.APPROVE_OPTION) { // nếu lấy được ra 1 file
					java.io.File file = fileDialog.getSelectedFile(); // gán file được chọn vào file khởi tạo
//                    statusLabel.setText("File Selected :" + file.getName());	// file.getName(): lấy ra tên file
					FileReader fr, fr1;
					BufferedReader br, br1;
					
					boolean errFile = false;
					if(1 == 1) {
						try {
							fr1 = new FileReader(file);
							br1 = new BufferedReader(fr1);

							String lengthLine = br1.readLine();
//							System.out.println(lengthLine);
							String lengthMaTran[] = lengthLine.split(" ");
							int length = lengthMaTran.length;
							
							if(!lengthMaTran[0].equals("0")) {
								errFile = true;
							}
							
							for (int i = 2; i <= length; i++) {
								String line = br1.readLine();
								String numbers[] = line.split(" ");
								if(!numbers[i-1].equals("0")) {
									errFile = true;
								}
							}
						}
						catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							System.out.println("file lỗi");
						}
					}
					
					
					if(errFile == false) {
						try {
							fr = new FileReader(file);
							br = new BufferedReader(fr);

							String lengthLine = br.readLine();
//							System.out.println(lengthLine);
							String lengthMaTran[] = lengthLine.split(" ");
							int length = lengthMaTran.length;

							for (int i = 1; i <= length; i++) {
								dl.soLuong++;
								dl.lbMaTran.updataMaTran();
								dl.diem[dl.soLuong] = new diem(dl, dl.soLuong * 50, 50);
							}

							String numbers1[] = lengthLine.split(" ");
//							System.out.println(numbers1.length);
							for (int j = 1; j <= numbers1.length; j++) {
								dl.lbMaTran.text[1][j].setText(numbers1[j - 1]);
							}

							for (int i = 2; i <= length; i++) {
								String line = br.readLine();
								String numbers[] = line.split(" ");
								for (int j = 1; j <= numbers.length; j++) {
									dl.lbMaTran.text[i][j].setText(numbers[j - 1]);
								}
							}
							
							// đọc vị trí
							try {
								for(int i=1; i<=dl.soLuong; i++) {
									String viTri = br.readLine();
									String lViTri[] = viTri.split(" ");
									dl.diem[i].x = Integer.parseInt(lViTri[0]);
									dl.diem[i].y = Integer.parseInt(lViTri[1]);
								}
							} catch (Exception e2) {
								System.out.println("Ko có vị trí");
							}

							dl.lbMaTran.xoaDiemDu();
							dl.lbMaTran.repaint();
							dl.updata();

						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							System.out.println("file lỗi");
						}
					}
					else {
						JOptionPane.showMessageDialog(dl.khungBangVe, "Lỗi file!!!");
					}
					
				} else {
					System.out.println("Không có file");
				}
//				dl.khungCongCu.cBFS.doClick();
			}
		});

// Save file
		itemSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { // nơi hiện khung chọn file
				FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt", "text");
				fileDialog.setFileFilter(filter); // ép kiểu dữ liệu thành .txt
				int userSelection = fileDialog.showSaveDialog(dl.khungBangVe);

				if (userSelection == JFileChooser.APPROVE_OPTION) {
					File fileToSave = fileDialog.getSelectedFile();
					System.out.println("Save as file: " + fileToSave.getAbsolutePath() + ".txt");

					FileOutputStream fout = null;
					try {
						fout = new FileOutputStream(fileToSave + ".txt");
						String s = "";
						for (int i = 1; i <= dl.soLuong; i++) {
							for (int j = 1; j <= dl.soLuong; j++) {
								if (dl.lbMaTran.text[i][j].getText().equals("")) {
									s += "0";
								} else {
									s += dl.lbMaTran.text[i][j].getText();
								}
								s += " ";

							}
							s += "\n";
						}
						byte b[] = s.getBytes();// converting string into byte array
						fout.write(b);
						fout.close();
//			            System.out.println("success...");
					} catch (Exception e1) {
						System.out.println(e1);
					} finally {
						try {
							fout.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		
		// Save vị trí
				itemSaveViTri.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) { // nơi hiện khung chọn file
						FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt", "text");
						fileDialog.setFileFilter(filter); // ép kiểu dữ liệu thành .txt
						int userSelection = fileDialog.showSaveDialog(dl.khungBangVe);

						if (userSelection == JFileChooser.APPROVE_OPTION) {
							File fileToSave = fileDialog.getSelectedFile();
							System.out.println("Save as file: " + fileToSave.getAbsolutePath() + ".txt");

							FileOutputStream fout = null;
							try {
								fout = new FileOutputStream(fileToSave + ".txt");
								String s = "";
								for (int i = 1; i <= dl.soLuong; i++) {
									for (int j = 1; j <= dl.soLuong; j++) {
										if (dl.lbMaTran.text[i][j].getText().equals("")) {
											s += "0";
										} else {
											s += dl.lbMaTran.text[i][j].getText();
										}
										s += " ";

									}
									s += "\n";
								}
								for(int i = 1; i<=dl.soLuong; i++) {
									s += dl.diem[i].x + " " + dl.diem[i].y + "\n";
								}
								byte b[] = s.getBytes();// converting string into byte array
								fout.write(b);
								fout.close();
//					            System.out.println("success...");
							} catch (Exception e1) {
								System.out.println(e1);
							} finally {
								try {
									fout.close();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
					}
				});

// New file
		itemNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dl.soLuong = 0;
				dl.lbMaTran.xoaDiemDu();
				dl.lbMaTran.updataMaTran();
				dl.updata();
				dl.lbMaTran.repaint();
				dl.khungBangVe.repaint();
				dl.khungBangVe.setBackground(Color.lightGray);
				dl.khungBangVe.repaint();
			}
		});

		itemExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});

		itemSaveImage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { // nơi hiện khung chọn file
				FileNameExtensionFilter filter = new FileNameExtensionFilter(".png", "png", "png");
				fileDialog.setFileFilter(filter); // ép kiểu dữ liệu thành .txt
				
				int userSelection = fileDialog.showSaveDialog(dl.khungBangVe);

				if (userSelection == JFileChooser.APPROVE_OPTION) {
					File fileToSave = fileDialog.getSelectedFile();
					System.out.println("Save as file: " + fileToSave.getAbsolutePath() + ".png");
					dl.khungBangVe.saveImage(dl.khungBangVe, fileToSave);
				}
			}
		});

//			@Override
//			public void actionPerformed(ActionEvent e) {
//				dl.khungBangVe.saveImage(dl.khungBangVe);
//				
//			}
//		});

		menuFile.add(itemNew);
		menuFile.add(itemOpen);
		menuFile.add(itemSave);
		menuFile.add(itemSaveViTri);
		menuFile.add(itemSaveImage);
		menuFile.add(itemExit);

		// Menu Edit
		MenuItem itemCopy = new MenuItem("Copy");
		MenuItem itemCut = new MenuItem("Save");

		menuEdit.add(itemCopy);
		menuEdit.add(itemCut);

		// Menu View
		MenuItem itemAnh = new MenuItem("Xem ảnh");

		menuView.add(itemAnh);

	}
}
