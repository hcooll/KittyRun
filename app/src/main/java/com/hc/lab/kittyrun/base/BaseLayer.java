package com.hc.lab.kittyrun.base;

import android.app.Activity;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGSize;

public abstract class BaseLayer extends CCLayer {
	protected CGSize cgSize ;
	protected static SoundEngine engine;
	
	static{
		engine = SoundEngine.sharedEngine();
//		engine.preloadSound(getContext(), R.raw.go);
//		engine.preloadSound(getContext(), R.raw.day);
//		engine.preloadSound(getContext(), R.raw.night);
//		engine.preloadSound(getContext(), R.raw.onclick);
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

}
