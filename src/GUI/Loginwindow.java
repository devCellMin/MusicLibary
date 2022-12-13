//package GUI;
//
//import java.awt.BorderLayout;
//
//import java.awt.Color;
//import java.awt.FlowLayout;
//import java.awt.Font;
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.WindowEvent;
//import java.awt.event.WindowListener;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.swing.BorderFactory;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JPasswordField;
//import javax.swing.JTextField;
//
//import UserSetting.*;
//
//public class Loginwindow extends JFrame{
//	private JButton BtnLogin, BtnJoin;
//	private JTextField IDText; private JPasswordField PWDText;
//	private JPanel FramePanel;
//	private UserTableData UTD = new UserTableData();
//	public Loginwindow() {
//		run();
//	}
//
//	private void run() {
//		setLayout(null);
//		FramePanel = new JPanel(new BorderLayout(10, 10));
//		FramePanel.setBounds(0, 0, 480, 360);
//		
//		showNorth();
//		showCenter();
//		showSouth();
//		
//		FramePanel.setBackground(new Color(255, 242, 230));
//		add(FramePanel);
//		setTitle("Music Library");
//		setSize(480, 360);
//		setLocation(700, 300);
//		setResizable(false);
//		setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	}
//
//	private void showNorth() {
//		JPanel panel = new JPanel(new GridLayout(2, 0));
//		JLabel TitleLabel = new JLabel("MusicLibrary");
//		JLabel bywhoLabel = new JLabel("by Hysung KBS Team.");
//		
//		TitleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
//		TitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
//		bywhoLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
//		panel.setBackground(new Color(255, 242, 230));
//		bywhoLabel.setBackground(new Color(255, 242, 230));
//		
//		// 글자를 중간에 위치하도록 설정
//		TitleLabel.setHorizontalAlignment(JLabel.CENTER);
//		bywhoLabel.setHorizontalAlignment(JLabel.CENTER);
//		
//		//큰 Panel에 Title과 bywho Label을 삽입
//		panel.add(TitleLabel);
//		panel.add(bywhoLabel);
//		FramePanel.add(panel, BorderLayout.NORTH);
//	}
//	
//	private void showCenter() {
//		// ID와 PWD를 입력받은 TextFelid와 Label, Button들을 삽입
//		JPanel panel = new JPanel(new GridLayout(3, 0));
//		JPanel IDPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//		JPanel PWDPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//		JPanel BtnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//		JLabel IDLabel = new JLabel("ID : ");
//		JLabel PWDLabel = new JLabel("Password : ");
//		
//		BtnLogin = new JButton("LogIn");
//		BtnJoin= new JButton("Join");
//		IDText = new JTextField(15);
//		PWDText = new JPasswordField(15);
//		
//		//Font 설정
//		IDLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
//		IDText.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
//		PWDLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
//		PWDText.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
//		panel.setBackground(new Color(255, 242, 230));
//		IDPanel.setBackground(new Color(255, 242, 230));
//		PWDPanel.setBackground(new Color(255, 242, 230));
//		BtnPanel.setBackground(new Color(255, 242, 230));
//		
//		// Border 설정
//		IDPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));
//		PWDPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));
//		BtnPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 60));
//		
//		// Panel에 Label과 소형 Panel을 삽입
//		IDPanel.add(IDLabel);
//		IDPanel.add(IDText);
//		PWDPanel.add(PWDLabel);
//		PWDPanel.add(PWDText);
//		BtnPanel.add(BtnLogin);
//		BtnPanel.add(BtnJoin);
//		
//		panel.add(IDPanel);
//		panel.add(PWDPanel);
//		panel.add(BtnPanel);
//		FramePanel.add(panel, BorderLayout.CENTER);
//		
//		//Buttons ActionListener Method
//		BtnLogin.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				List<String> InsertedData = new ArrayList<String>();
//				InsertedData.add(IDText.getText());
//				InsertedData.add(String.valueOf(PWDText.getPassword()));
//				Userset user = UTD.UserLogin(InsertedData);
//				if(user == null) {
//					return;
//				}else {
//					if(user.getUserType().equals("User")) {
//						new MainGUI(user, true);
//						dispose();
//					}else {
//						new MainGUI(user, false);
//						dispose();
//					}
//				}
//			}
//		});
//		BtnJoin.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				new UserFrame().addWindowListener(new WindowListener() {
//					public void windowOpened(WindowEvent e) {}
//					public void windowIconified(WindowEvent e) {}
//					public void windowDeiconified(WindowEvent e) {}
//					public void windowActivated(WindowEvent e) {}
//					public void windowClosing(WindowEvent e) {}
//					public void windowDeactivated(WindowEvent e) {
//						UTD.refresh();
//					}
//					public void windowClosed(WindowEvent e) {
//						UTD.refresh();
//					}
//				});;
//			}
//		});
//		
//		
//	}
//	
//	private void showSouth() {	
//		JPanel panel = new JPanel();
//		panel.setBackground(new Color(255, 242, 230));
//		panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
//		FramePanel.add(panel, BorderLayout.SOUTH);
//	}
//	
//}