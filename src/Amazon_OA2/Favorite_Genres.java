package Amazon_OA2;

import com.sun.security.jgss.GSSUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Given a map Map<String, List<String>> userSongs with user names as keys and a list of all the songs that the user has listened to as values.
 *
 * Also given a map Map<String, List<String>> songGenres, with song genre as keys and a list of all the songs within that genre as values. The song can only belong to only one genre.
 *
 * The task is to return a map Map<String, List<String>>, where the key is a user name and the value is a list of the user's favorite genre(s). Favorite genre is the most listened to genre. A user can have more than one favorite genre if he/she has listened to the same number of songs per each of the genres.
 *
 * Example 1:
 *
 * Input:
 * userSongs = {
 *    "David": ["song1", "song2", "song3", "song4", "song8"],
 *    "Emma":  ["song5", "song6", "song7"]
 * },
 * songGenres = {
 *    "Rock":    ["song1", "song3"],
 *    "Dubstep": ["song7"],
 *    "Techno":  ["song2", "song4"],
 *    "Pop":     ["song5", "song6"],
 *    "Jazz":    ["song8", "song9"]
 * }
 *
 * Output: {
 *    "David": ["Rock", "Techno"],
 *    "Emma":  ["Pop"]
 * }
 *
 * Explanation:
 * David has 2 Rock, 2 Techno and 1 Jazz song. So he has 2 favorite genres.
 * Emma has 2 Pop and 1 Dubstep song. Pop is Emma's favorite genre.
 * Example 2:
 *
 * Input:
 * userSongs = {
 *    "David": ["song1", "song2"],
 *    "Emma":  ["song3", "song4"]
 * },
 * songGenres = {}
 *
 * Output: {
 *    "David": [],
 *    "Emma":  []
 * }
 */
public class Favorite_Genres {

    public static HashMap<String, List<String>> favoriteGenres(HashMap<String, List<String>> userSongs, HashMap<String, List<String>> songGenres){
        HashMap<String, List<String>> res = new HashMap<>();
        if (songGenres.isEmpty() || userSongs.isEmpty()) return res;
        HashMap<String, String> songToGenre = new HashMap<>();
        for (String genre: songGenres.keySet()){
            List<String> songs = songGenres.get(genre);
            for (String song: songs){
                songToGenre.put(song, genre);
            }
        }

        HashMap<String, Integer> countGenres;
        for (String user: userSongs.keySet()){
            int maxCount = 0;
            res.put(user, new ArrayList<>());
            List<String> songs = userSongs.get(user);
            countGenres = new HashMap<>();
            for (String song: songs){
                String genre = songToGenre.get(song);
                int c = countGenres.getOrDefault(genre, 0) + 1;
                maxCount = Math.max(maxCount, c);
                countGenres.put(genre, c);
            }

            for (String genre: countGenres.keySet()){
                if (countGenres.get(genre) == maxCount) res.get(user).add(genre);
            }
        }
        return res;
    }

    public static void main(String[] args){
        HashMap<String, List<String>> userSongs = new HashMap<>();
        HashMap<String, List<String>> songGenres = new HashMap<>();

        List<String> s1 = new ArrayList<>();
        s1.add("song1");
        s1.add("song2");
        s1.add("song3");
        s1.add("song4");
        s1.add("song8");
        userSongs.put("David", s1);
        List<String> s2 = new ArrayList<>();
        s2.add("song5");
        s2.add("song6");
        s2.add("song7");
        userSongs.put("Emma", s2);

        List<String> s3 = new ArrayList<>();
        s3.add("song1");
        s3.add("song3");
        songGenres.put("Rock", s3);
        List<String> s4 = new ArrayList<>();
        s4.add("song7");
        songGenres.put("Dubstep", s4);
        List<String> s5 = new ArrayList<>();
        s5.add("song2");
        s5.add("song4");
        songGenres.put("Techno", s5);
        List<String> s6 = new ArrayList<>();
        s6.add("song5");
        s6.add("song6");
        songGenres.put("Pop", s6);
        List<String> s7 = new ArrayList<>();
        s7.add("song8");
        s7.add("song9");
        songGenres.put("Jazz", s7);

        HashMap<String, List<String>> res = favoriteGenres(userSongs, songGenres);
        for (String key: res.keySet()){
            System.out.println(key + ":" + res.get(key));
        }
    }
}
