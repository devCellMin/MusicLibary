package MusicSetting;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;



/*
 * Table To show Musics for User and Administer
 * Show Music's MusicCode, title, artist, albumName, Reldate, Composer, Lyricist to Administer
 * Show Music's MusicCode, title, artist
 * */
public class MusicTableData extends AbstractTableModel{
	List<Music> MusicList;
	String[] adminheader = new String[] {"음악번호", "곡제목", "아티스트", "앨범", "발매일", "작곡가", "작사가"};
	String[] showheader = new String[] {"곡제목", "아티스트", "앨범", "발매일"};
	String[] userheader = new String[] {"음악번호", "곡 제목", "아티스트"};
	
	public MusicTableData() {
		updateList();
	}
	
	private void updateList() {
		MusicList = new ArrayList<>();
		try{
			Scanner scanner = new Scanner(new File("database/MusicDB.csv"));
			for(int i=0;scanner.hasNextLine();i++) {
				String[] data = scanner.nextLine().split("\",\"");
				if(data == null) {
					continue;
				}
				if(i!=0) { // CSV의 첫줄은 Header이기 때문이다.
					Music music = new Music();
					MusicBuilder MB = new MusicBuilder(music);
					music = MB.MusicCode(data[0].replace("\"", "")).Title(data[1]).Artist(data[2])
							.AlbumName(data[3]).RelDate(data[4])
							.Composer(data[5]).Lyricist(data[6].replace("\"", "")).getMusic();
					MusicList.add(music);
				}
			} scanner.close();
			
		} catch(Exception e) {e.printStackTrace();}
	}

	public List<Music> getMusicList() {
		return MusicList;
	}
	
	public Music getMusicfromTable(int rowindex) {
		return MusicList.get(rowindex);
	}
	
	public String[] getAdminheader() {
		return adminheader;
	}

	public String[] getUserheader() {
		return userheader;
	}

	public String[] getShowheader() {
		return showheader;
	}
	

	public String[][] showUserTable(){
		updateList();
		int cnt=0;String[][] info = new String[MusicList.size()][userheader.length];
		for(Music music : MusicList) {
			info[cnt][0] = String.valueOf(cnt+1);
			info[cnt][1] = music.getTitle();
			info[cnt][2] = music.getArtist();
			cnt++;
		}return info;
	}
	
	//Show Music's MusicCode, title, artist, albumName, Reldate, Composer, Lyricist to Administer
	public String[][] showAdminTable(){
		updateList();
		int cnt=0;String[][] info = new String[MusicList.size()][showheader.length];
		for(Music music : MusicList) {
			info[cnt][0] = music.getTitle();
			info[cnt][1] = music.getArtist();
			info[cnt][2] = music.getAlbumName();
			info[cnt][3] = music.getReldate();
			cnt++;
		}return info;
	}
	
	public Music getMusic(String Musictitle) {
		int cnt = 0;
		for(Music music : MusicList) {
			if (music.getTitle().equals(Musictitle)) {
				return music;
			}cnt++;
		} return null;
	}
	
	public void refresh() {
		updateList();
		super.fireTableDataChanged();
	}
	
