package MusicSetting;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MusicFrame extends JFrame {
//	public static void main(String[] args) {
//		new MusicFrame();
//	}
	Music music; Boolean tf = true;
	MusicTableData MTD = new MusicTableData();
	// Revise Music
	public MusicFrame(Music music) throws HeadlessException {
		super();
		this.music = music;
		new MainMusicFrame(music);
	}
	
	// Add Music
	public MusicFrame() {
		this.music = new Music();
		new MainMusicFrame();
	}

	// Return Music
	public Music getMusic() {
		return music;
	}

	private class MainMusicFrame{
		private MainMusicFrame() {
			run();
		}
		
		private MainMusicFrame(Music music) {
			run(music);
		}
		
		private void run() {
			setLayout(new BorderLayout());
			
			showNorth();
			showCenter();
			showSouth();
			
			btnIntroduce.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new setIntroduceText();
					
				}
			});
			
			btnLyric.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new setLyricText(TfMusicTitle.getText());
					
				}
			});
			
			btnMusicSubmit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					music.setTitle(TfMusicTitle.getText());
					music.setArtist(TfMusicArtist.getText());
					music.setAlbumName(TfMusicAlbumName.getText());
					music.setReldate(TfMusicReldate.getText());
					music.setComposer(TfMusicComposer.getText());
					music.setLyricist(TfMusicLyricist.getText());
					MTD.save(music);
					JOptionPane.showMessageDialog(null, "추가 완료");
					dispose();
				}
			});
			
			
			
			setTitle("Add Music");
			setBounds(700, 300, 400, 600);
			setVisible(true);
			setResizable(false);
		}
		
		private void run(Music music) {
			setLayout(new BorderLayout());
			
			showNorth(music.getAlbumName());
			showCenter();
			showSouth();
			
			setInformation(music);
			
			btnIntroduce.setText("소개 수정");
			btnIntroduce.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
				}
			});
			
			btnLyric.setText("가사 수정");
			btnLyric.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new setLyricText(TfMusicTitle.getText(), music);
				}
			});
			
			btnMusicSubmit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					music.setTitle(TfMusicTitle.getText());
					music.setArtist(TfMusicArtist.getText());
					music.setAlbumName(TfMusicAlbumName.getText());
					music.setReldate(TfMusicReldate.getText());
					music.setComposer(TfMusicComposer.getText());
					music.setLyricist(TfMusicLyricist.getText());
					MTD.saveAll(music);
					JOptionPane.showMessageDialog(null, "수정 완료");
					dispose();
				}
			});
			
			
			
			setTitle("Edit Music");
			setBounds(700, 300, 400, 600);
			setVisible(true);
			setResizable(false);
		}
		
		// New Music North
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
		
		// Edit Music North
		private void showNorth(String MusicAlbum) {
			JPanel NorthPanel = new JPanel();
			NorthPanel.setBackground(Color.decode("#333333"));
			
			JLabel LblLogoImg = new JLabel();
			
			Image LogoImg = new ImageIcon("database\\AlbumImg\\"+MusicAlbum+".jpg").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			LblLogoImg.setIcon(new ImageIcon(LogoImg));
			LblLogoImg.setHorizontalAlignment(JLabel.CENTER);
			LblLogoImg.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));
			LblLogoImg.setPreferredSize(new Dimension(150, 170));


			NorthPanel.add(LblLogoImg);
			add(NorthPanel, BorderLayout.NORTH);
		}
		
		JTextField TfMusicTitle, TfMusicArtist, TfMusicAlbumName;
		JTextField TfMusicReldate, TfMusicComposer, TfMusicLyricist;
		JButton btnMusicSubmit, btnLyric, btnIntroduce;
		private void showCenter() {
			JPanel CenterPanel = new JPanel(new GridLayout(6, 0));
			CenterPanel.setBackground(Color.decode("#333333"));
			
			// Music Title
			JPanel MusicTitlePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			JLabel LblMusicTitle = new JLabel("음악 제목 : ");
			TfMusicTitle = new JTextField(15);
			LblMusicTitle.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			TfMusicTitle.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			MusicTitlePanel.setBackground(Color.decode("#333333"));
			LblMusicTitle.setForeground(Color.decode("#cccccc"));

			// Add Title Part
			MusicTitlePanel.add(LblMusicTitle);
			MusicTitlePanel.add(TfMusicTitle);
			CenterPanel.add(MusicTitlePanel);
			
			
			TfMusicTitle.addKeyListener(new KeyListener() {
				public void keyTyped(KeyEvent e) {
					
				}
				public void keyReleased(KeyEvent e) {
					if(!TfMusicTitle.getText().equals(music.getTitle())) {
						File prev = new File("database\\Lyric\\"+music.getTitle()+".txt");
						File rename = new File("database\\Lyric\\"+TfMusicTitle.getText()+".txt");
						prev.renameTo(rename);
					}
				}
				public void keyPressed(KeyEvent e) {}
			});

			

			// Music artist
			JPanel MusicArtistPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			JLabel LblMusicArtist = new JLabel("가수 : ");
			TfMusicArtist = new JTextField(15);
			LblMusicArtist.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			TfMusicArtist.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			MusicArtistPanel.setBackground(Color.decode("#333333"));
			LblMusicArtist.setForeground(Color.decode("#cccccc"));

			// Add Artist Part
			MusicArtistPanel.add(LblMusicArtist);
			MusicArtistPanel.add(TfMusicArtist);
			CenterPanel.add(MusicArtistPanel);

			//albumName; // 앨범명
			JPanel MusicAlbumNamePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			JLabel LblMusicAlbumName = new JLabel("앨범명 : ");
			TfMusicAlbumName = new JTextField(15);
			LblMusicAlbumName.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			TfMusicAlbumName.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			MusicAlbumNamePanel.setBackground(Color.decode("#333333"));
			LblMusicAlbumName.setForeground(Color.decode("#cccccc"));

			MusicAlbumNamePanel.add(LblMusicAlbumName);
			MusicAlbumNamePanel.add(TfMusicAlbumName);
			CenterPanel.add(MusicAlbumNamePanel);
			
			// reldate; // 발매일
			JPanel MusicReldatePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			JLabel LblMusicReldate = new JLabel("발매일 : ");
			TfMusicReldate = new JTextField(15);
			LblMusicReldate.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			TfMusicReldate.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			MusicReldatePanel.setBackground(Color.decode("#333333"));
			LblMusicReldate.setForeground(Color.decode("#cccccc"));

			MusicReldatePanel.add(LblMusicReldate);
			MusicReldatePanel.add(TfMusicReldate);
			CenterPanel.add(MusicReldatePanel);
			
			// composer; // 작곡가
			JPanel MusicComposerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			JLabel LblMusicComposer = new JLabel("작곡가 : ");
			TfMusicComposer = new JTextField(15);
			LblMusicComposer.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			TfMusicComposer.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			MusicComposerPanel.setBackground(Color.decode("#333333"));
			LblMusicComposer.setForeground(Color.decode("#cccccc"));

			MusicComposerPanel.add(LblMusicComposer);
			MusicComposerPanel.add(TfMusicComposer);
			CenterPanel.add(MusicComposerPanel);
			
			// lyricist; // 작사가
			JPanel MusicLyricistPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			JLabel LblMusicLyricist = new JLabel("작사가 : ");
			TfMusicLyricist = new JTextField(15);
			LblMusicLyricist.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			TfMusicLyricist.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			LblMusicLyricist.setForeground(Color.decode("#cccccc"));
			MusicLyricistPanel.setBackground(Color.decode("#333333"));

			MusicLyricistPanel.add(LblMusicLyricist);
			MusicLyricistPanel.add(TfMusicLyricist);
			CenterPanel.add(MusicLyricistPanel);
			
			CenterPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 50));
			
			
			add(CenterPanel, BorderLayout.CENTER);
			
		}
		
		private void showSouth() {
			JPanel SouthPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			SouthPanel.setBackground(Color.decode("#333333"));
			
			btnIntroduce = new JButton("소개 추가");
			btnIntroduce.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			btnIntroduce.setBackground(Color.decode("#d7d2cb"));
			
			btnLyric = new JButton("가사 추가");
			btnLyric.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			btnLyric.setBackground(Color.decode("#d7d2cb"));
			
			btnMusicSubmit = new JButton(" 확 인 "); //제출버튼
			btnMusicSubmit.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			btnMusicSubmit.setBackground(Color.decode("#d7d2cb"));
			
			SouthPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 40, 0));
			SouthPanel.add(btnIntroduce);
			SouthPanel.add(btnLyric);
			SouthPanel.add(btnMusicSubmit);
			add(SouthPanel, BorderLayout.SOUTH);
		}
		
		private void setInformation(Music music) {
			TfMusicTitle.setText(music.getTitle());
			TfMusicArtist.setText(music.getArtist());
			TfMusicAlbumName.setText(music.getAlbumName());
			TfMusicReldate.setText(music.getReldate());
			TfMusicComposer.setText(music.getComposer());
			TfMusicLyricist.setText(music.getLyricist());
		}
	}
}

















