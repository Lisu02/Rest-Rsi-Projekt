package org.example.restrsiprojekt.model;


import java.util.List;
public class Movie {

    private Long id;
    private String title;
    private String director;
    private String releaseDate; //Standard od Java 8 (sama data bez godziny)
    private String description;
    private MovieType movieType;
    private List<Long> actorList;
    private String image;

    /* Do zdjec NIEAKTUALNE NIEAKTUALNE
    * jest image a jako 'implementacja' dajemy File i potem ImageIO.read('wczytane zdjecie z pliku')
    * */

//    public static DataHandler loadImageOrDefault(String pathToImage) {
//        if (pathToImage == null || pathToImage.isBlank()) {
//            pathToImage = "/images/shrek.png"; // domyślny obrazek
//        }
//
//        try (InputStream is = Movie.class.getResourceAsStream(pathToImage)) {
//            if (is == null) throw new RuntimeException("Obrazek nie został znaleziony w zasobach: " + pathToImage);
//
//            Path temp = Files.createTempFile("image_", ".png");
//            Files.copy(is, temp, StandardCopyOption.REPLACE_EXISTING);
//            return new DataHandler(new FileDataSource(temp.toFile()));
//        } catch (IOException e) {
//            throw new RuntimeException("Nie można załadować obrazka: " + pathToImage, e);
//        }
//    }




    public Movie(Long id, String title, String director, String releaseDate, String description, MovieType movieType, String imagePath) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.releaseDate = releaseDate;
        this.description = description;
        this.movieType = movieType;
        this.image = imagePath;
    }

    public Movie(String title, String director, String releaseDate, String description, MovieType movieType, String imagePath) {
        this(0L,title, director, releaseDate,description,movieType,imagePath);
    }

    public Movie(){
        this(0L,"tytul","Andrzej Tralala","26-08-2002","fajny film",MovieType.ACTION,"");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public List<Long> getActorList() {
        return actorList;
    }

    public void setActorList(List<Long> actorList) {
        this.actorList = actorList;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
