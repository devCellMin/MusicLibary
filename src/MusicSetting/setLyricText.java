package MusicSetting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class setLyricText extends JFrame{
//	public static void main(String[] args) {
//		new setLyricText();
//	}	
	Music music;
	String MusicName;
	
	// Add
	public setLyricText (String MusicName) {
		this.MusicName = MusicName;
		run(MusicName);
	}
	
	// Editing
	public setLyricText (String MusicName, Music music) {
		this.MusicName = MusicName;
		this.music = music;
		run(MusicName, music);
	}
	
	private void run(String MusicName) {
		setLayout(new BorderLayout());
		
		showNorth();
		showCenter();
		showSouth();
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File csv = new File("database/Lyric/"+MusicName+".txt");
				
		        BufferedWriter bw = null; // 출력 스트림 생성
		        try {
		            bw = new BufferedWriter(new FileWriter(csv));
		            // csv파일의 기존 값에 이어쓰려면 위처럼 true를 지정하고, 기존 값을 덮어쓰려면 true를 삭제한다\
		            String str = TA.getText();
		            bw.write(str);
		        	JOptionPane.showMessageDialog(null, "저장완료");
		        	dispose();
		        }
		        catch (FileNotFoundException e1) {e1.printStackTrace();}
		        catch (IOException e1) {e1.printStackTrace();}
		        finally {
		            try {
		                if (bw != null) {
		                    bw.flush(); // 남아있는 데이터까지 보내 준다
		                    bw.close(); // 사용한 BufferedWriter를 닫아 준다
		                }
		            } catch (IOException e1) {e1.printStackTrace();}
		        }
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();				
			}
		});
		
		setTitle("Add Lyric");
		setBounds(800, 200, 400, 500);
		setResizable(false);
		setVisible(true);
	}
	
	

	private void run(String MusicName, Music music) {
		setLayout(new BorderLayout());
		
		showNorth();
		showCenter();
		showSouth();
		
		LblTitle.setText("가사 수정");
		
		readprevLyric(music);
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File csv = new File("database/Lyric/"+music.getTitle()+".txt");
				if(!MusicName.equals(music.getTitle())) {
					File rename = new File("database/Lyric/"+MusicName+".txt");
					csv.renameTo(rename);
				}
				
				BufferedWriter bw = null; // 출력 스트림 생성
		        try {
		            bw = new BufferedWriter(new FileWriter(csv));
		            // csv파일의 기존 값에 이어쓰려면 위처럼 true를 지정하고, 기존 값을 덮어쓰려면 true를 삭제한다\
		            String str = TA.getText();
		            bw.write(str);
		        	JOptionPane.showMessageDialog(null, "저장완료");
		        	dispose();
		        }
		        catch (FileNotFoundException e1) {e1.printStackTrace();}
		        catch (IOException e1) {e1.printStackTrace();}
		        finally {
		        	try {
		        		if (bw != null) {
		                    bw.flush(); // 남아있는 데이터까지 보내 준다
		                    bw.close(); // 사용한 BufferedWriter를 닫아 준다
		                }
		            } catch (IOException e1) {e1.printStackTrace();}
		        }
		    }
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();				
			}
		});
		
		
		setTitle("Edit Lyric");
		setBounds(800, 200, 400, 500);
		setResizable(false);
		setVisible(true);
	}

	JLabel LblTitle;
	JTextArea TA; JButton btnSave, btnCancel;
	JScrollPane TAScroll;
	private void showNorth() {
		JPanel TitlePanel = new JPanel();
		LblTitle = new JLabel("가사 추가");
		LblTitle.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		TitlePanel.setBackground(Color.decode("#333333"));
		LblTitle.setForeground(Color.decode("#cccccc"));
		TitlePanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
		TitlePanel.add(LblTitle);
		add(TitlePanel, BorderLayout.NORTH);
	}
	private void showCenter() {
		JPanel TextAreaPanel = new JPanel(null);
		TA = new JTextArea();
		TA.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		TAScroll = new JScrollPane(TA, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		TAScroll.setBounds(20, 5, 340, 350);
		TAScroll.setPreferredSize(new Dimension(340, 350));
		TextAreaPanel.setBackground(Color.decode("#333333"));
		TextAreaPanel.add(TAScroll);
		add(TextAreaPanel, BorderLayout.CENTER);
	}
	private void showSouth() {
		JPanel ButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btnSave = new JButton(" 저 장 ");
		JLabel LblSpace = new JLabel(" ");
		btnCancel = new JButton(" 취 소 ");
		
		btnSave.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnCancel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnSave.setBackground(Color.decode("#d7d2cb"));
		btnCancel.setBackground(Color.decode("#d7d2cb"));
		
		ButtonPanel.add(btnSave);
		ButtonPanel.add(LblSpace);
		ButtonPanel.add(btnCancel);
		ButtonPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 20));
		ButtonPanel.setBackground(Color.decode("#333333"));
		add(ButtonPanel, BorderLayout.SOUTH);
	}
	private void readprevLyric(Music music) {
		TA.setText("");
		String line;
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = Files.newBufferedReader(Paths.get("database\\Lyric\\"+music.getTitle()+".txt"), Charset.forName("UTF-8"));
			while((line = bufferedReader.readLine()) != null) {
				TA.append(line+"\n");
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
