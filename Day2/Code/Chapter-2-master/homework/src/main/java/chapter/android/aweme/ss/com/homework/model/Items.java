package chapter.android.aweme.ss.com.homework.model;

public class Items {
    private int mAvatarId;
    private int mRobotNoticeId;
    private String mDescription;
    private String mTitle;
    private String mTime;

    public Items(int avatarId, int robotNoticeId, String title, String description, String time) {
        mAvatarId = avatarId;
        mRobotNoticeId = robotNoticeId;
        mDescription = description;
        mTitle = title;
        mTime = time;
    }

    public int getAvatarId() {
        return mAvatarId;
    }

    public int getRobotNoticeId() {
        return mRobotNoticeId;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getTime() {
        return mTime;
    }
}
