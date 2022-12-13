package LoginGUI;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import GUI.MainGUI;
import UserSetting.UserTableData;
import UserSetting.Userset;

public class LoginScreen extends JFrame {
	UserTableData UTD = new UserTableData();
	public LoginScreen() {
		
		setTitle("Login");
		
		JPanel title1 = new JPanel();
		JPanel title2 = new JPanel();		
		JLabel label = new JLabel();
		JLabel label2 = new JLabel();
		
		Image logo1 = new ImageIcon("images\\gui\\logo.png").getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(logo1));
		
		Image logo2 = new ImageIcon("images\\gui\\LogoAdd.png").getImage().getScaledInstance(180, 60, Image.SCALE_SMOOTH);
		label2.setIcon(new ImageIcon(logo2));
		
		title1.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
		
		title1.add(label);
		title2.add(label2);
		
		add(title1, BorderLayout.NORTH);
		add(title2, BorderLayout.CENTER);
		title1.setBackground(Color.decode("#333333"));
		title2.setBackground(Color.decode("#333333"));
		setBackground(Color.decode("#333333"));

		
		JPanel jp1 = new JPanel();
		jp1.setLayout(new GridLayout(3, 0));
		
		JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel jlb1 = new JLabel("아 이 디  : ", JLabel.CENTER);
		JTextField jtf1 = new JTextField(10);
		jlb1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		jtf1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		jlb1.setForeground(Color.decode("#cccccc"));
		
		idPanel.add(jlb1);
		idPanel.add(jtf1);
		
		jp1.add(idPanel);
		jp1.setBackground(Color.decode("#333333"));
		idPanel.setBackground(Color.decode("#333333"));
		
		JPanel pwdPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel jlb2 = new JLabel("비밀번호 : ", JLabel.CENTER);
		JPasswordField jtf2 = new JPasswordField(10);
		
		jlb2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		jtf2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		jlb2.setForeground(Color.decode("#cccccc"));
		
		pwdPanel.add(jlb2);
		pwdPanel.add(jtf2);
		
		jp1.add(pwdPanel);
		pwdPanel.setBackground(Color.decode("#333333"));

		
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton jLogin = new JButton("로 그 인");
		JLabel lalSpace = new JLabel("   ");
		JButton join = new JButton("회원가입");

		jLogin.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		join.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		btnPanel.add(jLogin); 
		btnPanel.add(lalSpace);
		btnPanel.add(join);
		btnPanel.setBackground(Color.decode("#333333"));
		jLogin.setBackground(Color.decode("#d7d2cb"));
		join.setBackground(Color.decode("#d7d2cb"));
		
		jp1.add(btnPanel);
		
		btnPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10));
		jp1.setBorder(BorderFactory.createEmptyBorder(10, 0, 50, 70));
			
		add(jp1, BorderLayout.SOUTH);
		
		setBounds(700, 200, 400, 600);
		setResizable(false);  
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
		jLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> InsertedData = new ArrayList<String>();
				InsertedData.add(jtf1.getText());
				InsertedData.add(String.valueOf(jtf2.getPassword()));
				Userset user = UTD.UserLogin(InsertedData);
				if(user == null) {
					return;
				}else {
					if(user.getUserType().equals("User")) {
						new MainGUI(user, true);
						dispose();
					}else {
						new MainGUI(user, false);
						dispose();
					}
				}
			}
		});
		
		
		join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JoinScreen().addWindowListener(new WindowListener() {
					public void windowOpened(WindowEvent e) {}
					public void windowIconified(WindowEvent e) {}
					public void windowDeiconified(WindowEvent e) {}
					public void windowActivated(WindowEvent e) {}
					public void windowClosing(WindowEvent e) {}
					public void windowDeactivated(WindowEvent e) {
						UTD.refresh();
					}
					public void windowClosed(WindowEvent e) {
						UTD.refresh();
					}
				});
				
				
			}
		});

		
	}
}





























