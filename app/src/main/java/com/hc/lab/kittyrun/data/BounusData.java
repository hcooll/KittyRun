package com.hc.lab.kittyrun.data;

/**
 * Created by congwiny on 2017/4/19.
 */

public class BounusData {

    private BonusStat bonusStat;

    class BonusStat {
        public int upto = 10; //单位
        public int times = 0; //Kitty 碰撞了多少次礼物
        public int addup = 0;//累积了到了多少单位
    }

    public BounusData() {
        bonusStat = new BonusStat();
    }

    public void resetBonusCount() {
        bonusStat.upto = 10;
        bonusStat.times = 0;
        bonusStat.addup = 0;
    }

    public int getBonus(int moonCost) {
        int bonus = 0;
        if (0 < moonCost && moonCost < 10) {
            bonusStat.addup += moonCost;
            if (bonusStat.addup >= bonusStat.upto) {
                bonus = 1;
                bonusStat.times += 1;
                bonusStat.upto = (bonusStat.times / 5 + 1) * 10;
                bonusStat.addup = 0;
            }
        } else {
            if (moonCost >= 10 && moonCost < 100) {
                bonus = 1;
            } else if (moonCost >= 100 && moonCost < 5000) {
                bonus = (int) (moonCost * 0.01);
            } else {
                if (moonCost >= 5000) {
                    bonus = 50;
                }
            }
        }
        return bonus;
    }

}
