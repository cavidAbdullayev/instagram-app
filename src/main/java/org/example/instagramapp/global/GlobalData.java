package org.example.instagramapp.global;

import java.util.Arrays;
import java.util.List;

public class GlobalData {
    public static Integer currentUserId = 5;
    public static final Integer otpMinute = 5;
    public static final String mediaPath = "C:\\cavid\\Java Projects\\instagram-app\\src\\main\\resources\\static\\data\\";
    public static final String mediaPathInResources = "static\\data\\";
    public static final String hostEmail = "cavidabdullayevv20@gmail.com";
    public static final String postSharing = "Post sharing...";
    public static final String postLiked = "Post liked successfully!";
    public static final String commentPosted = "Comment posted";
    public static final String activeTypeUpdated = "Active type updated successfully!";
    public static final String followSent = "Follow sent successfully!";


    public static List<String> imageExtensions = Arrays.asList(
            "jpeg", "jpg", "png", "tiff", "tif", "bpm", "raw", "webp");
    public static List<String> videoExtensions = Arrays.asList(
            "mp4", "avi", "mov", "wmv", "mkv", "flv", "mpeg");
}