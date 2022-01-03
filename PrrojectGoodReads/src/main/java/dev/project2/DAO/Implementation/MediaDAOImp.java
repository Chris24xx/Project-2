package dev.project2.DAO.Implementation;
import dev.project2.DAO.Abstract.MediaDAO;
import dev.project2.Entities.Media;
import dev.project2.dbcon.DBConn;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


// This is the DAO class that implements the interface "MediaDAO". It contains all the methods that will interact with
// the database and the website.
public class MediaDAOImp implements MediaDAO {

    @Override
    public Media createNewMedia(Media media) {
        try (Connection connection = DBConn.createConnection()){
            String sql = "insert into project2.media values(default, ?, ?, ?, ?, ?, default, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, media.getTitle());
            preparedStatement.setString(2, media.getCreator());
            preparedStatement.setString(3, media.getSynopsis());
            preparedStatement.setString(4, media.getMediaType());
            preparedStatement.setString(5, media.getGenre());
            preparedStatement.setInt(6, media.getUserId());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            media.setMediaId(resultSet.getInt("media_id"));
            return media;
        } catch (SQLException q) {
            q.printStackTrace();
            return null;
        }
    }



    @Override
    public List<Media> getAllMedia() {
        try (Connection connection = DBConn.createConnection()){
            String sql = "select * from project2.media";
            Statement statement = connection.createStatement();
            ResultSet resultSet  = statement.executeQuery(sql);
            List<Media> allMedia = new ArrayList<>();
            while(resultSet.next()){
                Media media = new Media(
                        resultSet.getInt("media_id"),
                        resultSet.getString("title"),
                        resultSet.getString("creator"),
                        resultSet.getString("synopsis"),
                        resultSet.getString("media_type"),
                        resultSet.getString("genre"),
                        resultSet.getBoolean("status"),
                        resultSet.getInt("user_id")
                );
                allMedia.add(media);
            }
            return allMedia;
        } catch (SQLException q){
            q.printStackTrace();
            return null;
        }
    }




    @Override
    public List<Media> getAllBooks() {
        try (Connection connection = DBConn.createConnection()){
            String sql = "select * from project2.media where media_type = 'Book'";
            Statement statement = connection.createStatement();
            ResultSet resultSet  = statement.executeQuery(sql);
            List<Media> allMedia = new ArrayList<>();
            while(resultSet.next()){
                Media media = new Media(
                        resultSet.getInt("media_id"),
                        resultSet.getString("title"),
                        resultSet.getString("creator"),
                        resultSet.getString("synopsis"),
                        resultSet.getString("media_type"),
                        resultSet.getString("genre"),
                        resultSet.getBoolean("status"),
                        resultSet.getInt("user_id")
                );
                allMedia.add(media);
            }
            return allMedia;
        } catch (SQLException q){
            q.printStackTrace();
            return null;
        }
    }



    @Override
    public List<Media> getAllMovies() {
        try (Connection connection = DBConn.createConnection()){
            String sql = "select * from project2.media where media_type = 'Movie'";
            Statement statement = connection.createStatement();
            ResultSet resultSet  = statement.executeQuery(sql);
            List<Media> allMedia = new ArrayList<>();
            while(resultSet.next()){
                Media media = new Media(
                        resultSet.getInt("media_id"),
                        resultSet.getString("title"),
                        resultSet.getString("creator"),
                        resultSet.getString("synopsis"),
                        resultSet.getString("media_type"),
                        resultSet.getString("genre"),
                        resultSet.getBoolean("status"),
                        resultSet.getInt("user_id")
                );
                allMedia.add(media);
            }
            return allMedia;
        } catch (SQLException q){
            q.printStackTrace();
            return null;
        }
    }



