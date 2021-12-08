package com.ingatlan2.ingatlan.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Table(name = "REAL_ESTATE")
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private long advert_id;
    private String title;
    private String description;
    private String tags;
    private String type;
    private Double area;
    private int roomCount;
    private String location;
    private boolean furnished;
    @ManyToOne
    private User user;

    public Advert() {
        this.advert_id = ThreadLocalRandom.current().nextLong(1000000, 1000000000);
    }

    public Advert(String title, String description, String tags, String type, Double area, int roomCount,
            String location, boolean furnished, User user) {
        this.advert_id = ThreadLocalRandom.current().nextLong(1000000, 1000000000);
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.type = type;
        this.area = area;
        this.roomCount = roomCount;
        this.location = location;
        this.furnished = furnished;
        this.user = user;
    }

    public long getAdvert_id() {
        return advert_id;
    }

    public void setAdvert_id(long advert_id) {
        this.advert_id = advert_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isFurnished() {
        return furnished;
    }

    public void setFurnished(boolean furnished) {
        this.furnished = furnished;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToMany(mappedBy = "real_estate")
    private Set<User> users = new HashSet<User>();

    @Override
    public String toString() {
        return "Advert{" +
                "id=" + advert_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", tags='" + tags + '\'' +
                ", type='" + type + '\'' +
                ", area=" + area +
                ", roomCount=" + roomCount +
                ", location='" + location + '\'' +
                ", furnished=" + furnished +
                ", uplader_userid=" + user +
                '}';
    }
}
