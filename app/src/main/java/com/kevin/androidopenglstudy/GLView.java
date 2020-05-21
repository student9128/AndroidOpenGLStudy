package com.kevin.androidopenglstudy;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * Created by Kevin on 2020/5/21<br/>
 * Blog:http://student9128.top/
 * 公众号：竺小竹
 * Describe:<br/>
 */
public class GLView extends GLSurfaceView {
    public GLView(Context context) {
        this(context,null);
    }

    public GLView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setEGLContextClientVersion(2);
        GLRender glRender = new GLRender();
        setRenderer(glRender);
    }
}
