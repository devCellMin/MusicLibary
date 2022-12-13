package GUI;

import java.awt.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URI;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import LoginGUI.LoginScreen;
import MusicSetting.*;
import UserSetting.UserBuilder;
import UserSetting.UserFrame;
import UserSetting.UserTableData;
import UserSetting.Userset;

public class MainGUI extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean isUser;String UserselectedTitle, AdminselectedTitle;
	Userset user;
	public MainGUI(Userset user, boolean isUser) {
		this.user = user;
		this.isUser = isUser;
		run();
	}
	
	public MainGUI() {
		this.user = setuser();
		if(this.user.getUserType().equals("User")) {
			this.isUser = true;
		}else {
			this.isUser = false;
		}
		run();
	}
	private Userset setuser() {
		Userset userset = new Userset();
		UserBuilder ub = new UserBuilder(userset);
		userset = ub.userId("kbs1").userName("국민방송1").userPWD("1234")
				.userAge(100).userGender("남자").userPhoneNum("010-9755-9686").userAddr("부산광역시").userType("Administer").getUser();
		return userset;
	}

	MusicTableData MusicTable = new MusicTableData();
	UserTableData UserTable = new UserTableData();
	CardLayout Card, BtnCard; // CardLayout for Each Pages and Button Navigation Bar
	JPanel btnPanel; JButton btnUser, btnAdmin; // Button to go User's Page or Admin's Page
	JPanel ShowPanel, UserPanel, AdminPanel; // Show UserPanel and AdminPanel with CardLayout
	JPanel BCPanel, NavUserPanel, NavAdminPanel; // Show User's Navigation Bar and Administer's Navigation Bar with CardLayout
	JButton btnMyPage, btnShowMusicinfo, btnLogOut; // User's Button Navigation Bar
	JButton btnAddMusic, btnEdit, btnDelMusic, btnRefresh; // Administer's Button Navigation Bar
	// Screen Size(1280, 720)
	private void run() {
		String GuiPath = "images\\gui\\";
		ImagePanel GUIPanel = new ImagePanel(new ImageIcon(GuiPath+"background.png").getImage());
		Card = new CardLayout(0, 0);
		
		Image logoIcon = new ImageIcon(GuiPath+"logo.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		JLabel LblLogo = new JLabel(new ImageIcon(logoIcon));
		
		Image logoAddIcon = new ImageIcon(GuiPath+"LogoAdd.png").getImage().getScaledInstance(200, 60, Image.SCALE_SMOOTH);
		JLabel LblLogoadd = new JLabel(new ImageIcon(logoAddIcon));
		
		LblLogo.setBounds(10, 10, 60, 60);
		LblLogo.setPreferredSize(new Dimension(60, 60));
		LblLogoadd.setBounds(75, 10, 200, 60);
		LblLogoadd.setPreferredSize(new Dimension(200, 60));

		// Show Part
		ShowPanel = new JPanel(Card);
		ShowPanel.setBounds(10, 90, 1245, 620);

		addUserPanel();
		addAdminPanel();
		
		//Add Button to go User's Page and Admin's Page
		btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		btnPanel.setBounds(936, 10, 335, 35);
		btnPanel.setBackground(Color.decode("#333333"));
		AddBtnPage();
		
		btnUser.addActionListener(this);
		btnAdmin.addActionListener(this);
		
		btnUser.setBackground(Color.decode("#d7d2cb"));
		btnAdmin.setBackground(Color.decode("#d7d2cb"));
		
		// Add Buttons on the UserPanel or AdminPanel
		// User =>  My Page / My Favorite List / Logout
		// Administer => 곡추가/곡수정/곡삭제/회원관리
		BtnCard = new CardLayout(0, 0);
		BCPanel = new JPanel(BtnCard);
		BCPanel.setBounds(571, 50, 680, 35);
		BCPanel.setBackground(Color.decode("#333333"));
		
		AddUserButtonCard();
		AddAdminButtonCard();

		// Add Containers in to Component
		GUIPanel.add(LblLogo);
		GUIPanel.add(LblLogoadd);
		GUIPanel.add(btnPanel);
		GUIPanel.add(ShowPanel);
		GUIPanel.add(BCPanel);
		add(GUIPanel);
		
		// Frame Setting
		setTitle("Music Library");
		setVisible(true);
		setSize(GUIPanel.getDim());
		setPreferredSize(GUIPanel.getDim());
		setResizable(false);
		setLocation(250, 150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void AddBtnPage() {
		btnUser = new JButton("유저페이지");
		btnAdmin = new JButton("관리자모드");
		
		// If Constructor get's Boolean 
		if(isUser) {
			btnPanel.setVisible(false);
			btnUser.setVisible(false);
			btnAdmin.setVisible(false);
			btnUser.setEnabled(false);
			btnAdmin.setEnabled(false);
		}else {
			btnPanel.setVisible(true);
			btnUser.setVisible(true);
			btnAdmin.setVisible(true);
			btnUser.setEnabled(true);
			btnAdmin.setEnabled(true);
		}
		
		btnUser.setSize(150, 30);
		btnUser.setPreferredSize(new Dimension(150, 30));
		btnAdmin.setSize(150, 30);
		btnAdmin.setPreferredSize(new Dimension(150, 30));
		
		btnUser.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnAdmin.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		
		btnPanel.add(btnUser);
		btnPanel.add(btnAdmin);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Change the Page of Panel and Buttons from CardLayout
		String str = e.getActionCommand();
		if(str.equals("유저페이지")) {
			Card.show(ShowPanel, "User");
			BtnCard.show(BCPanel, "User");
		}else if(str.equals("관리자모드")) {
			Card.show(ShowPanel, "Admin");
			BtnCard.show(BCPanel, "Admin");
		}
	}
	JPanel MusicListPanel;
	JScrollPane MusicListScroll;
	JTable UserMusicTable, FavoriteMusicTable;
	MusicPanel musicPanel;
	private void addUserPanel() {
		// Add User Page
		UserPanel = new JPanel(null);
		musicPanel = new MusicPanel("default");
		MusicListPanel = new JPanel(null);
		showTable("MusicList"); // Set Table
		JTableHeader header = UserMusicTable.getTableHeader();

		
		
		// Set Panel Size
		UserPanel.setBounds(0, 0, 1245, 620);
		musicPanel.setLocation(10, 10);
		MusicListPanel.setBounds(835, 20, 430, 610);
		UserMusicTable.setRowHeight(25);
		UserMusicTable.setBorder(BorderFactory.createEmptyBorder());
		MusicListScroll.setBounds(0, 23, 375, 550);
		MusicListScroll.setPreferredSize(new Dimension(375, 550));
	    header.setPreferredSize(new Dimension(UserMusicTable.getWidth(), 30));
		
		
		// Set Label
	    musicPanel.setBackground(Color.decode("#333333"));
		UserMusicTable.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		header.setFont(new Font("맑은 고딕", Font.BOLD,14)); // Table Header

		// Set Panel's Background Color
		UserPanel.setBackground(Color.decode("#333333"));
		MusicListPanel.setBackground(Color.decode("#333333"));
		MusicListScroll.setBackground(Color.decode("#333333"));
		header.setBackground(Color.decode("#cccccc"));
	    header.setForeground(Color.decode("#333333"));
	    
	    UserMusicTable.addMouseListener(new MouseListener() {
	    	public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable table = (JTable)e.getSource();
				UserselectedTitle = MusicTable.getMusicfromTable(table.getSelectedRow()).getTitle();
				if(e.getClickCount()==2) {
					Music music = MusicTable.getMusic(UserselectedTitle);
					musicPanel.setVisible(false);;
					musicPanel = new MusicPanel(music);
					musicPanel.setLocation(10, 10);
					musicPanel.setBackground(Color.decode("#333333"));
					UserPanel.add(musicPanel);
				}
			}
		});
	    

		
		// Add Things to Panels and Frame
	    MusicListPanel.add(MusicListScroll);
		UserPanel.add(musicPanel);
		UserPanel.add(MusicListPanel);
		ShowPanel.add("User", UserPanel);
	}

	private void showTable(String string) {
		UserMusicTable = new JTable(MusicTable.showUserTable(), MusicTable.getUserheader()) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				//all cells false
				return false;
			}
		};
		MusicListScroll = new JScrollPane(UserMusicTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	}
	



	JTable AdminMusicTable, AdminUserTable;
	private void addAdminPanel() {
		// Add Administer Page
		AdminPanel = new JPanel(null);
		AdminPanel.setBounds(0, 0, 1245, 620);
		AdminPanel.setBackground(Color.decode("#333333"));
		
		// Add Administer Music Table at Left of Admin's Panel
		AdminMusicTable = new JTable(MusicTable.showAdminTable(), MusicTable.getShowheader()) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				//all cells false
				return false;
			}
		};
		JScrollPane AdminMusicPane = new JScrollPane(AdminMusicTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		AdminMusicPane.setBounds(10, 10, 720, 600);
		AdminMusicPane.setPreferredSize(new Dimension(720, 600));
		AdminMusicTable.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		AdminMusicTable.setRowHeight(20);
		AdminMusicPane.setBackground(Color.decode("#333333"));
		
		JTableHeader header = AdminMusicTable.getTableHeader();
	    header.setBackground(Color.decode("#cccccc"));
	    header.setForeground(Color.decode("#333333"));
	    header.setFont(new Font("맑은 고딕", Font.BOLD,14));
		
	    // Add Administer User Table at Left of Admin's Panel
	    AdminUserTable = new JTable(UserTable.getUserTableData(), UserTable.getShowheaders()) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				//all cells false
				return false;
			}
		};
	    JScrollPane AdminUserPane = new JScrollPane(AdminUserTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	    AdminUserPane.setBounds(740, 10, 490, 600);
	    AdminUserPane.setPreferredSize(new Dimension(490, 600));
	    AdminUserTable.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
	    AdminUserTable.setRowHeight(20);
	    AdminUserPane.setBackground(Color.decode("#333333"));
		
		JTableHeader header1 = AdminUserTable.getTableHeader();
		header1.setBackground(Color.decode("#cccccc"));
		header1.setForeground(Color.decode("#333333"));
		header1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
	    
		AdminMusicTable.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable table = (JTable)e.getSource();
				AdminselectedTitle = MusicTable.getMusicfromTable(table.getSelectedRow()).getTitle();
				if(e.getClickCount()==2) {
					Music music = MusicTable.getMusic(AdminselectedTitle);
					new MusicFrame(music).addWindowListener(new WindowListener() {
						public void windowOpened(WindowEvent e) {}
						public void windowIconified(WindowEvent e) {}
						public void windowDeiconified(WindowEvent e) {}
						public void windowDeactivated(WindowEvent e) {
							AdminMusicTable.setModel(new DefaultTableModel(MusicTable.showAdminTable(), MusicTable.getShowheader()));
							UserMusicTable.setModel(new DefaultTableModel(MusicTable.showUserTable(), MusicTable.getUserheader()));
						}
						public void windowClosing(WindowEvent e) {}
						public void windowActivated(WindowEvent e) {}
						public void windowClosed(WindowEvent e) {
							AdminMusicTable.setModel(new DefaultTableModel(MusicTable.showAdminTable(), MusicTable.getShowheader()));
							UserMusicTable.setModel(new DefaultTableModel(MusicTable.showUserTable(), MusicTable.getUserheader()));
						}
					});
				}
			}
		});
		
		AdminUserTable.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable table = (JTable)e.getSource();
				if(e.getClickCount()==2) {
					Userset selecteduser = UserTable.getUser(table.getSelectedRow());
					new UserFrame(selecteduser, isUser).addWindowListener(new WindowListener() {
						public void windowOpened(WindowEvent e) {}
						public void windowIconified(WindowEvent e) {}
						public void windowDeiconified(WindowEvent e) {}
						public void windowDeactivated(WindowEvent e) {
							AdminUserTable.setModel(new DefaultTableModel(UserTable.getUserTableData(), UserTable.getShowheaders()));
							user = UserTable.updateUser(user);
						}
						public void windowClosing(WindowEvent e) {}
						public void windowActivated(WindowEvent e) {}
						public void windowClosed(WindowEvent e) {
							AdminUserTable.setModel(new DefaultTableModel(UserTable.getUserTableData(), UserTable.getShowheaders()));
						}
					});;
				}
			}
		});
		
		
		
		
		AdminPanel.add(AdminMusicPane);
		AdminPanel.add(AdminUserPane);
		ShowPanel.add("Admin", AdminPanel);
	}
	
	private void AddUserButtonCard() {
		//User =>  My Page / My Favorite List / Logout
		NavUserPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btnMyPage = new JButton("마이페이지");
		btnShowMusicinfo = new JButton("음악정보 보기");
		btnLogOut = new JButton("로그아웃");
		
		btnMyPage.setSize(150, 30);
		btnMyPage.setPreferredSize(new Dimension(150, 30));
		btnShowMusicinfo.setSize(150, 30);
		btnShowMusicinfo.setPreferredSize(new Dimension(150, 30));
		btnLogOut.setSize(150, 30);
		btnLogOut.setPreferredSize(new Dimension(150, 30));
		NavUserPanel.setBackground(Color.decode("#333333"));
		
		btnMyPage.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnShowMusicinfo.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnLogOut.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		
		btnMyPage.setBackground(Color.decode("#d7d2cb"));
		btnShowMusicinfo.setBackground(Color.decode("#d7d2cb"));
		btnLogOut.setBackground(Color.decode("#d7d2cb"));
		
		NavUserPanel.add(btnMyPage);
		NavUserPanel.add(btnShowMusicinfo);
		NavUserPanel.add(btnLogOut);
		
//		setMusicPanel
//		https://stackoverflow.com/questions/1097366/java-swing-revalidate-vs-repaint
		btnShowMusicinfo.addActionListener(new ActionListener() {
			Desktop desktop = Desktop.getDesktop();
			public void actionPerformed(ActionEvent e) {
				try{
					URI LoaCon = new URI("https://www.youtube.com/watch?v=10okVreqe0s&t=17111s");
					desktop.browse(LoaCon);
				}catch (Exception e1){
					e1.printStackTrace();
				}

			}
		});
		
		btnMyPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Userset currentUser = user;
				UserFrame uf = new UserFrame(currentUser, isUser);
				uf.addWindowListener(new WindowListener() {
					public void windowOpened(WindowEvent e) {}
					public void windowIconified(WindowEvent e) {}
					public void windowDeiconified(WindowEvent e) {}
					public void windowDeactivated(WindowEvent e) {
						user = uf.getUser();
						AdminUserTable.setModel(new DefaultTableModel(UserTable.getUserTableData(), UserTable.getShowheaders()));
						UserMusicTable.setModel(new DefaultTableModel(MusicTable.showUserTable(), MusicTable.getUserheader()));
					}
					public void windowClosing(WindowEvent e) {}
					public void windowActivated(WindowEvent e) {}
					public void windowClosed(WindowEvent e) {
						user = uf.getUser();
						AdminUserTable.setModel(new DefaultTableModel(UserTable.getUserTableData(), UserTable.getShowheaders()));
						UserMusicTable.setModel(new DefaultTableModel(MusicTable.showUserTable(), MusicTable.getUserheader()));
					}
				});
			}
		});
		
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginScreen();
				dispose();
			}
		});

		BCPanel.add("User", NavUserPanel);
	}
	
	private void AddAdminButtonCard() {
		// Administer => 곡추가/곡수정/곡삭제/회원관리
		NavAdminPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btnAddMusic = new JButton("곡 추가");
		btnEdit = new JButton("곡 수정");
		btnDelMusic = new JButton("곡 삭제");
		btnRefresh = new JButton("새로고침");
		
		btnAddMusic.setSize(150, 30);
		btnAddMusic.setPreferredSize(new Dimension(150, 30));
		btnEdit.setSize(150, 30);
		btnEdit.setPreferredSize(new Dimension(150, 30));
		btnDelMusic.setSize(150, 30);
		btnDelMusic.setPreferredSize(new Dimension(150, 30));
		btnRefresh.setSize(150, 30);
		btnRefresh.setPreferredSize(new Dimension(150, 30));
		NavAdminPanel.setBackground(Color.decode("#333333"));
		
		btnAddMusic.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnEdit.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnDelMusic.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnRefresh.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		
		
		btnAddMusic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MusicFrame().addWindowListener(new WindowListener() {
					public void windowOpened(WindowEvent e) {}
					public void windowIconified(WindowEvent e) {}
					public void windowDeiconified(WindowEvent e) {}
					public void windowDeactivated(WindowEvent e) {}
					public void windowClosing(WindowEvent e) {}
					public void windowActivated(WindowEvent e) {}
					public void windowClosed(WindowEvent e) {
						AdminMusicTable.setModel(new DefaultTableModel(MusicTable.showAdminTable(), MusicTable.getShowheader()));
						UserMusicTable.setModel(new DefaultTableModel(MusicTable.showUserTable(), MusicTable.getUserheader()));
					}
				});;
			}
		});
		
		
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(AdminselectedTitle == null) {
					JOptionPane.showMessageDialog(null, "음악을 선택하세요.", "Warning", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Music selectedMusic = MusicTable.getMusic(AdminselectedTitle);
				new MusicFrame(selectedMusic).addWindowListener(new WindowListener() {
					public void windowOpened(WindowEvent e) {}
					public void windowIconified(WindowEvent e) {}
					public void windowDeiconified(WindowEvent e) {}
					public void windowDeactivated(WindowEvent e) {
						AdminMusicTable.setModel(new DefaultTableModel(MusicTable.showAdminTable(), MusicTable.getShowheader()));
						UserMusicTable.setModel(new DefaultTableModel(MusicTable.showUserTable(), MusicTable.getUserheader()));
					}
					public void windowClosing(WindowEvent e) {}
					public void windowActivated(WindowEvent e) {}
					public void windowClosed(WindowEvent e) {
						AdminMusicTable.setModel(new DefaultTableModel(MusicTable.showAdminTable(), MusicTable.getShowheader()));
						UserMusicTable.setModel(new DefaultTableModel(MusicTable.showUserTable(), MusicTable.getUserheader()));
					}
				});
			}
		});
		
		btnDelMusic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((AdminselectedTitle == null)) {
					JOptionPane.showMessageDialog(null, "음악을 선택하세요.", "Warning", JOptionPane.WARNING_MESSAGE);
					return;
				}
				MusicTable.deleteMusic(AdminMusicTable.getSelectedRow());
				AdminMusicTable.setModel(new DefaultTableModel(MusicTable.showAdminTable(), MusicTable.getShowheader()));
				UserMusicTable.setModel(new DefaultTableModel(MusicTable.showUserTable(), MusicTable.getUserheader()));
			}
		});
		
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminMusicTable.setModel(new DefaultTableModel(MusicTable.showAdminTable(), MusicTable.getShowheader()));
				AdminUserTable.setModel(new DefaultTableModel(UserTable.getUserTableData(), UserTable.getShowheaders()));
				UserMusicTable.setModel(new DefaultTableModel(MusicTable.showUserTable(), MusicTable.getUserheader()));
			}
		});
		
		btnAddMusic.setBackground(Color.decode("#d7d2cb"));
		btnRefresh.setBackground(Color.decode("#d7d2cb"));
		btnEdit.setBackground(Color.decode("#d7d2cb"));
		btnDelMusic.setBackground(Color.decode("#d7d2cb"));
		btnRefresh.setBackground(Color.decode("#d7d2cb"));
		
		NavAdminPanel.add(btnAddMusic);
		NavAdminPanel.add(btnEdit);
		NavAdminPanel.add(btnDelMusic);
		NavAdminPanel.add(btnRefresh);

		BCPanel.add("Admin", NavAdminPanel);
	}
	
	private class MyMusicInfoListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(UserselectedTitle == null) {
				JOptionPane.showMessageDialog(null, "음악을 선택하세요.", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			Music music = MusicTable.getMusic(UserselectedTitle);
			musicPanel.setVisible(false);;
			musicPanel = new MusicPanel(music);
			musicPanel.setLocation(10, 10);
			musicPanel.setBackground(Color.decode("#333333"));
			UserPanel.add(musicPanel);
		}
	}

}

























