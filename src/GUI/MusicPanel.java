package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import MusicSetting.Music;

public class MusicPanel extends JPanel {
	
	public MusicPanel(String str) {
		run();
		setMusicPanel();
	}
	
	public MusicPanel(Music music) {
		run();
		setMusicPanel(music);
	}
	
	JPanel MusicinfoPanel, MusicLyricPanel, MusicListPanel;
	JLabel LblMusic, LblMusicName, LblMusicImg, LblMusicArtist;
	JLabel LblMusicAlbum, LblMusicReldate, LblMusicComposer, LblMusicLyricist;
	JTextArea MusicIntroduce, MusicLyric;
	JScrollPane Musicintro, MusicLyrics;
	private void run() {
		setLayout(null);
		
		MusicinfoPanel = new JPanel(null);
		MusicLyricPanel = new JPanel(null);
		LblMusic = new JLabel();
		LblMusicImg = new JLabel();
		LblMusicName = new JLabel();
		LblMusicArtist = new JLabel();
		LblMusicAlbum = new JLabel();
		LblMusicReldate = new JLabel();
		LblMusicComposer = new JLabel();
		LblMusicLyricist = new JLabel();
		MusicIntroduce = new JTextArea();
		MusicLyric = new JTextArea();
		MusicIntroduce.setText("소개\n");
		MusicLyric.setText("가사\n");
		Musicintro = new JScrollPane(MusicIntroduce, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		MusicLyrics = new JScrollPane(MusicLyric, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		MusicinfoPanel.setBounds(10, 10, 400, 610);
		MusicLyricPanel.setBounds(410, 10, 400, 610);
		LblMusic.setBounds(40, 20, 310, 40);
		LblMusicImg.setBounds(40, 70, 120, 120);
		LblMusicName.setBounds(170, 70, 160, 30);
		LblMusicArtist.setBounds(170, 100, 160, 30);
		LblMusicAlbum.setBounds(170, 130, 160, 30);
		LblMusicReldate.setBounds(170, 160, 160, 30);
		LblMusicComposer.setBounds(40, 190, 310, 30);
		LblMusicLyricist.setBounds(40, 220, 310, 30);
		
		
		
		Musicintro.setBounds(40, 250, 340, 325);
		Musicintro.setPreferredSize(new Dimension(340, 325));
		MusicLyrics.setBounds(30, 25, 340, 550);
		MusicLyrics.setPreferredSize(new Dimension(340, 550));
		LblMusic.setHorizontalAlignment(JLabel.CENTER);
		LblMusic.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		LblMusicName.setHorizontalAlignment(JLabel.LEFT);
		LblMusicName.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		LblMusicArtist.setHorizontalAlignment(JLabel.LEFT);
		LblMusicArtist.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		LblMusicAlbum.setHorizontalAlignment(JLabel.LEFT);
		LblMusicAlbum.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		LblMusicReldate.setHorizontalAlignment(JLabel.LEFT);
		LblMusicReldate.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		LblMusicComposer.setHorizontalAlignment(JLabel.LEFT);
		LblMusicComposer.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		LblMusicLyricist.setHorizontalAlignment(JLabel.LEFT);
		LblMusicLyricist.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		MusicIntroduce.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		MusicLyric.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		LblMusic.setForeground(Color.white);
		LblMusicName.setForeground(Color.white);
		LblMusicArtist.setForeground(Color.white);
		LblMusicAlbum.setForeground(Color.white);
		LblMusicReldate.setForeground(Color.white);
		LblMusicComposer.setForeground(Color.white);
		LblMusicLyricist.setForeground(Color.white);
		
		MusicinfoPanel.setBackground(Color.decode("#333333"));
		MusicLyricPanel.setBackground(Color.decode("#333333"));
		MusicIntroduce.setBackground(Color.decode("#cccccc"));
		MusicLyric.setBackground(Color.decode("#cccccc"));
		
		
		MusicIntroduce.setEditable(false);
		MusicLyric.setEditable(false);
		
		
		MusicinfoPanel.add(LblMusic);
		MusicinfoPanel.add(LblMusicImg);
		MusicinfoPanel.add(LblMusicName);
		MusicinfoPanel.add(LblMusicArtist);
		MusicinfoPanel.add(LblMusicAlbum);
		MusicinfoPanel.add(LblMusicReldate);
		MusicinfoPanel.add(LblMusicComposer);
		MusicinfoPanel.add(LblMusicLyricist);
		MusicinfoPanel.add(Musicintro);
		MusicLyricPanel.add(MusicLyrics);
		
		add(MusicinfoPanel);
		add(MusicLyricPanel);

		setSize(new Dimension(820, 620));
		setVisible(true);
	}
	
	
	private void setMusicPanel() {
		String AlbumName;
		AlbumName = "defaultPanel";
		LblMusic.setText("음악을 선택하여 주세요.");
		LblMusicName.setText("곡 제목 : ");
		LblMusicArtist.setText("가수 : ");
		LblMusicAlbum.setText("앨범 : ");
		LblMusicReldate.setText("발매일 : ");
		LblMusicComposer.setText("작곡가 : ");
		LblMusicLyricist.setText("작사가 : ");
		Image albumImg = new ImageIcon("database\\AlbumImg\\"+AlbumName+".jpg").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		ImageIcon albumicon = new ImageIcon(albumImg);
		LblMusicImg.setIcon(albumicon);
		readIntroduce(AlbumName);
		readLyric(AlbumName);
	}
	
	private void setMusicPanel(Music music) {
		LblMusic.setText("곡 정보");
		LblMusicName.setText("곡 제목 : "+music.getTitle());
		LblMusicArtist.setText("가수 : "+music.getArtist());
		LblMusicAlbum.setText("앨범 : "+music.getAlbumName());
		LblMusicReldate.setText("발매일 : "+music.getReldate());
		LblMusicComposer.setText("작곡가 : "+music.getComposer());
		LblMusicLyricist.setText("작사가 : "+music.getLyricist());
		Image albumImg = new ImageIcon("database\\AlbumImg\\"+music.getAlbumName()+".jpg").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		ImageIcon albumicon = new ImageIcon(albumImg);
		LblMusicImg.setIcon(albumicon);
		readIntroduce(music.getTitle());
		readLyric(music.getTitle());
	}

	private void readIntroduce(String MusicName) {
		MusicIntroduce.setText("");
		String line;
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = Files.newBufferedReader(Paths.get("database\\Introduce\\"+MusicName+".txt"), Charset.forName("UTF-8"));
			while((line = bufferedReader.readLine()) != null) {
				MusicIntroduce.append(line+"\n");
			}
		}catch(NoSuchFileException e) {return;}
		catch(FileNotFoundException e) {return;}
		catch (IOException e) {e.printStackTrace();}
		finally {
			try {if(bufferedReader != null) bufferedReader.close();}
			catch(IOException e) {e.printStackTrace();}
		}
	}
	private void readLyric(String MusicName) {
		MusicLyric.setText("");
		String line;
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = Files.newBufferedReader(Paths.get("database\\Lyric\\"+MusicName+".txt"), Charset.forName("UTF-8"));
			while((line = bufferedReader.readLine()) != null) {
				MusicLyric.append(line+"\n");
			}
		}catch(NoSuchFileException e) {return;}
		catch(FileNotFoundException e) {return;}
		catch (IOException e) {e.printStackTrace();}
		finally {
			try {if(bufferedReader != null) bufferedReader.close();}
			catch(IOException e) {e.printStackTrace();}
		}
	}
}
