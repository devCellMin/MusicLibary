package MusicSetting;

import java.util.Arrays;

public class Music {
	private String musicCode; // 곡번호
	private String title; // 곡명
	private String artist; // 가수
	private String albumName; // 앨범명
	private String reldate; // 발매일
	private String composer; // 작곡가
	private String lyricist; // 작사가
	
	public String getMusicCode() {
		return musicCode;
	}
	public void setMusicCode(String musicCode) {
		this.musicCode = musicCode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public String getReldate() {
		return reldate;
	}
	public void setReldate(String reldate) {
		this.reldate = reldate;
	}
	public String getComposer() {
		return composer;
	}
	public void setComposer(String composer) {
		this.composer = composer;
	}
	public String getLyricist() {
		return lyricist;
	}
	public void setLyricist(String lyricist) {
		this.lyricist = lyricist;
	}
	@Override
	public String toString() {
		return "\""+musicCode+"\", \""+title+"\", \""+artist+"\", \""+albumName+"\", \""+reldate+"\", \""+composer+"\", \""+lyricist+"\"";
	}

}
