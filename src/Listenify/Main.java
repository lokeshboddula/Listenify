package Listenify;

import javax.naming.LinkLoopException;
import java.util.*;

public class Main {
    public static List<Album> albums = new ArrayList<>();
    public static void main(String[] args) {
        Album album = new Album("Old Hindi Songs", "Arijit Singh");

        album.addSongToAlbum("ae dil hai mushkil", 4.5);
        album.addSongToAlbum("channa mereya", 3.9);
        album.addSongToAlbum("hawayein", 4.2);

        albums.add(album);

        album = new Album("new Hindi Songs", "Arigit Singh");

        album.addSongToAlbum("pathaan", 4.6);
        album.addSongToAlbum("kesariya", 3.9);
        album.addSongToAlbum("tere pyaar mein", 2.58);

        albums.add(album);

        LinkedList<Song> playList_1 = new LinkedList<>();

        albums.get(0).addSongToPlaylist("channa mereya", playList_1);
        albums.get(0).addSongToPlaylist("hawayein", playList_1);
        albums.get(1).addSongToPlaylist("kesariya", playList_1);
        albums.get(1).addSongToPlaylist("pathaan", playList_1);

        play(playList_1);
    }
    public static void play(LinkedList<Song> playList) {
        ListIterator<Song> listIterator = playList.listIterator();
        if(playList.isEmpty()) return;

        Scanner sc = new Scanner(System.in);
        printMenu();

        System.out.println("Now playing " + listIterator.next());
        boolean forward = true;
        boolean quit = false;

        while (!quit) {
            int choice = sc.nextInt();

            switch (choice) {
                case 0 : quit = true;
                    break;
                case 1 :
                    if(!forward) {
                        listIterator.next();
                        forward = true;
                    }
                    if(listIterator.hasNext()) {
                        System.out.println(listIterator.next().toString());
                    } else {
                        System.out.println("You are already at the last song");
                    }
                    //to play the next song
                    break;
                case 2 :
                    //to play the previous song
                    if(forward) {
                        listIterator.previous();
                        forward = false;
                    }
                    if(listIterator.hasPrevious()) {
                        System.out.println(listIterator.previous().toString());
                    } else {
                        System.out.println("You are already at the first song");
                    }
                    break;

                case 3 :
                    //replay current song
                    if(forward) {
                        System.out.println(listIterator.previous().toString());
                        forward = false;
                    } else {
                        System.out.println(listIterator.next().toString());
                        forward = true;
                    }
                    break;
                    
                case 4 :
                    printALLSongs(playList);
                    break;

                case 5 :
                    printMenu();
                    break;

                case 6 :
                    if(!playList.isEmpty()) {
                        System.out.println(listIterator.previous().toString() + " has been removed from playlist.");
                        listIterator.remove();

                        if(!playList.isEmpty() && listIterator.hasPrevious()) {
                            System.out.println("Now playing " + listIterator.next().toString());
                        } else if(!playList.isEmpty() && listIterator.hasNext()) {
                            System.out.println("Now playing " + listIterator.previous().toString());
                        } else {
                            System.out.println("playlist is empty");
                        }
                    }
                    
                    break;
            }
        }
    }

    private static void printALLSongs(LinkedList<Song> playList) {

        ListIterator listIterator = playList.listIterator();

        while (listIterator.hasNext()) {
            System.out.println(listIterator.next().toString());
        }
    }

    public static void printMenu() {
        // different options we have
        System.out.println("Available options \n press");
        System.out.println("0 - to quit \n" +
                "1 - to play next song \n" +
                "2 - to play previous song\n" +
                "3 - to replay the current song \n" +
                "4 - list all songs \n" +
                "5 - print all available options\n" +
                "6 - delete current song");
    }
}