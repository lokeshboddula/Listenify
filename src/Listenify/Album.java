package Listenify;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
public class Album {
    public String albumName;
    public String artistName;
    public List<Song> songList;

    public Album(String albumName, String artistName) {
        this.albumName = albumName;
        this.artistName = artistName;
        this.songList = new ArrayList<>();
    }

    boolean findSongInAlbum(String title) {
        //iterate over song list ans match
        //the titles to find the req song

        for(Song song : songList) {
            if(song.title.equals(title)) return true;
        }
        return false;
    }

    public String addSongToAlbum(String title, double duration) {
        //check if song exists if not only then add the song

        if(findSongInAlbum(title)) {
            return "Song already exists";
        } else {
            Song newSong = new Song(title, duration);
            songList.add(newSong);
            return "Song added successfully!";
        }
    }

    public String addSongToPlaylist(int trackNo, LinkedList<Song> playList) {
        //track no is a number in songList
        //TrackNo 1,2,3,4,5.....
        //indices 0,1,2,3,4.....
        int  index = trackNo - 1;

        if(index >= 0 && index < this.songList.size()) {
            Song song = this.songList.get(index);
            playList.add(song);
            return "Song added to playList";
        }
        return "Invalid track number";
    }

    public String addSongToPlaylist(String title, LinkedList<Song> playList) {

        //we need to find the song with that title and put it in the playlist
        for(Song song : songList) {
            if(song.title.equals(title)) {
                playList.add(song);
                //System.out.println("Song added to playList");
                return "Song added to playList";
            }
        }
        return "Song does not exist";
    }
}
