package entity;

import java.sql.Date;
import java.util.Objects;

public class Pet {
    private Long id;
    private String nickname;
    private Date birthday;
    private String type;
    private Long ownerId;

    public Pet() {
    }

    public Pet(Long id, String nickname, Date birthday, String type, Long ownerId) {
        this.id = id;
        this.nickname = nickname;
        this.birthday = birthday;
        this.type = type;
        this.ownerId = ownerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwner_id(Long owner_id) {
        this.ownerId = owner_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet)) return false;
        Pet pet = (Pet) o;
        return birthday == pet.birthday && Objects.equals(id, pet.id) && Objects.equals(nickname, pet.nickname) && Objects.equals(type, pet.type) && Objects.equals(ownerId, pet.ownerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, birthday, type, ownerId);
    }

    @Override
    public String toString() {
        return  nickname + ", " + birthday + ", " + type;
    }
}
