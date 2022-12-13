package UserSetting;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.JobAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class UserFrame extends JFrame {
//	public static void main(String[] args) {
//		new UserFrame();
//	}
	Userset user;boolean isUser;
	UserTableData UTD = new UserTableData();

	// UserManegment
	public UserFrame(Userset user, boolean isUser) throws HeadlessException {
		super();
		this.user = user;
		this.isUser = isUser;
		new MainUserFrame(user);
	}
	
	// Join User
	public UserFrame() {
		this.user = new Userset();
		new MainUserFrame();
	}

	// Return Music
	public Userset getUser() {
		return this.user;
	}

	private class MainUserFrame{
		private MainUserFrame() {
			run();
		}
		private MainUserFrame(Userset user) {
			run(user);
		}
		
		// Run Method to change User Information
		private void run(Userset user) {
			setLayout(new BorderLayout());
			
			showNorth();
			showCenter();
			showSouth();
			
			insertinfo(user);
			fieldsettings();
			
			if(isUser == true) {
				btnUserDelete.setVisible(false);
			}
			
			
			btnUserSubmit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					user.setUserId(TfUserID.getText());
					user.setUserName(TfUserName.getText());
					user.setUserPWD(String.valueOf(PfUserPWD.getPassword()));
					user.setUserAge(Integer.parseInt(TfUserAge.getText()));
					if(RadioGender_M.isSelected()) {
						user.setUserGender("남자");
					} else {
						user.setUserGender("여자");
					}
					user.setUserPhoneNum(TfUserPhoneNum.getText());
					user.setUserAddr(TfUserAddr.getText());
					if(RadioUser.isSelected()) {
						user.setUserType("User");
					} else {
						user.setUserType("Administer");
					} 
					UTD.saveAll(user);
					dispose();
				}
			});
			
			btnUserDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(null, "정보를 삭제하시겠습니까?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.YES_OPTION) {
						UTD.deleteUserSet(user);
						JOptionPane.showMessageDialog(null, "삭제 완료하였습니다.");
						dispose();
					}
				}
			});
			
			
			setTitle("Edit User Information");
			setBounds(700, 300, 400, 600);
			setVisible(true);
			setResizable(false);
		}

		// RunMethod for New User
		private void run() {
			setLayout(new BorderLayout());
			
			showNorth();
			showCenter();
			showSouth();
			
			// Don't need to show User type and BtnDelete for new User
			UserTypePanel.setVisible(false);
			btnUserDelete.setVisible(false);
			
			btnUserSubmit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(TfUserID.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "아이디를 입력하세요.", "Warning", JOptionPane.WARNING_MESSAGE);
						return;
					}else if(TfUserName.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "이름을 입력하세요.", "Warning", JOptionPane.WARNING_MESSAGE);
						return;
					}else if(String.valueOf(PfUserPWD.getPassword()).equals("")) {
						JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요.", "Warning", JOptionPane.WARNING_MESSAGE);
						return;
					}else if(TfUserAge.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "나이를 입력하세요.", "Warning", JOptionPane.WARNING_MESSAGE);
						return;
					}else if((!RadioGender_M.isSelected())&&(!RadioGender_FM.isSelected())) {
						JOptionPane.showMessageDialog(null, "성별을 선택하세요.", "Warning", JOptionPane.WARNING_MESSAGE);
						return;
					}else if(TfUserPhoneNum.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "전화번호를 입력하세요.", "Warning", JOptionPane.WARNING_MESSAGE);
						return;
					}else if(TfUserAddr.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "주소를 입력하세요.", "Warning", JOptionPane.WARNING_MESSAGE);
						return;
					}

					if(UTD.Sameid(TfUserID.getText())) {
						JOptionPane.showMessageDialog(null, "중복된 아이디가 존재합니다.\n다른 아이디를 입력하여주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
						return;
					}
					user.setUserId(TfUserID.getText());
					user.setUserName(TfUserName.getText());
					user.setUserPWD(String.valueOf(PfUserPWD.getPassword()));
					user.setUserAge(Integer.parseInt(TfUserAge.getText()));
					if(RadioGender_M.isSelected()) {
						user.setUserGender("남자");
					} else {
						user.setUserGender("여자");
					}
					user.setUserPhoneNum(TfUserPhoneNum.getText());
					user.setUserAddr(TfUserAddr.getText());
					user.setUserType("User");
					UTD.save(user);
					JOptionPane.showMessageDialog(null, "회원가입 완료~!");
					dispose();
				}
			});
			
			setTitle("User Registration");
			setBounds(700, 300, 400, 600);
			setVisible(true);
			setResizable(false);
		}

		private void showNorth() {
			JPanel NorthPanel = new JPanel();
			NorthPanel.setBackground(Color.decode("#333333"));
			
			JLabel LblLogoImg = new JLabel();
			
			Image LogoImg = new ImageIcon("images\\gui\\logo.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			LblLogoImg.setIcon(new ImageIcon(LogoImg));
			LblLogoImg.setHorizontalAlignment(JLabel.CENTER);
			LblLogoImg.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));
			LblLogoImg.setPreferredSize(new Dimension(150, 170));


			NorthPanel.add(LblLogoImg);
			add(NorthPanel, BorderLayout.NORTH);
		}
		
		JTextField TfUserID, TfUserName; JPasswordField PfUserPWD;
		JTextField TfUserAge, TfUserPhoneNum, TfUserAddr;
		JRadioButton RadioGender_M, RadioGender_FM, RadioUser, RadioAdmin;
		JButton btnUserSubmit, btnUserDelete; JPanel UserTypePanel;
		private void showCenter() {
			JPanel CenterPanel = new JPanel(new GridLayout(8, 0));
			CenterPanel.setBackground(Color.decode("#333333"));
			
			// UserID
			JPanel UserIDPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			JLabel LblUserID = new JLabel("아이디 : ");
			TfUserID = new JTextField(15);
			LblUserID.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			TfUserID.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			UserIDPanel.setBackground(Color.decode("#333333"));
			LblUserID.setForeground(Color.decode("#cccccc"));

			// Add UserID Part
			UserIDPanel.add(LblUserID);
			UserIDPanel.add(TfUserID);
			CenterPanel.add(UserIDPanel);

			// UserName
			JPanel UserNamePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			JLabel LblUserName = new JLabel("이름 : ");
			TfUserName = new JTextField(15);
			LblUserName.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			TfUserName.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			UserNamePanel.setBackground(Color.decode("#333333"));
			LblUserName.setForeground(Color.decode("#cccccc"));

			// Add UserName Part
			UserNamePanel.add(LblUserName);
			UserNamePanel.add(TfUserName);
			CenterPanel.add(UserNamePanel);

			// UserPWD
			JPanel UserPWDPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			JLabel LblUserPWD = new JLabel("비밀번호 : ");
			PfUserPWD = new JPasswordField(15);
			LblUserPWD.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			PfUserPWD.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			UserPWDPanel.setBackground(Color.decode("#333333"));
			LblUserPWD.setForeground(Color.decode("#cccccc"));

			UserPWDPanel.add(LblUserPWD);
			UserPWDPanel.add(PfUserPWD);
			CenterPanel.add(UserPWDPanel);
			
			// UserAge
			JPanel UserAgePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			JLabel LblUserAge = new JLabel("나이 : ");
			TfUserAge = new JTextField(15);
			LblUserAge.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			TfUserAge.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			UserAgePanel.setBackground(Color.decode("#333333"));
			LblUserAge.setForeground(Color.decode("#cccccc"));

			UserAgePanel.add(LblUserAge);
			UserAgePanel.add(TfUserAge);
			CenterPanel.add(UserAgePanel);
			
			// UserGender
			JPanel UserGenderPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			JLabel LblUserGender = new JLabel("성별 : ");
			RadioGender_M = new JRadioButton("남자      ");
			RadioGender_FM = new JRadioButton("여자              ");
			ButtonGroup GenderRadio = new ButtonGroup();
			GenderRadio.add(RadioGender_M);
			GenderRadio.add(RadioGender_FM);
			LblUserGender.setForeground(Color.decode("#cccccc"));
			
			RadioGender_M.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			RadioGender_FM.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			LblUserGender.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			RadioGender_M.setBackground(Color.decode("#333333"));
			RadioGender_FM.setBackground(Color.decode("#333333"));
			UserGenderPanel.setBackground(Color.decode("#333333"));
			RadioGender_M.setForeground(Color.decode("#cccccc"));
			RadioGender_FM.setForeground(Color.decode("#cccccc"));

			UserGenderPanel.add(LblUserGender);
			UserGenderPanel.add(RadioGender_M);
			UserGenderPanel.add(RadioGender_FM);
			CenterPanel.add(UserGenderPanel);
			
			// UserPhoneNum
			JPanel UserPhoneNumPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			JLabel LblUserPhoneNum = new JLabel("연락처 : ");
			TfUserPhoneNum = new JTextField(15);
			LblUserPhoneNum.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			TfUserPhoneNum.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			UserPhoneNumPanel.setBackground(Color.decode("#333333"));
			LblUserPhoneNum.setForeground(Color.decode("#cccccc"));

			UserPhoneNumPanel.add(LblUserPhoneNum);
			UserPhoneNumPanel.add(TfUserPhoneNum);
			CenterPanel.add(UserPhoneNumPanel);
			
			JPanel UserAddrPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			JLabel LblUserAddr = new JLabel("주소 : ");
			TfUserAddr = new JTextField(15);
			LblUserAddr.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			TfUserAddr.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			UserAddrPanel.setBackground(Color.decode("#333333"));
			LblUserAddr.setForeground(Color.decode("#cccccc"));

			UserAddrPanel.add(LblUserAddr);
			UserAddrPanel.add(TfUserAddr);
			CenterPanel.add(UserAddrPanel);
			
			
			UserTypePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			JLabel LblUserType = new JLabel("계급 : ");
			RadioUser = new JRadioButton("User      ");
			RadioAdmin = new JRadioButton("Administer    ");
			ButtonGroup TypeRadio = new ButtonGroup();
			TypeRadio.add(RadioUser);
			TypeRadio.add(RadioAdmin);
			LblUserType.setForeground(Color.decode("#cccccc"));
			
			RadioUser.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			RadioAdmin.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			LblUserType.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			RadioUser.setBackground(Color.decode("#333333"));
			RadioAdmin.setBackground(Color.decode("#333333"));
			UserTypePanel.setBackground(Color.decode("#333333"));
			RadioUser.setForeground(Color.decode("#cccccc"));
			RadioAdmin.setForeground(Color.decode("#cccccc"));

			UserTypePanel.add(LblUserType);
			UserTypePanel.add(RadioUser);
			UserTypePanel.add(RadioAdmin);
			CenterPanel.add(UserTypePanel);
			
			
			
			CenterPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 50));
			add(CenterPanel, BorderLayout.CENTER);
			
		}
		
		private void showSouth() {
			JPanel SouthPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			SouthPanel.setBackground(Color.decode("#333333"));
			
			btnUserSubmit = new JButton("확인"); //제출버튼
			btnUserDelete = new JButton("삭제"); //삭제버튼
			btnUserSubmit.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			btnUserDelete.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			btnUserSubmit.setBackground(Color.decode("#d7d2cb"));
			btnUserDelete.setBackground(Color.decode("#d7d2cb"));
			
			SouthPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 40, 0));
			SouthPanel.add(btnUserSubmit);
			SouthPanel.add(btnUserDelete);
			add(SouthPanel, BorderLayout.SOUTH);
		}
		
		private void insertinfo(Userset user) {			
			TfUserID.setText(user.getUserId());
			TfUserName.setText(user.getUserName());
			PfUserPWD.setText(user.getUserPWD());
			TfUserAge.setText(String.valueOf(user.getUserAge()));
			if(user.getUserGender().equals("남자")) {
				RadioGender_M.setSelected(true);
			}else {
				RadioGender_FM.setSelected(true);
			}
			TfUserPhoneNum.setText(user.getUserPhoneNum());
			TfUserAddr.setText(user.getUserAddr());
			if(user.getUserType().equals("User")) {
				RadioUser.setSelected(true);
			}else {
				RadioAdmin.setSelected(true);
			}
		}
		
		private void fieldsettings() {
			if(isUser) { //Change UserInformation
				TfUserID.setEditable(false);
				TfUserName.setEditable(false);
				UserTypePanel.setVisible(false);
			} else {
				TfUserID.setEditable(false);
				TfUserName.setEditable(false);
			}
		}
	}
}
















