package LoginGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;

import UserSetting.UserTableData;
import UserSetting.Userset;

public class JoinScreen extends JFrame {

	String choice = null;
	UserTableData UTD = new UserTableData();
	Userset user;
	
	public JoinScreen() {
		
		setTitle("회원가입 화면");
		
		JPanel titlepanel = new JPanel(new GridLayout(2,0));
		JLabel title = new JLabel("회원가입", JLabel.CENTER);
		title.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		title.setForeground(Color.decode("#cccccc"));
		titlepanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
		
		
		JButton join = new JButton("회원가입");
		JButton cancel = new JButton("취소");
		join.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		cancel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		
		
		JTextField id = new JTextField(15);
		JPasswordField pwd = new JPasswordField(15);
		JPasswordField pwdCheck = new JPasswordField(15);
		JTextField name = new JTextField(15);
		JTextField age = new JTextField(15);
		ButtonGroup gender = new ButtonGroup();
		JTextField phone = new JTextField(15);
		JTextField adress = new JTextField(15);
		
		JPanel idPanel = new JPanel();
		idPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel idJLabel = new JLabel("아이디 : ");
		idPanel.add(idJLabel);
		idPanel.add(id);
		idJLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		id.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		idJLabel.setForeground(Color.decode("#cccccc"));
		
		JPanel pwdPanel = new JPanel();
		pwdPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel pwdlJLabel = new JLabel("비밀번호 : ");
		pwdPanel.add(pwdlJLabel);
		pwdPanel.add(pwd);
		pwdlJLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		pwd.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		pwdlJLabel.setForeground(Color.decode("#cccccc"));
		
		JPanel pwdCheckPanel = new JPanel();
		pwdCheckPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel pwdCJLabel = new JLabel("비밀번호 확인: ");
		pwdCheckPanel.add(pwdCJLabel);
		pwdCheckPanel.add(pwdCheck);
		pwdCJLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		pwdCheck.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		pwdCJLabel.setForeground(Color.decode("#cccccc"));
		
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel nameLabel = new JLabel("이름 : ");
		namePanel.add(nameLabel);
		namePanel.add(name);
		nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		name.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		nameLabel.setForeground(Color.decode("#cccccc"));
		
		JPanel agePanel = new JPanel();
		agePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel ageJLabel = new JLabel("나이 : "); 
		agePanel.add(ageJLabel);
		agePanel.add(age);
		ageJLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		age.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		ageJLabel.setForeground(Color.decode("#cccccc"));
		
		JPanel genderPanel = new JPanel();
		genderPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel genderJLabel = new JLabel("성별 : "); 
		genderPanel.add(genderJLabel);
		genderJLabel.setForeground(Color.decode("#cccccc"));
		
		JRadioButton male = new JRadioButton("  남  ");
		JRadioButton female = new JRadioButton("  여  ");

		
		gender.add(male); // ButtonGroup gender
		gender.add(female);
		genderPanel.add(male);
		genderPanel.add(new JLabel("     "));
		genderPanel.add(female);
		genderJLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		male.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		female.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		male.setBackground(Color.decode("#333333"));
		female.setBackground(Color.decode("#333333"));
		male.setForeground(Color.decode("#cccccc"));
		female.setForeground(Color.decode("#cccccc"));
		
		
		JPanel phonePanel = new JPanel();
		phonePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel phoneJLabel = new JLabel("연락처 : "); 
		phonePanel.add(phoneJLabel);
		phonePanel.add(phone);
		phoneJLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		phone.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		phoneJLabel.setForeground(Color.decode("#cccccc"));
		
		JPanel adressPanel = new JPanel();
		adressPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel addJLabel = new JLabel("거주지 : ");
		adressPanel.add(addJLabel);
		adressPanel.add(adress);
		addJLabel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		adress.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		addJLabel.setForeground(Color.decode("#cccccc"));
		
		JPanel logo = new JPanel();
		JLabel label = new JLabel();
		Image logo2 = new ImageIcon("images\\gui\\LogoAdd.png").getImage().getScaledInstance(180, 60, Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(logo2));
		logo.add(label);
		
		
		
		titlepanel.add(logo);
		titlepanel.add(title);
		logo.setBackground(Color.decode("#333333"));
		titlepanel.setBackground(Color.decode("#333333"));
		
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(8, 0));
		formPanel.add(idPanel);
		formPanel.add(pwdPanel);
		formPanel.add(pwdCheckPanel);
		formPanel.add(namePanel);
		formPanel.add(agePanel);
		formPanel.add(genderPanel);
		formPanel.add(phonePanel);
		formPanel.add(adressPanel);
		genderPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 43));
		formPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));
		formPanel.setBackground(Color.decode("#333333"));
		idPanel.setBackground(Color.decode("#333333"));
		pwdPanel.setBackground(Color.decode("#333333"));
		pwdCheckPanel.setBackground(Color.decode("#333333"));
		namePanel.setBackground(Color.decode("#333333"));
		agePanel.setBackground(Color.decode("#333333"));
		genderPanel.setBackground(Color.decode("#333333"));
		phonePanel.setBackground(Color.decode("#333333"));
		adressPanel.setBackground(Color.decode("#333333"));
		setBackground(Color.decode("#333333"));
		
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new FlowLayout());
		contentPanel.add(formPanel);
		contentPanel.setBackground(Color.decode("#333333"));
		
		JPanel panel = new JPanel();

		panel.add(join);
		panel.add(cancel);
		panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
		panel.setBackground(Color.decode("#333333"));
		
		add(titlepanel, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
		
		
		setBounds(700, 200, 400, 600);
		setVisible(true);
		
		join.setBackground(Color.decode("#d7d2cb"));
		cancel.setBackground(Color.decode("#d7d2cb"));
		
		
		join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "아이디를 입력하세요.", "Warning", JOptionPane.WARNING_MESSAGE);
					return;
				}else if(name.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "이름을 입력하세요.", "Warning", JOptionPane.WARNING_MESSAGE);
					return;
				}else if(String.valueOf(pwd.getPassword()).equals("")) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요.", "Warning", JOptionPane.WARNING_MESSAGE);
					return;
				}else if(age.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "나이를 입력하세요.", "Warning", JOptionPane.WARNING_MESSAGE);
					return;
				}else if((!male.isSelected())&&(!female.isSelected())) {
					JOptionPane.showMessageDialog(null, "성별을 선택하세요.", "Warning", JOptionPane.WARNING_MESSAGE);
					return;
				}else if(phone.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "전화번호를 입력하세요.", "Warning", JOptionPane.WARNING_MESSAGE);
					return;
				}else if(adress.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "주소를 입력하세요.", "Warning", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(UTD.Sameid(id.getText())) {
					JOptionPane.showMessageDialog(null, "중복된 아이디가 존재합니다.\n다른 아이디를 입력하여주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(!String.valueOf(pwd.getPassword()).equals(String.valueOf(pwdCheck.getPassword()))){
					JOptionPane.showMessageDialog(null, "비밀번호와 비밀번호확인이 같지 않습니다.\n비밀번호를 확인하여주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				user = new Userset();
				user.setUserId(id.getText());
				user.setUserName(name.getText());
				user.setUserPWD(String.valueOf(pwd.getPassword()));
				user.setUserAge(Integer.parseInt(age.getText()));
				if(male.isSelected()) {
					user.setUserGender("남자");
				} else {
					user.setUserGender("여자");
				}
				user.setUserPhoneNum(phone.getText());
				user.setUserAddr(adress.getText());
				user.setUserType("User");
				UTD.save(user);
				JOptionPane.showMessageDialog(null, "회원가입 완료~!");
				dispose();

			}
		});
		
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();	
			}
		});
	}
}



