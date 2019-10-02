package models;

import org.sql2o.*;

import java.util.List;

public class Upload {

    private String picture;
    private String caption;
    private String video;
    private int id;

    public Upload(String picture, String caption, String video){
        this.picture = picture;
        this.caption =caption;
        this.video = video;
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public String getCaption() {
        return caption;
    }

    public String getVideo() {
        return video;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void save(Upload upload){
        String sql = "INSERT INTO videos (picture, caption, video) VALUES (:picture, :caption, :video)";
        try (Connection con = DB.sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(upload)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
            upload.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    public static List<Upload> getAll(){
        try(Connection con = DB.sql2o.open()){
            return con.createQuery("SELECT * FROM videos")
                    .executeAndFetch(Upload.class);
        }
    }
}
   