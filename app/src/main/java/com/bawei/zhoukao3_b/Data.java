package com.bawei.zhoukao3_b;

/**
 * 作    者：云凯文
 * 时    间：2017/2/22
 * 描    述：
 * 修改时间：
 */
public class Data {
    private String img;
    private String introduction;
    private String occupation;
    private String userName;
    private int userAge;

    public Data() {
    }

    public Data(String img, String introduction, String occupation, String userName, int userAge) {
        this.img = img;
        this.introduction = introduction;
        this.occupation = occupation;
        this.userName = userName;
        this.userAge = userAge;
    }

    public String getImg() {
        return img;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    @Override
    public String toString() {
        return "Data{" +
                "img='" + img + '\'' +
                ", introduction='" + introduction + '\'' +
                ", occupation='" + occupation + '\'' +
                ", userName='" + userName + '\'' +
                ", userAge=" + userAge +
                '}';
    }
}
