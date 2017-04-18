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
    public String giftIcon = "http://s3-us-west-2.amazonaws.com/solomedia/upload/4bac83933d85d097f733d4cde90816e9.png";
    public String senderAvatar = "http://s3-us-west-2.amazonaws.com/solomedia/image/100097/avatar/deff9b4cfff9e2137d596b1c800bf06b.0";
    public int diamond = 5;
    public int count = 1;
    public int giftId = 279;
    public int userId = 100097;
    public int type = 1;

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
}
