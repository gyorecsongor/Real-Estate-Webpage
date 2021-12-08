package com.ingatlan2.ingatlan.service;

import com.ingatlan2.ingatlan.entities.Advert;
import com.ingatlan2.ingatlan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AdvertService extends JdbcDaoSupport {

    private UserServiceImpl userServiceImpl;

    @Autowired
    DataSource dataSource;

    @Autowired
    public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
        this.userServiceImpl=userServiceImpl;
    }


    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }


    public List<Advert> listAdverts(){
        String sql = "SELECT * FROM real_estate";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Advert> result = new ArrayList<Advert>();
        for(Map<String, Object> row:rows){
            Advert advert = new Advert();
            advert.setAdvert_id((long)row.get("id"));
            advert.setTitle((String)row.get("title"));
            advert.setDescription((String) row.get("description"));
            advert.setTags((String) row.get("tags"));
            advert.setType((String) row.get("type"));
            advert.setArea((Double) row.get("area"));
            advert.setRoomCount((int) row.get("room_count"));
            advert.setLocation((String) row.get("location"));
            advert.setFurnished((boolean) row.get("furnished"));
            advert.setUser(userServiceImpl.getUserById((Long) row.get("user_userid")));
            result.add(advert);
        }
        return result;

    }

    public void insertAdvert(Advert advert) {
        String sql = "INSERT INTO real_estate(id,title, description, tags, type, area, room_count, location, furnished, user_userid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        getJdbcTemplate().update(sql, new Object[]{
                advert.getAdvert_id(),
                advert.getTitle(),
                advert.getDescription(),
                advert.getTags(),
                advert.getType(),
                advert.getArea(),
                advert.getRoomCount(),
                advert.getLocation(),
                advert.isFurnished(),
                advert.getUser().getUserid()
        });
    }

    public Advert getAdvertById(long id){
        String sql = "SELECT * FROM real_estate WHERE id="+id+";";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Advert> result = new ArrayList<Advert>();
        for(Map<String, Object> row:rows){
            Advert advert = new Advert();
            advert.setAdvert_id((long)row.get("id"));
            advert.setTitle((String)row.get("title"));
            advert.setDescription((String) row.get("description"));
            advert.setTags((String) row.get("tags"));
            advert.setType((String) row.get("type"));
            advert.setArea((Double) row.get("area"));
            advert.setRoomCount((int) row.get("room_count"));
            advert.setLocation((String) row.get("location"));
            advert.setFurnished((boolean) row.get("furnished"));
            advert.setUser(userServiceImpl.getUserById((Long) row.get("user_userid")));
            result.add(advert);
        }
        if (result.size()==0)
            return null;
        return result.get(0);
    }


    public void deleteAdvert(long id){
        String sql = "DELETE FROM real_estate WHERE id="+id+";";
        getJdbcTemplate().update(sql);
    }

    public void updateAdvert(long id, String title, String description, String tags, String type, Double area, int roomCount, String location, boolean furnished){
        String sql = "UPDATE real_estate SET title='"+title+"', description='"+description+"', tags='"+tags+"', type='"+type+"', area='"+area+"', location='"+location+"', furnished='"+furnished+"' WHERE id="+id;
        getJdbcTemplate().update(sql);
    }
    public void insertFavourite(Long user_id, Long real_estate_id) {
        if (getFavourite(user_id, real_estate_id).size() == 0) {
            String sql = "INSERT INTO favourites(user_id,real_estate_id) VALUES (?, ?)";
            getJdbcTemplate().update(sql, new Object[]{
                    user_id,
                    real_estate_id
            });
        }
    }
    public List<Map<String, Object>> getFavourite(Long user_id, Long real_estate_id) {
        String sql = "Select real_estate_id  from favourites where user_id= ? and  real_estate_id= ?;";
        return getJdbcTemplate().queryForList(sql, new Object[]{
                user_id,
                real_estate_id
        });
    }

    public List<Long> getFavourites(Long real_estate_id) {

        String sql = "Select user_id from favourites where real_estate_id= ? ;";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, new Object[]{
                real_estate_id		});
        List<Long> result = new ArrayList<>();
        for(Map<String, Object> row:rows){
            result.add((long)row.get("user_id"));
        }
        return result;
    }

    public void deleteFavourites(long id){
        String sql = "DELETE FROM favourites WHERE real_estate_id="+id+";";
        getJdbcTemplate().update(sql);
    }

    public List<Advert> listAdvertfavs(Long userid) {
        System.out.println(userid);
        String sql = "SELECT real_estate.* FROM real_estate, favourites, users WHERE users.userid=favourites.user_id and favourites.real_estate_id=real_estate.id and user_id='"+userid+"';";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Advert> res= new ArrayList<Advert>();
        for(Map<String, Object> row:rows){
            Advert advert = new Advert();
            advert.setAdvert_id((long)row.get("id"));
            advert.setTitle((String)row.get("title"));
            advert.setDescription((String) row.get("description"));
            advert.setTags((String) row.get("tags"));
            advert.setType((String) row.get("type"));
            advert.setArea((Double) row.get("area"));
            advert.setRoomCount((int) row.get("room_count"));
            advert.setLocation((String) row.get("location"));
            advert.setFurnished((boolean) row.get("furnished"));
            res.add(advert);
        }

        return res;

    }
}
