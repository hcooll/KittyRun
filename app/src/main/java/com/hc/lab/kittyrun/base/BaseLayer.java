package com.hc.lab.kittyrun.base;

import android.app.Activity;

import com.hc.lab.kittyrun.R;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGSize;

public abstract class BaseLayer extends CCLayer {
	protected CGSize cgSize ;
	protected static SoundEngine mSoundEngine;

	static{
		mSoundEngine = SoundEngine.sharedEngine();
		mSoundEngine.preloadSound(getContext(), R.raw.bgm);
		mSoundEngine.preloadEffect(getContext(), R.raw.jump);
		mSoundEngine.preloadEffect(getContext(), R.raw.fall_down);
		mSoundEngine.preloadEffect(getContext(), R.raw.receive_gift);
	}

	public BaseLayer(){
		cgSize = CCDirector.sharedDirector().screenSize();
	}

	/**
	 * 获取上下文信息（Activity）
	 * @return
	 */
	protected static Activity getContext() {
		return CCDirector.sharedDirector().getActivity();
	}

	public CGSize getCgSize() {
		return cgSize;
	}

    public void playSound(int resId, boolean loop) {
        mSoundEngine.playSound(getContext(), resId, loop);
    }

    public void playEffect(int resId) {
        mSoundEngine.playEffect(getContext(), resId);
    }

	@Override
	public void onExit() {
		super.onExit();
		mSoundEngine.realesAllSounds();
		mSoundEngine.realesAllEffects();
	}


}
