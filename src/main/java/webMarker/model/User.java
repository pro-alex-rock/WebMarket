package webMarker.model;

import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String password;
    private String sole;

    public User(int id, String name, String password, String sole) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.sole = sole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && name.equals(user.name) && password.equals(user.password) && sole.equals(user.sole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, sole);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