    @Override
    public List<Media> getAllGames() {
        try (Connection connection = DBConn.createConnection()){
            String sql = "select * from project2.media where media_type = 'Game'";
            Statement statement = connection.createStatement();
            ResultSet resultSet  = statement.executeQuery(sql);
            List<Media> allMedia = new ArrayList<>();
            while(resultSet.next()){
                Media media = new Media(
                        resultSet.getInt("media_id"),
                        resultSet.getString("title"),
                        resultSet.getString("creator"),
                        resultSet.getString("synopsis"),
                        resultSet.getString("media_type"),
                        resultSet.getString("genre"),
                        resultSet.getBoolean("status"),
                        resultSet.getInt("user_id")
                );
                allMedia.add(media);
            }
            return allMedia;
        } catch (SQLException q){
            q.printStackTrace();
            return null;
        }
    }



    @Override
    public Media getMediaByTitle(String title) {
        try (Connection connection = DBConn.createConnection()){
            String sql = "select * from project2.media where title = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            Media media = new Media(
                    rs.getInt("media_id"),
                    rs.getString("title"),
                    rs.getString("creator"),
                    rs.getString("synopsis"),
                    rs.getString("media_type"),
                    rs.getString("genre"),
                    rs.getBoolean("status"),
                    rs.getInt("user_id")
            );
            return media;
        } catch (SQLException q){
            q.printStackTrace();
            return null;
        }
    }




    @Override
    public List<Media> getAllMediaPerUser(int userId) {
        try (Connection connection = DBConn.createConnection()){
            String sql = "select * from project2.media where user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            List<Media> userMedia = new ArrayList<>();
            while(rs.next()){
                Media media = new Media(
                        rs.getInt("media_id"),
                        rs.getString("title"),
                        rs.getString("creator"),
                        rs.getString("synopsis"),
                        rs.getString("media_type"),
                        rs.getString("genre"),
                        rs.getBoolean("status"),
                        rs.getInt("user_id")
                );
                userMedia.add(media);
            }
            return userMedia;
        } catch (SQLException q){
            q.printStackTrace();
            return null;
        }
    }



    @Override
    public List<Media> getPendingMedia() {
        try (Connection connection = DBConn.createConnection()){
            String sql = "select * from project2.media where status = false";
            Statement statement = connection.createStatement();
            ResultSet resultSet  = statement.executeQuery(sql);
            List<Media> pendingMedia = new ArrayList<>();
            while(resultSet.next()){
                Media media = new Media(
                        resultSet.getInt("media_id"),
                        resultSet.getString("title"),
                        resultSet.getString("creator"),
                        resultSet.getString("synopsis"),
                        resultSet.getString("media_type"),
                        resultSet.getString("genre"),
                        resultSet.getBoolean("status"),
                        resultSet.getInt("user_id")
                );
                pendingMedia.add(media);
            }
            return pendingMedia;
        } catch (SQLException q){
            q.printStackTrace();
            return null;
        }
    }





    @Override
    public List<Media> getApprovedMedia() {
        try (Connection connection = DBConn.createConnection()){
            String sql = "select * from project2.media where status = true";
            Statement statement = connection.createStatement();
            ResultSet resultSet  = statement.executeQuery(sql);
            List<Media> approvedMedia = new ArrayList<>();
            while(resultSet.next()){
                Media media = new Media(
                        resultSet.getInt("media_id"),
                        resultSet.getString("title"),
                        resultSet.getString("creator"),
                        resultSet.getString("synopsis"),
                        resultSet.getString("media_type"),
                        resultSet.getString("genre"),
                        resultSet.getBoolean("status"),
                        resultSet.getInt("user_id")
                );
                approvedMedia.add(media);
            }
            return approvedMedia;
        } catch (SQLException q){
            q.printStackTrace();
            return null;
        }
    }



    @Override
    public boolean approveMedia(int mediaId) {
        try (Connection connection = DBConn.createConnection()) {
            String sql = "update project2.media set status = true where media_id = ? returning status";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, mediaId);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            boolean approved = rs.getBoolean("status");
            return approved;
        } catch (SQLException q) {
            q.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean deleteMedia(int mediaId) {
        return false;
    }
}