	public void save(Music music) {
		music.setMusicCode(String.valueOf(MusicList.size()+1));
		MusicList.add(music);
		//Method to Save one Music List at Final
		 File csv = new File("database/MusicDB.csv"); 
         BufferedWriter bw = null; // 출력 스트림 생성
         try {
             bw = new BufferedWriter(new FileWriter(csv, true));
             // csv파일의 기존 값에 이어쓰려면 위처럼 true를 지정하고, 기존 값을 덮어쓰려면 true를 삭제한다\
             String inputdata="\n";
        	 inputdata += "\""+music.getMusicCode()+"\",";
        	 inputdata += "\""+music.getTitle()+"\",";
        	 inputdata += "\""+music.getArtist()+"\",";
        	 inputdata += "\""+music.getAlbumName()+"\",";
        	 inputdata += "\""+music.getReldate()+"\",";
        	 inputdata += "\""+music.getComposer()+"\",";
        	 inputdata += "\""+music.getLyricist()+"\"";
        	 bw.write(inputdata);
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
	
	public void saveAll(Music Editedmusic) {
		MusicList.set(Integer.parseInt(Editedmusic.getMusicCode())-1, Editedmusic);
		
		//Method to Save All Music List
		 File csv = new File("database/MusicDB.csv"); 
         BufferedWriter bw = null; // 출력 스트림 생성
         try {
             bw = new BufferedWriter(new FileWriter(csv));
             bw.write("\"음악번호\",\"곡제목\",\"아티스트\",\"앨범\",\"발매일\",\"작곡가\",\"작사가\"");
             bw.newLine();int cnt=0;
             // csv파일의 기존 값에 이어쓰려면 위처럼 true를 지정하고, 기존 값을 덮어쓰려면 true를 삭제한다\
             for(Music music : MusicList) {
            	 String inputdata="";
            	 inputdata += "\""+music.getMusicCode()+"\",";
            	 inputdata += "\""+music.getTitle()+"\",";
            	 inputdata += "\""+music.getArtist()+"\",";
            	 inputdata += "\""+music.getAlbumName()+"\",";
            	 inputdata += "\""+music.getReldate()+"\",";
            	 inputdata += "\""+music.getComposer()+"\",";
            	 inputdata += "\""+music.getLyricist()+"\"";
            	 bw.write(inputdata);
            	 if(cnt<MusicList.size()-1) {bw.newLine();} // 개행
            	 cnt++;
             }
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
	
	public void saveAll() {
		//Method to Save All Music List
		 File csv = new File("database/MusicDB.csv"); 
         BufferedWriter bw = null; // 출력 스트림 생성
         try {
             bw = new BufferedWriter(new FileWriter(csv));
             bw.write("\"음악번호\",\"곡제목\",\"아티스트\",\"앨범\",\"발매일\",\"작곡가\",\"작사가\"");
             bw.newLine();int cnt=0;
             // csv파일의 기존 값에 이어쓰려면 위처럼 true를 지정하고, 기존 값을 덮어쓰려면 true를 삭제한다\
             for(Music music : MusicList) {
            	 String inputdata="";
            	 inputdata += "\""+music.getMusicCode()+"\",";
            	 inputdata += "\""+music.getTitle()+"\",";
            	 inputdata += "\""+music.getArtist()+"\",";
            	 inputdata += "\""+music.getAlbumName()+"\",";
            	 inputdata += "\""+music.getReldate()+"\",";
            	 inputdata += "\""+music.getComposer()+"\",";
            	 inputdata += "\""+music.getLyricist()+"\"";
            	 bw.write(inputdata);
            	 if(cnt<MusicList.size()-1) {bw.newLine();} // 개행
            	 cnt++;
             }
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
	
	public void deleteMusic(int number) {
		MusicList.remove(number);
		int code = 1;
		for(Music music : MusicList) {
			music.setMusicCode(String.valueOf(code));
			code++;
		}saveAll();
		JOptionPane.showMessageDialog(null, "삭제완료");
	}
	
	// Count of Rows of User's Table and Admin's Table is same
	public int getRowCount() {
		return MusicList.size();
	}
	
	// Count of Columns for Administer
	public int getColumnCount() {
		return adminheader.length;
	}
	
	// Count of Columns for user
	public int getUserColumnCount() {
		return userheader.length;
	}

	// It's only for Administer
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0:
			return MusicList.get(rowIndex).getMusicCode();
		case 1:
			return MusicList.get(rowIndex).getTitle();
		case 2:
			return MusicList.get(rowIndex).getArtist();
		case 3:
			return MusicList.get(rowIndex).getAlbumName();
		case 4:
			return MusicList.get(rowIndex).getReldate();
		case 5:
			return MusicList.get(rowIndex).getComposer();
		case 6:
			return MusicList.get(rowIndex).getLyricist();
		} return null;
	}	
}