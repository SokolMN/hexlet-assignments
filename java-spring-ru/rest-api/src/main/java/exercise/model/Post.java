package exercise.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class Post {
    private int userId;
    private String slug;
    private String title;
    private String body;

    public String getSlug() {
        return slug;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
