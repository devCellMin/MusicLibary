package MusicSetting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class setIntroduceText extends JFrame{
	public setIntroduceText () {
		run();
	}

	private void run() {
		setTitle("곡 정보 & 가사 입력");
		
		JPanel title = new JPanel();
		JPanel contents = new JPanel();
		JPanel btn = new JPanel();
		
		JLabel titleName = new JLabel("곡 정보 & 가사 입력", JLabel.CENTER);
		titleName.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		
		title.add(titleName);
		
		JTextArea musicInfo = new JTextArea(30, 35);
		musicInfo.setText("이 영역에 곡 정보를 입력해 주세요.");
		
		JTextArea lyricInfo = new JTextArea(30, 35);
		lyricInfo.setText("이 영역에 가사를 입력해 주세요");

		contents.add(musicInfo);
		contents.add(lyricInfo);

		JButton submit = new JButton("저장");
		JButton reset = new JButton("리셋");
		JButton cancel = new JButton("취소");
		
		btn.add(submit);
		btn.add(reset);
		btn.add(cancel);
		
		add(title, BorderLayout.NORTH);
		add(contents, BorderLayout.CENTER);
		add(btn, BorderLayout.SOUTH);
		
		setBounds(600, 200, 800, 500);
		setResizable(false);
		setVisible(true);
		
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				musicInfo.setText("이 영역에 곡 정보를 입력해 주세요.");
				lyricInfo.setText("이 영역에 가사를 입력해 주세요");
				
				JOptionPane.showMessageDialog(null, "리셋 되었습니다!");
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
	}
}
