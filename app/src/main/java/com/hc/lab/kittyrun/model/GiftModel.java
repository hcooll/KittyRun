package com.hc.lab.kittyrun.model;

/**
 * Created by congwiny on 2017/4/17.
 * <p>
 * "gift": {
 * "lang": null,
 * "mini_icon": "http://s3-us-west-2.amazonaws.com/solomedia/upload/5543ffd8ce643aa59486f5b2f2f6af2b.png",
 * "diamond": 5,
 * "name": "กล้วยหอม",
 * "gift_id": 279,
 * "country": "CN",
 * "image": "http://s3-us-west-2.amazonaws.com/solomedia/upload/a558ccf5348d0b1af4fd8124062405e0.png",
 * "effect": "",
 * "exp": 50,
 * "type": 1,
 * "effect_md5": "",
 * "icon": "http://s3-us-west-2.amazonaws.com/solomedia/upload/4bac83933d85d097f733d4cde90816e9.png"
 * }
 */

public class GiftModel {
    public String giftIcon = "http://s3-us-west-2.amazonaws.com/solomedia/upload/5543ffd8ce643aa59486f5b2f2f6af2b.png";
    public String senderAvatar = "http://s3-us-west-2.amazonaws.com/solomedia/image/100097/avatar/deff9b4cfff9e2137d596b1c800bf06b.0";
    public int diamond = 5;
    public int count = 1;
    public int giftId = 279;
    public int userId = 100097;
    public int type = 1;
    public boolean isGuideGift = false; // 是否是引导礼物

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GiftModel giftModel = (GiftModel) o;

        if (diamond != giftModel.diamond) return false;
        if (count != giftModel.count) return false;
        if (giftId != giftModel.giftId) return false;
        if (userId != giftModel.userId) return false;
        if (type != giftModel.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = diamond;
        result = 31 * result + count;
        result = 31 * result + giftId;
        result = 31 * result + userId;
        result = 31 * result + type;
        return result;
    }

    public String getGiftIcon() {
        return giftIcon;
    }

    public void setGiftIcon(String giftIcon) {
        this.giftIcon = giftIcon;
    }

    public String getSenderAvatar() {
        return senderAvatar;
    }

    public void setSenderAvatar(String senderAvatar) {
        this.senderAvatar = senderAvatar;
    }

    public int getDiamond() {
        return diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getGiftId() {
        return giftId;
    }

    public void setGiftId(int giftId) {
        this.giftId = giftId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isGuideGift() {
        return isGuideGift;
    }

    public void setGuideGift(boolean guideGift) {
        isGuideGift = guideGift;
    }

    // 新手引导礼物
    public void setGuideGift(){
        setSenderAvatar("http://s3-us-west-2.amazonaws.com/solomedia/image/314451/avatar/48f9947bb15bacfe0d5f09e93b3ecda4.jpg");
        setCount(0);
        setDiamond(80);
        setGiftIcon("http://s3-us-west-2.amazonaws.com/solomedia/upload/resource/31a93616c0f34391aabf260afafd4306_mini_icon.png");
        setGiftId(4781);
        setType(3);
        setUserId(314451);
        setGuideGift(true);
    }

    public void setSecondGift(){
        setSenderAvatar("http://s3-us-west-2.amazonaws.com/solomedia/image/314451/avatar/48f9947bb15bacfe0d5f09e93b3ecda4.jpg");
        setCount(1);
        setDiamond(100);
        setGiftIcon("http://s3-us-west-2.amazonaws.com/solomedia/upload/resource/31a93616c0f34391aabf260afafd4306_mini_icon.png");
        setGiftId(4780);
        setType(3);
        setUserId(314451);
    }

    public void setThirdGift(){
        setSenderAvatar("http://s3-us-west-2.amazonaws.com/solomedia/image/314451/avatar/48f9947bb15bacfe0d5f09e93b3ecda4.jpg");
        setCount(1);
        setDiamond(50);
        setGiftIcon("http://s3-us-west-2.amazonaws.com/solomedia/upload/25d49b49382a59c931d923721a8a5816.png");
        setGiftId(287);
        setType(1);
        setUserId(314451);
    }
}
