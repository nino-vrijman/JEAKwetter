package model;

/**
 * Created by Nino Vrijman on 9-5-2017.
 */
public class UserDTO {
    private long id;
    private String name;
    private String bio;
    private String location;
    private String avatarURL;
    private String websiteURL;
    private String username;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public String getWebsiteURL() {
        return websiteURL;
    }

    public void setWebsiteURL(String websiteURL) {
        this.websiteURL = websiteURL;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserDTO() {}

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.bio = user.getBio();
        this.location = user.getLocation();
        this.avatarURL = user.getAvatarURL();
        this.websiteURL = user.getWebsiteURL();
        this.username = user.getUsername();
    }
}
