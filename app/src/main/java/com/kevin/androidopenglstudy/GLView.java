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


    public GLView(Context context, String x) {
        super(context);
        init(x);

    }


    private void init(String type) {
        setEGLContextClientVersion(2);
        GLRender glRender = new GLRender(type);
        setRenderer(glRender);
    }
}
