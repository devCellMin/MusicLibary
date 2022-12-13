package MusicSetting;
public class MusicBuilder {
	private Music music;
	public MusicBuilder(Music music) {
		super();
		this.music = music;
	}
	
	public MusicBuilder MusicCode(String MusicCode) {
		music.setMusicCode(MusicCode);
		return this;
	}
	
	public MusicBuilder Title(String title) {
		music.setTitle(title);
		return this;
	}
	
	public MusicBuilder Artist(String artist) {
		music.setArtist(artist);
		return this;
	}
	
	public MusicBuilder AlbumName (String albumname) {
		music.setAlbumName(albumname);
		return this;
	}
	
	public MusicBuilder RelDate(String reldate) {
		music.setReldate(reldate);
		return this;
	}
	
	public MusicBuilder Composer(String composer) {
		music.setComposer(composer);
		return this;
	}
	
	public MusicBuilder Lyricist(String lyricist) {
		music.setLyricist(lyricist);
		return this;
	}
	
	public Music getMusic() {
		return this.music;
	}
	
}
