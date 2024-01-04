package com.example.newlook.artist.POJO;

public class ArtistImgShowModel {

//        private int imgURL;

    private String course_name;
    private int imgid;
    public ArtistImgShowModel() {
        }


    public ArtistImgShowModel(String course_name, int imgid) {
        this.course_name = course_name;
        this.imgid = imgid;
    }

//        public ArtistImgShowModel(int imgURL) {
//            this.imgURL = imgURL;
//        }

//        public int getImgURL() {
//            return imgURL;
//        }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }
}
